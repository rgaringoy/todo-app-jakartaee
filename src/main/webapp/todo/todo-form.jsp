
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
      position: relative;
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
      <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
    </ul>
  </div>
</nav>

<div class="container">
  <div class="card col-md-6 col-offset">
    <div class="card-body">
      <c:if test="${todo != null}">
      <form action="update" method="post">
        </c:if>
        <c:if test="${todo == null}">
        <form action="insert" method="post">
          </c:if>

          <caption>
            <h2>
              <c:if test="${todo != null}">
                Edit Todo
              </c:if>
              <c:if test="${todo == null}">
                Add New Todo
              </c:if>
            </h2>
          </caption>

          <c:if test="${todo != null}">
            <input type="hidden" name="id" value="<c:out value='${todo.id}' />" />
          </c:if>

          <fieldset class="form-group">
            <label>Todo Title</label> <input type="text"
                                             value="<c:out value='${todo.title}' />" class="form-control"
                                             name="title" required="required" minlength="5">
          </fieldset>

          <fieldset class="form-group">
            <label>Todo Decription</label> <input type="text"
                                                  value="<c:out value='${todo.description}' />" class="form-control"
                                                  name="description" minlength="5">
          </fieldset>

          <fieldset class="form-group">
            <label>Todo Status</label> <select class="form-control"
                                               name="isDone">
            <option value="false">In Progress</option>
            <option value="true">Complete</option>
          </select>
          </fieldset>

          <fieldset class="form-group">
            <label>Todo Target Date</label> <input type="date"
                                                   value="<c:out value='${todo.targetDate}' />" class="form-control"
                                                   name="targetDate" required="required">
          </fieldset>

          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
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

