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
var selectedRole;
//
Ext.onReady(function () {
	var treeStoreOrganization = new Ext.data.TreeStore({
	    //autoLoad: false,
        proxy: {
            type: 'ajax',
            url: baseDataPath+'/organization/queryAllToTree'
        },
        root: {
            text: 'root',
            id: 'root',
            expanded: true
        }
    });
	//
	var treeStoreRole = new Ext.data.TreeStore({
        root: {
            text: '角色',
            id: 'root',
            expanded: false
        }
    });
	//
	var treeStoreModule = new Ext.data.TreeStore({
        root: {
        	text: '模块',
            id: 'root',
            parentId: null,
            leaf: false,            
            expanded: false
        },
        fields: ['id','code','name','text','leaf','category','sortOrder','description']
    });
	//
	//
	var treeStoreRoleModule = new Ext.data.TreeStore({	    
        root: {
        	text: '模块',
            id: 'root',
            expanded: true
        }
    });
	//
	var storeUser = Ext.create('Ext.data.Store', {
	    //autoLoad: false,
	    fields:['id', 'account', 'password', 'userName', 'departmentName'],
	    remoteSort: true,
	    pageSize: itemsPerPage,
	    proxy: {
	        type: 'ajax',
	        url: baseDataPath+'/user/queryUsersByLoginUsername',
	        extraParams: {username:""},
	        reader: {
	            type: 'json',
	            root: 'data',
	            totalProperty: 'totalCount'
	        }
	    }
	});
	//
	function showYesNo(value) 
	{ 
		return ((value==true || value=='true')?'是':'否'); 
	} 
	//
	var viewport = new Ext.Viewport({        
		layout: {
			 type: 'border',
	         padding: '0'
	    },
	    defaults: { margins: '0 0 0 0' },    
        items: [
                {
                	region:'west',
                	layout: 'hbox',
                	items:[{
							title: '组织机构',
						 	xtype: 'treepanel',
						 	width: 200,
						 	height:'100%',
							useArrows : true,
							rootVisible: false,
							store: treeStoreOrganization,
						   	listeners: {
						   	    itemclick: function(view,record,item,index,e) {						   	    	
						   	    	treeStoreRole.setProxy({
						   	            type: 'ajax',
						   	            url: baseDataPath+'/role/queryByOrganizationId',
						   	         	extraParams:{'organizationId': record.raw.id}
						   	        });
						   	    	treeStoreRole.reload();	
						   	    	var permissionPanel = Ext.getCmp('permissionPanel');
	     	           	    		permissionPanel.setTitle('角色授权-');
						   	    }
						   	}
						},
						{
							title: '角色列表',
						 	xtype: 'treepanel',
						 	width: 200,
						 	height:'100%',
							useArrows : true,
							rootVisible: false,
							store: treeStoreRole,
						   	listeners: {
						   	    itemclick: function(view,record,item,index,e) {
						   	    	treeStoreModule.setProxy({
						   	            type: 'ajax',
						   	            url: baseDataPath+'/permission/queryResourcesByRoleId',
						   	         	extraParams:{'roleId': record.raw.id}
						   	        });
						   	    	treeStoreModule.reload();
						   	    	selectedRole = record.raw;
						   	    	var permissionTabpanel = Ext.getCmp('permissionTabpanel');
						   	    	permissionTabpanel.setActiveTab(0);
						   	    	var checkboxGroupOperatePanel = Ext.getCmp('checkboxGroupOperatePanel');
	     	           	    		checkboxGroupOperatePanel.removeAll();
	     	           	    		//
	     	           	    		var permissionPanel = Ext.getCmp('permissionPanel');
	     	           	    		permissionPanel.setTitle('角色授权-'+selectedRole.name);
						   	    }
						   	}
						}
                	]
                },
            	{
                	id:'permissionPanel',
                	region: 'center',
                	title: '角色授权',
                    layout: 'anchor',
                    defaults: {
                   	 	anchor: '100% 100%'
                	},
                    items: [{
                    	id: 'permissionTabpanel',
                   	 	xtype: 'tabpanel',
                   	    activeTab: 0,
                   	    layout:'anchor',
                   	    anchor: '100% 100%',
   	 		            defaults:{
	   	 		            bodyBorder:false,
	   	 		            border: 0,
   	 		                bodyPadding: 0
   	 		            },
   	 		            bodyBorder:false,
   	 		            border: 0,
                   	    items: [{
                   	    	title: '用户',
                   	    	anchor:'100% 100%',
                   	    	layout:'anchor',
                       	    items: [{
                       	    	id:'formpanelUser',
	                       	 	title: '查询条件',
	                       	 	xtype: 'form',
	                       	    frame:true,
	                       	    bodyBorder:false,
	                       		border: 0,
	                       	    autoHeight : true,	                       	    
	                       	    layout: 'anchor',
	                       		  defaults: {
	                       		    anchor: '100%'
	                       		  },
	                       	    fieldDefaults: {
	                       	    	labelWidth : 65,
	                       	        labelAlign: 'right'
	                       	    },	                       	    
	                       	    items: [{ // 行1
	                       	    	xtype: 'fieldcontainer',
	                       	    	bodyBorder:false,
	                       	    	border: 0,
	                       	        layout : "column",
	                       	        items : [{
	                       	           xtype: 'fieldcontainer',
	                       	           columnWidth : .3,  
	                       	           layout : "form",
	                       	           //frame:true,
	                       	           bodyBorder:false,
	                       	       	   border: 0,
	                       	           items : [{
	                       	              xtype : "textfield",              
	                       	              fieldLabel: '用户名',
	                       	              name: 'userName',
	                       	              emptyText: '请输入',
	                       	              width : 120
	                       	             }]
	                       	          }, {
	                       	           xtype: 'fieldcontainer',
	                       	           columnWidth : .3,
	                       	           layout : "form",
	                       	           bodyBorder:false,
	                       	       	   border: 0,
	                       	           items : [{        	  
	                       	              xtype : "textfield",
	                       	              fieldLabel: '登录名',
	                       	              name: 'account',
	                       	              emptyText: '请输入',
	                       	              width : 120
	                       	             }]
	                       	          }, 
	                       	          {
	                       	              xtype: 'fieldcontainer',
	                       	              columnWidth : .3,
	                       	              layout : "form",
	                       	              bodyBorder:false,
	                       	          	  border: 0,
	                       	              items : [{        	  
	                       	                 xtype : "textfield",
	                       	                 fieldLabel: '部门',
	                       	                 name: 'department',
	                       	                 emptyText: '请选择',
	                       	                 width : 120
	                       	                }]
	                       	             }]
	                       	       }],
                       	         buttons: [{
                       	            text: '查询',
                       	            scope: this,
                       	            handler: function() {                       	            	
                       	               var formpanelUser = Ext.getCmp('formpanelUser');
                       	               var form = formpanelUser.getForm();
                       	               var username = form.findField('userName').getValue();
                       	               var proxy = storeUser.getProxy();
                       	               proxy.setExtraParam("username", username);
                       	            	storeUser.loadPage(1);                       	              
                       	           }
                       	        },{
                       	            text: '清除',
                       	            scope: this,
                       	            handler: function() {
                       	            	var formpanelUser = Ext.getCmp('formpanelUser');
                        	            var form = formpanelUser.getForm();
                        	            form.reset();
                       	           }
                       	        }]    
                       		},{
                       			id:'gridpanelUser',
                       			xtype:'grid',
                       	        title: '用户列表',
                       	        columns: [
                       				{
                       				    text: '编号',
                       				    dataIndex: 'id',
                       				    width: 100
                       				},
                       			    {
                       			        text: '登录名',
                       			        dataIndex: 'account',
                       			        width: 150
                       			    },
                       			    {
                       			        text: '密码',
                       			        dataIndex: 'password',
                       			        hidden: false,
                       			        width: 150
                       			    },
                       			    {
                       			        text: '用户名',
                       			        dataIndex: 'userName',
                       			        width: 150
                       			    },
                       			    {
                       			        text: '部门',
                       			        dataIndex: 'departmentName',
                       			        flex: 1
                       			    }
                       	    	],
                       			store: storeUser,                       	        
                       	        dockedItems: [{
                       		        xtype: 'pagingtoolbar',
                       		        store: storeUser,
                       		        dock: 'bottom',
                       		        displayInfo: true
                       		    }],
                       		    tbar: [{
                       		    	xtype:'buttongroup',
                       	            items: [{text: '刷新',
                       		        iconCls: 'findIcon'
                       		    }]},'-',{
                       		    	xtype:'buttongroup',
                       	            items: [{text: '添加用户',
                       		        iconCls: 'addIcon'
                       		    }]},{
                       		    	xtype:'buttongroup',
                       	            items: [{text: '删除用户',
                       		        iconCls: 'deleteIcon'
                       		    }]}]
                       	    }]
                   	    },{
   	 	              		title: '模块权限',
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
         	             			    width: 150
         	             			},
         	             		    {
         	             		        text: '分类',
         	             		        dataIndex: 'category',
         	             		        width: 150
         	             		    },
         	             		  	{
         	             		        text: '排序',
         	             		        dataIndex: 'sortOrder',         	             		        
         	             		        width: 50
         	             		    },
         	             		    {
         	             		        text: '叶节点',
         	             		        dataIndex: 'leaf',
         	             		        width: 50,
         	             		      	renderer:showYesNo
         	             		    },
	       	             		    {
	       	             		        text: '说明',
	       	             		        dataIndex: 'description',
	       	             		        width: 250
	       	             		    }
         	                 	],
    	     	           		listeners: {
    	     	           	    	itemclick: function(view,record,item,index,e) {	           		   
    	     	           		    
    	     	           	    	}
    	     	           	},
    	                    tbar: [{
    	              	    	xtype:'buttongroup',
    	                       	items: [{text: '刷新',
    	           	        	iconCls: 'addIcon',
	    	           	     	handler: showAdd
	    	           	    }]},'-',{
	    	             	    	xtype:'buttongroup',
	    	                        items: [{text: '重置',
	    	             	        iconCls: 'addIcon',
	    	             	     	handler: showAdd
	    	             	    }]},{
	    	             	    	xtype:'buttongroup',
	    	                       	items: [{text: '授权',
	    	           	        	iconCls: 'editIcon'
	    	           	    }]}]
                   	    }, {     
                   	    	title: '功能权限',
                   	    	anchor:'100% 100%',
                   	    	layout: 'hbox',
                            defaults: {           	 		            
                           	 	anchor: '100% 100%'
                           	 	//bodyPadding: 0,
                        	},	
                       	    items: [{
	   	                     	title: '菜单',
	   	         	            width:200,
	   	         	            height:'100%',
	   	         	            xtype: 'treepanel',
		   	         	        useArrows : true,
	   	 	     	    		rootVisible: false,
	   	         	            store: treeStoreRoleModule,
		   	         	        listeners: {
		     	           	    	itemclick: function(view,record,item,index,e) {
		     	           	    		var items = record.raw.operate;
		     	           	    	 	var checkboxGroupOperate = Ext.create('Ext.form.CheckboxGroup',{columns: 3,items:items, bodyPadding: 10});
		     	           	    		var checkboxGroupOperatePanel = Ext.getCmp('checkboxGroupOperatePanel');
		     	           	    		checkboxGroupOperatePanel.removeAll();
		     	           	    		checkboxGroupOperatePanel.add(checkboxGroupOperate);
		     	           	    		checkboxGroupOperatePanel.doLayout();
		     	           	    	}
		     	           		}
                       	    },{
	   	                     	title: '按钮',
	   	         	            width: '100%',
	   	         	            height:'100%',
	   	         	            xtype: 'panel',
	   	         	            id: 'checkboxGroupOperatePanel',
		   	         	        layout:'anchor',
		   	         	        anchor:'100% 100%',
			   	         	    defaults: {
	                           	 	anchor: '100% 100%'
	                           	 	//bodyPadding: 0,
	                        	}
                       	    }],
	   	                    tbar: [{
	   	               	    	xtype:'buttongroup',
	   	                        items: [{text: '刷新',
	   	            	        iconCls: 'addIcon',
	   	            	     handler: showAdd
	   	            	    }]},'-',{
	   	              	    	xtype:'buttongroup',
	   	                          items: [{text: '重置',
	   	              	        iconCls: 'addIcon',
	   	              	     handler: showAdd
	   	              	    }]},{
	   	              	    	xtype:'buttongroup',
	   	                        items: [{text: '授权',
	   	            	        iconCls: 'editIcon'
	   	            	    }]}],
		   	            	listeners: {
		   	            		activate: function(tab, options) {		   	            			
		   	            			treeStoreRoleModule.setProxy({
						   	            type: 'ajax',
						   	            url: baseDataPath+'/permission/queryResourcesOfRoleByRoleId',
						   	         	extraParams:{'roleId': selectedRole.id}
						   	        });
		   	            			treeStoreRoleModule.reload();
		   	                  }
		   	              }
                        }]	                	
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