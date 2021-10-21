<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MemberVO vo = (MemberVO) request.getAttribute("vo");

if(vo == null) { // 중복된 ID가 없음
	%>
	{ "resultCnt" : 0 }
	<%	
} else { // 중복된 ID가 있음
	%>
	{ "resultCnt" : 1 }
	<%	
}
%>
