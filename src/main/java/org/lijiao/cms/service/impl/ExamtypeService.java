package org.lijiao.cms.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.lijiao.basic.model.Pager;
import org.lijiao.cms.dao.IExamtypeDao;
import org.lijiao.cms.dao.IQuestionDao;
import org.lijiao.cms.model.CmsException;
import org.lijiao.cms.model.Examtype;
import org.lijiao.cms.model.Question;
import org.lijiao.cms.service.IExamtypeService;
import org.springframework.stereotype.Service;

@Service("examtypeService")
public class ExamtypeService implements IExamtypeService{
	private IExamtypeDao examtypeDao;
	private IQuestionDao questionDao;
	public IExamtypeDao getExamtypeDao() {
		return examtypeDao;
	}
	@Inject
	public void setExamtypeDao(IExamtypeDao examtypeDao) {
		this.examtypeDao = examtypeDao;
	}

	public IQuestionDao getQuestionDao() {
		return questionDao;
	}
	@Inject
	public void setQuestionDao(IQuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	@Override
	public void add(Examtype examtype) {
		// TODO Auto-generated method stub
		examtypeDao.add(examtype);
	}

	@Override
	public void delete(int id) {
		List<Question> questions = questionDao.listExamtypeQuestions(id);
		if(questions!=null&&questions.size()>0) throw new CmsException("删除的试卷中还有试题，不能删除");		
		examtypeDao.delete(id);
	}

	@Override
	public Examtype load(int id) {
		// TODO Auto-generated method stub
		return examtypeDao.load(id);
	}

	@Override
	public void update(Examtype examtype) {
		examtypeDao.update(examtype);
	}

	@Override
	public List<Examtype> listExamtype() {
		return examtypeDao.listExamtype();
	}

	@Override
	public Pager<Examtype> findExamtype() {
		return examtypeDao.findExamtype();
	}

	@Override
	public void deleteExamtypeQuestions(int eid) {
		examtypeDao.deleteExamtypeQuestions(eid);
	}
}
