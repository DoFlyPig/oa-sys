package org.zj.oasys.web.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zj.oasys.domain.Privilege;
import org.zj.oasys.service.IPrivilegeService;

import com.opensymphony.xwork2.ActionContext;
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

	@Autowired
	private IPrivilegeService privilegeService;
	
	
	@Action(
			value="/index",
			results={
						@Result(name="index",location="/WEB-INF/view/home/index.jsp")
			}
		)
	public String index() {
		
		List<Privilege> privileges = privilegeService.findTopmenus();
			
		ActionContext.getContext().put("privileges", privileges);
		
		return "index";
	}
	

	@Action(
			value="/welcome",
			results={
						@Result(name="welcome",location="/WEB-INF/view/home/welcome.jsp")
			}
		)
	public String welcome() {
		
		return "welcome";
	}
	
}
