package org.lijiao.cms.dao.impl;


import java.util.List;

import org.lijiao.basic.dao.BaseDao;
import org.lijiao.cms.dao.IRoleDao;
import org.lijiao.cms.model.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public List<Role> listRole() {
		return this.list("from Role");
	}

	@Override
	public void deleteRoleUsers(int rid) {
		this.updateByHql("delete UserRole ur where ur.role.id=?",rid);
	}


}
