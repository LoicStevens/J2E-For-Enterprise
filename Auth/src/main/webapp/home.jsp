<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="auth.model.User" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html data-bs-theme="light" lang="en-US" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JobOs Home</title>
    <!-- Favicons -->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/img/favicons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/img/favicons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/img/favicons/favicon-16x16.png">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicons/favicon.ico">
    <link rel="manifest" href="assets/img/favicons/manifest.json">
    <meta name="msapplication-TileImage" content="assets/img/favicons/mstile-150x150.png">
    <meta name="theme-color" content="#ffffff">
    <!-- Stylesheets -->
    <link rel="stylesheet" href="vendors/swiper/swiper-bundle.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300;400;500;600;700&amp;display=swap" rel="stylesheet">
    <link href="assets/css/theme.css" rel="stylesheet" id="style-default">
    <link href="assets/css/user-rtl.css" rel="stylesheet" id="user-style-rtl">
    <link href="assets/css/user.css" rel="stylesheet" id="user-style-default">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.8/css/line.css">
    <style>
        .navbar-custom {
            background-color: #007bff; /* Adjust to a lighter blue shade */
        }
        .nav-item .nav-link .nav-bar-item {
            text-color: white; /* Change text color to white */
        }
        .navbar-custom .navbar-toggler {
            border-color: white; /* Change border color to white */
        }
    </style>
</head>

<body class="bg-light">
    <!-- Main Content -->
    <main class="main" id="top">
        <div class="bg-white">
            <div class="content">
                <nav class="navbar navbar-expand-lg py-1 navbar-custom" id="navbar-top" data-navbar-soft-on-scroll="data-navbar-soft-on-scroll">
                    <div class="container">
                        <a class="navbar-brand me-lg-auto cursor-pointer text-white" href=""><div class="font-bold">JOBOS</div></a>
                        <button class="navbar-toggler border-0 pe-0 text-white" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent" data-navbar-collapse="data-navbar-collapse">
                            <div class="container d-lg-flex justify-content-lg-end pe-lg-0 w-lg-100">
                                <form class="form-inline position-relative w-lg-50 ms-lg-4 ms-xl-9 mt-3 mt-lg-0" onsubmit="return false;">
                                    <input class="search fs-8 bg-transparent form-control text-white" type="search" name="search" placeholder="search your provider" />
                                    <div class="search-icon"><span class="uil uil-search"></span></div>
                                </form>
                                <ul class="navbar-nav mt-2 mt-lg-1 ms-lg-4 ms-xl-8 ms-2xl-9 gap-lg-x1" data-navbar-nav="data-navbar-nav">
                                    <li class="nav-item"><a class="nav-link nav-bar-item px-0 text-white" href="#home" title="home">Home</a></li>
                                    <li class="nav-item"><a class="nav-link nav-bar-item px-0 text-white" href="#about" title="about">Notification</a></li>
                                    <li class="nav-item"><a class="nav-link nav-bar-item px-0 text-white" href="#products" title="cata">CreatePost</a></li>
                                   
                                    <% User user = (User) session.getAttribute("user"); %>
                                    <% if (user != null) { %>
                                        <li class="nav-item"><a class="nav-link nav-bar-item px-0 text-white" href="LogoutServlet" title="">Deconnexion</a></li>
                                    <% } %>
                                     <% if (user == null) { %>
                                          <li class="nav-item"><a class="nav-link nav-bar-item px-0 text-white" href="login.jsp" title="cata">signin</a></li>
                                    <% } %>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
                <div class="container" data-bs-target="#navbar-top" data-bs-spy="scroll" tabindex="0">
                    <section class="mb-9 mb-lg-10 mb-xxl-11 text-center text-lg-start mt-9" id="home">
                        <div class="row g-4 g-lg-6 g-xl-7 mb-6 mb-lg-7 mb-xl-10 align-items-center">
                            <div class="col-12 col-lg-6">
                                <% if (user != null) { %>
                                    <h1>Welcome <%= user.getRole() %> <%= user.getUsername() %></h1>
                                <% } else { %>
                                    <h1>Welcome</h1>
                                <% } %>
                                <h1 class="fs-5 fs-md-3 fs-xxl-2 text-black fw-light mb-4">Find <span class="fw-bold">The best </span>talent for you project <br class="d-sm-none d-md-block d-xxl-none" />with <span class="text-gradient fw-bold">JOBOS</span></h1>
                                <p class="fs-8 fs-md-11 fs-xxl-7 text-primary mb-5 mb-lg-6 mb-xl-7 fw-light">
                                    And boost your success with our revolutionary platform
                                </p>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </main>

    <!-- JavaScript to ask confirmation before leaving the page -->
    <script>
        window.addEventListener("beforeunload", function (e) {
            var confirmationMessage = "Êtes-vous sûr de vouloir quitter la session ?";
            e.returnValue = confirmationMessage;
            return confirmationMessage;
        });
    </script>
</body>

</html>
