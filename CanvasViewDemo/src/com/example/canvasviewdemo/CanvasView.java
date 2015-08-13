package com.example.canvasviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.R.animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class CanvasView extends View{
	
	private Camera mCamera;
	private Matrix mMatrix;
	private Matrix mMatrixCamera;
	
	private List<PaneDrawable> mPanes = new ArrayList<PaneDrawable>();
	private PaneDrawable mPane;
	
	private boolean mIsAnimating = false;
	private ObjectAnimator mAnimator;
	
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
		
		mAnimator = ObjectAnimator.ofFloat(this, "value", 1.0f, 0.0f);//TODO this will be setting time
		mAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		mAnimator.setRepeatMode(ObjectAnimator.REVERSE);
		mAnimator.setDuration(1000);
	}
	
	public void setPaneItems(List<PaneItem> panes){
		mPanes.clear();
		for(int i = 0; i < panes.size(); i++){
			mPanes.add(new PaneDrawable(panes.get(i)));
		}
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
			mMatrix.postTranslate(mPane.getTranslationX(), mPane.getTranslationY());
			
			//Now scale about its center
			//centX = mPane.getWidth()/2.0f + mPane.getTranslationX();
			//centY = mPane.getHeight()/2.0f + mPane.getTranslationY();	
			//mMatrix.postScale(mPane.getScale(), mPane.getScale(), centX, centY);			
			//canvas.setMatrix(mMatrix);
			
			//Translate so that the center of the pane is at (0,0) so that the camera rotations
			//can be applied
			//mMatrix.postTranslate(-centX, -centY);
			
			//This will basically give us a matrix that was rotated about 0,0,-8
			//Which is not about the center of the pane (unless by coincidence)			
			//mCamera.setLocation(0, 0, mPane.getTranslationZ());
			//mCamera.rotate(mPane.getThetaX(), mPane.getThetaY(), mPane.getThetaZ());
			//mCamera.getMatrix(mMatrixCamera);//this resets mMatrix to the new matrix
			
			//mMatrix.postConcat(mMatrixCamera);
			
			//Now move the pane back to its final position
			//mMatrix.postTranslate(centX, centY);	
			
			//Apply the matrix to the canvas
			canvas.setMatrix(mMatrix);
			
			//And finally draw the path
			canvas.drawPath(mPane.getPath(), mPane.getPaint());			
			
			//Restore to the blank slate
			mCamera.restore();
			canvas.restore();
		}
	}
	
	public boolean isAnimating(){
		return mIsAnimating;
	}
	
	public void startAnimating(){
		//if(mAnimator.)
		
		BounceInterpolator interpolator = new BounceInterpolator();
		//interpolator.
		
		//Each pane can have its own animator that is not running but gets updated by the global animator
		//then, the animator 
		//ObjectAnimator animator = ObjectAnimator.ofFloat(target, property, values);
		//animator.get
	}
	
	public void stopAnimating(){
		
	}
}
