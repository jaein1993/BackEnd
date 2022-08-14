package org.zerock.myapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.entity.Notice;

// 이 파일을 실행하면 URI가 /practice/list 로 실행됨을 명시
@WebServlet("/practice/list")
public class PracticeListControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public PracticeListControllerServlet() {
        super();
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DB에서 불러온 NOTICE 테이블 저장할 배열 
		List<Notice> list= new ArrayList<>();
		
		// DB와 연결할 떄 필요한 정보들
		String url = "클라우드 전자지갑";
		String user="ADMIN";
		String pwd="비밀번호";
		String sql = "SELECT * FROM NOTICE";	// NOTICE 테이블의 모든 정보를 불러옴

		try {
			// DB에서 값을 가져올때 필요한 객체들을 생성해준다
			// Connection 객체로 DB의 로그인 정보를 넣어서 로그인해주고
			// Statement객체를 만든다 (ResultSet 객체를 만들기 위해서) 
			// ResultSet 객체를 만들어 SQL문으로 테이블에서 필요한 값들을 불러온다
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(url,user,pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 테이블에 더 이상 데이터가 없을때까지 반복분 수행
			while(rs.next()){
				// 각 변수에 테이블의 컬럼값을 하나씩 매칭해서 저장한다
				// ex) ID 컬럼의 값은 id 변수에 저장
				Integer id= rs.getInt("ID");
				String title=rs.getString("TITLE");
				String writerId=rs.getString("WRITER_ID");
				String content=rs.getString("CONTENT");
				Date regdate=rs.getDate("REGDATE");
				String hit=rs.getString("HIT");
				String files=rs.getString("FILES");
				
				// Notice 객체를 생성해서 각 컬럼의 값을 저장한다
				Notice notice= new Notice(
						id,title,writerId,content,regdate,hit,files);
				// Notice 객체를 list에 담는다 (list는 Notice객체의 모음이 됨)
				list.add(notice);
			} 
			
			// 자원을 닫아준다
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// 클래스를 못 찾는거에 대한 예외처리
			e.printStackTrace();
		} catch (SQLException e) {
			// SQL문에 오류가 있을때에 대한 예외처리
			e.printStackTrace();
		}
		
		// request 객체를 이용하여 list를 Attribute로 설정해준다
		request.setAttribute("list", list);
		// list를 모든 웹페이지가 공유가능한 Attrubute를 설정완료하면, list.jsp 페이지로 화면을 넘긴다
		request.getRequestDispatcher("/practice/list.jsp")
		.forward(request, response);

	}

}
