var roleMapApp = angular.module("EDMSApp",['smart-table','ui.bootstrap','ng-bootstrap-datepicker', 'treeControl']);
roleMapApp.controller("roleMapCtrl",['$scope','$http', function($scope,$http){
	var urlBase="/dms/";
	$scope.roleMapList=[];
	$scope.masterentity={};
	$scope.model={};
	$scope.masterdata={};
	$scope.tree_data=[];
	getDesginationList();
	getRoleList();
	//getRoleList();
	
	$scope.selectedNode="";
	$scope.selectedNodes=[];
	$scope.selectedIds=[];
	
	
	 $scope.showSelected = function(sel)
	    {
	        $scope.selectedNode = sel;
	    };
	    $scope.clearSelected = function() {
	         $scope.selected = undefined;
	     }
	    
	     $scope.selection=[];

	    
	    	   $scope.toggleSelection = function(data) {
	    			console.log("toggle data ");
	    			console.log(data);
	    			// var idx = $scope.selection.indexOf(data);
	    			//alert($scope.selectedIds.indexOf(data));
	    			// is currently selected
	    			if ($scope.selectedIds.indexOf(data) == -1) {
	    				$scope.selectedIds.push(data);
	    			}

	    			// is newly selected
	    			else {
	    				$scope.selectedIds.splice($scope.selectedIds.indexOf(data), 1);
	    			}
	    		};
	    		
	    		

	    	/*   
	    	   
	    	   $scope.toggleSelection = function (data) {
	    	    	console.log("toggle data ");
	    	    	console.log(data);
	    	     // var idx = $scope.selection.indexOf(data);
	    	    		//alert($scope.selection.indexOf(data));
	    	      // is currently selected
	    	      if ($scope.selection.indexOf(data)== -1) {
	    	        $scope.selection.push(data);
	    	      }

	    	      // is newly selected
	    	      else {
	    	        $scope.selection.splice($scope.selection.indexOf(data),1);
	    	      }
	    	    };
	    	    */
	    
	function getDesginationList() {			
		var response = $http.get(urlBase+'roleMapping/getRoleList')
		.success(function(data, status, headers, config) {
			/*console.log("--------getroleMapping data------")
			console.log(data);*/
			$scope.roleMapList=data;
			
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
		
	};
	
	
	
	function getRoleList() {			
		var response = $http.get(urlBase+'roleMapping/getRole')
		.success(function(data, status, headers, config) {
			//console.log("---------------role data--------");
			//console.log(data);
			$scope.tree_data=data;
			
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
		
	};

	$scope.getDataByRoleID = function() {
	
		var date = new Date($scope.model.reportdate);
		$http.get(urlBase+'roleMapping/getDataByRoleID',{params: {'roleID': $scope.model.ro_role_id}}).success(function(data) {
			console.log("MOdel List");
			console.log(data.modelList); //selected ids
			$scope.treeData= $scope.tree_data;
			$scope.tree_data= [];
			
			//debugger;
			angular.forEach($scope.treeData,function(value,index){				
				$scope.children= [];
				console.log("value.children");
				console.log(value.children);
				if(data.modelList.indexOf(value.id) > -1){
					value.checkbox=true;
				}else 
					value.checkbox=false;
				if(value.children){
					angular.forEach(value.children,function(value1,index1){
						//console.log(value1);
						if(data.modelList.indexOf(value1.id) > -1){
							value1.checkbox=true;
							
						}else
							value1.checkbox=false;
						
						$scope.children.push(value1);
					});
				}
				
				value.children = $scope.children;				
				$scope.tree_data.push(value);
			});
			console.log("----------------tree data -----------");
			console.log($scope.tree_data);
		}).error(function(data, status, headers, config) {
			console.log("Error in getting DailyReportData ");
		});

	};


	/*	function MultiSelection($scope) {
	 $scope.treeOptions = {multiSelection: true};
	 $scope.treedata=createSubTree(3, 4, "");
	 $scope.selectedNodes = [];
	 $scope.showSelected = function(node, selected) {
	 $("#multi-selection-events-listing").append(""+node.label+ (selected?" selected":" deselected") + "");
	 };
	 }
	
	 */
	$scope.save = function(tree_data,model) {
		
		$scope.entity = tree_data;
		
		
		console.log($scope.entity);
		
		// $scope.roleObject=[{"ro_role_id":1,"ro_om_mid":2}]
	$scope.roleObject = [];
		console.log("-----selectedIds- id-------");
		console.log($scope.selectedIds);
		angular.forEach($scope.selectedIds, function(value, index) {
			var tempobject={};
			
			tempobject.ro_role_id=$scope.model.ro_role_id;
			tempobject.ro_om_mid=value.id;
			
			tempobject.checkbox=value.checkbox;
			console.log("------------value of  tempobject---------");
			console.log(tempobject);
			$scope.roleObject.push(tempobject);
		});

		console.log("------------value of  tempobject---------");
		console.log($scope.roleObject);
		
		//alert((''+$scope.roleObject).length)
		
	
		if(((''+$scope.roleObject).length )>0)
		{
	
		$http.post(urlBase + 'roleMapping/create', $scope.roleObject).success(
				function(data) {
					if (data.response == "FALSE") {
						$scope.errorlist = data.dataMapList;
					} else {
						bootbox.alert("Successfully created record");
						
						$scope.masterdata.push(data);
						//	$scope.displayedCollection = [].concat($scope.masterdata);
						//$("#folderCreate").modal('hide');	
					}
				}).error(function(data, status, headers, config) {
			console.log("Error in getting tree data");
			
		});
		
	}
	else
		bootbox.alert(" Please select any  Role");
	};

}]);

	