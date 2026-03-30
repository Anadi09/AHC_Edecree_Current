var EDMSApp = angular.module("EDMSApp", ['smart-table']);


EDMSApp.controller("CaseFileCtrl",	function($scope, $sce, $http,$filter) {
	var urlBase="/dms/";
	$scope.subDocuments=[];
	$scope.metadata=[];
	$scope.doc_id= $('#doc_id').val();
	//$scope.judgmentdocId= $('#judgmentdocId').val();
	
	//alert(2);
	$scope.document_name= $('#document_name').val();
	//alert($scope.fd_file_bar_code);
	var url = urlBase+'uploads/' + $scope.document_name+".pdf";
	DEFAULT_URL = url;
	
   // alert(DEFAULT_URL);
	$scope.petitions=[];
	$scope.petitionsData=[];
	$scope.rejoinders=[];
	$scope.rejoindersData=[];
	$scope.applications=[];
	$scope.applicationsData=[];
	$scope.supp_affidavits=[];
	$scope.supp_affidavitsData=[];
	$scope.coun_affidavits=[];
	$scope.coun_affidavitsData=[];
	$scope.supp_coun_affidavits=[];
	$scope.supp_coun_affidavitsData=[];
	$scope.supp_rejoinders=[];
	$scope.supp_rejoindersData=[];
	$scope.order_sheets=[];
	$scope.order_sheetsData=[];
	$scope.office_reports=[];
	$scope.office_reportsData=[];
	$scope.others=[];
	$scope.othersData=[];
	$scope.nt={};
	getSubDocuments();
	getCaseFileDetails();
	

	
	function getSubDocuments(){
		$http.get(urlBase+'lkocasefile/getsubdocumentsLKO/'+$scope.doc_id).success(function (data) {
	    	$scope.subDocuments=data.modelList;
	    	console.log($scope.subDocuments);
	    	
	    	
	    	 for(var v = 0; v < $scope.subDocuments.length; v++){
		        	
		        	for(var i = 0; i <  $scope.subDocuments.length; i++){
		        		
		        		var date=$scope.subDocuments[v].sd_submitted_date;
		        		console.log(date);
		        		
		        		var date1=new Date(date);
		        		
		        		var date2 = $filter('date')(new Date(date1), 'dd/MM/yyyy');
		        		
		        	//	var filterdatetime = $filter('date')( date1 );
		        		
		        		
		        		
		    
		        		
		        		if (v != i) {
		        			if ($scope.subDocuments[v].sd_document_name == $scope.subDocuments[i].sd_document_name) {
		        				
		        				
		        				/*if($scope.subDocuments[v].documentType.at_id == 14 || $scope.subDocuments[v].documentType.at_id ==22 ||
		        						$scope.subDocuments[v].documentType.at_id ==7 ||
		        						$scope.subDocuments[v].documentType.at_id ==44 ||
		        						$scope.subDocuments[v].documentType.at_id ==45) {*/
		        					
		        					
		        					
		        					$scope.subDocuments[v].indexField.if_label=$scope.subDocuments[v].documentType.at_name+ ' / '
			        				+$scope.subDocuments[v].sd_document_no +' / '+$scope.subDocuments[v].sd_document_year+' / '+date2 +' // '
			        				+$scope.subDocuments[i].documentType.at_name+' / '
			        				+$scope.subDocuments[i].sd_document_no+' / '+$scope.subDocuments[i].sd_document_year;
		        					
		        					
		        				
		        					
		        					$scope.subDocuments[v].documentType.at_name=$scope.subDocuments[v].documentType.at_name+ ' / '
			        				+$scope.subDocuments[v].sd_document_no +' / '+$scope.subDocuments[v].sd_document_year+' / '+date2 +' // '
			        				+$scope.subDocuments[i].documentType.at_name+' / '
			        				+$scope.subDocuments[i].sd_document_no+' / '+$scope.subDocuments[i].sd_document_year;
			        				
			        				
			        				
		        				
		        				
		        				
		        				$scope.subDocuments.splice(i,1); 
		        				
		        				i=i-1;
		        				
		        				   
		        				
		        				
		        				 
				        		
		        				$scope.subDocuments[v].flag=true;
								
								$scope.apflag=true;
							}
		        			
		        			
		        			
						}
		        		
		        		
		        		
		        		
		        		
		        	}
		        	
		        	console.log($scope.subDocuments);
		        	//$scope.applications[0]=0;
		        	
		        }
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	angular.forEach($scope.subDocuments, function(value, key) {
	    		  
	    		  $scope.subdocument=value;
	    		  var type=$scope.subdocument.indexField.if_name;
	    		  switch(type){
	    		  case'petition':
	    			  $scope.petitions.push($scope.subdocument);
	    			  break;
	    		  case'rejoinder':
	    			  $scope.rejoinders.push($scope.subdocument);
	    			  break;
	    		  case'application':
	    			  $scope.applications.push($scope.subdocument);
	    			  break;
					case'olr':
	    			  $scope.applications.push($scope.subdocument);
	    			  break;
	    		  case'supp_affidavit':
	    			  $scope.supp_affidavits.push($scope.subdocument);
	    			  break;
	    		  case'coun_affidavit':
	    			  $scope.coun_affidavits.push($scope.subdocument);
	    			  break;
	    		  case'supp_coun_affidavit':
	    			  $scope.supp_coun_affidavits.push($scope.subdocument);
	    			  break;
	    		  case'supp_rejoinder':
	    			  $scope.supp_rejoinders.push($scope.subdocument);
	    			  break;
	    		  case'order_sheet':
	    			  $scope.order_sheets.push($scope.subdocument);
	    			  break;
					case'others':
	    			  $scope.others.push($scope.subdocument);
	    			  break;
	    		  }
	    		  
	    		});
	    	
	    	//generateRecallOrder();
	    	
	    	getOrderReports();
	    	$scope.petitionsData = [].concat($scope.petitions);
	        $scope.rejoindersData = [].concat($scope.rejoinders);
	        $scope.applicationsData = [].concat($scope.applications);
	        $scope.supp_affidavitsData = [].concat($scope.supp_affidavits);
	        $scope.coun_affidavitsData = [].concat($scope.coun_affidavits);
	        $scope.supp_coun_affidavitsData = [].concat($scope.supp_coun_affidavits);
	        $scope.supp_rejoindersData = [].concat($scope.supp_rejoinders);
			$scope.othersData = [].concat($scope.others);
			
			console.log($scope.petitionsData);
			console.log($scope.applicationsData);
	        
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function getCaseFileDetails(){
		$http.get(urlBase+'lkocasefile/getcasefiledetailsLKO/'+$scope.doc_id).success(function (data) {
	    	$scope.casefile=data.modelData;
	    	  
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function getOrderReports(){
		$http.get(urlBase+'lkocasefile/getorderreportsLKO/'+$scope.doc_id).success(function (data) {
	    	$scope.orderReports=data.modelList;
	    	generateReportData();
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	}
	function generateReportData(){
		$scope.orderData=[];
		angular.forEach($scope.order_sheets, function(value, key) {
			/*$scope.ordermodel={'sd_id':value.sd_id,'document_type':value.documentType.at_description,'sd_created_date':value.sd_cr_date,'sd_submitted_date':value.sd_submitted_date,'sd_party':value.sd_party,'sd_description':value.sd_description,'ord_remark':'','checked':false};*/
			$scope.ordermodel={'sd_id':value.sd_id,'document_type':value.documentType.at_description,'sd_created_date':value.sd_cr_date,'sd_submitted_date':value.sd_submitted_date,'sd_party':value.sd_party,'sd_nonmaintainable':value.sd_nonmaintainable,'sd_description':value.sd_description,'ord_remark':'','checked':false};
			$scope.orderData.push($scope.ordermodel);
		});
		angular.forEach($scope.orderReports, function(value, key) {
			var sd_id=null;
			
			if(value[0].subDocument!=null)
				sd_id=value[0].subDocument.sd_id;
				$scope.ordermodel={'sd_id':sd_id,'document_type':'Off. Rep.','sd_created_date':value[0].ord_created,'sd_submitted_date':value[0].ord_created,'sd_party':'','sd_description':'','ord_remark':$sce.trustAsHtml(value[0].ord_remark),'ord_consignment_no':value[0].ord_consignment_no==null?'': value[0].ord_consignment_no,'consignmentno':value[0].ord_consignment_no==null?'':'Consignment_No:-', 'um_fullname':'Submitted By:-  '+value[1].um_fullname};
				$scope.orderData.push($scope.ordermodel);
				console.log($scope.orderData)
				
				
				
			});
		//alert(JSON.stringify($scope.orderData));
		$scope.orderDataList = [].concat($scope.orderData);
		
		console.log("orderData-----");
		console.log($scope.orderData);
	}
	
	function generateRecallOrder(){
		$scope.recallOrderData=[];
		angular.forEach($scope.applications, function(value, key) {
			console.log($scope.applications[key]);
			if($scope.applications[key].documentType.at_name=="RECALL OF ORDER"){
			$scope.recallOrdermodel={'sd_id':value.sd_id,'sd_document_no':value.sd_document_no,'sd_document_year':value.sd_document_year,'document_type':value.documentType.at_description,'sd_created_date':value.sd_cr_date,'sd_submitted_date':value.sd_submitted_date,'sd_party':value.sd_party,'sd_description':value.sd_description};
			$scope.recallOrderData.push($scope.recallOrdermodel);
			}
		});
		//alert(JSON.stringify($scope.orderData));
		//$scope.orderDataList = [].concat($scope.orderData);
		
		console.log("orderData-----"+$scope.recallOrderData);
		console.log($scope.recallOrderData);
	}
	
	$scope.showSubDocument=function(sd_id,$index,doc_type){
		console.log(sd_id);
		console.log($scope.subDocuments);
		
		if(doc_type.valueOf()[$index].checked==false){
		    $scope.personColour = { 'color': 'blue' };
		    doc_type.valueOf()[$index].checked=true;
		}
		
/*		if(doc_type=='St. Rep.' || doc_type=='ORD' || doc_type=='Off. Rep.')
		if($scope.orderData[$index].isColor==false){
		    $scope.personColour = { 'color': 'blue' };
		    $scope.orderData[$index].isColor=true;
		}
		
		if(doc_type=='petition'){
			if($scope.petitionsData[$index].checked==false){
			    $scope.personColour = { 'color': 'blue' };
			    $scope.petitionsData[$index].checked=true;
			}
			}
		
		if(doc_type=='coun_affidavit'){
			if($scope.coun_affidavits[$index].checked==false){
			    $scope.personColour = { 'color': 'blue' };
			    $scope.petitionsData[$index].checked=true;
			}
			}*/
		
		$http.get(urlBase+'lkocasefile/subdocumentLKO/'+sd_id).success(function (data) {
	    	$scope.sample=data.modelData;
	    	DEFAULT_URL=urlBase+'uploads/' +data.modelData.document_name+".pdf";;
			console.log($scope.document_name);
			PDFViewerApplication.open(DEFAULT_URL);
			console.log(DEFAULT_URL);
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });

	};
	$scope.viewAllOrders=function(){
		//window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
		window.open(urlBase+'lkocasefile/viewordersLKO/'+$scope.doc_id,'_self');
	};
	
	$scope.downloadfiles=function(){
		//window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
		window.open(urlBase+'lkocasefile/download/'+$scope.doc_id,'_self');
	};
	
	$scope.showHighCourtCase=function(io_id){
		//window.open(urlBase+'casefile/viewdocument/'+sd_id,'_blank');
		window.open(urlBase+'lkocasefile/impugnedorderLKO/'+io_id,'_self');
	};
	
	$scope.trackReport=function(ord_id){
		window.open('https://www.indiapost.gov.in/MBE/Pages/Content/Speed-Post.aspx', '_blank');
	};
	
	$scope.caseTab = function(id,$index,doc_type) 
    {
		
      window.open(urlBase+'lkocasefile/subdocumentTabLKO/'+id, '_blank');
      
      if(doc_type.valueOf()[$index].checked==false){
		    $scope.personColour = { 'color': 'blue' };
		    doc_type.valueOf()[$index].checked=true;
		}
      
     };
	
     
     $scope.saveNotes = function(nt) {
    	 console.log("in");
    	 console.log(nt);
    	 console.log($scope.doc_id);
    	 $scope.nt = nt;
    	 
    	 if(nt.nt_notes!=null)
    		 {
    		 $scope.notesdata={
    				 'nt_notes':nt.nt_notes,
    				 'nt_fd_mid':$scope.doc_id
    		 };
    		 debugger;
				console.log($scope.notesdata);
    		 }
    	 
    	 var response = $http.post(urlBase+ 'lkocasefile/NotesSaveLKO',$scope.notesdata);
    	 	response.success(function(data) {
    		 console.log("***Notes Is saved***");
				if (data.response == "FALSE") {
					$scope.errorlist = data.dataMapList;
				} 
				else {
					$scope.errorlist = [];
					/*alert("Notes Save Successfully!");*/
					getMasterdata();

				}
			})
};


$scope.openNav=function (){
	
	document.getElementById("sidebar").style.zIndex = "1";
	document.getElementById("header").style.zIndex = "2";
	document.getElementById("mainContainer").style.zIndex = "1";
	
		console.log($scope.doc_id);
	
	
	    $http.get(urlBase+'lkocasefile/getnotesLKO/'+$scope.doc_id).success(function (data) {
	    	$scope.nt=data.modelData;	
	    	console.log($scope.nt);
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting sub documents");
	      });
	
	
	}

/*$scope.changeColor = function($index) {
	if($scope.orderData[$index].isColor==false){
    $scope.personColour = { 'color': 'blue' };
    $scope.orderData[$index].isColor=true;
}
                           // or 'background-color' whatever you what
}

$scope.recoverColor = function() {
    $scope.personColour = {};
}*/
    	

/* ------------------ Sushant----------------------------*/
     




	
});