
var EDMSApp = angular.module('EDMSApp', ['ui.bootstrap']);

EDMSApp.controller('AmendmentController',['$scope','$http',function ($scope, $http) {
	var urlBase="/dms/";
	$scope.users=[];
	$scope.userAdvocate={};
	$scope.count = 0;
	$scope.searcheduser={};
	/*getApplicationTypes();*/
	$scope.model={};
	$scope.fd_id= $('#fd_id').val();
	getAdvocates();
	getAmendments();
	$scope.amendments=[];
	$scope.searchuser=false;
	function getApplicationTypes()
	 {
		 $http.get(urlBase+'amendment/getApplicationTypes').
	        success(function (data) {
	        	$scope.applicationTypeList=data.modelList;
	        	console.log(data.modelList);
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting ApplicationTypes data");
	        });
	 };
	 
	 function getAdvocates(){
		  var response =$http.get(urlBase+'amendment/getadvocates/'+$scope.fd_id);
			response.success(function(data, status, headers, config){				
				   if(data.response=="TRUE"){
					$scope.users=data.modelList;
				   }
				    
	  });
	  
	  }
	 function getAmendments(){
		  var response =$http.get(urlBase+'amendment/getamendments/'+$scope.fd_id);
			response.success(function(data, status, headers, config){				
				   if(data.response=="TRUE"){
					$scope.amendments=data.modelList;
				   }
			});	  
	  }
	 $scope.searchCaseFile=function(){
		 
		 $scope.searchuser=true;
		 $scope.searcheduser=[];
		 $http.get(urlBase+'amendment/searchuser', {params : {'username' :$scope.search.username}}).
	        success(function (data) {
	        	$scope.useNotFoundFlag=false;
	        	if(data.response=="TRUE"){
	        		$scope.searcheduser=data.modelData;
	        	}	else{
	        		$scope.count++;
	        		$scope.useNotFoundFlag=true;
	        		if($scope.count>1){
	        			alert("Search by using Advocate Id");
	        		}
	        	}             	
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });		 
	 }
	 $scope.setUser=function(user){
		 getApplicationTypes()
		 console.log($scope.fd_id);
		 $scope.user=user;
	 }
	 $scope.addUser=function(user){
		 $scope.userObj=user;
		 $scope.userObj.flag=true;
		 $scope.searcheduser = $scope.userObj;
		 
		 $scope.searcheduser = $scope.users.push($scope.userObj);
	 }
	 $scope.removeUser=function(user,index){
		 $scope.userObj=user;
			//	 $scope.searchedusers.push($scope.userObj);
				 $scope.users.splice(index,1);
	 }
	 $scope.addAmendment=function(row){
		 $scope.amendment={'am_type':'A','am_at_mid':row[2],'am_document_no':row[3],'am_document_year':row[4],'am_fd_mid':$scope.fd_id,'am_um_mid':$scope.user.um_id};
		 
		 	$http.post(urlBase+'amendment/addamendment',$scope.amendment).success(function (data) {
		    	if(data.response=="TRUE"){
		    		$scope.amendments.push(data.modelData);
		    	}
		    	alert(data.data);
		    	window.location.reload();
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in adding amendment applicaion");
		      });
	 }
	 $scope.allowPetition=function(user){
		 $scope.user=user;
		 $scope.amendment={'am_type':'P','am_fd_mid':$scope.fd_id,'am_um_mid':$scope.user.um_id};
		 
		 	$http.post(urlBase+'amendment/addamendment',$scope.amendment).success(function (data) {
		    	if(data.response=="TRUE"){
		    		$scope.amendments.push(data.modelData);
		    		
		    	}
		    	alert(data.data);
		    	$scope.userObj.flag=false;
		    	window.location.reload();
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in adding amendment applicaion");
		      });
	 }
	 $scope.deleteAmendment=function(am_id){
		  var response =$http.get(urlBase+'amendment/deleteamendment/'+am_id);
			response.success(function(data, status, headers, config){				
				   if(data.response=="TRUE"){
					alert(data.data);
				   }
				   else{					   
					alert(data.data);
				   }
				   window.location.reload();
			});	  
	  }
}]);