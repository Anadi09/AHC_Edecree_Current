var EDMSApp = angular.module("EDMSApp", ['ngFileUpload','ngMask','ui.bootstrap']);

EDMSApp.controller('CaseStempReportController',['$scope','$http','Upload',function ($scope, $http,Upload) {
	  var urlBase="/dms/";
	  $scope.picFile='';
	  $scope.caseList=[];
	  getCaseListByUser();
	  
	  
	  
	  
	  $scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
	  
	  function getCaseListByUser(){
		  $http.get(urlBase+'casefile/getCaseListByUser').success(function (data) {
		    		$scope.caseList=data.modelList;		    	
		    	console.log($scope.caseList);
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	 
	  
}]);