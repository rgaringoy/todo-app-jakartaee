
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String userName = null;
//allow access only if session exists
    if(session.getAttribute("user") == null){
        response.sendRedirect("login");
    }else userName = (String) session.getAttribute("user");
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <style>
        /* Footer style */
        .footer {
            text-align: center;
            padding: 10px;
            background-color: #f5f5f5;
            position: absolute;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Todo List</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><form action="<%=request.getContextPath()%>/logout" method="post">
                <input type="submit" value="Logout" >
            </form></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="form-group">
        <label>Add Task</label> <br>
        <a href="<%=request.getContextPath()%>/new" class="btn btn-primary">Add</a>
    </div>
    <hr>
    <table class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
            <th>Title</th>
            <th>Target Date</th>
            <th>Todo Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody id="todo-list">
        <c:forEach var="todo" items="${listTodo}">
        <tr>
            <td><c:out value="${todo.title}" /></td>
            <td><c:out value="${todo.targetDate}" /></td>
            <td><c:out value="${todo.status}" />

            </td>
            <td>
                <a href="update?id=<c:out value='${todo.id}' />" class="btn btn-xs btn-success">Update</a>
                <a href="delete?id=<c:out value='${todo.id}' />" class="btn btn-xs btn-danger">Delete</a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<footer class="footer">
    <p>&copy; 2023 Todo List App. All rights reserved.</p>
</footer>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>

