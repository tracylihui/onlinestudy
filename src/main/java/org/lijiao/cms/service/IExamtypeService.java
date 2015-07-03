package org.lijiao.cms.service;

import java.util.List;

import org.lijiao.basic.model.Pager;
import org.lijiao.cms.model.Examtype;

public interface IExamtypeService {
	public void add(Examtype examtype);
	public void delete(int id);
	public Examtype load(int id);
	public void update(Examtype examtype);
	public List<Examtype> listExamtype();
	public Pager<Examtype> findExamtype();
	public void deleteExamtypeQuestions(int eid);
}
