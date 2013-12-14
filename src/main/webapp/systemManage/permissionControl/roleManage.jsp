<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/ext.jsp" %>
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
var itemsPerPage = 10;
Ext.onReady(function () {
	var treeStore = new Ext.data.TreeStore({
	    //autoLoad: false,
        proxy: {
            type: 'ajax',
            url: baseDataPath+'/organization/queryToTreeWithLevel',
            extraParams: {maxLevel:1},
        },
        root: {
            text: 'root',
            id: 'root',
            expanded: false
        }
    });
	//
	var store = new Ext.data.Store({
		id:'store',
	    autoLoad: false,
	    fields:['id', 'code', 'name', 'superRole', 'description'],
	    remoteSort: true,
	    pageSize: itemsPerPage,
	    proxy: {
	        type: 'ajax',
	        url: baseDataPath+'/role/queryByOrganizationId',
	        extraParams: {userId:""},
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
         		title: '组织机构',
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
	           	listeners: {
	           	    itemclick: function(view,record,item,index,e) {	           		   
	           		    var proxy = store.getProxy();
	           	        proxy.setExtraParam("organizationId", record.raw.id);
	           	        store.loadPage(1);
	           	    }
	           	}
            },
            {
                region: 'center', 
                title: '组织机构',
                xtype: 'gridpanel',
                store: store,
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
              		        //renderer:showUrl,
              		        flex: 1
              		    }
                  	],              		
                 dockedItems: [{
           	        xtype: 'pagingtoolbar',
           	        store: store,
           	        dock: 'bottom',
           	        displayInfo: true
           	    }],
	           	tbar: [{
	     	    	xtype:'buttongroup',
	                 items: [{text: '刷新',
	     	        iconCls: 'findIcon'
	     	    }]},'-',{
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
            }
        ]
    });
	
	
	
	
	
});
</script>
</head>
<body>
</body>
</html>