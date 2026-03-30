var EDMSApp = angular.module("EDMSApp", ['ui.bootstrap','smart-table','angularUtils.directives.dirPagination']);

EDMSApp.controller('SearchController',['$scope', '$http' ,'$q','$filter','$sce',function ($scope, $http,$q,$filter,$sce) {
	var urlBase="/dms/";


    getMasterdata();
    $scope.handoverdata=[];
    $scope.handoverdataArray=[];
    
    
    
   
    $scope.trustedHtml=null;
	 $scope.open = function($event,opened) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[opened] = true;

		  };
		  
			 $scope.opens = function($event,opened) {
				    $event.preventDefault();
				    $event.stopPropagation();

				    $scope[opened] = true;
				  };

		//		  
				  $scope.searchreport = function(model) {                                                                     

						$http.get(urlBase+'reports/getReportData',{params: {'fromDate':$scope.model.cl_date,'toDate':$scope.model.cl_dol}}).success(function(data) {
					
							for(let i =0 ; i< data.modelData.length ;i++){
								var handoverdata1={};
								
								console.log("data in LLLLLLLLLLLLLLL",data.modelData.length);
							
								
								handoverdata1.ct_name =data.modelData[i][2].ct_label;
								handoverdata1.sd_cr_date =data.modelData[i][0].sd_cr_date;
								handoverdata1.sd_document_name =data.modelData[i][0].sd_document_name;
								handoverdata1.fd_case_no =data.modelData[i][1].fd_case_no;
								handoverdata1.sd_id =data.modelData[i][0].sd_id;
								handoverdata1.fd_case_year =data.modelData[i][1].fd_case_year;
								handoverdata1.um_fullname =data.modelData[i][0].user.um_fullname;
									handoverdata1.ord_remark =$sce.trustAsHtml(data.modelData[i][3].ord_remark);
								$scope.handoverdataArray.push(handoverdata1);
							}
							

						}).error(function(data, status, headers, config) {
							console.log("Error in getting ProductivityReportData ");
						});

					};

			$scope.Getfile=function(data)

			{
			filename=data.sd_document_name;
			sd_id=data.sd_id;
			$http.get(urlBase+'reports/Getfilename',{params: {'sd_document_name':filename,'sd_id':sd_id}})
				.success(function(data){

					
				$scope.fileurl=urlBase+"uploads/"+filename+".pdf";
				$scope.title=filename;
				$('#casefile_Modal').modal('show');
				console.log($scope.fileurl);


			}).error(function(data, status, headers, config) {
			console.log("Error in getting Report ");
			});	
			//}
			};


			function getMasterdata() {
			debugger;
			var response = $http.get(urlBase+'reports/getByOfficeReport');
			response.success(function(data, status, headers, config) {
				console.log(data);
				//$scope.displayedCollections = [].concat($scope.handoverdata);
			//	console.log($scope.displayedCollections);

				for(let i =0 ; i< data.modelData.length ;i++){
					var handoverdata1={};
					
					console.log("data in LLLLLLLLLLLLLLL",data.modelData[i][2].ct_label);

					handoverdata1.ct_name =data.modelData[i][2].ct_label;
					console.log(handoverdata1,"data in LLLLLLLLLLLLLLL",data.modelData[i][2].ct_label);
					handoverdata1.sd_cr_date =data.modelData[i][0].sd_cr_date;
					handoverdata1.sd_document_name =data.modelData[i][0].sd_document_name;
					handoverdata1.fd_case_no =data.modelData[i][1].fd_case_no;
					handoverdata1.sd_id =data.modelData[i][0].sd_id;
					handoverdata1.fd_case_year =data.modelData[i][1].fd_case_year;
					handoverdata1.um_fullname =data.modelData[i][0].user.um_fullname;
					handoverdata1.ord_remark =$sce.trustAsHtml(data.modelData[i][3].ord_remark);
					$scope.handoverdataArray.push(handoverdata1);
				}
				
	
				
				
			});
			response.error(function(data, status, headers, config) {
			});

			};

}]);