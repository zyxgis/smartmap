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
var basePagePath='<%=basePath%>';
var baseDataPath='<%=basePath%>/spring';
//
Ext.onReady(function () {
	//提取用户ID信息
	var queryString = window.location.search.substring(1,window.location.search.length);
	var queryStringArray = queryString.split("&");
	var queryStringParamObject = new Object();
	var valueKey = null;
	for(var i=0; i<queryStringArray.length; i++)
	{
		valueKey = queryStringArray[i].split("=");
		queryStringParamObject[valueKey[0]]=valueKey[1];
	}
	//查询用户菜单
	var menuMain = new Ext.Toolbar({enableOverflow: true});	
	Ext.Ajax.request({
		method:"GET",
	    url:baseDataPath+'/permission/queryResourcesByUserId',  
	    params:{  
      		userId:queryStringParamObject.userId
	    },
	    success: function(resp,opts) {
			var menuConfig = Ext.JSON.decode(resp.responseText);
			buildMenu(menuMain, menuConfig);
        },   
        failure: function(resp,opts) {
            Ext.Msg.alert('错误', resp.responseText);
        }
	});
	//构建菜单
	function buildMenu(menu, menuConfig) {
	    for (var i = 0; i < menuConfig.length; i++) {
	        var menuItemConfig = menuConfig[i];
	        var name = menuItemConfig["name"];
	        if (menuItemConfig["children"] != null) {
	            var subMenu = new Ext.menu.Menu({xtype: 'splitbutton'});
	            buildMenu(subMenu, menuItemConfig.children);
	            menu.add({text: name, menu: subMenu});
	        }
	        else {
	            menu.add({text: name, data: menuItemConfig, handler: navigation});
	        }
	    }
	}
	//点击菜单进行导航
	function navigation(item, e)
	{
		var contentFrame = document.getElementById("contentFrame");
		contentFrame.src = item.data.url;
	}
	
	var viewport = new Ext.Viewport({
        id: 'mainFrame',
        layout: 'border',
        items: [
         	{
	         	region: 'north',
	         	contentEl: 'north',
	         	height: 90,
	         	bbar:menuMain
            },
            {
	             region: 'center',
	             contentEl: 'center',
	             html: '<iframe id="contentFrame" align="" frameborder="0" height="100%" width="100%" marginheight="0px" marginwidth="0px" scrolling="no" src="systemManage/index.jsp"></iframe>'             
            }
        ]
    });
});
</script>
</head>
<body>
<div id="west">  
</div>
<div id="east">        
</div>
<div id="north">
	<table cellpadding="0" cellspacing="0" style="background-image: url('images/loginIcon/top_03.jpg'); width: 100%"> 
		<tr>  
			<td>
                <img src="images/loginIcon/top_02.jpg" />
            </td>
            <td width="200px">
                <a id="login" href="#">
                    <img alt="登录" src="images/loginIcon/top_09.jpg" />
                </a> 
                <a id="passowrd" href="#" >
                    <img alt="修改密码" src="images/loginIcon/top_05.jpg" />
                </a> 
                <a id="logout" href="#" >
                    <img alt="注销" src="images/loginIcon/top_07.jpg" />
               	</a>
            </td>
        </tr>
    </table>
</div>
<div id="center"></div>
<div id="south"></div>
</body>
</html>