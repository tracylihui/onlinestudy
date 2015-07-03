package org.lijiao.cms.dao.impl;

import org.lijiao.basic.dao.BaseDao;
import org.lijiao.basic.model.Pager;
import org.lijiao.cms.dao.IStudentDao;
import org.lijiao.cms.model.Student;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDao extends BaseDao<Student> implements IStudentDao {

	@Override
	public Student loadByUsername(String username) {
		String hql = "from Student where username=?";
		return (Student) this.queryObject(hql, username);
	}

	@Override
	public Pager<Student> findStudent() {
		return this.find("from Student");
	}


}
