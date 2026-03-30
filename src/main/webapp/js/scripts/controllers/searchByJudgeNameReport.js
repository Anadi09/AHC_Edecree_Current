var DocumentApp = angular.module("EDMSApp", [ 'ui.bootstrap',
		'ng-bootstrap-datepicker', 'smart-table', 'ngSanitize', 'ngCsv' ,'angularjs-dropdown-multiselect']);


DocumentApp.controller("searchByJudgeNameReportCtrl", function($scope, $http,
		$filter) {

	$scope.masterdata = [];
	$scope.masterentity = {};
	$scope.model = {};

	$scope.validationmodel = []; 
	$scope.reportList = [];
	$scope.judgereportListData=[];
	var baseUrl = "/dms/";

	$scope.judgeName;
	$scope.branchCode;

	$scope.branchDataList = {};
	$scope.judgeDataList = {};

	getBenchdata();
	getJudgeNamedata();

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

	function getJudgeNamedata() {
		var response = $http.get(baseUrl+'report/getjudgeDetails');
		response.success(function(data, status, headers, config) {
			console.log("--------Judge Names ------")
			console.log(data);
			$scope.judgeDataList = data.JudgeName;
			
			//$scope.example1data = [ {id: 1, label: "David"}, {id: 2, label: "Jhon"}, {id: 3, label: "Danny"}]; 
			 $scope.obj=[];
			  for(var i=0;i<data.JudgeName.length;i++){
				  $scope.tempobj={
						  id:data.JudgeName[i].jg_id,
						  label:data.JudgeName[i].jg_name,
						 	  
				  }
				  
				  $scope.obj.push($scope.tempobj);	
			  }
			

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
	$scope.getSearchByJudgeNameReportData = function() {
		console.log("----Judge,Data-----");
		
		if($scope.model.fromDate!=null){
			$scope.model.fromDate=convertDate($scope.model.fromDate);
		}
		if($scope.model.toDate!=null){
			$scope.model.toDate=convertDate($scope.model.toDate);
		}
		
		var strids="";
		angular.forEach($scope.validationmodel,function(value,index){
			id=value.id;
			strids=strids+id+",";
		});
		$scope.ids=strids.replace(/,\s*$/, "");
		if($scope.ids==""){
			bootbox.alert('Please Select Atleast One Judgename');
			return false;
		}
		console.log("tttttt id");
		console.log($scope.ids);
		$http.get(baseUrl+'report/getSearchByJudgeNameReportData', {
			params : {
				'judgeId' : $scope.ids,
				'benchCodeId' : $scope.model.benchCodeId,
				'fromDate' : $scope.model.fromDate,
				'toDate' : $scope.model.toDate
			}
		}).success(function(data) {
			console.log("----Judge Name ReportData-------")
			console.log(data);
			
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			
			console.log("ReportData");
			console.log($scope.ReportData);
			
			
		//	debugger;
				for(var i=0;i<$scope.ReportData.length;i++){
//				var myDate=$scope.ReportData[i].parameter2;
//					myDate=myDate.split("/");
//					var newDate=myDate[2]+"/"+myDate[1]+"/"+myDate[0];
				$scope.judgereportList = {
						//caseNo','Case No','Case Year','First Petitioner','First Respondent','Case Type','Judgement Date
						'caseNo':$scope.ReportData[i].caseNo,
						'caseYear':$scope.ReportData[i].caseYear,						
						'firstPetitioner':$scope.ReportData[i].firstPetitioner,
						'firstRespondent': $scope.ReportData[i].firstRespondent,
						'caseType':$scope.ReportData[i].caseType,
						'judgementDate': $scope.ReportData[i].judgementDate						
						
				};					
					$scope.judgereportListData.push($scope.judgereportList);
					
				}
	
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
