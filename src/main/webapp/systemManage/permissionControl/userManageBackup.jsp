<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    var gridPanel = new Ext.grid.Panel({
    	id:'gridPanelUser',
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
		        renderer:showUrl,
		        flex: 1
		    }
    	],
		store: store,
        renderTo: 'gridUser',
        dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: store,
	        dock: 'bottom',
	        displayInfo: true
	    }],
	    tbar: [{
	    	xtype:'buttongroup',
            items: [{text: '刷新',
	        iconCls: 'findIcon',
	        handler: refresh
	    }]},'-',{
	    	xtype:'buttongroup',
            items: [{text: '添加',
	        iconCls: 'addIcon',
	        handler: add
	    }]},{
	    	xtype:'buttongroup',
            items: [{text: '删除',
	        iconCls: 'deleteIcon',
	        handler: remove
	    }]},{
	    	xtype:'buttongroup',
            items: [{text: '修改',
	        iconCls: 'editIcon',
	        handler: edit
	    }]},'-',{
	    	xtype:'buttongroup',
            items: [{text: '查看详细',
	        iconCls: 'findIcon',
	        handler: detail
	    }]},{
	    	xtype:'buttongroup',
            items: [{text: '重置密码',
	        iconCls: 'findIcon',
	        handler: resetPassword
	    }]},{
	    	xtype:'buttongroup',
            items: [{text: '分配角色',
	        iconCls: 'findIcon',
	        handler: grandRole
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
    //
    function refresh()
    {
    	
    }
    function detail()
    {
    	
    }
    function add() {
		var addWindow = new Ext.Window({
			title: '添加用户',
			id: 'addWindow',	       
	        height:360,
	        width: 350,
	        layout: 'fit', 
	        items:[{
				  xtype: 'form',
				  url: baseDataPath+'/user/save',
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
				  items: [{
	                    fieldLabel: '账号',
	                    name: 'loginUsername',
	                    allowBlank: false
	                  },
	                  {
	                    fieldLabel: '密码',
	                    name: 'loginPassword',
	                    allowBlank: false
	                  },                  
	                  {
	                    fieldLabel: '姓名',
	                    name: 'name',
	                    allowBlank: false
	                  },
	                  {
	                    fieldLabel: '性别',
	                    name: 'gender',
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
	                          "value": "1",
	                          "name": "男"
	                        },
	                        {
	                          "value": "0",
	                          "name": "女"
	                        }
	                      ]
	                    })
	                  },
	                  {
	                    fieldLabel: '邮箱',
	                    name: 'email'
	                  },
	                  {
	                    fieldLabel: '手机号码',
	                    name: 'mobileNumber'
	                  },
	                  {
	                    fieldLabel: '组织',
	                    name: 'organizationId',
                    	xtype: 'treecombox',
	                    align: 'center',
	                    displayField: 'text',
	                    valueField: 'id',
	                    store: Ext.create('Ext.data.TreeStore',{
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
	                        url: baseDataPath+'/organization/queryAllToTree'
	                      }
	                    })
	                  },
	                  {
	                    fieldLabel: '描述',
	                    name: 'description',
	                    xtype: 'textareafield',
	                    grow: true,
	                    anchor: '100%'
	                  }],
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
    function edit()
    {
    	var gridPanelUser = Ext.getCmp('gridPanelUser');
    	
    }
    function remove()
    {
    	
    }
    function resetPassword()
    {
    	
    }
    function grandRole()
    {
    	
    }
});
</script>
</head>
<body>
	<div id="form">  
	</div>
	<div id="gridUser">        
	</div>
</body>
</html>