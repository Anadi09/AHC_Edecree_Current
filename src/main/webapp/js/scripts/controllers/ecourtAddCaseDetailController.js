
var EDMSApp = angular.module('EDMSApp', ['ngFileUpload','ui.bootstrap']);
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

EDMSApp.controller('addCaseDetailController',['$scope','$http','Upload',function ($scope, $http,Upload) {

	var urlBase="/onlinecasefiling/";
	
	$scope.petitionerDataList=[];
	$scope.respondentDataList=[];
	$scope.courtFeeList=[];
	$scope.formShow=true;
	$scope.petitionerDetails={};
	$scope.registerCase={};
	
	$scope.caseDetail={};
	$scope.tabShow1=true;
	$scope.tabShow2=false;
	$scope.tabShow3=false;
	$scope.tabShow4=false;
	$scope.tabShow5=false;
	$scope.tabShow=false;

	
	
	
	
	
	
	
	$scope.stateList=[];
	$scope.districtList=[];
	
	
	getStateList();
	$scope.draftId= $('#draftId').val();
	 getResetData();
	
	 function getStateList()
	 {
			$http.get(urlBase+'ecourt/getStateList').
	        success(function (data) {
	        	$scope.stateList=data.modelList;
	        	console.log($scope.petitionerDataList);
	        }).
	        error(function(data, status, headers, config) {
	        	console.log("Error in getting tree data");
	        });
		};
		
		
		getDistrictList();
		
		 function getDistrictList()
		 {
				$http.get(urlBase+'ecourt/getDistrictList').
		        success(function (data) {
		        	$scope.districtList=data.modelList;
		        	
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
			};
			getActMasters();
			$scope.stateActList=[];
			$scope.centralActList=[];
			function getActMasters()
			{
					$http.get(urlBase+'ecourt/getActMasterList').
			        success(function (data) {
			        	$scope.actDetailsList=data.modelList;
			        	
			        	angular.forEach($scope.actDetailsList, function(value, key) {
			        		  if(value.act_type=="C"){
			        			  $scope.centralActList.push(value);
			        		  }
			        		  if(value.act_type=="S"){
			        			  $scope.stateActList.push(value);
			        		  }
			        		});
			        	
			        }).
			        error(function(data, status, headers, config) {
			        	console.log("Error in getting tree data");
			        });
			};
			
	
			
			getCaseTypes();
			
			 function getCaseTypes()
			 {
					$http.get(urlBase+'ecourt/getCaseTypes').
			        success(function (data) {
			        	$scope.caseTypeList=data.modelList;
			        	
			        }).
			        error(function(data, status, headers, config) {
			        	console.log("Error in getting tree data");
			        });
				};
				
				getLowerCourtCaseTypes();
				
				 function getLowerCourtCaseTypes()
				 {
						$http.get(urlBase+'ecourt/getLowerCourtCaseTypes').
				        success(function (data) {
				        	$scope.lowerCourtCaseTypes=data.modelList;
				        	
				        }).
				        error(function(data, status, headers, config) {
				        	console.log("Error in getting tree data");
				        });
					};
					getLowerCourtList();
					
					 function getLowerCourtList()
					 {
							$http.get(urlBase+'ecourt/getLowerCourtList').
					        success(function (data) {
					        	$scope.lowerCourtList=data.modelList;
					        	
					        }).
					        error(function(data, status, headers, config) {
					        	console.log("Error in getting tree data");
					        });
						};
				
			
				
				
				
				
				
				
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
				
				/*function convertDate(inputFormat) 
				{
					  function pad(s) { return (s < 10) ? '0' + s : s; }
					  var d = new Date(inputFormat);
					  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
				}
	*/
	
	
	
	
	
	
	
	
	
	
	/** ***********NEW CASE STARTS HERE*********************************** */
	
	
	
	
	   
	if($scope.draftId!=null && $scope.draftId!=undefined)
	getRegisterCase($scope.draftId);
	
		
			function getRegisterCase(id){
				
		    $http.get(urlBase+ 'ecourt_add_case/getRegisterCase', {
				params : {
					'docId' : id
				}
			}).success(function(data, status, headers, config) {
		                
		                $scope.registerCase = data.modelData;
	                 /*
						 * $scope.registerCase = data.modelData; var date=new
						 * Date(parseInt($scope.registerCase.rcd_cr_date));
						 * $scope.registerCase.rcd_cr_date=("0" +
						 * date.getDate()).slice(-2) + '/' + ("0" +
						 * (date.getMonth() + 1)).slice(-2) + '/' +
						 * date.getFullYear();
						 * 
						 */
		
		}).error(function(data, status, headers, config) {
			});

		}

			function getResetData(){
			if($scope.draftId!=null && $scope.draftId!=undefined){
			getPetitioner($scope.draftId);
			getRespondent($scope.draftId);
			getActDetails($scope.draftId);
			getImpugnedOrder($scope.draftId);
			getTrialCourt($scope.draftId);
			getCourtFee($scope.draftId);
			getUploadedDocuments();
			}}
			
			
			function getPetitioner(id){
				
			    $http.get(urlBase+ 'ecourt/getPetitioner', {
					params : {
						'docId' : id
					}
				}).success(function(data, status, headers, config) {
			                
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
				        /* for(var i=0; i<courtFeeList.length; i++){      
				              
				              var date(parseInt($scope.courtFeeList[i].cf_date));
								  $scope.$scope.courtFeeList[i].cf_date=("0" +
								  date.getDate()).slice(-2) + '/' + ("0" +
								  (date.getMonth() + 1)).slice(-2) + '/' +
								  date.getFullYear();
								  
				         } */
				              
				              
				              
				}).error(function(data, status, headers, config) {
					});

				}

				
				  
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
		  
			
		
		
			/*
			 * var ddate=registerCase.rcd_cr_date;
			 * 
			 * ddate=ddate.split("/"); var
			 * newDate=ddate[0]+"/"+ddate[1]+"/"+ddate[2];
			 * 
			 * registerCase.rcd_date_of_section4=new Date(newDate).getTime();
			 */

	
	$scope.addRegisterCase=function(registerCase){
		
		var response =$http.post(urlBase+'ecourt_add_case/addRegisterCase',registerCase);
		response.success(function(data, status, headers, config){
			   if(data.response=="TRUE"){
				   
				alert("CaseDetails Added Successfully!");
				$scope.registerCase=data.modelData;
			   }
			   else if(data.data=="Update"){
				   
				alert(" caseDetails Updated Successfully!");
			   }
	
			
		});	
	}

  /**
	 * ************************** add case Ends
	 * here*******************************
	 */

$scope.ShowId = function(event)
{
	
	$scope.tabShow1=true;
	$scope.tabShow2=true;
	$scope.tabShow3=true;
	$scope.tabShow4=true;
	$scope.tabShow5=true;
	$scope.tabShow6=true;
	$scope.tabShow7=true;
	$scope.tabShow8=true;
	$scope.tabShow=true;
};
	
$scope.nextTab=function(event){
}


	
	
$scope.addShow=true;
	
	
	
		
	/*
	 * getPetitionerDetails();
	 * 
	 * function getPetitionerDetails(){
	 * $http.get(urlBase+'ecourt_add_case/getPetitionerDetails').
	 * success(function (data) { $scope.petitionerDataList=data.modelList;
	 * console.log($scope.petitionerDataList); }). error(function(data, status,
	 * headers, config) { console.log("Error in getting tree data"); }); };
	 */
		
		$scope.editPet=function(row){
			$scope.petitionerDetails=row;
		}
		$scope.registerCase.rcd_id;
		$scope.addPetitioner=function(PetitionerDetails){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			PetitionerDetails.pt_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addPetitioner',PetitionerDetails);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("Petitioner Added Successfully!");
					$scope.petitionerDetails="";
					 if($scope.draftId!=null && $scope.draftId!=undefined)
				     {
						 getPetitioner($scope.draftId);
				     }
					 else{
					getPetitioner($scope.registerCase.rcd_id);
					 }
					
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" petitioner Updated Successfully!");
							$scope.petitionerDetails="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
							getPetitioner($scope.draftId);
							}
							else
								{
							getPetitioner($scope.registerCase.rcd_id);
								}
				   }
		
				
			});	
		}
		
		
		
		
		
		
		
		$scope.deletePetitioner=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deletePetitioner/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert(" petitioner Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
					getPetitioner($scope.draftId);
					}
					else{
					getPetitioner($scope.registerCase.rcd_id);
				 	
				   }
				   }
				
			});	
		}
		
		}
	
		
		
	
		
		
		
		/**
		 * *************************PETITIONER DETAILS ENDED
		 * HERE****************************
		 */
		
		
		
		
	/**
	 * *************************** Respondent code starts
	 * here***************************************
	 */	
		
		
		$scope.respondentDetails={};
		
		$scope.editResp=function(row){
			$scope.respondentDetails=row;
			
		}
			
		
		/*
		 * getRespondentDetails();
		 * 
		 * function getRespondentDetails(){
		 * $http.get(urlBase+'ecourt_add_case/getRespondentDetails').
		 * success(function (data) { $scope.respondentDataList=data.modelList;
		 * console.log($scope.respondentDataList); }). error(function(data,
		 * status, headers, config) { console.log("Error in getting tree data");
		 * }); };
		 */
			
			
		
		
		
		

		$scope.addRespondent=function(respondentDetails){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			respondentDetails.rt_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addRespondent',respondentDetails);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("Respondent Added Successfully!");
					$scope.respondentDetails="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getRespondent($scope.draftId);	
					}
					else{
					getRespondent($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						 alert(" respondent Updated Successfully!");
							$scope.respondentDetails="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getRespondent($scope.draftId);	
							}
							else{
							getRespondent($scope.registerCase.rcd_id);
							}
							
				   }
		
				
			});	
		}
		
		
		
		
		
		$scope.deleteRespondent=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteRespondent/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("  respondent Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getRespondent($scope.draftId);	
					}
					else{
					getRespondent($scope.registerCase.rcd_id);
					}
				 	
				   }
				
			});	
		}
			 
		}
		
		
		
		/**
		 * ***********************RESPONDENT DETAILS ENDED
		 * HERE******************
		 */
		
		
		
		/** ******************impugned Order starts here************** */
		
		
		
		
		
		
		$scope.impugnedOrder={};
		
		
		
		$scope.editImp=function(data){
			$scope.impugnedOrder=data;
			
		}
		
		

