
var edmsApp = angular.module("EDMSApp", ['ngFileUpload','smart-table','ui.bootstrap','ngMask']);

edmsApp.controller("addCaveatController",['$scope','$http', 'Upload',function($scope,$http,Upload) {
	var urlBase="/dms/";
	
	$scope.masterentity = {};	
	$scope.masterdata=  [];
	$scope.roleData=[];
	$scope.departmentData=[];
	$scope.designationData=[];
	$scope.userrole={};
	$scope.userroles=[];
	$scope.errorlist=null;
	$scope.userpemissions=[];
	$scope.repositories=[];
	$scope.folders=[];
	$scope.userApprovalData=[];
	$scope.manualCaveat={};
	$scope.picFile ='';
	$scope.CaseType={};
	$scope.CaseFileDetail={};
	$scope.search={};
	$scope.manualCaveat1 ={};
	
	
	getUserForApproval();
	getMasterdata();
	getRoleDD();
	
	  $scope.buttonDisabled=false;
	  
	  $scope.document_name= $('#mcav_document_name').val();
	  var url = urlBase+'uploads/' +'manualcaveat_'+ $scope.document_name+".pdf";
		DEFAULT_URL = url;
		console.log("default urlllllllllllllllllllllllllllllll",DEFAULT_URL);
		console.log("data is correcttttttttttttttttt");

	
	$scope.open = function($event,opened) {
	    $event.preventDefault();
	    $event.stopPropagation();
        
	    $scope[opened] = true;
	    console.log(open);
	  };
	    $scope.formats = ['dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate'];
		$scope.format = $scope.formats[0];
		
       $scope.dateOptions = {
       formatYear: 'yy',
       startingDay: 1
};
		
		
		
		function convertDate(inputFormat) 
		{
			  function pad(s) { return (s < 10) ? '0' + s : s; }
			  var d = new Date(inputFormat);
			  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
		}
	
$scope.approveUser =function(data){
		
		console.log("approve urser dataaaaaaaaaaa",data);
		
		data.hcu_approved_status= "Y";
		
		$http.post(urlBase+'/userApproval',data).success(function (data){
			
			console.log("status of approve user",data);
			
			
		}).error(function(data, status, headers, config){
			
			console.log("error in getting data");
			
		});
};
		
		 function getUserForApproval() 
			{
		    	console.log("this method invokedddd");
				$http.get(urlBase+'/getUserForApproval').success(function (data) {
		            	console.log("user approval dataaaaaaaa",data);
		            	

		            	
		            	$scope.userApprovalData = data.modelData;
		            	console.log("dataaaaaaaaaaaaa approv",$scope.userApprovalData);
		           
		            }).
		            error(function(data, status, headers, config) {
		            	console.log("Error in getting User data");
		            });
		    };

           
	function getMasterdata() 
	{
		$http.get(urlBase+'user/getallusers').success(function (data) {
            	$scope.masterdata = data; 
            	$scope.displayedCollection = [].concat($scope.masterdata);
            }).
            error(function(data, status, headers, config) {
            	console.log("Error in getting User data");
            });
    };
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
    
    $scope.changeStatus=function(permission){
    	$scope.errorlist=[];
    	$scope.entity=angular.copy(permission);
    	
    	if($scope.entity.status==0)
    		$scope.entity.status=1;
    	else
    		$scope.entity.status=0;
    	
    	$http.post(urlBase+'user/updateuserpermission',$scope.entity).
        success(function (data) {
        	if(data.response=="TRUE"){
        		$scope.repositories=data.repositories;
            	$scope.folders=data.folders;
        	}else{
        		$scope.errorlist=data.dataMapList;
        	}
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting property details");
        });
    };
    
    $scope.user_create = function(masterentity) {
    	console.log(masterentity);
    	
    	$scope.masterentity=masterentity;
    	
//    	if($scope.masterentity){
//    		angular.forEach($scope.masterdata,function(value,index){	    			
//    			if(value.username==$scope.masterentity.username){
//    				if($scope.masterentity.um_id){
//    					if($scope.masterentity.um_id!=value.um_id){
//    						$scope.error=true;
//    					}
//    				}else{
//    					$scope.error=true;
//    				}
//    			}
//            });
//    	}
    	
    		var response = $http.post(urlBase+'user/create',$scope.masterentity);		 
				response.success(function(data, status, headers, config) {					
					if(data.response=="FALSE"){					
						$scope.errorlist=data.dataMapList;
					}else{		
						$('#user_Modal').modal('hide');		
						$scope.errorlist=[];
						
						$('.form-group').removeClass('has-error');
						
								alert("User created Successfully!");
								getMasterdata();						
					}
					
			});
			response.error(function(data, status, headers, config) {
				alert( "Error");
			});
    	
	};
	
	
	
	
	$scope.edit = function(data) {
		
	$scope.manualCaveat = angular.copy(data);
	};
    

	$scope.resetModel=function()
	{
		$scope.masterentity.um_rec_status=1;
		$scope.masterentity={};		
		$('.form-group').removeClass('has-error');	
		$scope.index=-1;
		
	};
    
	$scope.Refresherrorlist=function(data)
	{
		$scope.um_id=data.um_id;
		$scope.errorlist = null;
	};

	$scope.user={};
	
	$scope.changePassword = function(data) {
				$scope.user=data;
				$scope.user.um_id=$scope.um_id;
		var response = $http.post(urlBase+'user/changepassword',
				$scope.user);
		response.success(function(data, status, headers, config) {
			if (data.response == "TRUE") {
				$('#pass_Modal').modal('hide');		
				$scope.errorlist=null;
				$('.form-group').removeClass('has-error');
				alert("Password changed Successfully!");

			} else {
				$scope.errorlist = data.dataMapList;
				$scope.ansDetails = [];
			
			}

		});
		response.error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});	
	}
	
	 //file upload 
	$scope.loading = true;
	$scope.addCaveat=function() 
	{
		console.log("In caveat controller");
		console.log("fileeeeeeeeeeeeeeeeee",$scope.picFile);
		
		var str=$scope.picFile.name;
		 var extn = str.split(".").pop();
		 extn=extn.toLowerCase();
	 	 if(extn!="pdf"){
			 alert("Only pdf format are allowed");
			 return false;
		 }
		$scope.buttonDisabled=true;
		
		if($scope.manualCaveat.mcav_no==null ||$scope.manualCaveat.mcav_year== null || $scope.manualCaveat.mcav_submitted_date==null){
			
			$scope.loading = true;
			 alert("Please select required fields");
			 $scope.loading = true;
			 return false;
			
		 }
		  var file=$scope.picFile;
		    file.upload = Upload.upload({
		      url: urlBase + 'caveat/addManualCaveat',  
		      headers: {
		    	  'optional-header': 'header-value',
		    		
		        },
		        fields:$scope.manualCaveat,
    		   file:file,
		    });
		    
            if(true){
		    file.upload.then(function (response) {
		    	$scope.buttonDisabled=false;
		        if(response.data.response=="TRUE"){
		      
		        	console.log("responseeeeeeee of cr",response);	       	
		        		alert("Files Uploaded successfully..."); 
		        		
		        		$scope.loading = true;
		        		$scope.picFile='';	
		        }else if(response.data.response=="FALSE"){
		  			  $scope.loading=true;
		  			  alert("Caveat no. is same");			
		        }
		        else{
		        	$scope.loading = true;
		        	alert("Some Problem");
		        	$scope.caveatno='';
		        	$scope.errorlist = response.data.dataMapList;
		        }
		      }, function (response) {
		        
		      }, function (evt) {
		        // Math.min is to fix IE which reports 200% sometimes
		        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		      });
		    
		    file.upload.xhr(function (xhr) {
		        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
		      });
	}else{
		$scope.buttonDisabled=false;
		}

		}
	
   
    $scope.searchCaveat=function()
  	  {
    
  	console.log("Search Caveat method invokedddd",$scope.manualCaveat1);
		  $scope.caveatList=[];
		  $scope.loading = false;
		  $http.post(urlBase+'caveat/getCaveatList',$scope.manualCaveat1).success(function (data) {
		    	if(data.response=="TRUE")
		    		$scope.caveatList=data.modelList;	  	
		    	   else
		    		   $scope.caveatList=[];		
			      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });				  
	  }
    
	
	
		/*
		
		console.log(manualCaveat);
		$scope.manualCaveat=manualCaveat;
		
		var response=$http.post(urlBase+'caveat/update',$scope.manualCaveat);
		response.success(function(data,status,headers,config){
			console.log("aaaaaaaaaaaaaaaaaa",data);
			if(data.response=="TRUE")
				{
				alert("Manual Caveat updated Successfully!");		
				
				}
			
		});
			response.error(function(data, status, headers, config) {
				alert( "Error");
			}); 
	};*/
    
    $scope.manualCaveat_update=function(manualCaveat)
	{ 
		console.log(manualCaveat);
		$scope.manualCaveat=manualCaveat;
		manualCaveat.caseFileDetail=null;
		
		var file=$scope.picFile;
		  var flag=confirm("Are you sure");
		  if (flag)
		  {
			  
			  if(file!="")
			  {				  
			    file.upload = Upload.upload({
			      url: urlBase +'caveat/update',
			      headers: {
			    	  'optional-header': 'header-value'
			        },
	  		   file:file,
	  		 fields:$scope.manualCaveat,
			    });
	
			    file.upload.then(function (response) {
			        if(response.data.response=="TRUE"){
			        	$scope.errorlist =null;
			        	alert("Successfully Update!");
			        	$scope.picFile='';	        	
			        }else{
			        	alert(response.data.data);
			        }
			      }, function (response) {
			        
			      }, function (evt) {
			       
			      });
	
			      file.upload.xhr(function (xhr) {
			      });
			  }
		  }else{
			  alert("Please select valid PDF file for upload")
		  }
		}

}]);