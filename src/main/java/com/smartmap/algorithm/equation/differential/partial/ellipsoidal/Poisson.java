package com.smartmap.algorithm.equation.differential.partial.ellipsoidal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

public class Poisson {
	public static void main(String[] args) {
		int m = 64; int n = 32;
		int elementCount = (m-1)*(n-1);
		double minX = 0; 
		double maxX = 2;
		double dx = (maxX - minX) / m;
		double minY = 0;
		double maxY = 1;
		double dy = (maxY - minY) / n;
		//
		double h1 = (maxX - minX) / m;
		double h2 = (maxY - minY) / n;
		double ccOne = -(1/Math.pow(h1, 2));
		double ccTwo = ((1/Math.pow(h2, 2)) + (1/Math.pow(h1, 2)))*2;
		double aa[][];
		double dd[] = new double[elementCount];
		double xx[][] = new double[n-1][m-1];
		double bb[];
		//
		System.out.println("----------------------------------------");
		for(int j=1; j<m; j++)
		{			
			double value = dx * j;
			System.out.print(value + " ");
		}
		System.out.println("");
		System.out.println("----------------------------------------");
		for(int j=1; j<n; j++)
		{			
			double value = dy * j;
			System.out.print(value + " ");
		}
		System.out.println("");
		//
		
		//建立矩阵系数
		aa= getAA(n, m, ccOne, ccTwo, h1, h2);
		bb = getBB(n, m, h1, h2);
		
		dd=jacobiMethod(aa, bb, dd);
		//outputAA(aa, elementCount, "d:\\", "aa");
		System.out.println("--------------------------------");
		for(int j=0; j<n-1; j++)
		{
			for(int k=0; k<m-1; k++)
			{
				xx[j][k] = dd[j*(m-1)+k];
				System.out.printf(" %8.6f", xx[j][k]);
			}	
			System.out.println(";");
		}		
		
		/*
		System.out.println("-----------------------------");
		for(int j=0; j<elementCount; j++)
		{
			for(int k=0; k<elementCount; k++)
			{
				System.out.printf(" %8.6f", aa[j][k]);
			}
			System.out.println("");
		}
		*/
		/*
		System.out.println("-----------------------------");
		for(int j=0; j<elementCount; j++)
		{
			System.out.printf("	%8.6f", bb[j]);			
			System.out.println("");
		}
		*/
		//解矩阵
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double[][] getAA(int n, int m, double ccOne, double ccTwo, double h1, double h2)
	{
		int elementCount = (m-1)*(n-1);
		double aa[][] = new double[elementCount][elementCount];
		double cc[][] = getCC(m, ccOne, ccTwo);
		double dd[][] = getDD(m, ccOne, ccTwo);
		for(int i=0; i<n-1; i++)
		{
			int startRow = i * (m - 1);
			int startColumn = i * (m - 1);
			//
			if(i==0)
			{
				//CC
				for(int j=0; j<m-1; j++)
				{
					for(int k=0; k<m-1; k++)
					{
						aa[startRow+j][startColumn+k] = cc[j][k];
					}
				}
				//DD
				for(int j=0; j<m-1; j++)
				{
					for(int k=0; k<m-1; k++)
					{
						aa[startRow+j][startColumn+(m-1)+k] = dd[j][k];
					}
				}
				
			}
			else if(i==n-2)
			{				
				//DD
				for(int j=0; j<m-1; j++)
				{
					for(int k=0; k<m-1; k++)
					{
						aa[startRow+j][startColumn-(m-1)+k] = dd[j][k];
					}
				}
				//CC
				for(int j=0; j<m-1; j++)
				{
					for(int k=0; k<m-1; k++)
					{
						aa[startRow+j][startColumn+k] = cc[j][k];
					}
				}
			}
			else
			{
				//DD
				for(int j=0; j<m-1; j++)
				{
					for(int k=0; k<m-1; k++)
					{
						aa[startRow+j][startColumn-(m-1)+k] = dd[j][k];
					}
				}
				//CC
				for(int j=0; j<m-1; j++)
				{
					for(int k=0; k<m-1; k++)
					{
						aa[startRow+j][startColumn+k] = cc[j][k];
					}
				}
				//DD
				for(int j=0; j<m-1; j++)
				{
					for(int k=0; k<m-1; k++)
					{
						aa[startRow+j][startColumn+(m-1)+k] = dd[j][k];
					}
				}
			}
		}
		
		return aa;
	}
	
	
	public static double[] getBB(int n, int m, double h1, double h2)
	{
		double minX = 0; 
		double maxX = 2;
		double minY = 0;
		double maxY = 1;		
		//
		int elementCount = (m-1)*(n-1);
		double bb[] = new double[elementCount];
		for(int i=0; i<n-1; i++)
		{
			int startRow = i * (m - 1);
			for(int j=0; j<m-1; j++)
			{
				double x = minX + h1 * j;
				double y = minY + h2 * i;
				bb[startRow+j]=getF(x + h1, y + h2);
				if(j==0)
				{
					bb[startRow+j] += getZeroXU(y + h2)*(1/Math.pow(h1, 2));
				}
				else if(j==m-2)
				{
					bb[startRow+j] += getTwoXU(y + h2)*(1/Math.pow(h1, 2));
				}					
			}
		}
		return bb;
	}
	
	public static double[][] getCC(int m, double ccOne, double ccTwo)
	{
		double cc[][] = new double[m-1][m-1];
		for(int i=0; i<m-1; i++)
		{
			if(i==0)
			{
				cc[i][i]=ccTwo;
				cc[i][i+1]=ccOne;
			}
			else if(i==m-2)
			{
				cc[i][i-1]=ccOne;
				cc[i][i]=ccTwo;
			}
			else
			{
				cc[i][i-1]=ccOne;
				cc[i][i]=ccTwo;
				cc[i][i+1]=ccOne;
			}
		}		
		return cc;
	}
	
	public static double[][] getDD(int m, double ccOne, double ccTwo)
	{
		double dd[][] = new double[m-1][m-1];
		for(int i=0; i<m-1; i++)
		{
			dd[i][i]=ccOne;
		}
		return dd;
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
	
	public static double[] jacobiMethod(double[][] aa, double[] bb, double[] xx)
	{
		int n = bb.length;
		double[] yy = new double[n];
		double EPSON = 0.00000000001;
		int index = 0;
		boolean flag = true;
		while(true)
		{
			index++;
			for(int i=0; i<n; i++)
			{
				yy[i] = bb[i];
				/*
				for(int j=0; j<n; j++)
				{
					yy[i] = yy[i]-aa[i][j]*xx[j];
				}
				yy[i] = (yy[i]+aa[i][i]*xx[i]) / aa[i][i];
				*/
				for(int j=0; j<n; j++)
				{
					if(i!=j)
					{
						yy[i] = yy[i]-aa[i][j]*xx[j];
					}
				}
				yy[i] = yy[i] / aa[i][i];
			}
			//判断是否停止
			flag = true;
			for(int i=0; i<n; i++)
			{
				if(Math.abs(yy[i]-xx[i])>EPSON)
				{
					flag = false;
				}
			}
			if(flag == true)
			{
				break;
			}
			else
			{
				//System.out.println("------------------------------");
				//System.out.print(index+",    ");
				for(int i=0; i<n; i++)
				{
					xx[i] = yy[i];
					//System.out.print(xx[i]+",    ");
				}
				//System.out.println("");
			}
		}
		return yy;
	}
	
}

