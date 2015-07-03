package org.lijiao.cms.dao.impl;

import java.util.List;

import org.lijiao.basic.dao.BaseDao;
import org.lijiao.basic.model.Pager;
import org.lijiao.cms.dao.IExamtypeDao;
import org.lijiao.cms.model.Examtype;
import org.springframework.stereotype.Repository;

@Repository("examtypeDao")
public class ExamtypeDao extends BaseDao<Examtype> implements IExamtypeDao {

	@Override
	public List<Examtype> listExamtype() {
		// TODO Auto-generated method stub
		return this.list("from Examtype");
	}

	@Override
	public Pager<Examtype> findExamtype() {
		// TODO Auto-generated method stub
		return this.find("from Examtype");
	}

	@Override
	public void deleteExamtypeQuestions(int eid) {
		this.updateByHql("delete QuestionExamtype qe where qe.examtype.id=?",eid);
	}


}
