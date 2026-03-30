var edmsApp = angular.module("EDMSApp", [ 'smart-table' ]);

edmsApp.controller("judgeMstrCtrl", ['$scope','$http', function($scope,$http){
	var urlBase="/dms/";
	getMasterdata();
	$scope.masterdata = [];
	$scope.branchData=[];
	$scope.addjudgeForm=[];
	$scope.displayedCollection = [];
	$scope.judgeList = [];
	getjudgeList();
	getDDdata();
	
	$scope.years = [];
	getYears();
	
	function getYears()
    {
		var currentYear = new Date().getFullYear();
		for (var year = 1950; year <= currentYear; year++) 
	      {
					$scope.years.push(year);
	      }
    }
	
	function getMasterdata() {
		var response = $http.get(urlBase+'judge_master/getjudgeDetails')
		.success(function(data, status, headers, config) {
			$scope.masterdata = data;
			console.log("--GOT MASTER--");
			console.log(data);
			$scope.displayedCollection = [].concat($scope.masterdata);

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	};
		$scope.resetModel=function(){
		$scope.addjudge = {};
		console.log($scope.addjudge);
		$scope.addjudge.jg_rec_status=1;
		$scope.errorlist=[];
		getjudgeList();	
		getDDdata();	
		
	};

	$scope.setMasterdata = function(data) {
		console.log("SET DATA");
		console.log(data);
		getjudgeList();	
		$scope.addjudge = angular.copy(data);
		
		getDDdata();	
		$scope.errorlist=[];
		
	};
	
	 $scope.checkboxSelection = '1';
	    $scope.isCheckboxSelected = function(index) {
	        return index === $scope.checkboxSelection;
	    };
	
	function getjudgeList() {
		$http.get(urlBase+'judge_master/getjudgeList').success(function(data) {
			console.log("--judge--");
			console.log(data);
			$scope.judgeList = data;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});
	}
	
	
    function getDDdata() {
		$http.get(urlBase+'judge_master/getbranchDetails').
            success(function (data) {
            	$scope.branchData=data.branchData;
            }).
            error(function(data, status, headers, config) {
            	console.log("Error in getting property details");
            });
		
		}
    
	$scope.save=function(data)
	{
		$scope.addjudge = data;
		console.log($scope.addjudge);
		//$scope.addjudgeForm  = data;
	
	//console.log($scope.addjudgeForm);
	var response = $http.post(urlBase+'judge_master/save',$scope.addjudge);
		response.success(function (data,status, headers, config) {
				
			 if(data.response=="FALSE"){					
					$scope.errorlist=data.dataMapList;					
					$.each($scope.errorlist, function(k, v) {
		                    $("#"+k).parent().parent().addClass('has-error');
		            });
			 }else{		
				 $scope.errorlist=[];
				$('.form-group').removeClass('has-error');
				
				$scope.masterdata.unshift($scope.addjudge);
				 $('#judge_Modal').modal('hide');
				 
				 bootbox.alert("Judge created Successfully!");
				 $scope.displayedCollection = [].concat($scope.addjudge);
			
			
			 }
		});
	response.error(function(data, status, headers, config) {
	      	console.log("Error in getting Master");
	    });
	}		
}]);