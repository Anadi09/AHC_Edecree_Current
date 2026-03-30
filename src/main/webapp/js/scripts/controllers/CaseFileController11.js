var EDMSApp = angular.module("EDMSApp", ['ngFileUpload', 'ngMask', 'ui.bootstrap']);

EDMSApp.controller('CaseFileController', ['$scope', '$http', '$sce', 'Upload', function($scope, $http, $sce, Upload) {
	var urlBase = "/eDecree/";
	$scope.picFile = '';
	$scope.caseTypes = [];
	$scope.search = {};
	$scope.subdocument = {};
	$scope.offRep = {};
	$scope.dmsCaseData = {};
	getCaseListByUser();
	getCaseTypes();
	getIndexFields();
	getMasterdata();
$scope.response=[];
	$scope.hideCheckAll = true

	$scope.isAllCheck = false;
	$scope.advFileList = [];
	$scope.selectAll = false;
	
	$scope.caseData={};




	$scope.applyTillDateToSelected = function() {
		angular.forEach($scope.advFileList, function(row) {
			if (row.selected) {
				row.tillDate = $scope.commonTillDate;
			}
		});
	};

	/*          Method for refresh decree-send-sms- page             */




	// Load data from backend
	$http.get('getSmsList').then(function(response) {
		$scope.advFileList = response.data;

		// add selected flag
		angular.forEach($scope.advFileList, function(row) {
			row.selected = false;
		});
	});

	// Select / Unselect all
	$scope.toggleAll = function() {
		angular.forEach($scope.advFileList, function(row) {
			row.selected = $scope.selectAll;
		});
	};

	// Sync Select All checkbox
	$scope.syncSelectAll = function() {
		$scope.selectAll = $scope.advFileList.length &&
			$scope.advFileList.every(row => row.selected);
	};

	// Send SMS to single row
	$scope.sendSingleSms = function(row) {

		// Clone row to avoid mutating table data
		var payload = angular.copy(row);
		payload.caseType=$scope.caseData.caseType;
		payload.caseNo=$scope.caseData.caseNumber;
		payload.caseYear=$scope.caseData.caseYear;

		if (payload.tilldate) {
			var d = new Date(payload.tilldate);

			// yyyy-MM-ddTHH:mm:ss
			payload.tilldate =
				d.getFullYear() + '-' +
				('0' + (d.getMonth() + 1)).slice(-2) + '-' +
				('0' + d.getDate()).slice(-2) + 'T' +
				('0' + d.getHours()).slice(-2) + ':' +
				('0' + d.getMinutes()).slice(-2) + ':' +
				('0' + d.getSeconds()).slice(-2);
		}

		console.log(payload);

		$http.post(urlBase + 'sms/sendAdvSingleSms', payload)
			.then(function(response) {
				if (response.data == 1) {
					$scope.caseTypes = [];
					$scope.advFileList=[];
					getCaseTypes();
					$scope.search = {};
					alert('SMS sent to ' + row.name);
				} else {
					alert("Failed to send sms");
				}
			});
	};


	// Check if any checkbox selected
	$scope.hasAnySelected = function() {
		return $scope.advFileList.some(row => row.selected);
	};

	// Send SMS to selected rows
	$scope.sendAllSms = function() {

		//  Get selected rows
		var selectedList = $scope.advFileList.filter(row => row.selected);

		if (selectedList.length === 0) {
			alert('Please select at least one record');
			return;
		}

		selectedList.forEach(function(row) {

		      if (!row.tillDate) row.tillDate = sourceDate;

		      //  ADD THESE 3 PROPERTIES
		      row.caseType = $scope.caseData.caseType;
		      row.caseNo   = $scope.caseData.caseNumber;
		      row.caseYear = $scope.caseData.caseYear;
		  });


		var sourceDate = selectedList.find(row => row.tillDate)?.tillDate;

		if (!sourceDate) {
			alert("Please select a Till Date in at least one!");
			return;
		}


		selectedList.forEach(function(row) {
			if (!row.tillDate) row.tillDate = sourceDate;
		});

		/*  Validate all mobile numbers before sending */
		for (var i = 0; i < selectedList.length; i++) {
			var item = selectedList[i];
			if (!item.mobile || item.mobile.length !== 10) {
				alert("Please enter valid mobile no for this AOR: " + item.aor);
				return;
			}
		}

		/* If all mobile numbers are valid, send once */
		$http.post(urlBase+'sms/sendAdvAllSms', selectedList)
		.then(function (res) {

		    console.log("----------------------"+JSON.stringify(res.data));

		    // SESSION EXPIRED
		    if (res.data.status === 'SESSION_EXPIRED') {
		        alert(res.data.message);
		        window.location.href = 'login'; // or your login URL
		        return;
		    }

		    // SUCCESS RESPONSE
		    if (res.data.status === 'COMPLETED') {

		        $scope.response = res.data.details;
				console.log("***************"+  $scope.response)

		        alert(
		            'SMS Completed!\n' +
		            'Success: ' + res.data.successCount + '\n' +
		            'Failed: ' + res.data.failureCount
		        );

		        // Reset UI state
		        $scope.advFileList = [];
		        selectedList.forEach(function (row) {
		            row.tillDate = null;
		        });

		        $scope.caseTypes = [];
		        getCaseTypes();
		        $scope.search = {};
		        $scope.hideCheckAll = false;
		    }

		})
		.catch(function (error) {
		    console.error(error);
		    alert('Server error while sending SMS');
		});


	};




	$scope.open1 = function($event, type) {
		$event.preventDefault();
		$event.stopPropagation();

		if (type == "fromDate1")
			$scope.fromDate1 = true;
		if (type == "toDate1")
			$scope.toDate = true;
	};

	$scope.toggleMax = function() {
		//$scope.minDate = $scope.minDate ? null : new Date();
		$scope.maxDate = new Date();
	};
	$scope.toggleMax();

	$scope.open = function($event, type) {
		$event.preventDefault();
		$event.stopPropagation();

		if (type == "fromDate")
			$scope.fromDate = true;
		if (type == "toDate")
			$scope.toDate = true;
	};

	$scope.dateOptions = {
		formatYear: 'yy',
		startingDay: 1

	};

	$scope.formats = ['dd-MMMM-yyyy', 'dd-mm-yyyy', 'yyyy/MM/dd', 'dd-MM-yyyy', 'shortDate'];
	$scope.format = $scope.formats[3];

	function convertDate(inputFormat) {
		function pad(s) { return (s < 10) ? '0' + s : s; }
		var d = new Date(inputFormat);
		return [d.getFullYear(), pad(d.getMonth() + 1), pad(d.getDate())].join('-');
	}

	$scope.searchreport = function(id) {

		$http.get(urlBase + 'casefile/getReportData/' + id).success(function(data) {

			$scope.officeReport = data.modelList;

			for (let i = 0; i < $scope.officeReport.length; i++) {
				$scope.officeReport[i].ord_remark = $sce.trustAsHtml(data.modelList[i].ord_remark);
			}


		}).error(function(data, status, headers, config) {
			console.log("Error in getting ProductivityReportData ");
		});

	};

	$scope.setOfficeRpt = function(officeRpt) {
		$scope.officeRpt = officeRpt;
		$scope.TemplateDescription = $scope.officeRpt.ord_remark;
		console.log($scope.officeRpt);
		$("#txtEditor1").Editor("setText", $sce.getTrustedHtml($scope.officeRpt.ord_remark));
		console.log($("#txtEditor").Editor("getText"));
	}


	$scope.updateOfficeRpt = function(officeRpt) {
		$scope.loading = false;

		$scope.newString = $("#txtEditor1").Editor("getText");
		// $scope.newString = $scope.newString.replace(/&amp;/g, "&");

		officeRpt.ord_remark = $scope.newString;

		console.log($scope.newString);
		/*var response =$http.post(urlBase+'casefile/updatereportdata',officeRpt);
		response.success(function(data, status, headers, config){
			   if(data.response=="Update"){
				   
				alert("Office Report Updated Successfully!");
				$scope.registerCase=data.modelData;
				$("#updateOfficeRpt").modal("hide");
			   }
			   else if(data.response=="Not Allowed") {
				   alert("Not Allowed!");
				   
			   }
			   else if(data.response=="Listed"){
				   alert("Listed In Court");
			   }
			   else {
				   alert("Some Problem while uploading");
				   
			   }
		});	*/

		var file = $scope.picFile3;
		console.log("File Found", file);

		if (file) {
			console.log("Fileeeeeeeeee exists ", file);
			$http({
				method: 'POST',
				url: urlBase + 'casefile/updatereportdatafile',
				headers: { 'Content-Type': undefined },
				transformRequest: function(data) {
					var formData = new FormData();

					formData.append('officeRpt', new Blob([angular.toJson(officeRpt)], {
						type: "application/json"
					}));
					formData.append("file", data.file);
					return formData;
				},
				data: { subDocument: $scope.subdocument, file: file }

			}).
				success(function(data, status, headers, config) {
					if (data.response == "Update") {

						alert("Office Report Updated Successfully!");
						$scope.registerCase = data.modelData;
						$("#updateOfficeRpt").modal("hide");
						$scope.picFile3 = null;
					}
					else if (data.response == "Not Allowed") {
						$scope.picFile3 = null;
						alert("Not Allowed!");

					}
					else if (data.response == "Listed") {
						$scope.picFile3 = null;
						alert("Listed In Court");
					}
					else {
						$scope.picFile3 = null;
						alert("Some Problem while uploading");

					}

				}).
				error(function(data, status, headers, config) {
					console.log("Error in getting casetypes");
					$scope.picFile3 = null;
				});
		}
		else {

			console.log("File not Found", file);
			var response = $http.post(urlBase + 'casefile/updatereportdata', officeRpt);
			response.success(function(data, status, headers, config) {
				if (data.response == "Update") {

					alert("Office Report Updated Successfully!");
					$scope.registerCase = data.modelData;
					$("#updateOfficeRpt").modal("hide");
				}
				else if (data.response == "Not Allowed") {
					alert("Not Allowed!");

				}
				else if (data.response == "Listed") {
					alert("Listed In Court");
				}
				else {
					alert("Some Problem while uploading");

				}
			});

		}


	}


	function getCaseTypes() {
		$http.get(urlBase + 'master/getcasetypes').success(function(data) {
			for (var i = 0; i < data.modelList.length; i++) {
				data.modelList[i].labelandname = "";
				data.modelList[i].labelandname = data.modelList[i].ct_label.concat("-", data.modelList[i].ct_name);

			}

			$scope.caseTypes = data.modelList;

			/* for (var i = 0; i < $scope.caseTypes.length; i++) {
				 $scope.caseTypes[i].labelandname ="";
				 $scope.caseTypes[i].labelandname = caseTypes[i].ct_label.concat("-",caseTypes[i].ct_label);
				 console.log("aaaaaaaaaaaaaaaaaaaaaaaa",$scope.caseTypes[i].labelandname);
			 }*/

		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting casetypes");
			});
	}

	function getCaseTypesForChangeCaseType() {
		$http.get(urlBase + 'master/getcasetypesforchange').success(function(data) {
			$scope.caseTypesForChange;
			for (var i = 0; i < data.modelList.length; i++) {
				data.modelList[i].labelandname = "";
				data.modelList[i].labelandname = data.modelList[i].ct_label.concat("-", data.modelList[i].ct_name);

			}

			$scope.caseTypesForChange = data.modelList;

			/* for (var i = 0; i < $scope.caseTypes.length; i++) {
				 $scope.caseTypes[i].labelandname ="";
				 $scope.caseTypes[i].labelandname = caseTypes[i].ct_label.concat("-",caseTypes[i].ct_label);
				 console.log("aaaaaaaaaaaaaaaaaaaaaaaa",$scope.caseTypes[i].labelandname);
			 }*/

		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting casetypes");
			});
	}


	getCaseTypesForChangeCaseType();


	function getIndexFields() {
		$http.get(urlBase + 'master/getindexfields').success(function(data) {
			$scope.index_fields = data.modelList;

		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting indexfields");
			});
	}
	$scope.getApplications = function() {
		$http.get(urlBase + 'master/getapplications/' + $scope.subdocument.if_id).success(function(data) {
			$scope.applications = data.modelList;

		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting indexfields");
			});
	}
	$scope.setModel = function(casefile) {
		$scope.casefile = casefile;
		$scope.sd_submitted_date = '';
		$scope.subdocument = {};
		$scope.picFile = '';
		console.log($scope.casefile);
	}

	$scope.searchCaseFiles = function() {
		$scope.showLoader = true;
		$scope.showStatus = false;
		$scope.caseFileList = [];
		$http.post(urlBase + 'casefile/getCaseFileList', $scope.search).success(function(data) {
			if (data.response == "TRUE") {
				$scope.caseFileList = data.modelList;

				if ($scope.caseFileList != null) {

				}
				else {
					$scope.showStatus = true;
				}
				$scope.showLoader = false;
			}



			else {
				$scope.caseFileList = [];
				$scope.showLoader = false;
				$scope.showStatus = true;

			}
		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting tree data");
			});

	}
	//  =======================================update by dev 2025 snd sms ===========================================

	$scope.searchCaseFileDecree = function() {

	    $scope.showLoader = true;
	    $scope.showStatus = false;
	    $scope.caseFileList = [];

	    $http.post(urlBase + 'casefile/getCaseFileDecree', $scope.search)
	        .success(function(data) {

	            if (data.response == "TRUE") {

	                $scope.advFileList = data.advList;
	                $scope.caseData = data.data;

	                angular.forEach($scope.advFileList, function(row) {
	                    row.selected = false;
	                });

	                $scope.isAllCheck = true;

	                //  CALL this api for sms send log
					$http.post(urlBase + 'sms/sent', {
					    caseType: $scope.caseData.caseType,
					    caseNo: $scope.caseData.caseNumber,
					    caseYear: $scope.caseData.caseYear
					}, {
					    headers: { 'Content-Type': 'application/json' }
					}).success(function(smsData) {

	                    $scope.smsLogList = smsData;
	                    console.log("SMS Log List:", smsData);

	                }).error(function() {
	                    console.log("Error while fetching SMS log");
	                });

	                $scope.showLoader = false;

	            } else {

	                $scope.advFileList = [];
	                $scope.showLoader = false;
	                $scope.showStatus = true;
	            }

	        })
	        .error(function() {
	            console.log("Error in getting case file data");
	            $scope.showLoader = false;
	        });
	};
	
	
	$scope.smsLogList = [];

	$scope.openSmsHistory = function () {

	    if (!$scope.caseData) {
	        alert("Please search case first");
	        return;
	    }

	    $http.post(urlBase + 'sms/sent', {
	        caseType: $scope.caseData.caseType,
	        caseNo: $scope.caseData.caseNumber,
	        caseYear: $scope.caseData.caseYear
	    }).then(function (response) {

	        $scope.smsLogList = response.data;

	        $('#smsHistoryModal').modal({
	            backdrop: 'static',
	            keyboard: false
	        });

	        $('#smsHistoryModal').modal('show');

	    }).catch(function (error) {
	        console.log("Error loading SMS history", error);
	    });
	};


/*==========================extra advocate===========================*/
$scope.extraAdv = {};

	$scope.saveExtraAdvocate = function () {

	    if ( !$scope.extraAdv.name) {
	        alert(" Advocate Name is  required !");
	        return;
	    }
		if(!$scope.extraAdv.mobile){
			alert("Mobile number is required!");
			return;
		}

	    $scope.extraAdv.caseType = $scope.caseData.caseType;
	    $scope.extraAdv.caseNo = $scope.caseData.caseNumber;
	    $scope.extraAdv.caseYear = $scope.caseData.caseYear;

	    if ($scope.extraAdv.mobile.length !== 10) {
	        alert("Please enter valid 10-digit mobile number");
	        return;
	    }

	    console.log("FINAL URL:", urlBase + 'sms/saveExtraAdvocate');
	    console.log("Sending:", $scope.extraAdv);

	    $http.post(urlBase + 'sms/saveExtraAdvocate', $scope.extraAdv)
	        .then(function (response) {

	            if (response.data.status === "success") {

	                alert("Saved Successfully");
					
					// Reload advocate list
					      $scope.loadgetadvocate(); 
					

	                if (!$scope.extraAdvList) {
	                    $scope.extraAdvList = [];
	                }

	                $scope.extraAdvList.push(response.data.data);

	                $scope.extraAdv = {};
	                $('#extraAdvocateModal').modal('hide');

	            } else {
	                alert(response.data.message || "Error saving data");
	            }

	        })
	        .catch(function (error) {
	            console.error("Error:", error);
	            alert("Server Error");
	        });
	};
	
	$scope.deleteExtraAdvocate = function (adv) {

	    if (!confirm("Are you sure you want to delete this record?")) {
	        return;
	    }

	    $http.post(urlBase + 'sms/deleteExtraAdvocate', { id: adv.ea_id })
	        .then(function (res) {

	            if (res.data.status === "success") {
	                alert("Deleted Successfully");

	                // 🔥 Refresh list
	                $scope.loadgetadvocate();

	            } else {
	                alert(res.data.message || "Delete failed");
	            }

	        })
	        .catch(function (err) {
	            console.error(err);
	            alert("Server error");
	        });
	};




	//================================================================================== 	  
	/* $scope.caseFileList=[];
	 $http.post(urlBase+'casefile/getCaseFileList',$scope.search).success(function (data) {
		   if(data.response=="TRUE")
			   {
			   $scope.caseFileList=data.modelList;
		   	
			   $scope.dmsCaseData=$scope.caseFileList[0];
			   $scope.dmsCaseData.caseType.ct_label=$scope.search.fd_case_type;
				 for (var i = 0; i < $scope.dmsCaseData.petitioners.length; i++) {
				   if($scope.dmsCaseData.petitioners[i].pt_sequence==1)
					   {
					   $scope.dmsCaseData.first_petitioner=$scope.dmsCaseData.petitioners[i].pt_name;
					   console.log("$scope.dmsCaseData.first_petitioner---"+$scope.dmsCaseData.first_petitioner);
					   }
			   };
			   for (var i = 0; i < $scope.dmsCaseData.respondents.length; i++) {
				   if($scope.dmsCaseData.respondents[i].rt_sequence==1)
					   {
					   $scope.dmsCaseData.first_respondent=$scope.dmsCaseData.respondents[i].rt_name;
					   console.log("$scope.dmsCaseData.first_respondent---"+$scope.dmsCaseData.first_respondent);
					   }
			   };
		   	
			   }
		   else
			   {
			   $scope.caseFileList=[];
			   $scope.dmsCaseData=$scope.caseFileList[0];
			   }
		 }).
		 error(function(data, status, headers, config) {
			   console.log("Error in getting tree data");
		 });*/




	$scope.loading = true;
	$scope.addCaseEfling = function() {
		$scope.loading = false;
		var response =
			http.post(urlBase + 'casefile/addCaseEfling', $scope.dmsCaseData);
		response.success(function(data, status, headers, config) {

			if (data.response == "TRUE") {
				$scope.dmsCaseData = data.data;
				alert(data.data);
				$("#addcaseefiling").modal("hide");
				$scope.loading = true;
			}
			else {
				alert("error ocurred  please check details");
				$("#addcaseefiling").modal("hide");
				$scope.loading = true;
			}
		});

	}

	function getCaseListByUser() {
		$http.get(urlBase + 'casefile/getCaseListByUser').success(function(data) {
			$scope.caseFileList = data.modelList;
			console.log($scope.caseList);
		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting casetypes");
			});
	}


	$scope.ord_remark = "";
	$scope.loading = true;
	$scope.cancel = function() {
		$scope.loading = true;
	}
	$scope.save = function() {
		$scope.loading = false;
		$scope.newString = $("#txtEditor").Editor("getText");
		$scope.subdocument.ord_remark = $scope.newString.replace("&nbsp;", " ");

		$scope.subdocument.ord_remark = $scope.newString.replace(/&nbsp;/g, " ");

		$scope.subdocument.ord_remark = $("#txtEditor").Editor("getText");
		console.log($scope.TemplateDescription);
		$scope.subdocument.sd_fd_mid = $scope.casefile.fd_id;
		if ($scope.sd_submitted_date != null) {
			$scope.sd_submitted_date = convertDate($scope.sd_submitted_date);
		}
		$scope.subdocument.sd_submitted_date = $scope.sd_submitted_date;


		if ($scope.subdocument.if_id != 43 && $scope.subdocument.if_id != 10 && $scope.subdocument.if_id != 19) {
			if ($scope.subdocument.if_id == null || $scope.subdocument.at_id == null) {

				alert("Please select required fields");
				$scope.loading = true;
				return false;

			}
		}

		var file = $scope.picFile;
		if ($scope.subdocument.ord_remark != '' && file == "") {
			addReportData();
			$scope.loading = true;
		}
		if (file != "") {
			/*file.upload = Upload.upload({
			  url: urlBase + 'casefile/uploadJudgement',
			  headers: {
				  'optional-header': 'header-value'
				},
			   file:file,
			   fields:$scope.subdocument,
			  
			});

			file.upload.then(function (response) {
				if(response.data.response=="TRUE"){
					$scope.errorlist =null;
					alert("Successfully uploaded document");
					$("#uploadDocument").modal("hide");
					//window.location.reload();
					$scope.loading = true;
					$scope.picFile='';
					$scope.if_id='';
					$scope.ord_remark='';
					
				}else{
					$scope.errorlist = response.data.dataMapList;
					//$scope.loading = true;
				}
			  }, function (response) {
			    
			  }, function (evt) {
				// Math.min is to fix IE which reports 200% sometimes
				//file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			  });

			  file.upload.xhr(function (xhr) {
				// xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
			  });*/

			$http({
				method: 'POST',
				url: urlBase + 'casefile/uploadJudgement',
				headers: { 'Content-Type': undefined },
				transformRequest: function(data) {
					var formData = new FormData();

					formData.append('subDocument', new Blob([angular.toJson($scope.subdocument)], {
						type: "application/json"
					}));
					formData.append("file", data.file);
					return formData;
				},
				data: { subDocument: $scope.subdocument, file: file }

			}).
				success(function(data, status, headers, config) {
					if (data.response == "TRUE") {
						$scope.errorlist = null;
						alert("Successfully uploaded document");
						$("#uploadDocument").modal("hide");
						//window.location.reload();
						$scope.loading = true;
						$scope.picFile = '';
						$scope.if_id = '';
						$scope.ord_remark = '';

					} else {
						$scope.errorlist = response.data.dataMapList;
						//$scope.loading = true;
					}

				});
		}

	}


	/*	  $scope.save=function() 
		  {
			  $scope.loading = false;
			  $scope.newString = $("#txtEditor").Editor("getText");
			$scope.subdocument.ord_remark=$scope.newString.replace("&nbsp;"," ");
			
			  $scope.subdocument.ord_remark=$scope.newString.replace(/&nbsp;/g, " ");
			
			  $scope.subdocument.ord_remark=$("#txtEditor").Editor("getText");
			  console.log($scope.TemplateDescription);
			 $scope.subdocument.sd_fd_mid=$scope.casefile.fd_id;
			  if($scope.sd_submitted_date!=null){
				  $scope.sd_submitted_date=convertDate($scope.sd_submitted_date);
				}
			  $scope.subdocument.sd_submitted_date=$scope.sd_submitted_date;
			  
			  
			 if($scope.subdocument.if_id!=43 && $scope.subdocument.if_id!=10 && $scope.subdocument.if_id!=19 ){ 
			 if($scope.subdocument.if_id==null ||$scope.subdocument.at_id== null){
				
				 alert("Please select required fields");
				 $scope.loading = true;
				 return false;
				
			 }
			 }
			 
				var file=$scope.picFile;
				  if($scope.subdocument.ord_remark!='' && file==""){
					  addReportData();
					  $scope.loading = true;
				  }
				  if(file!="")
				  {				  
					file.upload = Upload.upload({
					  url: urlBase + 'casefile/uploadJudgement',
					  headers: {
						  'optional-header': 'header-value'
						},
					   file:file,
					   fields:$scope.subdocument,
					  
					});
	
					file.upload.then(function (response) {
						if(response.data.response=="TRUE"){
							$scope.errorlist =null;
							alert("Successfully uploaded document");
							$("#uploadDocument").modal("hide");
							//window.location.reload();
							$scope.loading = true;
							$scope.picFile='';
							$scope.if_id='';
							$scope.ord_remark='';
							
						}else{
							$scope.errorlist = response.data.dataMapList;
							//$scope.loading = true;
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
				 
				}*/


	/*	  $scope.save=function() 
		  {
			  
			  
			  var test = {
						description:"Test",
						status: "REJECTED"
					};
	
				var fd = new FormData();
				fd.append('data', angular.toJson(test));
				fd.append("file", $scope.file);
				$http({
					method: 'POST',
					url: 'EmployeeService/employee/data/fileupload',
					headers: {'Content-Type': undefined},
					data: fd,
					transformRequest: angular.identity
				})
				.success(function(data, status) {
								alert("success");
				});
				
				
				
				
				
			  
			  $scope.loading = false;
			  $scope.newString = $("#txtEditor").Editor("getText");
			$scope.subdocument.ord_remark=$scope.newString.replace("&nbsp;"," ");
			
			  $scope.subdocument.ord_remark=$scope.newString.replace(/&nbsp;/g, " ");
			
			  $scope.subdocument.ord_remark=$("#txtEditor").Editor("getText");
			  console.log($scope.TemplateDescription);
			 $scope.subdocument.sd_fd_mid=$scope.casefile.fd_id;
			  if($scope.sd_submitted_date!=null){
				  $scope.sd_submitted_date=convertDate($scope.sd_submitted_date);
				}
			  $scope.subdocument.sd_submitted_date=$scope.sd_submitted_date;
			  
			  
			 if($scope.subdocument.if_id!=43 && $scope.subdocument.if_id!=10 && $scope.subdocument.if_id!=19 ){ 
			 if($scope.subdocument.if_id==null ||$scope.subdocument.at_id== null){
				
				 alert("Please select required fields");
				 $scope.loading = true;
				 return false;
				
			 }
			 }
			 
			
			 
			 
			 
				var file=$scope.picFile;
				  if($scope.subdocument.ord_remark!='' && file==""){
					  addReportData();
					  $scope.loading = true;
				  }
				  if(file!="")
				  {	
					  var fd = new FormData(); 
					  
					  fd.append('data', angular.toJson($scope.subdocument));
						fd.append("file", file);
						
						$http({
							method: 'POST',
							url: urlBase +'EmployeeService/employee/data/fileupload',
							headers: {'Content-Type': undefined},
							data: fd,
							transformRequest: angular.identity
						})
						.success(function(data, status) {
										alert("success");
						});
					  
					  
					file.upload = Upload.upload({
					  url: urlBase + 'casefile/uploadJudgement',
					  headers: {
						  'optional-header': 'header-value'
						},
					   file:file,
					   fields:$scope.subdocument,
					});
	
					file.upload.then(function (response) {
						if(response.data.response=="TRUE"){
							$scope.errorlist =null;
							alert("Successfully uploaded document");
							$("#uploadDocument").modal("hide");
							//window.location.reload();
							$scope.loading = true;
							$scope.picFile='';
							$scope.if_id='';
							$scope.ord_remark='';
							
						}else{
							$scope.errorlist = response.data.dataMapList;
							//$scope.loading = true;
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
				 
				}*/
	$scope.uploadNotice = function() {





		var file = $scope.picFile;
		$scope.subdocument = {};
		if (file != "") {
			file.upload = Upload.upload({
				url: urlBase + 'notice/uploadNotice',
				headers: {
					'optional-header': 'header-value'
				},
				file: file,
				fields: $scope.subdocument,
			});

			file.upload.then(function(response) {
				if (response.data.response == "TRUE") {
					$scope.errorlist = null;
					alert("Successfully uploaded document");
					$("#uploadDocument").modal("hide");
					//window.location.reload();
					$scope.loading = true;
					$scope.picFile = '';
					$scope.if_id = '';
					$scope.ord_remark = '';

				} else {
					$scope.errorlist = response.data.dataMapList;
					//$scope.loading = true;
				}
			}, function(response) {
				console.log("responseeeeeeeeeeeeeeeeeeeee", response);

			}, function(evt) {
				// Math.min is to fix IE which reports 200% sometimes
				//file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			});

			file.upload.xhr(function(xhr) {
				// xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
			});
		}

	}
	function addReportData() {
		$scope.subdocument.sd_submitted_date = $scope.sd_submitted_date;

		var sb = {};

		sb.at_id = $scope.subdocument.at_id
		sb.sd_fd_mid = $scope.subdocument.sd_fd_mid
		sb.ord_remark = $scope.subdocument.ord_remark
		sb.sd_submitted_date = $scope.subdocument.sd_submitted_date
		sb.ord_consignment_no = $scope.subdocument.ord_consignment_no



		$http.post(urlBase + 'casefile/addreportdata', angular.toJson(sb)


		)
			.success(function(data) {
				if (data.response == "TRUE") {
					$scope.loading = true;
					$scope.picFile = '';
					$scope.if_id = '';
					$scope.ord_remark = '';
					alert("Successfully added order report data");
				}

				else
					alert("Error occurred while adding order report data");

				$("#uploadDocument").modal("hide");
			}).
			error(function(data, status, headers, config) {
				console.log("Error in getting tree data");
			});
	}
	$scope.downloadFiles = function(id) {
		window.open(urlBase + "casefile/downloadlist/" + id, "_self");
	}
	$scope.viewCaseFile = function(id) {
		window.open(urlBase + "casefile/view/" + id, "_blank");
	}

	$scope.sendSmsFile = function(mobile) {
		window.open(urlBase + "casefile/sendAdvSms/" + mobile, "_blank");
	}


	$scope.notice = function(id) {
		window.open(urlBase + "casefile/civilNotice/" + id, "_blank");
	}
	$scope.viewDetail = function(id) {
		window.open(urlBase + "casefile/viewdetail/" + id, "_blank");
	}
	$scope.getSubDocuments = function(doc_id) {
		$scope.doc_id = doc_id;
		$http.get(urlBase + 'casefile/getsubdocuments/' + $scope.doc_id).success(function(data) {
			$scope.subDocuments = data.modelList;
		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting sub documents");
			});
	}

	$scope.deleteSubDocument = function(id) {
		var result = confirm("Are you really want to Delete file");
		if (result) {
			$http({
				method: 'DELETE',
				url: urlBase + 'casefile/deletesubdocument/' + id
			}).success(function(response) {
				alert("Successfully deleted record");
				$scope.getSubDocuments($scope.doc_id);
			});
		}
	}
	$scope.loading = true;
	$scope.updateCasetype = function() {

		$scope.loading = false;

		if ($scope.new_case_type == $scope.casefile.fd_case_type) {
			$scope.loading = true;
			alert("Existing and new case type is same");
			return false;
		}

		if ($scope.new_case_type && $scope.new_case_no && $scope.new_case_year) {
			$http.post(urlBase + 'casefile/updatecasetype?fd_id=' + $scope.casefile.fd_id
				+ "&new_case_type=" + $scope.new_case_type
				+ "&new_case_no=" + $scope.new_case_no
				+ "&new_case_year=" + $scope.new_case_year

			)
				.success(function(data) {
					if (data.response == "TRUE") {
						$scope.loading = true;
						alert("Successfully updated case type information");
					}
					else {
						$scope.loading = true;
						alert("Error occurred while updating case type information");
					}

					$scope.new_case_type = '';
					$scope.new_case_no = '';
					$scope.new_case_year = '';

					$("#updateCaseType").modal("hide");
				}).
				error(function(data, status, headers, config) {
					$scope.loading = true;
					console.log("Error in getting tree data");
				});

		}
		else {
			alert("Please Enter All The Fields");
		}
	}


	$scope.caseAssignTo = function() {
		$scope.loading = false;
		var userid = $scope.um_id;
		console.log("um_id---" + $scope.um_id);
		if (typeof userid === "undefined") {
			$scope.loading = true;
			alert("Please Select User");
			return false;
		}
		$http.post(urlBase + 'casefile/caseAssignTo?fd_id=' + $scope.casefile.fd_id
			+ "&fd_assign_to=" + $scope.um_id
		).success(function(data) {
			if (data.response == "TRUE") {
				$scope.loading = true;
				alert("Successfully Assigned");
			}
			else {
				$scope.loading = true;
				alert("Error occurred while case assigning");
			}

			$scope.new_case_type = '';
			$("#caseAssignTo").modal("hide");
		}).error(function(data, status, headers, config) {
			$scope.loading = true;
			console.log("Error in getting tree data");
		});
	}
	$scope.validate = true;
	$scope.offrepohide = false;
	$scope.hideOther = function() {
		if ($scope.offRep.off_rep == 1) {
			$scope.offrepohide = true;
			/*$scope.subdocument.ord_consignment_no = "";
			$scope.subdocument.ord_consignment_no = false;*/

		}
		else {
			$scope.offrepohide = false;
			/*$scope.subdocument.ord_consignment_no = true;*/
		}

	}


	function getMasterdata() {
		$http.get(urlBase + 'user/getallusers').success(function(data) {
			$scope.masterdata = data;
			$scope.displayedCollection = [].concat($scope.masterdata);
		}).
			error(function(data, status, headers, config) {
				console.log("Error in getting User data");
			});
	}


	$scope.updateremark = function(data, index) {
		debugger;
		console.log("index dataaaaaaaaaaaaaaa", index);
		console.log("index dataaaaaaaaaaaaaaa", data);
		$scope.updateData = data;
		$scope.orderIndex = index;
		console.log("xxxxxxxxxxxxxxxxx", $scope.updateData);
		var x = document.getElementsByClassName("Editor-editor");

		console.log("object of editor", x);

		$("#updateremar").modal("show");
		x[1].innerHTML = data.ord_remark;
	};


	$scope.viewCaseedit = function(id) {
		debugger;
		var response = $http.get(urlBase + 'casefile/getByOfficeedit/' + id);
		response.success(function(data, status, headers, config) {
			console.log(data);

			$scope.dataArray = [];
			for (let i = 0; i < data.offcrpt.length; i++) {
				var officeReportdata1 = {};

				console.log("data in LLLLLLLLLLLLLLL", data.offcrpt.length);
				officeReportdata1.ord_id = data.offcrpt[i].ord_id;

				officeReportdata1.ct_name = data.offcrpt[i].caseFileDetail.caseType.ct_label;
				officeReportdata1.ord_created = data.offcrpt[i].ord_created;
				//
				officeReportdata1.fd_case_no = data.offcrpt[i].caseFileDetail.fd_case_no;
				if (data.offcrpt[i].subDocument != null) {
					//handoverdata1.sd_id =data.modelData[i].subDocument.sd_id;
					officeReportdata1.sd_document_name = data.offcrpt[i].subDocument.sd_document_name;
				}
				//
				officeReportdata1.ord_sd_mid = data.offcrpt[i].ord_sd_mid;
				officeReportdata1.ord_mod_by = data.offcrpt[i].ord_mod_by;
				officeReportdata1.ord_mod_date = data.offcrpt[i].ord_mod_date;
				officeReportdata1.ord_rec_status = data.offcrpt[i].ord_rec_status;
				officeReportdata1.ord_fd_mid = data.offcrpt[i].ord_fd_mid;
				officeReportdata1.ord_created = data.offcrpt[i].ord_created;
				officeReportdata1.ord_consignment_no = data.offcrpt[i].ord_consignment_no;
				officeReportdata1.ord_submitted_date = data.offcrpt[i].ord_submitted_date;
				officeReportdata1.um_fullname = data.offcrpt[i].user.um_fullname;


				officeReportdata1.ord_remark = $sce.trustAsHtml(data.offcrpt[i].ord_remark);
				console.log("data in LLLLpppppppppppppppLLLLLLLLLLL", officeReportdata1);
				$scope.dataArray.push(officeReportdata1);
				console.log("data in LLLLpppppppppppppppLLLLLLLLLLL11111111", $scope.dataArray);
			}

		});
		response.error(function(data, status, headers, config) {
		});

	};



	//

	//  function update(){
	$scope.update = function() {
		$scope.loading = false;
		$scope.newString = $("#txtEditor1").Editor("getText");
		console.log("updated data", $scope.updateData);

		$scope.subdocument = {
			'ord_id': $scope.updateData.ord_id,
			'ord_remark': $scope.newString.replace(/&nbsp;/g, " "),
			'ord_created': $scope.updateData.ord_created,
			'ord_fd_mid': $scope.updateData.ord_fd_mid,
			'ord_submitted_date': $scope.updateData.ord_submitted_date,
			'ord_mod_by': $scope.updateData.ord_mod_by,
			'ord_created_by': $scope.updateData.ord_created_by,
			'ord_sd_mid': $scope.updateData.ord_sd_mid,
			'ord_rec_status': $scope.updateData.ord_rec_status,
			'ord_mod_date': $scope.updateData.ord_mod_date,
			'ord_consignment_no': $scope.updateData.ord_consignment_no

		};

		$http.post(urlBase + 'casefile/addReportupdate', $scope.subdocument)
			.success(function(data) {

				console.log("data of repert", data);


				if (data.response == "TRUE") {
					console.log("yyyyyyyyyyyyyyyyyyyyyyyyy", $scope.dataArray[$scope.orderIndex]);
					//console.log("yyyyyyyyyyyyyyyyyyyyyyyyy",$scope.handoverdataArray[$scope.orderIndex].ord_remark);
					$scope.dataArray[$scope.orderIndex].ord_remark = $sce.trustAsHtml(data.modelData.ord_remark)
					alert("Successfully added order report data"),

						$("#updateremar").modal("hide");
				}
				else if (data.response == "Time_over") {
					alert("Today is on the case caust list");
				} else if (data.response == "Time") {
					alert("Time Over");
				} else
					alert("Error occurred while adding order report data");

				$("#updateremar").modal("hide");
			}).
			error(function(data, status, headers, config) {
				console.log("Error in getting tree data");
			});

	}


	$scope.Getfileview = function(data) {
		filename = data.sd_document_name;
		ord_sd_mid = data.ord_sd_mid;
		$http.get(urlBase + 'casefile/Getfileview', { params: { 'sd_document_name': filename, 'ord_sd_mid': ord_sd_mid } })
			.success(function(data) {


				//$scope.fileurl=urlBase+"uploads/"+filename+".pdf";
				//$scope.title=filename;
				//$('#casefile_Modal').modal('show');
				window.open(urlBase + "uploads/" + filename + ".pdf", '_blank');
				console.log($scope.fileurl);


			}).error(function(data, status, headers, config) {
				console.log("Error in getting Report ");
			});
		//}
	};


	$scope.deleteremark = function(data) {
		ord_id = data.ord_id;
		$http.get(urlBase + 'casefile/deleteremark', { params: { 'ord_id': ord_id } })
			.success(function(data) {


				if (data.response == "TRUE") {
					alert("Successfully deleted record");
					window.location.reload();
				} else {
					alert("Data Not Delete")
				}


			}).error(function(data, status, headers, config) {
				console.log("Error in getting Report ");
			});
		//}
	};


	//
	/*END*/

	$scope.genrateDecree = function(id) {

		window.open(urlBase + "casefile/genrateDecree/" + id, "_blank");

	}






}]);