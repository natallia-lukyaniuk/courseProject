<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html ng-app="univerApp">
<head>
<title>Accounting system for store</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname
				.indexOf("/", 2));
	}
</script>
<style>
.form-container {
	margin-top: 100px;
}

.input-custom-style {
	margin-bottom: 20px;
}

.has-error {
	color: red;
}

* {
	margin: 0;
	padding: 0;
}

html, body, .wrapper {
	height: 100%;
}

.content {
	box-sizing: border-box;
	min-height: 100%;
	padding-bottom: 90px;
}

.footer {
	height: 80px;
	margin-top: -80px;
	width: 100%;
	background-color: #f5f5f5;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Accounting system for store</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#users">User</a></li>
					<li><a href="#providers">Provider</a></li>
					<li><a href="#goods">Good</a></li>
					<li><a href="#goodsTypes">Good type</a></li>
					<li><a href="#goodsSupplys">Goods supply</a></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" style='cursor: pointer;'>Reports <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#report-semstr">Report Group Semestr</a></li>
							<li><a href="#report-group-subject">Report Group Subject</a></li>
							<li><a href="#report-student">Report Student</a></li>
							<li><a href="#report-appExam">Report AppExam</a></li>
							<li><a href="#report-certificate">Report certificate </a></li>
						</ul></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class='wrapper'>
		<div class='content'>
			<ng-view></ng-view>
		</div>

		<footer class='footer'>
			<div class='container'>
				<p class='text-right' style='margin: 20px 0;'>© RIS - UNIVER
					COURSE PROJECT. LUKYANYUK, PRISCHEP, SCHERBINSKAYA</p>
			</div>
		</footer>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
	<script type="text/javascript">
		$('body').on('click', 'a#form-editor-btn', function(e) {
			$('#form-editor').slideDown('slow');
		});

		$('body').on('click', '#edit-btn', function(e) {
			$('#form-editor').slideDown('slow');
		});

		/* $('body').on('click', '#form-editor-submit-btn', function(e) {
			$('#form-editor').slideUp('slow');
		}); */
		$('body').on('click', '#modalSave-btn', function(e) {
			$('#myModal').modal('hide');
		});
		// initializes and invokes show immediately
	</script>

	<script type="text/javascript">
		
	<%@include file="app/js/app.js"%>
		
	<%@include file="app/js/service/UserService.js"%>
		
	<%@include file="app/js/controller/UserController.js"%>
		
	<%@include file="app/js/service/ProviderService.js"%>
		
	<%@include file="app/js/controller/ProviderController.js"%>
		
	<%@include file="app/js/service/GoodService.js"%>
		
	<%@include file="app/js/controller/GoodController.js"%>
		
	<%@include file="app/js/service/GoodsTypeService.js"%>
		
	<%@include file="app/js/controller/GoodsTypeController.js"%>
		
	<%@include file="app/js/service/GoodsSupplyService.js"%>
		
	<%@include file="app/js/controller/GoodsSupplyController.js"%>
		
	
		
	</script>
</body>
</html>