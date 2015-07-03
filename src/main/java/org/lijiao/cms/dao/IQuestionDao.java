package org.lijiao.cms.dao;

import java.util.List;

import org.lijiao.basic.dao.IBaseDao;
import org.lijiao.basic.model.Pager;
import org.lijiao.cms.model.Examtype;
import org.lijiao.cms.model.Question;
import org.lijiao.cms.model.QuestionExamtype;

public interface IQuestionDao extends IBaseDao<Question> {
	public List<Question> listQuestion();
	public Pager<Question> findQuestion();
	public Pager<Question> findExamtypeQuestions(int eid);
	public List<Question> listExamtypeQuestions(int eid);
	public void addQuestionExamtype(Question question, Examtype examtype);
	public QuestionExamtype loadQuestionExamtype(int questionId, int examtypeId);
}
