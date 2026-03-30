var EDMSApp = angular.module("EDMSApp", []);


EDMSApp.controller('dashboardCtrl', ['$scope','$http', function ($scope,$http) {
	
	$scope.masterdata = [];
	$scope.masterentity = {};
	
	var baseUrl="/dms/";
	
	loadMasterData();
	
	function loadMasterData() {
		var response = $http.get(baseUrl+'dashboard/getreport');
		response.success(function(data, status, headers, config) {		
			console.log("get Report");
			$scope.masterdata= data.modelList;
			
			console.log("-----------*----------------");
			console.log($scope.masterdata);
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
}]);