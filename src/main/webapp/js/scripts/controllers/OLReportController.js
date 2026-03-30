var edmsApp = angular.module("EDMSApp", ['ui.bootstrap']);
edmsApp.controller("OLReportController",['$scope','$http', function($scope,$http) {
	var urlBase="/dms/";
	$scope.model={};
	$scope.olrsearch={};
	  
	$scope.getList=function(){
		getData();
	}
	function getData(){	
		$http.get(urlBase+'olreport/getall')
			.success(function(data) {
				$scope.olreports=data.modelList;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
	};
		$scope.onFileSelect = function ($files) {
        $scope.uploadProgress = 0;
        $scope.selectedFile = $files;
    };

	$scope.viewFile=function(id){
		  window.open(urlBase+"olreport/viewdocument/"+id,"_blank");
		  
		  console.log("$scope.olrsearch.ol_no --"+$scope.olrsearch.ol_no);
		  if($scope.olrsearch.ol_no!= null || $scope.olrsearch.ol_year!=null || $scope.olrsearch.ol_created!= null)
		    {
			  getsearchOLR();
		    }
		  else
			  {
			   getData();
			   location.reload(true); 
			  }
		  //location.reload(true);
		  
		  //getsearchOLR();
	  }
	

	$scope.resetStatus=function(id){
		 /* window.open(urlBase+"olreport/resetStatus/"+id);
		  location.reload(true);
		  
		  */
		  
		  $http.get(urlBase+'olreport/resetStatus/'+id).success(function (data) {
		    	/*if(data.response=="TRUE")
		    		$scope.olreports=data.modelList;
		    	else
		    		$scope.olreports=[];*/
			  location.reload(true);
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
		  
		  
		  
		  
	  }
	
	
	$scope.remove=function(id){
		 /* window.open(urlBase+"olreport/resetStatus/"+id);
		  location.reload(true);
		  
		  */
		  
		  $http.get(urlBase+'olreport/remove/'+id).success(function (data) {
		    	/*if(data.response=="TRUE")
		    		$scope.olreports=data.modelList;
		    	else
		    		$scope.olreports=[];*/
			  location.reload(true);
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
		  
		  
		  
		  
	  }
	
	
	$scope.open = function($event,type) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    
	    if(type=="stageDate")
	    	$scope.stageDate= true;
	    
	};
	
	
	$scope.dateOptions = {
			formatYear : 'yy',
			startingDay : 1
		};
	
	  $scope.searchOLR=function() 
	  {
		  $http.post(urlBase+'olreport/searchOLR',$scope.olrsearch).success(function (data) {
		    	if(data.response=="TRUE")
		    		$scope.olreports=data.modelList;
		    	else
		    		$scope.olreports=[];
		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
	  }
	  
	  

	  function getsearchOLR()
		{
			$http.post(urlBase+'olreport/searchOLR',$scope.olrsearch).success(function (data) {
		    	if(data.response=="TRUE")
		    	{
		    		$scope.olreports=data.modelList;
		    		
		    	}
		    	else
		    		$scope.olreports=[];
		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
			
			
		}
		
}]);
