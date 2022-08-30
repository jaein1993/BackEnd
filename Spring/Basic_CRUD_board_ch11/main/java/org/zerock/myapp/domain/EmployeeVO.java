package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;

@Value
public class EmployeeVO {
	
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber; 
	private Date hireDate;
	private String jobId;
	private Double salary;
	private Double commissionPct;
	private Integer managerId;
	private Integer departmentId;

}
