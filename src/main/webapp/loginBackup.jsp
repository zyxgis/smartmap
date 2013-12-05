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
<STYLE TYPE="text/css">  
    .addIcon { background-image: url(images/menu/add.gif) !important; }  
    .deleteIcon { background-image: url(images/menu/delete.gif) !important; }  
    .editIcon {background-image: url(images/menu/edit.gif) !important; }  
    .findIcon {background-image: url(images/menu/find.gif) !important; }
    .groupIcon {background-image: url(images/menu/group.gif) !important; }
	.viewIcon {background-image: url(images/menu/view.gif) !important; }  
</STYLE> 
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
	var form = new Ext.FormPanel( {//建立表单面板
		defaultType:'textfield',
		labelWidth:60,
		style:'background:#ffffff; padding:25px 35px 30px 16px;',
		region:"center",
		waitMsgTarget:true,
		defaults:{
			border:false,
			allowBlank:false,
			msgTarget:'side',
			blankText:'该字段不允许为空'
		},
		items:[//表单项
			{
				fieldLabel:'登录帐号',
				name:'username',
				regex:/^[0-9a-zA-Z]{2,20}$/,
				regexText:'只能为两到六位的大小写字母。'
			},
			{
				fieldLabel:'登录密码',
				name:'password',
				inputType:'password',
				regex:/^.{4,}$/,
				regexText:'长度不能少于4位'
			}
		],
		buttons:[{
			text:'登陆',
			handler:function(){
				form.getForm().submit({
					success:function(form, action) {
						//OpenFullWin(a.result.url);
						// window.open('main.html','','fullscreen=1');
						//alert("OK");
						window.location.href = basePagePath+'/index.jsp?userId=' +action.result.userId;
					},
					failure:function(form, action) {
                        Ext.MessageBox.alert('提示', action.result);
                        return;
                    },
                    scope: this,
					url:baseDataPath+'/login/login',
					waitMsg:'正在提交，请稍等...'
				});
			}
		},{
			text:'取消',
			handler:function() {
				form.getForm().reset();
			}
		}]
	});

	var panel = new Ext.Panel({
		renderTo:'loginpanel',
		layout:"border",
		width:525,
		height:290,
		defaults:{
			border:false
		},
		items:[form]
	});
	Ext.get('loginpanel').setStyle('position', 'absolute').center(Ext.getBody());
});

</script>
</head>
<body>
<div id="loginpanel"></div>
</body>
</html>