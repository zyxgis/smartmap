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
	var treeStore = new Ext.data.TreeStore({
	    //autoLoad: false,
        proxy: {
            type: 'ajax',
            url: baseDataPath+'/organization/queryAllToTree'
        },
        root: {
            text: 'root',
            id: 'root',
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
	        url: baseDataPath+'/employee/queryByOrganizationId',
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
                items:[{
                	title: '职员信息查询',
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
                    	//frame:true,
                        layout : "column", // 从左往右的布局
                        items : [{
                           xtype: 'fieldcontainer',
                           columnWidth : .3, // 该列有整行中所占百分比           
                           layout : "form", // 从上往下的布局      
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
                             }/*, {
                           xtype: 'fieldcontainer',
                           columnWidth : .3,
                           layout : "form",
                           border:false,
                           bodyBorder:false,           
                           items : [{
                        	   xtype:'checkbox',
                               fieldLabel: '是否禁用',
                               name: 'forbidden',
                              width : 120
                             }]
                          }*/]
                       }],
                         buttons: [{
                            text: '查询',
                            scope: this,
                            handler: function() {               
                               var form = formPanel.getForm();
                               var username = form.findField('userName').getValue();
                               var proxy = gridPanel.store.getProxy();
                               proxy.setExtraParam("username", username);
                               gridPanel.store.loadPage(1);
                               /*
                               var conn = new Ext.data.Connection();  
                		       conn.request({  
                			       url: baseDataPath+'/user/queryUsersByRoleId?limit=10&page=1',   
                			       params: {roleId:2},
                			       method: 'get',
                			       scope: this,
                			       callback:function(options,success, response){   
                			       if(success){   
                				       //Ext.MessageBox.alert("提示","所选记录成功删除！");  
                				       store.reload();  
                				       gridPanel.store.reload();
                			       }   
                			       else   
                			       { 
                			       	   Ext.MessageBox.alert("提示","所选记录删除失败！");}   
                			       }
                		       }); 
                		       */
                           }
                        },{
                            text: '清除',
                            scope: this,
                            handler: function() {
                               formPanel.getForm().reset();               
                           }
                        }]    
                	},
                	{
                		id:'gridpanel',
   		            	title: '职员信息列表',
   			            //region: 'center',
   			            xtype: 'gridpanel',
   			            store: store,	            
   			            dockedItems: [{
   			    	        xtype: 'pagingtoolbar',
   			    	        store: store,
   			    	        dock: 'bottom',
   			    	        displayInfo: true
   			    	    }],	   			    	 
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
   		           	    }],
	   		           	tbar: [{
	   	           	    	xtype:'buttongroup',
	   	                    items: [{text: '刷新',
	   	        	        iconCls: 'addIcon',
	   	        	     handler: showAdd
	   	        	    }]},'-',{
	   	          	    	xtype:'buttongroup',
	   	                      items: [{text: '添加',
	   	          	        iconCls: 'addIcon',
	   	          	     handler: showAdd
	   	          	    }]},{
	   	          	    	xtype:'buttongroup',
	   	                    items: [{text: '修改',
	   	        	        iconCls: 'editIcon'
	   	        	    }]},{
	   	          	    	xtype:'buttongroup',
	   	                      items: [{text: '删除',
	   	          	        iconCls: 'deleteIcon'
	   	          	    }]}]
                	}
                	
                	]
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