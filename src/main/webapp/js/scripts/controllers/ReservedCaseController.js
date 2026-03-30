var edmsApp = angular.module("EDMSApp", ['ngMask','ui.bootstrap']);
edmsApp.controller("ReservedCaseController",['$scope','$http', function($scope,$http) {
	var urlBase="/dms/";
	$scope.model={};
	$scope.reservedCase={};
	  
	$scope.getList=function(){
		getData();
	}
	function getData(){	
		$http.get(urlBase+'reserve/getAllReserveCase')
			.success(function(data) {
				$scope.reservedCase=data.modelList;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
	};
	
	 $scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
		$scope.onFileSelect = function ($files) {
        $scope.uploadProgress = 0;
        $scope.selectedFile = $files;
    };

	
	$scope.remove=function(id){
		  $http.get(urlBase+'reserve/removeReservedCase/'+id).success(function (data) {
			  location.reload(true);
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
	  }
		
}]);
