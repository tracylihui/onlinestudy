package org.lijiao.cms.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.lijiao.basic.model.Pager;
import org.lijiao.cms.dao.IQuestionDao;
import org.lijiao.cms.dao.impl.ExamtypeDao;
import org.lijiao.cms.model.CmsException;
import org.lijiao.cms.model.Examtype;
import org.lijiao.cms.model.Question;
import org.lijiao.cms.service.IQuestionService;
import org.springframework.stereotype.Service;
@Service("questionService")
public class QuestionService implements IQuestionService{
	private IQuestionDao questionDao;
	private ExamtypeDao examtypeDao;
	
	public IQuestionDao getQuestionDao() {
		return questionDao;
	}
	@Inject
	public void setQuestionDao(IQuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public ExamtypeDao getExamtypeDao() {
		return examtypeDao;
	}
	@Inject
	public void setExamtypeDao(ExamtypeDao examtypeDao) {
		this.examtypeDao = examtypeDao;
	}
	@Override
	public void add(Question question,int eid) {
		questionDao.add(question);
		Examtype examtype = examtypeDao.load(eid);
		if(examtype==null) throw new CmsException("要添加的考试不存在");
		questionDao.addQuestionExamtype(question, examtype);
	}

	@Override
	public void delete(int id) {
		questionDao.delete(id);		
	}

	@Override
	public Question load(int id) {
		return questionDao.load(id);
	}

	@Override
	public void update(Question question) {
		questionDao.update(question);
	}

	@Override
	public List<Question> listQuestion() {
		return questionDao.listQuestion();
	}

	@Override
	public Pager<Question> findQuestion() {
		return questionDao.findQuestion();
	}

	@Override
	public Pager<Question> findExamtypeQuestions(int eid) {
		return questionDao.findExamtypeQuestions(eid);
	}
	@Override
	public List<Question> listExamtypeQuestions(int eid) {
		return questionDao.listExamtypeQuestions(eid);
	}

}
