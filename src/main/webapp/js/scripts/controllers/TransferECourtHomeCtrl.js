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



EDMSApp.controller('TransferECourtHomeCtrl',['$scope','$http',function ($scope, $http) {
	var baseUrl="/dms/";
	$scope.causelist_date = convertDate(new Date());
	$scope.countadd =0;
	$scope.countsupp =0;
	//loadMasterData2();
	//$scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"];
	//$scope.data = [300, 500, 100, 40, 120];
	$scope.dailycount=0;
	$scope.freshcount=0;
	$scope.backlogcount=0;
	$scope.supplementarycount=0;
	$scope.additionalcount=0;
	$scope.applicationcount=0;
	$scope.correctioncount=0;
	$scope.divShow=false;
	$scope.listDataForSupp =[];
	$scope.listDataForAdd =[];
	
	
	$scope.listData =[];
	
	
	/*function loadMasterData2() {
		
		$scope.listDataForAdd =[];
		var response = $http.get(baseUrl+'ecourt/getreportforadditionallistsForTransferCases?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.listDataForAdd= data.modelList;
			console.log("Dataaaaaaaa",$scope.listDataForAdd);
			  angular.forEach($scope.listDataForAdd, function(object, key) {
				  $scope.countadd = $scope.countadd + object.count;
				  
			  
		  });
			  
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};*/
	
/*function loadMasterData5() {
		
		$scope.listDataForSupp =[];
		var response = $http.get(baseUrl+'ecourt/getreportforsupplementarylistForTransferCases?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.listDataForSupp= data.modelList;
			console.log("Dataaaaaaaa",$scope.listDataForSupp);
			  angular.forEach($scope.listDataForSupp, function(object, key) {
				  $scope.countsupp = $scope.countsupp + object.count;
				  $scope.courtForAdd =object.courtMaster.cm_name ;
				  
			  
		  });
			  
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};*/
	
	//loadMasterData5();
	
	$scope.viewList =function(data){
		 console.log("function run",data);
		 window.open(baseUrl+"causelist/typeForTrasnfer/"+data.cl_list_type_mid+"/"+data.cl_court_no,"_blank");
		 
		 
	 }
	
	/*function pushToArray(arr, obj) {
	    const index = arr.findIndex((e) => e.cl_court_no == obj.cl_court_no  && e.cl_list_type_mid == obj.cl_list_typr_mid);

	    if (index === -1) {
	        arr.push(obj);
	    } else {
	        arr[index] = obj;
	    }
	}*/
	
	
	// this function used when using arrow function 
	
	/*function listExists(arr, obj) {
	    const index = arr.findIndex((e) => e.cl_court_no == obj.cl_court_no  && e.cl_list_type_mid == obj.cl_list_type_mid);

	    return index;
	}*/
	

	
	
	/*function listExists(arr, obj) {
	    const index = arr.findIndex((e) => e.cl_court_no == obj.cl_court_no  && e.cl_list_type_mid == obj.cl_list_type_mid);

	    return index;
	}*/
	
	
	/*function listExists(myArray, obj){
		
		
	
	    for (var i=0; i < myArray.length; i++) {
	    	
	        if (myArray[i].cl_court_no == obj.cl_court_no && myArray[i].cl_list_type_mid == obj.cl_list_type_mid) {
	        	
	            return i;
	        }
	        else {
	        	return -1;
	        }
	    }
	}*/
	
	
	function listExists(myArray, obj){
		
		
	var indexValue =	myArray.findIndex(function matchElemet(arr){
		
		return arr.cl_court_no == obj.cl_court_no && arr.cl_list_type_mid == obj.cl_list_type_mid;
		
		})
		
	    
		return indexValue;
	}
	
	
	
	function loadMasterData3() {
		$scope.showTransfer =false;
		var countadd =0;
		$scope.transferCases =[];
		var response = $http.get(baseUrl+'ecourt/getTranferCases?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {
			var b =data.modelList;
			console.log("main dataaaaaaaaaa",b);
			var a= data.modelList
			
			var courtArray =[];
			resultArray =[];
			for(var i =0 ;i< a.length ; i++){
				if(a[i].cl_list_type_mid == 7 || a[i].cl_list_type_mid == 11 || a[i].cl_list_type_mid == 12 || 
						a[i].cl_list_type_mid == 13 || a[i].cl_list_type_mid == 14 || a[i].cl_list_type_mid == 15 ){
					
					if(resultArray.length > 0){
						a[i].cl_list_type_mid =7;
						var index =listExists(resultArray, a[i]);
						console.log("INdex found",index);
						if(index != -1){
							console.log("Index Found",a[index])
							resultArray[index].count =a[i].count+resultArray[index].count;
						}
						else {
							resultArray.push(a[i]);
						}
					}else {
						console.log("in else conditon");
						a[i].cl_list_type_mid =7
						resultArray.push(a[i]);
						
						
						
					}
					
					
					
					
				}
				else if(a[i].cl_list_type_mid == 6 || a[i].cl_list_type_mid == 8 || a[i].cl_list_type_mid == 9 || a[i].cl_list_type_mid == 10  ){
					if(resultArray.length > 0){
						a[i].cl_list_type_mid =6;
						var index =listExists(resultArray, a[i]);
						console.log("INdex found",index);
						if(index != -1){
							console.log("Index Found",a[index])
							a[index].count =a[i].count+a[index].count;
						}
						else {
							resultArray.push(a[i]);
						}
					}else {
						console.log("in else conditon");
						a[i].cl_list_type_mid =6
						resultArray.push(a[i]);
						
						
						
					}
				}
				else if(a[i].cl_list_type_mid == 5 || a[i].cl_list_type_mid == 16 || a[i].cl_list_type_mid == 17 || a[i].cl_list_type_mid == 18  ){
					if(resultArray.length > 0){
						a[i].cl_list_type_mid =5;
						var index =listExists(resultArray, a[i]);
						console.log("INdex found",index);
						if(index != -1){
							console.log("Index Found",a[index])
							a[index].count =a[i].count+a[index].count;
						}
						else {
							resultArray.push(a[i]);
						}
					}else {
						console.log("in else conditon of fresh");
						a[i].cl_list_type_mid =5
						resultArray.push(a[i]);
						
						
						
					}
				}
				else {
					resultArray.push(a[i]);
					
				}
				
			}
			console.log("Resulted Array",resultArray);
			
			$scope.transferCases= resultArray;
			console.log("Dataaaaaaaa",$scope.transferCases);
			
			if(false){
				 if($scope.listDataForAdd.length != 0){
					  for(var  i =0 ; i< $scope.listDataForAdd.length ; i++){
						  $scope.listDataForAdd[i].transferFrom ="Transferred Additional From "+$scope.listDataForAdd[i].courtMaster.cm_name;
						 
					  }
				
				  }
			}
			else {
				console.log("condition false");
			  angular.forEach($scope.transferCases, function(object, key) {
				  if(object.cl_list_type_mid==5){
					  object.transferFrom ="Transferred Fresh Cases From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
					 
				  }
				  if(object.cl_list_type_mid==3){
					  object.transferFrom ="Transferred Cause List From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				  if(object.cl_list_type_mid==1){
					  object.transferFrom ="Transferred Application From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				  if(object.cl_list_type_mid==2){
					  object.transferFrom ="Transferred Correction Application From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				 
				  if(object.cl_list_type_mid==4){
					  object.transferFrom ="Transferred Backlog From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				 
				  if(object.cl_list_type_mid==6){
					  object.transferFrom ="Transferred Supplementary From "+object.courtMaster.cm_name
					  object.count = object.count + $scope.countsupp;
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				  if(object.cl_list_type_mid==7  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  /*if(object.cl_list_type_mid==11  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==12 ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==13  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==14  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==15  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}*/
				  
			  
		  });
			  
			  
			  
		}
			 
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	
	/*function loadMasterData3() {
		$scope.showTransfer =false;
		var countadd =0;
		$scope.transferCases =[];
		var response = $http.get(baseUrl+'ecourt/getTranferCases?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {
			var a= data.modelList
			console.log("original dataaaaa",a);
			var courtArray =[];
			resultArray =[];
			for(var i =0 ;i< a.length ; i++){
				if(a[i].cl_list_type_mid == 7 || a[i].cl_list_type_mid == 11 || a[i].cl_list_type_mid == 12 || 
						a[i].cl_list_type_mid == 13 || a[i].cl_list_type_mid == 14 || a[i].cl_list_type_mid == 15 ){
					
					if(resultArray.length > 0){
						a[i].cl_list_type_mid =7;
						var index =listExists(resultArray, a[i]);
						console.log("INdex found",index);
						if(index != -1){
							console.log("Index Found",a[index])
							a[index].count =a[i].count+a[index].count;
						}
						else {
							resultArray.push(a[i]);
						}
					}else {
						console.log("in else conditon");
						a[i].cl_list_type_mid =7
						resultArray.push(a[i]);
						
						
						
					}
					
					
					
					
				}
				else if(a[i].cl_list_type_mid == 6 || a[i].cl_list_type_mid == 8 || a[i].cl_list_type_mid == 9 || a[i].cl_list_type_mid == 10  ){
					if(resultArray.length > 0){
						a[i].cl_list_type_mid =6;
						var index =listExists(resultArray, a[i]);
						console.log("INdex found",index);
						if(index != -1){
							console.log("Index Found",a[index])
							a[index].count =a[i].count+a[index].count;
						}
						else {
							resultArray.push(a[i]);
						}
					}else {
						console.log("in else conditon");
						a[i].cl_list_type_mid =6
						resultArray.push(a[i]);
						
						
						
					}
				}
				else if(a[i].cl_list_type_mid == 5 || a[i].cl_list_type_mid == 16 || a[i].cl_list_type_mid == 17 || a[i].cl_list_type_mid == 18  ){
					if(resultArray.length > 0){
						a[i].cl_list_type_mid =5;
						var index =listExists(resultArray, a[i]);
						console.log("INdex found",index);
						if(index != -1){
							console.log("Index Found",a[index])
							a[index].count =a[i].count+a[index].count;
						}
						else {
							resultArray.push(a[i]);
						}
					}else {
						console.log("in else conditon");
						a[i].cl_list_type_mid =5
						resultArray.push(a[i]);
						
						
						
					}
				}
				else {
					resultArray.push(a[i]);
					
				}
				
			}
			console.log("Resulted Array",resultArray);
			
			$scope.transferCases= resultArray;
			console.log("Dataaaaaaaa",$scope.transferCases);
			
			if(false){
				 if($scope.listDataForAdd.length != 0){
					  for(var  i =0 ; i< $scope.listDataForAdd.length ; i++){
						  $scope.listDataForAdd[i].transferFrom ="Transferred Additional From "+$scope.listDataForAdd[i].courtMaster.cm_name;
						 
					  }
				
				  }
			}
			else {
				console.log("condition false");
			  angular.forEach($scope.transferCases, function(object, key) {
				  if(object.cl_list_type_mid==5){
					  object.transferFrom ="Transferred Fresh Cases From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
					 
				  }
				  if(object.cl_list_type_mid==3){
					  object.transferFrom ="Transferred Cause List From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				  if(object.cl_list_type_mid==1){
					  object.transferFrom ="Transferred Application From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				  if(object.cl_list_type_mid==2){
					  object.transferFrom ="Transferred Correction Application From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				 
				  if(object.cl_list_type_mid==4){
					  object.transferFrom ="Transferred Backlog From "+object.courtMaster.cm_name
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				 
				  if(object.cl_list_type_mid==6){
					  object.transferFrom ="Transferred Supplementary From "+object.courtMaster.cm_name
					  object.count = object.count + $scope.countsupp;
					  if(object.count >= 1){
						  $scope.showTransfer =true;
					  }
				  }
				  if(object.cl_list_type_mid==7  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==11  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==12 ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==13  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==14  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  if(object.cl_list_type_mid==15  ){
					  object.transferFrom ="Transferred Additional From "+object.courtMaster.cm_name;
					  
					  if(object.count >= 1){
						 
						  $scope.showTransfer =true;
					  }
					  
					}
				  
			  
		  });
			  
			  
			  
		}
			 
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};*/
	
	loadMasterData3();
	
	
	
	
	
	function loadMasterData() {
		var response = $http.get(baseUrl+'ecourt/getreport?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.masterdata= data.modelList;
			
			angular.forEach($scope.masterdata, function(object, key) {
				  
				  if(object.cl_list_type_mid==1){
					  $scope.applicationcount=object.count
				  }
				  if(object.cl_list_type_mid==2){
					  $scope.correctioncount=object.count
				  }
				  if(object.cl_list_type_mid==3){
					  $scope.dailycount=object.count
				  }
				  if(object.cl_list_type_mid==4){
					  $scope.backlogcount=object.count
				  }
				  if(object.cl_list_type_mid==5){
					  $scope.freshcount=object.count
				  }
				  if(object.cl_list_type_mid==6){
					  $scope.supplementarycount=object.count
				  }
				  if(object.cl_list_type_mid==7){
					$scope.additionalcount=object.count;					 
					}
					  
					
				});
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	function loadMasterData1() {
		var response = $http.get(baseUrl+'ecourt/downloadCauseList?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.masterdata= data.modelList;
			angular.forEach($scope.masterdata, function(object, key) {
				  
				  if(object.cl_list_type_mid==1){
					  $scope.applicationcount=object.count
				  }
				  if(object.cl_list_type_mid==2){
					  $scope.correctioncount=object.count
				  }
				  if(object.cl_list_type_mid==3){
					  $scope.dailycount=object.count
				  }
				  if(object.cl_list_type_mid==4){
					  $scope.backlogcount=object.count
				  }
				  if(object.cl_list_type_mid==5){
					  $scope.freshcount=object.count
				  }
				  if(object.cl_list_type_mid==6){
					  $scope.supplementarycount=object.count
				  }
				  if(object.cl_list_type_mid==7){
					  $scope.additionalcount=object.count
				  }
				});
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	
	$scope.doownloadCauseList =function (){
		
		  console.log("function invokedddddgggg");
		  loadMasterData1();
	  }
	
	
	
	$scope.getCauseList=function(cause_list_date){
		$scope.causelist_date=convertDate(cause_list_date);
		loadMasterData();
		
		console.log("hello"+cause_list_date);
		
	}
	
	$scope.show=function (){
		
		
		$scope.divShow=true;
	
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
		
		function convertDate(inputFormat) 
		{
			  function pad(s) { return (s < 10) ? '0' + s : s; }
			  var d = new Date(inputFormat);
			  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
		}
}]);