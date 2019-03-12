package com.bory.company.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Carrier {

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public Carrier(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

	}

	public void carryTo(String option, String viewName) {

		try {
			if (option.equals("forward")) {
				request.getRequestDispatcher(viewName).forward(request, response);
			}else if(option.equals("redirect")){
				response.sendRedirect(viewName);
			}else {
				
			}
		} catch (ServletException e) {

		} catch (IOException e) {

		}
	}
}
