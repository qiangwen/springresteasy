package com.jrj.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;

@Controller
@Path("/helloworld")
public class HelloWorldService {

	@Path("/sayHello/{msg}")
	@GET
	@Produces("application/json;charset=utf-8")
	public String sayHello(@PathParam("msg") String msg){
		
		String info ="hello world!"+ msg;
		
		return info;
		
	}
}
