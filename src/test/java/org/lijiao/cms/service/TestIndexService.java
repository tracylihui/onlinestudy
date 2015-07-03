package org.lijiao.cms.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lijiao.cms.model.Student;
import org.lijiao.cms.service.IStudentService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestIndexService {
	@Inject
	private IStudentService studentService;
	@Inject
	private IChannelService channelService;
	@Test
	public void testLoadByusername() {
		Student student = studentService.loadByUsername("student1");
		if(student == null){
			System.out.println("111");
		}else{
			System.out.println(student);
		}
	}
	@Test
	public void testAdd(){
		System.out.println(channelService.listAllIndexChannel());
	}
}
