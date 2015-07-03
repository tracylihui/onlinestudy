package org.lijiao.cms.service;

import java.util.List;

import org.lijiao.basic.model.Pager;
import org.lijiao.cms.model.Question;

public interface IQuestionService {
	public void add(Question question,int eid);
	public void delete(int id);
	public Question load(int id);
	public void update(Question question);
	public List<Question> listQuestion();
	public Pager<Question> findQuestion();
	public Pager<Question> findExamtypeQuestions(int eid);
	public List<Question> listExamtypeQuestions(int eid);
}
