var DocumentApp = angular.module("EDMSApp", [ 'ui.bootstrap', 'smart-table',
		'ngSanitize', 'ngCsv' ]);

DocumentApp.controller("searchByCaseNumberCtrl", function($scope, $http,
		$filter) {


	$scope.model = {};

	$scope.reportList = [];
	$scope.reportListData = {};
	var baseUrl = "/dms/";

	$scope.branchDataList = {};
	$scope.years = [];
	$scope.fromYear;
	$scope.branchCode;
	$scope.casetype;
	$scope.casenumber;
	$scope.errorlist=[];

	getYears();
	getCaseTypedata();
	

	getBenchdata();
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

	function getCaseTypedata() {
		var response = $http.get(baseUrl+'report/getcasetypedata');
		response.success(function(data, status, headers, config) {
			console.log("--------Case Type data------")
			console.log(data);
			$scope.casetypeDataList = data.casetypeData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	;

	function getYears() {
		var currentYear = new Date().getFullYear();
		for (var year = 1840; year <= currentYear; year++) {
			$scope.years.push(year);
			
		}
	}

/*	$scope.getSearchByCaseNumberData = function() {

		console.log("----getcasetypedata-----");

		var response =  $http.get(baseUrl + 'report/getSearchByCaseNumberData ', {
			params : {
				'flag1' : $scope.fromYear,
				'flag2' : $scope.branchCode,
				'flag3' : $scope.casetype,
				'casenumber' :$scope.casenumber
			}
		});
			response.success(function(data) {
				if(data.response=="FALSE"){					
					$scope.errorlist=data.dataMapList;					
					$.each($scope.errorlist, function(k, v) {
		                    $("#"+k).parent().parent().addClass('has-error');
		            });
			 }else{		
				
			console.log("----CriticalReportData-------");
			console.log(data);
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			console.log("----********FirstDEReportData*******------");
			console.log($scope.ReportData);
			$scope.dailyreportListData = [];

			for (var i = 0; i < data.modelList.length; i++) {

				$scope.dailyreportList = {

					'parameter1' : data.modelList[i].parameter1,
					'parameter2' : data.modelList[i].parameter2,
					'parameter3' : data.modelList[i].parameter3,
					'parameter4' : data.modelList[i].parameter4,
					'parameter5' : data.modelList[i].parameter5,
				'parameter6': data.modelList[i].parameter6,
				'parameter7': data.modelList[i].parameter7,

				};
				$scope.dailyreportListData.push($scope.dailyreportList);

			}
			 }
			//$scope.SearchList = data.SearchedData;
		});
			response.error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});

	};*/
	
$scope.getSearchByCaseNumberData = function() {

		console.log("----getcasetypedata-----");

		$http.get(baseUrl + 'report/getSearchByCaseNumberData ', {
			params : {
				'benchCodeId' : $scope.model.benchCodeId,
				'caseTypeId' : $scope.model.caseTypeId,
				'year' : $scope.model.year,
				'caseNumber' :$scope.model.caseNumber
			}
		}).success(function(data) {
			console.log("----CriticalReportData-------");
			console.log(data);
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			console.log("----********FirstDEReportData*******------");
			console.log($scope.ReportData);
			$scope.dailyreportListData = [];

		}).error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});

	};

});
