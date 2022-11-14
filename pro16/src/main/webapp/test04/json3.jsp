<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(function() {
		$("#checkJson").click(function() {
			var jsonStr = '{"name":"박지성","age":25,"gender":"남자","nickname":"날센돌이"}';
			var jsonObj = JSON.parse(jsonStr);
			var output = "회원 정보<br>";
			output += "======<br>";
			output += "이름: " + jsonObj.name + "<br>";
			output += "나이: " + jsonObj.age + "<br>";
			output += "성별: " + jsonObj.gender + "<br>";
			output += "별명: " + jsonObj.nickname + "<br>";
			$("#output").html(output);
		
		
		});
	});
</script>
</head>
<body>
	<a id="checkJson">출력</a><br><br>
	<div id="output"></div>
</body>
</html>