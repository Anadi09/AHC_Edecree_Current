var edmsApp = angular.module('EDMSApp', []);

edmsApp.controller('loginController', ['$scope','$http','$controller', function ($scope,$http,$controller) {
	
	
	var urlBase="/eDecree/";
	$scope.flattypes=[];
	$scope.flatcats=[];
	$scope.loginform={};
	$scope.userRoles=[];
	$scope.errorlist = '';
	//$scope.errorlist={"name":["Already Exist"]};
	/*$('.register').hide();*/
	//$('.login').hide();
	$scope.register={};
	$scope.register.type='aor';
	$scope.isadvocate=false;
	$scope.designations = [];
	$scope.sections =[];
	$scope.registerData ={};
	
	
	/*deleteHistory();
	function deleteHistory()
	{
		alert("Please clear browser history by press Ctrl + H  ");
	}
	
	*/
	getUserDesignation();
	
	$scope.show=false;
	
	$scope.signUp=function(){
		
		$scope.show=true;
		document.getElementById('login').style.height='500px';
	}
	getUserSection();
	getRoleDD();
	
	
	function getRoleDD() {
		$http.get(urlBase+'user/getmasters').
            success(function (data) {
            	$scope.roleData=data.roleData;
            	$scope.departmentData=data.departmentData;
            	$scope.designationData=data.designationData;
            	$scope.courtsData=data.courtsData;
            	$scope.masterdata.um_pass_validity_date = new Date();
            }).
            error(function(data, status, headers, config) {
            	console.log("Error in getting property details");
            });
		
		}
	
	
	$scope.darkMode =function (){
		
		var dark = localStorage.getItem("mode");
	    if(dark==''){
	        localStorage.setItem("mode", 'dark-mode-live');
	        $("body").toggleClass("dark-mode-live");
	        $('.inner-switch-dark').prop('title', 'Disable Contrast Mode');
	    }else{
	        localStorage.setItem("mode", '');
	        $("body").toggleClass("dark-mode-live");
	        $('.inner-switch-dark').prop( "title","Enable Contrast Mode");
	    }
	}
	
	
	
	function getUserDesignation(){
		
		$http.get(urlBase+'userregistration/getUserDesignations').success(function (data) {
			
			$scope.designations  =data.modelData;
			
			
			console.log("user designationnnnnn",$scope.designations)
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting UserDesignations");
	      });
		
		
		
		
	}
	
	function getUserSection(){
		
		$http.get(urlBase+'userregistration/getUserSections').success(function (data) {
			
			$scope.sections =data.modelData;
			console.log("user sectionnnnnn",$scope.sections);
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting UserSection");
	      });
		
		
	}
	
	
	
	$scope.registerForm=function(){
		$('.register').show();
		$('.login').hide();
		this.refresh();
	}
	$scope.loginForm=function(){
		$('.login').show();
		$('.register').hide();
		
	}
	$scope.refresh=function(){
		$scope.registertemp=$scope.register;
		$scope.register={};
		$scope.isadvocate=false;
		$scope.register.type=$scope.registertemp.type;
	}
	$scope.validateAOR=function(){
		if(AORFormValidate()=="TRUE"){
		$(".form-group").removeClass('has-error');
		$(".msg_div").html(" ");
		$http.post(urlBase+'user/validateAdvocate',$scope.register).
        success(function (data) {
        	if(data.response=="TRUE"){
        		$scope.isadvocate=true;
        		$scope.register.username=data.modelData.rollNo;
        		$scope.register.name=data.modelData.name;
        		$scope.register.email=data.modelData.email;
        		$scope.register.mobile=data.modelData.mobile;
        		
    			//window.location.href=urlBase+"ecourt/ecourtHome";
        	}else{
        		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
        		 setTimeout(function() {
        			 $(".msg_div").html("");
        		    }, 5000);
        		        		
        	}    
        	           	
        }).
        error(function(data, status, headers, config) {
    		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
        	console.log("Error in getting Login details");
        });
		}
	}
	
	$scope.registrationDecree=function(userReg){
		
		console.log(userReg);
		
		var response = $http.post(urlBase+'user/createDecree',$scope.masterentity);		 
		response.success(function(data, status, headers, config) {					
			if(data.response=="FALSE"){					
				$scope.errorlist=data.dataMapList;
			}else{		
				/*$('#user_Modal').modal('hide');	*/	
				$scope.errorlist=[];
				
				$('.form-group').removeClass('has-error');
				
						alert("User created Successfully!");
						
						 location.reload();
						//getMasterdata();						
			}
			
	});
	response.error(function(data, status, headers, config) {
		alert( "Error");
	});
		
	}
	
	
	$scope.registration=function(){
		
		console.log("dataaaaaaaaaa",$scope.register);
		$(".form-group").removeClass('has-error');
		$(".msg_div").html(" ");
		if(RegisterFormValidate()=="TRUE"){
			
		$scope.errorlist='';
		$http.post(urlBase+'userregistration/create',$scope.register).
        success(function (data) {
        	if(data.response=="TRUE"){
        		$(".msg_div").html("<div class='alert alert-success alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
        		setTimeout(function() {
       			 $(".msg_div").html("");
       		    }, 5000);
        		$scope.registertemp=$scope.register;
        		$scope.register={};
        		$scope.register.type=$scope.registertemp.type;	
    			$('.login').show();
    			$('.register').hide();
    			$("#userid").parent().removeClass('has-error');
    			$("#name").parent().removeClass('has-error');
    			$("#email").parent().removeClass('has-error');
    			$("#mobile").parent().removeClass('has-error');
    			$("#confirmPassword").parent().removeClass('has-error');
    			$("#username").parent().removeClass('has-error');
    			$("#password").parent().removeClass('has-error');
    			//window.location.href=urlBase+"ecourt/ecourtHome";
        		//$scope.register={'type':$scope.register.type,'username':$scope.register.username,'name':$scope.register.name,'mobile':$scope.register.mobile,'email':$scope.register.email,'password':$scope.register.password,'confirmPassword':$scope.register.confirmPassword};
        	}else{
        		$scope.errorlist=data.dataMapLists;
        	}           	
        }).
        error(function(data, status, headers, config) {
    		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
        	console.log("E" +
        			" rror in getting Login details");
        });
		}
	}
	function AORFormValidate(){
		$scope.validate = "TRUE";
		
			if($scope.register.hasOwnProperty("rollNo") == false){
				$("#rollNo").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#rollNo").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("enrollNo") == false){
				$("#enrollNo").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#enrollNo").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("enrollYear") == false){
				$("#enrollYear").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#enrollYear").parent().removeClass('has-error');
			}
		return $scope.validate;
		
	}
	function RegisterFormValidate(){
		
		console.log("validateeee");
		$scope.validate = "TRUE";
			if($scope.register.hasOwnProperty("hcu_office_id") == false){
				$("#userid").parent().addClass('has-error');
				$scope.validate = "FALSE";
				console.log("validateeee1111");
			}else{
				$("#userid").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("hcu_username") == false){
				$("#name").parent().addClass('has-error');
				$scope.validate = "FALSE"; 
			}else{
				$("#name").parent().removeClass('has-error');
			}
			
			if($scope.register.hasOwnProperty("hcu_email") == false){
				$("#email").parent().addClass('has-error');
				$scope.validate = "FALSE";
				
			}else{
				if(isValidEmailAddress($scope.register.hcu_email)){
					$("#email").parent().removeClass('has-error');					
				}else
				{
					$scope.validate = "FALSE";
					$("#email").parent().addClass('has-error');
				}
					
			}
			if($scope.register.hasOwnProperty("hcu_contact_number") == false){
				$("#mobile").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#mobile").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("hcu_password") == false){
				$("#registerpassword").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#registerpassword").parent().removeClass('has-error');
			}
			if($scope.register.hasOwnProperty("hcu_confirm_password") == false){
				$("#confirmPassword").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#confirmPassword").parent().removeClass('has-error');
			}
			var msg="Please update latest Email/Mobile in advocate role.";
			if($scope.register.type=="aor" &&( $scope.register.email=='' || $scope.register.mobile=="")){
				$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+msg+"</div>");
				
			}else{
				$(".msg_div").html("");
			}
		return $scope.validate;
		
	}
	function isValidEmailAddress(emailAddress) {
	    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	    return pattern.test(emailAddress);
	};

	function loginFormValidate(action){
		
		$scope.validate = "TRUE";
		$(".form-group").removeClass('has-error');  
		$("#msg_div").html(" ");
		
		if(action=="login"){
		
			if($scope.loginform.hasOwnProperty("username") == false){	
		
				 $("#username").parent().addClass('has-error');
				 $scope.validate = "FALSE";
		
			}else
			{
		
				$("#username").parent().removeClass('has-error');
			}
			if($scope.loginform.hasOwnProperty("password")==false){
				$("#password").parent().addClass('has-error');
				$scope.validate = "FALSE";
			}else{
				$("#password").parent().removeClass('has-error');
			}
		}
		if(action=="fgpwd"){
			
			if($scope.loginform.hasOwnProperty("username") == false){	
		
				 $("#username").parent().addClass('has-error');
				 $scope.validate = "FALSE";
		
			}else
			{
		
				$("#username").parent().removeClass('has-error');
			}
			
		}
		
		return $scope.validate;
	}
	
	$scope.login= function() {
		 
		if(loginFormValidate("login")=="TRUE"){	
			console.log($scope.loginform); 
	        $http.post(urlBase+'dms/userlogin',$scope.loginform).
	            success(function (data) {
	            	console.log("Success got Login details");
	            	console.log(data);  	            	
	            	if(data.response=="TRUE"){	
            			window.location.href=urlBase+data.data;
            			
	            	}
	            	else{
	            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            		setTimeout(function() {
	           			 $(".msg_div").html("");
	           		    }, 5000);
	            	}           	
	            }).
	            error(function(data, status, headers, config) {
            		$(".msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		}	     
    };
    
    $scope.fagpass= function() {
    	
		if(loginFormValidate("fgpwd")=="TRUE"){	
			console.log($scope.loginform); 
	
	        $http.post(urlBase+'dms/userFagPass',$scope.loginform). 
	            success(function (data) 
	            		{
	            	 if(data.response=="TRUE")
	            	{	
	            		
            			window.location.href=urlBase+"views/forgetPassword";
	            	}else if(data.response=="FALSE"){
	            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            	}           	
	            }).
	            error(function(data, status, headers, config) {
            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		}
    };

    $scope.accountActivation= function() {
    	
		if(loginFormValidate("fgpwd")=="TRUE"){	
			console.log($scope.loginform); 
	
	        $http.post(urlBase+'dms/userFagPass',$scope.loginform). 
	            success(function (data) 
	            		{
	            	 if(data.response=="TRUE")
	            	{	
	            		
            			window.location.href=urlBase+"views/accountActivation";
	            	}else if(data.response=="FALSE"){
	            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+data.data+"</div>");
	            	}           	
	            }).
	            error(function(data, status, headers, config) {
            		$("#msg_div").html("<div class='alert alert-danger alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button> <strong>Oops!</strong> Something went wrong.</div>");
	            	console.log("Error in getting Login details");
	            });
		}
    };    
    
}]);

var compareTo = function() {
	
	
    return {
      require: "ngModel",
      scope: {
        otherModelValue: "=compareTo"
      },
      link: function(scope, element, attributes, ngModel) {

        ngModel.$validators.compareTo = function(modelValue) {
          return modelValue == scope.otherModelValue;
        };

        scope.$watch("otherModelValue", function() {
          ngModel.$validate();
        });
      }
    };
  };
  
  edmsApp.directive("compareTo", compareTo);

