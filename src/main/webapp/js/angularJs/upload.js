'use strict';


var app = angular.module('fileUpload', ['ngFileUpload']);
var version = '5.0.0';

app.controller('MyCtrl', ['$scope', '$http', '$timeout', '$compile', 'Upload', function ($scope, $http, $timeout, $compile, Upload) {
    $scope.usingFlash = FileAPI && FileAPI.upload != null;

    
    $scope.$watch('files', function (files) {
        $scope.formUpload = false;
        if (files != null) {
            for (var i = 0; i < files.length; i++) {
                $scope.errorMsg = null;
                (function (file) {
                    upload(file);
                })(files[i]);
            }
        }
    });

    $scope.uploadPic = function (files) {
        $scope.formUpload = true;
        if (files != null) {
            uploadUsingUpload(files[0])
        }
    };

    function uploadUsingUpload(file) {
	
	console.log(file);
        file.upload = Upload.upload({
            url: 'http://localhost:8080/registration/registration/addparty',
            method: 'POST',
            headers: {
                'my-header': 'my-header-value'
            },
            fields: {"partytypeId":1,"firstName":"asd","lastName":"asd","mobile":"23432434","registrationId":"289","emailId":"ajit@celusion.com","panNo":"asdd","age":"22","u_id":"23434","flatNo":"32","buildingname":"asd","floorNo":"33","road":"44","location":"mumbai","pincode":"32434","city":"mumbai","state":"maharashtra","occupationId":"68","gender":"male","partyNature":"2"},
	    // fields: {username: $scope.username},
            file: file,
            fileFormDataName: 'imagepath',
	    crossDomain:true,
        });

        file.upload.then(function (response) {
	    console.log(response.data);
            $timeout(function () {
                file.result = response.data;
            });
        }, function (response) {
	    alert("res");
	    console.log(response);
            if (response.status > 0)
                $scope.errorMsg = response.status + ': ' + response.data;
        });
    }

}]);
