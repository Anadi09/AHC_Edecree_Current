var EDMSApp = angular.module("EDMSApp", ['ui.bootstrap','smart-table','angularUtils.directives.dirPagination']);

EDMSApp.controller('SearchController',['$scope','$http','$q',function ($scope, $http,$q) {
	var urlBase="/dms/";
	$scope.pageno=1;
	 $scope.itemsPerPage = 10;
	$scope.searchmodel = {};
    $scope.total_count = 0;
    $scope.caseTypes=[];
    $scope.lcCaseTypes=[];
    $scope.hcCaseTypes=[];
    $scope.getCaseTypes=function(){
    	
		  $http.get(urlBase+'master/getcasetypes').success(function (data) {
		    		$scope.caseTypes=data.modelList;
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	 }
    $scope.getHighCourtCaseTypes=function(){
    	
		  $http.get(urlBase+'master/getcasetypes').success(function (data) {
		    		$scope.hcCaseTypes=data.modelList;
		    		updateCaseTypes();
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	 }
    $scope.getDistricts=function(){
    	
		  $http.get(urlBase+'master/getdistricts').success(function (data) {
		    		$scope.districts=data.modelList;		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting districts");
		      });
	 }
    $scope.getLowerCourtCaseTypes=function(){
		  $http.get(urlBase+'master/getlccasetypes').success(function (data) {
		    		$scope.lcCaseTypes=data.modelList;
		    		updateCaseTypes();
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	 }
    function updateCaseTypes(){
    	
    	if($scope.searchmodel.courtType=='h' && $scope.hcCaseTypes.length>0)
    	{
    		$scope.caseTypes=$scope.hcCaseTypes;
    	}
    	if($scope.searchmodel.courtType=='l' && $scope.lcCaseTypes.length>0)
    	{
    		$scope.caseTypes=$scope.lcCaseTypes;
    	}
    }
    $scope.getIOCaseTypes=function(){
    	if($scope.searchmodel.courtType=='h' && $scope.hcCaseTypes.length==0){
    		this.getHighCourtCaseTypes();
    	}
    	if($scope.searchmodel.courtType=='l' && $scope.lcCaseTypes.length==0){
    		this.getLowerCourtCaseTypes();
    	}
    	if($scope.hcCaseTypes.length>0 && $scope.lcCaseTypes.length>0){
    		updateCaseTypes();
    	}
    };
    $scope.getJudges=function(){
		  $http.get(urlBase+'master/gethighcourtjudges').success(function (data) {
		    		$scope.judges=data.modelList;		    	
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	 }
    $scope.searchByCaseDetail = function(pageno){
    	
    	$scope.showLoader =true;
    	$scope.showStatus =false;
    	$scope.caseFileDetails=[];
    	$scope.total_count =0;
    	$scope.searchmodel.pageNumber=pageno;
    	$scope.searchmodel.itemsPerPage=$scope.itemsPerPage;
    	$http.post(urlBase+'search/getByCaseDetail',$scope.searchmodel).success(function(response){ 
         $scope.caseFileDetails = response.modelList;
         if($scope.caseFileDetails.length > 0){
        
         }
         else {
        	 $scope.showStatus =true;
         }
         $scope.showLoader =false;
         
         $scope.total_count = response.data;
        });
    };
    
    $scope.searchByParty = function(pageno){ 
    	$scope.showLoader =true;
    	$scope.showStatus =false;
    	$scope.caseFileDetails=[];
    	$scope.total_count =0;
    	$scope.searchmodel.pageNumber=pageno;
    	$scope.searchmodel.itemsPerPage=$scope.itemsPerPage;
    	$http.post(urlBase+'search/getByParty',$scope.searchmodel).success(function(response){ 
         $scope.caseFileDetails = response.modelList;
         
         $scope.caseFileDetails = response.modelList;
         if($scope.caseFileDetails.length > 0){
        
         }
         else {
        	 $scope.showStatus =true;
         }
         $scope.showLoader =false;
         $scope.total_count = response.data;
        });
    };
    $scope.searchByCounsel = function(pageno){
    	$scope.showLoader =true;
    	$scope.showStatus =false;
    	$scope.caseFileDetails=[];
    	$scope.total_count =0;
    	$scope.searchmodel.pageNumber=pageno;
    	$scope.searchmodel.itemsPerPage=$scope.itemsPerPage;
    	$http.post(urlBase+'search/getByCounsel',$scope.searchmodel).success(function(response){ 
         $scope.caseFileDetails = response.modelList;
         if($scope.caseFileDetails.length > 0){
             
         }
         else {
        	 $scope.showStatus =true;
         }
         $scope.showLoader =false;
         $scope.total_count = response.data;
        });
    };
    $scope.searchByJudge = function(pageno){
    	$scope.caseFileDetails=[];
    	$scope.total_count =0;
    	$scope.searchmodel.pageNumber=pageno;
    	$scope.searchmodel.itemsPerPage=$scope.itemsPerPage;
    	$http.post(urlBase+'search/getByJudge',$scope.searchmodel).success(function(response){ 
         $scope.caseFileDetails = response.modelList;
         $scope.total_count = response.data;
        });
    };
    $scope.searchByImpugned = function(pageno){
    	$scope.caseFileDetails=[];
    	$scope.total_count =0;
    	$scope.searchmodel.pageNumber=pageno;
    	$scope.searchmodel.itemsPerPage=$scope.itemsPerPage;
    	$http.post(urlBase+'search/getByImpugnedDetail',$scope.searchmodel).success(function(response){ 
         $scope.caseFileDetails = response.modelList;
         $scope.total_count = response.data;
        });
    };
    $scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_new");
	  }

}]);