var EDMSApp = angular.module("EDMSApp", ['ngMask','ui.bootstrap']);

EDMSApp.controller("editProfileCtrl", function($scope, $http) {

	
	$scope.userobj = {};
	$scope.errors = [];
	$scope.date={};
	$scope.validitydate={};
	$scope.old_password;
	$scope.matchflag =false;
	var baseUrl = "/eDecree/";

	$scope.matchOldPassword = function(){
		console.log("Passworddddddd"+$scope.old_password);
		
		if($scope.old_password){
		
		$scope.entity = {
				'old_password' : $scope.old_password
			}
		var response = $http.post(baseUrl + 'user/matchOldPassword',
				$scope.entity);
		response.success(function(data, status, headers, config) {
			if (data.response == "TRUE") {
				$scope.matchflag =true;
				
				
				$scope.errorlist = [];

			} else {
				
				bootbox.alert({
		    	    message: "Your old Password did not match",
		    	    className: 'rubberBand animated'
		    	});
				$scope.errorlist = data.dataMapList;

			}

		});
		response.error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});
		}
		
	}
	


	function convertDate(inputFormat) 
	{
		  function pad(s) { return (s < 10) ? '0' + s : s; }
		  var d = new Date(inputFormat);
		  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
	}
	
	
       getEditProfileData();
	
	  function getEditProfileData() 
	  {debugger
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
		$scope.passwordn;
		$scope.passwordcn;
	
		console.log(data);
		console.log("********");

		if(!$scope.matchflag){
			
			bootbox.alert({
	    	    message: "Old Password did not match",
	    	    className: 'rubberBand animated'
	    	});
			return false;
		}
		
		if(!$scope.passwordn && !$scope.passwordcn && !$scope.old_password ){
			
			bootbox.alert({
		    	    message: "Plese Fill all the fields",
		    	    className: 'rubberBand animated'
		    	});
			return false;
		}
		if($scope.passwordn == $scope.passwordcn){
			
		}
		else{
			
			bootbox.alert({
	    	    message: "New Password and Confirmed Password are not Matched",
	    	    className: 'rubberBand animated'
	    	});
			return false;
		}
		
		
				
			
			$scope.entity={'um_id':$scope.userobj.um_id,'password':$scope.passwordn,'confirmpassword':$scope.passwordcn}
		
			/*alert("Password:"+$scope.passwordn+"confirm password:"+$scope.passwordcn);*/
        
		    var response = $http.post('/eDecree/user/changepassword',$scope.entity);
		    response.success(function(data, status, headers, config) {
			if (data.response == "TRUE") {
				
				
				/*bootbox.alert({
		    	    message: "Password changed Successfully Press Ok to login",
		    	    className: 'rubberBand animated'
		    	    	
		    	});*/
				bootbox.confirm({ 
				    size: "small",
				    message: "Password changed Successfully Press Ok to login?", 
				    className: 'rubberBand animated',
				    callback: function(result){ 
				    	if(result){
				    		window.location.href=baseUrl;
				    	}
				    }
				})
				
				
				
				
				//window.location.href = "/pdms/pdms/logout";
				
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
	   $scope.entity = userobj;

		var response = $http.post(baseUrl+'user/updateUser',$scope.entity);
		response.success(function(data, status, headers, config) {
			$scope.errorlist = {};
			if (data.response == "FALSE") {
				$scope.errorlist = data.dataMapList;
			}
			else 
			{
				 alert("User updated successfully");
		  }

		});
	};
	
	$scope.resetModel = function(userobj) 
	 { 
	   $scope.entity = userobj;

		var response = $http.post(baseUrl+'user/updateUser',$scope.entity);
		response.success(function(data, status, headers, config) {
			$scope.errorlist = {};
			if (data.response == "FALSE") {
				$scope.errorlist = data.dataMapList;
			}
			else 
			{
				 alert("User updated successfully");
		  }

		});
	};
	

	
	/*
	 $scope.landingForm= function() 
	   {
              window.location.href=baseUrl+"views/landingPage";
	   } */        	
  
});
