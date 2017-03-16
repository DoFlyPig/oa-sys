package org.zj.oasys.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope(value="prototype")
@ParentPackage("struts-default")
@Namespace("/home")
@Results( 
			{ 
				@Result(name = "error", location = "/error.jsp")
			}
		) 

@ExceptionMappings( { @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") }) 
public class HomeAction extends ActionSupport {
	
	@Action(
			value="/index",
			results={
						@Result(name="index",location="/WEB-INF/view/home/index.jsp")
			}
		)
	public String index() {
		
		return "index";
	}
	
}
