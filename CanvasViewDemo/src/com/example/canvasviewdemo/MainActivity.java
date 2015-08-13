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
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	//Galaxy Tab 7: w=600 h=1024(976 after lower info bar)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_main);
        
        CanvasView canvasView = (CanvasView)findViewById(R.id.canvas);
        
        float width = 400;//ScreenW = 600
        float height = 594;//ScreenH = 1024
        float left = (600 - width)/2.0f;//To center rectangle horizontally on screen
        float top = (976 - height)/2.0f;//To center rectangle vertically on screen
        
        Random rand = new Random();
        List<PaneItem> panes = new ArrayList<PaneItem>();
       
    	PaneItem pane = new PaneItem(PaneItem.RECTANGLE, left, top, width, height);
    	
    	pane.setColor(Color.rgb(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	pane.setAlpha(255);
    	
    	pane.setRotation(0);
    	pane.setRotationX(45);
    	pane.setRotationY(45);
    	
    	pane.setScale(0.5f);
    	
    	panes.add(pane);    
        
        canvasView.setPaneItems(panes);
    }
}
