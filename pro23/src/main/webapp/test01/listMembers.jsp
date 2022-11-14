<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
 <%@ taglib prefix ="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%
 	request.setCharacterEncoding("UTF-8");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<table border='1' width='800'>
		<tr align ="center" bgcolor="#ccff66" >
		<td> 아이디</td>
		<td> 비밀번호</td>
		<td> 이름</td>
		<td> 이메일</td>
		<td> 가입일 </td>
		
	<c:forEach var="member" items="${membersList }">
		<tr align ="center" >
		<td= width='400'> 
		<td> ${member.id }</td>
		<td> ${member.pwd }</td>
		<td> ${member.name }</td>
		<td> ${member.email }</td>
		<td> ${member.joinDate }</td>
	</tr>
	</c:forEach>
	</table>
	</div>
	<p class="cls2">회원가입하기</p></a>
	</table>
</body>
</html>