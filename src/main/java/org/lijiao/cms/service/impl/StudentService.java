package org.lijiao.cms.service.impl;


import java.util.Date;

import javax.inject.Inject;

import org.lijiao.basic.model.Pager;
import org.lijiao.cms.dao.IStudentDao;
import org.lijiao.cms.model.Student;
import org.lijiao.cms.service.IStudentService;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentService implements IStudentService {
	private IStudentDao studentDao;

	public IStudentDao getStudentDao() {
		return studentDao;
	}

	@Inject
	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public Student add(Student student) {
		student.setCreateDate(new Date());
		return studentDao.add(student);
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public void update(Student student) {
		studentDao.update(student);
	}

	@Override
	public Pager<Student> findStudent() {
		return studentDao.findStudent();
	}

	@Override
	public Student load(int id) {
		return studentDao.load(id);
	}

	@Override
	public Student login(String username, String password) {
		// TODO Auto-generated method stub
		return studentDao.loadByUsername(username);
	}
	@Override
	public Student loadByUsername(String username) {
		return studentDao.loadByUsername(username);
	}

}
