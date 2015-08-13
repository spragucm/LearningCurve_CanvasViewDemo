package com.example.canvasviewdemo;

import java.util.List;

public abstract class PanesAnimator {
	
	protected long mDuration;
	
	public PanesAnimator(long duration){
		mDuration = duration;
	}
	
	public long getDuration(){
		return mDuration;
	}
	
	public void setDuration(long duration){
		mDuration = duration;
	}
	
	public abstract void setPaneAnimationValues(List<PaneDrawable> panes);
}
