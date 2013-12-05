<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String extLibPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/ext4";
    String ctx = request.getContextPath();
    pageContext.setAttribute("extLibPath", extLibPath);
    pageContext.setAttribute("ctx", ctx);
    String extPath = "http://localhost:9090/ext-4.2.1";    
    //
    String appContext = request.getContextPath();  
    String basePath =  request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort() + appContext; 
%>
