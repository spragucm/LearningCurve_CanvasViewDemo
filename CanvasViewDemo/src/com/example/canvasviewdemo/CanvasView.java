package com.example.canvasviewdemo;

import java.util.List;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View{
	
	private Camera mCamera;
	private Matrix mMatrix;
	private Matrix mMatrixCamera;
	
	private List<PaneItem> mPanes = null;
	private PaneItem mPane;
	
	public CanvasView(Context context) {
		super(context);
		init();
	}
	
	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	private void init(){
		setBackgroundColor(0xFFFFFFFF);
		
		mCamera = new Camera();
		mMatrix = new Matrix();
		mMatrixCamera = new Matrix();
	}
	
	public void setPaneItems(List<PaneItem> panes){
		mPanes = panes;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		if(mPanes == null){
			return;
		}
		
		float centX;
		float centY;
		for(int i = 0; i < mPanes.size(); i++){
			mPane = mPanes.get(i);
			
			//Assume that every pane will start from a blank slate
			canvas.save();
			mCamera.save();
			
			//Start anew by resetting to unity matrix
			mMatrix.reset();
			
			//Pane's top left corner is at (0,0) so translate to its target position
			mMatrix.postTranslate(mPane.getLeft(), mPane.getTop());
			
			//Now scale about its center
			centX = mPane.getWidth()/2.0f + mPane.getLeft();
			centY = mPane.getHeight()/2.0f + mPane.getTop();	
			mMatrix.postScale(mPane.getScale(), mPane.getScale(), centX, centY);			
			canvas.setMatrix(mMatrix);
			
			//Translate so that the center of the pane is at (0,0) so that the camera rotations
			//can be applied
			mMatrix.postTranslate(-centX, -centY);
			
			//This will basically give us a matrix that was rotated about 0,0,-8
			//Which is not about the center of the pane (unless by coincidence)			
			mCamera.setLocation(0, 0, -8);
			mCamera.rotate(mPane.getRotationX(), mPane.getRotationY(), mPane.getRotation());
			mCamera.getMatrix(mMatrixCamera);//this resets mMatrix to the new matrix
			
			mMatrix.postConcat(mMatrixCamera);
			
			//Now move the pane back to its final position
			mMatrix.postTranslate(centX, centY);	
			
			//Apply the matrix to the canvas
			canvas.setMatrix(mMatrix);
			
			//And finally draw the path
			canvas.drawPath(mPane.getPath(), mPane.getPaint());			
			
			//Restore to the blank slate
			mCamera.restore();
			canvas.restore();
		}
	}
}
