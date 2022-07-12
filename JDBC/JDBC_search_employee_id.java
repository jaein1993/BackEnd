package org.zerock.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JDBC_search_employee_id { //#Employee에서 salary가 7000 이상 / employee_id가 50번 이상인 사원을 구하라
	
	//JDBC Driver에 필요한 필수연결정보
	private static String jdbcUrl="jdbc:oracle:thin:@db20220510180837_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP";
	private static String user="HR";
	private static String pass="Oracle12345678";
	
	public static void main(String[] args) {
		try {
			//JDBC Driver Class를 등록
			@Cleanup Connection con= DriverManager.getConnection(jdbcUrl, user, pass);
			
			String SQL="SELECT * FROM employees WHERE salary>=? AND department_id>=?";
			//SQL문장을 전송
			@Cleanup PreparedStatement pstmt= con.prepareStatement(SQL);
			
			
			pstmt.setDouble(1, 7000); //salary>=7000
			pstmt.setInt(2, 50);	//department_id>=50
			
			@Cleanup ResultSet rs= pstmt.executeQuery();
			
			
			//ResultSet을 이용해서, 각 레코드별로, 각 컬럼의 값 추출
			while(rs.next()) { 
				Integer EMPLOYEE_ID 	= rs.getInt("EMPLOYEE_ID");
				String 	FIRST_NAME 		= rs.getString("FIRST_NAME");
				String 	LAST_NAME 		= rs.getString("LAST_NAME");
				String 	EMAIL 			= rs.getString("EMAIL");
				String 	PHONE_NUMBER 	= rs.getString("PHONE_NUMBER");
				Date 	HIRE_DATE 		= rs.getDate("HIRE_DATE");
				String 	JOB_ID 			= rs.getString("JOB_ID");
				Double 	SALARY 			= rs.getDouble("SALARY");
				Double 	COMMISSION_PCT 	= rs.getDouble("COMMISSION_PCT");
				Integer MANAGER_ID 		= rs.getInt("MANAGER_ID");
				Integer DEPARTMENT_ID 	= rs.getInt("DEPARTMENT_ID");
				
				String employee = 
					String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
								EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER,
								HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID,
								DEPARTMENT_ID);
				
				log.info("Employee: {}", employee);
			} // while
								
	} catch(SQLException e){
		e.printStackTrace();
	}
} // end class
}