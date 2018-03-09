'use strict';

angular.module('angApp',[
	'ngRoute',
	'angApp.controllers',
	'angApp.directives',
	'angApp.services',
	'ngCookies',
	'ngStorage'
]).
config(['$routeProvider','$httpProvider' ,function($routeProvider, $httpProvider){
$routeProvider.when('/login',{templateUrl:'login.html',controller:'loginController'});
$routeProvider.when('/manageUser',{templateUrl:'manageUser.html',controller:'userController'});
$routeProvider.when('/home',{templateUrl:'home.html'});
$routeProvider.when('/register',{templateUrl:'registerUser.html',controller:'userController'});
$routeProvider.when('/updateUser',{templateUrl:'updateUser.html',controller:'userController'});
$routeProvider.when('/addRole',{templateUrl:'addRole.html',controller:'userRoleController'});
$routeProvider.when('/manageRole',{templateUrl:'manageRole.html',controller:'loginController'});
$routeProvider.when('/',{templateUrl:'home.html'});

$routeProvider.otherwise({redirectTo:'/'});
$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

$httpProvider.interceptors.push(function ($q, $rootScope, $location) {
    return {
    	'request': function(config) {
    		var isRestCall = config.url.indexOf('/api') == 0;
    		if (isRestCall && angular.isDefined($rootScope.authToken)) {
    			
    			var authToken = $rootScope.authToken;
				config.headers['X-Auth-Token'] = authToken;
    		}
    		return config || $q.when(config);
    	}
    };
}
);

}])
.run(function($rootScope, $location, $cookieStore, $http, $sessionStorage) {
	
	/* Reset error when a new view is loaded */
	$rootScope.$on('$viewContentLoaded', function() {
		
		delete $rootScope.error;
		delete $rootScope.success;
		$rootScope.allroles = $sessionStorage.allroles; 
		$rootScope.userToUpdate = $sessionStorage.userToUpdate;
	});
	
	$rootScope.logout = function() {
		delete $rootScope.user;
		delete $rootScope.authToken;
		delete $sessionStorage.user;
		$rootScope.authenticated = false;
		$cookieStore.remove('authToken');
		$location.path("/login");
	};
	
	$rootScope.hasRole = function(role) {
		if ($rootScope.user === undefined) {
			return false;
		}
		if ($rootScope.user.roles !== undefined && $rootScope.user.roles.name === role ){
			return true;
		}else if ($rootScope.user.roles[0] !== undefined && $rootScope.user.roles[0].name === role ){
			return true;
		}
		return false;
	};
	
	$rootScope.setUser = function(opt) {
		$rootScope.userToUpdate = opt;
		$sessionStorage.userToUpdate = opt; 
	};
	
	var originalPath = $location.path();
	var authToken = $cookieStore.get('authToken');
	if(authToken !== undefined){
		$rootScope.authToken = authToken;
		var currentRole = $sessionStorage.user.roles[0].name
		$http.get('/user',{params : {role:currentRole}}).success(function(data) {
		      if (data.username) {
		        $rootScope.username = data.username;
		        $rootScope.user = data; 
		        $sessionStorage.user = data; 
		        $rootScope.authenticated = true
		        $location.path(originalPath);
		      } else {
		      }
		    }).error(function() {
		      $rootScope.authenticated = false;
		      $location.path("/login");
		    });
	}else {
		$location.path("/login");
	}
	$rootScope.initialized = true;
});