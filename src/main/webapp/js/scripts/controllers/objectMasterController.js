var roleMapApp = angular.module("EDMSApp",['smart-table','ui.bootstrap','ng-bootstrap-datepicker', 'treeControl']);
//roleMapApp.controller("objectMasterCtrl",function($scope,$http)
roleMapApp.controller("objectMasterCtrl",['$scope','$http', function($scope,$http){
	var urlBase="/dms/";
	$scope.roleMapList=[];
	$scope.masterentity={};
	$scope.model={};
	$scope.tree_data=[];
	getRoleList();
	$scope.addobject={};
	$scope.selectedNode="";
	$scope.selectedNodes=[];
	$scope.selectedIds=[];
    $scope.treedata=[];

//    $scope.opts = {
//        nodeChildren: "childrens"
//    };
    $scope.showSelected = function(val)
    {
 
        $scope.selectedNode = val;
        $scope.addobject=val;
    };

	$scope.resetModel=function()
	{
		$scope.addobject = {};
		$scope.addobject.om_rec_status=1;
		$scope.errorlist={};
		getRoleList();
	};
    
	function getRoleList() {			
		var response =$http.get(urlBase+'objectMaster/getRole')
		.success(function(data, status, headers, config) {
			console.log("---------------role data--------");
			console.log(data);
			$scope.tree_data=data;
			
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
		
	};

	$scope.getDataByRoleID = function() {
		console.log("----branch,inward_date-----");
		console.log($scope.model);
		var date = new Date($scope.model.reportdate);
		$http.get(urlBase+'objectMaster/getDataByRoleID',{params: {'roleID': $scope.model.ro_role_id}}).success(function(data) {
			console.log("----getDataByRoleID-------")
			console.log(data);
			$scope.tree_data=data.modelList;
			
				
			
		}).error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});

	};

	
	$scope.save = function(data) {
	 console.log(data);
	 $scope.addobject=data;
	 
	 delete  $scope.addobject.children;
	 console.log("--------add object-------");
	 console.log($scope.addobject);
	 var response = $http.post(urlBase+'objectMaster/create',$scope.addobject);
		response.success(function(data, status, headers, config) {	
			console.log(data);
			if(data.response=="FALSE"){					
				$scope.errorlist=data.dataMapList;					
				$.each($scope.errorlist, function(k, v) {
                    $("#"+k).parent().parent().addClass('has-error');
              });			
			}else{			
				bootbox.alert("Object created Successfully!");					
				getRoleList();
				$scope.addobject = {};
				$scope.addobject.om_rec_status=1;	
	
			}
			
		});
		response.error(function(data, status, headers, config) {
			alert( "Error");
		});
};
}]);
//});