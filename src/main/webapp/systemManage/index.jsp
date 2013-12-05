<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/ext.jsp" %>
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
             region: 'center',
             contentEl: 'center',
             html: '<iframe align="" frameborder="0" height="100%" width="100%" marginheight="0px" marginwidth="0px" scrolling="no" src="userManage.jsp"></iframe>'             
           }
        ]
    });
    function loadSecondLevelMenu()
    {
    
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
</div>
<div id="center">
</div>
<div id="south">
</div>
</body>
</html>