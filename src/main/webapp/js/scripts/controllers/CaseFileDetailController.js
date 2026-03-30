var EDMSApp = angular.module("EDMSApp", ['ngFileUpload','ngMask','ui.bootstrap']);


EDMSApp.controller("CaseFileCtrl",	['$scope','$sce','$http','Upload',function ($scope,$sce, $http,Upload) {
	var urlBase="/dms/";
	$scope.subDocuments=[];
	$scope.index_fields=[];
	$scope.applications=[];
	$scope.applicationsTypeList =[];
	$scope.applicationWithPetition =[];
	$scope.casefile={};
	$scope.subdocument={};
	$scope.appWithPetition={};
	
	 $scope.editablerow = '';
	 
	 
	 $scope.deleteApplicationWithPetition =function (indx,awp){
		  $http.post(urlBase+'casefile/deleteAppWithPetition',awp).success(function (data) {
		    	if(data.response=="TRUE"){
		    		console.log("data daaaaaa",data);
		    		$scope.reset();  
		    		getApplicationWithPetition1();
		        
		    	}
		    	else if(data.response == "FALSE") {
		    		console.log("Dataaaaaaaaaaa");
		    		/*$scope.courtmaster1 =data.modelData;*/
		/*    		bootbox.confirm({
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
		    		 		    		 $scope.courtList[indx] = angular.copy(data.modelData);
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
		    		});*/
		    	}
		    	else
		    		{
		    		console.log("Some problem")
		    		}
		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
		 
	 }
	 
	  $scope.updateApplicationWithPetition = function (indx,awp) {
		  console.log("Court Master",awp);
		  $http.post(urlBase+'casefile/updateAppWithPetition',awp).success(function (data) {
		    	if(data.response=="TRUE"){
		    		console.log("data daaaaaa",data);
		    		 $scope.applicationWithPetition[indx] = angular.copy(data.modelData);
		        $scope.reset();  
		    	}
		    	else if(data.response == "FALSE") {
		    		console.log("Dataaaaaaaaaaa",data);
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
	  
	  $scope.getData = function(content) { 
		    if (content.awp_id == $scope.editablerow.awp_id) return 'edit';
		        else return 'view';
		 }

	$scope.doc_id= $('#doc_id').val();
	$scope.open1 = function($event,type) {
		$event.preventDefault();
		$event.stopPropagation();
		if(type=="fromDate1")
			$scope.fromDate1= true;		
	};
	
$scope.getApplicationWithPetition =function (){
		
		console.log("app called");
		
		$http.get(urlBase+'casefile/getApplicationWithPetition/'+$scope.doc_id).success(function (data) {
			$scope.applicationWithPetition=data.modelList;
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}

function getApplicationWithPetition1() {
	
	console.log("app called");
	
	$http.get(urlBase+'casefile/getApplicationWithPetition/'+$scope.doc_id).success(function (data) {
		$scope.applicationWithPetition=data.modelList;
      }).
      error(function(data, status, headers, config) {
      	console.log("Error in getting sub documents");
      });
}
	
	$scope.toggleMax = function() {
	    //$scope.minDate = $scope.minDate ? null : new Date();
		$scope.maxDate = new Date();
	};
	$scope.toggleMax();
	
	$scope.open = function($event,type) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    
	    if(type=="fromDate")
	    	$scope.fromDate= true;
		
	};
	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1	    
	};
		
	$scope.formats = ['dd-MMMM-yyyy','dd-mm-yyyy', 'yyyy/MM/dd', 'dd-MM-yyyy', 'shortDate'];
	$scope.format = $scope.formats[3];
	function convertDate(inputFormat) 
	{
		  function pad(s) { return (s < 10) ? '0' + s : s; }
		  var d = new Date(inputFormat);
		  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
	}
	
	
	
	getSubDocuments();
	getCaseFileDetails();
	getOrderReports();
	getApplicationsTypeList();
	$scope.setModel=function(){
		  $scope.sd_submitted_date='';
		  $scope.subdocument={};
		  $scope.picFile='';
		  
		  if($scope.index_fields.length==0)
			  getIndexFields();
		  
	  }
	function getIndexFields(){
		  $http.get(urlBase+'master/getindexfields').success(function (data) {
		    		$scope.index_fields=data.modelList;
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting indexfields");
		      });
	  }
	  $scope.getApplications=function(){
		  $http.get(urlBase+'master/getapplications/'+$scope.subdocument.if_id).success(function (data) {
	    		$scope.applications=data.modelList;
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting indexfields");
	      });
	  }
	  function getApplicationsTypeList(){
		  $http.get(urlBase+'master/getApplicationsTypeList').success(function (data) {
	    		$scope.applicationsTypeList=data.modelList;
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting indexfields");
	      });
	  }
	function getSubDocuments(){
		$http.get(urlBase+'casefile/getsubdocuments/'+$scope.doc_id).success(function (data) {
	    	$scope.subDocuments=data.modelList;
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function getCaseFileDetails(){
		$http.get(urlBase+'casefile/getcasefiledetails/'+$scope.doc_id).success(function (data) {
	    	$scope.casefile=data.modelData;
	    	  
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function getOrderReports(){
		$http.get(urlBase+'casefile/getorderreports/'+$scope.doc_id).success(function (data) {
	    	$scope.orderReports=data.modelList;	
	    	
	    	angular.forEach($scope.orderReports,function(value,index)
	    			{
	    		 value[0].ord_remark=$sce.trustAsHtml(value[0].ord_remark);
	    	
	    			});
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	$scope.showSubDocument=function(sd_id){
		window.open(urlBase+'casefile/showfile/'+sd_id,'_new');
	};
	$scope.viewAllOrders=function(){
		window.open(urlBase+'casefile/vieworders/'+$scope.doc_id,'_self');
	};
	$scope.showHighCourtCase=function(io_id){
		window.open(urlBase+'casefile/impugnedorder/'+io_id,'_self');
	};
	$scope.deletesubdocument=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletesubdocument/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	
$scope.setModel=function(subdocument){
		
			$scope.sd_id=subdocument.sd_id;
			$scope.sd_fd_mid=subdocument.sd_fd_mid;
		
}
	$scope.fileReplace=function(){
		$scope.subdocument={'sd_id':$scope.sd_id,'sd_fd_mid':$scope.sd_fd_mid};
		var file=$scope.picFile;
		  var flag=confirm("Are you sure");
		  if (flag)
		  {
			  if(file!="")
			  {				  
			    file.upload = Upload.upload({
			      url: urlBase + 'casefile/replaceSubDocument',
			      headers: {
			    	  'optional-header': 'header-value'
			        },
	  		   file:file,
	  		   fields:$scope.subdocument,
			    });
	
			    file.upload.then(function (response) {
			        if(response.data.response=="TRUE"){
			        	$scope.errorlist =null;
			        	alert("Successfully Replace File");
			        	$("#uploadModel").modal("hide");
			        	window.location.reload();
			        	$scope.picFile='';	        	
			        }else{
			        	alert(response.data.data);
			        }
			      }, function (response) {
			        
			      }, function (evt) {
			       
			      });
	
			      file.upload.xhr(function (xhr) {
			      });
			  }
		  }else{
			  alert("Please select valid PDF file for upload")
		  }
		}
	
	
	
	
	$scope.deleteofficereport=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deleteofficereport/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deletepetitioner=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletepetitioner/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deleterespondent=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deleterespondent/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deletepcounsel=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletepcounsel/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	$scope.deletercounsel=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletercounsel/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();
		   		});	
		  }
	  }
	  $scope.save=function() 
	  {
		 $scope.subdocument.sd_fd_mid=$scope.casefile.fd_id;
		  if($scope.sd_submitted_date!=null){
			  $scope.sd_submitted_date=convertDate($scope.sd_submitted_date);
			}
		  $scope.subdocument.sd_submitted_date=$scope.sd_submitted_date;
		  
		 if($scope.subdocument.if_id==null ||$scope.subdocument.at_id== null){
			 alert("Please select required fields");
			 return false;
		 }
		 
		    var file=$scope.picFile;
			  if($scope.subdocument.ord_remark!='' && file==""){
				  addReportData();
			  }
			  if(file!="")
			  {				  
			    file.upload = Upload.upload({
			      url: urlBase + 'casefile/uploadJudgement',
			      headers: {
			    	  'optional-header': 'header-value'
			        },
	    		   file:file,
	    		   fields:$scope.subdocument,
			    });

			    file.upload.then(function (response) {
			        if(response.data.response=="TRUE"){
			        	alert("Successfully uploaded document");
			        	$("#uploadDocument").modal("hide");
			        	//window.location.reload();
			        	window.location.reload();
			        }else{
			        	alert("Error occurred while uploading document");
			        }
			        
			      }, function (response) {
			        
			      }, function (evt) {
			        // Math.min is to fix IE which reports 200% sometimes
			        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			      });

			      file.upload.xhr(function (xhr) {
			        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
			      });
			  }
			}
	  function addReportData(){
		  $http.post(urlBase+'casefile/addreportdata?at_id='+$scope.subdocument.at_id
				  +"&sd_fd_mid="+$scope.subdocument.sd_fd_mid
				  +"&ord_remark="+$scope.subdocument.ord_remark
				  +"&sd_submitted_date="+$scope.subdocument.sd_submitted_date
			  )
			  .success(function (data) {
			    	if(data.response=="TRUE")
			    		alert("Successfully added order report data");		    	
			    	else
			    		alert("Error occurred while adding order report data");
			    	
			    	$("#uploadDocument").modal("hide");
			      }).
			      error(function(data, status, headers, config) {
			      	console.log("Error in getting tree data");
			      });
	  }
	 $scope.loading = true;
	  $scope.cancel= function()
	  {
		  $scope.loading = true;
	  }
	  
	 $scope.saveAWP= function(){
		  $scope.loading = false;
		  $scope.appWithPetition.fd_id=$scope.casefile.fd_id;
		  for ( var i=0; i<$scope.subDocuments.length;i++) {
			  if($scope.subDocuments[i].sd_if_mid==1)
				  {
				  	console.log("ver--"+$scope.subDocuments[i].sd_if_mid);
					console.log("ver--"+$scope.subDocuments[i].sd_id);
					$scope.appWithPetition.awp_sd_mid=$scope.subDocuments[i].sd_id
				  	break;
				  }
		  }
		  if($scope.appWithPetition.awp_ap_at_mid==null ||$scope.appWithPetition.awp_ap_no== null ||$scope.appWithPetition.awp_ap_year== null){
				
				 alert("Please select required fields");
				 $scope.loading = true;
				 return false;
				
			 }
		  
		  $http.post(urlBase+'casefile/addAppWithPetition?at_id='+$scope.appWithPetition.awp_ap_at_mid
				  +"&fd_id="+$scope.appWithPetition.fd_id
				  +"&awp_sd_mid="+$scope.appWithPetition.awp_sd_mid
				  +"&awp_ap_no="+$scope.appWithPetition.awp_ap_no
				  +"&awp_ap_year="+$scope.appWithPetition.awp_ap_year
			  )
			  .success(function (data) {
			    	if(data.response=="TRUE"){
			    		$scope.loading = true;
			    		alert("Successfully added");
			    		$("#addWithPetition").modal("hide");
			    		$("#uploadForm").trigger("reset");
			    	}
			    	else{
			    		$scope.loading = true;
			    		alert("Error occurred while adding data");
			    	}
			    	
			    	
			      }).
			      error(function(data, status, headers, config) {
			      	console.log("Error in getting tree data");
			      });
	  }
	  
	  $scope.savePetition=function() 
	  {
		 var file=$scope.petitionFile;
			  
			  if(file!="")
			  {				  
			    file.upload = Upload.upload({
			      url: urlBase + 'casefile/uploadpetition',
			      headers: {
			    	  'optional-header': 'header-value'
			        },
	    		   file:file,
	    		   fields:$scope.casefile,
			    });

			    file.upload.then(function (response) {
			        if(response.data.response=="TRUE"){
			        	alert("Successfully uploaded petition");
			        }else{
			        	alert("Error occurred while uploading petition");			        	
			        }
			        $("#uploadPetition").modal("hide");
		        	window.location.reload();
		        	
			      }, function (response) {
			        
			      }, function (evt) {
			        // Math.min is to fix IE which reports 200% sometimes
			        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			      });

			      file.upload.xhr(function (xhr) {
			        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
			      });
			  }
		}
	  
}]);