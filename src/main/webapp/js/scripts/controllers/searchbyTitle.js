var DocumentApp = angular.module("EDMSApp", [ 'ui.bootstrap',
		'ng-bootstrap-datepicker', 'smart-table', 'ngSanitize', 'ngCsv' ]);

DocumentApp.controller("searchbyTitle", function($scope, $http, $filter) {

	$scope.model = {};
	$scope.branchDataList = {};
	$scope.reportList = [];
	$scope.reportListData = {};
	var baseUrl = "/dms/";

	$scope.branchDataList = {};
	$scope.branchCode;
	$scope.searchfor;
	$scope.byname;

	getBenchdata();

	$scope.today = function() {
		$scope.model.fromDate = new Date();
		$scope.model.toDate = new Date();
	};

	$scope.today();

	$scope.clear = function() {
		$scope.model.fromDate = null;
		$scope.model.toDate = new Date();
	};

	$scope.open = function($event, opened) {
		$event.preventDefault();
		$event.stopPropagation();

		$scope[opened] = true;
	};

	$scope.dateOptions = {
		formatYear : 'yy',
		startingDay : 1
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

	}
	;

	$scope.formats = [ 'dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate' ];
	$scope.format = $scope.formats[0];
	function convertDate(inputFormat) 
 	{
 		  function pad(s) { return (s < 10) ? '0' + s : s; }
 		  var d = new Date(inputFormat);
 		 return [ pad(d.getDate()),pad(d.getMonth()+1),d.getFullYear() ].join('/');
 	}
	
	$scope.getsearchByTitle = function() {

		console.log("----branch,inward_date-----");
		
		if($scope.model.fromDate!=null){
			$scope.model.fromDate=convertDate($scope.model.fromDate);
		}
		if($scope.model.toDate!=null){
			$scope.model.toDate=convertDate($scope.model.toDate);
		}
		
		$http.get(baseUrl+'report/getsearchByTitle ', {
			params : {
				'counsel' : $scope.model.counsel,
				'counselName'  : $scope.model.counselName,
				'benchCodeId'  : $scope.model.benchCodeId,
				'fromDate'  : $scope.model.fromDate,
				'toDate' :  $scope.model.toDate
			}
		}).success(function(data) {
			console.log("----getsearchByTitle-------");
			console.log(data);
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			
			// $scope.SearchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});

	};

});

DocumentApp.filter('dateFormat1', function($filter) {
	return function(input) {
		if (input == null) {
			return "";
		}

		var _date = $filter('date')(new Date(input), 'dd/ MM /yyyy');

		return _date.toUpperCase();

	};
});