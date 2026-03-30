
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

EDMSApp.controller('AllowToEfilingController',['$scope','$http',function ($scope, $http) {
	var urlBase="/dms/";
	$scope.users=[];
	getCaseTypes();
	$scope.model={};
	
	$scope.caseData ={};
	$scope.applicationData={};
	
	$scope.visible =true;
	$scope.appVisible =false;
	$scope.caseFileList=[];
	
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
	 
	 $scope.addCaseEfling=function(caseDetail) 
		{
		 $scope.loading = false;
		 console.log("In add case",caseDetail);
		 var response = $http.post(urlBase+'allowtoefiling/addCaseEflingForAllow',caseDetail);
		 response.success(function(data, status, headers, config){
			 
			   if(data.response=="TRUE"){
				   $scope.dmsCaseData=data.data;
				   $scope.caseFileList.push(data.modelData);
		        	alert(data.data);
		        	
		        	$scope.loading = true;
			   }
			   else{					   
				   alert("error ocurred  please check details");
		        	
		        	$scope.loading = true;
			   }  
		 });
		 
		}
	 
	 $scope.searchCaseFile=function(){
		 $scope.caseFileList=[];
		 $http.get(urlBase+'/allowtoefiling/searchCaseFileForAllow', {params : {'case_year' :$scope.model.fd_case_year,'case_type' :$scope.model.fd_case_type,'case_no' :$scope.model.fd_case_no}}).
	        success(function (data) {
	        	
	        	if(data.modelList.length>0){
	        	$scope.caseFileList=data.modelList;
	        	}else{
	        		bootbox.confirm({
	        		    title: "Case Not Found",
	        		    message: "Case not exists Do you want to create it",
	        		    buttons: {
	        		        cancel: {
	        		            label: '<i class="fa fa-times"></i> Cancel'
	        		        },
	        		        confirm: {
	        		            label: '<i class="fa fa-check"></i> Confirm'
	        		        }
	        		    },
	        		    callback: function (result) {
	        		    	if(result){
	        		    		$scope.addCaseEfling($scope.model);
	        		    		console.log('This was logged in the callback: ' + result);
	        		    	}
	        		        console.log('This was logged in the callback: ' + result);
	        		    }
	        		});
	        		
	        		
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
	 
	 $scope.showApplicationForm= function(){
		 console.log("app calledddd")
		 $scope.visible =false;
	 }
	 $scope.showCaseForm= function(){
		 console.log("case calledddd")
		 $scope.appVisible=false;
		 $scope.visible =true;
	 }
	 
	 $scope.allowApplication =function(data){
		 console.log("Allow application called",data);
		 $scope.appVisible=true;
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
	  
	  
	  $scope.submitCase=function()
	  {
		  console.log("case data  ",$scope.caseData);
		  bootbox.confirm({
  		    title: "Check Verification Code",
  		    message: "Please check your verification code again -"+$scope.caseData.ae_code +".",
  		    buttons: {
  		        cancel: {
  		            label: '<i class="fa fa-times"></i> Cancel'
  		        },
  		        confirm: {
  		            label: '<i class="fa fa-check"></i> Confirm'
  		        }
  		    },
  		    callback: function (result) {
  		    	if(result){
  		    		$http.post(urlBase+'allowtoefiling/saveCaseVerificationCode',$scope.caseData).success(function (data) {
  				    	if(data.response=="TRUE"){
  				    		$scope.caseData.ae_code =null;
  				    		console.log("api dataaaa",data);
  				    	bootbox.alert({
  				    	    message: "Code Saved Successfully",
  				    	    className: 'rubberBand animated'
  				    	});
  				    	}
  				    	else if(data.response == "already exists"){
  				    		bootbox.alert({
  	  				    	    message: "Verification code already exists",
  	  				    	    className: 'rubberBand animated'
  	  				    	});
  				    		
  				    	}
  				    	else{
  				    		console.log("api dataaaa",data);	
  				    	}
  				      }).
  				      error(function(data, status, headers, config) {
  				      	console.log("Error in getting tree data");
  				      });		
  		    		console.log('This was logged in the callback: ' + result);
  		    	}
  		        console.log('This was logged in the callback: ' + result);
  		    }
  		});
		    
	  }
	  
	  $scope.submitApplication=function()
	  {
		  console.log("case data  ",$scope.applicationData);
		  console.log("case data  ",$scope.caseFileList);
		  $scope.applicationData.ae_fd_mid = $scope.caseFileList[0].fd_id;
		  bootbox.confirm({
  		    title: "Check Verification Code",
  		    message: "Please check your verification code again -"+$scope.applicationData.ae_code +".",
  		    buttons: {
  		        cancel: {
  		            label: '<i class="fa fa-times"></i> Cancel'
  		        },
  		        confirm: {
  		            label: '<i class="fa fa-check"></i> Confirm'
  		        }
  		    },
  		    callback: function (result) {
  		    	if(result){
  		    		$http.post(urlBase+'allowtoefiling/saveApplicationVerificationCode',$scope.applicationData).success(function (data) {
  				    	if(data.response=="TRUE"){
  				    		$scope.applicationData.ae_code =null;
  				    		$scope.caseFileList =[];
  				    		$scope.model.fd_case_year=null;
  				    		$scope.model.fd_case_no=null;
  				    		$scope.appVisible =false;
  				    		console.log("api dataaaa",data);
  				    	bootbox.alert({
  				    	    message: "Code Saved Successfully",
  				    	    className: 'rubberBand animated'
  				    	});
  				    	}
  				    	else if(data.response == "already exists"){
  				    		bootbox.alert({
  	  				    	    message: "Verification code already exists",
  	  				    	    className: 'rubberBand animated'
  	  				    	});
  				    		
  				    	}
  				    	else{
  				    		console.log("api dataaaa",data);	
  				    	}
  				      }).
  				      error(function(data, status, headers, config) {
  				      	console.log("Error in getting tree data");
  				      });		
  		    		console.log('This was logged in the callback: ' + result);
  		    	}
  		        console.log('This was logged in the callback: ' + result);
  		    }
  		});
		    
	  }
	
}]);