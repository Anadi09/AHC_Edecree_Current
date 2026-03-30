var EDMSApp = angular.module("EDMSApp", ['ui.bootstrap','smart-table','angularUtils.directives.dirPagination']);

EDMSApp.controller('ReportController',['$scope','$http',function ($scope, $http) {
	var urlBase="/dms/";
	$scope.downloadhistory = [];
    $scope.pageno = 1;
    $scope.total_count = 0;
    $scope.itemsPerPage = 10; 
    $scope.getData = function(pageno){ 
    	$scope.pageno=pageno;     
    	$http.get(urlBase+'reports/getdownloadhistory',{params:{'itemsPerPage':$scope.itemsPerPage,'pagenumber':$scope.pageno}}).success(function(response){ 
         $scope.downloadhistory = response.modelList;
         $scope.total_count = response.data;
        });
    };
    $scope.setFiles=function(data){
		
		$http.get(urlBase+'casefile/getdownloadedfiles/'+data.dr_id).success(function (data) {
	    	$scope.files=data.modelList;
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting files");
	      });
	}

}]);