package com.smartmap.algorithm.equation.differential.partial;

public class Matrix {


	public static void main(String[] args) {
		int n = 3;
		double[][] aa = {{10,-1,-2},  {-1,10,-2}, {-1,-1,5}};
		double[] bb = {7.2,  8.3,  4.2};
		double[] xx = {0,  0,  0};
		//解矩阵
		double[] uu = sorMethod(aa, bb, xx, 1.2);  //  gaussSeidelMethod    //    jacobiMethod  // sorMethod
		
		for(int i=0; i<n; i++)
		{
			System.out.println(i+" : "+uu[i]);
		}
		System.out.println("----------------------------------------------------");
		
	}

	public static double[] jacobiMethod(double[][] aa, double[] bb, double[] xx)
	{
		int n = bb.length;
		double[] yy = new double[n];
		double EPSON = 0.0000001;
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
				System.out.println("------------------------------");
				System.out.print(index+",    ");
				for(int i=0; i<n; i++)
				{
					xx[i] = yy[i];
					System.out.print(xx[i]+",    ");
				}
				System.out.println("");
			}
		}
		return yy;
	}
	
	
	public static double[] gaussSeidelMethod(double[][] aa, double[] bb, double[] xx)
	{
		int n = bb.length;
		double[] yy = new double[n];
		double EPSON = 0.0000001;
		int index = 0;
		for(int i=0; i<n; i++)
		{
			yy[i] = xx[i];
		}
		boolean flag = true;
		while(true)
		{
			index++;
			for(int i=0; i<n; i++)
			{
				yy[i] = bb[i];
				for(int j=0; j<n; j++)
				{
					if(i!=j)
					{
						yy[i] = yy[i]-aa[i][j]*yy[j];
					}
				}
				yy[i] = yy[i] / aa[i][i];
				//
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
				System.out.println("------------------------------");
				System.out.print(index+",    ");
				for(int i=0; i<n; i++)
				{
					xx[i] = yy[i];
					System.out.print(xx[i]+",    ");
				}
				System.out.println("");
			}
		}
		return yy;
	}
	
	public static double[] sorMethod(double[][] aa, double[] bb, double[] xx, double w)
	{
		int n = bb.length;
		double[] yy = new double[n];
		double EPSON = 0.0000001;
		int index = 0;
		for(int i=0; i<n; i++)
		{
			yy[i] = xx[i];
		}
		boolean flag = true;
		while(true)
		{
			index++;
			for(int i=0; i<n; i++)
			{
				yy[i] = bb[i];
				for(int j=0; j<n; j++)
				{
					if(i!=j)
					{
						yy[i] = yy[i]-aa[i][j]*yy[j];
					}
				}
				yy[i] = yy[i] / aa[i][i];
				yy[i] = xx[i] + w *(yy[i] - xx[i]);
				//
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
				System.out.println("------------------------------");
				System.out.print(index+",    ");
				for(int i=0; i<n; i++)
				{
					xx[i] = yy[i];
					System.out.print(xx[i]+",    ");
				}
				System.out.println("");
			}
		}
		return yy;
	}
}
