<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page session="false"  %>
<%

//뒤로 가기 방지
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", 0L); 
HttpSession cySession = request.getSession();
//1: 기존의 세션 데이터를 모두 삭제
cySession.invalidate();
//2: 로그인 페이지로 이동시킴.
response.sendRedirect("cy_main.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cy_logoutChk</title>
</head>
<body>
</body>
</html>