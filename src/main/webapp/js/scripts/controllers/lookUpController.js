var edmsApp = angular.module('EDMSApp', ['smart-table']);
edmsApp.filter('unique', function() {
    return function(input, key) {
        var unique = {};
        var uniqueList = [];
        for(var i = 0; i < input.length; i++){
            if(typeof unique[input[i][key]] == "undefined"){
                unique[input[i][key]] = "";
                uniqueList.push(input[i]);
            }
        }
        return uniqueList;
    };
});
edmsApp.controller("lookupMstrCtrl",['$scope','$http', function($scope,$http){
	var baseUrl="/dms/";
	$scope.lookupdataList=[];
	$scope.masterdata = [];
	$scope.addlookup = {};
	$scope.setNameList=[];
	$scope.getparentList=[];
	$scope.displayedCollection = [];
	getsetNameList()	
	getMasterdata();
	
	function getMasterdata() {			
		var response = $http.get(baseUrl+'lookup/getlookupdata')
		.success(function(data, status, headers, config) {	
			$scope.lookupdataList=data.modelList;
			$scope.displayedCollection= [].concat($scope.lookupdataList);
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
		
	};	
	
	$scope.resetModel=function()
	{
		$scope.addlookup = {};
		$scope.addlookup.lk_value=" ";
		$scope.addlookup.lk_rec_status=1;
		$scope.errorlist=[];
		getdropdown();	
		getsetNameList();	
	};
	
	$scope.setMasterdata = function(data) {
		console.log("SET DATA");
		//console.log(data);
		$scope.addlookup = angular.copy(data);
		getsetNameList();	
		getdropdown();	
		$scope.errorlist=[];
	};
	
	
	function getsetNameList() {
		$http.get(baseUrl+'lookup/getsetNameList')
		.success(function(data) {
			//console.log(data);
			$scope.setNameList = data;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});
	
	
		$scope.getLookupparentChange = function(){
			//$scope.addlookup.lk_longname='';
			$scope.getparentList=[];
			getdropdown();
		}
	
		function getdropdown(){	
			//console.log($scope.addlookup);
			//$scope.bundleAllocation.ta_oprator_id='';
			if($scope.addlookup.lk_setname){
				$.each($scope.lookupdataList, function(k, v) {
					if(v.lk_setname==$scope.addlookup.lk_setname)
						$scope.getparentList.push(v); 
				});
				console.log($scope.getparentList);
			}
		};
		
	    $scope.checkboxSelection = '1';
	    $scope.isCheckboxSelected = function(index) {
	        return index === $scope.checkboxSelection;
	    };
	    
		$scope.save = function(data) {
			console.log(data);
		 $scope.addlookup=data;
		 var response = $http.post(baseUrl+'lookup/save',$scope.addlookup);
			response.success(function(data, status, headers, config) {	
				console.log(data);
				if(data.response=="FALSE"){					
					$scope.errorlist=data.dataMapList;					
					$.each($scope.errorlist, function(k, v) {
	                    $("#"+k).parent().parent().addClass('has-error');
                  });			
				}else{
					$('#lookup_Modal').modal('hide');				
					bootbox.alert("Lookup Code created Successfully!");					
					$scope.errorlist=[];
					$scope.lookupdataList.unshift(data);
					getMasterdata();
				}
			});
			response.error(function(data, status, headers, config) {
				alert( "Error");
			});
	};
	}
}]);