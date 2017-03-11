package test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zj.oasys.domain.User;
import org.zj.oasys.service.IUserServcie;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:applicationContext.xml")
public class TestMysqlJDbc {
	@Autowired
	private IUserServcie userDao;
	
	@Test
	public void testSpring() {
		
		User user = new User();
		
		user.setBirthday(new Date());
		user.setLogname("pig");
		user.setPassword("123");
		user.setSex(1);
		
		userDao.save(user);
		
	}
	
}
