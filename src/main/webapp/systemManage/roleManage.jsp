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
<STYLE TYPE="text/css">  
    .addIcon { background-image: url('<%=basePath%>/images/menu/add.gif') !important; }
    .deleteIcon { background-image: url('<%=basePath%>/images/menu/delete.gif') !important; }  
    .editIcon {background-image: url('<%=basePath%>/images/menu/edit.gif') !important; }  
    .findIcon {background-image: url('<%=basePath%>/images/menu/find.gif') !important; }
    .groupIcon {background-image: url('<%=basePath%>/images/menu/group.gif') !important; }
	.viewIcon {background-image: url('<%=basePath%>/images/menu/view.gif') !important; }  
</STYLE>
<script type="text/javascript">
Ext.Loader.setConfig({enabled : true});
Ext.BLANK_IMAGE_URL = '<%=extPath%>/resources/themes/images/default/tree/s.gif';
Ext.QuickTips.init();
var basePagePath='<%=basePath%>';
var baseDataPath='<%=basePath%>/spring';
//
Ext.onReady(function () {
var formPanel = new Ext.FormPanel({
	title: '用户管理',
    renderTo: "form",    
    autoHeight : true,    
    layout : "form",
    fieldDefaults: {
    	labelWidth : 65,
        labelAlign: 'right'
    },
    frame:true,
    border:false,
    bodyBorder:false,
    items: [{ // 行1
    	xtype: 'fieldcontainer',
    	border:false,
        bodyBorder:false,
    	frame:true,
        layout : "column", // 从左往右的布局
        items : [{
           xtype: 'fieldcontainer',
           columnWidth : .5, // 该列有整行中所占百分比           
           layout : "form", // 从上往下的布局      
           frame:true,
           border:false,
           bodyBorder:false,
           items : [{
              xtype : "textfield",              
              fieldLabel: '角色名',
              name: 'roleName',
              emptyText: '请输入',
              width : 120
             }]
          }, {
           xtype: 'fieldcontainer',
           columnWidth : .5,
           layout : "form",
           border:false,
           bodyBorder:false,
           items : [{        	  
              xtype : "textfield",
              fieldLabel: '登录名',
              name: 'username',
              emptyText: '请输入',
              width : 120
             }]
          }]
       }],
         buttons: [{
            text: '查询',
            scope: this,
            handler: function() {               
               var form = formPanel.getForm();
               var roleName = form.findField('roleName').getValue(); 
               //gridPanel.store.currentPage=1;
               var proxy = gridPanel.store.getProxy();
               if(roleName!="")
            	{
            	   proxy.setExtraParam("roleName", roleName);
            	}               
               gridPanel.store.loadPage(1);
           }
        },{
            text: '清除',
            scope: this,
            handler: function() {
               formPanel.getForm().reset();               
           }
        }]
	});
	//
	function showUrl(value) 
	{ 
		return "<a href='http://"+value+"' target='_blank'>"+value+"</a>"; 
	} 
		
	var itemsPerPage = 10;
	var store = Ext.create('Ext.data.Store', {
	    id:'store',
	    autoLoad: false,
	    fields:['id', 'code', 'name', 'superRole', 'description'],
	    remoteSort: true,
	    pageSize: itemsPerPage,
	    proxy: {
	        type: 'ajax',
	        url: baseDataPath+'/role/queryRolesByRoleName',
	        extraParams: {userId:""},
	        reader: {
	            type: 'json',
	            root: 'data',
	            totalProperty: 'totalCount'
	        }
	    }
	});
	//
    var gridPanel = new Ext.grid.Panel({
        //title: '用户信息',
        columns: [
		    {
		        text: '编号',
		        dataIndex: 'code',
		        width: 150
		    },
		    {
		        text: '角色名',
		        dataIndex: 'name',
		        hidden: false,
		        width: 150
		    },
		    {
		        text: '超级角色',
		        dataIndex: 'superRole',
		        width: 150
		    },
		    {
		        text: '描述',
		        dataIndex: 'description',		        
		        renderer:showUrl,
		        flex: 1
		    }
    	],
		store: store,
        renderTo: 'grid',
        dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: store,
	        dock: 'bottom',
	        displayInfo: true
	    }],
	    tbar: [{
	    	xtype:'buttongroup',
            items: [{text: '添加',
	        iconCls: 'addIcon'
	    }]},{
	    	xtype:'buttongroup',
            items: [{text: '删除',
	        iconCls: 'deleteIcon'
	    }]},{
	    	xtype:'buttongroup',
            items: [{text: '修改',
	        iconCls: 'editIcon'
	    }]}]
    });
	//
	var viewport = new Ext.Viewport({
        layout: {
            type: 'vbox',
            padding: '0',
            align: 'stretch'
        },
        defaults: { margins: '0 0 0 0' },
        items: [formPanel, gridPanel]
    });
    store.loadPage(1);
});
</script>
</head>
<body>
	<div id="form">  
	</div>
	<div id="grid">        
	</div>
</body>
</html>