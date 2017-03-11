package org.zj.oasys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zj.oasys.dao.IUserDao;
import org.zj.oasys.domain.User;
import org.zj.oasys.service.IUserServcie;

@Service
@Transactional
public class UserServiceImpl implements IUserServcie {

	@Autowired
	private IUserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
		int i = 1 / 0;
	}

}
