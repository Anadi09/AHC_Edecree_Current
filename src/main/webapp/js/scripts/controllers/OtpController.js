var edmsApp = angular.module('EDMSApp', []);

edmsApp.controller('OtpController', ['$scope','$http','$controller', function ($scope,$http,$controller) {
	hideElement();
	function hideElement(){
		document.getElementById('sidebar').style.display = 'none';
		//document.getElementById('header').style.display = 'none';
		
		document.getElementById('content').style.margin = '0 0 0 0';
	/*	$(".sidebar").hide();*/
	}
	
	$scope.pass_code=null;
	var urlBase="/dms/";
	function getCodeBoxElement(index) {
		  return document.getElementById('codeBox' + index);
		}
	$scope.onKeyUpEvent =	function (index, event) {
		console.log("Eventttttttttttttt ",event);
		  const eventCode = event.which || event.keyCode;
		  if(index === 1){
			  $scope.pass_code=getCodeBoxElement(index).value;
		  }
		  else{
			  $scope.pass_code+=getCodeBoxElement(index).value;
		  }
		 
		  if (getCodeBoxElement(index).value.length === 1) {
			 if (index !== 4) {
				getCodeBoxElement(index+ 1).focus();
			 } else {
				getCodeBoxElement(index).blur();
				// Submit code
				console.log('submit code ');
				console.log( $scope.pass_code);
			 }
			 
			 if(index == 4){
				 
				 $http.post(urlBase+'dms/userOnlinelogin',$scope.pass_code).
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
		  }
		  if (eventCode === 8 && index !== 1) {
			 getCodeBoxElement(index - 1).focus();
		  }
		}
	$scope.onFocusEvent =	function (index) {
		  for (item = 1; item < index; item++) {
			 const currentElement = getCodeBoxElement(item);
			 if (!currentElement.value) {
				  currentElement.focus();
				  break;
			 }
		  }
		}  
    
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

