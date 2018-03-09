angular.module('angApp.directives',[])
	.directive('userInfo',[function(SYSTEM_URL){
		return{
			restrict:'E',
			transclude:true,
			scope:{
				user :'=',
				dirTitle :'@',
				index:'@',
				addUserinformation:'&'
			},
			link:function(scope,element,attrs){
			},
			controller:function($scope,$element){
				console.log($scope.index)
				if($scope.index / 2){
					console.log($element.html());
				}
				$scope.addUserinformation();
			},
			templateUrl:'views/partial/userInfoDirectives.html'
			}
	}]);