// getImpugnedOrder();
//		
// function getImpugnedOrder(){
// $http.get(urlBase+'ecourt_add_case/getImpugnedOrder').
// success(function (data) {
// $scope.impugnedDataList=data.modelList;
// }).
// error(function(data, status, headers, config) {
// console.log("Error in getting tree data");
// });
// };
//			
//			
		
		

		$scope.addImpugnedOrder=function(impugnedOrder){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			/*$scope.model={};
			$scope.model.io_lct_mid =$scope.impugnedOrder.io_lct_mid ;
			$scope.model.io_s_id =$scope.impugnedOrder.io_s_id;
			$scope.model.io_judge_name=$scope.impugnedOrder.io_judge_name;
			$scope.model.io_case_no=$scope.impugnedOrder.io_case_no;
			$scope.model.io_district=$scope.impugnedOrder.io_district;
			$scope.model.io_decision_date=$scope.impugnedOrder.io_decision_date;
			$scope.model.io_ct_mid=$scope.impugnedOrder.io_ct_mid;
			$scope.model.io_rcd_mid=$scope.registerCase.rcd_id;*/
			/*impugnedOrder.io_rcd_mid=$scope.registerCase.rcd_id;*/
			$scope.impugnedOrder.io_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addImpugnedOrder',$scope.impugnedOrder);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("ImpugnedOrder Added Successfully!");
					$scope.impugnedOrder="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getImpugnedOrder($scope.draftId);	
					}
					else{
						getImpugnedOrder($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" impugnedOrder Updated Successfully!");
							$scope.impugnedOrder="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getImpugnedOrder($scope.draftId);	
							}
							else{
								getImpugnedOrder($scope.registerCase.rcd_id);
							}
							
				   }
		
				
			});	
		}
		
		
		
		
		
		$scope.deleteImpugnedOrder=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteImpugnedOrder/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
			     alert("  ImpugnedOrder Deleted Successfully!");
			     if($scope.draftId!=null && $scope.draftId!=undefined){
						getImpugnedOrder($scope.draftId);	
					}
					else{
						getImpugnedOrder($scope.registerCase.rcd_id);
					}
				   }
		
				
			});	
		}
		
		}
		
		
		/**
		 * **********************************IMPUGNED ORDER ENDED
		 * HERE*******************************************************
		 */
		
		
		
		/**
		 * ************************************* ACT DETAILS CODE STARTS HERE
		 * ********************************************
		 */
		
		
		$scope.actDetails={
				
				
		}; 
		
		
		
		
		$scope.centralShow=true;
		$scope.stateShow=false;
		$scope.changeAct=function(event){
			if(event.target.id=="central"){
				$scope.centralShow=true;
				$scope.stateShow=false;
		
				
			}
			else{
				$scope.stateShow=true;
				$scope.centralShow=false;
			}
		};
		
		
		$scope.editAct=function(data){
			$scope.actDetails=data;
			
		}
		
		

		/*
		 * getActDetails();
		 * 
		 * function getActDetails(){
		 * $http.get(urlBase+'ecourt_add_case/getActDetails'). success(function
		 * (data) { $scope.actDataList=data.modelList; }). error(function(data,
		 * status, headers, config) { console.log("Error in getting tree data");
		 * }); };
		 * 
		 */
		
		

		$scope.addActDetails=function(actDetails){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			if(actDetails.act_type==1)
				actDetails.act_type='central';
			actDetails.act_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addActDetails',actDetails);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("ActDetails Added Successfully!");
					$scope.actDetails="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getActDetails($scope.draftId);	
					}
					else{
						getActDetails($scope.registerCase.rcd_id);
					}
					
					
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
					   alert(" actDetails Updated Successfully!");
							$scope.actDetails="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getActDetails($scope.draftId);	
							}
							else{
								getActDetails($scope.registerCase.rcd_id);
							}
							
				   }
		
				
			});	
		}
		
		
		
		$scope.deleteActDetails=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteActDetails/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
					alert("  actDetails Deleted Successfully!");
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getActDetails($scope.draftId);	
					}
					else{
						getActDetails($scope.registerCase.rcd_id);
					}
					
				 	
				   }
		
				
			});	
		}
		
		}
		
