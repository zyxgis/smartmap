package com.smartmap.gis.controller.evaluate;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartmap.gis.dao.evaluate.PjjgDao;
import com.smartmap.gis.model.evaluate.Pjjg;


@Controller
@RequestMapping("/pjjg")
public class PjjgController {

	private static final Logger logger = LoggerFactory.getLogger(PjjgController.class);

	@Autowired
	private PjjgDao pjjgDao;
	
	@RequestMapping(method=RequestMethod.GET, value="edit")
	public ModelAndView editPjjg(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit person id : "+id);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("edit");
 		Pjjg pjjg = null;
 		if (id == null) {
 			pjjg = new Pjjg();
 		} else {
 			pjjg = pjjgDao.find(id);
 		}
 		mav.addObject("pjjg", pjjg);
		return mav;		
	}
	
	
	@RequestMapping(value="databaseTest")
    @ResponseBody  
    public Object databaseTest(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
    		@RequestBody @RequestParam(value="jcsj",required=false) String jcsj) throws UnsupportedEncodingException{      
		response.setCharacterEncoding("UTF-8");
		//
        //取出参数
        System.out.println(request.getAttribute("jcsj"));
        System.out.println(request.getParameter("jcsj"));
  		//编号
  		String resultJson="";
  		//查询数据库
  		List<Pjjg> pjjgList = pjjgDao.getPjjgByJcsj(jcsj);
  		//
  		//组织数据
  		//
  		//数据输出
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		//
  		Iterator<Pjjg> iteratorPjjg = pjjgList.iterator();
  		while (iteratorPjjg.hasNext()) {
  			Pjjg pjjg = iteratorPjjg.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("code", pjjg.getJcdmbm());
  	  		jsonObject.put("name", pjjg.getJcdmmc());
  	  		jsonObject.put("x", pjjg.getX());
  	  		jsonObject.put("y", pjjg.getY());
  	  		jsonObject.put("waterType", pjjg.getSzlx());
  	  		jsonObject.put("waterTypePlan", pjjg.getMbszlx());
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
