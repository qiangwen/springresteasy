package com.jrj.resteasy;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;

import org.jboss.resteasy.annotations.Form;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.jrj.entity.FormEntity;

@Controller
@Path("/content")
public class ContentService {

	
	/**
	 * path通配符
	 * @return
	 */
	@Path("/pathRegular/{msg}")
	@GET
	public String pathRegular(@PathParam("msg") String m){
		
		return m;
	}
	/**
	 * {var:.*}代表可以是任意多个路径名，如：a/b/c
	 * @param m
	 * @return
	 */
	@Path("/pathRegular1/{var:.*}/{msg}")
	@GET
	public String pathRegular1(@PathParam("msg") String m){
		
		return m;
	}
	
	/**
	 * {var}代表只能有一个路径名，如：a
	 * @param m
	 * @return
	 */
	@Path("/pathRegular1/{var}/{msg}")
	@GET
	public String pathRegular2(@PathParam("msg") String m){
		
		return m;
	}
	
	/**
	 * @PathParam的用法
	 * @param param
	 * @return
	 */
	@Path("/pathParam/{param}")
	@GET
	public String pathParam(@PathParam("param") String param){
		
		return "hello"+param;
	}
	
	/**
	 * @QueryParam的用法，如：GET /queryParam/2?name=wenqiang&age=23
	 * @param id
	 * @param name
	 * @param age
	 * @return
	 */
	@Path("/queryParam/{id}")
	@GET
	public String queryParam(@PathParam("id") int id,
							@QueryParam("name") String name,
							@QueryParam("age") int age){
		if(id==1){
			return "this is no people";
		}
		return "name is "+name+"age is" +age;
	}
	
	/**
	 * @HeadParam的用法，其中HeaderParam可以获取httpheader中的一些信息
	 * @param head
	 * @param myhead
	 * @return
	 */
	@Path("/headParam/{head}")
	@GET
	public String headParam(@PathParam("head") String head,
							@HeaderParam("myhead") String myhead){
		return head + " is " + myhead;
	}
	
	/**
	 * @MatixParam的用法(矩阵参数)，如：GET：/matixParam/wenqiang;sex=male;age=23
	 * @param name
	 * @return
	 */
	@Path("/matixParam/{name}")
	@GET
	public String matixParam(@PathParam("name") String name,
							@MatrixParam("sex") String sex,
							@MatrixParam("age") int age){
		return "name is " + name +" age is " + age +" sex is "+sex;
	}
	
	/**
	 * 使用@PathParam和PathSegment来实现MatixParam
	 * 当矩阵参数中存在着相同的参数使用@MatixParam来获取参数会出现问题，此时应该使用@PathParam和PathSegment实现
	 * GET：/matixParam/wenqiang;sex:male;age:23
	 * @return
	 */
	@Path("/pathSegment/{info}")
	@GET
	public String pathSegment(@PathParam("info") PathSegment info){
		StringBuffer sb = new StringBuffer("name is ");
		 MultivaluedMap<String, String> paraMap = info.getMatrixParameters();
		 /**
		  * 也可以使用迭代器
		  */
		/* for(Iterator<String> it = paraMap.keySet().iterator();it.hasNext();){
			 String temp = paraMap.get(it.next()).get(0);
		 }*/
		 String temp = paraMap.get("name").get(0);
		 sb.append(temp);
		 temp = paraMap.get("age").get(0);
		 sb.append(" age is " + temp);
		 temp = paraMap.get("sex").get(0);
		 sb.append(" sex is " + temp);
		 return sb.toString();
	}
	
	/**
	 * @CookieParam的用法
	 * @param cookie
	 * @param sum
	 * @param sessionid
	 * @return
	 */
	@Path("/cookieParam/{cookieName}")
	@GET
	public String cookieParam(@PathParam("cookieName") String cookie,
							@QueryParam("sum") int sum,
							@CookieParam("JSESSIONID") String sessionid){
		
		return "cookieName is " + cookie + "sessionId is " + sessionid + " sum is " + sum;
	}
	
	/**
	 * @FormParam的用法，通过form表单传递值
	 * @param name
	 * @param firstname
	 * @param secondname
	 * @return
	 */
	@Path("/formParam/{name}")
	@POST
	public String formParam(@PathParam("name") @DefaultValue("limin") String name,
							@FormParam("firstname") String firstname,
							@FormParam("secondname") String secondname){
		
		return name+ " firstname is " + firstname + " secondname is " + secondname;
	}
	
	/**
	 * @Form的用法，使用@Form可以把其他的@*Param的类型的参数放在同一个类中
	 * @return
	 */
	@Path("/form")
	@GET
	public String form(@Form FormEntity entity){
		return entity.toString();
	}
	
	/**
	 * @Context的用法，@Context只允许注入以下这些类的实例：
	 * javax.ws.rs.core.HttpHeaders
	 * javax.ws.rs.core.UriInfo
	 * javax.ws.rs.core.Request
	 * javax.servlet.HttpServletRequest
	 * javax.servlet.HttpServletResponse
	 * javax.servlet.ServletConfig
	 * javax.servlet.ServletContext
	 * javax.ws.rs.core.SecurityContext
	 * @param headers
	 * @return
	 */
	@Path("/context")
	@POST
	public String context(@Context HttpHeaders headers,String content){
		
		JSONObject jsonObject = JSONObject.parseObject(content);
		String name = jsonObject.getString("name");
		int age = jsonObject.getIntValue("age");
		return name+","+age;
	}
}
