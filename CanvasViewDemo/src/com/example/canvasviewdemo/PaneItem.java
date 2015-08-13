package com.example.canvasviewdemo;

import android.graphics.Path;

public class PaneItem {
	
	public static final int RECTANGLE = 0;
	
	private int mShape;
	private int mAlpha;
	private int mColor;
	private float mScale;
	private float mLeft;
	private float mTop;
	private float mCameraHeight;// -8 is default
	private float mWidth;
	private float mHeight;
	private float mRotationX;
	private float mRotationY;
	private float mRotation;
	private boolean mIsBeingEdited = false;
	
	private Path mPath;
		
	public PaneItem(int shape, int alpha, int color, float scale, float left, float top, float cameraHeight,
			float width, float height, float rotationX, float rotationY, float rotationZ){
		mShape = shape;
		mAlpha = alpha;
		mColor = color;
		mScale = scale;
		mLeft = left;
		mTop = top;
		mCameraHeight = cameraHeight;
		mWidth = width;
		mHeight = height;
		mRotationX = rotationX;
		mRotationY = rotationY;
		mRotation = rotationZ;
		
		mPath = createPath(mShape, mWidth, mHeight);
	}
	
	public float getLeft() {
		return mLeft;
	}
	public void setLeft(float left) {
		mLeft = left;
	}
	public float getTop() {
		return mTop;
	}
	public void setTop(float top) {
		mTop = top;
	}
	public float getCameraHeight(){
		return mCameraHeight;
	}
	public void setCameraHeight(float height){
		mCameraHeight = height;
	}	
	public float getWidth() {
		return mWidth;
	}
	public void setWidth(float width) {
		if(mWidth == width){
			return;
		}
		mWidth = width;
		mPath = createPath(mShape, mWidth, mHeight);
	}
	public float getHeight() {
		return mHeight;
	}
	public void setHeight(float height) {
		if(mHeight == height){
			return;
		}
		mHeight = height;
		mPath = createPath(mShape, mWidth, mHeight);
	}
	public float getScale() {
		return mScale;
	}
	public void setScale(float scale) {
		mScale = scale;
	}
	public float getRotation() {
		return mRotation;
	}
	public void setRotation(float rotation) {
		mRotation = rotation;
	}
	public float getRotationX() {
		return mRotationX;
	}
	public void setRotationX(float rotationX) {
		mRotationX = rotationX;
	}
	public float getRotationY() {
		return mRotationY;
	}
	public void setRotationY(float rotationY) {
		mRotationY = rotationY;
	}
	public int getColor() {
		return mColor;
	}
	public void setColor(int color) {
		mColor = color;
	}
	public int getAlpha() {
		return mAlpha;
	}
	public void setAlpha(int alpha) {
		mAlpha = alpha;
	}
	
	private static Path createPath(int shape, float width, float height){
		
		Path path = new Path();
		
		//All paths assume 0,0 origin (ie if a rectangle and left != 0, first point is 0,0 and left is accounted for
		//using a transformation			
		
		switch(shape){
		case RECTANGLE:
		default:
			path.moveTo(0,0);			
			path.lineTo(width, 0);
			path.lineTo(width, height);
			path.lineTo(0, height);
			path.lineTo(0,0);	
			break;
		}
		
		return path;
	}
	
	public Path getPath(){
		return mPath;
	}
	
	public void setIsBeingEdited(boolean isBeingEdited){
		mIsBeingEdited = isBeingEdited;
	}
	
	public boolean isBeingEdited(){
		return mIsBeingEdited;
	}
}
