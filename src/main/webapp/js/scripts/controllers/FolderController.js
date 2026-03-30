/*
 * 
 */

 
var edmsApp = angular.module("EDMSApp", ['smart-table','ui.bootstrap','treeControl']);

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
edmsApp.controller("folderController",['$scope','$http', function($scope,$http) {
	var urlBase="/dms/";
	$scope.folder={};
	getFoldersdata();
	
	$scope.selectedNode="";
    $scope.treedata=[];
    $scope.caseTypes=[];
    $scope.benchCodes=[];
    $scope.opts = {
        nodeChildren: "childrens"
    };
    $scope.showSelected = function(sel)
    {
        $scope.selectedNode = sel;
    };

    
	function getFoldersdata()
	{		
		$http.get(urlBase+'folder/getallfolders').
        success(function (data) {
        	
        	$scope.masterdata = data;
        	$scope.displayedCollection = [].concat($scope.masterdata);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	$scope.getRepositories=function(){
		$scope.folder={};
		$http.get(urlBase+'repository/getrepositories').
        success(function (data) {
        	$scope.repositories = data;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
		
		getCasetypes();
	};
	$scope.getBenchCodes=function(){
		$http.get(urlBase+'folder/getbenchcodes').
        success(function (data) {
        	$scope.benchCodes = data;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting bench data");
        });
	};
	
	$scope.getCasetypes=function(){
		$http.post(urlBase+'folder/getcasetypes',$scope.folder).
        success(function (data) {
        	$scope.caseTypes = data;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting casetypes data");
        });
	};
	
	$scope.getFolderStructure=function(){
		$scope.entity={"rep_id":1};
		console.log($scope.entity);
		$http.post(urlBase+'folder/gettree',$scope.entity).
        success(function (data) {
        	$scope.treedata=data.treedata;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	
	$scope.setMasterdata = function(data) {
		console.log("SET DATA");
		console.log(data);		
		getFoldersdata();
		
		
		$scope.folder = angular.copy(data);
		
		$scope.folder.repository.name=data.repository.name;
		console.log($scope.folder.repository.name);
//		
//		
		
		$scope.errorlist=[];
		
	};
	
	
	$scope.create=function(folder){
		$scope.entity=folder;
		
		if($scope.selectedNode!=""){
			$scope.entity.parent_id=$scope.selectedNode.id;
		}
		$scope.entity.rep_id=1;
		$http.post(urlBase+'folder/create',$scope.entity).
        success(function (data) {
        	if(data.response=="FALSE"){
				$scope.errorlist=data.dataMapList;
					
			}else{
			bootbox.alert("Successfully created folder");	
			$scope.masterdata.push(data.data);
			$scope.displayedCollection = [].concat($scope.masterdata);
			$("#folderCreate").modal('hide');	
			}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	
	$scope.update=function(folder){
		$scope.entity=folder;
		
		if($scope.selectedNode!=""){
			$scope.entity.parent_id=$scope.selectedNode.id;
		}
		$http.post(urlBase+'folder/update',$scope.entity).
        success(function (data) {
        	if(data.response=="FALSE"){
				$scope.errorlist=data.dataMapList;
			}else{
			bootbox.alert("Successfully updated folder");	
			$scope.masterdata.push(data.modelData);
			$scope.displayedCollection = [].concat($scope.masterdata);
			$("#folderCreate").modal('hide');	
			}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
		
}]);