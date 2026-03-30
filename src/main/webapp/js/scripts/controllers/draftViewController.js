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





EDMSApp.controller('draftViewController',['$scope','$http',function ($scope, $http) {
 
 
  var urlBase="/onlinecasefiling/"
  

	
    $scope.show=false;
  

  $scope.count='';
 $scope.registerCase={};
  
  
  $scope.petitionerDataList=[];
  $scope.respondentDataList=[];
  $scope.courtFeeList=[];
  $scope.trialDataList=[];
  $scope.impugnedDataList=[];
  $scope.draftList=[];
 
 
  $scope.caseId= $('#caseId').val();
 
              getDraftDetails();
  
      $scope.submitForm=function(registerCase){
		  var response =$http.post(urlBase+'ecourt_add_case/submitRegisterCase',registerCase);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){					   
					alert(" CaseDetails Submitted Successfully!");
					window.location.reload();
					$scope.registerCase=data.modelData;
				   }else{
					   alert(" Please submit all the required fields");   
				   }			
			});	  
	  }
  
  function getDraftDetails(){
	  	$http.get(urlBase+'ecourt/getDraftDetails').
      success(function (data) {
    	  
      	$scope.count=data.data;
      	$scope.draftList=data.modelList;
    	  
      }).
      error(function(data, status, headers, config) {
      	console.log("Error in getting tree data");
      });
	}
  $scope.checkList=[];
	function getCheckList(id){
	    $http.get(urlBase+ 'ecourt/getCheckList', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	                
	              $scope.checkList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
  
  
  $scope.viewDetails=function(id){
		window.open(urlBase+"ecourt_add_case/draftView/"+id,'_self');
	  
}


$scope.previewDetails=function(id){
window.open(urlBase+"ecourt/previewList/"+id,'_self');
}


	
	
if($scope.caseId!=null && $scope.caseId!=undefined){
	getRegisterCase($scope.caseId);
	getPetitioner($scope.caseId);
    getRespondent($scope.caseId);
    getActDetails($scope.caseId);
    getImpugnedOrder($scope.caseId);
    getCourtFee($scope.caseId);
    getUploadedDocuments($scope.caseId);
    getCheckList($scope.caseId);
}


function getRegisterCase(id){
$http.get(urlBase+ 'ecourt_add_case/getRegisterCase', {
	params : {
		'docId' : id
	}
}).success(function(data, status, headers, config) {
            debugger
          $scope.registerCase = data.modelData;
            var date=new Date(parseInt($scope.registerCase.rcd_date_of_section4));
			$scope.registerCase.rcd_date_of_section4=("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear();
			

}).error(function(data, status, headers, config) {
});

}


























	function getPetitioner(id){
    $http.get(urlBase+ 'ecourt/getPetitioner', {
		params : {
			'docId' : id
		}
	}).success(function(data, status, headers, config) {
                debugger
              $scope.petitionerDataList = data.modelList;
               
}).error(function(data, status, headers, config) {
	});

}
	
	
	function getRespondent(id){
	    $http.get(urlBase+ 'ecourt/getRespondent', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	                debugger
	              $scope.respondentDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}




	function getActDetails(id){
	    $http.get(urlBase+ 'ecourt/getActDetails', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	                debugger
	              $scope.actDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}


	
	function getImpugnedOrder(id){
	    $http.get(urlBase+ 'ecourt/getImpugnedOrder', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.impugnedDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}

     
	
	function getTrialCourt(id){
	    $http.get(urlBase+ 'ecourt/getTrialCourt', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.trialDataList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
	
	
	
	function getCourtFee(id){
	    $http.get(urlBase+ 'ecourt/getCourtFee', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {
	              $scope.courtFeeList = data.modelList;
	               
	}).error(function(data, status, headers, config) {
		});

	}
	
	function getUploadedDocuments(id){
	   	 
		$http.get(urlBase+'ecourt_add_case/getUploadedDocuments', {params : {'rcd_id' : id}}).
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
	















	
 /* $scope.viewDetails=function(id){
		window.open(urlBase+"ecourt_add_case/draftView/"+id,'_blank');
	  
  }
  
  
  $scope.previewDetails=function(id){
  window.open(urlBase+"ecourt/previewList/"+id,'_blank');
  }
  
  
  
  
 
  
 
  
 
  alert(caseId);

  
	function getRegisterCase(id){
		debugger
    $http.get(urlBase+ 'ecourt_add_case/getRegisterCase', {
		params : {
			'docId' : id
		}
	}).success(function(data, status, headers, config) {
                debugger
              $scope.registerCase = data.modelData;
                var date=new Date(parseInt($scope.registerCase.rcd_date_of_section4));
    			$scope.registerCase.rcd_date_of_section4=("0" + date.getDate()).slice(-2) + '/' + ("0" + (date.getMonth() + 1)).slice(-2) + '/' +  date.getFullYear();
    			

}).error(function(data, status, headers, config) {
	});

}
	
	
	
	
  
  
  */
  
  
  
  
  
  
  
}]);