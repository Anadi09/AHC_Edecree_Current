
var EDMSApp = angular.module("EDMSApp", ['ngFileUpload','ngMask','ui.bootstrap']);

EDMSApp.controller('NoticeController',['$scope','$http','$sce','Upload',function ($scope, $http,$sce,Upload) {
	var urlBase="/eDecree/";
	$scope.doc_id= $('#doc_id').val();
	$scope.picFile='';
	$scope.caseTypes=[];
	$scope.search={};
	$scope.subdocument={};
	$scope.offRep={};
	$scope.dmsCaseData={};
	$scope.isStruckThrough = false;

	$scope.decreeStage=[];
	
	
	var initConfig = {
	          "preSignCallback": function() {
	            
	            return true;
	          },
	          "postSignCallback": function(alias, sign) {
	            $('#signedPdfData').val(sign);
	             
	            $('#downloadDiv').addClass('btn btn-info');
	            
	            var pdfData = sign;
	            var dlnk = document.getElementById('downloadDiv');
	            dlnk.href = 'data:application/pdf;base64,' + pdfData;
	            $("#downloadDiv").text("Download Signed PDF File");
	            
	            $scope.decreeForm.pdfStrem=sign;
	            
	            $http.post(urlBase+'notice/genratePdfStrem',$scope.decreeForm).success(function (data) {
			    	if(data.response=="TRUE"){
			    		console.log("data daaaaaa",data);
			    		getDecreeForm();
			    	}
			    	else
			    		{
			    		console.log("Some problem")
			    		}
			    		
			      }).
			      error(function(data, status, headers, config) {
			      	console.log("Error in getting tree data");
			      });
	            
	            
	          },
	          signType: 'pdf',
	          mode: 'nostampingnoencryptionv2',
	          certificateData: $('#cert').val()
	         
	        };
	        dscSigner.configure(initConfig);
	       
	        $('#signPdf').click(function() {
	          var data = $("#pdfData").val();
	          if (data != null || data != '') {
	            dscSigner.sign(data);
	          }
	        });
	        
	      
	         /*$scope.toggleStrikeThrough = function() {
	        	 
	            // Select all <p> elements on the page
	            var paragraphs = document.querySelectorAll('p');

	            // Iterate over each paragraph and toggle the 'strikethrough' class
	            paragraphs.forEach(function(paragraph) {
	              paragraph.classList.toggle('strikethrough');
	            });
	        }*/
	        
	       
	        $scope.toggleStrikeThrough = function() {
	        	 var selection = window.getSelection();
	             
	             // Check if text is selected
	             if (!selection.rangeCount) return;
	             
	             var range = selection.getRangeAt(0);
	             var selectedText = range.toString();
	             
	             if (selectedText) {
	                 // Toggle strike-through by wrapping the selected text with a <span> tag
	                 var span = document.createElement('span');
	                 span.style.textDecoration = 'line-through';
	                 span.textContent = selectedText;

	                 // Replace the selected content with the strike-through span
	                 range.deleteContents();
	                 range.insertNode(span);
	             }
	           
	            }
	       /* EDMSApp.filter('strikethrough', function() {
	            return function(input) {
	                // You could modify the input here, or apply the strike-through style directly
	                return input; // This simple example just passes the input unchanged
	            };
	        });*/
	            
	        
	        
	        
	        

	        function readURL(input) {
	          if (input.files && input.files[0]) {
	        	  console.log("in base64");
	            var reader = new FileReader();
	            reader.onload = function(e) {
	              var data = e.target.result;
	              var base64 = data.replace(/^[^,]*,/, '');
	              $("#pdfData").val(base64);
	            }
	            reader.readAsDataURL(input.files[0]);
	          }
	        }
	        $("#pdfFile").change(function() {
	          readURL(this);
	        });
	
	
	        $scope.digitalSign=function(fd_id){
	        	
	        	  $http.get(urlBase+'notice/digitalSignDecree/'+fd_id).success(function (data) {
			    		$scope.byte1=data.data;	
			    		
			    		console.log($scope.byte1);
			    		
			    		var base64 = $scope.byte1.replace(/^[^,]*,/, '');
			    		
			    		 $("#pdfData").val(base64);
			    		
			    		 dscSigner.sign(base64);
			    	
			    	
			      }).
			      error(function(data, status, headers, config) {
			      	console.log("Error in getting casetypes");
			      });
	        	  
	        }
	        
	        $scope.digitalSign1=function(fd_id){
	        	
	        	  $http.get(urlBase+'notice/getCaseDetails/'+$scope.doc_id).success(function (data) {
			    		$scope.caseDetailsCIS=data.modelData;	
			    		$scope.jgName=data.dataList;
			    		$scope.filingDate=new Date($scope.caseDetailsCIS[6]);
			    		$scope.decisionDate=new Date($scope.caseDetailsCIS[11]);
			    		
			    		$scope.ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format($scope.filingDate);
			    		$scope.mo = new Intl.DateTimeFormat('en', { month: 'long' }).format($scope.filingDate);
			    		$scope.da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format($scope.filingDate);
			    		
			    		
			    		$scope.dye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format($scope.decisionDate);
			    		$scope.dmo = new Intl.DateTimeFormat('en', { month: 'long' }).format($scope.decisionDate);
			    		$scope.dda = new Intl.DateTimeFormat('en', { day: '2-digit' }).format($scope.decisionDate);
			    		
			    		
			    		
			    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS[0]);
			    		getPet();
			    		getAdv();
			    		  //getRes();
			    	
			      }).
			      error(function(data, status, headers, config) {
			      	console.log("Error in getting casetypes");
			      });
	  		  
	  		  $http.get(urlBase+'notice/digitalSignDecree/'+fd_id).success(function (data) {
	  			/*document.getElementById('pdfFile').value=urlBase+'uploads/Decree.pdf';*/
	  			  
	  			/*  window.open(,urlBase+'uploads/Decree.pdf');
	  			const file = new File(["Decree"],urlBase+'uploads/Decree.pdf', {
	  			  type: "application/pdf",
	  			});
	  			
	  			 readURL(file);
	  		  }).*/
	  			  
	  			 getFileObject('dms/assets/img/1.jpg', function (fileObject) {
	  				 var file1=fileObject;
	  				window.open(new File([fileObject], "decree"),'_blank');
	  		         console.log(fileObject);
	  		    })
	  			 
	  		  }).
	  	      error(function(data, status, headers, config) {
	  		      	console.log("Error in getting sub documents");
	  		      });
	  		  
	  		 /* window.open(urlBase+'notice/downloadDecree/'+$scope.doc_id,'_self');*/
	  	  }
	        
	        
	        var getFileBlob = function (url, cb) {
	            var xhr = new XMLHttpRequest();
	            xhr.open("GET", url);
	            xhr.responseType = "blob";
	            xhr.addEventListener('load', function() {
	                cb(xhr.response);
	            });
	            xhr.send();
	    };

	    var blobToFile = function (blob, name) {
	            blob.lastModifiedDate = new Date();
	            blob.name = name;
	            return blob;
	    };

	    var getFileObject = function(filePathOrUrl, cb) {
	           getFileBlob(filePathOrUrl, function (blob) {
	              cb(blobToFile(blob, '1.jpg'));
	           });
	    };

	   
	
	
	$scope.recipient="ARVIND AGARWAL";


	$scope.decreeForm={
		'df_fd_mid': $scope.doc_id
	};

	 getCaseDetails();
	 
	 $scope.genrateDecree = function(id) {		
			window.open(urlBase + "casefile/genrateDecree/" + id, "_blank");
		}
	
	
	
	  function getCaseDetails(){
		  $http.get(urlBase+'notice/getCaseDetails/'+$scope.doc_id).success(function (data) {
		    		$scope.caseDetailsCIS=data.data;	
		    		$scope.jgName=$scope.caseDetailsCIS.disposedJudgeName.split(",");
		    		
		    		var dateParts = $scope.caseDetailsCIS.createdDT.split("-");
		    		
		    		var dateParts1 = $scope.caseDetailsCIS.disposedDate.split("-");

		    		// month is 0-based, that's why we need dataParts[1] - 1
		    		
		    		$scope.caseDet=$scope.caseDetailsCIS.displayCaseno.split("/");
		    		 
		    		
		    		$scope.filingDate=new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
		    		$scope.decisionDate=new Date(+dateParts1[2], dateParts1[1] - 1, +dateParts1[0]);
		    		
		    		$scope.ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format($scope.filingDate);
		    		$scope.mo = new Intl.DateTimeFormat('en', { month: 'long' }).format($scope.filingDate);
		    		$scope.da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format($scope.filingDate);
		    		
		    		if($scope.caseDetailsCIS.disposedDate !=""){
		    		$scope.dye = new Intl.DateTimeFormat('en', { year: '2-digit' }).format($scope.decisionDate);
		    		$scope.dmo = new Intl.DateTimeFormat('en', { month: 'long' }).format($scope.decisionDate);
		    		$scope.dda = new Intl.DateTimeFormat('en', { day: '2-digit' }).format($scope.decisionDate);
		    		}
		    		
		    		
		    		
		    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS[0]);
		    		getPet();
		    		getAdv();
		    		  //getRes();
		    		  getlowerDetails();
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	  
	  $scope.deleteFile=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'notice/deleteFile/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	  
	  
	 /* $scope.updateForm=function(data){
		  var result=confirm("Are you really want to update this record");
		  
		  var response = $http.post(urlBase + 'notice/updateDecreeForm',data);
		  	response.success(function(data, status, headers, config){
		  		
				$scope.decree = data.modelData;
			
				 				   
						
				});	
		  	response.error(function(data, status, headers, config) {
				bootbox.alert("Error");
			});
			  
		  }*/
		  
	 /* $scope.updateForm = function(data) { 
		    var result = confirm("Are you really want to update this record?");
		    
		    if (result) {
		        // Ensure data is correctly structured and populated
		        var response = $http.post(urlBase + 'notice/updateDecreeForm', data, {
		            headers: {
		                'Content-Type': 'application/json'
		            }
		        });

		        response.success(function(data, status, headers, config) {
		            if (data.response === "TRUE") {
		                // Handle successful update
		                $scope.decree = data.modelData;
		                bootbox.alert("Update successful!");
		            } else {
		                bootbox.alert("Update failed!");
		            }
		        });

		        response.error(function(data, status, headers, config) {
		            console.error("Error occurred: ", status, data);
		            bootbox.alert("Error: " + status);
		        });
		    }
		};*/
		  
		  
	  
	  
	  $scope.preview = function(df_fd_mid){
      	
      	var response = $http.get(urlBase
					+ 'notice/previewFile', {
				params : {
					'df_fd_mid' : df_fd_mid
				}
			});
			response.success(function(data, status, headers, config) {
	        	
        		console.log(data);
				if (data.response == "TRUE") {
					window
							.open(urlBase + "/uploads/" + data.data,
									'_blank');
				} else if (data.response == "FALSE") {
					alert(data.data);
				}
        	});
        	response.error(function(data, status, headers, config) {
				bootbox.alert("Error");
			});
        };
	  
	   $scope.getDecreeExamList = function(){
		  $http.get(urlBase+'notice/getDecreeExamList').success(function (data) {
	    		$scope.decreeExaminer=data.modelData;	
	    		
	    	
	      }).
	      error(function(data, status, headers, config) {
	      	console.log("Error in getting casetypes");
	      });
	  }
	   
	   
	   $scope.uploadFile=function() 
		{
		
			console.log("In Upload judgement controller");
			
		console.log("fileeeeeeeeeeeeeeeeee",$scope.picFile)
			var str=$scope.picFile.name;
			 var extn = str.split(".").pop();
			 extn=extn.toLowerCase();
		 	 if(extn!="pdf"){
				 alert("Only pdf format are allowed");
				 return false;
			 }
			$scope.buttonDisabled1=true;
			  var file=$scope.picFile;
			  
			    file.upload = Upload.upload({
			      url: urlBase + 'notice/upload_compo_files',
			      params:{rcd_id: $scope.decreeForm.df_fd_mid},
			      headers: {
			    	  'optional-header': 'header-value'
			        },
			       fields:$scope.document,
	    		   file:file,
			    });
	if(file.upload){
			    file.upload.then(function (response) {
			    	$scope.buttonDisabled1=false;
			        if(response.data.response=="TRUE"){
			        //	$scope.errorlist =null;
			        	$scope.decreeForm=response.data.modelData;
			        
			        	console.log("responseeeeeeee of cr",response);
			        	//bootbox.alert("Uploading Done");
			        //	$("#documentCreate").modal("hide");
			        	$scope.document={};
			        	//window.location.reload();
			        	
			        			alert("Files Uploaded successfully...");
			        			
			        			$scope.picFile='';	
			        			
			        			
			        	
			        }else{
			        	alert("Some Problem");
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
		}else{$scope.buttonDisabled=false;}

			      
			}
	   
	   $scope.getDecreeDR = function(){
			  $http.get(urlBase+'notice/getDecreeDR').success(function (data) {
		    		$scope.decreeExaminer=data.modelData;	
		    		
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
		  }
	  
	  function getPet(){
		  $http.get(urlBase+'notice/getPet/'+$scope.caseDetailsCIS.caseId).success(function (data) {
		    		$scope.petitioner=data.data;	
		    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS.CaseId);
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	  function getAdv(){
		  $http.get(urlBase+'notice/getAdv/'+$scope.caseDetailsCIS.caseId).success(function (data) {
		    		$scope.advocates=data.data;	
		    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS.CaseId);
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	  function getlowerDetails(){
		  $http.get(urlBase+'notice/getLowerDetails/'+$scope.caseDetailsCIS.caseId).success(function (data) {
		    		$scope.lowerDetails=data.data;	
		    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS.caseId);
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	  
	  $scope.caseDetailsCIS=[];
	  function getRes(){
		  $http.get(urlBase+'notice/getRes/'+$scope.caseDetailsCIS[0]).success(function (data) {
		    		$scope.respondent=data.modelData;	
		    		console.log("dataaaaaaaaaaaaaaaaa",$scope.caseDetailsCIS);
		    		
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	 
	  
	  $scope.testMethod=function(){
		  var HTML_Width = $("#target").width();
			var HTML_Height = $("#target").height();
			var top_left_margin = 15;
			var PDF_Width = HTML_Width+(top_left_margin*2);
			var PDF_Height = (PDF_Width*1.5)+(top_left_margin*2);
			var canvas_image_width = HTML_Width;
			var canvas_image_height = HTML_Height;
			
			var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;
			
			 html2canvas($("#target")[0], {quality: 4,scale:5, onrendered :function (canvas) {
				 canvas.getContext('2d');
					
					console.log(canvas.height+"  "+canvas.width);
					
					
					var imgData = canvas.toDataURL("image/jpeg", 1.0);
					var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);
				    pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,canvas_image_height);
					
					
					for (var i = 1; i <= totalPDFPages; i++) { 
						pdf.addPage(PDF_Width, PDF_Height);
						pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
					}
					
				    pdf.save("HTML-Document.pdf");
		      }
			 });

	  }
	  
	  $scope.test2=function(){
		  var pdf = new jsPDF('p', 'pt', 'letter'); //letter = letter size pdf

      // source can be HTML-formatted string, or a reference
      // to an actual DOM element from which the text will be scraped.
      source = $('#target')[0];

      // we support special element handlers. Register them with jQuery-style 
      // ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
      // There is no support for any other type of selectors 
      // (class, of compound) at this time.
      specialElementHandlers = {
          // element with id of "bypass" - jQuery style selector
          '#bypassme': function (element, renderer) {
              // true = "handled elsewhere, bypass text extraction"
              return true
          }
      };
      margins = {
          top: 80,
          bottom: 60,
          left: 40,
          width: 522
      };
      // all coords and widths are in jsPDF instance's declared units
      // 'inches' in this case
      pdf.fromHTML(
          source, // HTML string or DOM elem ref.
          margins.left, // x coord
          margins.top,   // y coord
          {
              'width': margins.width // max width of content on PDF

          },

          function (dispose) {
              // dispose: object with X, Y of the last line add to the PDF 
              //          this allow the insertion of new lines after html
              pdf.save('Filename.pdf');
          }, margins
      );
      }
	  
	  
	  $scope.selectedList={};
	  
	  $scope.test5=function(){
		 
		  angular.forEach($scope.selectedList, function (selected, day) {
			  var pdf = new jsPDF('p', 'pt', 'a4');
		        if (selected) {
		           console.log(day);
		           $scope.recipient=day;
		           return html2canvas($('#target1'), {
			            background: "#ffffff",
			            onrendered: function(canvas) {
			            	
			            	html2canvas($('#target2'), {
			    	            background: "#ffffff",
			    	            onrendered: function(canvas2) {
			            	
			            	
			                var myImage = canvas.toDataURL("image/jpeg,1.0");
			                
			               
			                // Adjust width and height
			                var imgWidth =  (canvas2.width * 60) / 240;
			                var imgHeight = (canvas2.height * 70) / 240;
			              /*  var imgWidth =doc.internal.pageSize.getWidth();;
			                var imgHeight =doc.internal.pageSize.getHeight();*/
			                // jspdf changes
			                
			                pdf.addImage(myImage, 'JPEG', 90,30,655,1000); // 2: 19
			                
			                var myImage2 = canvas2.toDataURL("image/jpeg,1.0");
			                
			                pdf.addPage();
			                
			                pdf.addImage(myImage2, 'JPEG', 90,30,imgWidth,imgHeight); // 2: 19
			                pdf.save(day+'.pdf');
			            }
			        });
			            	
			            }
			        });
		        }
		    });
		  
		  console.log($("#target").width()+"  "+$("#target").height());
		  //$scope.recipient="Sushant";
		 
		
	  }
	  
	  
	  $scope.decreeForm={};
	 
	  getDecreeForm();
	  $scope.showButton = false;
	  function getDecreeForm(){
		  $http.get(urlBase+'notice/getDecreeForm/'+$scope.doc_id).success(function (data) {
			  $scope.decreeForm=data.modelData;	
			  $scope.decreeStage=data.decreeStage;
			  /*for (var i = 0; i < $scope.decreeStage.length; i++) {
			      console.log($scope.decreeStage[i].ds_stage_lid);
				  if ($scope.decreeStage[i].ds_cr_by &&
				              [4000,4002,4004,4006].includes(Number($scope.decreeStage[i].ds_stage_lid))) {
				              $scope.showButton = true;
				          }
			  }*/
			  
			  if(data.modelData !=null){			 
				 /* $scope.decreeForm.df_4th_div.replace("{{decreeForm.crBy.um_fullname}}",$scope.decreeForm.crBy.um_fullname);*/
		    		$('#target11').html($scope.decreeForm.df_first_div);
                    $('#target21').html($scope.decreeForm.df_2nd_div);
                    $("#txtEditor1").Editor("setText", $scope.decreeForm.df_editor);
                    $('#target31').html($scope.decreeForm.df_3rd_div);
                    $('#target41').html($scope.decreeForm.df_4th_div);
                                       
                    console.log("dataaaaaaaaaaaaaaaaa",$scope.decreeForm);
					console.log("dataaaaaaStageeeeeeee= ",$scope.decreeStage);
                    
                   // $scope.decreeForm.df_first_div.df_assign_to=null;
                    
                    $scope.getDecreeCreator($scope.decreeForm.df_id);
		    		
		    		
		  }
		  else{
			  console.log("in else",$scope.decreeForm);
			/*console.log ("DecreeForm Else in new !!!!!!! ", $scope.decreeForm={});*/
			 
		  }
		    	
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting casetypes");
		      });
	  }
	  
	  $scope.getDecreeCreator = function(df_id){
		  
		  $http.get(urlBase+'notice/getDecreeCreator/'+df_id).success(function (data) {
			  
			  $scope.decreeCreator=data.modelData;	
			  
		  });
		  
	  }
	  
	  $scope.viewCaseFile=function(id){
		  window.open(urlBase+"casefile/view/"+id,"_blank");
	  }
	  
	
	  $scope.deleteDecreeFile=function(id){
		  var result=confirm("Are you really want to delete this record");
		  if (result) {
			  $http({
				  method : 'DELETE',
				  url : urlBase + 'notice/getDeleteDecree/' + id
		   		}).success(function(response) {
		   			alert("Successfully deleted record");
		   			window.location.reload();			
		   		});	
		  }
	  }
	  
	
	  $scope.saveForm = function () {		 
		  if($scope.decreeForm==null){				
		  $scope.decreeForm={};
		  $scope.decreeForm.df_fd_mid = $scope.doc_id;
		  var MyDiv1 = document.getElementById('target1');
		  $scope.decreeForm.df_first_div=MyDiv1.innerHTML;
		  $scope.decreeForm.df_2nd_div=document.getElementById('target2').innerHTML ;
		  $scope.decreeForm.df_3rd_div=document.getElementById('target3').innerHTML;
		  $scope.decreeForm.df_4th_div=document.getElementById('target4').innerHTML;
		  $scope.decreeForm.df_5th_div=document.getElementById('target5').innerHTML;
		  $scope.decreeForm.df_editor=$("#txtEditor2").Editor("getText");
		  
		 
		  }
		  else{
			  $scope.decreeForm.df_fd_mid = $scope.doc_id;
			  var MyDiv1 = document.getElementById('target11');
			  $scope.decreeForm.df_first_div=MyDiv1.innerHTML;
			  $scope.decreeForm.df_2nd_div=document.getElementById('target21').innerHTML ;
			  $scope.decreeForm.df_3rd_div=document.getElementById('target31').innerHTML;
			  $scope.decreeForm.df_4th_div=document.getElementById('target41').innerHTML;
			  $scope.decreeForm.df_5th_div=document.getElementById('target51').innerHTML;
			 // $scope.decreeForm.df_stage_lid=$scope.decreeForm.df_stage_lid+1;
			  $scope.decreeForm.df_editor=$("#txtEditor1").Editor("getText");
		  }
		  
		  
		  $http.post(urlBase+'notice/saveDecreeForm',$scope.decreeForm).success(function (data) {
		    	if(data.response=="TRUE"){
		    		console.log("data daaaaaa",data);
		    		alert("Decree saved successfully");
		    		getDecreeForm();
		    	}
		    	else
		    		{
		    		console.log("Some problem")
		    		}
		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
		  
		        $scope.courtList[indx] = angular.copy($scope.editablerow);
		        $scope.reset();
	    };
	    
	    $scope.assign_to=null;
	    
	    $scope.nextStage=function(stage){
	    	console.log($scope.decreeForm);
	    	if($scope.assign_to){
	    		$scope.decreeForm.df_assign_to=$scope.assign_to;
	    	$scope.decreeForm.df_stage_lid=$scope.decreeForm.df_stage_lid+1;
	    	$http.post(urlBase+'notice/nextStage',$scope.decreeForm).success(function (data) {
		    	if(data.response=="TRUE"){
		    		console.log("data daaaaaa",data);
		    		alert("Send to next Stage");
		    		getDecreeForm();
		    		
		    		setTimeout(function(){
		    		    location.reload();
		    		}, 1000); // 3000 milliseconds = 3 seconds
		    	}
		    	else
		    		{
		    		console.log("Some problem")
		    		}
		    		
		      }).
		      error(function(data, status, headers, config) {
		      	console.log("Error in getting tree data");
		      });
	    	}
	    	else{
	    		alert("Please Select");
	    	}
	    }
	  
	  $scope.newString =null;
	  
	  $scope.downloadDecree=function(){
		  
		  window.open(urlBase+'notice/downloadDecree/'+$scope.doc_id,'_self');
		
	  }
	  
	  $scope.generateDoc=function()
	  		 {
	  			
	  			window.open(urlBase+'notice/downloadDecreeDoc/'+$scope.doc_id,'_self');
	  			
	  			
	  			
	  		 }	
			 
			 
	  
	  
	  
	  


	 
	 
	 
	 
	 
	 

	
	$scope.downloadDecreeJs = function () {

		      let content = document.getElementById("pdfcontent");

		      if (!content) {
		          alert("Content not found");
		          return;
		      }

		      let clonedContent = content.cloneNode(true);

		      // ✅ Replace textarea with actual text
		      clonedContent.querySelectorAll("textarea").forEach(el => {
		          let div = document.createElement("div");
		          div.className = "print-text";
		          div.innerText = el.value || el.innerHTML || "";
		          el.replaceWith(div);
		      });

		      // ✅ Remove unwanted UI
		      clonedContent.querySelectorAll(".no-print, button, .btn, .fa").forEach(el => el.remove());

		      // ✅ Remove contenteditable (VERY IMPORTANT)
		      clonedContent.querySelectorAll("[contenteditable]").forEach(el => {
		          el.removeAttribute("contenteditable");
		      });

		      // ✅ Remove empty editor wrappers
		      clonedContent.querySelectorAll(".editor-content").forEach(el => {
		          if (!el.innerText.trim()) el.remove();
		      });

		      let printWindow = window.open('', '', 'width=900,height=650');

		      printWindow.document.write(`
		          <html>
		          <head>
		              <title>Print</title>
		              <style>
		                  body {
		                      font-family: Arial, sans-serif;
		                      padding: 20px;
		                  }

		                  table {
		                      width: 100%;
		                      border-collapse: collapse;
		                  }

		                  table, th, td {
		                      border: 1px solid black;
		                  }

		                  .print-text {
		                      white-space: pre-wrap;
		                      text-align: justify;
		                  }
		              </style>
		          </head>
		          <body>
		              ${clonedContent.innerHTML}
		          </body>
		          </html>
		      `);

		      printWindow.document.close();

		      setTimeout(() => {
		          printWindow.print();
		          printWindow.close();
		      }, 500);
		  };
		 
	 
	 

	
	  
	//==============================================  
	
	  
	  $scope.test6=function(){

		  var MyDiv1 = document.getElementById('target1');
		  var testHtml=MyDiv1.innerHTML;
		  $scope.newString = $("#txtEditor1").Editor("getText");
		  $('.results').html($scope.newString);
		
			  var pdf = new jsPDF('p', 'pt', 'a4');
		       
			  
			  var day="test5";
		           console.log(day);
		           $scope.recipient=day;
		           return html2canvas($('#target11'), {
			            background: "#ffffff",
			            onrendered: function(canvas) {
			            	
			            	html2canvas($('#target21'), {
			    	            background: "#ffffff",
			    	            onrendered: function(canvas2) {
			            	
			            	
			    	            	html2canvas($('#target3'), {
					    	            background: "#ffffff",
					    	            onrendered: function(canvas3) {
					    	            	

					    	            	html2canvas($('#target4'), {
							    	            background: "#ffffff",
							    	            onrendered: function(canvas4) {
					            	
					            	
					                var myImage = canvas.toDataURL("image/jpeg,1.0");
					                
					               
					                // Adjust width and height
					                var imgWidth =  (canvas.width * 60) / 240;
					                var imgHeight = (canvas.height * 70) / 240;
					              /*  var imgWidth =doc.internal.pageSize.getWidth();;
					                var imgHeight =doc.internal.pageSize.getHeight();*/
					                // jspdf changes
					                
					                pdf.addImage(myImage, 'JPEG', 5,10,585,700); // 2: 19
					                
					                var myImage2 = canvas2.toDataURL("image/jpeg,1.0");
					                
					                var imgHeight2 = (canvas2.height * 70) / 120;
					                
					                pdf.addPage();
					                
					                pdf.addImage(myImage2, 'JPEG', 5,10,585,imgHeight2); // 2: 19
					                
                                     var myImage3 = canvas3.toDataURL("image/jpeg,1.0");
                                     var imgHeight3 = (canvas2.height * 70) / 120;
					                
					                pdf.addPage();
					                
					                pdf.addImage(myImage3, 'JPEG', 5,10,585,imgHeight3); // 2: 19
					                
					                var myImage4 = canvas4.toDataURL("image/jpeg,1.0");
					                
                                    pdf.addPage();
					                
					                pdf.addImage(myImage4, 'JPEG', 5,10,585,700);
					                
					                
					                //pdf.save(day+'.pdf');
					                window.open(pdf.output('bloburl'), '_blank');
					            }
					    	            	});
			    	            	}
					        
			        });
			            	
			            }
			            	
			        });
			            	
		           }
	            	
      });
		        
		  
		  
		  console.log($("#target").width()+"  "+$("#target").height());
		  //$scope.recipient="Sushant";
		
	  }
	  
	  
	  $scope.returnToDecreeWriter =function(decreeForm){
			console.log("case detailssssssssssss",decreeForm);
			df_id=decreeForm.df_id;
			df_fd_mid=decreeForm.df_fd_mid; 
			remark=decreeForm.remark
			if(!remark){
				alert("Field should not be empty");
				return false;
				
			}
			else {
				 var confirmbox = confirm("Be Careful:\n ONCE FILE WILL BE RETURNED TO DECREE WRITER");
		    	 if (confirmbox) 
		    	 {
		    		 $http.get(urlBase+ 'notice/getDecreeRemark/'+$scope.doc_id, {
		    				params : {
		    					'df_id' : df_id,'remark' : remark,	
		    				}
		    			}).success(function(data, status, headers, config) {
		    				$('#returntodecree').modal('hide');
		    				
		    				//alert("FILE RETURN TO DECREE WRITER ...");
		    				alert(data.data);
		    				
		    				setTimeout(function(){
		    				    location.reload();
		    				}, 1000); // 3000 milliseconds = 3 seconds
		    				
		    			         
		    			}).error(function(data, status, headers, config) {
		    			});
		    	 }
			}
			
			
		}
		
		
		$scope.TemplateDescription = "";

		   $scope.cleanPaste = function(event) {
		       event.preventDefault(); // stop default paste
		       let text = (event.originalEvent || event).clipboardData.getData('text/plain');
		       $scope.TemplateDescription += text; // append clean text
		       $scope.$apply();
		   };
		   
		   


}]);