var DocumentApp = angular.module("EDMSApp", [ 'ui.bootstrap',
		'ng-bootstrap-datepicker', 'smart-table', 'ngSanitize', 'ngCsv' ]);

DocumentApp.controller("fullBenchJudgmentOrderSearchCtrl", function($scope,
		$http, $filter) {

	$scope.model = {};
	$scope.branchDataList = {};
	$scope.reportList = [];
	$scope.reportListData = {};
	var baseUrl = "/dms/";

	
	$scope.firstJudge;
	$scope.secondJudge;
	$scope.thirdJudge;
	$scope.judgeType;
	$scope.judgmentType;
	$scope.judgeDataList = {};

	getJudgeNamedata();

	

	$scope.today = function() {
		$scope.model.fromdate = new Date();
		$scope.model.todate = new Date();
	};

	$scope.today();

	$scope.clear = function() {
		$scope.model.fromdate = null;
		$scope.model.todate = new Date();
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
	
	function getJudgeNamedata() {
		var response = $http.get(baseUrl+'report/getjudgeDetails');
		response.success(function(data, status, headers, config) {
			console.log("--------Judge Names ------")
			console.log(data);
			$scope.judgeDataList = data.JudgeName;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	};

	$scope.getsearchByfullBenchJudgmentOrder = function() {

		console.log("----branch,inward_date-----");
		console.log($scope.model);
		var date = new Date($scope.model.fromdate);
		var date1 = new Date($scope.model.todate);
		$http.get(baseUrl+'report/getsearchByfullBenchJudgmentOrder', {
			params : {
				'ib_inward_date' : date.toUTCString(),
				'ib_inwrd_date' : date1.toUTCString(),
				'firstJudge' : $scope.firstJudge,
				'secondJudge' : $scope.secondJudge,
				'thirdJudge' : $scope.thirdJudge,
				'judgeType' : $scope.judgeType,
				'judgmentType' : $scope.judgmentType
				
			}
		}).success(function(data) {
			console.log("----searchByfullBenchJudgmentOrder-------");
			console.log(data);
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			
			//$scope.SearchList = data.SearchedData;
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
