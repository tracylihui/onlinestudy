package org.lijiao.cms.dao;

import org.lijiao.basic.dao.IBaseDao;
import org.lijiao.basic.model.Pager;
import org.lijiao.cms.model.Student;

public interface IStudentDao extends IBaseDao<Student>{
	
	/**
	 * 根据用户名获取用户对象
	 * @param username
	 * @return
	 */
	public Student loadByUsername(String username);
	
	public Pager<Student> findStudent();
}
