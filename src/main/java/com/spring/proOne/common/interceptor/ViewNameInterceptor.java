package com.spring.proOne.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.proOne.member.vo.MemberVO;

public class ViewNameInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HttpSession session = request.getSession(true);
		
		try {
			String viewName = getViewName(request);
			MemberVO membervo = (MemberVO) session.getAttribute("member");
			System.out.println("viewName : "+viewName);
			if(viewName.matches(".*mypage.*")||viewName.matches(".*applyForm.*")||viewName.matches(".*admin.*")) {
				if(membervo.getId().isEmpty()) {
					viewName = "forward:/member/loginForm.do";
				}else if(viewName.matches(".*admin.*")&&!membervo.getId().equals("admin")) {
					viewName = "forward:/main/main.do";
				}
			}
			request.setAttribute("viewName", viewName);
			
		} catch (Exception e) {
			String viewName = "forward:/member/loginForm.do";
			request.setAttribute("viewName", viewName);
			return true;
		}
		return true;
//		try {
//			String viewName = getViewName(request);
//			request.setAttribute("viewName", viewName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
		}
		return fileName;
	}
}
