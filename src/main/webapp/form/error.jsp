<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Error Page</title>
	<link rel="stylesheet"
		href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
</head>
<body style="padding: 15px">
	<form class="pure-form">
		<fieldset>
			<legend>Error Page : <%=exception %></legend>
			請選擇您要去的頁面<p />
			<ol>
				<li><a href="${pageContext.request.contextPath}/servlet/cart">登入頁面</a></li>
				<li><a href="${pageContext.request.contextPath}/servlet/cart">購物頁面</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">登出頁面</a></li>
			</ol>
		</fieldset>
	</form>		
</body>
</html>