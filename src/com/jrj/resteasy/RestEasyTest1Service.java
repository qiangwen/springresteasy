package com.jrj.resteasy;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.jrj.entity.Person;

@Controller
@Path("/restful")
public class RestEasyTest1Service {
		
	
	@Path("/moneyAdd/{type}")
	@GET
	@Produces("application/json;charset=utf-8")
	public String moneyAdd(@PathParam("type") String type,@QueryParam("one") double money1,@QueryParam("two") double money2){
		
		String str = type + (money1+money2);
		
		return JSONObject.toJSONString(str);
	}
	
	@Path("/compare")
	@POST
	@Produces("application/json;charset=utf-8")
	public String compareTo(@FormParam("name1") String name1,@FormParam("name2") String name2){
		
		if(StringUtils.isBlank(name1)||StringUtils.isBlank(name2))
			return "name is null";
		if(name1.equals(name2)){
			Person p = new Person();
			p.setAge(10);
			p.setEmail("com.jrj.wen@qq.com");
			p.setName("wenqiang");
			
			return JSONObject.toJSONString(p);
		}
		return "name is not equal";
	}
	
	@Path("/delete")
	@DELETE
	@Produces("application/json,charset=utf-8")
	public String delete(@FormParam("msg") String msg){
		
		if(StringUtils.isBlank(msg))
			return "msg is blank";
		if("delete".equals(msg))
			return "msg is deleted";
		return "msg failure delete";
	}
}
