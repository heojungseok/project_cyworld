<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>cyworld_member</title>
</head>
<style>
body {
	width: 100%;
}

#wrap {
	margin: auto;
	width: 700px;
	height: 950px;
}

#mainImg {
	position: absolute;
	width: 900px;
	height: 530px;
	margin: -250px 0px 0px -450px;
	top: 50%;
	left: 50%;
}

#mainLogo {
	position: absolute;
	width: 200px;
	height: 150px;
	margin: 0px 0px 0px 0px;
	top: 0%;
	left: 30%;
}

#mainLogoLoginId {
	position: absolute;
	width: 400px;
	height: 25px;
	margin: 75px 0px 0px -195px;
	top: 50%;
	left: 50%;
}

#mainLogoLoginPw {
	position: absolute;
	width: 400px;
	height: 25px;
	margin: 105px 0px 0px -200px;
	top: 50%;
	left: 50%;
}

#mainLogoLoginBtn {
	position: absolute;
	width: 85px;
	height: 70px;
	margin: 68px 0px 0px 115px;
	top: 50%;
	left: 50%;
}

#fixStr {
	font-family: monospace;
	font-weight: bolder;
	text-align: right;
	width: 250px;
}
</style>
<body>
	<section class="" id="wrap">
		<div class="d-flex flex-column mb-3">
			<div class="d-flex justify-content-end">
			<img alt="main_logo" src="./img/main_logo.jpg" style="height: 150px;" />
			</div>
			<hr>
			<div class="">
			<form action="join_member" method="post">
			<table class="d-flex justify-content-center">
				<tr>
					<td id="fixStr">아이디 : </td>
					<td><input name="userID" type="text" value=""></td>
				</tr>
				<tr>
					<td id="fixStr">비밀번호 : </td>
					<td><input name="userPW" type="password" value=""></td>
				</tr>
				<tr>
					<td id="fixStr">비밀번호 확인 : </td>
					<td><input name="userPW" type="password" value=""></td>
				</tr>
				<tr>
					<td id="fixStr">e-mail : </td>
					<td><input name="email" type="email" value=""></td>
				</tr>
				<tr>
					<td id="fixStr">이름 : </td>
					<td><input name="userName" type="text" value=""></td>
				</tr>
				<tr>
					<td id="fixStr">전화번호( - 없이 입력) : </td>
					<td><input name="PhoneNo" type="text" value=""></td>
				</tr>
				<tr>
					<td id="fixStr">주소 : </td>
					<td><input name="address" type="text" value=""></td>
				</tr>
				<tr class="d-flex justify-content-between">
					<td id="fixStr" class="">성별 : </td>
					<td><input name="gender" type="radio" value="남">남자</td>
					<td><input name="gender" type="radio" value="여">여자</td>
				</tr>
				
			</table>
			<hr>
			<div class="d-flex justify-content-center">
				<a href="cy_main.jsp">
					<button id="mainBtn" type="button" class="btn btn-outline-dark ms-5" >Go to Main</button>
				</a>
					<button type="submit" class="btn btn-outline-dark ms-5" >OK</button>
			</div>
			</div>
			</form>
		</div>
	</section>
	<script type="text/javascript">
		// 체크하는 방법 Dom tree 가 완성이 잘 됐는지. 즉, 페이지 로딩이 잘 끝났는지 확인
		$(document).ready(function() {

			$("#loginBtn").on('click', function() {
				location
				href = 'cy_login.jsp';
			});

		});
	</script>
</body>
</html>