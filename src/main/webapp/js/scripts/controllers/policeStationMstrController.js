var pdmsApp = angular.module("EDMSApp", [ 'smart-table' ]);

pdmsApp.controller("policeStationMstrCtrl", function($scope, $http) {

	getMasterdata();
	$scope.masterdata = [];
	$scope.displayedCollection = [];
	$scope.getpoliceStationLocationList = [];
	$scope.addpolicestation =[];
	getpoliceStationLocationList();
	//getbranchDetails();
	$scope.errorlist = [];
	function getMasterdata() {
		var response = $http
				.get('/dms/police_station_master/getpoliceStationDetails');
		response.success(function(data, status, headers, config) {
			$scope.masterdata = data;
			console.log("--GOT MASTER--");
			$scope.displayedCollection = [].concat($scope.masterdata);

		});
		response.error(function(data, status, headers, config) {
			// alert("Error");
		});

	}
	;

	$scope.setMasterdata = function(data) {
		console.log("SET DATA");
		console.log(data);
		getpoliceStationLocationList();
		$scope.addpolicestation = angular.copy(data);
		
		$scope.errorlist = [];

	};

	function getpoliceStationLocationList() {
		$http.get('/dms/police_station_master/getpoliceStationLocationList')
				.success(function(data) {
					console.log("--judge--");
					console.log(data);
					$scope.getpoliceStationLocationList = data;
				}).error(function(data, status, headers, config) {
					console.log("Error in getting Master");
				});
	}

	$scope.resetModel=function()
	{	
		$scope.addpolicestation = {};	
		$scope.addpolicestation.ps_rec_status=1;
		$scope.errorlist=[];
		getpoliceStationLocationList();
		
	};
	
    $scope.checkboxSelection = '1';
    $scope.isCheckboxSelected = function(index) {
        return index === $scope.checkboxSelection;
    };
    
	$scope.save = function(data) {
		$scope.addpolicestation = data;
		console.log($scope.addpolicestation);
		var response = $http.post('/dms/police_station_master/save',
				$scope.addpolicestation);
		response.success(function(data, status, headers, config) {
			if (data.response == "FALSE") {
				$scope.errorlist = data.dataMapList;
				$.each($scope.errorlist, function(k, v) {
					$("#" + k).parent().parent().addClass('has-error');
				});
			} else {

				$('#policeStation_Modal').modal('hide');
				bootbox.alert("Police Station added Successfully!");
				$scope.addpolicestation = {};
				console.log($scope.addpolicestation);
				$scope.masterdata.unshift(data);
				getMasterdata();
				$scope.errorlist = [];
				
			}
		});
		response.error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});

		//};

	}

});