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

Ext.onReady(function () {
var viewport = new Ext.Viewport({
        id: 'border-example',
        layout: 'border',
        items: [
         {
         	region: 'north',
         	contentEl: 'north',
         	height: 90,
                fbar:new Ext.Toolbar({                	
		            enableOverflow: true,
		            items: ['->',{
		                xtype:'splitbutton',
		                text: '实时监测',
		                iconCls: 'add16',
		                menu: [{text: 'Menu Item 1'}]
		            },'-',{
		                xtype:'splitbutton',
		                text: '预警信息',
		                iconCls: 'add16',
		                menu: [{text: 'Cut menu'}]
		            },{
		                text: '预报信息',
		                iconCls: 'add16',
		                menu: [{text: 'Cut menu'}]
		            },'-',{
		                text: '基础信息',
		                iconCls: 'add16',
		                menu: [{text: 'Paste menu'}]
		            },{
		                text: '系统管理',
		                iconCls: 'add16',
		                menu: [{text: '用户权限管理', menu:new Ext.menu.Menu({items: [
		                                                {
		                                                    text: 'Aero Glass',
		                                                    checked: true,
		                                                    group: 'theme',
		                                                    checkHandler: loadSecondLevelMenu
		                                                }, {
		                                                    text: 'Vista Black',
		                                                    checked: false,
		                                                    group: 'theme',
		                                                    checkHandler: loadSecondLevelMenu
		                                                }, {
		                                                    text: 'Gray Theme',
		                                                    checked: false,
		                                                    group: 'theme',
		                                                    checkHandler: loadSecondLevelMenu
		                                                }, {
		                                                    text: 'Default Theme',
		                                                    checked: false,
		                                                    group: 'theme',
		                                                    checkHandler: loadSecondLevelMenu
		                                                }
		                                            ]})},
		                       {text: '部门管理',handler: loadSecondLevelMenu},
		                       {text: '日志管理',handler: loadSecondLevelMenu},
		                       {text: '系统参数管理',handler: loadSecondLevelMenu},
		                       ]		                
		            }]
		        })
            },{
                region: 'south',
                contentEl: 'south',
             	height: 30,
                    //dock: 'top',
                    //margins: '0 0 0 0',
                    xtype: 'toolbar',
                    items: [ '->', {
                       xtype: 'button',
                       text: 'test',
                       tooltip: 'Test Button'
                    }]
                 
             }, {
                 region: 'west',
                 contentEl: 'west',
                 id: 'west-panel',
                 //title: 'West',
                 header: false,
                 split: true,
                 width: 200,
                 minWidth: -20,
                 maxWidth: 400,
                 collapsible: true,
                 animCollapse: false,
                 collapseMode:'mini',
                 margins: '0 0 0 0',
                 layout: 'accordion',
                 items: [{
                     html:'<div id="west" class="x-hide-display"> <p>Hi. Im the west panel.</p> </div>',
                     title: 'Navigation',
                     iconCls: 'nav'
                 }, {
                     title: 'Settings',
                     html: '<div><p>Some settings in here.</p></div>',
                     iconCls: 'settings'
                 }, {
                     title: 'Information',
                     html: '<div><p>Some info in here.</p></div>',
                     iconCls: 'info'
                 }]
             },
         {
         	 xtype: 'tabpanel',
             region: 'center',
             contentEl: 'center',
             deferredRender: false,
             activeTab: 0,
             items: [{
                 html:'<div id="center1" class="x-hide-display"><p><b>Done reading me? Close me by clicking the X in the top right corner.</b></p></div>',	                    
                 title: 'Close Me',
                 closable: true,
                 autoScroll: true
             }, {
                 html:'<div id="center2" class="x-hide-display"> <a id="hideit" href="#">Toggle the west region</a></div>',
                 title: 'Center Panel',
                 autoScroll: true
             }]
           }
        ]
    });
    function loadSecondLevelMenu()
    {
    	var conn = new Ext.data.Connection();  
	       conn.request({  
		       url: baseDataPath+'/user/queryUsersByRoleId?limit=10&page=1',   
		       params: {roleId:2},
		       method: 'get',
		       scope: this,
		       callback:function(options,success, response){   
		       if(success){   
			       //Ext.MessageBox.alert("提示","所选记录成功删除！");  
			        //Ext.Msg.alert('信息提示', Ext.JSON.encode(record.raw));
	           		    //alert(Ext.JSON.encode(record.raw));
			       store.reload();  
			       gridPanel.store.reload();
		       }   
		       else   
		       { 
		       	   Ext.MessageBox.alert("提示","所选记录删除失败！");}   
		       }
	       }); 
    }
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