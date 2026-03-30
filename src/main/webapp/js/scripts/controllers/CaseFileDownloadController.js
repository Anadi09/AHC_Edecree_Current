var EDMSApp = angular.module("EDMSApp", ['ui.bootstrap']);
EDMSApp.controller("DownloadCtrl",	['$scope','$sce','$http',function ($scope,$sce, $http) {
	var urlBase="/dms/";
	$scope.subDocuments=[];
	$scope.index_fields=[];
	$scope.applications=[];
	$scope.ord_remark=[];
	$scope.casefile={};
	$scope.subdocument={};
	$scope.doc_id= $('#doc_id').val();
	getCaseFileDetails();
	getSubDocuments();
	getOrderReports();
	getDownloadHistory();
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
	    	
	            var i=0;
	    	angular.forEach($scope.orderReports,function(value,index)
	    			{
	    		console.log(value[0].ord_remark);
	    		//console.log($scope.orderReports[i].ord_remark);
	    		// value[0].ord_remark=$sce.trustAsHtml(value[0].ord_remark);
	    		
	    		$scope.ord_remark[i]=$sce.trustAsHtml(value[0].ord_remark);
	    	        i++;
	    			});
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
		console.log($scope.orderReports);
	}
	function getDownloadHistory(){
		$http.get(urlBase+'casefile/getdownloadhistory/'+$scope.doc_id).success(function (data) {
	    	$scope.reports=data.modelList;
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	$scope.removeFiles=function(id){
		  var result=confirm("Are you really want to Remove");
			if (result) {
		   $http({
			method : 'DELETE',
			url : urlBase + 'casefile/deleteDownloadHistory/' +id 
			}).success(function(res) {
			   if(res.response=="TRUE"){
				getDownloadHistory();
			   }
			
		});	
	}
		 
	}

	$scope.setFiles=function(data){
		
		$http.get(urlBase+'casefile/getdownloadedfiles/'+data.dr_id).success(function (data) {
	    	$scope.files=data.modelList;
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	$scope.showSubDocument=function(sd_id){
		//window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
		window.open(urlBase+'casefile/subdocument/'+sd_id,'_self');
	};
	$scope.showSubDocumentshow=function(sd_id){
		//window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
		window.open(urlBase+'casefile/showfile/'+sd_id,'_new');
	};
	$scope.downloadFiles=function(dr_id){
		window.open(urlBase+'casefile/downloadfile/'+dr_id,'_self');
	}
	$scope.addfiles=function(){
		$scope.subDocumentsList=[];
		$scope.ordersList=[];
		angular.forEach($scope.subDocuments,function(value,index)
		{							 
			if(value.checked == true)
			{
				 $scope.subDocumentsList.push(value);
			}
		});
		angular.forEach($scope.orderReports,function(value,index)
				{							 
					if(value.checked == true)
					{
						$scope.ordersList.push(value[0]);
					}
				});
		if($scope.subDocumentsList.length > 0 || $scope.ordersList.length>0)
		{
			var confirmbox = confirm("Do you really want to add these files for download");
	   	 	if (confirmbox) 
	   	 	{
	   	 		$scope.entity={'subDocuments':$scope.subDocumentsList,'orderReports':$scope.ordersList,'fd_id':$scope.doc_id};
	   	 		$scope.entity.stampReporterData=$scope.stampReporterData;
	   	 		console.log($scope.entity);
	   	 		var response = $http.post(urlBase+ 'casefile/addfiles',$scope.entity);
	   	 		response.success(function(data, status, headers, config) {
					window.location.reload();
	   	 		});
	   	 		
	   	 	}	
		}
		
	}
	$scope.checkAll = function () 
	{		
  	  if ($scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
  	  	angular.forEach($scope.subDocuments,function(value,index){
  		  
  		  value.checked=$scope.selectedAll;
  		 // $scope.bundlelist.splice($scope.bundlelist.indexOf(value), 1);
  		  
        });
  	  	angular.forEach($scope.orderReports,function(value,index){
  		  
  		  value.checked=$scope.selectedAll;
  		 // $scope.bundlelist.splice($scope.bundlelist.indexOf(value), 1);
  		  
        });

	 };
}]);