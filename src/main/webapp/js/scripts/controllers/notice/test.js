var app=angular
    .module("applicationNoticeModule", []);

    app.controller("applicationNoticeController", function($scope,$http) {
      $scope.applicationNotice = {};
      
      $scope.submitForm = function() {
      	console.log($scope.applicationNotice);
        	  $http
              .post("application_notice",JSON.stringify($scope.applicationNotice))
              .then(function(response) {
      			$scope.responseData = response;
              }, function(response){
                  console.log("Exception details: " + JSON.stringify(response));
              });
          };
    });