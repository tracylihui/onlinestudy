package org.lijiao.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户组对象，存储用户和组的关联
 * @author Administrator
 *
 */
@Entity
@Table(name="t_question_examtype")
public class QuestionExamtype {
	private int id;
	private Question question;
	private Examtype examtype;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@ManyToOne
	@JoinColumn(name="q_id")
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	@ManyToOne
	@JoinColumn(name="e_id")
	public Examtype getExamtype() {
		return examtype;
	}
	public void setExamtype(Examtype examtype) {
		this.examtype = examtype;
	}
}
