package com.example.canvasviewdemo;

import java.util.List;

public class SpinningPanesAnimator extends PanesAnimator{
	
	public SpinningPanesAnimator(long duration){
		super(duration);
	}
	
	@Override
	public void setPaneAnimationValues(List<PaneDrawable> panes) {
		
		for(int i = 0; i < panes.size(); i++){
			PaneDrawable pane = panes.get(i);
			
			//By default, let's say all panes are spinning for the duration of the animation
			pane.setTimeFrame(0, mDuration);
			
			//Get a handle to the original values
			PaneItem p = pane.getPaneItem();
			float rotX = p.getRotation() + i*360 + (i+1)*50 + (i+5)*(i+2);
			//pane.setAlphaValues(values);
			//pane.setColorValues(values);
			//pane.setScaleValues(values);
			pane.setThetaXValues(new float[]{p.getRotation(), rotX, -rotX, p.getRotation()});
			//pane.setThetaYValues(values);
			//pane.setThetaZValues(values);
			//pane.setTranslationXValues(values);
			//pane.setTranslationYValues(values);
			//pane.setTranslationZValues(values);
			
			//DO NOT forget to update the animator!
			pane.updateAnimator();
		}
	}
}
