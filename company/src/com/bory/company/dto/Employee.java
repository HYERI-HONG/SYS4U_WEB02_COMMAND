package com.bory.company.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

public class Employee implements Serializable {

	private static final long serialVersionUID = -4312910826157323298L;

	private String ename, job, dname;
	private int empno, mgr, sal, comm, deptno;
	private Date hiredate;

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public void create(HttpServletRequest request) { 
		
		this.comm = Integer.parseInt(request.getParameter("comm")); 	
		this.deptno = Integer.parseInt(request.getParameter("deptno"));
		this.empno = Integer.parseInt(request.getParameter("empno"));
		this.ename = request.getParameter("ename");
		this.hiredate = java.sql.Date.valueOf(request.getParameter("hiredate"));
		this.job = request.getParameter("job");
		this.mgr = Integer.parseInt(request.getParameter("mgr"));
		this.sal = Integer.parseInt(request.getParameter("sal"));

	}
}
