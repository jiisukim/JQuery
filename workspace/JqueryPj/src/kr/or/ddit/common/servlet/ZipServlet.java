package kr.or.ddit.common.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.service.ZipService;
import kr.or.ddit.common.vo.ZipVO;

@WebServlet("/ZipServlet")
public class ZipServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		
		retrieveZipList(request);
		url = "common/zipListResult.jsp";
		
		RequestDispatcher disp = request.getRequestDispatcher(url); // 결과를 받을 URL을 세팅
		disp.forward(request, response);
		
	}
	
	private void retrieveZipList(HttpServletRequest request) {
		String dong = request.getParameter("dong");
		
		ZipService service = new ZipService();
		List<ZipVO> list = service.retrieveZipList(dong);
		
		// 브라우저에 전달할 내용(결과)를 request의 Attribute에 담아주기
		// 브라우저에서는 request의 Attribute에 꺼내서 사용할 수 있음 
		request.setAttribute("list", list);
	}
	
	
}
