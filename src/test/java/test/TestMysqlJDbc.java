package test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zj.oasys.dao.IPrivilegeDao;
import org.zj.oasys.domain.Privilege;
import org.zj.oasys.domain.User;
import org.zj.oasys.service.IPrivilegeService;
import org.zj.oasys.service.IUserServcie;

@SuppressWarnings("all")
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class TestMysqlJDbc {
	
	@Autowired
	private IUserServcie userDao;
	
	@Autowired
	private IPrivilegeDao privilegeDao;
	
	
	@Autowired
	private IPrivilegeService privilegeService;
	
	
	@Test
	public void testPrivilege() {
		
		List<Privilege> privileges = privilegeService.findTopmenus();
		
		for (Privilege privilege : privileges) {
			System.out.print(privilege.getName() + ":");
			Set<Privilege> childs = privilege.getChilds();
			
			for (Privilege privilege2 : childs) {
				System.out.print("\t" + privilege2.getName() + ",");
			}
			System.out.println();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testSpring() {
		
		User user = new User();
		
		user.setBirthday(new Date());
		user.setLogname("pig");
		user.setPassword("123");
		user.setSex(1);
		
		userDao.save(user);
		
	}
	
	@Test
	public void initData() {

		Privilege p1, p2 , p3;
		p1 = new Privilege("系统管理","sys_manager",null,null,0,0,1,null);
		Privilege p11, p12, p13;
		p11 = new Privilege("用户管理","account_manager","/account_list",null,0,1,11,p1);
		p12 = new Privilege("角色管理","role_manager","/role_list",null,0,1,12,p1);
		p13 = new Privilege("部门管理","department_manager","/department_list",null,0,1,13,p1);
		
		privilegeDao.save(p1);
		privilegeDao.save(p11);
		privilegeDao.save(p12);
		privilegeDao.save(p13);
		
	}
	
}
