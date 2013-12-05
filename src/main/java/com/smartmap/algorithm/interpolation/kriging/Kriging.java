package com.smartmap.algorithm.interpolation.kriging;

public class Kriging {
	public double[] valueList = {1,2,3};
	
	/*
	 * 计算平均值
	 */
	public double getAverage(double[] values)
	{
		double val = 0;
		for(int i=0; i<values.length; i++)
		{
			val += values[i];
		}
		val = val / values.length;
		return val;
	}
	
	/*
	 * 计算方差
	 */
	public double getVariance(double[] values)
	{
		double average = getAverage(values);
		double val = 0;
		for(int i=0; i<values.length; i++)
		{
			//离差
			double deviation = values[i] - average;
			val += Math.pow(deviation, 2);
		}
		val = val / values.length;
		return val;
	}
	
	/*
	 * 计算标准差
	 */
	public double getStandardDeviation(double[] values)
	{
		double variance = getVariance(values);
		double val = Math.sqrt(variance);
		return val;
	}
	
	
	
	
	
}
