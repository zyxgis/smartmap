<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/ext.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务基础平台</title>
<link rel="stylesheet" type="text/css" href="<%=extPath%>/resources/css/ext-all.css"/>
<script type="text/javascript" src="<%=extPath%>/ext-all-debug.js"></script>
<script type="text/javascript" src="<%=extPath%>/locale/ext-lang-zh_CN.js"></script>
<style type="text/css"> 
   html,body {
     height:100%; 
     width:100%; 
     margin:0px;
     padding:0px;
     overflow:hidden;
   }      
</style>

<script type="text/javascript">
Ext.Loader.setConfig({enabled : true});
Ext.BLANK_IMAGE_URL = '<%=extPath%>/resources/themes/images/default/tree/s.gif';
Ext.QuickTips.init();
//Ext.lib.Ajax.defaultPostHeader += ";charset=utf-8";
var basePagePath='<%=basePath%>';
var baseDataPath='<%=basePath%>/spring';
//
Ext.onReady(function () {	
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	//
	var form = new Ext.FormPanel({
		renderTo: 'loginpanel',
		title: '系统登录',
	    frame: true,
	    width: 320,
	    bodyPadding: 10,
	    defaultType: 'textfield',
	    url: baseDataPath+'/login/login',
	    standardSubmit: true,
	    defaults: {
	        anchor: '100%'
	    },
	    items: [
	        {
	        	fieldLabel: '登录帐号',
				name: 'username',
				regex: /^[0-9a-zA-Z]{2,20}$/,
				regexText: '只能为两到六位的大小写字母。',
	            allowBlank: false,	            
	            emptyText: 'admin'
	        },
	        {
	        	fieldLabel: '登录密码',
				name: 'password',
				inputType: 'password',
				regex: /^.{4,}$/,
				regexText: '长度不能少于4位',
	            allowBlank: false,	            
	            emptyText: 'admin'
	        },
	        {
	            xtype: 'checkbox',
	            fieldLabel: '让系统记住我',
	            name: 'rememberMe'
	        }
	    ],
	    buttons:[{
			text: '登陆',
			handler: function(){
				form.getForm().submit();
			}
		},{
			text:'取消',
			handler:function() {
				form.getForm().reset();
			}
		}]
	});
	Ext.get('loginpanel').setStyle('position', 'absolute').center(Ext.getBody());
});

</script>
</head>
<body>
<div id="loginpanel"></div>
</body>
</html>