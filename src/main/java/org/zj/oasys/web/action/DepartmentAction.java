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
import org.zj.oasys.domain.Account;
import org.zj.oasys.domain.Department;
import org.zj.oasys.domain.Role;
import org.zj.oasys.service.IAccountService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope(value="prototype")
@ParentPackage("struts-default")
@Results( 
			{ 
				@Result(name = "error", location = "/error.jsp")
			}
		) 
@ExceptionMappings( { @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") }) 
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	
	private Department department = new Department();
	@Action(
			value="department_list",
			results={
						@Result(name="list",location="/WEB-INF/view/department/list.jsp")
			}
		)
	public String list() {
		return "list";
	}

	@Override
	public Department getModel() {
		
		return department;
	}
	
	
}
