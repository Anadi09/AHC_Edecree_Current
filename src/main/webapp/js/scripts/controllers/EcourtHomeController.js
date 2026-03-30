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



EDMSApp.controller('ECourtHomeCtrl',['$scope','$http',function ($scope, $http) {
	var baseUrl="/dms/";
	$scope.causelist_date = convertDate(new Date());
	loadMasterData();
	//$scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"];
	//$scope.data = [300, 500, 100, 40, 120];
	$scope.dailycount=0;
	$scope.freshcount=0;
	$scope.backlogcount=0;
	$scope.supplementarycount=0;
	$scope.additionalcount=0;
	$scope.applicationcount=0;
	$scope.correctioncount=0;
	$scope.productioncount=0;
	$scope.tiacount=0;
	$scope.divShow=false;
	$scope.showProductionList =false;
	$scope.showtiaList =false;
	$scope.tdcount=0;
	$scope.showtdList =false;
	$scope.tfacount=0;
	$scope.showtfList =false;
	$scope.tscount=0;
	$scope.showtsList =false;
	$scope.tucount=0;
	$scope.showtuList =false;
	$scope.tu1count=0;
	$scope.showtu1List =false;
	
	$scope.listData =[];
	
	 $scope.open1 = function($event,type) {
		    $event.preventDefault();
		    $event.stopPropagation();
		    
		    if(type=="fromDate1")
		    	$scope.fromDate1= true;
		    if(type=="toDate1")
		    	$scope.toDate= true;
		};
	
	function loadMasterData2() {
		var countadd =0;
		$scope.listData =[];
		var response = $http.get(baseUrl+'ecourt/getreportforadditionallists?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.listData= data.modelList;
			console.log("Dataaaaaaaa",$scope.listData);
			  angular.forEach($scope.listData, function(object, key) {
				  countadd = countadd + object.count;
				  
			  
		  });
			  $scope.additionalcount= countadd+$scope.additionalcount;
			
			if($scope.listData.length > 0){
				//$scope.showTransfer = true;
			}
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	function getFreshMentionCount() {
		var countfreshmention =0;
		$scope.listData =[];
		var response = $http.get(baseUrl+'ecourt/getreportforfreshmentionlist?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.listData= data.modelList;
			console.log("Dataaaaaaaa",$scope.listData);
			  angular.forEach($scope.listData, function(object, key) {
				  countfreshmention = countfreshmention + object.count;
				  
			  
		  });
			  $scope.freshcount= countfreshmention+$scope.freshcount;
			
			if($scope.listData.length > 0){
				//$scope.showTransfer = true;
			}
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	loadMasterData5();
	function loadMasterData5() {
		var countadd =0;
		var response = $http.get(baseUrl+'ecourt/getreportforcorrectionlists?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.listData= data.modelList;
			console.log("Dataaaaaaaa",$scope.listData.length);
			 angular.forEach($scope.listData, function(object, key) {
				  countadd = countadd + object.count;
				  
			  
		  });
			 $scope.correctioncount=countadd+$scope.correctioncount;
			
			if($scope.listData.length != 0){
				 $scope.showList =true;
			}
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	
	getFreshMentionCount();
	function loadMasterData4() {
		var countadd =0;
		$scope.listData =[];
		var response = $http.get(baseUrl+'ecourt/getreportforsupplementarylist?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {		
			$scope.listData= data.modelList;
			console.log("Dataaaaaaaa",$scope.listData);
			  angular.forEach($scope.listData, function(object, key) {
				  countadd = countadd + object.count;
				  
			  
		  });
			  $scope.supplementarycount= countadd+$scope.supplementarycount;
			
			if($scope.listData.length > 0){
				//$scope.showTransfer = true;
			}
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	loadMasterData4();
	
	
	
	
	
	function loadMasterData3() {
		$scope.showTransfer =false;
		
		
		$scope.totalCases =null;
		var response = $http.get(baseUrl+'ecourt/getTranferCasesCount?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {	
			if(data.modelList.length > 0 && data.modelList[0] > 0)
				{
				$scope.totalCases= data.modelList[0];
			
			 $scope.showTransfer =true;
				}
			
	
			 
		
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	loadMasterData3();
	loadMasterData2();
	
	
	
	
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
				  
				  if(object.cl_list_type_mid==19){
						$scope.productioncount=object.count;
						
						if($scope.productioncount > 0){
							$scope.showProductionList =true;
						}
						}
				  if(object.cl_list_type_mid==26){
						$scope.tdcount=object.count;
						
						if($scope.tdcount > 0){
							$scope.showtdList =true;
						}
						}
				  if(object.cl_list_type_mid==27){
						$scope.tfcount=object.count;
						
						if($scope.tfcount > 0){
							$scope.showtfList =true;
						}
						}
				  
				  if(object.cl_list_type_mid==28){
						$scope.tiacount=object.count;
						
						if($scope.tiacount > 0){
							$scope.showtiaList =true;
						}
						}
				  if(object.cl_list_type_mid==29){
						$scope.tscount=object.count;
						
						if($scope.tscount > 0){
							$scope.showtsList =true;
						}
						}
				  if(object.cl_list_type_mid==30){
						$scope.tucount=object.count;
						
						if($scope.tucount > 0){
							$scope.showtuList =true;
						}
						}
				  if(object.cl_list_type_mid==31){
						$scope.tu1count=object.count;
						
						if($scope.tu1count > 0){
							$scope.showtu1List =true;
						}
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