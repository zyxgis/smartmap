package com.smartmap.algorithm.equation.differential.partial.ellipsoidal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

public class PoissonDerivativeBoundary {
	public static void main(String[] args) {
		int m = 64; int n = 32;		
		double minX = 0; 
		double maxX = 2;
		double dx = (maxX - minX) / m;
		double minY = 0;
		double maxY = 1;
		double dy = (maxY - minY) / n;
		//		
		double xx[][] = new double[m+1][n+1];
		//生成X
		System.out.println("----------------------------------------");
		for(int j=0; j<m+1; j++)
		{			
			double value = dx * j;
			System.out.print(value + " ");
		}
		System.out.println("");
		//生成Y
		System.out.println("----------------------------------------");
		for(int j=0; j<n+1; j++)
		{
			double value = dy * j;
			System.out.print(value + " ");
		}
		System.out.println("");
		//迭代运算
		double error;
		while(true)
		{
			error = gaussSeidelCompute(xx, m, n, dx, dy);
			System.out.println("error: "+error);
			if(error < 0.00000000001)
			{
				break;
			}
		}
		//输出结果
		System.out.println("--------------------------------");
		for(int k=0; k<n+1; k++)
		{
			for(int j=0; j<m+1; j++)
			{			
				System.out.printf(" %8.6f", xx[j][k]);
			}	
			System.out.println(";");
		}		
	}

	
	public static double gaussSeidelCompute(double[][] xx, int m, int n, double dx, double dy)
	{
		double dxdy = (1.0/Math.pow(dy, 2) + 1.0/Math.pow(dx, 2)) * 2;
		double error = 0;
		//
		for(int i=0; i<m+1; i++)
		{
			for(int j=0; j<n+1; j++)
			{
				if(i==0)
				{
					if(j==0)
					{
						
					}
					else if(j==n)
					{
						
					}
					else
					{
						
					}						
				}
				else if(i==m)
				{
					if(j==0)
					{
						
					}
					else if(j==n)
					{
						
					}
					else
					{
						
					}
				}
				else
				{
					if(j==0)
					{
						
					}
					else if(j==n)
					{
						
					}
					else
					{
						double oldValue = xx[i][j];
						double x = 0 + dx * i;
						double y = 0 + dy * j;
						double f = getF(x, y);
						xx[i][j] = (f + xx[i][j-1]*(1.0/Math.pow(dy, 2)) + xx[i-1][j]*(1.0/Math.pow(dx, 2)) + xx[i+1][j]*(1.0/Math.pow(dx, 2)) + xx[i][j+1]*(1.0/Math.pow(dy, 2)));
						xx[i][j] = xx[i][j] / dxdy;
						oldValue = Math.abs(xx[i][j] - oldValue);
						if(oldValue > error)
							error = oldValue;
					}					
				}
			}
		}
		return error;
	}
	
	public static void outputAA(double[][] aa, int elementCount, String filePath, String fileName)
    {
		try
		{
			FileOutputStream fileStream = new FileOutputStream(filePath + "\\"+fileName+".txt", false);
			OutputStream outputStream = fileStream;
			StringWriter streamWriter=new StringWriter();
			
			for(int j=0; j<elementCount; j++)
			{
				for(int k=0; k<elementCount; k++)
				{
					streamWriter.write(String.format("	%8.6f", aa[j][k]));	 
				}
				streamWriter.write("\n");
			}
	        char[] linearRingStringCharArray = streamWriter.toString().toCharArray();
	        for(int i=0; i<linearRingStringCharArray.length; i++)
	        {
	        	outputStream.write(linearRingStringCharArray[i]);
	        }
	        fileStream.close();		
			streamWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public static double getF(double x, double y)
	{
		double returnValue = 0;
		returnValue = (Math.pow(Math.PI, 2) - 1) * Math.exp(x) * Math.sin(Math.PI * y); 
		return returnValue;
	}
	
	public static double getZeroXU(double y)
	{
		double returnValue = 0;
		returnValue = Math.sin(Math.PI * y); 
		return returnValue;
	}
	
	public static double getTwoXU(double y)
	{
		double returnValue = 0;
		returnValue = Math.exp(2) * Math.sin(Math.PI * y); 
		return returnValue;
	}
	
	public static double getZeroYU(double y)
	{
		double returnValue = 0;
		return returnValue;
	}
	
	public static double getOneYU(double y)
	{
		double returnValue = 0;
		return returnValue;
	}

}


