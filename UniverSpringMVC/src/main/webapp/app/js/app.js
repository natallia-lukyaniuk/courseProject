'use strict';

var univerApp = angular.module('univerApp', [ "ngRoute", "ngCookies"])
	.config(function($routeProvider)
		{
	$routeProvider.when('/users', {
		templateUrl : 'app/view/user-list.jsp',
		controller : 'UserController'
	});
	$routeProvider.when('/providers', {
		templateUrl : 'app/view/provider-list.jsp',
		controller : 'ProviderController'
	});
	$routeProvider.when('/goods', {
		templateUrl : 'app/view/good-list.jsp',
		controller : 'GoodController'
	});
	$routeProvider.when('/goodsTypes', {
		templateUrl : 'app/view/goodsType-list.jsp',
		controller : 'GoodsTypeController'
	});
	$routeProvider.when('/goodsSupplys', {
		templateUrl : 'app/view/goodsSupply-list.jsp',
		controller : 'GoodsSupplyController'
	});
});

function closeForm() {
	$('#form-editor').slideUp('slow');
}

/*
function HomeController($scope, $window, authService) {

	$scope.isLoggedIn = false;

	authService.getUserInfo(function(userInfo) {
		$scope.isLoggedIn = userInfo ? true : false;
	});

	$scope.logout = function() {
		authService.logout().success(function() {
			$window.location.reload();
		});
	};
}
function SanctumController($window, $scope, authService) {

	$scope.fullName = "";
	$scope.country = "";
	$scope.imagePath = getContextPath()
			+ "/resources/partials/protected/photo.jpg";

	authService.getUserInfo(function(userInfo) {
		if (userInfo) {
			$scope.fullName = userInfo.fullName;
			$scope.country = userInfo.country;
		}
	});

	$scope.logout = function() {
		authService.logout().success(function() {
			$window.location.href = "index.html";
		});
	};
}
function LoginController($rootScope, $window, $scope, authService) {

	$scope.username = '';
	$scope.password = '';
	$scope.remember = false;
	$scope.loginError = false;

	$scope.login = function() {
		authService
				.authenticate(
						$scope.username,
						$scope.password,
						$scope.remember,
						function(success) {
							if (success) {
								$window.location = $rootScope.targetUrl ? $rootScope.targetUrl
										: "#/home";
							} else
								$scope.loginError = true;
						});
	};

	$scope.resetStatus = function() {
		$scope.loginError = false;
	};

}
*/
/*var mainModule = angular
		.module('mainModule', [ 'ngRoute', 'ngCookies' ])
		.service(
				'authService',
				[
						'$http',
						'$cookies',
						function($http, $cookies) {

							*//**
							 * @desc Аутентифицировать пользователя. Если
							 *       пользователь вошел в систему - будет
							 *       вызвана функция callback с аргументом true,
							 *       иначе будет вызвана функция callback с
							 *       аргументом false.
							 * 
							 * @param name
							 *            Логин
							 * @param password
							 *            Пароль
							 * @param remember
							 *            true если нужно запомнить
							 *            пользователя, иначе false
							 * @param callback
							 *            Функция
							 * 
							 *//*
							this.authenticate = function(name, password,
									remember, callback) {

								if (!name || !password) {
									callback(false);
									return;
								}

								var headers = {
									authorization : "Basic "
											+ btoa(name + ":" + password)
								};

								$http.get(getContextPath() + '/auth/user', {
									'headers' : headers
								}).success(function(data) {

									if (data) {
										if (remember) {
											createRememberMeCookie(data);
										}
										callback(true);
									} else {
										callback(false);
									}

								}).error(function() {
									callback(false);
								});
							};

							*//**
							 * @desc Получить информацию о текущем пользователе.
							 *       Если пользователь вошел в систему - будет
							 *       вызвана функция callback с данными
							 *       пользователя, иначе будет функция callback
							 *       будет вызывана с аргументом null.
							 * 
							 * @param callback
							 *            Функция
							 * 
							 *//*
							this.getUserInfo = function(callback) {

								$http.get(getContextPath() + '/auth/user', {})
										.success(function(data) {

											if (data.username) {
												callback(data);
											} else {
												callback(null);
											}

										}).error(function() {
											callback(null);
										});
							};

							*//**
							 * @desc Выйти из системы
							 * 
							 *//*
							this.logout = function() {
								removeRememberMeCookie();
								return $http.post(getContextPath()
										+ '/auth/logout', {});
							};

							*//**
							 * @desc Создать cookie для аутентификации
							 *       пользователя без ввода имени и пароля.
							 *       Функция вызывается в том случае, если
							 *       пользователь установил опцию "Запомнить
							 *       меня". Вызывается сразу после успешного
							 *       входа в функции authenticate.
							 * 
							 * @param userdetials
							 *            Данные пользователя после успешной
							 *            аутентификации, которые вернул сервер.
							 * 
							 *//*
							var createRememberMeCookie = function(userdetials) {

								var name = userdetials.username;
								var pwd = userdetials.password;
								var expireDate = new Date();
								expireDate.setDate(expireDate.getDate() + 30);

								var cookieValue = btoa(name
										+ ":"
										+ expireDate.getTime().toString()
										+ ":"
										+ md5(name
												+ ":"
												+ expireDate.getTime()
														.toString() + ":" + pwd
												+ ":"
												+ "DEVELNOTES_REMEMBER_TOKEN"));

								$cookies.put('DEVELNOTES_REMEMBER_ME_COOKIE',
										cookieValue, {
											'expires' : expireDate,
											'path' : '/'
										});
							};

							*//**
							 * @desc Удалить "remember me" cookie. Сбрасывает
							 *       дату действия для cookie
							 *       DEVELNOTES_REMEMBER_ME_COOKIE на текущую -
							 *       1 день
							 * 
							 *//*
							var removeRememberMeCookie = function() {
								var expireDate = new Date();
								expireDate.setDate(expireDate.getDate() - 1);
								$cookies.put('DEVELNOTES_REMEMBER_ME_COOKIE',
										'', {
											'expires' : expireDate,
											'path' : '/'
										});
							};

						} ])

		.controller('HomeController',
				[ '$scope', '$window', 'authService', HomeController ])
		.controller('SanctumController',
				[ '$window', '$scope', 'authService', SanctumController ])
		.controller(
				'LoginController',
				[ '$rootScope', '$window', '$scope', 'authService',
						LoginController ])

		.config(
				[
						'$routeProvider',
						'$httpProvider',
						function($routeProvider, $httpProvider) {

							$httpProvider.interceptors.push('responseObserver');

							$routeProvider
									.when('/home', {

										templateUrl : 'app/partials/home.jsp',
										controller : 'HomeController'

									})
									.when('/login', {

										templateUrl : 'partials/login.jsp',
										controller : 'LoginController'

									})
									.when(
											'/photos',
											{

												templateUrl : 'partials/protected/photos.html',
												controller : 'SanctumController'

											})
									.when(
											'/profile',
											{

												templateUrl : 'partials/protected/profile.html',
												controller : 'SanctumController'

											}).otherwise({
										redirectTo : '/home'
									});

						} ])

		.factory(
				'responseObserver',
				[
						'$rootScope',
						'$q',
						'$window',
						function responseObserver($rootScope, $q, $window) {

							return {
								'responseError' : function(errorResponse) {
									switch (errorResponse.status) {
									case 403:

										if ($window.location.hash
												.indexOf("login") == -1)
											$rootScope.targetUrl = $window.location.hash;

										$window.location = '#/login';
										break;
									}

									return $q.reject(errorResponse);
								}
							};
						} ]);

*/