'use strict';

angular.module('angApp.controllers',['angApp.services'])
.controller('loadPageOneCtrl',['$scope',function($scope){
	console.log('I am inside LoadPageOneCtrl');
	
	$scope.user={name:'Ashish',location:'Mumbai',newUser:'new'};
	
	$scope.changeScopeData = function(){
	console.log("hi changeScopeData");
	$scope.user.name="Ketan";
	}
}])
.controller('loginController',['$rootScope','$scope','$http','$location','$cookieStore','$sessionStorage',
                               function($rootScope, $scope, $http, $location, $cookieStore, $sessionStorage) {

	console.log('I am inside loginController');
	  var authenticate = function(credentials) {
    	  var dataObj = {
					userDtos : [{
						username: $scope.credentials.username,
						password: $scope.credentials.password,
						roles: [$scope.credentials.role]
				}]
			};
    	  
        	$scope.rememberMe = true;
    		$http.post('/authenticate', dataObj).success(function(authenticationResult) {
    					var authToken = authenticationResult.token;
    					$rootScope.authToken = authToken;
    					if ($scope.rememberMe) {
    						//$cookieStore.put('authToken', authToken);
    					}
    					$scope.getLoggedInUser();	
		    }).error(function() {
   			      $rootScope.authenticated = false;
   			      $rootScope.error = "Invalid credentials"
		    });
      }  

	  $scope.credentials = {};
	  $scope.login = function() {
	      authenticate($scope.credentials);
	  }
	  
	  $scope.getLoggedInUser = function() {
	    	
	    	var headers = {Authorization : $rootScope.authToken }
	  	    	
	  	    	$scope.dataLoading = true 
	  	    	$http.get('api/user', {headers : headers}).success(function(data) {
	  	    		if (data.username) {
	  			        $rootScope.username = data.username;
	  			        $rootScope.user = data;
	  			        $sessionStorage.user = data ;
	  			        $rootScope.authenticated = true;
	  			      } else {
	  			      }
	  	    		
	  	  		  $scope.dataLoading = false; 
	  	  		$location.path("/");
	  	    	}).error(function(data,status) {
	  			      $rootScope.authenticated = false;
	  			      $scope.dataLoading = false;
	  			      $rootScope.error = "Invalid credentials";
	  			      $location.path("/login");
	  	    	});
	      }
	  
	  $scope.getAllRoles = function(){
	    	
	    	var url = "api/role/getallroles";

	        var request = $http({
	                    			method: 'GET',
	                    			url: url,
	                			}
	           				);

	        request.success(
	            function(data) {
	            	console.log("roles: ",data);
	            	$rootScope.allroles = data;
	            	$sessionStorage.allroles = data;
	            }
	        );

	        request.error(
	            function(data) {
	            	$rootScope.error = data.errorMessage;
	            }
	        );
	    };
}])
.controller('Mycontroller',function MyController($scope,$q,$timeout){
	var standAlone1 = function(val){
		var defer = $q.defer();
		$timeout(function (){
			defer.resolve(val*2);
		},2000)
		return defer.promise;
	},
	standAlone2 = function(val){
		var defer = $q.defer();
		$timeout(function(){
			defer.resolve(val/2);
		},2000);
		return defer.promise;
	};
	$q.all([standAlone1(100),standAlone2(200)]).
	then(function(data){
		console.log("data",data);
	},function(error){
		console.log("error",error);
	});
})
.controller('userController', ['$scope', '$http', '$log','$location', '$rootScope', function($scope, $http, $log,$location,$rootScope) {
	
    $scope.add = function(userData) {
        var url = "api/user/create";

        var request = $http({
                    			method: 'POST',
                    			url: url,
                    			data:{
                    				userDtos : [{
	                    							username: userData.username,
		                    						password: userData.password,
		                    						firstName: userData.firstName,
		                    						lastName: userData.lastName,
		                    						email: userData.email
                    						}]
        			           		}
                			}
           				);

        request.success(
            function(data) {
            	$rootScope.success = "Successfully registered user ";
            	$rootScope.userToRegister = {};
            	$location.path("/register");
            }
        );

        request.error(
            function(data,status) {
            	$location.path("/register");
            	$rootScope.error = data.errorMessage;
            }
        );

    },
    
    $scope.update = function(userData) {
        var url = "api/user/update";
        console.log(userData.roles);
        var roles = [];
//        roles.push(userData.roles);
        var request = $http({
                    			method: 'POST',
                    			url: url,
                    			data:{
                    				userDtos : [{
			                    					username: userData.username,
			                						password: userData.password,
			                						firstName: userData.firstName,
			                						lastName: userData.lastName,
			                						email: userData.email,
			                						roles : userData.roles
                    						}]
        			           		}
                			}
           				);

        request.success(
            function(data) {
            	$location.path("/updateUser");
            	$rootScope.success = "Successfully updated profile ";
            }
        );

        request.error(
            function(data) {
            	$location.path("/updateUser");
            	$rootScope.error = data.errorMessage;
            }
        );
    } ,
    
    $scope.refreshData = function(){
    	var url = "api/user/getusers";

        var request = $http({
                    			method: 'GET',
                    			url: url,
                			}
           				);

        request.success(
            function(data) {
                $scope.userInfo = data;
            }
        );

        request.error(
            function(data) {
            	$rootScope.error = data.errorMessage;
            }
        );
    },
    
    $scope.deleteUser = function(userData){
    	
    	var url = "api/user/delete";

        var request = $http({
                    			method: 'PUT',
                    			url: url,
                    			data:{
                    				userDtos : [{
			                    					username: userData.username,
			                						password: userData.password,
			                						firstName: userData.firstName,
			                						lastName: userData.lastName,
			                						email: userData.email
                    						}]
        			           		}
                			}
           				);

        request.success(
            function(data) {
            	$location.path("/manageUser");
            	$rootScope.success = "Successfully deleted profile";
            }
        );

        request.error(
            function(data) {
            	$location.path("/manageUser");
            	$rootScope.error = data.errorMessage;
            }
        );
    };
    
}])
.controller('userRoleController',['$scope', '$http', '$log','$location', '$rootScope', 
                                  function($scope, $http, $log,$location,$rootScope) {

	$scope.addRole = function(roleData){
    	
    	var url = "api/role/create";

        var request = $http({
                    			method: 'POST',
                    			url: url,
                    			data:{
                    				roleDtos : [{
                    								name: roleData.rolename
                    						}]
        			           		}
                			}
           				);

        request.success(
            function(data) {
            	$location.path("/addRole");
            	$rootScope.success = "Successfully added new role";
            }
        );

        request.error(
            function(data) {
            	$location.path("/addRole");
            	$rootScope.error = data.errorMessage;
            }
        );
    };
}]);
