package com.smartmap.algorithm.equation.differential.ordinary.secondOrder;

public class Dirichlet {

	public static void main(String[] args) {
		int n = 160;
		double h = Math.PI / n;
		double[] dd=new double[n - 1];
		double[] aa=new double[n - 1];
		double[] bb=new double[n - 1];
		double[] cc=new double[n - 1];
		//建立矩阵系数
		for(int i=1; i<n; i++)
		{
			double dh = h * i;
			dd[i-1] = h * h * getF(dh);
			bb[i-1] = 2 + h * h;
			cc[i-1] = -1;
			aa[i-1] = -1;
		}
		//解矩阵
		double[] uu = chasingMethod(aa, bb, cc, dd);
		int d = n / 5;
		for(int i=1; i<n; i++)
		{
			System.out.println(i+" : "+uu[i-1]);
		}
		System.out.println("----------------------------------------------------");
		for(int i=1; i<5; i++)
		{
			System.out.println(i+" : "+uu[d*i-1]);
		}
	}

	public static double[] chasingMethod(double[] aa, double[] bb, double[] cc, double[] dd)
	{
		int n = aa.length;
		double[] uu = new double[n];
		//
		//
		double temp = 0;
		double[] gg = new double[n];
		double[] ww = new double[n];
		//第一步
		
		for(int i=0; i<n; i++)
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
		uu[n - 1] = gg[n - 1];
		for(int i=n-2; i>-1; i--)
		{
			uu[i] = gg[i] - ww[i]*uu[i+1];
		}
		return uu;
	}
	
	
	public static double getF(double x)
	{
		double returnValue = 0;
		returnValue = Math.exp(x) * (Math.sin(x) - Math.cos(x) * 2); //Math.pow(Math.E,x);
		return returnValue;
	}
}
