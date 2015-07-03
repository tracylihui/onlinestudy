package org.lijiao.cms.dao;

import java.util.List;

import org.lijiao.basic.dao.IBaseDao;
import org.lijiao.cms.model.Role;

public interface IRoleDao extends IBaseDao<Role> {
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
