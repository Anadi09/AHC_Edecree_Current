/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['ngFileUpload','smart-table','ui.bootstrap','ngMask']);

//edmsApp.directive('fileModel', [ '$parse', function($parse) {
//    return {
//        restrict : 'A',
//        link : function(scope, element, attrs) {
//            var model = $parse(attrs.fileModel);
//            var modelSetter = model.assign;
//
//            element.bind('change', function() {
//                scope.$apply(function() {
//                    modelSetter(scope, element[0].files[0]);
//                });
//            });
//        }
//    };
//} ]);


/**
 * Controller in index.jsp
 */

edmsApp.controller("transferCasesController",['$scope','$http','Upload', function($scope,$http,Upload) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;
		$scope.model={};
	  $scope.invalidFiles = [];
	  $scope.checkListObj=[];
	  $scope.model={};
	  $scope.report={};
	  $scope.range ={};
	
	  $scope.itr=0;
	  $scope.type_id= $('#type_id').val();
	  $scope.checkListObj=[];
	  $scope.$watch('files', function (files) {
	    $scope.formUpload = false;
	    if (files != null) {
	      if (!angular.isArray(files)) {
	        $timeout(function () {
	          $scope.files = files = [files];
	        });
	        return;
	      }
	      for (var i = 0; i < files.length; i++) {
	        $scope.errorMsg = null;
	        (function (f) {
	          $scope.upload(f, true);
	        })(files[i]);
	      }
	    }
	  });
	$scope.folder={};
	$scope.errorlist=null;
	$scope.picFile='';
    $scope.causeListTypes =[];
    $scope.masterdata=[];
    $scope.displayedCollection = []
    $scope.displayedCollection1 = [];
    $scope.courtList=[];
    $scope.causelist={};
    $scope.clmodel={};
    $scope.search=false;
    $scope.caseFileList=[{}];
    $scope.entity2 =[];
    $scope.len=0;
    $scope.subDocuments=[];
    $scope.approw=false;
    
    
    $scope.caseTypes=[];
    getCaseTypes();
    getCauseListTypes();
    getCourtList();
    
    $scope.movieArray=[];
    $scope.addcl=[];    
    $scope.showTansferMessage =false;
    
    $scope.submitCaseToTransfer =function(){
    	var response = $http.post(urlBase+'/transferCase/submitTransferCases',$scope.displayedCollection1);	
    	response.success(function(data, status, headers, config) {
    		console.log("Data Log");
			//$scope.courtList = data.modelList;
			if(data.response=="TRUE"){
				console.log("dataaaaaaaaa",data);
				$scope.displayedCollection1=[];
				bootbox.alert({
	  			    message: "Cases Transferred Successfully",
	  			    className: 'rubberBand animated',
	  			    	backdrop: true
	  			
	  			});
				
			}else{
				alert(data.data);
			}

		});
		response.error(function(data, status, headers, config) {
			alert("Input field not empty");
		});
    }
    
    
    $scope.transferAllCases =function(){
    	console.log("Transfer all called",$scope.model);
    	console.log("Transfer all called",$scope.range);
    	$scope.submitAll ={};
    	$scope.submitAll.cl_dol =$scope.model.cl_dol;
    	$scope.submitAll.cl_court_no =$scope.model.cl_court_no;
    	$scope.submitAll.cl_transfer_to =$scope.range.cl_transfer_to ;
    	
    	if($scope.submitAll.cl_dol){
    		bootbox.alert({
  			    message: "Please Select Date First",
  			    className: 'rubberBand animated',
  			    	backdrop: true
  			
  			});
    	}
    	
    	
    	
    	var response = $http.post(urlBase+'/transferCase/submitAllTransferCases',$scope.submitAll);	
    	response.success(function(data, status, headers, config) {
    		console.log("Data Log");
			//$scope.courtList = data.modelList;
			if(data.response=="TRUE"){
				console.log("dataaaaaaaaa",data);
				$scope.displayedCollection1=[];
				bootbox.alert({
	  			    message: "Cases Transferred Successfully",
	  			    className: 'rubberBand animated',
	  			    	backdrop: true
	  			
	  			});
				
			}else{
				alert(data.data);
			}

		});
		response.error(function(data, status, headers, config) {
			alert("Input field not empty");
		});
    	
    }
    
    $scope.showTransferCases =function (range){
    	$scope.showTansferMessage =false;
    	$scope.displayedCollection1 =[];
    	console.log("transfer infoooooo",range);
    	angular.forEach($scope.displayedCollection, function (value, key) { 
          console.log("valuessssssssssssss",value);
          if(value.cl_serial_no >= range.from && value.cl_serial_no <= range.to ){
        	  value.checked =true;
        	  value.cl_transfer_to =range.cl_court_no;
        	  $scope.displayedCollection1.push(value);
        	  $scope.showTansferMessage =true
        	 
          }
        });
    	console.log("new dataaaaaaaaaaaaaaaa",$scope.displayedCollection1);
    	if($scope.showTansferMessage){
    		if($scope.courtList && $scope.courtList1){
    			if($scope.model.cl_court_no){
    		 var court1 =	search($scope.model.cl_court_no , $scope.courtList);
    		 var court2 =	search($scope.range.cl_court_no , $scope.courtList1);
    		 console.log("Court",court1);
    		 console.log("Court",$scope.displayedCollection1.length);
    		 var count = $scope.displayedCollection1.length ;
    			 $scope.showMessage = count+" Cases are transfered from "+court1.cm_name+" to "+court2.cm_name;
    		// $scope.showMessage = "helllo";
    			 console.log("Transfered",court1)
    			}
    		}
    		 
    	}
    	
    }
    
    
    function search(nameKey, myArray){
        for (var i=0; i < myArray.length; i++) {
            if (myArray[i].cm_id == nameKey) {
                return myArray[i];
            }
        }
    }
    $scope.addCaseToCouseList = function (clmodel) {
    	
    	$scope.appMendotory=false;
    	if(clmodel.cl_list_type_mid==1 || clmodel.cl_list_type_mid==2 ){
    		if($scope.subDocumentsList==undefined && $scope.itr!=0){
	  		$scope.appMendotory=true;
    		}
	  	}
    	
    	 var len=Object.keys($scope.caseFileList).length;
    	  for(var i=0;i<len;i++){
    		  $scope.caseFileList[i].cl_court_no=clmodel.cl_court_no;
    		  $scope.caseFileList[i].cl_dol=clmodel.cl_dol;
    		  $scope.caseFileList[i].cl_list_type_mid=clmodel.cl_list_type_mid;
    		  $scope.caseFileList[i].subDocument=$scope.subDocumentsList;
    	  }
    	  
			  	if($scope.caseFileList==undefined)
			  		{
			  		$scope.entity2 =clmodel;
			  		}
			  	else
			  		{
			  		$scope.entity2= $scope.caseFileList;
			  		}   	
			  	
			  	
			  	
			  	if($scope.appMendotory==true){
			  		alert("Please select Application");
			  	}

			  	else
		  	{
		  	var response = $http.post(urlBase+'causelist/addCaseToCouseList',$scope.entity2);		  	
			response.success(function(data, status, headers, config) {
				$scope.courtList = data.modelList;
				if(data.response=="TRUE"){
					$scope.caseFileList=[{}];
					$scope.clmodel={};
					$scope.subDocuments={};
					$scope.itr=0;
					alert(data.data);
					 getCourtList();
				}else{
					alert(data.data);
				}

			});
			response.error(function(data, status, headers, config) {
				alert("Input field not empty");
			});
		  	}
		}
    
	$scope.checklist = function () 
	{
		$scope.subDocumentsList=[];
  	  	angular.forEach($scope.subDocuments,function(value,index){
  	  		
  	  	if(value.checked == true)
		{
			 $scope.subDocumentsList.push(value);
		}
  		  
        });
	 };
    
    
    $scope.vieapplication = function (row1,clmodel) {
	  	/*$scope.row=$scope.caseFileList[0];*/
    	$scope.row=row1;
	  	if($scope.row==undefined)
	  		{
	  		$scope.entity2 =clmodel;
	  			console.log("view Application");
	  		}
	  	else
	  		{
	  		$scope.entity2 =$scope.row; //angular.extend($scope.row,clmodel);
	  		}
	  	var response = $http.post(urlBase+'causelist/vieapplication',$scope.entity2);
		response.success(function(data, status, headers, config) {
			$scope.subDocuments = data.modelList;
			if(data.response=="TRUE")
			{
				if($scope.subDocuments.length==0)
					{
						alert("No Record found..");
					}
				else
					{
				$scope.approw=true;
					}
				
			}else{
				alert(data.data);
			}

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	}
    

/*	function getCauseListTypes() {
		var response = $http.get(urlBase+'causelist/getCauseListTypes');
		response.success(function(data, status, headers, config) {
			$scope.causeListTypes = data.modelList;
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}*/
    
	$scope.deleteCauseFile=function(index){
		if(index==0 && $scope.itr==1){
			$scope.caseFileList[index]={};
			$scope.itr=0;	
		
	}
		else{
			$scope.caseFileList.splice(index, 1);
			$scope.itr--;
		}
	}
	
	$scope.applicationOnly=function(index,clmodel){
		
		if(clmodel.cl_list_type_mid==1 || clmodel.cl_list_type_mid==2){
			
			if($scope.itr!=0){
			
			for(var i=$scope.itr-1;i>index;i--){
				$scope.caseFileList.splice(i, 1);
			}
			$scope.itr=index+1;
			}	
		}
	}
	
	
	  $scope.searchCaseFiles=function() 
	  {
		  $scope.len=Object.keys($scope.caseFileList).length;
		  var bool=false;
		  console.log($scope.len);
		  for(var i=0;i<$scope.len;i++){
			  var test1=$scope.caseFileList[i];
			  if(test1.fd_case_no==$scope.search.fd_case_no && test1.fd_case_year==$scope.search.fd_case_year){
				  bool=true;
			  }
		  }
		  
		  console.log($scope.caseFileList);
		  
		  if(!bool){
		  $http.post(urlBase+'casefile/getCaseFileLists',$scope.search).success(function (data) {
		    	if(data.response=="TRUE")
		    		{
		    		$scope.caseFileList[$scope.itr]=data.modelData;
		    		$scope.addcl=data.modelList;
		    		console.log("-----------------------------"+$scope.addcl);
		    		console.log($scope.caseFileList);
		    		$scope.itr++;
		    		}
		    	else
		    		alert("No Record Found");
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
	  }
		  console.log($scope.caseFileList.length);
	  }
    
    
    $scope.formats = ['dd-MM-yyyy', 'yyyy-MM-dd', 'shortDate'];
	$scope.format = $scope.formats[0];
    $scope.today = function() {
    	$scope.model.cl_dol = new Date();
    	$scope.clmodel.cl_dol = new Date();
    	$scope.report.cl_date = null;
    	$scope.maxDate = new Date()
	};
	if($scope.type_id!='' && $scope.type_id!=null){
		$scope.model.cl_list_type_mid=$scope.type_id;
		$scope.model.cl_dol=new Date();
		$scope.maxDate = new Date()
		getData($scope.model);
	}
	$scope.today();
	
	$scope.clear = function () {
		$scope.model.cl_dol = null;
		$scope.report.cl_date=null;
		$scope.clmodel.cl_dol = null;
	};	

	 $scope.open = function($event,opened) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[opened] = true;
		  };
    	
	$scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	};

    

	function getCauseListTypes() {
		var response = $http.get(urlBase+'causelist/getCauseListTypes');
		response.success(function(data, status, headers, config) {
			$scope.causeListTypes = data.modelList;
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	
	  function getCaseTypes(){
		  $http.get(urlBase+'master/getcasetypes').success(function (data) {
		    		$scope.caseTypes=data.modelList;		    	
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	$scope.getCaseTypes=function() {
		var response = $http.get(urlBase+'master/getcasetypes');
		response.success(function(data, status, headers, config) {
			$scope.caseTypes = data.modelList;
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	$scope.getApplicationStages=function() {
		var response = $http.get(urlBase+'master/getapplicationstages');
		response.success(function(data, status, headers, config) {
			$scope.applicationStages = data.modelList;
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getCourtList() {
		var response = $http.get(urlBase+'causelist/getCourtList');
		response.success(function(data, status, headers, config) {
			$scope.courtList = data.modelList;
			$scope.courtList1 = data.modelList;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	}
	$scope.getList=function(){
		getData();
	}
	function getData(){	
		$scope.search=true;
		$http.post(urlBase+'causelist/getCauseListForTransfer',$scope.model)
			.success(function(data) {
				$scope.masterdata=data.modelList;
				$scope.displayedCollection1 =[];
				$scope.showTansferMessage =false;
				$scope.displayedCollection = [].concat($scope.masterdata);
			//$scope.SearchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
	};
	$scope.downloadList=function(){	
		var d = new Date($scope.model.cl_dol);
	  	var m=("0" + (d.getMonth() + 1)).slice(-2)
	    var year=d.getFullYear();
	    var date = d.getDate()+"-"+m+"-"+year;	    
		window.open(urlBase+"causelist/downloadfiles/?courtno="+$scope.model.cl_court_no+"&date="+date,"_blank");			
	};
    $scope.$watch('files', function (files) {
        $scope.formUpload = false;
        if (files != null) {
          if (!angular.isArray(files)) {
            $timeout(function () {
              $scope.files = files = [files];
            });
            return;
          }
          for (var i = 0; i < files.length; i++) {
            $scope.errorMsg = null;
            (function (f) {
              $scope.upload(f, true);
            })(files[i]);
          }
        }
      });
	$scope.refreshModel=function(){
		$scope.document={};
		$scope.repositories=[];
		$scope.treedata=[];
		$scope.picFile='';
		$scope.errorlist=null;
	};
	$scope.onFileSelect = function ($files) {
        $scope.uploadProgress = 0;
        $scope.selectedFile = $files;
    };
    $scope.files = [];

    //listen for the file selected event
    $scope.$on("fileSelected", function (event, args) {
    	$scope.$apply(function () {            
            $scope.files.push(args.file);
        });
    });
    
    $scope.loading = true;
	$scope.save=function() 
	{
		 $scope.loading = false;
		  var file=$scope.picFile;
		  if($scope.picFile==''){
			  alert("Please select file");
			  return false;
		  }
		  	//var d = new Date($scope.causelist.cl_date);
		  	//var m=("0" + (d.getMonth() + 1)).slice(-2)
		    //var year=d.getFullYear();
		    //var n = d.getDate()+"-"+m+"-"+year;
		    //$scope.causelist.cl_date=n;
		    file.upload = Upload.upload({
		      url: urlBase + 'causelist/create',
		      headers: {
		    	  'optional-header': 'header-value'
		        },
		        fields:$scope.causelist,
    		   file:file,
    		  
		    });
		   
		    file.upload.then(function (response) {
		        if(response.data.response=="TRUE"){
		        	$scope.errorlist =null;
		        	
		        	alert("Successfully uploaded complete causelist");
		        	$("#causeListCreate").modal("hide");
		        	$scope.loading = true;
		        	//window.location.reload();
		        }else{
		        	alert(response.data.data);
		        	$("#causeListCreate").modal("hide");
		        	$scope.loading = true;
		        }
		      }, function (response) {
		        
		      }, function (evt) {
		        // Math.min is to fix IE which reports 200% sometimes
		        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		      });

		      file.upload.xhr(function (xhr) {
		        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
		      });
		}  	  
	$scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
	$scope.viewApplication=function(application_no,application_year,case_id){
		  window.open(urlBase+"casefile/applicationview/?app_no="+application_no+"&app_year="+application_year+"&case_id="+case_id,"_blank");
	  }
	
	$scope.updatePriority=function(id){
		var response = $http.get(urlBase+'causelist/updatepriority/'+id);
		response.success(function(data, status, headers, config) {
			if(data.response=="TRUE"){
				alert("Priority updated please refresh the page for updated list");
			}
		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	  }
	$scope.submitReport=function(){
		var confirmbox = confirm("Do you really want to proceed");
	   	 if (confirmbox) 
	   	 {
	   		 angular.forEach($scope.displayedCollection,function(value,index){							 
				if(value.checked == true)
				{
					$scope.Obj=value;
					$scope.Obj.cl_stage_id=$scope.report.cl_stage_id;
					$scope.Obj.cl_date=$scope.report.cl_date;
					$scope.checkListObj.push($scope.Obj);
				}
				
			});
	   		 if($scope.checkListObj.length>0){
	   			$http.post(urlBase+'causelist/updateapplicationstatus',$scope.checkListObj)
				.success(function(data) {
					if(data.response=="TRUE"){
						alert(data.data);
					}else{
						alert(data.data);
					}
					window.location.reload();
				//$scope.SearchList = data.SearchedData;
				}).error(function(data, status, headers, config) {					
				});	
	   		 }else{
	   			 alert('Please select atleast one application');
	   		 }
	   	 }
	}
	$scope.deletecase=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'causelist/delete/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	
	$scope.checkAll = function () 
	{
		if($scope.range.cl_court_no){
		$scope.displayedCollection1=[];
  	  if ($scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
  	  	angular.forEach($scope.displayedCollection,function(value,index){
  		  
  		  value.checked=$scope.selectedAll;
  		  if($scope.range.cl_court_no){
  		 if(value.checked){
  			 value.cl_transfer_to =$scope.range.cl_court_no;
  			$scope.displayedCollection1.push(value);
  			$scope.showTansferMessage =true;
  	    	
  	    	
  	    	
  	    	console.log("new dataaaaaaaaaaaaaaaa",$scope.displayedCollection1);
  	    	if($scope.showTansferMessage){
  	    		if($scope.courtList && $scope.courtList1){
  	    			if($scope.model.cl_court_no){
  	    		 var court1 =	search($scope.model.cl_court_no , $scope.courtList);
  	    		 var court2 =	search($scope.range.cl_court_no , $scope.courtList1);
  	    		 console.log("Court",court1);
  	    		 console.log("Court",$scope.displayedCollection1.length);
  	    		 var count = $scope.displayedCollection1.length ;
  	    			 $scope.showMessage = count+" Cases are transfered from "+court1.cm_name+" to "+court2.cm_name;
  	    		// $scope.showMessage = "helllo";
  	    			 console.log("Transfered",court1)
  	    			}
  	    		}
  	    		 
  	    	}
  		 }
  		 else {
  			$scope.showTansferMessage =false;
  			$scope.displayedCollection1 =[];
  		 }
  		  }
  		  
  		 // $scope.bundlelist.splice($scope.bundlelist.indexOf(value), 1);
  		  
        });
	}
		else {
  			bootbox.alert({
  			    message: "First please Select The Court No in which you wish to transfer cases",
  			    className: 'rubberBand animated',
  			    	backdrop: true
  			
  			});
  			
  			/*alert("First please Select The Court No in which you wish to transfer cases");*/
  			
  			return false;
  			
  		  }

	 };
	 $scope.updatecauselist=function(){
			var confirmbox = confirm("Do you really want to proceed");
		   	 if (confirmbox) 
		   	 {
		   		 angular.forEach($scope.displayedCollection,function(value,index){							 
					if(value.checked == true)
					{
						$scope.Obj=value;
						$scope.Obj.cl_new_court_no=$scope.cl_new_court_no;
						$scope.checkListObj.push($scope.Obj);
					}
				});
		   		 if($scope.checkListObj.length>0){
		   			$http.post(urlBase+'causelist/updatecauselist',$scope.checkListObj)
					.success(function(data) {
						if(data.response=="TRUE"){
							alert(data.data);
						}else{
							alert(data.data);
						}
						window.location.reload();
					//$scope.SearchList = data.SearchedData;
					}).error(function(data, status, headers, config) {					
					});	
		   		 }else{
		   			 alert('Please select atleast one record');
		   		 }
		   	 }
		}
	 $scope.deleteall=function(){
			var confirmbox = confirm("Do you really want to proceed");
		   	 if (confirmbox) 
		   	 {
		   		 angular.forEach($scope.displayedCollection,function(value,index){							 
					if(value.checked == true)
					{
						$scope.Obj=value;
						$scope.Obj.cl_new_court_no=$scope.cl_new_court_no;
						$scope.checkListObj.push($scope.Obj);
					}
				});
		   		 if($scope.checkListObj.length>0){
		   			$http.post(urlBase+'causelist/deleteall',$scope.checkListObj)
					.success(function(data) {
						if(data.response=="TRUE"){
							alert(data.data);
						}else{
							alert(data.data);
						}
						window.location.reload();
					//$scope.SearchList = data.SearchedData;
					}).error(function(data, status, headers, config) {					
					});	
		   		 }else{
		   			 alert('Please select atleast one record');
		   		 }
		   	 }
		}
	 $scope.create=function(){	
			$http.post(urlBase+'causelist/addcase',$scope.clmodel)
				.success(function(data) {
					if(data.response=="TRUE"){
						alert(data.data);
					}else{
						alert(data.data);
					}
					$("#caseAdd").modal("hide");
				//$scope.SearchList = data.SearchedData;
			}).error(function(data, status, headers, config) {
				console.log("Error in adding causelist ");
			});	
		};

		

		

		
}]);
