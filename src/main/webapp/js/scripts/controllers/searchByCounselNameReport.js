var DocumentApp = angular.module("EDMSApp", [ 'ui.bootstrap',
		'ng-bootstrap-datepicker', 'smart-table', 'ngSanitize', 'ngCsv' ]);

DocumentApp.controller("searchByCounselNameReportCtrl", function($scope, $http,
		$filter) {

	$scope.masterdata = [];
	$scope.masterentity = {};
	$scope.model = {};
	$scope.reportList = [];
	$scope.reportListData = {};
	var baseUrl = "/dms/";

	$scope.counsel;
	$scope.con_Name;
	$scope.branchCode;

	$scope.branchDataList = {};

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

	$scope.formats = [ 'dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate' ];
	$scope.format = $scope.formats[0];

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
	function convertDate(inputFormat) 
	{
		  function pad(s) { return (s < 10) ? '0' + s : s; }
		  var d = new Date(inputFormat);
		  return [ pad(d.getDate()),pad(d.getMonth()+1),d.getFullYear() ].join('/');
	}
	$scope.getSearchByCounselNameReportData = function() {
		console.log("----Counsel,Data-----");
		console.log($scope.model);
		if($scope.model.fromDate!=null){
			$scope.model.fromDate=convertDate($scope.model.fromDate);
		}
		if($scope.model.toDate!=null){
			$scope.model.toDate=convertDate($scope.model.toDate);
		}
		
		$http.get(baseUrl+'report/getSearchByCounselNameReportData', {
			params : {
				'counsel' : $scope.model.counsel,
				'benchCodeId' : $scope.model.benchCodeId,
				'fromDate' : $scope.model.fromDate,
				'toDate' : $scope.model.toDate,
				'counselName' : $scope.model.counselName
			}
		}).success(function(data) {
			console.log("----Counsel Name ReportData-------")
			console.log(data);
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			
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
