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





EDMSApp.controller('scrutinyViewController',['$scope','$http',function ($scope, $http) {
 
 
  var urlBase="/adminpanel/"
  

	
    $scope.show=false;
  
  	$scope.stampReporterData={};
  $scope.count='';
 $scope.registerCase={};
  
  
  $scope.petitionerDataList=[];
  $scope.respondentDataList=[];
  $scope.courtFeeList=[];
  $scope.trialDataList=[];
  $scope.impugnedDataList=[];
  $scope.draftList=[];
  $scope.uploadedDocumentsList=[];
  $scope.checkList=[];
  $scope.checkListObj=[];
  $scope.chckedIndexs = [];
  $scope.actDataList=[];
 
	$scope.open1 = function($event,type) {
	    $event.preventDefault();
	    $event.stopPropagation();
	    
	    if(type=="fromDate1")
	    	$scope.fromDate1= true;
	    if(type=="toDate1")
	    	$scope.toDate= true;
	};
	

	/*$scope.toggleMax = function() {
		$scope.maxDate = new Date();
	};*/
	//$scope.toggleMax();				
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
  $scope.caseId= $('#caseId').val();
 
  
  getApplicationTypes();
  
  $scope.viewDetails=function(id){
		window.open(urlBase+"ecourt_add_case/draftView/"+id,'_blank');
	  
}


$scope.previewDetails=function(id){
window.open(urlBase+"ecourt/previewList/"+id,'_blank');
}


	
	
if($scope.caseId!=null && $scope.caseId!=undefined){
	getRegisterCase($scope.caseId);
	getStampReporterData($scope.caseId);
	getPetitioner($scope.caseId);
    getRespondent($scope.caseId);
    getActDetails($scope.caseId);
    getImpugnedOrder($scope.caseId);
    getCourtFee($scope.caseId);
    getUploadedDocuments($scope.caseId);
    getRemarkHistory($scope.caseId);
    getRemarkList();
}


function getRegisterCase(id){
$http.get(urlBase+ 'casefile/getCaseDetail', {
	params : {
		'docId' : id
	}
}).success(function(data, status, headers, config) {
            
          $scope.registerCase = data.modelData;
            var date=new Date(parseInt($scope.registerCase.rcd_date_of_section4));
			$scope.registerCase.rcd_date_of_section4=("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear();
			

}).error(function(data, status, headers, config) {
});

}
	function getStampReporterData(id){
		$http.get(urlBase+ 'scrutiny/getStampReporterData', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {	            
		          $scope.stampReporterData = data.modelData;	            
		}).error(function(data, status, headers, config) {
		});
	}
	function getRemarkHistory(id){
		$http.get(urlBase+ 'scrutiny/getCaseHistory', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {	            
		          $scope.checkListHistory = data.modelList;	            
		}).error(function(data, status, headers, config) {
		});
	}



function getApplicationTypes(){
	$http.get(urlBase+'application/getApplicationTypes').
    success(function (data) {
    	$scope.applicationTypeList=data.modelList;
    }).
    error(function(data, status, headers, config) {
    	console.log("Error in getting ApplicationTypes data");
    });
}

	function getPetitioner(id){
    $http.get(urlBase+ 'casefile/getPetitioner', {
		params : {
			'docId' : id
		}
	}).success(function(data, status, headers, config) {
                
              $scope.petitionerDataList = data.modelList;
               
}).error(function(data, status, headers, config) {
	});

}
	
	
	function getRespondent(id){
	    $http.get(urlBase+ 'casefile/getRespondent', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	                
	              $scope.respondentDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}




	function getActDetails(id){
	    $http.get(urlBase+ 'casefile/getActDetails', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.actDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}


	
	function getImpugnedOrder(id){
	    $http.get(urlBase+ 'casefile/getImpugnedOrder', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.impugnedDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}

     
	
	function getTrialCourt(id){
	    $http.get(urlBase+ 'casefile/getTrialCourt', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.trialDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
	
	
	
	function getCourtFee(id){
	    $http.get(urlBase+ 'casefile/getCourtFee', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.courtFeeList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
	
	
	function getUploadedDocuments(id){
   	 
		$http.get(urlBase+'casefile/getUploadedDocuments', {params : {'rcd_id' : id}}).
        success(function (data) {
        	$scope.uploadedDocumentsList=data.modelList;
        	console.log($scope.uploadedDocumentsList);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	
	$scope.showDocument=function(selectedfile){
		var response = $http.get(urlBase+'scrutiny/copyFile',{params: {'pu_document_name': selectedfile.pu_document_name}});
		response.success(function(data, status, headers, config) {		
			console.log(data);
			if(data.data != null)
			{
				window.open(urlBase+"/uploads/"+data.data,'_blank');
			}
		});
		response.error(function(data, status, headers, config) {
			bootbox.alert("Error");
		});
	};
	
	function getRemarkList(){
	    $http.get(urlBase+ 'scrutiny/getCheckList', {
	    	params : {
	    		'type' : 'F'
	    	}
	    }).success(function(data, status, headers, config) {
	              $scope.checkList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
	
	$scope.checkAll = function () 
	 { 
   	  if ($scope.selectedAll) {
             $scope.selectedAll = true;
         } else {
             $scope.selectedAll = false;
         }
   	  angular.forEach($scope.checkList,function(value,index){
   	
   		  value.checkbox=$scope.selectedAll;
   		 // $scope.bundlelist.splice($scope.bundlelist.indexOf(value), 1);
   		  $scope.checkListObj.push(value);
   		  console.log("Value in checkListObj");
   		  console.log(JSON.stringify($scope.checkListObj));
   		    
         });

     };
     
     $scope.checkedIndex = function (data)
     {
	         if ($scope.chckedIndexs.indexOf(data) === -1)
	         {
	             $scope.chckedIndexs.push(data);
	         }
	         else {
	             $scope.chckedIndexs.splice($scope.chckedIndexs.indexOf(data), 1);
	         }
	         console.log("checked value"+JSON.stringify($scope.chckedIndexs));
	     }

     $scope.submit_file = function(remarks) 
	 {
		   $scope.buttonDisabled = true;
	   
		
			 angular.forEach($scope.checkList,function(value,index)
		   	   {							 
					if(value.checkbox == true)
					{
						 $scope.checkListObj.push(value);
					}
			});
			 
			
			 $scope.entity={'checkList':$scope.checkListObj,'rcd_id':$scope.registerCase.rcd_id};
				//	$scope.registerCase.checkList=$scope.checkListObj;
			 $scope.entity.stampReporterData=$scope.stampReporterData;
			 var response = $http.post(urlBase+ 'scrutiny/submit_case_file',$scope.entity);
				response.success(function(data, status, headers, config) {
					//;
					$scope.buttonDisabled = false;
				
					if (data.response == "REJECT") {
						$scope.errorlist = data.dataMapList;
						alert("File Sent for correction");
						window.location.href=urlBase+"scrutiny/cases";
					}
					else 
					{
						$scope.buttonDisabled = false;
						alert("File Submited for Case no generation");
						window.location.href=urlBase+"scrutiny/cases";
							
					}

				});
				response.error(function(data, status, headers, config) {
					$scope.buttonDisabled = false;
					alert("Error");
				});
	
	 			 
			};


			$scope.searchCaveat=function(){
				 $http.get(urlBase+ 'casefile/searchCaveat', {
						params : {
							'docId' : $scope.registerCase.rcd_id
						}
					}).success(function(data, status, headers, config) {
				                
				              $scope.caveatDataList = data.modelList;
				              searchCaveatold();
				}).error(function(data, status, headers, config) {
					});

			}
			function searchCaveatold(){
				 $http.get(urlBase+ 'casefile/searchCaveatold', {
						params : {
							'docId' : $scope.registerCase.rcd_id
						}
					}).success(function(data, status, headers, config) {
				                
				              $scope.oldcaveatDataList = data.modelList;
				              
				               
				}).error(function(data, status, headers, config) {
					});

			}
  
}]);