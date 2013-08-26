package com.guide.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guide.dao.GuideBean;
import com.guide.dao.GuideDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

public class GuideEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		GuideDAO gd = new GuideDAO();
		GuideBean bean = new GuideBean();
		
	    ActionForward forward=new ActionForward();
	    
	    response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
		
	    int guide_num = Integer.parseInt(request.getParameter("num")); //글번호
	    int page=Integer.parseInt(request.getParameter("page"));  //쪽번호 저장
	    
	    String guide_s_date = request.getParameter("guide_s_date").trim();
		String guide_e_date = request.getParameter("guide_e_date").trim();
		int guide_state = Integer.parseInt(request.getParameter("guide_state"));
		int guide_post = Integer.parseInt(request.getParameter("guide_post"));
		String guide_product = request.getParameter("guide_product").trim();
		String guide_q1 = request.getParameter("guide_q1").trim();
		String guide_q1_a1 = request.getParameter("guide_q1_a1").trim();
		String guide_q1_a2 = request.getParameter("guide_q1_a2").trim();
		String guide_q1_a3 = request.getParameter("guide_q1_a3").trim();
		String guide_q2 = request.getParameter("guide_q2").trim();
		String guide_q2_a1 = request.getParameter("guide_q2_a1").trim();
		String guide_q2_a2 = request.getParameter("guide_q2_a2").trim();
		String guide_q2_a3 = request.getParameter("guide_q2_a3").trim();
		String guide_q3 = request.getParameter("guide_q3").trim();
		String guide_q3_a1 = request.getParameter("guide_q3_a1").trim();
		String guide_q3_a2 = request.getParameter("guide_q3_a2").trim();
		String guide_q3_a3 = request.getParameter("guide_q3_a3").trim();
		String guide_q4 = request.getParameter("guide_q4").trim();
		String guide_q4_a1 = request.getParameter("guide_q4_a1").trim();
		String guide_q4_a2 = request.getParameter("guide_q4_a2").trim();
		String guide_q4_a3 = request.getParameter("guide_q4_a3").trim();
		
		// 수정 내용 빈에 담기
		bean.setGuide_s_date(guide_s_date);
		bean.setGuide_e_date(guide_e_date);
		bean.setGuide_state(guide_state);
		bean.setGuide_post(guide_post);
		
		bean.setGuide_product(guide_product);
		bean.setGuide_q1(guide_q1);
		bean.setGuide_q1_a1(guide_q1_a1);
		bean.setGuide_q1_a2(guide_q1_a2);
		bean.setGuide_q1_a3(guide_q1_a3);
		bean.setGuide_q2(guide_q2);
		bean.setGuide_q2_a1(guide_q2_a1);
		bean.setGuide_q2_a2(guide_q2_a2);
		bean.setGuide_q2_a3(guide_q2_a3);
		bean.setGuide_q3(guide_q3);
		bean.setGuide_q3_a1(guide_q3_a1);
		bean.setGuide_q3_a2(guide_q3_a2);
		bean.setGuide_q3_a3(guide_q3_a3);
		bean.setGuide_q4(guide_q4);
		bean.setGuide_q4_a1(guide_q4_a1);
		bean.setGuide_q4_a2(guide_q4_a2);
		bean.setGuide_q4_a3(guide_q4_a3);
		
		bean.setGuide_num(guide_num);
	
		gd.guideEdit(bean);//수정메서드 호출
          
		response.sendRedirect("admin_guide_cont.do?num="+guide_num+"&page="+page+"&state=cont");
        //내용보기로 이동(글번호, 페이지번호, 내용)
		
		return null;
	}

}
