var DocumentApp = angular.module("EDMSApp", [ 'ui.bootstrap','smart-table']);

DocumentApp.controller("editProfileCtrl", function($scope, $http) {

	
	$scope.userobj = {};
	$scope.errors = [];
	$scope.date={};
	$scope.validitydate={};
	var baseUrl = "/dms/";

/*	$scope.getEditProfileData = function() {
		//alert(11);
		console.log("Edit Profile");

		$http.get(baseUrl + 'user/getUserDetails').success(function(data) {
			console.log(data);

			$scope.userobj = data.data;

			console.log($scope.userobj);
		}).error(function(data, status, headers, config) {
			console.log("Error in getting property details");
		});

	};*/


	function convertDate(inputFormat) 
	{
		  function pad(s) { return (s < 10) ? '0' + s : s; }
		  var d = new Date(inputFormat);
		  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
	}
	
	
       getEditProfileData();
	
	  function getEditProfileData() 
	  {
		//alert(11);
		console.log("Edit Profile");
		$http.get(baseUrl+'user/getUserDetails').success(function(data) {
			console.log(data);
			$scope.date = data.data.um_pass_validity_date;
			//alert($scope.date);
			if($scope.date!=null){
				$scope.validitydate=convertDate($scope.date);
				//alert($scope.validitydate);
			}
	            $scope.userobj = data.data;
	            
			console.log($scope.userobj);
		}).error(function(data, status, headers, config) {
			console.log("Error in getting property details");
		});

	};
	
	
	

	$scope.savePassword = function(data) {
		console.log(data);
		console.log("********");

		$scope.password;

		$scope.entity = {
			'password' : $scope.password
		}

		console.log($scope.password);

		var response = $http.post(baseUrl + 'user/updateUserDetails',
				$scope.entity);
		response.success(function(data, status, headers, config) {
			if (data.response == "TRUE") {
				bootbox.alert("Password Change Successfully!");
				window.location.href = baseUrl + "views/landingPage";
				$scope.errorlist = [];

			} else {

				$scope.errorlist = data.dataMapList;

			}

		});
		response.error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});

	}
	
	
	$scope.changePassword = function(data) {
		alert(1);
		console.log(data);
		console.log("********");

				
			$scope.passwordn;
			$scope.passwordcn;
			$scope.entity={'password':$scope.passwordn,'passwordc':$scope.passwordcn}
		
			alert("Password:"+$scope.passwordn+"confirm password:"+$scope.passwordcn);
        
		    var response = $http.post('/dms/user/changePassword',$scope.entity);
		    response.success(function(data, status, headers, config) {
			if (data.response == "TRUE") {
				bootbox.alert("Password changed Successfully!");
				//window.location.href = "/pdms/pdms/logout";
				window.location.href=baseUrl+"views/landingPage";
				$scope.errorlist = [];
			} else 
			{ 
				$scope.errorlist = data.dataMapList;
         	    //bootbox.alert("Password and Confirm Password not matched");
				$scope.ansDetails = [];
			
				

			}

		});
		response.error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});

	}
	
	
	
	
	
	
	
	$scope.save = function(userobj) 
	 { 
	   //alert(1);	
	   $scope.entity = userobj;
	   
		console.log("List before save");
		var response = $http.post('/dms/user/updateName',$scope.entity);
		response.success(function(data, status, headers, config) {
			$scope.errorlist = {};
			if (data.response == "FALSE") {
				$scope.errorlist = data.dataMapList;
			}
			else 
			{
				 bootbox.alert("Successfully updated record", function() {
					
			});
		  }

		});
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 $scope.landingForm= function() 
	   {
              window.location.href=baseUrl+"views/landingPage";
	   }         	
  
});
