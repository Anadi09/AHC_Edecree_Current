/*
 * 
 */

 
var edmsApp = angular.module("EDMSApp", ['smart-table','ui.bootstrap']);

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
edmsApp.controller("metaTemplateController",['$scope','$http', function($scope,$http) {
	var urlBase="/dms/";
	
	$scope.errorlist=[];	
	getAll();
	$scope.index=-1;
	
	function getAll()
	{
		$http.get('getall').
        success(function (data) {
        	$scope.masterdata = data.modelList;
        	$scope.displayedCollection = [].concat($scope.masterdata.modelList);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	$scope.setData=function(data,index){
		$scope.index=index;
		$scope.masterentity= data;
	}; 
	$scope.save=function(){
		$scope.masterentity.isDeleted=0;
		var response=$http.post('save',$scope.masterentity);
		response.success(function (data, status, headers, config) {
			if(data.response=="FALSE"){
				$scope.errorlist=data.dataMapList;
			}else{
				if($scope.index==-1){
					bootbox.alert("Successfully Created");	
					$scope.masterdata.push(data.modelData);
					$scope.displayedCollection = [].concat($scope.masterdata);
					$("#create").modal('hide');	
				}else{
					bootbox.alert("Successfully Updated");	
					$scope.masterdata.push(data.modelData);
					$scope.displayedCollection = [].concat($scope.masterdata);
					$("#create").modal('hide');	
				}
				
			}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in creating repository");
        });
	};
	
}]);