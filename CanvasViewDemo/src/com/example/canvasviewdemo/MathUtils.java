package com.example.canvasviewdemo;

import java.math.BigDecimal;
import java.util.Random;


public class MathUtils {
	
	
	/**
	 * Given a value round it at a given decimal place. This should round down if <=n.xxx4 and round up if n.xxx5>= 
	 * @param value
	 * @param precision
	 * @return
	 */	
	public static double round(double value, int precision){
		return (double)Math.round(value * precision) / precision;
	}
	
	public static double roundUp(double value, int precision){
		return (double)Math.ceil(value * precision) / precision;
	}
	
	public static double roundDown(double value, int precision){
		return (double)Math.floor(value * precision) / precision;
	}
	
	public static double roundBigDecimal(double value, int numberDecimalPlaces){
		BigDecimal bd = new BigDecimal(String.valueOf(value));
		bd = bd.setScale(numberDecimalPlaces, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
	public static double roundUpBigDecimal(double value, int numberDecimalPlaces){
		BigDecimal bd = new BigDecimal(String.valueOf(value));
		bd = bd.setScale(numberDecimalPlaces, BigDecimal.ROUND_CEILING);
		return bd.doubleValue();
	}
	
	public static double roundDownBigDecimal(double value, int numberDecimalPlaces){
		BigDecimal bd = new BigDecimal(String.valueOf(value));
		bd = bd.setScale(numberDecimalPlaces, BigDecimal.ROUND_FLOOR);
		return bd.doubleValue();
	}
	
	/**
	 * Use this when being used on the same thread since it uses one instance of Random.
	 * When using on many threads each thread should have their own instance of Random
	 * @param min
	 * @param max
	 * @param createNew True if using for a new thread
	 * @return
	 */
	public static double randomNumberInRange2(double min, double max, boolean createNew){
		Double rand;
		if(createNew){
			rand = new Random().nextDouble();
		}else{
			rand = Math.random();
		}
		return min + rand*(max - min);
	}
	
	/**
	 * Get a random number between the ints, including min and max as solutions.
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomNumberInRange(int min, int max){
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	public static int sign(int i) {
	    if (i == 0) return 0;
	    if (i >> 31 != 0) return -1;
	    return +1;
	}
	
	public static int sign(long i) {
	    if (i == 0) return 0;
	    if (i >> 63 != 0) return -1;
	    return +1;
	}
	
	public static int sign(double f) {
	    if (f != f) throw new IllegalArgumentException("NaN");
	    if (f == 0) return 0;
	    f *= Double.POSITIVE_INFINITY;
	    if (f == Double.POSITIVE_INFINITY) return +1;
	    if (f == Double.NEGATIVE_INFINITY) return -1;

	    //this should never be reached, but I've been wrong before...
	    throw new IllegalArgumentException("Unfathomed double");
	}
	
	/** 
	 * A linear interpolator for:
	 * valI = val0 + (valF - val0) * (rangeI - 0) / (1 - 0)
	 **/
	public static float interpolate(float val0, float valF, float rangeI){
		return val0 + (valF - val0) * rangeI;//0 <= mValue <= 1 ...so (mValue - 0)/(1 - 0) ...hence *mValue	
	}
	
	/** 
	 * A linear interpolator for:
	 * valI = val0 + (valF - val0) * (rangeI - range0) / (rangeF - range0)
	 **/
	public static float interpolate(float val0, float valF, float range0, float rangeI, float rangeF){	
		return val0 + (valF - val0) * (rangeI - range0) / (rangeF - range0);//0 <= mValue <= 1 ...so (mValue - 0)/(1 - 0) ...hence *mValue		
	}
}
