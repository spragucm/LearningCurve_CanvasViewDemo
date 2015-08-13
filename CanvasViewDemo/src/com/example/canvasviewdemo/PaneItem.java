package com.example.canvasviewdemo;

import android.graphics.Paint;
import android.graphics.Path;

public class PaneItem {
	
	public static final int RECTANGLE = 0;
	
	private int mShape = RECTANGLE;
	private float mLeft;
	private float mTop;
	private float mWidth;
	private float mHeight;
	private float mScale = 1.0f;
	private float mRotation = 0;
	private float mRotationX = 0;
	private float mRotationY = 0;
	
	private Path mPath;
	private Paint mPaint = new Paint();
	
	public PaneItem(int shape, float left, float top, float width, float height){
		mLeft = left;
		mTop = top;
		mWidth = width;
		mHeight = height;
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
		return mPaint.getColor();
	}
	public void setColor(int color) {
		mPaint.setColor(color);
	}
	public int getAlpha() {
		return mPaint.getAlpha();
	}
	public void setAlpha(int alpha) {
		mPaint.setAlpha(alpha);
	}
	
	public Paint getPaint(){
		return mPaint;
	}
	
	public void setPaint(Paint paint){
		mPaint = paint;
	}
	
	public Path getPath(){
		return mPath;
	}
	
	public void setPath(Path path){
		mPath = path;
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
}
