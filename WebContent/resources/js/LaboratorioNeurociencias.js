var labNeuroCiencias = angular.module('LabNeurociencias', [ 'ngRoute',
		'ngCookies' ]);

labNeuroCiencias.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/iniciarSesion', {
		templateUrl : 'login.html',
		controller : 'LoginController'
	}).when('/', {
		templateUrl : 'ListarDispositivos.html',
		controller : 'DispositivoController'
	}).when('/crearDispositivo', {
		templateUrl : 'CrearDispositivo.html',
		controller : 'DispositivoController'
	});

} ]);

labNeuroCiencias
		.service(
				'administrador',
				function($http) {
					this.validar = function(user, pws) {
						return $http({
							method : 'GET',
							url : 'http://localhost:8080/LaboratorioNeurocienciasWS/rest/Administrador/login',
							params : {
								email : user,
								pass : pws
							}
						})
					}
				});

labNeuroCiencias
		.service(
				'dispositivo',
				function($http) {
					this.listarDispositivo = function() {
						return $http({
							method : 'GET',
							url : 'http://localhost:8080/LaboratorioNeurocienciasWS/rest/Dispositivo/consultarTodos',
						})
					}
					this.crearDispositivo = function(dispositivoNuevo) {
						return $http({
							method : 'POST',
							url : 'http://localhost:8080/LaboratorioNeurocienciasWS/rest/Dispositivo/crear',
							params : {
								referencia : dispositivoNuevo.referencia,
								nombre : dispositivoNuevo.nombre,
								descripcion : dispositivoNuevo.descripcion,
								tipo : dispositivoNuevo.tipo,
								foto : dispositivoNuevo.foto,
								emailAdministrador : dispositivoNuevo.emailAdministrador
							}
						})
					}

				});
labNeuroCiencias
		.service(
				'tipo',
				function($http) {
					this.listarTipo = function() {
						return $http({
							method : 'GET',
							url : 'http://localhost:8080/LaboratorioNeurocienciasWS/rest/Tipo/consultarTodos'
						})
					}
				});

labNeuroCiencias.controller('LoginController', function($scope, $rootScope,
		administrador, $location, $cookies) {
	$scope.validar = function() {
		administrador.validar($scope.emailAdmin, $scope.contrasena).success(
				function(data) {
					if (data != "Login exitoso: true") {
						alert(data);
					} else {
						alert('Valido');
						$cookies.email = $scope.emailAdmin;
						$rootScope.logueado = true;
						$location.path("/crearDispositivo");
					}
				})
	}
	$scope.goDispositivo = function() {
		$location.path("/");
	}
	$scope.goLogin = function() {
		$location.path("/iniciarSesion");
	}
	$scope.goCrear = function() {
		$location.path("/crearDispositivo");
	}
	$scope.salir = function() {
		if ($scope.isLogueado) {
			$cookies.email = undefined;
			$scope.logueado = false;
			$location.path("/iniciarSesion");
		}
	}
});

labNeuroCiencias
		.controller(
				'DispositivoController',
				function($scope, $rootScope, dispositivo, tipo, $location,
						$cookies) {
					$scope.dispositivoNuevo = {};
					$scope.listar = function() {
						$scope.isLogueado = $rootScope.logueado;
						dispositivo
								.listarDispositivo()
								.success(
										function(data) {
											console.log(data);
											if (data == null) {
												$scope.dispositivos = null;
											} else {
												if (data.dispositivoWSDTO.length > 0) {
													$scope.dispositivos = data.dispositivoWSDTO;
													for (var i = 0; i < $scope.dispositivos.length; i++) {
														if ($scope.dispositivos[i].disponible == "true") {
															$scope.dispositivos[i].disponible = "Disponible";
														} else {
															$scope.dispositivos[i].disponible = "No disponible";
														}
													}
												}
											}
										})
						tipo.listarTipo().success(function(data) {
							if (data == null) {
								$scope.tipos = data;
							} else {
								if (data.tipoWSDTO.length > 0) {
									$scope.tipos = data.tipoWSDTO;
								} else {
									$scope.tipos = data;
								}
							}
						})
					}

					$scope.goDispositivo = function() {
						$location.path("/");
					}
					$scope.goLogin = function() {
						$location.path("/iniciarSesion");
					}
					$scope.goCrear = function() {
						$location.path("/crearDispositivo");
					}
					$scope.salir = function() {
						if ($scope.isLogueado) {
							$cookies.email = undefined;
							$scope.logueado = false;
							$location.path("/iniciarSesion");
						}
					}
					$scope.crearDispositivo = function(dispositivoNuevo) {
						dispositivoNuevo.emailAdministrador = $cookies.email;
						dispositivo
								.crearDispositivo(dispositivoNuevo)
								.success(
										function(data) {
											if (data == "El dispositivo se ha creado exitosamente") {
												alert(data);
												$location.path("/");
											} else {
												alert(data);
											}
										})
					}

					$scope.listar();
				});

labNeuroCiencias.run(function($rootScope, $cookies, $location) {
	$rootScope.$on('$routeChangeStart', function() {
		if (typeof ($cookies.email) == 'undefined') {
			$rootScope.logueado = false;
			if ($location.path() == ("/crearDispositivo")) {
				$location.path("/iniciarSesion");
			}
		}
	});
});
