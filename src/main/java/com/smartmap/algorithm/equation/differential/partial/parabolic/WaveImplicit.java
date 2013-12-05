package com.smartmap.algorithm.equation.differential.partial.parabolic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

public class WaveImplicit {
	public static void main(String[] args) {		
		int nt = 100; int nx = 100;		
		double minT = 0; 
		double maxT = 1;
		double minX = 0;
		double maxX = 1;
		double dt = (maxT - minT) / nt;		
		double dx = (maxX - minX) / nx;
		double a = 1;
		double s = a * dt / dx;
		//		
		double xx[][] = new double[nt+1][nx+1];
		//
		System.out.println("----------------------------------------");
		for(int k=0; k<nt+1; k++)
		{			
			double value = dt * k;
			System.out.print(value + " ");
		}
		System.out.println("");
		System.out.println("----------------------------------------");
		for(int i=0; i<nx+1; i++)
		{
			double value = dx * i;
			System.out.print(value + " ");
		}
		System.out.println("");
		//
		//设置初始参数
		for(int i=0; i<nx+1; i++)
		{
			double x = dx * i;
			xx[0][i] = getMinTU(x);
		}
		for(int j=0; j<nt+1; j++)
		{
			double t = dt * j;
			xx[j][0] = getMinXU(t);
			xx[j][nx] = getMaxXU(t);			
		}
		computeAAOneT(nt, nx, dt, dx, minT, minX, xx, s);
		computeAAOtherT(nt, nx, dt, dx, minT, minX, xx, s);
		//输出
		System.out.println("--------------------------------");
		for(int k=0; k<nx+1; k++)
		{
			for(int j=0; j<nt+1; j++)
			{			
				System.out.printf(" %8.6f", xx[j][k]);					
			}	
			System.out.println(";");			
		}		
	}

	public static void computeAAOneT(int nt, int nx, double dt, double dx, double minT, double minX, double aa[][], double s)
	{
		//
		double[] AA = new double[nx+1];
		double[] BB = new double[nx+1];
		double[] CC = new double[nx+1];
		double[] DD = new double[nx+1];
		double ss = Math.pow(s, 2);
		//
		for(int i=1; i<nx; i++)
		{
			double x = dx * i;
			double t = dt * 0;
			if(i==1)
			{
				//AA[i]=-ss/2;
				BB[i]=2+ss;
				CC[i]=-ss/2;
			}
			else if(i == nx-1)
			{
				AA[i]=-ss/2;
				BB[i]=2+ss;
				//CC[i]=-ss/2;
			}
			else
			{
				AA[i]=-ss/2;
				BB[i]=2+ss;
				CC[i]=-ss/2;
			}
			//
			//计算DD
			if(i==1)
			{
				DD[i]=(2-ss)*aa[0][i] + ss/2*aa[0][i+1];
				DD[i] += ss/2*(aa[1][0] + aa[0][0]);
				DD[i] += 2*dt*getZeroTDt(x) + Math.pow(dt, 2)*getF(x,t);
			}
			else if(i == nx-1)
			{
				DD[i]=ss/2*aa[0][i-1] + (2-ss)*aa[0][i];
				DD[i] += ss/2*(aa[1][i+1] + aa[0][i+1]);
				DD[i] += 2*dt*getZeroTDt(x) + Math.pow(dt, 2)*getF(x,t);
			}
			else
			{
				DD[i]=ss/2*aa[0][i-1] + (2-ss)*aa[0][i] + ss/2*aa[0][i+1];
				DD[i] += 2*dt*getZeroTDt(x) + Math.pow(dt, 2)*getF(x,t);
			}
		}
		int start=1;
		int end=nx;
		double[] uu = chasingMethod(start, end, AA, BB, CC, DD);
		for(int i=1; i<nx; i++)
		{
			aa[1][i] = uu[i];
		}
 	}
	

	public static void computeAAOtherT(int nt, int nx, double dt, double dx, double minT, double minX, double aa[][], double s)
	{
		//
		double[] AA = new double[nx+1];
		double[] BB = new double[nx+1];
		double[] CC = new double[nx+1];
		double[] DD = new double[nx+1];
		double ss = Math.pow(s, 2);
		//
		//
		for(int k=2; k<nt+1; k++)
		{
			for(int i=1; i<nx; i++)
			{
				double x = dx * i;
				double t = dt * k;
				if(i==1)
				{
					//AA[i]=-ss/2;
					BB[i]=1+ss;
					CC[i]=-ss/2;
				}
				else if(i == nx-1)
				{
					AA[i]=-ss/2;
					BB[i]=1+ss;
					//CC[i]=-ss/2;
				}
				else
				{
					AA[i]=-ss/2;
					BB[i]=1+ss;
					CC[i]=-ss/2;
				}
				//
				//计算DD
				if(i==1)
				{
					DD[i] =  -(1+ss)*aa[k-2][i] + ss/2*aa[k-2][i+1];
					DD[i] += ss/2*(aa[k][0]+aa[k-2][0]);
					DD[i] += 2*aa[k-1][i] + Math.pow(dt, 2)*getF(x, t);
				}
				else if(i == nx-1)
				{
					DD[i]= ss/2*aa[k-2][i-1] - (1+ss)*aa[k-2][i];
					DD[i] += ss/2*(aa[k][i+1]+aa[k-2][i+1]);
					DD[i] += 2*aa[k-1][i] + Math.pow(dt, 2)*getF(x, t);
				}
				else
				{
					DD[i]= ss/2*aa[k-2][i-1] - (1+ss)*aa[k-2][i] + ss/2*aa[k-2][i+1];
					DD[i] += 2*aa[k-1][i] + Math.pow(dt, 2)*getF(x, t);
				}
			}
			int start=1;
			int end=nx;
			double[] uu = chasingMethod(start, end, AA, BB, CC, DD);
			for(int i=1; i<nx; i++)
			{
				aa[k][i] = uu[i];
			}
		}
		
 	}
	
	
	public static double[] chasingMethod(int start, int end, double[] aa, double[] bb, double[] cc, double[] dd)
	{
		int n = aa.length;
		double[] uu = new double[n];
		//
		//
		double temp = 0;
		double[] gg = new double[n];
		double[] ww = new double[n];
		//第一步
		
		for(int i=start; i<end; i++)
		{
			if(i==0)
			{
				gg[i] = dd[i] / bb[i];
				ww[i] = cc[i] / bb[i];
			}
			else
			{
				temp = (bb[i] - aa[i]*ww[i-1]);
				gg[i] = (dd[i] - aa[i] * gg[i-1]) / temp;
				ww[i] = cc[i] / temp;
			}
		}
		//第二步
		uu[end - 1] = gg[end - 1];
		for(int i=end-2; i>start-1; i--)
		{
			uu[i] = gg[i] - ww[i]*uu[i+1];
		}
		//System.out.println("----------------------------------------------");
		for(int i=0; i<n; i++)
		{
			//System.out.println(uu[i]);
		}
		return uu;
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
		return returnValue;
	}
	
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public static double getMinTU(double x)
	{
		double returnValue = Math.exp(x);
		return returnValue;
	}
	
		
	public static double getZeroTDt(double x)
	{
		double returnValue = Math.exp(x);
		return returnValue;
	}
		
	/**
	 * 
	 * @param y
	 * @return
	 */
	public static double getMinXU(double t)
	{
		double returnValue = 0;
		returnValue = Math.exp(t);
		return returnValue;
	}
	
	public static double getMaxXU(double t)
	{
		double returnValue = 0;
		returnValue = Math.exp(t+1);
		return returnValue;
	}

}


