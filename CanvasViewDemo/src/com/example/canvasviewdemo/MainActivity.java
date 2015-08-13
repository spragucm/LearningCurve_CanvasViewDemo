/**
 * Objective of this demo app:
 * 1)Use a canvas view to show panes
 * 2)Panes need to rotateX,rotateY,rotateZ,translateX,translateY, translateZ(?),change
 * 	 alpha, 
 * 3)Animate all panes at different times within a given time-frame, and not in sync
 * 4)Animations need to be controlled by a single animator that can be swapped out easily
 * 5)Drag a pane around to prove concept
 */
package com.example.canvasviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity 
	implements OnClickListener{
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	//Galaxy Tab 7: w=600 h=1024(976 after lower info bar)
	//GS4: w=1080 h=1920
	
	private CanvasView mCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        
        mCanvasView = (CanvasView)findViewById(R.id.canvas);
        
        //Uncomment for Galaxy Tab
        float width = 400;//ScreenW = 600
        float height = 594;//ScreenH = 1024
        float left = (600 - width)/2.0f;//To center rectangle horizontally on screen
        float top = (976 - height)/2.0f;//To center rectangle vertically on screen
        
        //Uncomment for GS4
        //float screenW = 1080;
        //float screenH = 1920;
        //float width = 600;//ScreenW = 1080
        //float height = screenW - 20;//ScreenH = 1920
        //float left = (screenW - width)/2.0f;//To center rectangle horizontally on screen
        //float top = (screenH - height)/2.0f;//To center rectangle vertically on screen
        
        List<PaneItem> panes = new ArrayList<PaneItem>();
        
        //The first pane
    	PaneItem pane = new PaneItem(PaneItem.RECTANGLE, 255, Color.rgb(0,255,0), 1.5f, left, top, -8.0f, width, height, 0.0f, 0, 0);  	
    	panes.add(pane); 
    	
    	//The second pane
    	PaneItem pane2 = new PaneItem(PaneItem.RECTANGLE, 255, Color.rgb(0,0, 255), 0.5f, left, top, -8.0f, width, height, 0, 0, 0); 
    	panes.add(pane2);
        
        mCanvasView.setPaneItems(panes);
        
        mCanvasView.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");
		switch(v.getId()){
		case R.id.canvas:
			if(mCanvasView.isAnimating()){
				mCanvasView.stopAnimating();
			}else{
				mCanvasView.startAnimating();
			}
			break;
		}
	}
}
