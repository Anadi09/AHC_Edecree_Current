/*
 * 
 */

var edmsApp = angular.module("EDMSApp", ['ngFileUpload','smart-table','ui.bootstrap','treeControl']);

edmsApp.directive('loading', ['$http', function ($http) {
    return {
        restrict: 'A',
        link: function (scope, elm, attrs) {
            scope.isLoading = function () {
                return $http.pendingRequests.length > 0;
            };
            scope.$watch(scope.isLoading, function (v) {
                if (v) {
                    elm.show();
                } else {
                    elm.hide();
                }
            });
        }
    };
}]);
/**
 * Controller in index.jsp
 */

edmsApp.controller("documentlistController",['$scope','$http','Upload', function($scope,$http,Upload) {
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
	
	var baseUrl="/dms/";
	$scope.selectedNode="";
    $scope.treedata=[];
    $scope.error;
    $scope.documentlist=[];
   	$scope.document={};
   	rejectremarklist();
   	getRepositories();
   	
    $scope.opts = {
        nodeChildren: "childrens"
    };
    $scope.showSelected = function(sel)
    {
        $scope.selectedNode = sel;
    };
    function getRepositories(){
		$http.get(baseUrl+'repository/getrepositories').
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
		$http.post(baseUrl+'folder/gettree',$scope.entity).
        success(function (data) {
        	$scope.treedata=data.treedata;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	$scope.getsubdocuments=function(entity,doc_type)
	{
		$scope.doc_type=doc_type;
		
		$scope.document={'id':entity.id,'doc_type':doc_type};
		$http.post(baseUrl+'document/getsubdocuments',$scope.document).
        success(function (data) {
        	$scope.subdocuments = data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	};
	function rejectremarklist() {
		var response = $http.get(baseUrl+'dataentry/rejectremarklist');
		response.success(function(data, status, headers, config) {		
			console.log("== GET rejectremarklist ==");
			$scope.remarklist= data;
			//console.log(data);
			
		});
		response.error(function(data, status, headers, config) {
			// alert("Error");
		});
		
	};
	function getFoldersdata()
	{
		$http.get(baseUrl+'folder/getallfolders').
        success(function (data) {
        	$scope.masterdata = data;
        	$scope.displayedCollection = [].concat($scope.masterdata);
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting repositories data");
        });
	}
	
	
	$scope.getSearchData=function(document){
		$scope.entity=document;
		
		if($scope.selectedNode!=""){
			$scope.entity.folder_id=$scope.selectedNode.id;
		}
		
		$http.post(baseUrl+'document/getfreshdocumentlist',$scope.entity).
        success(function (data) {
        	$scope.documentlist=data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
		
	};
	$scope.getSavedDocumentList=function(document){
		$scope.entity=document;
		
		if($scope.selectedNode!=""){
			$scope.entity.folder_id=$scope.selectedNode.id;
		}
		
		$http.post(baseUrl+'document/getsaveddocumentlist',$scope.entity).
        success(function (data) {
        	$scope.documentlist=data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
		
	};
	$scope.getRejectedDocumentList=function(document){
		$scope.entity=document;
		
		if($scope.selectedNode!=""){
			$scope.entity.folder_id=$scope.selectedNode.id;
		}
		
		$http.post(baseUrl+'document/getrejecteddocumentlist',$scope.entity).
        success(function (data) {
        	$scope.documentlist=data.modelList;
        }).
        error(function(data, status, headers, config) {
        	console.log("Error in getting tree data");
        });
	};
	$scope.setModelData=function(document){
		$scope.document=angular.copy(document);
		console.log($scope.document);
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
		$scope.document.doc_type=1;
		console.log($scope.document);
		  var file=$scope.picFile;
		  
		    file.upload = Upload.upload({
		      url: baseUrl + 'document/reupload',
		      headers: {
		    	  'optional-header': 'header-value'
		        },
		       fields:$scope.document,
    		   file:file,
		    });

		    file.upload.then(function (response) {
		        if(response.data.response=="TRUE"){
		        	$scope.errorlist =null;
		        	bootbox.alert("Successfully Reuploaded Documents");
		        	$("#documentCreate").modal("hide");
		        	$scope.document={};
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

	$scope.rejectForScanning=function(data)
	{
		$scope.judgementFile=data;
		bootbox.confirm("Are you Rejecting file", function(result) {
			if(result){
					var response = $http.post(baseUrl+'casefile/rejectjudgementfile',$scope.judgementFile);
					response.success(function(data, status, headers, config) {
						
					});
			  }
			}); 

	}
	$scope.downloadFile=function(document,doc_type)
	{
		window.location.href="downloadfile?id="+document.id+"&doc_type="+doc_type;
	}
	
}]);