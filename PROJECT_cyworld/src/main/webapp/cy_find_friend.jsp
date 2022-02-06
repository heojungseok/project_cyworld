<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
HttpSession cySession = request.getSession();
String username = String.valueOf(cySession.getAttribute("username"));
String curIdx = String.valueOf(cySession.getAttribute("idx"));

if(username.equals("null") || username == null || curIdx == null || curIdx.equals("null")){
	
	username = "Guest";
	curIdx = "-1";
	cySession.setAttribute("idx", curIdx);
	
}else{
	int m_idx = Integer.parseInt(curIdx);
	username = username;
	cySession.setAttribute("idx", m_idx);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<title>cy_mini</title>
</head>
<style>
#wrap {
	margin: auto;
	width: 1000px;
	height: 600px;
	border: solid 4px;
	border-color : silver;
	background-color: #f3f3f3;
	 
}
</style>
<body>
	<div id="wrap">
		<span class="fs-2 fw-bolder font-monospace text-muted"><%=username%>
			님 친구를 찾아보세요 !</span>
		<hr>
		<section class="">
			<div class="d-grid gap-3">
				<form action="cy_find_friend" method="post">
					<div class="p-2 bg-light border">
						친구의 이름을 입력해주세요 &nbsp;<input type="text" name="username">
						<button type="submit" class="btn btn-light">검색</button>
					</div>
				</form>
				<div class="p-2 bg-light border ">
					<table class="border border-3 ">
						<thead class="">
							<th style="text-align: center">이름</th>
							<th style="text-align: center">성별</th>
							<th style="text-align: center">아이디</th>
							<th style="text-align: center">mail</th>
							<th style="text-align: center">domain</th>
						</thead>
						<tbody>
							<!--foreach  -->
						<c:forEach var="f" items="${friend }">
							<tr>
							<!-- <form action="cy_mini"> -->
								<td style="text-align: center">${f.username }</td>
							<!-- </form> -->
								<td style="text-align: center">${f.gender }</td>
								<td style="text-align: center">${f.userID }</td>
								<td style="text-align: center">${f.email }</td>
								<td style="text-align: center">${f.domain }</td>
								<td><a href="cy_mini?idx=${f.idx }"><button class="btn btn-light" type="button">Go </button></a></td>
							</tr>
						</c:forEach>
						</tbody>

					</table>
				</div>

				<div class="p-2 bg-light border">empty</div>
			</div>
		</section>
	</div>
</body>
</html>