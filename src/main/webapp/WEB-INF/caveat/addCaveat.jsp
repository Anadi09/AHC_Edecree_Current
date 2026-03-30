<jsp:include page="../content/header2.jsp"></jsp:include>
<div id="content" class="content">


	<div class="container-fluid" ng-controller="addCaveatController"
		oncontextmenu="return false;">

		<div class="row">
			<!-- begin col-12 -->
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i></a>
					</div>
					<h4 class="panel-title">Manage Caveat</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 ">
							<button type="button" class="btn btn-primary btn-sm pull-right"
								ng-click="resetModel()" data-toggle="modal"
								data-target="#user_Modal">
								<span class="glyphicon glyphicon-plus-sign"></span>Add Caveat
							</button>
						</div>
					</div>
					<div class="table-responsive">
						<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover"
							width="100%">
							<thead>
								<tr>

									<td><input type="text" class="form-control"
										placeholder="Caveat No" ng-model="manualCaveat1.mcav_no">
									</td>
									<td><input type="text" class="form-control"
										placeholder="Caveat Year" ng-model="manualCaveat1.mcav_year">
									</td>
									<td>
										<button id="search" type="submit"
											class="btn btn-primary btn-sm" ng-click="searchCaveat()">Search</button>
									</td>
								</tr>

							</thead>
						</table>

						<table id="data-table" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th style="width: 20%;" class="text-center">Case</th>
									<th style="width: 20%;" class="text-center">Caveat No.</th>
									<th style="width: 20%;" class="text-center">Caveat Year</th>

									<th style="width: 20%;" class="text-center">Action</th>
								</tr>
							</thead>
							<tbody class="text-center">
								<tr ng-repeat="data in caveatList" class="odd gradeX">

									<td
										style="text-align: center; cursor: pointer; text-decoration: underline;">
										<span>{{data.caseFileDetail.caseType.ct_label}}<br />{{data.caseFileDetail.fd_case_no}}/{{data.caseFileDetail.fd_case_year}}
									</span> <!-- <span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2" ng-click="viewCaseFile(data.cl_fd_mid)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span> -->
									</td>
									<td>{{data.mcav_no}}</td>
									<td>{{data.mcav_year}}</td>
									<td ng-if="!data.caseFileDetail"><a class="btn btn-info btn-xs"
										ng-click="edit(data)" data-toggle="modal"
										data-target="#user_Modal_update"><span
											class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
											Edit</a></td>
											<td ng-if="data.caseFileDetail">
											Already Moved</td>
								</tr>

								<tr ng-show="caveatList.length==0">
									<td colspan="8">
										<div class="alert alert-danger">No Records Found</div>

									</td>
								</tr>
							</tbody>
						</table>

					</div>

					<div class="modal fade" id="user_Modal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<span ng-if="!masterentity.um_id"><strong> Add
												New Caveat</strong></span> <!-- <span ng-if="masterentity.um_id"><Strong>Update
												Caveat</Strong></span> -->
									</h4>
								</div>


								<form class="form-horizontal reduce-gap" name="masterForm"
									role="form" novalidate>
									<div class="modal-body">
										<div ng-show="errorlist"
											class="alert alert-block alert-danger"></div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-md-4 control-label" for="mcav_no">Caveat
														No <span class="text-danger">*</span>
													</label>
													<div class="col-md-7">
														<input class="form-control" required type="text"
															id="mcav_no" name="mcav_no"
															ng-model="manualCaveat.mcav_no"
															placeholder="Enter Caveat No" />
													</div>
												</div>
											</div>
											<di
											 class="col-md-6">
												<div class="form-group">
													<label class="col-md-4 control-label" for="mcav_year">Caveat
														Year <span class="text-danger">*</span>
													</label>
													<div class="col-md-7">
														<input class="form-control" required type="text"
															id="mcav_year" name="mcav_year"
															ng-model="manualCaveat.mcav_year"
															placeholder="Enter Caveat Year" />
													</div>

													<input type="hidden" class="form-control"
														value=${mcav_document_name } id="mcav_document_name"
														name="mcav_document_name">
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-md-4">Date of
													Caveat: <span class="text-danger"> * </span>
												</label>
												<div class="col-md-4">
													<div class="input-group date">
														<input type="text" class="form-control"
															datepicker-popup="{{format}}"
															ng-model="manualCaveat.mcav_submitted_date"
															is-open="opened" datepicker-options="dateOptions"
															ng-disabled="true" /> <span class="input-group-addon"
															ng-click="open($event,'opened')"><i
															class="glyphicon glyphicon-calendar"></i></span>
													</div>
												</div>
											</div>



											<div class="smooth container w-xxxl w-auto-xs">
												<form name="petitioner" class="form-validation">
													<!-- <fieldset class="scheduler-border"> -->
													<div class="col-sm-8" style="float: centre">
														<div class="panel panel-default"
															style="margin-left: 140px;">
															<div class="panel-body">
																<div class="form-group pull-in clearfix">

																	<div class="col-sm-4"></div>

																</div>

																<div class="form-group pull-in clearfix">

																	<label class=" control-label" for="file">Upload
																		File<span class="text-danger">*</span>
																	</label> <input type="file" ngf-select ng-model="picFile"
																		name="file">
																</div>

															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>

									<div class="modal-footer">
										<div ng-if="!masterentity.um_id">
											<input type="submit" value="Submit"
												ng-disabled="masterForm.$invalid" ng-click="addCaveat()"
												class="btn btn-success" />
											<button type="button" class="btn btn-danger"
												data-dismiss="modal">Cancel</button>
										</div>

										<!-- <div ng-if="masterentity.um_id">
											<input type="reset" value="Reset" id="update-masterForm"
												ng-disabled="masterForm.$invalid"
												data-loading-text="Creating..." autocomplete="off"
												ng-click="Date_Reset()" class="btn btn-success" /> <input
												type="submit" value="Update" id="update-masterForm"
												ng-disabled="masterForm.$invalid"
												data-loading-text="Updating..." autocomplete="off"
												ng-click="user_update(manualCaveat)" class="btn btn-success" />
											<button type="button" class="btn btn-danger"
												data-dismiss="modal">Cancel</button>
										</div> -->
									</div>

								</form>
							</div>
						</div>
					</div>
					
					
					<!-- update manual caveat -->
				
					<div class="modal fade" id="user_Modal_update" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										 <span ><!-- ng-if="masterentity.um_id" --><Strong>Update
												Caveat</Strong></span>
									</h4>
								</div>


								<form class="form-horizontal reduce-gap" name="masterForm"
									role="form" novalidate >
									<div class="modal-body">
										<div ng-show="errorlist"
											class="alert alert-block alert-danger"></div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-md-4 control-label" for="mcav_no">Caveat
														No <span class="text-danger">*</span>
													</label>
													<div class="col-md-7">
														<input class="form-control" required type="text"
															id="mcav_no" name="mcav_no"
															ng-model="manualCaveat.mcav_no"
															placeholder="Enter Caveat No" />
													</div>
												</div>
											</div>
											
											<div class="col-md-6">
												<div class="form-group">
													<label class="col-md-4 control-label" for="mcav_year">Caveat
														Year <span class="text-danger">*</span>
													</label>
													<div class="col-md-7">
														<input class="form-control" required type="text"
															id="mcav_year" name="mcav_year"
															ng-model="manualCaveat.mcav_year"
															placeholder="Enter Caveat Year" />
													</div>

													<input type="hidden" class="form-control"
														value=${mcav_document_name } id="mcav_document_name"
														name="mcav_document_name">
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-md-4">Date of
													Caveat: <span class="text-danger"> * </span>
												</label>
												<div class="col-md-4">
													<div class="input-group date">
														<input type="text" class="form-control"
															datepicker-popup="{{format}}"
															ng-model="manualCaveat.mcav_submitted_date"
															is-open="opened" datepicker-options="dateOptions"
															ng-disabled="true" /> <span class="input-group-addon"
															ng-click="open($event,'opened')"><i
															class="glyphicon glyphicon-calendar"></i></span>
													</div>
												</div>
											</div>                                                      

											<div class="smooth container w-xxxl w-auto-xs">
												<form name="petitioner" class="form-validation">
													<!-- <fieldset class="scheduler-border"> -->
													<div class="col-sm-8" style="float: centre">
														<div class="panel panel-default"
															style="margin-left: 140px;">
															<div class="panel-body">
																<div class="form-group pull-in clearfix">

																	<div class="col-sm-4"></div>

																</div>

																<div class="form-group pull-in clearfix">

																	<label class=" control-label" for="file">Upload
																		File<span class="text-danger">*</span>
																	</label> <input type="file" ngf-select ng-model="picFile"
																		name="file">
																</div>

															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>

									<div class="modal-footer">

										<div >
											<input type="reset" value="Reset" id="update-masterForm"
												ng-disabled="masterForm.$invalid"
												data-loading-text="Creating..." autocomplete="off"
												ng-click="Date_Reset()" class="btn btn-success" /> <input
												type="submit" value="Update" id="update-masterForm"
												ng-disabled="masterForm.$invalid"
												data-loading-text="Updating..." autocomplete="off"
												ng-click="manualCaveat_update(manualCaveat)" class="btn btn-success" />
											<button type="button" class="btn btn-danger"
												data-dismiss="modal">Cancel</button>
										</div>
									</div>

								</form>
							</div>
						</div>
					</div>
					
				<!-- end manual caveat update -->
					
					
					<div class="modal fade" id="pass_Modal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header" style="background-color: black;">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">
										<strong style="color: #FBFCFD;">Change Password </strong>
									</h4>
								</div>
								<%@ include file="../user/passwordPage.jsp"%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</div>
<!-- end row -->

<!-- ================== END PAGE LEVEL JS ================== -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/addCaveatController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>


<script>
	$(document).ready(function() {
		App.init();

	});
</script>
