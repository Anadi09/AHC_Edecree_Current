var EDMSApp = angular.module('EDMSApp', ['ui.bootstrap']);
EDMSApp.directive('loading', ['$http', function($http) {
    return {
        restrict: 'A',
        link: function(scope, elm, attrs) {
            scope.isLoading = function() {
                return $http.pendingRequests.length > 0;
            };
            scope.$watch(scope.isLoading, function(v) {
                if (v) {
                    elm.show();
                } else {
                    elm.hide();
                }
            });
        }
    };
}]);

EDMSApp.controller('scrutinyViewController', ['$scope', '$http', function($scope, $http) {


    var urlBase = "/adminpanel/"



    $scope.show = false;


    $scope.count = '';
    $scope.registerCase = {};


    $scope.petitionerDataList = [];
    $scope.respondentDataList = [];
    $scope.courtFeeList = [];
    $scope.trialDataList = [];
    $scope.impugnedDataList = [];
    $scope.draftList = [];
    $scope.uploadedDocumentsList = [];
    $scope.checkList = [];
    $scope.checkListObj = [];
    $scope.chckedIndexs = [];


    $scope.caseId = $('#caseId').val();
    
    
    if ($scope.caseId != null && $scope.caseId != undefined)
    	getRegisterApplication($scope.caseId);

    function getRegisterApplication(id) {

        $http.get(urlBase + 'application/getRegisterApplication', {
            params: {
                'docId': id
            }
        }).success(function(data, status, headers, config) {

            $scope.application = data.modelData;
            getEditData();
        }).error(function(data, status, headers, config) {});

    }

    function getEditData() {
        getCourtFee($scope.caseId);
        getUploadedDocuments($scope.caseId);
        getCheckList($scope.caseId);
        getRemarkHistory($scope.caseId);
    }
    function getRemarkHistory(id){
		$http.get(urlBase+ 'scrutiny/getApplicationHistory', {
			params : {
				'docId' : id
			}
		}).success(function(data, status, headers, config) {	            
		          $scope.checkListHistory = data.modelList;	            
		}).error(function(data, status, headers, config) {
		});
	}

        function getCourtFee(id) {
        $http.get(urlBase + 'application/getCourtFee', {
            params: {
                'docId': id
            }
        }).success(function(data, status, headers, config) {
            $scope.courtFeeList = data.modelList;



        }).error(function(data, status, headers, config) {});

    }

    function getUploadedDocuments(id) {

        $http.get(urlBase + 'application/getUploadedDocuments', {
            params: {
                'ap_id': id
            }
        }).
        success(function(data) {
            $scope.uploadedDocumentsList = data.modelList;
            console.log($scope.uploadedDocumentsList);
        }).
        error(function(data, status, headers, config) {
            console.log("Error in getting tree data");
        });
    };

    $scope.onFileSelect = function($files) {
        $scope.uploadProgress = 0;
        $scope.selectedFile = $files;
    };

    function getCheckList() {
        $http.get(urlBase + 'scrutiny/getCheckList', {
	    	params : {
	    		'type' : 'A'
	    	}
	    }).success(function(data, status, headers, config) {
            $scope.checkList = data.modelList;

        }).error(function(data, status, headers, config) {});

    }

    $scope.checkAll = function() {
        if ($scope.selectedAll) {
            $scope.selectedAll = true;
        } else {
            $scope.selectedAll = false;
        }
        angular.forEach($scope.checkList, function(value, index) {

            value.checkbox = $scope.selectedAll;
            // $scope.bundlelist.splice($scope.bundlelist.indexOf(value), 1);
            $scope.checkListObj.push(value);
            console.log("Value in checkListObj");
            console.log(JSON.stringify($scope.checkListObj));

        });

    };

    $scope.checkedIndex = function(data) {
        if ($scope.chckedIndexs.indexOf(data) === -1) {
            $scope.chckedIndexs.push(data);
        } else {
            $scope.chckedIndexs.splice($scope.chckedIndexs.indexOf(data), 1);
        }
        console.log("checked value" + JSON.stringify($scope.chckedIndexs));
    }

    $scope.submit_file = function(remarks) {
        $scope.buttonDisabled = true;
        angular.forEach($scope.checkList, function(value, index) {
            if (value.checkbox == true) {
                $scope.checkListObj.push(value);
            }
        });
        $scope.entity = {
            'checkList': $scope.checkListObj,
            'ap_id': $scope.application.ap_id
        };
        var response = $http.post(urlBase + 'scrutiny/submit_application_file', $scope.entity);
        response.success(function(data, status, headers, config) {
            //debugger;
            $scope.buttonDisabled = false;

            if (data.response == "REJECT") {
                $scope.errorlist = data.dataMapList;
                alert("File Sent for correction");
                window.location.href=urlBase+"scrutiny/applications";
            } else {
                $scope.buttonDisabled = false;
                alert("File Submited for Case no generation");
                window.location.href=urlBase+"scrutiny/applications";

            }

        });
        response.error(function(data, status, headers, config) {
            $scope.buttonDisabled = false;
            alert("Error");
        });


    };
    $scope.showDocument=function(selectedfile){
		var response = $http.get(urlBase+'scrutiny/copyApplicationFile',{params: {'au_document_name': selectedfile.au_document_name}});
		response.success(function(data, status, headers, config) {		
			console.log(data);
			if(data.data != null)
			{
				window.open(urlBase+"/uploads/"+data.data,'_blank');
			}
		});
		response.error(function(data, status, headers, config) {
			bootbox.alert("Error");
		});
	};

}]);