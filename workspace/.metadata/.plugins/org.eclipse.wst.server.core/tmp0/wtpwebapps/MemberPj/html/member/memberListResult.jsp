<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
<%
List<MemberVO> list = (List<MemberVO>) request.getAttribute("list");
for(int i=0 ; i<list.size() ; i++){
	if(i>0) {
		%>,<%
	}
	
	MemberVO vo = list.get(i);
	String memId = vo.getMemId();
	String memName = vo.getMemName();
	String hp = vo.getMemHp();
	String birth = vo.getMemBir();
	%>
	{
		"id" : "<%=memId %>"
		,"name" : "<%=memName %>"
		,"hp" : "<%=hp %>"
		,"birth" : "<%=birth %>"
	}
	<%
}
%>
]
