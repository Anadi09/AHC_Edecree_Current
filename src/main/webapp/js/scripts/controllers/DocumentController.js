/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['ngFileUpload','smart-table','ui.bootstrap','treeControl','ngMask']);

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

edmsApp.controller("documentController",['$scope','$http','Upload', function($scope,$http,Upload) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;

	  $scope.invalidFiles = [];

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
	getFoldersdata();
	$scope.errorlist=null;
	$scope.selectedNode="";
	$scope.selectedFolder="";
	$scope.picFile='';
    $scope.treedata=[];
    $scope.benchCodes=[];
    $scope.caseTypes =[];
    $scope.years=[];
    getBenchdata();
    getCaseTypedata();
    getYears();
    function getBenchdata() {
		var response = $http.get(urlBase+'report/getbenchcode');
		response.success(function(data, status, headers, config) {
			$scope.benchCodes = data.branchData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getCaseTypedata() {
		var response = $http.get(urlBase+'report/getcasetypedata');
		response.success(function(data, status, headers, config) {
			$scope.caseTypes = data.casetypeData;

		});
		response.error(function(data, status, headers, config) {
			alert("Error");
		});

	}
	function getYears() {
		var currentYear = new Date().getFullYear();
		for (var year = 1950; year <= currentYear; year++) {
			$scope.years.push(year);
			
		}
	}

    $scope.opts = {
        nodeChildren: "childrens"
    };
    $scope.opts2 = {
            nodeChildren: "childrens"
        };
    $scope.getCasefiles = function(sel)
    {
        $scope.selectedFolder = sel;
        $scope.getCasefilesUsingFolder();
    };
    $scope.showSelected = function(sel)
    {
        $scope.selectedNode = sel;
    };
    $scope.setParent=function(document){
    	$('#parentdocuments').find('.btn-success').removeClass('btn-success');
    	$('#'+document.id).addClass('btn-success');
    	$scope.document.parent_id=document.id;
    	$scope.document.rep_id=document.rep_id;
    	$scope.document.folder_id=document.folder_id;
    }
    $scope.getCasefilesUsingCaseNo=function(){
    	
    	$scope.entity=$scope.document;
    	if($scope.document.caseNo=="")
    	{
    		bootbox.alert("Please Select Case No.");
    		return false;
    	}
    	$http.post(urlBase+'document/getcasefileusingcaseno',$scope.entity).
        success(function (data) {
        	$scope.parentdocuments = data;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
    	
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
    function getFoldersdata()
	{
		$http.get(urlBase+'document/getalldocuments').
        success(function (data) {
        	$scope.masterdata = data;
        	$scope.displayedCollection = [].concat($scope.masterdata);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	$scope.refreshModel=function(){
		$scope.document={};
		$scope.repositories=[];
		$scope.treedata=[];
		$scope.picFile='';
		$scope.errorlist=null;
		$scope.getRepositories();
	};
	$scope.getRepositories=function(){
		
		$http.get(urlBase+'repository/getrepositories').
        success(function (data) {
        	$scope.repositories = data;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	
	$scope.getFolderStructure=function(){
		$scope.entity={"rep_id":$scope.document.rep_id};
		console.log($scope.entity);
		$http.post(urlBase+'folder/gettree',$scope.entity).
        success(function (data) {
        	$scope.treedata=data.treedata;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
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
    
	$scope.save=function() 
	{
		  var file=$scope.picFile;
		  if($scope.selectedNode!=""){
				$scope.document.folder_id=$scope.selectedNode.id;
			}
		    file.upload = Upload.upload({
		      url: urlBase + 'document/create',
		      headers: {
		    	  'optional-header': 'header-value'
		        },
		       fields:$scope.document,
    		   file:file,
		    });

		    file.upload.then(function (response) {
		        if(response.data.response=="TRUE"){
		        	$scope.errorlist =null;
		        	bootbox.alert("Successfully uploaded Documents");
		        	$("#documentCreate").modal("hide");
		        	$scope.document={};
		        	//window.location.reload();
		        }else{
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
		}
        	  
       
		
}]);