package com.doran.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TimerFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
		chain.doFilter(req, res);
		System.out.println("process time : " + (System.currentTimeMillis() - startTime));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
