<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="x-iso-8859-11">
    <title>Todo App</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
    <jsp:include page="../common/header.jsp"></jsp:include>
    <div class="container ">
        <h2>User Register Form</h2>
        <div class="col-md-6 col-md-offset-3">
            <div class="alert alert-success center" role="alert">
                <p>${NOTIFICATION}</p>
            </div>

            <form action="<%=request.getContextPath()%>/register" method="post">
                <div class="form-group">
                    <label for="fname">First Name:</label>
                    <input type="text" class="form-control" id="fname" placeholder="First Name" name="firstname" />
                </div>
                <div class="form-group">
                    <label for="lname">Last Name:</label>
                    <input type="text" class="form-control" id="lname" placeholder="Last Name" name="lastname" />
                </div>
                <div class="form-group">
                    <label for="uname">Username:</label>
                    <input type="text" class="form-control" id="uname" placeholder="Username" name="username" />
                </div>
                <div class="form-group">
                    <label for="pass">Password:</label>
                    <input type="password" class="form-control" id="pass" placeholder="Password" name="password" />
                </div>

                <button type="submit" class="btn btn-primary">
                    Submit
                </button>
            </form>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
