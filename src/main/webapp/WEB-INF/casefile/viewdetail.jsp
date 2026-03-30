<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <jsp:include page="../content/header2.jsp"></jsp:include>
    <input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id">
    <div id="content" class="content">
        <div class="container-fluid" ng-controller="CaseFileCtrl">
            <div class="row">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-inverse overflow-hidden">
                        <div class="panel-heading">
                            <h3 class="panel-title">
	                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#caseDetails">
										    <i class="fa fa-plus-circle pull-right"></i> 
											Case Details
								</a>
	                        </h3>
                        </div>
                        <div id="caseDetails" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <!-- <div class="row">
	                        	<button type="button" class="btn btn-success m-r-5 m-b-5 pull-right" data-toggle="modal" ng-click="setModel()" data-target="#uploadPetition">Upload Petition</button>
	                        	<button type="button" class="btn btn-success m-r-5 m-b-5 pull-right" data-toggle="modal" ng-click="setModel()" data-target="#uploadDocument">Upload Other Document</button>
	                        </div> -->
                                <div class="table-responsive">
                                    <table id="data-table" class="table table-striped table-bordered">
                                        <tr>
                                            <th>Case Type</th>
                                            <th>Case No.</th>
                                            <th>Case Year</th>
                                            <th>Action</th>
                                        </tr>
                                        <tr>
                                            <td>{{casefile.caseType.ct_label}}</td>
                                            <td>{{casefile.fd_case_no}}</td>
                                            <td>{{casefile.fd_case_year}}</td>
                                            <td>
                                                <button type="button" class="btn btn-success m-r-5 m-b-5 pull-left" data-toggle="modal" ng-click="setModel()" data-target="#uploadPetition">Upload Petition</button>
                                                <button type="button" class="btn btn-success m-r-5 m-b-5 pull-left" data-toggle="modal" ng-click="setModel()" data-target="#uploadDocument">Upload Other Document</button>
                                                <button type="button" class="btn btn-success m-r-5 m-b-5 pull-left" data-toggle="modal" ng-click="setModel()" data-target="#addWithPetition">Add with Petition</button>
