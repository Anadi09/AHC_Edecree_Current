/*
 * 
 */

 
var edmsApp = angular.module("EDMSApp", ['smart-table']);

edmsApp.directive('loading', ['$http', function ($http) {
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
/**
 * Controller in index.jsp
 */
edmsApp.controller("repositoryController",['$scope','$http', function($scope,$http) {
	var urlBase="/dms/";
	
	$scope.errorlist=[];
	
	getRepositories();
	function getRepositories()
	{
		$http.get(urlBase+'repository/getrepositories').
        success(function (data) {
        	$scope.masterdata = data;
        	$scope.displayedCollection = [].concat($scope.masterdata);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	 
	$scope.create=function(masterentity){
		$scope.entity=masterentity;
		var response=$http.post(urlBase+'repository/create',$scope.entity);
		response.success(function (data, status, headers, config) {
			if(data.response=="FALSE"){
				$scope.errorlist=data.dataMapList;
			}else{
			bootbox.alert("Successfully created repository");	
			$scope.masterdata.push(data.modelData);
			$scope.displayedCollection = [].concat($scope.masterdata);
			$("#repositoryCreate").modal('hide');	
			}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in creating repository");
        });
	};
	$scope.setMasterdata = function(data) {
		console.log("SET DATA");
		console.log(data);		
		$scope.repository= angular.copy(data);
		$scope.errorlist=[];
		
	};
	$scope.update=function(masterentity){
		$scope.entity=masterentity;
		var response=$http.post(urlBase+'repository/update',$scope.entity);
		response.success(function (data, status, headers, config) {
			if(data.response=="FALSE"){
				$scope.errorlist=data.dataMapList;
			}else{
			bootbox.alert("Successfully updated repository");	
			$("#repositoryEdit").modal('hide');	
			}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in creating repository");
        });
	};
	
}]);