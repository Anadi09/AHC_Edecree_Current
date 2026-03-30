var edmsApp = angular.module('EDMSApp', []);

edmsApp.controller('ecourtCreateUserController', ['$scope','$http','$controller', function ($scope,$http,$controller) {
debugger;

var urlBase="/dms/";

$scope.saveUserDetail=[];



	$scope.checkusername =  function(){
		debugger;
		alert($scope.userCreationForm.username);
		
	};
	

	$scope.saveUserDetail = function(userCreationForm){
		debugger;
		$scope.entity = userCreationForm;
		console.log($scope.entity);
		alert($scope.entity.username);
		$http.post(urlBase+'ecourt/createaccount',$scope.entity).
		success(function(data){
			}).error(function(data){
				
			})
		}
        
	
	}]);