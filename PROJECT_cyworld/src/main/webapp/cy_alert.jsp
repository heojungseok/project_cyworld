<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>alert</title>

</head>
<body>

<script type="text/javascript">
alert("회원 가입 후 이용 가능한 서비스 입니다 :)")
location.href= "cy_member.jsp";
</script>
<%-- <%}else if(bidx != -1) {%>
<script type="text/javascript">
alert("삭제 완료")
location.href= "detail?bidx=<%=bidx%>";
</script>
<%} %> --%>
</body>
</html>