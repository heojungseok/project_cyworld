
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%

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
<title>cy_mini</title>
</head>
<style>
#wrap {
	margin: auto;
	width: 1200px;
	height: 600px;
	background-color: #f3f3f3;
}

header {
	height: 75px;
}

section {
	margin: auto;
	width: 1050px;
	height: 500px;
}
</style>
<body>
<%-- <%=request.getContextPath() %>
<%=request.getServletContext().getRealPath("edit_imgs") %>
<%=request.getServletPath() %> --%>
	<div class="d-flex justify-content-around">
		<!-- 회원과 비회원 구분 -->
		<c:choose>
			<c:when test="${username != null}">
			<div>
				<label>현재 접속중 : <a href="cy_mini?idx=${MyHidx }"><button class="btn btn-outline-dark">${username }</button></a></label>
				<label><a href="cy_logoutChk.jsp"><button class="btn btn-outline-dark"> 로그아웃</button></a></label>
			</div>	
			</c:when>
			<c:otherwise>
			<div>
				<label>현재 접속중 : guest</label>
				<a href="cy_member.jsp"><button class="btn btn-outline-dark">회원 가입</button></a>
				<a href="cy_main.jsp"><button class="btn btn-outline-dark">홈</button></a>
			</div>
			</c:otherwise>
		</c:choose>
	
		<div style="width: 330px;" class="d-flex justify-content-around">
			<a href="cy_find_friend.jsp"><button class="btn btn-light"
					type="button">친구 찾기</button></a>
			<c:if test="${miniData.username != username }">
	
				<c:choose>
					<c:when test="${isfollow != true }">
						<form action="cy_follow_user" method="post">
							<input type="hidden" name="h_idx" value="${miniData.idx }">
							<input type="hidden" name="h_username"
								value="${miniData.username }">
							<button class="btn btn-light" type="submit">일촌 맺기</button>
						</form>
					</c:when>
					<c:otherwise>
					<form action="cy_delete" method="get">
					<input type="hidden" name="m_idx" value="${miniData.idx2 }">
						<button class="btn btn-light" type="submit">일촌
								끊기</button>
					</form>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</div>
	<div class="border border-5 rounded border-light" id="wrap">
		<header
			class="d-flex align-items-center justify-content-between border border-2">
			<div>TOTAL :${miniData.today }</div>

			<div class="">${miniData.intro1 }</div>

			<div class="">
				<a href="cy_mini?idx=${miniData.idx }">
					cyworld_mini/${miniData.domain }</a>
			</div>
		</header>
		<div class="d-flex justify-content-between">
			<section class="mt-2 d-flex justify-content-between border border-2">
				<article id="" class="d-flex flex-column col-4">
					<div class="d-flex justify-content-center align-items-center"
						id="profile_img" style="height: 300px;">
						<c:choose>
						<c:when test="${miniData.imgs == null }">
						<img class="border border-1 rounded" alt="기본 이미지"
							src="./img/basic_profile.jpg"
							style="width: 200px; height: 230px;">
							
							</c:when>
							<c:otherwise>
							<img class="border border-1 rounded" alt="${miniData.idx } 의 이미지"
							<%-- src="./edit_imgs/${miniData.imgs }" --%>
							src="<%=request.getContextPath() %>/edit_imgs/${miniData.imgs }"
							style="width: 200px; height: 230px;">
							</c:otherwise>
						</c:choose>	
					</div>
					<div class="" id="profile_comment" style="height: 170px;">${miniData.intro2 }</div>
						<c:if test="${miniData.idx == MyHidx }">	
					<a href="cy_edit_minihp.jsp"><button class="btn btn-light" type="button" value=""
							style="width: 120px;">프로필 수정</button></a>
						</c:if>
				</article>
				<article id="mini_room" class="d-flex flex-column col-8">
					<div class="d-flex flex-column " id="room_section" style="height:;">
						<div class="fw-bolder font-monospace text-muted">mini Room</div>
						<img class="border border-1 rounded" alt="기본 룸 이미지"
							src="./img/create_room.jpg" style="width: 500px; height: 250px;">

					</div>
					<div id="recent_content" style="height: 270px; font-family:;">
						<span class="fw-bolder font-monospace text-muted"> 최근 게시물 </span>
					</div>

				</article>

			</section>
			<div class="d-flex flex-column justify-content-around ">
				<nav id="menu-bar">
					<ul class="nav flex-column nav-pills nav-fill">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="cy_mini?idx=${miniData.idx }">홈</a></li>
						<li class="nav-item"><a class="nav-link " href="cy_diary.jsp">다이어리</a></li>
						<li class="nav-item"><a class="nav-link "
							href="cy_visitbook?idx=${miniData.idx }">방명록</a></li>
					</ul>
				</nav>
				<nav>
					<div class="dropdown">
						<button class="btn btn-outline-primary btn-sm dropdown-toggle"
							type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
							aria-expanded="false">파도 타기</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
							<c:forEach var="i" items="${following }">
								<c:choose>
									<c:when test="${i.username_2  != null }">
										<li><a class="dropdown-item" href="cy_mini?idx=${i.h_idx }">${i.username_2 }</a></li>
									</c:when>
									<c:otherwise>
										<li>등록한 일촌이 없습니다 ㅠㅠ</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

						</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>