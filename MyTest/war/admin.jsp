<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="robots" content="noindex, nofollow">
		<meta name="googlebot" content="noindex, nofollow">
		<title>Admin panel</title>
		<% 
			UserService userService = UserServiceFactory.getUserService();
			if (userService.isUserLoggedIn()){ 
		%>
				<% 
					if (userService.isUserAdmin()) { 
				%>
		<script type="text/javascript" language="javascript"
			src="admin/admin.nocache.js"></script>
		<script type="text/javascript">
			var info = { "email" : "<%= userService.getCurrentUser().getEmail() %>" };
		</script>
	
		</head>
		<body>
<%-- 			<h3>Panel administracyjny dla <%= request.getServerName()%></h3> --%>
<%-- 			<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Log out</a> --%>
<!-- 			<div id="panel" /> -->


				<% } else { %>
					</head>
					<body>
					Konto <%= userService.getCurrentUser().getEmail() %> 
					nie ma dostępu do zasobów serwisu <a href="<%= request.getServerName() %>"><%= request.getServerName() %></a>
					<br />
					Należy zalogować się na inne konto.
					<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Log in</a>
				<% } %>
		<% 	} else { %>
				</head>
				<body>
				<h3>Panel administracyjny dla <%= request.getServerName()%></h3>
				<h3>Zaloguj sie do panelu administratora</h3>
				<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Log in</a>
		<% } %>
		<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
			style="position: absolute; width: 0; height: 0; border: 0"></iframe>
	</body>
</html>