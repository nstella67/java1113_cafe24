<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>*NN Home*</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="./css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/style.css?v=1">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <script src="./js/jquery-3.3.1.min.js"></script>
  <script src="./js/bootstrap.min.js"></script>
  <script src="./js/myscript.js"></script>
  <style>
  	 .time {
  	    background-color: #8cc4d9;
		color:#848484;
		font-weight:bold;
		font-size:20px;
		text-align:center;
		border:none;
  	}
  </style>
  </head>

<body onload="showtime()" onunload="killtime()">
<!-- Navbar -->
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">Home</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="./bbs/bbsList.jsp">Board</a></li>
        <li><a href="./notice/noticeList.jsp">Notice</a></li>
        <li><a href="./member/loginForm.jsp">Log in</a></li>
        <li><a href="./pds/pdsList.jsp">Photo</a></li>
        <li><a href="./mail/mailForm.jsp">Mail</a></li>
        <li><a href="./bbs2/bbslist.do">board(MVC)</a></li>
        <li><a href="./member2/loginForm.do">log in(MVC)</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="bg-5 text-center">
	<!-- 디지털시계 -->
	<form name="myform">
      <input type="text" name="clock" size="40" class="time" readonly>
    </form>	
</div>

<!-- First Container -->
<div class="container-fluid bg-1 text-center">
  <h3 class="margin">Who Am I?</h3>
  <img src="./images/tenor (6).gif" class="img-responsive img-circle margin" style="display:inline" alt="Bird" width="200" height="200">
</div>

<!-- Second Container -->
<div class="container-fluid bg-2 text-center">
	<h3 class="margin">Welcome to My Web</h3>
</div>

<!-- Third Container (Grid) -->
<div class="container-fluid bg-3 text-center">    
  <h3 class="margin">나만의 공간</h3><br>
</div>

<!-- Footer -->
<footer class="container-fluid2 bg-4 text-center">
	Copyright &copy; 2019 myweb 
</footer>

</body>
</html>
