package org.zj.oasys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zj.oasys.dao.IPrivilegeDao;
import org.zj.oasys.domain.Privilege;
import org.zj.oasys.service.IPrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl implements IPrivilegeService {

	@Autowired
	private IPrivilegeDao privilegeDao;
	
	public List<Privilege> findTopmenus() {
		
		String queryString = "FROM Privilege p where p.type = ?";
		
		List<Privilege> list = privilegeDao.findList(queryString, new Object[]{0});
		
		return list;
	}

}
