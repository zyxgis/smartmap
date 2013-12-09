package com.smartmap.systemManage.controller.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.smartmap.systemManage.model.Operate;

public class OperateUtil {
	public static void operateToJsonObject(long operateCodes, List<Operate> operateList, JSONObject jsonObject)
	{
		JSONArray operateArray = new JSONArray();	  	
  		Operate operate = null;
  		for(int i=0; i<operateList.size(); i++)
  		{
  			operate = operateList.get(i);
  			if((operate.getCode() & operateCodes) == operate.getCode())
  			{
  				operateArray.add(operate.getOperateName());  	  				
  			}
  		}
  		jsonObject.put("operate", operateArray);
	}
}