/** ***************************ACT DETAILS ENDED HERE*************************** */
		
		
	
		
		
		/** ******************Trial court Code******************************** */
		
		
    $scope.trialCourt={};
		
		
		
		$scope.editTrial=function(data){
			$scope.trialCourt=data;
			
		}
		
		

		/*
		 * getTrialCourt();
		 * 
		 * function getTrialCourt(){
		 * $http.get(urlBase+'ecourt_add_case/getTrialCourt'). success(function
		 * (data) { $scope.trialDataList=data.modelList; }).
		 * error(function(data, status, headers, config) { console.log("Error in
		 * getting tree data"); }); };
		 */
			
		
		

		$scope.addTrialCourt=function(){
			
			$scope.model={};
			//$scope.model.tr_sub_courtname =$scope.trialCourt.tr_sub_courtname ;
			/*$scope.model.tr_judge_name=$scope.trialCourt.tr_judge_name;
			$scope.model.tr_case_no=$scope.trialCourt.tr_case_no;
			$scope.model.tr_district=$scope.trialCourt.tr_district;
			$scope.model.tr_decision_date=$scope.trialCourt.tr_decision_date;
			$scope.model.tr_rcd_mid=$scope.registerCase.rcd_id;
			$scope.model.tr_ct_mid=$scope.trialCourt.tr_ct_mid;
			$scope.model.tr_lct_mid=$scope.trialCourt.tr_lct_mid;
			$scope.model.tr_s_id=$scope.trialCourt.tr_s_id;*/
			$scope.trialCourt.tr_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addTrialCourt',$scope.trialCourt);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("TrialCourt Added Successfully!");
					$scope.trialCourt="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getTrialCourt($scope.draftId);	
					}
					else{
						getTrialCourt($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" trialCourt Updated Successfully!");
							$scope.trialCourt="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getTrialCourt($scope.draftId);	
							}
							else{
								getTrialCourt($scope.registerCase.rcd_id);
							}
				   }
		
				
			});	
		}
		
		
		
		
		
		$scope.deleteTrialCourt=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteTrialCourt/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
				    alert("  trial Court Deleted Successfully!");
				    if($scope.draftId!=null && $scope.draftId!=undefined){
						getTrialCourt($scope.draftId);	
					}
					else{
						getTrialCourt($scope.registerCase.rcd_id);
					}
				 	
				   }
		
				
			});	
		}
		
		}
		
		/** ************************End of Trial court********************** */
		
		
		$scope.courtFee={};

		 $scope.editCourtFee=function(row){
			 $scope.courtFee=row;
			 
		 }
		$scope.addCourtFee=function(){
			if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
			$scope.model={};
			$scope.model.cf_date=$scope.courtFee.cf_date;
			$scope.model.cf_amount=$scope.courtFee.cf_amount;
			$scope.model.cf_receipt_no=$scope.courtFee.cf_receipt_no;
			
			//var ddate=courtFee.cf_date;
			 
			 //ddate=ddate.split("/"); 
			 //var newDate=ddate[0]+"/"+ddate[1]+"/"+ddate[2];
			  
			 //courtFee.cf_date=new Date(newDate).getTime();
			 
			$scope.model.cf_rcd_mid=$scope.registerCase.rcd_id;
			var response =$http.post(urlBase+'ecourt_add_case/addCourtFee',$scope.model);
			response.success(function(data, status, headers, config){
				   if(data.response=="TRUE"){
					alert("CourtFee Added Successfully!");
					$scope.courtFee="";
					if($scope.draftId!=null && $scope.draftId!=undefined){
						getCourtFee($scope.draftId);	
					}
					else{
						getCourtFee($scope.registerCase.rcd_id);
					}
				   }
	      		 // $scope.lookupdataList.unshift(data);
				   else if(data.data=="Update"){
						  alert(" courtFee Updated Successfully!");
							$scope.courtFee="";
							if($scope.draftId!=null && $scope.draftId!=undefined){
								getCourtFee($scope.draftId);	
							}
							else{
								getCourtFee($scope.registerCase.rcd_id);
							}
				   }
		
				
			});	
		}
		
		
		
		
		
		$scope.deleteTrialCourt=function(id){
			  var result=confirm("Are you really want to Remove");
				if (result) {
			   $http({
				method : 'DELETE',
				url : urlBase + 'ecourt_add_case/deleteTrialCourt/' + id + '/'
			}).success(function(res) {
				   if(res.response=="TRUE"){
				    alert("  trial Court Deleted Successfully!");
				    if($scope.draftId!=null && $scope.draftId!=undefined){
						getTrialCourt($scope.draftId);	
					}
					else{
						getTrialCourt($scope.registerCase.rcd_id);
					}
				 	
				   }
		
				
			});	
		}
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		/** ********************** Trial Court Code Ends********************** */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	$scope.criminalShow=false;
	$scope.changeCase=function(event)
{
		if(event.target.id=="civil"){
			
			$scope.criminalShow=false;
			
		}
		else{
			
			$scope.criminalShow=true;
			
		}
		
};




        $scope.motorShow=false;
          $scope.taxShow=false;
          $scope.landShow=true;
		$scope.changeMatters=function(event){
			if(event.target.id=="land"){
				$scope.motorShow=false;
				$scope.taxShow=false;
				$scope.landShow=true;
			
			}else if(event.target.id=="motor"){
				$scope.motorShow=true;
				$scope.landShow=false;
				$scope.taxShow=false;
		}
			else{
				$scope.taxShow=true;
				$scope.motorShow=false;
				$scope.landShow=false;
				
				
			}
					
			};
		
			
	
	 $scope.caseTypeOptions= [
			      {id: '1', value: 'Option A'},
			      {id: '2', value: 'Option B'},
			      {id: '3', value: 'Option C'}
			    ];
	 
		$scope.caseDetail= $scope.caseTypeOptions[0].value;
		
		////////////////////////////////////////////Document Upload Start//////////////////////////////////////////////
		
		$scope.documentTypes={};
		$scope.indexField={};
		$scope.picFile='';
		getDocumentTypes();
		
		$scope.selectedNode="";
		
		$scope.showSelected = function(sel)
	    {
	        $scope.selectedNode = sel;
	    };
		
	     function getDocumentTypes(){
	    	 
				$http.get(urlBase+'ecourt_add_case/getDocumentTypes').
		        success(function (data) {
		        	$scope.documentTypesList=data.modelList;
		        	console.log($scope.documentTypesList);
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
			};
			
			function getUploadedDocuments(){
				var id;
				if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
				{id=$scope.draftId;
				}else{id=$scope.registerCase.rcd_id}
			
		    	 
				$http.get(urlBase+'ecourt_add_case/getUploadedDocuments', {params : {'rcd_id' : id}}).
		        success(function (data) {
		        	$scope.uploadedDocumentsList=data.modelList;
		        	console.log($scope.uploadedDocumentsList);
		        }).
		        error(function(data, status, headers, config) {
		        	console.log("Error in getting tree data");
		        });
			};
			
			$scope.onFileSelect = function ($files) {
		        $scope.uploadProgress = 0;
		        $scope.selectedFile = $files;
		    };
		    $scope.files = [];

		    //listen for the file selected event
		    $scope.$on("fileSelected", function (event, args) {
		    	$scope.$apply(function () {            
		            $scope.files.push(args.file);
		        });
		    });
		    
			$scope.uploadFile=function() 
			{
				if($scope.registerCase.rcd_id==null || angular.isUndefined($scope.registerCase.rcd_id))
			{
				alert("First add Casefile details");
				return false;
			}
				$scope.buttonDisabled=true;
				  var file=$scope.picFile;
				  if($scope.selectedNode!=""){
						$scope.document.folder_id=$scope.selectedNode.id;
					}
				    file.upload = Upload.upload({
				      url: urlBase + 'ecourt_add_case/upload_file',
				      params:{rcd_id: $scope.registerCase.rcd_id},
				      headers: {
				    	  'optional-header': 'header-value'
				        },
				       fields:$scope.document,
		    		   file:file,
				    });
		if(file.upload){
				    file.upload.then(function (response) {
				    	$scope.buttonDisabled=false;
				        if(response.data.response=="TRUE"){
				        //	$scope.errorlist =null;
				        	$scope.errorlist = response.data.dataList;
				        	console.log($scope.errorlist);
				        	//bootbox.alert("Uploading Done");
				        //	$("#documentCreate").modal("hide");
				        	$scope.document={};
				        	//window.location.reload();
				        	
				        			alert("Files Uploaded successfully...");
				        			getUploadedDocuments();
				        			$scope.picFile='';	
				        	
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
			}else{$scope.buttonDisabled=false;}

				      
				}
		
			
			$scope.deleteDocument=function(id){
				  var result=confirm("Are you really want to Remove");
					if (result) {
				   $http({
					method : 'DELETE',
					url : urlBase + 'ecourt_add_case/deleteDocument/' + id + '/'
				}).success(function(res) {
					   if(res.response=="TRUE"){
						alert(" Document Deleted Successfully!");
						if($scope.registerCase.rcd_id!=null && $scope.registerCase.rcd_id!=undefined){
						//getDocument($scope.registerCase.rcd_id);
							getUploadedDocuments();
						}
						
					   }
					
				});	
			}
			
			}
		
		$scope.searchCaveat=function(){
			 $http.get(urlBase+ 'ecourt/searchCaveat', {
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
			 $http.get(urlBase+ 'ecourt/searchCaveatold', {
					params : {
						'docId' : $scope.registerCase.rcd_id
					}
				}).success(function(data, status, headers, config) {
			                
			              $scope.oldcaveatDataList = data.modelList;
			              
			               
			}).error(function(data, status, headers, config) {
				});

		}
		/////////////////Document Upload Close///////////////
	
	 
	 
	
}]);