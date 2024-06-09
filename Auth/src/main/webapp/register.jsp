<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Metas, stylesheets, scripts, etc. -->
    <meta charset="utf-8" />
    <title>JobOs register</title>
    <link rel="apple-touch-icon" sizes="180x180" href="vendors/images/apple-touch-icon.png" />
    <link rel="icon" type="image/png" sizes="32x32" href="vendors/images/favicon-32x32.png" />
    <link rel="icon" type="image/png" sizes="16x16" href="vendors/images/favicon-16x16.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="vendors/styles/core.css" />
    <link rel="stylesheet" type="text/css" href="vendors/styles/icon-font.min.css" />
    <link rel="stylesheet" type="text/css" href="vendors/styles/style.css" />
   
</head>
<body class="login-page">

    <!-- Page content -->

    <div class="login-wrap d-flex align-items-center flex-wrap justify-content-center">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-6 col-lg-7">
                    <img src="vendors/images/login-page-img.png" alt="" />
                </div>
                <div class="col-md-6 col-lg-5">
                    <div class="login-box bg-white box-shadow border-radius-10">
                        <div class="login-title">
                            <h2 class="text-center text-primary">Register To JobOs</h2>
                            <%-- Error messages section --%>
                            <%
                                List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");
                                if (errorMessages != null && !errorMessages.isEmpty()) {
                                    for (String errorMessage : errorMessages) {
                            %>
                                        <div class="alert alert-danger " role="alert">
                                            <%= errorMessage %>
                                        </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                        <form action="registerOtp" method="post">
                            <div class="select-role">
                                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                    <label class="btn active"> <input type="radio"
                                        name="role" value="client" id="client" required />
                                        <div class="icon">
                                            <img src="vendors/images/briefcase.svg" class="svg" alt="" />
                                        </div> <span>I'm</span> Customer
                                    </label> <label class="btn"> <input type="radio" name="role"
                                        value="prestataire" id="provider" required />
                                        <div class="icon">
                                            <img src="vendors/images/person.svg" class="svg" alt="" />
                                        </div> <span>I'm</span> Provider
                                    </label>
                                </div>
                            </div>
                            <div class="input-group custom">
                                <input type="text" name="username"
                                    class="form-control form-control-lg" placeholder="Username"
                                     />
                                <div class="input-group-append custom">
                                    <span class="input-group-text"><i
                                        class="icon-copy dw dw-user1"></i></span>
                                </div>
                            </div>
                            <div class="input-group custom">
                                <input type="email" name="email"
                                    class="form-control form-control-lg" placeholder="Email"
                                     />
                                <div class="input-group-append custom">
                                    <span class="input-group-text"><i
                                        class="fa fa-envelope-o" aria-hidden="true"></i></span>
                                </div>
                            </div>
                            <div class="input-group custom">
                                <input type="password" name="password"
                                    class="form-control form-control-lg" placeholder="**********"
                                     />
                                <div class="input-group-append custom">
                                    <span class="input-group-text"><i class="dw dw-padlock1"></i></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="input-group mb-0">
                                        <input class="btn btn-primary btn-lg btn-block" type="submit"
                                            value="Sign Up">
                                    </div>
                                    <div class="font-16 weight-600 pt-10 pb-10 text-center"
                                        data-color="#707373">OR</div>
                                    <div class="input-group mb-0">
                                        <a class="btn btn-outline-primary btn-lg btn-block"
                                            href="login.jsp">Log to my Account</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- welcome modal start -->
    <div class="welcome-modal">
        <!-- Content of welcome modal -->
    </div>
    <!-- welcome modal end -->

    <!-- Scripts -->
    <script src="vendors/scripts/core.js"></script>
    <script src="vendors/scripts/script.min.js"></script>
    <script src="vendors/scripts/process.js"></script>
    <script src="vendors/scripts/layout-settings.js"></script>
 
    
</body>
</html>
