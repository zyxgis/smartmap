<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../../include/ext.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务基础平台</title>
<link rel="stylesheet" type="text/css" href="<%=extPath%>/resources/css/ext-all.css"/>
<script type="text/javascript" src="<%=extPath%>/ext-all-debug.js"></script>
<script type="text/javascript" src="<%=extPath%>/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/scripts/commonComponent/comboBoxTree.js"></script>
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
	var treeStoreModule = new Ext.data.TreeStore({
	    //autoLoad: false,
        proxy: {
            type: 'ajax',
            url: baseDataPath+'/resource/queryAllResourcesToTree'
        },
        root: {
        	id: 'root',
            text: 'root',
            parentId: null,
            leaf: false,            
            expanded: true
        },
        fields: [{
            name: "id"
        }, {
            name: "code"
        }, {
            name: "name"
        }, {
            name: "text"
        }, {
            name: "leaf"
        },  {
            name: "target"
        }, {
            name: "url"
        }, {
            name: "sortOrder"
        }, {
            name: "parentName"
        }, {
            name: "description"
        }]
    });		
	
	//
	var viewport = new Ext.Viewport({
        layout: {
            type: 'border',
            padding: '0'
        },
        defaults: { margins: '0 0 0 0' },        
        items: [{
        		region: 'center',
        	    id:'treepanel',
           		title: '模块管理',	     	         	
  	         	xtype: 'treepanel',
  	    		useArrows : true,
  	    		rootVisible: false,
  	    		store: treeStoreModule,
  	    		anchor:'100% 100%',
  	    		columns: [{
           				xtype: 'treecolumn', 
           		        text: '名称',
           		        dataIndex: 'name',
           		        width: 300
           		    },
           		   {
           			    text: '编码',
           			    dataIndex: 'code',
           			    width: 100
           			},
           		    {
           		        text: '上级组织',
           		        dataIndex: 'parentName',
           		        //flex: 1
           		      	width: 150
           		    },
           		    {
           		        text: '连接目标',
           		        dataIndex: 'target',           		        
           		        width: 80
           		    },
           		    {
           		        text: 'Web连接地址',
           		        dataIndex: 'url',
           		        //flex: 1
           		      	width: 350
           		    },
           		    {
           		        text: '叶节点',
           		        dataIndex: 'leaf',
           		        width: 50
           		    },
           		    {
           		        text: '排序',
           		        dataIndex: 'sortOrder',
           		        width: 50
           		    }
               	],
           		listeners: {
 	           	    itemclick: function(view,record,item,index,e) {	           		   
 	           		    
 	           	    }
           		},
           		tbar: [{
           	    	xtype:'buttongroup',
                    items: [{text: '刷新',
        	        iconCls: 'addIcon'
        	    }]},'-',{
          	    	xtype:'buttongroup',
                      items: [{text: '添加',
          	        iconCls: 'addIcon'
          	    }]},{
          	    	xtype:'buttongroup',
                    items: [{text: '修改',
        	        iconCls: 'editIcon'
        	    }]},{
          	    	xtype:'buttongroup',
                      items: [{text: '删除',
          	        iconCls: 'deleteIcon'
          	    }]},'-',{
          	    	xtype:'buttongroup',
                    items: [{text: '分配按钮',
        	        iconCls: 'addIcon'
        	    }]}]
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
	function showAdd() {
		var addWindow = new Ext.Window({
			title: '添加菜单',
			id: 'addWindow',	       
	        height:360,
	        width: 350,
	        layout: 'fit', 
	        items:[{
				  xtype: 'form',
				  url: baseDataPath+'/resource/saveResource',
				  //frame:true,
				  bodyBorder:false,
				  border: 0,
				  buttonAlign: 'center',
				  layout: 'anchor',
				  defaults: {
				    anchor: '100%'
				  },
				  fieldDefaults: {
				    msgTarget: 'side',
				    labelWidth: 75
				  },
				  defaultType: 'textfield',
				  items: {
			            xtype:'tabpanel',
			            activeTab: 0,
			            defaults:{
			                bodyPadding: 10,
			                layout: 'anchor'
			            },
			            //frame:true,
			            bodyBorder:false,
			            border: 0,
			            items:[{
			                title:'菜单基本信息',
			                defaultType: 'textfield',
			                defaults: {
			                    anchor: '100%'
			                },
			                bodyBorder:false,
				            border: 0,
			                items: [{
			                    fieldLabel: '菜单名称',
			                    name: 'resourceName',
			                    allowBlank: false
			                  },
			                  {
			                    fieldLabel: '菜单代码',
			                    name: 'code',
			                    allowBlank: false
			                  },
			                  {
			                    fieldLabel: '上级菜单',
			                    name: 'parentId',
			                    xtype: 'treecombox',
			                    align: 'center',
			                    displayField: 'text',
			                    valueField: 'id',
			                    store: Ext.create('Ext.data.TreeStore',
			                    {
			                      fields: [
			                        'id',
			                        'text'
			                      ],
			                      autoLoad: true,
			                      root: {
			                        text: '系统',
			                        id: '',
			                        expanded: true
			                      },
			                      proxy: {
			                        type: 'ajax',
			                        url: baseDataPath+'/resource/queryAllResourcesToTree'
			                      }
			                    })
			                  },
			                  {
			                    fieldLabel: '连接目标',
			                    name: 'target',
			                    xtype: 'combobox',
			                    queryMode: 'local',
			                    displayField: 'name',
			                    valueField: 'value',
			                    store: Ext.create('Ext.data.Store',
			                    {
			                      fields: [
			                        'value',
			                        'name'
			                      ],
			                      data: [
			                        {
			                          "value": "iframe",
			                          "name": "iframe"
			                        },
			                        {
			                          "value": "javascript",
			                          "name": "javascript"
			                        },
			                        {
			                          "value": "click",
			                          "name": "click"
			                        }
			                      ]
			                    })
			                  },
			                  {
			                    fieldLabel: '菜单内容',
			                    name: 'url',
			                    xtype: 'textareafield',
			                    grow: true,
			                    allowBlank: false
			                  },
			                  {
			                    fieldLabel: '菜单排序',
			                    name: 'sortOrder'
			                  },
			                  {
			                    fieldLabel: '描述',
			                    name: 'description',
			                    xtype: 'textareafield',
			                    grow: true,
			                    anchor: '100%'
			                  }]
			            },{
			            	title: '菜单操作',
			                //defaultType: 'textfield',
			                //frame:true,
			                defaults: {
			                    anchor: '100%'
			                },
			                bodyBorder:false,
				            border: 0,
			                items: [{
			                	 xtype: 'checkboxgroup',
			                     //fieldLabel: 'Multi-Column (horizontal)',
			                     //cls: 'x-check-group-alt',
			                     columns: 3,
			                     items: [
			                         {boxLabel: 'Item 1', name: 'rb-horiz-1', inputValue: 1},
			                         {boxLabel: 'Item 2', name: 'rb-horiz-1', inputValue: 2, checked: true},
			                         {boxLabel: 'Item 3', name: 'rb-horiz-1', inputValue: 3},
			                         {boxLabel: 'Item 4', name: 'rb-horiz-2', inputValue: 1, checked: true},
			                         {boxLabel: 'Item 5', name: 'rb-horiz-2', inputValue: 2}
			                     ]
			                }]
			            }]
			        },
		        buttons: [{
		            text: 'Save',
		            handler: function() {
		            	this.up('form').getForm().submit();
		            }
		        },{
		            text: 'Cancel',
		            handler: function() {
		                this.up('form').getForm().reset();
		            }
		        }]
	        }]
		});
		addWindow.show();
	}
});
</script>
</head>
<body>
</body>
</html>