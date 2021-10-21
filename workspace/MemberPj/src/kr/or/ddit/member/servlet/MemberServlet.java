package kr.or.ddit.member.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		
		String flag = request.getParameter("flag");
//		flag.equals("CHKID") ==> flag가 null인 경우 에러 발생
		if("CHKID".equals(flag)) {
			// 아이디 중복검사 
			checkUserId(request);
			url = "html/member/memberResult.jsp";
			
		} else if("C".equals(flag)) {
			// 회원정보 등록 
		} else if("U".equals(flag)) {
			// 회원정보 변경 
		} else if("D".equals(flag)) {
			// 회원정보 삭제 
		} else if("R".equals(flag)) {
			// 회원정보 상세 조회 
			
		} else {
			retrieveMemberList(request);
			url = "html/member/memberListResult.jsp";
		}
		
		RequestDispatcher disp = request.getRequestDispatcher(url); // 결과를 받을 URL을 세팅
		disp.forward(request, response);
		
	}
	
	private void checkUserId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String memId = request.getParameter("userId");
		
				
		MemberService service = new MemberService();
		MemberVO memberVO = null;
		try {
			memberVO = service.retrieveMemberByMemId(memId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("vo", memberVO);
	}
	
	private void retrieveMemberList(HttpServletRequest request) {
		// 회원목록조회
		String memId = request.getParameter("memId");
		String memName = request.getParameter("memName");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(memId);
		memberVO.setMemName(memName);
		
		MemberService service = new MemberService();
		List<MemberVO> list = service.retrieveMemberList(memberVO);
		
		// 브라우저에 전달할 내용(결과)를 request의 Attribute에 담아주기
		// 브라우저에서는 request의 Attribute에 꺼내서 사용할 수 있음 
		request.setAttribute("list", list);
	}
	
	
}
