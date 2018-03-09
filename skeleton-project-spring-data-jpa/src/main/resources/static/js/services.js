'use strict';

angular.module('angApp.services', ['ngResource'])

.factory('UserService', function($http) {
	return {
		'getUser' : function (headers) {
			$http.get('controller/user', {headers : headers});
		}
	};
	/*
	return $resource('controller/user/:action', {},
			{
				authenticate: {
					method: 'POST',
					params: {'action' : 'authenticate'},
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				},
			}
		);
		*/
});