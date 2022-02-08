<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>cy_edit</title>
<style>
body{
	width: 800px;
	margin: auto;
}
e
</style>
</head>
<body>
<!--encoding 방법을 바꿀 때 설정 (파일을 보낼 수 있게) -->
	<form action="cy_edit_minihp" method="post" enctype="multipart/form-data">
		<div class="d-flex flex-column">
		
			<span class="fs-2 fw-bolder font-monospace text-muted"> 
			홈페이지 대문 글 입력해주세요.
			</span>
			<input type="text" name="intro1" >
			
			<span class="fs-2 fw-bolder font-monospace text-muted"> 
			프로필 소개 글 입력해주세요.
			</span>
			<input type="text" name="intro2" >
			
			<span class="fs-2 fw-bolder font-monospace text-muted"> 
			도메인을 입력해주세요.
			</span>
			<input type="text" name="domain" >
			
			<span class="fs-2 fw-bolder font-monospace text-muted"> 
			이미지를 선택해주세요.
			</span>
			<input type="file" name="imgs" value="img" >
			
			<input type="submit" name="done" value="수정 하기">
		
		</div>
		</form>
		<div>
		<button id="cancleBtn" name="cancle" >취소</button>
		</div>
</body>
</html>