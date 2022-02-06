
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%

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
<title>cy_visitor</title>
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

#recent_board {
	border: 2px solid white;
	border-radius: 5px;
	overflow: auto;
	overflow-x: hidden;
}
</style>
<body>
	<div class="d-flex justify-content-around">
		<c:choose>
			<c:when test="${m_idx != -1}">
				<div>
					<label>현재 접속중 : <a href="cy_mini?idx=${MyHidx }"><button
								class="btn btn-outline-dark">${username }</button></a></label> <label><a
						href="#"><button class="btn btn-outline-dark">로그아웃</button></a></label>
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<label>현재 접속중 : guest</label> <a href="cy_member.jsp"><button
							class="btn btn-outline-dark">회원 가입</button></a> <a href="cy_main.jsp"><button
							class="btn btn-outline-dark">홈</button></a>
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
						<a href="#"><button class="btn btn-light" type="button">일촌
								끊기</button></a>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</div>
	<div class="border border-5 rounded border-light" id="wrap">
		<header
			class="d-flex align-items-center justify-content-between border border-1">
			<div>TOTAL :${miniData.today }</div>
			<div class="">${miniData.intro1 }</div>
			<div class="">
				<a href="cy_mini?idx=${miniData.idx }">cyworld_mini/${miniData.domain }</a>
			</div>
		</header>
		<div class="d-flex justify-content-between">
			<section class="mt-2 d-flex justify-content-between border border-1">
				<article id="" class="d-flex flex-column col-4">
					<div class="d-flex justify-content-center align-items-center"
						id="profile_img" style="height: 300px;">
						<img class="border border-1 rounded" alt="기본 이미지"
							src="./img/basic_profile.jpg"
							style="width: 200px; height: 250px;">
					</div>
					<div class="" id="profile_comment" style="height: 170px;">${miniData.intro2 }</div>
					<a href=""><button class="btn btn-light" type="button" value=""
							style="width: 120px;">프로필 수정</button></a>
				</article>
				<article id=""
					class="d-flex flex-column justify-content-around col-8">
					<div class="bg-light d-flex flex-column " id=""
						style="height: 230px;">
						<%--  ${hidxNmidx } 앞 홈피 idx, 뒤 유저idx --%>
						<c:choose>
							<c:when test="${m_idx != -1 }">
								<form action="cy_visitbook" method="post">
									<input type="hidden" name="hidxNmidx" value="${hidxNmidx }">
									<div
										class="d-flex justify-content-start align-items-center fs-4 fw-bolder font-monospace text-muted">
										write here !</div>
									<div class="d-flex justify-content-start ">
										<textarea rows="5" cols="70" name="visitcontent"></textarea>
									</div>
									<div>
										<input type="checkbox" name="status" value="1">비밀글 <input
											type="submit" name="boardWrite" value="작성">
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<div style="height: 200px;"
									class="d-flex justify-content-center align-items-center fs-2 fw-bolder font-monospace text-muted">
									회원 가입 후 작성할 수 있습니다! :)</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="bg-light" id="recent_board" style="height: 270px;">
						<div
							class="d-flex justify-content-start align-items-center fs-4 fw-bolder font-monospace text-muted">
							visitor's book</div>
						<hr>
						<table class="border border-1">
							<thead>
								<th></th>
								<th></th>
								<th></th>
							</thead>
							<tbody>
								<c:forEach var="v" items="${visitLog }">
									<tr>
										<!-- 비밀글이 홈피 주인과 비밀글 작성자에게만 보여지고 일반글은 누구나 보여지기  -->
										<c:if
											test="${v.owner == true or username eq miniData.username or v.status == 0}">
											<td><a href="cy_mini?idx=${v.idx3 }"><button
														type="button" class="btn btn-outline-warning">${v.username }</button></a></td>
											<td>${v.visitContent }</td>
											<td><fmt:formatDate value="${v.wdate }" type="both" /></td>
											<c:if test="${v.status == 1 }">
												<td>
													<button class="btn btn-sm">비밀글 확인!</button>
												</td>
											</c:if>
										</c:if>
										<!-- 홈피 주인과 작성자만 삭제 가능하게  -->
										<c:if
											test="${v.owner == true or username eq miniData.username }">
											<form action="cy_delete" method="get">
											<input type="hidden" name="hidxNmidxNvidx" value="${hidxNmidx },${v.idx2 }">
											<td><button type="submit"
														class="btn btn-sm btn-outline-primary">삭제</button></td>
											</form>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</article>
			</section>
			<div class="d-flex flex-column justify-content-around ">
				<nav id="menu-bar">
					<ul class="nav flex-column nav-pills nav-fill">
						<li class="nav-item"><a class="nav-link " aria-current="page"
							href="cy_mini?idx=${miniData.idx }">홈</a></li>
						<li class="nav-item"><a class="nav-link " href="#">다이어리</a></li>
						<li class="nav-item"><a class="nav-link active"
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
									<c:when test="${i.username_2 != null }">
										<li><a class="dropdown-item"
											href="cy_mini?idx=${i.h_idx }">${i.username_2 }</a></li>
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
	<script type="text/javascript">
		/* $(document).ready(function() {
			
			
			$("#loginBtn").on('click', function() {
				window.open('test');
			});

		}); */
	</script>
</body>
</html>