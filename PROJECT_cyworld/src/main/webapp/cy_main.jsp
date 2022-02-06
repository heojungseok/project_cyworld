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
<title>cyworld</title>
</head>
<style>
body {
	width: 100%;
}

#wrap {
	margin: auto;
	width: 1000px;
	height: 600px;
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
	width: 470px;
	height: 330px;
	margin: -135px 0px 0px -220px;
	top: 50%;
	left: 50%;
}

#main-Id {
	position: absolute;
	width: 400px;
	height: 25px;
	margin: 90px 0px 0px -195px;
	top: 50%;
	left: 50%;
}

#main-Pw {
	position: absolute;
	width: 400px;
	height: 25px;
	margin: 120px 0px 0px -200px;
	top: 50%;
	left: 50%;
}

#main-LoginBtn {
	position: absolute;
	width: 120px;
	height: 70px;
	margin: 80px 0px 0px 125px;
	top: 50%;
	left: 50%;
}

#main-JoinBtn {
	position: absolute;
	width: 410px;
	height: 35px;
	margin: 155px 0px 0px -190px;
	top: 50%;
	left: 50%;
}
</style>
<body>
	<div class="" id="wrap">
		<section>
			<article>
				<div>
					<img id="mainImg" alt="main_home" src="./img/home_img.jpg" />
				</div>
				<div id="">
					<img id="mainLogo" alt="main_logo" src="./img/main_logo.jpg" />

					<form action="cy_mini" method="post">

						<div class="d-flex flex-column">
							<div id="main-Id" class="d-flex justify-content-around ">
								<span>cyworld&nbsp;ID</span> <input class="me-5" type="text"
									name="userID"><br>
							</div>
							<div id="main-Pw" class="d-flex justify-content-around ">
								<span>cyworld&nbsp;PW</span> <input class="me-5" type="password"
									name="userPW"><br>
							</div>
							<div id="main-LoginBtn" class="d-flex justify-content-center">
								<button id="loginBtn" type="submit" class="btn btn-dark">LOGIN</button>
					</form>

				</div>
				<div id="main-JoinBtn" class="d-flex justify-content-between">
					<div>
						<button id="joinBtn" type="button" class="btn btn-outline-dark">아이디
							찾기</button>
						<button id="joinBtn" type="button" class="btn btn-outline-dark">비밀번호
							찾기</button>
						<a href="cy_find_friend.jsp">
							<button class="btn btn-outline-dark" type="button">친구 찾기</button>
						</a>
					</div>
					<div>
					<a href="cy_memeber.jsp">
						<button type="button" class="btn btn-dark"> join</button>
					</a>
					</div>
				</div>
			</article>
		</section>
	</div>
	<script type="text/javascript">
		/*     // 체크하는 방법 Dom tree 가 완성이 잘 됐는지. 즉, 페이지 로딩이 잘 끝났는지 확인
		 $(document).ready(function () {

		 $("#loginBtn").on('click', function () {
		 window.open('test');
		 });
		
		 }); */
	</script>
</body>
</html>