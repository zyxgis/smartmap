<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</STYLE>
<script type="text/javascript">
Ext.Loader.setConfig({enabled : true});
Ext.BLANK_IMAGE_URL = '<%=extPath%>/resources/themes/images/default/tree/s.gif';
Ext.QuickTips.init();
var basePagePath='<%=basePath%>';
var baseDataPath='<%=basePath%>/spring';
var itemsPerPage = 10;
//
Ext.onReady(function () {
	var treeStore = new Ext.data.TreeStore({
	    //autoLoad: false,
        proxy: {
            type: 'ajax',
            url: baseDataPath+'/resource/queryAllResourcesToTree'
        },
        root: {
            text: 'Ext JS',
            id: 'src',
            expanded: false
        }
    });		
	//
	var store = new Ext.data.Store({
	    autoLoad: false,
	    fields:['id', 'code', 'name', 'url', 'sortOrder', 'parentName'],
	    remoteSort: true,
	    pageSize: itemsPerPage,
	    proxy: {
	        type: 'ajax',
	        url: baseDataPath+'/resource/queryResourcesByParentResourceId',
	        reader: {
	            type: 'json',
	            root: 'data',
	            totalProperty: 'totalCount'
	        }
	    }
	});	
	//
	var viewport = new Ext.Viewport({
        layout: {
            type: 'border',
            padding: '0'
        },
        defaults: { margins: '0 0 0 0' },        
        items: [
         	{
         		id:'treepanel',
         		title: '功能模块',
	         	region: 'west',
	         	xtype: 'treepanel',
	         	split: true,
	         	width: 300,
			    minWidth: -20,
			    maxWidth: 300,
			    collapsible: true,
			    animCollapse: false,
			    collapseMode:'mini',
	    		useArrows : true,
	    		rootVisible: false,
	    		store: treeStore,
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
           	    }]}],
	           	listeners: {
	           	    itemclick: function(view,record,item,index,e) {	           		   
	           		    var proxy = store.getProxy();
	           	        proxy.setExtraParam("parentId", record.raw.id);
	           	        store.loadPage(1);
	           	    }
	           	}
            },
            {
            	id:'gridpanel',
            	title: '下级功能模块',
	            region: 'center',
	            xtype: 'gridpanel',
	            store: store,
	            columns: [
           			{
           			    text: '编号',
           			    dataIndex: 'code',
           			    width: 100
           			},
           		    {
           		        text: '名称',
           		        dataIndex: 'name',
           		        width: 150
           		    },
           		    {
           		        text: '地址',
           		        dataIndex: 'url',
           		        hidden: false,
           		        width: 150
           		    },
           		    {
           		        text: '排序',
           		        dataIndex: 'sortOrder',
           		        width: 150
           		    },
           		    {
           		        text: '上级菜单',
           		        dataIndex: 'parentName',
           		        flex: 1
           		    }
               	],           		
                dockedItems: [{
                	id: 'pagingtoolbar',
           	        xtype: 'pagingtoolbar',
           	        store: store,
           	        dock: 'bottom',
           	        displayInfo: true
           	    }]
            }
        ]
    });
	/*
	Ext.getCmp('treepanel').on('itemclick', function(view,record,item,index,e) {
	    //Ext.Msg.alert('信息提示', Ext.JSON.encode(record.raw));
	    //alert(Ext.JSON.encode(record.raw));
	    var proxy = store.getProxy();
        proxy.setExtraParam("parentId", record.raw.id);
        store.loadPage(1);
	});
	*/
});
</script>
</head>
<body>
</body>
</html>