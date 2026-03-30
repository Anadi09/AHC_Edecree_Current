var EDMSApp = angular.module('EDMSApp', []);
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



EDMSApp.controller('AdminHomeCtrl',['$scope','$http',function ($scope, $http) {
	  var urlBase="/adminpanel/";
	  
	  var baseUrl="/dms/";
		$scope.causelist_date = convertDate(new Date());
	/*	loadMasterData();*/
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
						  $scope.additionalcount=object.count
					  }
					});
				
			});
			response.error(function(data, status, headers, config) {
				//alert("Error");
			});
			
		};
		
		
		
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