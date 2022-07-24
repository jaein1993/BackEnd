package org.zerock.myapp.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/miniCalculator")
public class MiniCalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf8");
		request.setCharacterEncoding("utf-8");
		
		String x_ = request.getParameter("x"); //add.html에 입력된 x의 값 그대로 가져오기
		String y_ = request.getParameter("y"); //add.html에 입력된 y의 값 그대로 가져오기
		String plus = request.getParameter("plus");//add.html에 입력된 plus값 그대로 가져오기
		String minus =request.getParameter("minus");
		String multi =request.getParameter("multi");
		String division =request.getParameter("division");
		
		
		
		int x=0; //값 초기화
		int y=0;
		
		if(!x_.equals("")) { x=Integer.parseInt(x_);} //빈 문자열이 아닐경우에만 정수로 변환
		if(!y_.equals("")) { y=Integer.parseInt(y_);} //빈 문자열이 아닐경우에만 정수로 변환
		
	
		
		int result = 0;
		if (!(plus==null)) {
			result=x+y; //덧셈
			response.getWriter().printf("x+y는 %d 입니다.", result);
			
		} else if (!(minus==null)) {
			result=x-y; //뺄셈
			response.getWriter().printf("x-y는  %d 입니다.", result);
			
		} else if (!(multi==null)) {
			result=x*y; //곱셈
			response.getWriter().printf("x*y는  %d 입니다.", result);
			
		} else if (!(division==null)) {
			result=x/y; //나눗셈
			response.getWriter().printf("x/y는  %d 입니다.", result);
		}
			
		
	}
		
		

}
