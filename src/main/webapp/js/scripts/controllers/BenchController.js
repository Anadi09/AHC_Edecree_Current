var EDMSApp = angular.module("EDMSApp", ['ngFileUpload','ngMask','ui.bootstrap']);

EDMSApp.controller('BenchController',['$scope','$http','$sce','Upload',function ($scope, $http,$sce,Upload) {
	  var urlBase="/dms/";
	  $scope.picFile='';
	  $scope.caseTypes=[];
	  $scope.search={};
	  $scope.subdocument={};
	  $scope.offRep={};
	  $scope.dmsCaseData={};
	
	
	  $scope.courtList =[];
	  
	  $scope.court={};
	  
	  $scope.subBeches={};
	  
	  $scope.editablerow = '';
	  
	  
	    $scope.formats = ['dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate'];
		$scope.format = $scope.formats[0];
	    $scope.today = function() {
	    	$scope.model.cl_dol = new Date();
	    	$scope.clmodel.cl_dol = new Date();
	    	$scope.report.cl_date = null;
	    	$scope.maxDate = new Date()
		};
	  
	  $scope.open = function($event,opened) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[opened] = true;
		  };
	  
	  $scope.setMasterdata=function(data){
		  $scope.subBeches.sb_cm_mid=data.cm_id;
		  console.log( $scope.subBeches);
	  }
	  
	  
	  $scope.updateBench = function (indx,courtmaster) {
		  console.log("Court Master",courtmaster);
		  $http.post(urlBase+'bench/updateCourt',courtmaster).success(function (data) {
		    	if(data.response=="TRUE"){
		    		console.log("data daaaaaa",data);
		    		 $scope.courtList[indx] = angular.copy(data.modelData);
		        $scope.reset();  
		    	}
		    	else if(data.response == "FALSE") {
		    		console.log("Dataaaaaaaaaaa",data);
		    		/*$scope.courtmaster1 =data.modelData;*/
		    		bootbox.confirm({
		    		    title: "Please Read The Content",
		    		    message: "The Bench Id - "+data.modelData.cm_bench_id+ " you want to update is already exists in "+data.modelData.cm_name
		    		            +" if you want to update it than please click confirm else click cancel",
		    		    buttons: {
		    		        cancel: {
		    		            label: '<i class="fa fa-times"></i> Cancel'
		    		            	
		    		        },
		    		        confirm: {
		    		            label: '<i class="fa fa-check"></i> Confirm'
		    		        }
		    		    },
		    		    callback: function (result) {
		    		        console.log('This was logged in the callback: ' + result);
		    		        if(result){
		    		        	courtmaster.updateFlag =true;
		    		        	 $http.post(urlBase+'bench/updateCourt',courtmaster).success(function (data) {
		    		 		    	if(data.response=="TRUE"){
		    		 		    		console.log("data daaaaaa",data);
		    		 		    		/* $scope.courtList[indx] = angular.copy(data.modelData);*/
		    		 		    		 $scope.reset();  
		    		 		    		getAllCourts();
		    		 		       
		    		 		    	}
		    		        	 }).
		    				      error(function(data, status, headers, config) {
		    				      	console.log("Error in getting tree data");
		    				      });
		    		        	
		    		        	
		    		        }
		    		        else {
		    		        	getAllCourts();
		    		        }
		    		    }
		    		});
		    	}
		    	else
		    		{
		    		console.log("Some problem")
		    		}
		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
		  
		        $scope.courtList[indx] = angular.copy($scope.editablerow);
		        $scope.reset();
	    };
	    $scope.reset = function(){
	    	$scope.editablerow = [];
	    	}
	  
	  $scope.editBench = function(content) { 
		  console.log("dataaaaaaaaaaaaaaaa",content);
	        $scope.editablerow = angular.copy(content);   
		
	 }
	  
	 
	  $scope.addBench=false;
	  $scope.newBench= function(){
		  $scope.addBench=true;
	  }
	  
	  $scope.benchAdd= function(row){
		  console.log(row);
		  console.log($scope.subBeches);
	  }
	  
	  $scope.getData = function(content) { 
		    if (content.cm_id == $scope.editablerow.cm_id) return 'edit';
		        else return 'view';
		 }
	  
	  
		$scope.deleteSubBench=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'bench/deleteSubBenches/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert(" Bench Deleted Successfully!");
					getAllCourts();
				   }
				
			});	
		}
		
		}
	  
	  
	  $scope.newBench = function () {
		  $http.post(urlBase+'bench/addBenches',$scope.subBeches).success(function (data) {
		    	if(data.response=="TRUE"){
		    		$('#user_Modal').modal('hide');		
		    		console.log("data daaaaaa",data);		    		
		    	}
		    	else
		    		{
		    		console.log("Some problem")
		    		}
		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
		  
		       
	    };
	  
	  
	  function getAllCourts(){
		  $http.get(urlBase+'bench/getAllCourts').success(function (data) {
		    		$scope.courtList=data.modelList;	
		    		console.log("dataaaaaaaaaaaaaaaaa",$scope.courtList);
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	
	 
	  
	
	  
	 /* getAllCourts();*/
	  
	  
	    $scope.court_create = function(court) {
	    	
	    		var response = $http.post(urlBase+'bench/create',$scope.court);		 
					response.success(function(data, status, headers, config) {					
						if(data.response=="FALSE"){					
							alert(data.data);
						}else{		
							$('#user_Modal').modal('hide');		
							
							alert(data.data);
											
						}
						
				});
				response.error(function(data, status, headers, config) {
					alert( "Error");
				});
	    	
		};
	  
	

	  
	 
	
	
	
		  
		
		  
		  
		 
		  
	
	  
	  $scope.loading = true;
	  
	
	 
	  
	 

	
			
				
			
			
	  //
	  /*END*/  
	  
	  

	  
}]);