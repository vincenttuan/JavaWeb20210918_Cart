<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart 10元商店</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
</head>
<body style="padding: 15px">
	
	<form class="pure-form" method="post" action="${pageContext.request.contextPath}/servlet/cart">
		<fieldset>
			<legend>10元商店-請選擇禮品</legend>
			編號: <input type="text" name="id" value="${ user.id }" readonly><p />
			禮品: 
				<c:forEach var="p" items="${ products }">  
					<input type="checkbox" name="data" value="${ p.id }"> ${ p.name }
				</c:forEach>
			<p />
			<button type="submit" class="pure-button pure-button-primary">加入購物車</button>
			<button type="button" 
					onclick="location.href='${pageContext.request.contextPath}/servlet/cart?type=1'"
					class="pure-button pure-button-primary">查詢購物車</button>
			<button type="button" 
					onclick="location.href='${pageContext.request.contextPath}/servlet/cart?type=2'"
					class="pure-button pure-button-primary">查詢訂單紀錄</button>
			<button type="button" 
					onclick="location.href='${pageContext.request.contextPath}/logout'"
					class="pure-button pure-button-primary">登出</button>					
		</fieldset>
	</form>
	
</body>
</html>