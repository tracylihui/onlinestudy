package org.lijiao.cms.dto;

import java.util.ArrayList;
import java.util.List;

import org.lijiao.cms.model.Question;

public class QuestionDto {
	private Question question;
	private String yourOption;
	private List<String> answers = new ArrayList<String>();
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	public String getYourOption() {
		return yourOption;
	}
	public void setYourOption(String yourOption) {
		this.yourOption = yourOption;
	}
	
}
