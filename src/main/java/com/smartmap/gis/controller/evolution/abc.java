package com.smartmap.gis.controller.evolution;

import java.io.UnsupportedEncodingException;

public class abc {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String a = "蒋坝水位站";
  		String b = new String(a.getBytes("ISO-8859-1"),"UTF-8");
  		String c = new String(a.getBytes("ISO-8859-1"),"GBK");
  		String d = new String(a.getBytes("GBK"),"UTF-8");
  		String e = new String(a.getBytes("UTF-8"),"GBK");
  		//
  		String f = new String(a.getBytes("ISO-8859-1"),"gb2312");
  		String g = new String(a.getBytes("gb2312"),"UTF-8");
  		String h = new String(a.getBytes("UTF-8"),"gb2312");
  		System.out.println(a+ "    " +b+"    "+c+ "    " +d+"    "+e+"    "+f+ "    " +g+"    "+h);
	}

}
