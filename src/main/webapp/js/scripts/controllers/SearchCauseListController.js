/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['smart-table','ui.bootstrap']);

//edmsApp.directive('fileModel', [ '$parse', function($parse) {
//    return {
//        restrict : 'A',
//        link : function(scope, element, attrs) {
//            var model = $parse(attrs.fileModel);
//            var modelSetter = model.assign;
//
//            element.bind('change', function() {
//                scope.$apply(function() {
//                    modelSetter(scope, element[0].files[0]);
//                });
//            });
//        }
//    };
//} ]);


/**
 * Controller in index.jsp
 */

edmsApp.controller("causeListController",['$scope','$http','$filter', function($scope,$http,$filter) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;
	$scope.search=false;
	$scope.getTransferFlag =false;
	$scope.model={};
    $scope.formats = ['dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate'];
	$scope.format = $scope.formats[0];
    $scope.today = function() {
    	$scope.model.cl_dol = new Date();
    	$scope.model.cl_dol = $filter('date')(new Date(),'yyyy-MM-dd');
	};
	
	
	
	$scope.today();
	
	$scope.clear = function () {
		$scope.model.cl_dol = null;
	};	

	 $scope.open = function($event,opened) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[opened] = true;
		  };
    	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	};
	getCauseListTypes();
	function getCauseListTypes() {
		var response = $http.get(urlBase+'causelist/getCauseListTypes');
		response.success(function(data, status, headers, config) {
			$scope.causeListTypes = data.modelList;
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	$scope.getList=function(){
		$scope.search=true;
		$http.post(urlBase+'causelist/getCauseList',$scope.model)
			.success(function(data) {
				$scope.masterdata=data.modelList;
				$scope.displayedCollection = [].concat($scope.masterdata);
			//$scope.SeasrchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
	};
	
	
	
	
	$scope.getListOnSearch = function(){	
		console.log("dataaaaa",$scope.getTransferFlag);
		$scope.showLoader =true;
    	$scope.showStatus =false;
		$scope.search=true;
		if($scope.getTransferFlag){
		$http.post(urlBase+'causelist/getCauseListOnSearch',$scope.model)
			.success(function(data) {
				$scope.masterdata=data.modelList;
				$scope.displayedCollection = [].concat($scope.masterdata);
				if($scope.displayedCollection.length > 0){
			        
		         }
		         else {
		        	 $scope.showStatus =true;
		         }
		         $scope.showLoader =false;
				
				
			//$scope.SearchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
		}
		else {
			$http.post(urlBase+'causelist/getCauseList',$scope.model)
			.success(function(data) {
				$scope.masterdata=data.modelList;
				$scope.displayedCollection = [].concat($scope.masterdata);
				if($scope.displayedCollection.length > 0){
			        
		         }
		         else {
		        	 $scope.showStatus =true;
		         }
		         $scope.showLoader =false;
			//$scope.SeasrchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
			
		}
	};
	$scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
	$scope.viewApplication=function(application_no,application_year,case_id){
		  window.open(urlBase+"casefile/applicationview/?app_no="+application_no+"&app_year="+application_year+"&case_id="+case_id,"_blank");
	  }
	
}]);
