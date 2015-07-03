package org.lijiao.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户组对象，使用该对象来获取可以发布文章的栏目信息
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_question")
public class Question {
	private int id;
	private String name;
	private String score;
	private String answer;
	private String theoption;

	public Question() {
	}

	public Question(int id, String name, String score, String answer,
			String theoption) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
		this.answer = answer;
		this.theoption = theoption;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getTheoption() {
		return theoption;
	}

	public void setTheoption(String theoption) {
		this.theoption = theoption;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", name=" + name + ", score=" + score
				+ ", answer=" + answer + ", theoption=" + theoption + "]";
	}


}
