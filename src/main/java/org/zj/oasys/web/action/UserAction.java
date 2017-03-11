package org.zj.oasys.web.action;

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
import org.zj.oasys.domain.User;
import org.zj.oasys.service.IUserServcie;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope(value="prototype")
@ParentPackage("struts-default")
@Namespace("/user")
@Results( 
			{ 
				@Result(name = "error", location = "/error.jsp")
			}
		) 

@ExceptionMappings( { @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") }) 
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private static final long serialVersionUID = 5346584665777755432L;

	@Autowired
	private IUserServcie userService;
	
	private User user = new User();
	
	@Action(
				value="/list",
				results={
							@Result(name="list",location="/WEB-INF/view/user/list.jsp")
				}
			)
	public String list() {
		return "list";
	}
	
	@Action(
				value="/save",
				results={
							@Result(name="tolist",location="list",type="redirectAction")
				}
			
			)
	public String save() {
		
		userService.save(user);
		
		return "tolist";
	}
	
	@Action(	
				value="/saveUI",
				results={
							@Result(name="saveUI",location="/WEB-INF/view/user/saveUI.jsp")
				}
			
			)
	public String saveUI() {
		
		return "saveUI";
	}
	
	@Action(
			value="/editeUI",
			results={
						@Result(name="editeUI",location="/WEB-INF/view/user/saveUI.jsp")
			}
		
		)
	public String editeUI() {
		
		return "editeUI";
	}

	@Override
	public User getModel() {
		
		return user;
	
	}
	
}
