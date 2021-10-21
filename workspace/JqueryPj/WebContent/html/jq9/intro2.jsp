<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="../../js/lib/jquery-3.6.0.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			
		})	
	
	</script>
</head>
<%
request.setCharacterEncoding("UTF-8");

String userName = (String)request.getAttribute("name");
String userName1 = (String)request.getParameter("name");
System.out.println(userName);
System.out.println(userName1);

/*********getParameter()와 getAttribute() 비교 ***********/
// getParameter() - set 메소드가 없음
// getAttribute() - set 메소드가 있음  // setAttribute() 사용해야 get할 수 있음
%>
<body>
	<p>로그인 : <span id="userName"><%=userName1 %></span></p>
	<h1>Welcome to the Web Programming!</h1>
	<img src = "../../images/coffee1.jfif">
	<br>
	<p>
		안녕하세요. 열심히 잘 해봅시다~
		<em>환영합니다!!</em>
	</p>
	<h2>내용</h2>
	<p>HTML5,CSS3,Javascript,jQuery,SQL,JSP,....</p>
	<h2>기간</h2>
	<p>2021.03.10~2021.10.21</p>
	<h2>장소</h2>
	<p>대덕인재개발원</p>
</body>
</html>