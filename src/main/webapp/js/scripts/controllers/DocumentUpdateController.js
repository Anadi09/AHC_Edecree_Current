/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['smart-table','ui.bootstrap','ngMask']);

edmsApp.directive('loading', ['$http', function ($http) {
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
/**
 * Controller in index.jsp
 */

edmsApp.controller("DocumentUpdateController",['$scope','$http', function($scope,$http) {
	var baseUrl="/dms/";
	$scope.folder={};
	$scope.search={};
	$scope.searchrowid=0;
	//getFoldersdata();
	//getRepositories();
	getMetafields();
    $scope.error;
    $scope.documentlist=[];
    $scope.metafields=[];
    $scope.usedmetafields=[];
    $scope.searchcriteria={};
    $scope.caseTypes=[];
    $scope.benchCodes=[];
    $scope.basicsearch={};
    $scope.years=[];
    $scope.documentlist=[];
    $scope.metafields=[];
    $scope.usedmetafields=[];
    $scope.subdocuments=[];
    $scope.searchcriteria={};
    $scope.date1='';
    $scope.date2='';
    $scope.doc_type='';
    getBenchdata();
    getCaseTypedata();
    getYears();
    getJudgeNamedata();
	function getBenchdata() {
		var response = $http.get(baseUrl+'report/getbenchcode');
		response.success(function(data, status, headers, config) {
			$scope.benchCodes = data.branchData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getCaseTypedata() {
		var response = $http.get(baseUrl+'report/getcasetypedata');
		response.success(function(data, status, headers, config) {
			$scope.caseTypes = data.casetypeData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getYears() {
		var currentYear = new Date().getFullYear();
		for (var year = 1950; year <= currentYear; year++) {
			$scope.years.push(year);
			
		}
	}
	function getJudgeNamedata() {
		var response = $http.get(baseUrl+'report/getjudgeDetails');
		response.success(function(data, status, headers, config) {
			$scope.judges = data.JudgeName;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	};

	function getMetafields(){
		$http.get(baseUrl+'metatemplate/getmetafields').
        success(function (data) {
        	$scope.metafields = data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	
	$scope.getBasicSearchData= function() {
		$http.get(baseUrl+'report/getBasicSearchData', {
			params : {
				'caseTypeId' : $scope.basicsearch.caseTypeId,
				'benchCodeId' : $scope.basicsearch.benchCodeId,
				'judgeId' : $scope.basicsearch.judgeId,
				'year' : $scope.basicsearch.year,
				'caseNumber' : $scope.basicsearch.caseNumber
			}
		}).success(function(data) {
			$scope.ReportData = data.modelList;
			$scope.displayedCollection = [].concat($scope.ReportData);
			$scope.documentlist=[];
			
		}).error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});
	};
	
	$scope.getsubdocuments=function(entity,doc_type)
	{
		$scope.doc_type=doc_type;
		
		$scope.document={'id':entity.fileId,'doc_type':doc_type};
		$http.post(baseUrl+'document/getsubdocuments',$scope.document).
        success(function (data) {
        	$scope.subdocuments = data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	
	$scope.deleteSubDocument=function(data,doc_type)
	{
		$scope.entity={};
		var action="";
		if(doc_type==2){
			$scope.entity={"jfd_id":data.jfd_id};
			action='document/deletejudgementfile';
		}
		if(doc_type==3){
			$scope.entity={"id":data.id};
			action='document/deletereopencasefile';
		}
		if(doc_type==4){
			$scope.entity={"mf_id":data.mf_id};
			action='document/deletemediafile';
		}
		$http.post(baseUrl+'document/deletejudgementfile',$scope.entity).
        success(function (data) {
        	if(data.response=="TRUE"){
        		bootbox.alert("Successfully deleted file");
        	}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	}
}]);