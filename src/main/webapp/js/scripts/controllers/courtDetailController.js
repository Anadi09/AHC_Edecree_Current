
var edmsApp = angular.module("EDMSApp", ['ngFileUpload','smart-table','ui.bootstrap','ngMask']);

edmsApp.controller("courtDetailController",['$scope','$http','Upload', function($scope,$http,Upload) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;

	  $scope.invalidFiles = [];
	  $scope.checkListObj=[];
	  $scope.model={};
	  $scope.report={};
	  $scope.itr=0;
	  $scope.type_id= $('#type_id').val();
	  $scope.checkListObj=[];
	 
	$scope.folder={};
	$scope.errorlist=null;
	$scope.picFile='';
    $scope.causeListTypes =[];
    $scope.masterdata=[];
    $scope.displayedCollection = []
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
    
    $scope.causelist_date = convertDate(new Date());
	$scope.dailycount=0;
	$scope.freshcount=0;
	$scope.backlogcount=0;
	$scope.supplementarycount=0;
	$scope.additionalcount=0;
	$scope.applicationcount=0;
	$scope.correctioncount=0;
	$scope.freshapplicationlist1=0;
	$scope.freshapplicationlist2=0;
	$scope.freshapplicationlist3=0;
	$scope.additionallist1=0;
	$scope.additionallist2=0;
	$scope.additionallist3=0;
	$scope.additionallist4=0;
	$scope.additionallist5=0;
	
	$scope.divShow=false;
	$scope.causeListReport =[];
	var currentCourt =null;
    
function arrangeListWithCourt(courtWiseData){
	var courtData = {};
	courtData.cl_court_no =courtWiseData[0].cl_court_no;
	for(var j= 0 ; j < courtWiseData.length ; j++){
		if(courtWiseData[j].cl_list_type_mid == 1){
			courtData.A =courtWiseData[j].cl_list_type_mid;
			courtData.Acount =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 2){
			courtData.C =courtWiseData[j].cl_list_type_mid;
			courtData.Ccount =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 3){
			courtData.D =courtWiseData[j].cl_list_type_mid;
			courtData.Dcount =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 4){
			courtData.B =courtWiseData[j].cl_list_type_mid;
			courtData.Bcount =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 5){
			courtData.F =courtWiseData[j].cl_list_type_mid;
			courtData.Fcount =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 6){
			courtData.S =courtWiseData[j].cl_list_type_mid;
			courtData.Scount =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 7){
			courtData.U =courtWiseData[j].cl_list_type_mid;
			courtData.Ucount =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 8){
			courtData.S1 =courtWiseData[j].cl_list_type_mid;
			courtData.S1count =courtWiseData[j].count;
			
		}
		if(courtWiseData[j].cl_list_type_mid == 9){
			courtData.S2 =courtWiseData[j].cl_list_type_mid;
			courtData.S2count =courtWiseData[j].count;
			
		}
		
	}
	$scope.causeListReport.push(courtData);
	
	console.log("Court Data latest",$scope.causeListReport);
	
	
	}
	
	
    function loadMasterData() {
		var response = $http.get(urlBase+'court/getAllCourtsCauselistReport?causelist_date='+$scope.causelist_date);
		response.success(function(data, status, headers, config) {
			var a =data.modelList;
			var courtNo =[500];
			currentCourt =null;
			
			for(var j= 0 ; j < a.length ; j++){
				let obj = courtNo.find(o => o == a[j].cl_court_no );
			if(obj){
			
			}
			else {
				courtNo.push(a[j].cl_court_no);
			}
			}
			
			console.log("Court no",courtNo);
			
			for(var k= 0 ; k < courtNo.length ; k++){
				courtWise =[];
				currentCourt =courtNo[k];
				courtWise =a.filter(filterData)
				console.log("gggggggggggggggggggg",courtWise);
				arrangeListWithCourt(courtWise);
				
				
			}
			
			
			
			
			
			
			$scope.masterdata= data.modelList;
			console.log("get report dataaaaaaaaaa",$scope.masterdata);
			angular.forEach($scope.masterdata, function(object, key) {
				  
				  if(object.cl_list_type_mid==1){
					
					  $scope.applicationcount=object.count
				  }
				  if(object.cl_list_type_mid==2){
					  $scope.correctioncount=object.count
				  }
				  if(object.cl_list_type_mid==3){
					  $scope.dailycount=object.count
				  }
				  if(object.cl_list_type_mid==4){
					  $scope.backlogcount=object.count
				  }
				  if(object.cl_list_type_mid==5){
					  $scope.freshcount=object.count
				  }
				  if(object.cl_list_type_mid==6){
					  $scope.supplementarycount=object.count
				  }
				  if(object.cl_list_type_mid==7){
					  $scope.additionalcount=object.count
				  }
				  if(object.cl_list_type_mid==8){
					  $scope.freshapplicationlist1=object.count
				  }
				  if(object.cl_list_type_mid==9){
					  $scope.freshapplicationlist2=object.count
				  }
				  if(object.cl_list_type_mid==10){
					  $scope.freshapplicationlist3=object.count
				  }
				  if(object.cl_list_type_mid==11){
					  $scope.additionallist1=object.count
				  }
				  if(object.cl_list_type_mid==12){
					  $scope.additionallist2=object.count
				  }
				  if(object.cl_list_type_mid==13){
					  $scope.additionallist3=object.count
				  }
				  if(object.cl_list_type_mid==14){
					  $scope.additionallist4=object.count
				  }
				  if(object.cl_list_type_mid==15){
					  $scope.additionallist5=object.count
				  }
				});
			
		});
		response.error(function(data, status, headers, config) {
			//alert("Error");
		});
		
	};
	
	function filterData(data){
		console.log("currentCourt",currentCourt);
		return data.cl_court_no == 72;
		
	}
	
	$scope.getCauseList=function(cause_list_date){
		$scope.causelist_date=convertDate(cause_list_date);
		loadMasterData();
		
		console.log("hello"+cause_list_date);
		
	}
	
		
		function convertDate(inputFormat) 
		{
			  function pad(s){ 
				  return (s < 10) ? '0' + s : s; 
				  }
			  var d = new Date(inputFormat);
			  return [ d.getFullYear(), pad(d.getMonth()+1),pad(d.getDate())].join('-');
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
    

    
    
       $scope.addManualCase = function () {
    	   
		  	var response = $http.post(urlBase+'causelist/addManualCase',$scope.causelist);		  	
			response.success(function(data, status, headers, config) {
				
				if(data.response=="TRUE"){
					
			    	alert("Record saved successfully");
			    	$("#addManualCase").modal("hide");
				}
			});
			response.error(function(data, status, headers, config) {
				
				alert("Error occurred while saved record");
			});
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

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	}
	/*$scope.getList=function(){
		getData();
	}
	function getData(){	
		$scope.search=true;
		$http.post(urlBase+'causelist/getCauseList',$scope.model)
			.success(function(data) {
				$scope.masterdata=data.modelList;
				$scope.displayedCollection = [].concat($scope.masterdata);
			//$scope.SearchList = data.SearchedData;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Cause List Data ");
		});	
	};*/
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
	
	
	
	$scope.checkAll = function () 
	{		
  	  if ($scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
  	  	angular.forEach($scope.displayedCollection,function(value,index){
  		  
  		  value.checked=$scope.selectedAll;
  		 // $scope.bundlelist.splice($scope.bundlelist.indexOf(value), 1);
  		  
        });

	 };
	
	
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

		
		 //loadMasterData();
	    	
	    	

		
}]);
