var DocumentApp = angular.module("EDMSApp", ['ui.bootstrap','ng-bootstrap-datepicker','smart-table','ngSanitize', 'ngCsv']);


DocumentApp.controller("searchByJudgementDateCtrl",function($scope, $http,$filter) {


	$scope.model={};
	$scope.branchDataList={};
	$scope.reportList=[];
	$scope.reportListData={};
	var baseUrl="/dms/";
	 
	$scope.branchDataList = {};
	$scope.branchCode;
	
	
	getBenchdata();
	
	$scope.today = function() {
		$scope.model.fromDate = new Date();
		$scope.model.toDate = new Date();
	};
	
	$scope.today();
	
	$scope.clear = function () {
		$scope.model.fromDate = null;
		$scope.model.toDate = new Date();
	};	

	 $scope.open = function($event,opened) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[opened] = true;
		  };
    	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	};
	
	
	function getBenchdata() {
		var response = $http.get(baseUrl+'report/getbenchcode');
		response.success(function(data, status, headers, config) {
			console.log("--------branch data------")
			console.log(data);
			$scope.branchDataList = data.branchData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	};

	 $scope.formats = ['dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate'];
     $scope.format = $scope.formats[0];
	
	

     function convertDate(inputFormat) 
 	{
 		  function pad(s) { return (s < 10) ? '0' + s : s; }
 		  var d = new Date(inputFormat);
 		  return [ pad(d.getDate()),pad(d.getMonth()+1),d.getFullYear() ].join('/');
 	}
	$scope.getsearchByJudgmentData = function() {
		
		if($scope.model.fromDate!=null){
			$scope.model.fromDate=convertDate($scope.model.fromDate);
		}
		if($scope.model.toDate!=null){
			$scope.model.toDate=convertDate($scope.model.toDate);
		}
		
		console.log("----branch,inward_date-----");
		console.log($scope.model);
		
		$http.get(baseUrl+'report/getsearchByJudgmentData',
				{params: 
				{'fromDate':$scope.model.fromDate,
				'toDate':$scope.model.toDate,
				'benchCodeId':$scope.model.benchCodeId}}
				).success(function(data) {
			console.log("----getsearchByJudgmentData-------");
			console.log(data);
			$scope.ReportData=data.modelList;
			$scope.displayedCollection= [].concat($scope.ReportData);
		
			//$scope.SearchList = data.SearchedData;
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

