package com.guide.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guide.dao.GuideBean;
import com.guide.dao.GuideDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class GuideWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String guide_s_date = request.getParameter("guide_s_date").trim();
		String guide_e_date = request.getParameter("guide_e_date").trim();
		int guide_post = Integer.parseInt(request.getParameter("guide_post"));
		int guide_state = Integer.parseInt(request.getParameter("guide_state"));
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
		
		GuideBean bean = new GuideBean();

		bean.setGuide_s_date(guide_s_date);
		bean.setGuide_e_date(guide_e_date);
		bean.setGuide_post(guide_post);
		bean.setGuide_state(guide_state);
		
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
		
		GuideDAO dao = new GuideDAO();
		
		//2) 참조변수를 이용하여 저장 메소드를 호출한다. 
		int re = dao.insertGuide(bean);
		
		if(re == 1){ //insert가 성공한 경우
			response.sendRedirect("./admin_guide_list.do");
		}else{ //insert가 실패한 경우
			out.println("<script>");
			out.println("alert('게시물 저장에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}	
		return null;
	}
}
