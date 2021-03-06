<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
</head>
<%
    if (request.getAttribute("username") == null) {
        request.setAttribute("username", "");
    }
    if (request.getAttribute("email") == null) {
        request.setAttribute("email", "");
    }
%>

<style>
    <%@ include file="/resources/css/main.css" %>
</style>
<body>
    <jsp:include page="partials/navbar.jsp" />
    <div class="container-fluid p-5" id="register-page">
        <h1 class="display-4">Please fill in your information</h1>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username" class="placeholder-label">
                    <input id="username" name="username" class="form-control" type="text" required value="<%= request.getAttribute("username")%>" placeholder=" ">
                    <span class="placeholder-text">Username</span>
                </label>
            </div>
            <div class="form-group">
                <label for="email" class="placeholder-label">
                    <input id="email" name="email" class="form-control" type="text" required value="<%= request.getAttribute("email")%>" placeholder=" ">
                    <span class="placeholder-text">Email</span>
                </label>
            </div>
            <div class="form-group">
                <label for="password" class="placeholder-label">
                    <input id="password" name="password" class="form-control" type="password" placeholder=" ">
                    <span class="placeholder-text">Password</span>
                </label>
            </div>
            <div class="form-group">
                <label for="confirm_password" class="placeholder-label">
                    <input id="confirm_password" name="confirm_password" class="form-control" type="password" required placeholder=" ">
                    <span class="placeholder-text">Confirm Password</span>
                </label>
            </div>
            <input type="submit" class="btn btn-outline-success btn-block">
        </form>
        <jsp:include page="/WEB-INF/partials/errors.jsp" />
    </div>

    <jsp:include page="/WEB-INF/partials/scripts.jsp" />
</body>
</html>
