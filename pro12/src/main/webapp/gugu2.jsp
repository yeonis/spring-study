<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	int dan=Integer.parseInt(request.getParameter("dan"));
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	for(int z=1; z<=dan; z++){
		for(int i=1; i<10; i++){
%>
<%	
	if(i%2==1){
%>
	<table>
	<tr align=center bgcolor="#ccff66">
				<td width='400'>
				<%=z %> * <%=i %>
			</td>
			<td width='400'>
				<%=i*z %>
			</td>
		</tr>
	
	
<%
	}else{
%>
	
	<tr align='center' bgcolor="#ccccff">
			<td width='400'>
				<%=z %> * <%=i %>
			</td>
			<td width='400'>
				<%=i*z %>
			</td>
		</tr>
	</table>	
<%
}
%>
<%
}
%>
<%
}
%>
</body>
</html>