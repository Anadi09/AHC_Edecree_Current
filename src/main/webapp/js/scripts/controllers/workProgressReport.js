var DocumentApp = angular.module("EDMSApp", ['ui.bootstrap','smart-table','ngSanitize', 'ngCsv']);


DocumentApp.controller("workProgressReportCtrl",function($scope, $http,$filter) {
	
	$scope.masterdata = [];
	$scope.masterentity = {};
	$scope.model={};
	
	$scope.reportList=[];
	$scope.reportListData={};
	var baseUrl="/dms/";
	 
	//getWorkProgressReportData();
	
	$scope.today = function() {
	    $scope.dt = new Date();
	};
	$scope.today();
	
	$scope.clear = function () {
		$scope.dt = null;
	};	
	// Disable weekend selection
	/*$scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	};*/
	
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
	
	$scope.getWorkProgressReportData = function() {                                                                     
		console.log("----WorkProgress,Data-----");
		console.log($scope.model);
		if($scope.model.fromDate!=null){
			$scope.model.fromDate=convertDate($scope.model.fromDate);
		}
		if($scope.model.toDate!=null){
			$scope.model.toDate=convertDate($scope.model.toDate);
		}
		$http.get(baseUrl+'report/getWorkProgressReportData ',{params: {'fromDate':$scope.model.fromDate,'toDate':$scope.model.toDate}}).success(function(data) {
			console.log("----WorkProgress ReportData-------")
			console.log(data);
			$scope.ReportData=data.modelList;
			$scope.displayedCollection= [].concat($scope.ReportData);
			console.log("----********WorkProgressReportData*******------")
			console.log($scope.ReportData);
		$scope.dailyreportListData=[];
			
			for(var i=0;i<data.modelList.length;i++)
			{
			$scope.dailyreportList = {
					'parameter1':data.modelList[i].parameter1,
					'parameter2':data.modelList[i].parameter2,
					'parameter3':data.modelList[i].parameter3,
					'parameter4':data.modelList[i].parameter4,
					'parameter5':data.modelList[i].parameter5,
					'parameter6':data.modelList[i].parameter6,
					'parameter7':data.modelList[i].parameter7,
					'parameter8':data.modelList[i].parameter8,
					'parameter9':data.modelList[i].parameter9,
					'parameter10':data.modelList[i].parameter10,
					'parameter11':data.modelList[i].parameter11,
					'parameter12':data.modelList[i].parameter12,
					'parameter13':data.modelList[i].parameter13,
					'parameter14':data.modelList[i].parameter14,
					
					
			};					
				$scope.dailyreportListData.push($scope.dailyreportList);
				
			}
			
		}).error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});

	};
	
});



DocumentApp.filter('dateFormat1', function($filter)
		{
		 return function(input)
		 {
		  if(input == null){ return ""; } 
		 
		  var _date = $filter('date')(new Date(input), 'dd/ MM /yyyy');
		 
		  return _date.toUpperCase();

		 };
		});





