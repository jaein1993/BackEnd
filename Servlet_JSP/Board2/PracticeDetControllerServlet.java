package org.zerock.myapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.entity.Notice;


@WebServlet("/practice/det")
public class PracticeDetControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PracticeDetControllerServlet() {
        super();
       
    }

	
	protected void service
	(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

int id=Integer.parseInt(request.getParameter("id"));

String url = "클라우드 전자지갑";
String user="ADMIN";
String pwd="비밀번호";
String sql = "SELECT * FROM NOTICE WHERE ID=?";



try {
	Class.forName("oracle.jdbc.OracleDriver");
	Connection conn = DriverManager.getConnection(url,user,pwd);
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setInt(1, id);
	ResultSet rs = stmt.executeQuery();
	rs.next();

	String title=rs.getString("TITLE");
	String writerId=rs.getString("WRITER_ID");
	String content=rs.getString("CONTENT");
	Date regdate=rs.getDate("REGDATE");
	String hit=rs.getString("HIT");
	String files=rs.getString("FILES");
	
	Notice notice= new Notice(
			id,title,writerId,content,regdate,hit,files);
			
	
	request.setAttribute("n", notice);
	
//	request.setAttribute("title", title);
//	request.setAttribute("regdate", regdate);
//	request.setAttribute("writerId", writerId);
//	request.setAttribute("hit", hit);

		rs.close();
		stmt.close();
		conn.close();
} catch (ClassNotFoundException e) {
	
	e.printStackTrace();
} catch (SQLException e) {
	
	e.printStackTrace();
}



request.getRequestDispatcher("/practice/det.jsp")
.forward(request, response);


		
	}

}