<!--                                                   <button type="button" class="btn btn-success m-r-5 m-b-5 pull-left" data-toggle="modal" ng-click="setModel()" ng-click ="getApplicationWithPetition()" data-target="#editApplicationWithPetition" >Edit Application with Petition</button>
 -->                                                                                                    <button type="button" class="btn btn-success m-r-5 m-b-5 pull-left" data-toggle="modal"  ng-click ="getApplicationWithPetition()" data-target="#editApplicationWithPetition" >Edit Application with Petition</button>
                                                  
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-inverse overflow-hidden">
                        <div class="panel-heading">
                            <h3 class="panel-title">
	                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#subdocuments">
										    <i class="fa fa-plus-circle pull-right"></i> 
											Sub Documents
								</a>
	                        </h3>
                        </div>
                        <div id="subdocuments" class="panel-collapse collapse">
                            <div class="panel-body">

                                <div class="table-responsive">
                                    <table id="data-table" class="table table-striped table-bordered">
                                        <tr>
                                            <th>Type</th>
                                            <th style="width: 20%">Document Type</th>
                                            <th style="width: 20%">Submitted</th>
                                            <th style="width: 20%">Uploaded Date</th>
                                            <th>Action</th>
                                        </tr>
                                        <tr ng-repeat="data in subDocuments">
                                            <td ng-click="showSubDocument(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:20%"><b>{{data.indexField.if_label}} <br/> {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                            <td>{{data.documentType.at_name}}</td>
                                            <td>{{data.sd_submitted_date | date:"dd/MM/yyyy"}}</td>
                                            <td>{{data.sd_cr_date | date:"dd/MM/yyyy, h:mm:ss a"}}</td>
                                            <td>
                                                <button class="btn btn-success btn-sm" ng-click="deletesubdocument(data.sd_id)">Delete</button>
                                                <span ng-if="data.sd_document_id==100001">
												<button  class="btn btn-success btn-sm" id="Submit" type="submit" class="btn btn-success" data-target="#uploadModel" data-toggle="modal" ng-click="setModel(data)" >Replace File</button>	                                                
                                                </span>
                                                
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            
                            <!-- changjhsd -->
							         <div class="modal fade" id="uploadModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<h4 class="modal-title" id="myModalLabel">
														<strong>Upload</strong>
													</h4>
												</div>
												<%@ include file="../casefile/uploadReplace.jsp"%>
											</div>
										</div>
									</div>
									
							<!-- changjhsd -->
									
									
									
                        </div>
                    </div>
                    <div class="panel panel-inverse overflow-hidden">
                        <div class="panel-heading">
                            <h3 class="panel-title">
	                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#officereport">
										    <i class="fa fa-plus-circle pull-right"></i> 
											Office Report
								</a>
	                        </h3>
                        </div>
                        <div id="officereport" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table id="data-table" class="table table-striped table-bordered">
                                        <tr>
                                            <th>Type</th>
                                            <th style="width: 60%">Remark</th>
                                            <th>Submitted</th>
                                            <th>Uploaded Date</th>
                                            <th>Action</th>
                                        </tr>
                                        <tr ng-repeat="data in orderReports">
                                            <td>
                                                <span ng-if="data[0].subDocument==null">Off. Rep.</span>
                                                <span ng-if="data[0].subDocument!=null" ng-click="showSubDocument(data[0].subDocument.sd_id)" style="text-decoration: underline;cursor:pointer;">Off. Rep.</span>
                                                <br> {{data[0].ord_mod_date | date:'dd-MM-yyyy'}}</b>
                                            </td>
                                            <td>
                                                <div ng-bind-html="data[0].ord_remark"></div>
                                                <br>
                                                <br><b>Submiited by :- {{data[1].um_fullname}}</td>
											<td>{{data[0].ord_created | date:"dd/MM/yyyy"}}</td>
											<td>{{data[0].ord_submitted_date | date:"dd/MM/yyyy, h:mm:ss a"}}</td>

											<td><button class="btn btn-success btn-sm" ng-click="deleteofficereport(data[0].ord_id)">Delete</button></td>
			                         	</tr>
			                		</table>
	                            </div>
	                        </div>
                    	</div>
                	</div>
                	<div  ng-show="casefile.impugnedOrders.length > 0" class="panel panel-inverse overflow-hidden">
				      	<div class="panel-heading">
				        	<h3 class="panel-title">
				              	<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseImpugnedOrders">
								   <i class="fa fa-plus-circle pull-right"></i> 
									Impugned Orders
								</a>
				          	</h3>
				    	</div>
      					<div id="collapseImpugnedOrders" class="panel-collapse collapse">
          					<div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
       							<div class="table-responsive">
									<table id="data-table" st-table="casefile.impugnedOrders" class="table table-striped table-bordered">
						               <thead>
						                   	<tr>
						                       <th>Case Details</th>
						                       <th>Type</th>
						                       <th>District</th>                                  
						                    </tr>
						                    <tr ng-repeat="data in casefile.impugnedOrders">
						                     <td style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><span ng-if="data.io_court_type==1">{{data.lcCaseType.ct_name}}<br/>{{data.io_case_no}}/{{data.io_case_year}}</span><span ng-click="showHighCourtCase(data.io_id)"  ng-if="data.io_court_type==2">{{data.hcCaseType.ct_name}}<br/>{{data.io_case_no}}/{{data.io_case_year}}</span></td>

						                     <td style="text-align:justify"><span ng-if="data.io_court_type==1">Lower Court</span><span ng-if="data.io_court_type==2">High Court</span></td>
						                     <td style="text-align:justify">{{data.district.dt_name}}</td>
						                         		</tr>
						                    </thead>

						          	</table>
            					</div>
        					</div>
    					</div>
					</div>
					<div  ng-show="casefile.petitioners.length > 0" class="panel panel-inverse overflow-hidden">
           				<div class="panel-heading">
               				<h3 class="panel-title">
                   				<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapsePetitioners">
				    				<i class="fa fa-plus-circle pull-right"></i> 
										Petitioners
								</a>
               				</h3>
           				</div>
           			<div id="collapsePetitioners" class="panel-collapse collapse">
               			<div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   			<div class="table-responsive">
                       			<table id="data-table" st-table="casefile.petitioners" class="table table-striped table-bordered">
                           			<thead>
                               			<tr>
                                   			<th>Name</th>
                                   			<th>Action</th>                                  
                                		</tr>
                                		<tr ng-repeat="data in casefile.petitioners">
                                 			<td style="text-align:justify">{{data.pt_name}}</td>
                                 			<td><button class="btn btn-success btn-sm" ng-click="deletepetitioner(data.pt_id)">Delete</button></td>                                 
                         				</tr>
                    				</thead>                    
                				</table>
            				</div>
        				</div>
    				</div>
				</div>
					<div  ng-show="casefile.respondents.length > 0" class="panel panel-inverse overflow-hidden">
           				<div class="panel-heading">
               				<h3 class="panel-title">
                   			<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseRespondents">
				    			<i class="fa fa-plus-circle pull-right"></i> 
									Respondents
							</a>
               				</h3>
           				</div>
           				<div id="collapseRespondents" class="panel-collapse collapse">
               				<div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
                   				<div class="table-responsive">
                       				<table id="data-table" st-table="casefile.respondents" class="table table-striped table-bordered">
                           				<thead>
                               				<tr>
                                   				<th>Name</th>
                                   				<th>Action</th>                                  
                                			</tr>
                                			<tr ng-repeat="data in casefile.respondents">
                                 				<td style="text-align:justify">{{data.rt_name}}</td>
                                 				<td><button class="btn btn-success btn-sm" ng-click="deleterespondent(data.rt_id)">Delete</button></td>                                 
                         					</tr>
                    					</thead>                    
                					</table>
            					</div>
        					</div>
    					</div>
					</div>
					<div  ng-show="casefile.pCounsels.length > 0" class="panel panel-inverse overflow-hidden">
	           			<div class="panel-heading">
	               			<h3 class="panel-title">
	                   			<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapsePCounsels">
					    			<i class="fa fa-plus-circle pull-right"></i> 
										Petitioner Counsels
								</a>		
	               			</h3>
	           			</div>
	           		<div id="collapsePCounsels" class="panel-collapse collapse">
	               		<div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
	                   		<div class="table-responsive">
	                       		<table id="data-table" st-table="casefile.pCounsels" class="table table-striped table-bordered">
	                           		<thead>
	                               		<tr>
	                                   		<th>Name</th>
	                                   		<th>Action</th>                                  
	                                	</tr>
	                                	<tr ng-repeat="data in casefile.pCounsels">
	                                 		<td style="text-align:justify">{{data.pc_name}}</td>
	                                 		<td><button class="btn btn-success btn-sm" ng-click="deletepcounsel(data.pc_id)">Delete</button></td>                                 
	                         			</tr>
	                    			</thead>                    
	                			</table>
	                		</div>
	        			</div>
	    			</div>
				</div>
					<div  ng-show="casefile.rCounsels.length > 0" class="panel panel-inverse overflow-hidden">
		           		<div class="panel-heading">
		               		<h3 class="panel-title">
		                   		<a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseRCounsels">
						    		<i class="fa fa-plus-circle pull-right"></i> 
									Respondent Counsels
								</a>
		               		</h3>
		           		</div>
		           		<div id="collapseRCounsels" class="panel-collapse collapse">
		               		<div class="panel-body" style="padding:2px;max-height:450px;overflow:auto;">
		                   		<div class="table-responsive">
		                       		<table id="data-table" st-table="casefile.rCounsels" class="table table-striped table-bordered">
		                           		<thead>
		                               		<tr>
		                                   		<th>Name</th>
		                                   		<th>Action</th>                                  
		                                	</tr>
		                                	<tr ng-repeat="data in casefile.rCounsels">
		                                 		<td style="text-align:justify">{{data.rc_name}}</td>
		                                 		<td><button class="btn btn-success btn-sm" ng-click="deletercounsel(data.rc_id)">Delete</button></td>                                 
		                         			</tr>
		                    			</thead>                    
		                			</table>
		            			</div>
		        			</div>
		    			</div>
					</div>
				</div>
				<div class="modal fade" id="uploadDocument" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<strong>Upload Document</strong>
									</h4>
								</div>
								<%@ include file="../casefile/_upload_form.jsp"%>
							</div>
						</div>
				</div>
				<div class="modal fade" id="uploadPetition" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<strong>Upload Petition</strong>
									</h4>
								</div>
								<%@ include file="../casefile/_upload_petition.jsp"%>
							</div>
						</div>
				</div>
				<div class="modal fade" id="addWithPetition" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<strong>Add Application With Petition</strong>
									</h4>
								</div>
								<%@ include file="../casefile/addWithPetion.jsp"%>
							</div>
						</div>
				</div>
				
				<div class="modal fade" id="editApplicationWithPetition" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<strong>Edit Application With Petition</strong>
									</h4>
								</div>
								<%@ include file="../casefile/editApplicationWithPetion.jsp"%>
							</div>
						</div>
				</div>

			</div>
		</div>
	</div>
	<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>

</div>

</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileDetailController.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script type="text/javascript"src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootbox.min.js"></script>
	<style>
	 .st-sort-ascent:before {
	    content: '\25B2';
	  }

	  .st-sort-descent:before {
	    content: '\25BC';
	  }
	</style>
	<script>
		$(document).ready(function() {
			App.init();

		});
	</script>
</html>