<%--
  Created by IntelliJ IDEA.
  User: Missaka Iddamalgoda
  Date: 2018-06-17
  Time: 1:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Online Shopping</title>

    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
            <div class="col-middle">
                <div class="text-center text-center">
                    <%
                        String statusCode,message1,message2;
                        statusCode=pageContext.getErrorData().getStatusCode()+"";
                        if(pageContext.getErrorData().getStatusCode()==404){
                            message1="Sorry but we couldn't find this page";
                            message2="This page you are looking for does not exist.";
                        }else if (pageContext.getErrorData().getStatusCode()==500){
                            message1="Internal Server Error";
                            message2="We track these errors automatically, but if the problem persists feel free to contact us. In the meantime, try refreshing. ";
                        }else if (pageContext.getErrorData().getStatusCode()==403){
                            message1="Access denied";
                            message2="Full authentication is required to access this resource.";
                        }else {
                            message1="Please contact system administrator.";
                            message2=pageContext.getException().getMessage();
                        }
                    %>
                    <h1 class="error-number"><%=statusCode%></h1>
                    <h2><%=message1%></h2>
                    <p><%=message2%><a href="#">Report this?</a>
                    </p>
                    <div class="mid_center">
                        <h3>Search</h3>
                        <form>
                            <div class="col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                              <button class="btn btn-default" type="button">Go!</button>
                          </span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->
    </div>
</div>

<!-- jQuery -->
<script src="vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="vendors/nprogress/nprogress.js"></script>

<!-- Custom Theme Scripts -->
<script src="build/js/custom.min.js"></script>
</body>
</html>

