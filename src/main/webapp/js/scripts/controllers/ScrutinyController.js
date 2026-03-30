var EDMSApp = angular.module('EDMSApp', []);
EDMSApp.directive('loading', ['$http', function ($http) {
    return {
        restrict: 'A',
        link: function (scope, elm, attrs) {
            scope.isLoading = function () {
                return $http.pendingRequests.length > 0;
            };
            scope.$watch(scope.isLoading, function (v) {
                if (v) {
                    elm.show();
                } else {
                    elm.hide();
                }
            });
        }
    };
}]);





EDMSApp.controller('ScrutinyCtrl',['$scope','$http',function ($scope, $http) {
 
 
	var urlBase="/adminpanel/";
  
  	$scope.getCaseList=function(){
  		$http.get(urlBase+'scrutiny/getCaseList').success(function (data) {
    	$scope.count=data.data;
      	$scope.draftList=data.modelList;
    	  
      }).
      error(function(data, status, headers, config) {
      	console.log("Error in getting tree data");
      });
	};
  	$scope.previewCaseDetails=function(id){
		window.open(urlBase+"scrutiny/case/"+id,"_self");
	}
  	$scope.getCaveatList=function(){
	  	$http.get(urlBase+'scrutiny/getCaveatList').success(function (data) {
    	$scope.count=data.data;
      	$scope.draftList=data.modelList;    	  
      }).
      error(function(data, status, headers, config) {
      	console.log("Error in getting tree data");
      });
	}
  	$scope.previewCaveatDetails=function(id){
		window.open(urlBase+"scrutiny/caveat/"+id,"_self");
	}
  	$scope.getApplicationList=function(){
	  	$http.get(urlBase+'scrutiny/getApplicationList').success(function (data) {
    	$scope.count=data.data;
      	$scope.draftList=data.modelList;
    	  
      }).
      error(function(data, status, headers, config) {
      	console.log("Error in getting tree data");
      });
	}
  	$scope.previewApplicationDetails=function(id){
		window.open(urlBase+"scrutiny/application/"+id,"_self");
	}	
  
}]);