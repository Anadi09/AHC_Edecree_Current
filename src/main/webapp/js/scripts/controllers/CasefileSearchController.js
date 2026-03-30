
var EDMSApp = angular.module('EDMSApp', ['ui.bootstrap']);
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

EDMSApp.controller('CasefileSearchController',['$scope','$http',function ($scope, $http) {
	var urlBase="/dms/";
	$scope.users=[];
	getCaseTypes();
	$scope.model={};
	
	 function getCaseTypes()
	 {
			$http.get(urlBase+'master/getcasetypes').
	        success(function (data) {
	        	$scope.caseTypeList=data.modelList;
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
	 };
	 
	 $scope.searchCaseFile=function(){
		 
		 $scope.showLoader =true;
	    	$scope.showStatus =false;
		 $http.get(urlBase+'amendment/searchCaseFile', {params : {'case_year' :$scope.model.fd_case_year,'case_type' :$scope.model.fd_case_type,'case_no' :$scope.model.fd_case_no}}).
	        success(function (data) {
	        	
	        	if(data.modelList.length>0){
	        	$scope.caseFileList=data.modelList;
	        	if($scope.caseFileList.length > 0){
	                
	            }
	            else {
	           	 $scope.showStatus =true;
	            }
	            $scope.showLoader =false;
	        	}else{
	        		  $scope.showLoader =false;
	        		  $scope.showStatus =true;
	        	}
	        	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
		 
	 }
	 $scope.viewAmendment=function(data){
		 console.log(data);
		 window.open(urlBase+"amendment/manage/"+data.fd_id,'_self');
	 }
	  $scope.viewAdvocates=function(data){
		  $scope.casefile=data;
		  var response =$http.post(urlBase+'amendment/getadvocates',$scope.casefile);
			response.success(function(data, status, headers, config){
				
				   if(data.response=="TRUE"){
					$scope.users=data.modelList;
				   }
				   else{					   
					alert(data.data);
				   }  
	  });
	  
	  }
	
}]);