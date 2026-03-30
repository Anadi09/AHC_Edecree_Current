var edmsApp = angular.module("EDMSApp", ['ngFileUpload','ngMask']);
edmsApp.controller("CourtOrderController",['$scope','$http','Upload', function($scope,$http,Upload) {
	var urlBase="/dms/";
	  //$scope.usingFlash = FileAPI && FileAPI.upload != null;
		$scope.model={};
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

	$scope.getList=function(){
		$http.get(urlBase+'causelist/getallorders')
		.success(function(data) {
			$scope.courtorders=data.modelList;
		}).error(function(data, status, headers, config) {
			console.log("Error in getting orders Data ");
		});
	}
	
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
		  
		    file.upload = Upload.upload({
		      url: urlBase + 'causelist/uploadorders',
		      headers: {
		    	  'optional-header': 'header-value'
		        },
    		   file:file,
		    });
		    file.upload.then(function (response) {
		        if(response.data.response=="TRUE"){
		        	$scope.errorlist =null;
		        	alert(response.data.data);
		        }else{
		        	$("#uploadDocument").modal("hide");
		        }
		        window.location.reload();
		      }, function (response) {
		        
		      }, function (evt) {
		        // Math.min is to fix IE which reports 200% sometimes
		        //file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		      });

		      file.upload.xhr(function (xhr) {
		        // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
		      });
		}  	  
	$scope.viewFile=function(id){
		  window.open(urlBase+"causelist/vieworder/"+id,"_blank");
	  }
		
}]);
