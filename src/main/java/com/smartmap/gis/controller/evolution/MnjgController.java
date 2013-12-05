package com.smartmap.gis.controller.evolution;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartmap.gis.dao.evolution.MnjgDao;
import com.smartmap.gis.model.evolution.Mnjg;

@Controller
@RequestMapping("/mnjg")
public class MnjgController {

	private static final Logger logger = LoggerFactory.getLogger(MnjgController.class);

	@Autowired
	private MnjgDao mnjgDao;
	
	@RequestMapping(method=RequestMethod.GET, value="edit")
	public ModelAndView editMnjg(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit person id : "+id);				
		ModelAndView mav = new ModelAndView();
 		mav.setViewName("edit");
 		Mnjg mnjg = null;
 		if (id == null) {
 			mnjg = new Mnjg();
 		} else {
 			mnjg = mnjgDao.find(id);
 		}
 		mav.addObject("mnjg", mnjg);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="edit") 
	public String savePerson(@ModelAttribute Mnjg mnjg) {
		logger.debug("Received postback on person "+mnjg);
		mnjgDao.save(mnjg);
		return "redirect:list";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="list")
	public ModelAndView listMnjg() {
		logger.debug("Received request to list mnjgs");
		ModelAndView mav = new ModelAndView();
		List<Mnjg> mnjg = mnjgDao.getMnjg();
		logger.debug("Mnjg Listing count = "+mnjg.size());
		mav.addObject("mnjg",mnjg);
		mav.setViewName("list");
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST,value="test")
    @ResponseBody  
    public Object test(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam(value="codeArrayString",required=false) String codeArrayString,
    		@RequestParam(value="name",required=false) String name){
        System.out.println("test....................");
		//
        //取出参数
        System.out.println(request.getAttribute("codeArrayString"));
        System.out.println(request.getParameter("name"));
  		//
  		System.out.println("codeArrayString="+codeArrayString+"&name="+name);
  		//编号
  		String resultJson="";
  		//查询数据库
  		
  		//
  		//组织数据
  		
  		//数据输出
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		//
  		jsonObject = new JSONObject();
  		jsonObject.put("code", "s00146");
  		jsonObject.put("name", "老子山水位站");
  		jsonObject.put("x", "368608");
  		jsonObject.put("y", "3674409");
  		jsonObject.put("sttp", "RR");
  		jsonArray.add(jsonObject);
  		//
  		jsonObject = new JSONObject();
  		jsonObject.put("code", "s00164");
  		jsonObject.put("name", "蒋坝水位站");
  		jsonObject.put("x", "381642");
  		jsonObject.put("y", "3664916");
  		jsonObject.put("sttp", "PP");
  		jsonArray.add(jsonObject);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println("蒋坝水位站");
  		return resultJson;
    }  
	
	@RequestMapping(method=RequestMethod.POST,value="databaseTest")
    @ResponseBody  
    public Object databaseTest(HttpSession session, HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam(value="sqId",required=false) String sqId,
    		@RequestParam(value="sj",required=false) String sj) throws UnsupportedEncodingException{      
		response.setCharacterEncoding("UTF-8");
		//
        //取出参数
        //System.out.println(request.getAttribute("codeArrayString"));
        //System.out.println(request.getParameter("name"));
  		//
  		System.out.println("sqId="+sqId+"&sj="+sj);
  		//编号
  		String resultJson="";
  		//查询数据库
  		List<Mnjg> mnjgList = mnjgDao.getMnjgBySqIdAndTime(sqId, sj);
  		//
  		//组织数据
  		//System.out.println(new String("蒋坝水位站".getBytes("ISO-8859-1"),"GBK"));
  		String a = "蒋坝水位站";
  		String b = new String(a.getBytes("ISO-8859-1"),"UTF-8");
  		String c = new String(a.getBytes("ISO-8859-1"),"GBK");
  		String d = new String(a.getBytes("GBK"),"UTF-8");
  		String e = new String(a.getBytes("UTF-8"),"GBK");
  		//
  		String f = new String(a.getBytes("ISO-8859-1"),"gb2312");
  		String g = new String(a.getBytes("gb2312"),"UTF-8");
  		String h = new String(a.getBytes("UTF-8"),"gb2312");
  		System.out.println(a+ "    " +b+"    "+c+ "    " +d+"    "+e+"    "+f+ "    " +g+"    "+h);
  		//
  		String des = new String(a.getBytes("iso8859-1"),"UTF-8");
  		//数据输出
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		//
  		Iterator<Mnjg> iteratorMnjg = mnjgList.iterator();
  		while (iteratorMnjg.hasNext()) {
  			Mnjg mnjg = iteratorMnjg.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("code", mnjg.getId());
  	  		jsonObject.put("name", mnjg.getDm());
  	  		jsonObject.put("x", mnjg.getDmx());
  	  		jsonObject.put("y", mnjg.getDmy());
  	  		jsonObject.put("length", mnjg.getDmlcz());
  	  		jsonObject.put("river", mnjg.getHl());
  	  		jsonObject.put("value", mnjg.getNd());
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
    }  
	
	 // http://127.0.0.1:8010/data/header  
    @RequestMapping(value = "header", method = RequestMethod.GET)  
    @ResponseBody  
    public String withHeader(@RequestHeader String Accept) {  
        return "Obtained 'Accept' header '" + Accept + "'";  
    }  
  
    @RequestMapping(value = "cookie", method = RequestMethod.GET)  
    @ResponseBody  
    public String withCookie(@CookieValue String openid_provider) {  
        return "Obtained 'openid_provider' cookie '" + openid_provider + "'";  
    }  
  
    @RequestMapping(value = "body", method = RequestMethod.POST)  
    @ResponseBody  
    public String withBody(@RequestBody String body) {  
        return "Posted request body '" + body + "'";  
    }  
      
    @RequestMapping(value="entity", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")  
    @ResponseBody  
    public String withEntity(HttpEntity<String> entity) throws UnsupportedEncodingException {
    	System.out.println("result: \n"+entity.getBody());
    	//    	
    	String body = entity.getBody();
        System.out.println(body);
        String b = new String(body.getBytes("ISO-8859-1"),"UTF-8");
        return "Posted '" + entity.getBody() + "'; \nheaders = " + b;
    }  
    
    @RequestMapping("/entity/headers")
    public ResponseEntity<String> responseEntityCustomHeaders() throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("charset", "UTF-8");
        String body = "管理员:";
        System.out.println(body);
        return new ResponseEntity<String>(body, headers, HttpStatus.OK);
    }  
	
    public static void main(String[] args) throws UnsupportedEncodingException {		
		String a = "蒋坝水位站";
  		String b = new String(a.getBytes("ISO-8859-1"),"UTF-8");
  		String c = new String(a.getBytes("ISO-8859-1"),"GBK");
  		String d = new String(a.getBytes("GBK"),"UTF-8");
  		String e = new String(a.getBytes("UTF-8"),"GBK");
  		//
  		String f = new String(a.getBytes("ISO-8859-1"),"gb2312");
  		String g = new String(a.getBytes("gb2312"),"UTF-8");
  		String h = new String(a.getBytes("UTF-8"),"gb2312");
  		System.out.println(a+ "    " +b+"    "+c+ "    " +d+"    "+e+"    "+f+ "    " +g+"    "+h);
	}
}
