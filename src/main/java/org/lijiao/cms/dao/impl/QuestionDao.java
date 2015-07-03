package org.lijiao.cms.dao.impl;

import java.util.List;

import org.lijiao.basic.dao.BaseDao;
import org.lijiao.basic.model.Pager;
import org.lijiao.cms.dao.IQuestionDao;
import org.lijiao.cms.model.Examtype;
import org.lijiao.cms.model.Question;
import org.lijiao.cms.model.QuestionExamtype;
import org.springframework.stereotype.Repository;

@Repository("questionDao")
public class QuestionDao extends BaseDao<Question> implements IQuestionDao {

	@Override
	public List<Question> listQuestion() {
		return this.list("from Question");
	}

	@Override
	public Pager<Question> findQuestion() {
		return this.find("from Question");
	}

	@Override
	public Pager<Question> findExamtypeQuestions(int eid) {
		String hql = "select qe.question from QuestionExamtype qe where qe.examtype.id=?";
		return this.find(hql, eid);
	}

	@Override
	public void addQuestionExamtype(Question question, Examtype examtype) {
		QuestionExamtype qe = this.loadQuestionExamtype(question.getId(), examtype.getId());
		if (qe != null)
			return;
		qe = new QuestionExamtype();
		qe.setExamtype(examtype);
		qe.setQuestion(question);
		this.getSession().save(qe);
	}

	@Override
	public QuestionExamtype loadQuestionExamtype(int questionId, int examtypeId) {
		String hql = "select qe from QuestionExamtype qe left join fetch qe.question q left join fetch qe.examtype e where q.id=? and e.id=?";
		return (QuestionExamtype) this.getSession().createQuery(hql)
				.setParameter(0, questionId).setParameter(1, examtypeId)
				.uniqueResult();
	}

	@Override
	public List<Question> listExamtypeQuestions(int eid) {
		String hql = "select qe.question from QuestionExamtype qe where qe.examtype.id=?";
		return this.list(hql, eid);
	}

}
