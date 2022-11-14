<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false"
 %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
 <%
 	request.setCharacterEncoding("UTF-8");
 %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
<style>

.cls1 {
text-align:center;
width:100%;
}
</style>
</head>
<body>
<div class="cls1">
<h3>회원정보</h3>
<table>
<c:choose>
<c:when test="${ membersList == null }">
	<tr>
	<td colspan=5>
	<b>등록된 회원이 없습니다.</b>
	</td>
	</tr>
	</c:when>
	
	<c:when test="${membersList != null }">
	<table border='1' width='800'>
		<tr align ="center" bgcolor="#ccff66" >
		<td> 아이디</td>
		<td> 비밀번호</td>
		<td> 이름</td>
		<td> 이메일</td>
		
	
	<c:forEach var="mem" items="${membersList }">
		<tr align ="center" >
		<td= width='400'> 
		<td> ${mem.id }</td>
		<td> ${mem.pwd }</td>
		<td> ${mem.name }</td>
		<td> ${mem.email }</td>
	</tr>
	</c:forEach>
	</c:when>
	</c:choose>
	</table>
	</div>
	<a href="${pageContext.request.contextPath}/member/memberForm.do">
	<p class="cls2">회원가입하기</p></a>
	
</body>
</html>