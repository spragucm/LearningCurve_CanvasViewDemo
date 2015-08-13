package com.example.canvasviewdemo;

import android.animation.ArgbEvaluator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.Log;

public class PaneDrawable {
	
	private static final String TAG = PaneDrawable.class.getSimpleName();
	private static final String ALPHA = "alpha";
	private static final String COLOR = "color";
	private static final String SCALE = "scale";
	private static final String THETAX = "thetax";
	private static final String THETAY = "thetay";
	private static final String THETAZ = "thetaz";
	private static final String TRANSX = "transx";
	private static final String TRANSY = "transy";
	private static final String TRANSZ = "transz";
		
	/**This animator is only used to update transitional values for animation, but doesn't get started*/
	private PropertyValuesHolder mPvhAlpha;
	private PropertyValuesHolder mPvhColor;	
	private PropertyValuesHolder mPvhScale;
	private	PropertyValuesHolder mPvhThetaX;
	private	PropertyValuesHolder mPvhThetaY;
	private	PropertyValuesHolder mPvhThetaZ;
	private	PropertyValuesHolder mPvhTransX;
	private	PropertyValuesHolder mPvhTransY;
	private	PropertyValuesHolder mPvhTransZ;
	private ValueAnimator mAnimator;
	
	/** The following values can change due to animation even thouh the PaneItem's values are static*/
	private int mAlpha;
	private int mColor; 	
	private float mScale;
	private float mThetaX;
	private float mThetaY;
	private float mThetaZ;
	private float mTranslationX;
	private float mTranslationY;
	private float mTranslationZ;
	
	private long mStartTime;
	private long mTime;
	private long mEndTime;
	private long mDuration;
	
	/**This item holds the pane's original information, which is never updated */
	private PaneItem mPaneItem;
	
	private Paint mPaint;
	
	public PaneDrawable(PaneItem item){
		mPaneItem = item;
		PaneItem p = mPaneItem;
		
		mPaint = new Paint();
		mPaint.setAlpha(p.getAlpha());
		mPaint.setColor(p.getColor());
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setStyle(Style.FILL);
		
		//There is no animation to start with
		setAlphaValues(new int[]{p.getAlpha(), p.getAlpha()});
		setColorValues(new int[]{p.getColor(), p.getColor()});
		setScaleValues(new float[]{p.getScale(), p.getScale()});
		setThetaXValues(new float[]{p.getRotationX(), p.getRotationX()});
		setThetaYValues(new float[]{p.getRotationY(), p.getRotationY()});
		setThetaZValues(new float[]{p.getRotation(), p.getRotation()});
		setTranslationXValues(new float[]{p.getLeft(), p.getLeft()});
		setTranslationYValues(new float[]{p.getTop(), p.getTop()});
		setTranslationZValues(new float[]{p.getCameraHeight(), p.getCameraHeight()});
		updateAnimator();

		//Default duration of 1000ms and calculate values at 0
		setTimeFrame(0, 1000);	
		setCurrentTime(0);
	}
	
	public void setInterpolator(TimeInterpolator interpolator){
		mAnimator.setInterpolator(interpolator);
	}
	
	/**Set the start and end time for this pane's animation, relative to the global time*/
	public void setTimeFrame(long startTime, long endTime){
		mStartTime = startTime;
		mEndTime = endTime;
		mDuration = mEndTime - mStartTime;
		mAnimator.setDuration(mDuration);
	}
	
