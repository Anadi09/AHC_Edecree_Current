/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['ngFileUpload','smart-table','ui.bootstrap','ngMask']);


edmsApp.controller("nominatedController",['$scope','$http','Upload', function($scope,$http,Upload) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;
		$scope.model={};
	  $scope.invalidFiles = [];
	  $scope.checkListObj=[];
	  $scope.model={};
	  $scope.report={};
	  $scope.nominatedReport={};
	  $scope.cnReport={};
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
    $scope.casefiledetail={};
 
    $scope.caseTypes=[];
    $scope.judgeList = [];
    getCaseTypes();
    getCauseListTypes();
    getCourtList();
    getCaseListByUser();
    getjudgeList();
    
    function getjudgeList() {
		$http.get(urlBase+'master/gethighcourtjudges').success(function(data) {
			console.log("--judge--");
			console.log(data);
			$scope.judgeList = data.modelList;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting Master");
		});
	}
    
    $scope.getJudge=function(){
		  $http.get(urlBase+'master/getapplications/'+$scope.subdocument.if_id).success(function (data) {
	    		$scope.applications=data.modelList;		    	
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting indexfields");
	      });
	  }
    
    $scope.loading = true;
	 $scope.saveRegistrarListing=function(row) 
		{
		 $scope.loading = false;
		 $scope.casefiledetail=row;
		 var response = $http.post(urlBase+'nomination/saveRegistrarListing',row);
		 response.success(function(data, status, headers, config){
			 
			   if(data.response=="TRUE"){
				   $scope.dmsCaseData=data.data;
		        	alert("Submitted Successfully ");
		        	$scope.loading = true;
		        	location.reload(true);
			   }
			   else{					   
				    alert("error ocurred  please check details");
		        	$scope.loading = true;
			   }  
		 });
		 
		}
	
    
    function getCaseListByUser(){
		  $http.get(urlBase+'casefile/getCaseListByUser').success(function (data) {
			  
			  	$scope.caseFileList=data.modelList;	
		    	console.log($scope.caseFileList);
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
    
    $scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
    
    
    $scope.getNominatedReport=function(caseNominated){
    	$scope.nominatedReport=angular.copy(caseNominated);
		$scope.srd={};
		$scope.caveat ={};
        $scope.courtFee=null;
	  	$http.get(urlBase+ 'nomination/getNominatedReport', {
				params : {
					'docId' : $scope.nominatedReport.caseNominated.cn_fd_mid
				}
			}).success(function(data) {	
				      $scope.cnReport=data.data;
					  console.log( $scope.cnReport);
			          $scope.srd = data.srd;
			          $scope.caveat = data.caveat;
			          $scope.courtFee=data.courtFee;
			}).error(function(data) {
			});			
  }
    
    document.getElementById("btnPrint").onclick = function() 
	{
		var printContents = document.getElementById("report_table").innerHTML;
	    var popupWin = window.open('', '_blank', 'width=300,height=300');
	    popupWin.document.open();
	    var style = "<style>";
        style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
        style = style + "padding: 2px 3px;text-align: left;}";
        style = style + "</style>";
        popupWin.document.write('<html><head>');
        //popupWin.document.write('<title></title>');
        popupWin.document.write(style);        
        popupWin.document.write('</head>');
        popupWin.document.write('<body onload="window.print()">');
        popupWin.document.write('<h4 style="text-align: center;text-decoration: underline;">Case Nominated To Court Details </h4>');
        popupWin.document.write(printContents);
        popupWin.document.write('</body></html>');
        
	    popupWin.document.close();
	}

    
    
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

	function getCourtList() {
		var response = $http.get(urlBase+'causelist/getCourtList');
		response.success(function(data, status, headers, config) {
			$scope.courtList = data.modelList;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});
	}
	$scope.getList=function(){
		getData();
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



		

		

		
}]);
