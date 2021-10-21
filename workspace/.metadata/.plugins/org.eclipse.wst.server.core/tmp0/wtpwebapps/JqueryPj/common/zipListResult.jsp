<%@page import="kr.or.ddit.common.vo.ZipVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
<%
List<ZipVO> list = (List<ZipVO>) request.getAttribute("list");
for(int i=0 ; i<list.size() ; i++){
   if(i>0) {
      %>,<%
   }
   
   ZipVO vo = list.get(i);
   String bunji = vo.getBunji();
   String dong = vo.getDong();
   String gugun = vo.getGugun();
   String sido = vo.getSido();
   String zipcode = vo.getZipcode();
   %>
   {
      "bunji" : "<%=bunji %>"
      ,"dong" : "<%=dong %>"
      ,"gugun" : "<%=gugun %>"
      ,"sido" : "<%=sido %>"
      ,"zipcode" : "<%=zipcode %>"
   }
   <%
}
%>
]