	public void setCurrentTime(float time){
		//Update the local time based on the global time
		mTime = (int) MathUtils.interpolate(0, mDuration, mStartTime, time, mEndTime);
		mTime = mTime < mStartTime ? mStartTime : mTime;
		mTime = mTime > mEndTime ? mEndTime : mTime;
		
		//Move the interpolator to the new time in order to update the values
		mAnimator.setCurrentPlayTime(mTime);
				
		mAlpha = (Integer) mAnimator.getAnimatedValue(ALPHA);
		mColor = (Integer) mAnimator.getAnimatedValue(COLOR);
		mScale = (Float) mAnimator.getAnimatedValue(SCALE);
		mThetaX = (Float) mAnimator.getAnimatedValue(THETAX);
		mThetaY = (Float) mAnimator.getAnimatedValue(THETAY);
		mThetaZ = (Float) mAnimator.getAnimatedValue(THETAZ);
		mTranslationX = (Float) mAnimator.getAnimatedValue(TRANSX);
		mTranslationY = (Float) mAnimator.getAnimatedValue(TRANSY);
		mTranslationZ = (Float) mAnimator.getAnimatedValue(TRANSZ);
		
		mPaint.setAlpha(mAlpha);
		mPaint.setColor(mColor);
		
		Log.d(TAG, "setCurrentTime time:" + time +",localTime:" + mTime + ",alpha:" + mAlpha + ",color:" + mColor
				+ ",scale:" + mScale + ",thetaX:" + mThetaX + ",thetaY:" + mThetaY + ",thetaZ:" + mThetaZ
				+ ",transX" + mTranslationX + ",transY:" + mTranslationY + ",transZ:" + mTranslationZ);
	}	
		
	public Paint getPaint(){
		return mPaint;
	}
	
	public float getScale(){
		return mScale;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setScaleValues(float[] values){
		mPvhScale = PropertyValuesHolder.ofFloat(SCALE, values);
	}
	
	public int getAlpha(){
		return mAlpha;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setAlphaValues(int[] values){
		mPvhAlpha = PropertyValuesHolder.ofInt(ALPHA, values);
	}
	
	public int getColor(){
		return mColor;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setColorValues(int[] values){
		mPvhColor = PropertyValuesHolder.ofInt(COLOR, values);
		mPvhColor.setEvaluator(new ArgbEvaluator());//Change so that color values are correctly evaluated
	}
		
	public float getThetaX(){
		return mThetaX;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setThetaXValues(float[] values){
		Log.d(TAG,"setThetaXValues:" + values[0] +"," +values[1]);
		mPvhThetaX = PropertyValuesHolder.ofFloat(THETAX, values);
	}
	
	public float getThetaY(){
		return mThetaY;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setThetaYValues(float[] values){
		mPvhThetaY = PropertyValuesHolder.ofFloat(THETAY, values);
	}
	
	public float getThetaZ(){
		return mThetaZ;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setThetaZValues(float[] values){
		mPvhThetaZ = PropertyValuesHolder.ofFloat(THETAZ, values);
	}
	
	public float getTranslationX(){
		return mTranslationX;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setTranslationXValues(float[] values){
		mPvhTransX = PropertyValuesHolder.ofFloat(TRANSX, values);
	}
	
	public float getTranslationY(){
		return mTranslationY;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setTranslationYValues(float[] values){
		mPvhTransY = PropertyValuesHolder.ofFloat(TRANSY, values);
	}
	
	public float getTranslationZ(){
		return mTranslationZ;
	}
	/**These values will only be applied to the animator after updateAnimator is called*/
	public void setTranslationZValues(float[] values){
		mPvhTransZ = PropertyValuesHolder.ofFloat(TRANSZ, values);
	}
	
	public void updateAnimator(){
		mAnimator = ValueAnimator.ofPropertyValuesHolder(mPvhAlpha, mPvhColor, mPvhThetaX,
			mPvhThetaY, mPvhThetaZ, mPvhTransX, mPvhTransY, mPvhTransZ, mPvhScale);
		mAnimator.setDuration(mDuration);
	}
	
	public float getWidth(){
		return mPaneItem.getWidth();
	}
	
	public float getHeight(){
		return mPaneItem.getHeight();
	}
	
	public Path getPath(){
		return mPaneItem.getPath();
	}
	
	public PaneItem getPaneItem(){
		return mPaneItem;
	}
}
