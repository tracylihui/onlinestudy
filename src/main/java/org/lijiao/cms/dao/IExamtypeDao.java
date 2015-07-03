package org.lijiao.cms.dao;

import java.util.List;

import org.lijiao.basic.dao.IBaseDao;
import org.lijiao.basic.model.Pager;
import org.lijiao.cms.model.Examtype;

public interface IExamtypeDao extends IBaseDao<Examtype> {
	public List<Examtype> listExamtype();
	public Pager<Examtype> findExamtype();
	public void deleteExamtypeQuestions(int eid);

}
