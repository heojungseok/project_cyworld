<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%
/* HttpSession cySession = request.getSession();
String username = String.valueOf(request.getAttribute("username"));
int idx = Integer.parseInt(String.valueOf(request.getAttribute("idx"))); */

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>cy_create</title>
</head>
<style>
body {
	width: 100%;
}

#wrap {
	margin: auto;
	width: 1000px;
	height: 700px;
}

header {
	height: 75px;
}

section {
	margin: auto;
	width: 850px;
	height: 500px;
}

#intro1 {
	width: 350px;
	text-align: center;
}

#intro2 {
	width: 250px;
	height: 150px;
}

#mini_room {
	margin: auto;
}
</style>
<body>
	<div class="border border-2 rounded" id="wrap">
		<span class="display-3" style="font-family: monospace;">WELCOME
			TO CYWORLD ! <c:out value="${username }"/></span>
			<form action="cy_create_minihp" method="post">
			<input type="hidden" name="m_idx" value="${idx }">
			<input type="hidden" name="username" value="${username }">
		<header
			class="d-flex align-items-center justify-content-between ">
				<div>	
					<span style="font-family: monospace; font-weight: bolder;">CYWORLD</span>
				</div>
				<div class="">
					<input id="intro1" type="text" name="intro1"
						value="홈페이지 소개 글을 입력해주세요.">
				</div>
				<div class="">
					<a href="">cyworld_mini/</a><input type="text" name="domain"
						value="도메인을 설정해주세요.">
				</div>
		</header>
		<div class="d-flex justify-content-between">
			<section class="mt-2 d-flex justify-content-between ">
				<article id="" class="d-flex flex-column col-4">
					<div class="d-flex justify-content-center align-items-center"
						id="profile_img" style="height: 300px;">
						<img class="border border-1 rounded" alt="기본 이미지"
							src="./img/basic_pro.jpg" style="width: 200px; height: 250px;">
					</div>
					<div class="" id="profile_comment" style="height: 170px;">
						<textarea class="ms-2" id="intro2" rows="" cols="" name="intro2">자기 소개 글을 입력해주세요. </textarea>
					</div>
				</article>
				<article id="mini_room" class="d-flex flex-column col-8">
					<div id="room_section" style="height:;">
						<img class="border border-1 rounded" alt="기본 룸 이미지"
							src="./img/create_room.jpg" style="width: 500px; height: 350px;">

					</div>

				</article>

			</section>
			<nav id="menu-bar">
				<ul class="nav flex-column nav-pills nav-fill">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">홈</a></li>
					<li class="nav-item"><a class="nav-link " href="#">다이어리</a></li>
					<li class="nav-item"><a class="nav-link "
						href="cy_visitor.jsp">방명록</a></li>
				</ul>
			</nav>
		</div>
		<button class="btn btn-light" type="submit" value=""
				style="width: 155px;">미니 홈피 만들기</button>
				 <span class="fs-4 fw-bolder font-monospace text-muted">* 로그인 페이지로 이동됩니다. </span>
	</div>
	</form>
</body>
</html>