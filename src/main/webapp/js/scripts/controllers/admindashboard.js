var EDMSApp = angular.module("EDMSApp", ['chart.js','smart-table']);


EDMSApp.controller('dashboardCtrl', ['$scope','$http', function ($scope,$http) {
	
	$scope.masterdata = [];
	$scope.masterentity = {};
	$scope.folders=[];
	var baseUrl="/dms/";
	$scope.foldersdata = [];
	$scope.foldersCollection = [];
	loadMasterData();
	getFolderStructure();
	//$scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales", "Tele Sales", "Corporate Sales"];
	//$scope.data = [300, 500, 100, 40, 120];
	function loadMasterData() {
		var response = $http.get(baseUrl+'dashboard/getreport');
		response.success(function(data, status, headers, config) {		
			console.log("get Report");
			$scope.masterdata= data.modelList;
			
			$scope.labels=[$scope.masterdata[0].parameter1,$scope.masterdata[0].parameter2,$scope.masterdata[0].parameter3,$scope.masterdata[0].parameter4,$scope.masterdata[0].parameter5];
			$scope.data	=[$scope.masterdata[0].value1,$scope.masterdata[0].value2,$scope.masterdata[0].value3,$scope.masterdata[0].value4,$scope.masterdata[0].value5];
$scope.options = {
				  showTooltips: false,
				};
			console.log("-----------*----------------");
			console.log($scope.masterdata);
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	function getFolderStructure() {
		var response = $http.get(baseUrl+'folder/getfoldersbysize');
		response.success(function(data, status, headers, config) {		
			$scope.foldersdata = data;
        	$scope.foldersCollection = [].concat($scope.foldersdata);
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
}]);