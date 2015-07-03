package org.lijiao.cms.dto;

import java.util.Arrays;

public class aaa {
	public static void main(String[] args) {
		String answer = "答案1/答案2/答案3/答案4";
		String[] aa = answer.split("/");
		for(String a : aa){
			System.out.println(a);
		}
	}
}
