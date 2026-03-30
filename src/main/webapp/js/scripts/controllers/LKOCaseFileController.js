var EDMSApp = angular.module("EDMSApp", ['ngFileUpload','ngMask','ui.bootstrap']);

EDMSApp.controller('LKOCaseFileController',['$scope','$http','Upload',function ($scope, $http,Upload) {
	  var urlBase="/dms/";
	  $scope.picFile='';
	  $scope.caseTypes=[];
	  $scope.lkocaseTypes=[];
	  $scope.lkosearch={};
	  $scope.subdocument={};
	  $scope.offRep={};
	  getCaseTypes();
	  getLKOCaseTypes();
	  getIndexFields();
	  $scope.open1 = function($event,type) {
		    $event.preventDefault();
		    $event.stopPropagation();
		    
		    if(type=="fromDate1")
		    	$scope.fromDate1= true;
		    if(type=="toDate1")
		    	$scope.toDate= true;
		};
	
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
		    if(type=="toDate")
		    	$scope.toDate= true;
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

	  
	  function getCaseTypes(){
		  $http.get(urlBase+'master/getcasetypes').success(function (data) {
		    		$scope.caseTypes=data.modelList;		    	
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  function getLKOCaseTypes(){
		  $http.get(urlBase+'lkomaster/getLKOCaseTypes').success(function (data) {
		    		$scope.lkocaseTypes=data.modelList;		    	
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
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
	  $scope.setModel=function(casefile){
		  $scope.casefile=casefile;		  
		  $scope.sd_submitted_date='';
		  $scope.subdocument={};
		  $scope.picFile='';
		  console.log($scope.casefile);
	  }
	  $scope.lkosearchCaseFiles=function() 
	  {
		  $scope.lkocaseFileList=[];
		  $http.post(urlBase+'lkocasefile/getLKOCaseFileList',$scope.lkosearch).success(function (data) {
		    	if(data.response=="TRUE")
		    		$scope.lkocaseFileList=data.modelList;
		    	else
		    		$scope.lkocaseFileList=[];
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
	  }
	  $scope.ord_remark="";
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
			        	$scope.errorlist =null;
			        	alert("Successfully uploaded document");
			        	$("#uploadDocument").modal("hide");
			        	//window.location.reload();
			        	$scope.picFile='';
			        	$scope.if_id='';
			        	$scope.ord_remark='';
			        	
			        }else{
			        	$scope.errorlist = response.data.dataMapList;
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
		  $scope.subdocument.sd_submitted_date=$scope.sd_submitted_date;
		  $http.post(urlBase+'casefile/addreportdata?at_id='+$scope.subdocument.at_id
				  +"&sd_fd_mid="+$scope.subdocument.sd_fd_mid
				  +"&ord_remark="+$scope.subdocument.ord_remark
				  +"&sd_submitted_date="+$scope.subdocument.sd_submitted_date
				  +"&ord_consignment_no="+$scope.subdocument.ord_consignment_no
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
	  $scope.downloadFiles=function(id){
			window.open(urlBase+"lkocasefile/downloadlistLKO/"+id,"_self");
		}
	  $scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_self");
	  }
	  
	  $scope.viewCaseFileLKO=function(id){
		  window.open(urlBase+"lkocasefile/viewLKO/"+id,"_self");
	  }
	  
	  $scope.viewDetail=function(id){
		  window.open(urlBase+"casefile/viewdetail/"+id,"_self");
	  }
	  $scope.getSubDocuments=function(doc_id){
		  $scope.doc_id=doc_id;
		  $http.get(urlBase+'casefile/getsubdocuments/'+$scope.doc_id).success(function (data) {
		    	$scope.subDocuments=data.modelList;
		  }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	  }
	  
	  $scope.deleteSubDocument=function(id){
		  var result=confirm("Are you really want to Delete file");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'casefile/deletesubdocument/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			$scope.getSubDocuments($scope.doc_id);			
		   		});	
		  }
	  }
	  
	  $scope.loading=true;
	  $scope.updateCasetype=function(){
		  
		  $scope.loading=false;
		  
		  if($scope.new_case_type==$scope.casefile.fd_case_type){
			  $scope.loading=true;
			  alert("Existing and new case type is same");
			  return false;
		  }
		  $http.post(urlBase+'lkocasefile/moveCaseLKOtoALD?fd_id='+$scope.casefile.fd_id
				  +"&new_case_type="+$scope.new_case_type
				  +"&new_case_no="+$scope.new_case_no
				  +"&new_case_year="+$scope.new_case_year
				  
			  )
			  .success(function (data) {
			    	if(data.response=="TRUE")
			    	{
			    		$scope.loading=true;
			    		alert(data.data);
			    	}
			    		/*alert("Successfully updated case type information");*/		    	
			    	else
			    	{
			    		$scope.loading=true;
			    		alert(data.data);
			    		
			    	}
			    	
			    	$scope.new_case_type='';
			    	$scope.new_case_no='';
			    	$scope.new_case_year='';
			    	
			    	$("#updateCaseType").modal("hide");
			      }).
			      error(function(data, status, headers, config) {
			    	  $scope.loading=true;
			      	console.log("Error in getting tree data");
			      });
	  }
	  
	 
	  $scope.validate = true;
	  $scope.offrepohide=false;
	  $scope.hideOther=function(){
		  if($scope.offRep.off_rep==1)
			  {
			  $scope.offrepohide=true;
			  /*$scope.subdocument.ord_consignment_no = "";
			  $scope.subdocument.ord_consignment_no = false;*/
			 
			  }
		  else
			  {
			  $scope.offrepohide=false;
			  /*$scope.subdocument.ord_consignment_no = true;*/
			  }
		  
	  }
	  
	  
}]);