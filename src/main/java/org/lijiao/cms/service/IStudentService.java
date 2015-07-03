package org.lijiao.cms.service;


import org.lijiao.basic.model.Pager;
import org.lijiao.cms.model.Student;

public interface IStudentService {
	/**
	 * 添加用户，需要判断用户名是否存在，如果存在抛出异常
	 * 
	 * @param user
	 *            用户对象
	 * @param rids
	 *            用户的所有角色信息
	 * @param gids
	 *            用户的所有组信息
	 */
	public Student add(Student student);

	/**
	 * 删除用户，注意需要把用户和角色和组的对应关系删除 如果用户存在相应的文章不能删除
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 用户的更新，如果rids中的角色在用户中已经存在，就不做操作
	 * 如果rids中的角色在用户中不存在就要添加，如果用户中的角色不存在于rids中需要进行删除 对于group而已同样要做这个操作
	 * 
	 * @param user
	 * @param rids
	 * @param gids
	 */

	public void update(Student student);

	/**
	 * 列表用户
	 */
	public Pager<Student> findStudent();

	/**
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public Student load(int id);

	public Student login(String username, String password);
	public Student loadByUsername(String username);
}
