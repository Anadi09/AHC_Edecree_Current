<%@ include file="../content/header2.jsp"%>
<style>
.animated-btn {
	animation: pulse 1.5s infinite;
}

@
keyframes pulse { 0% {
	transform: scale(1);
} 50% {
transform:scale(1.08);
}100% {
transform:scale(1);
}
}
/* spacing */
.mb-3 {
	margin-bottom: 15px;
}

/* input focus effect */
.form-control:focus {
	border-color: #007bff;
	box-shadow: none;
}

/* modal smooth look */
.modal-content {
	border-radius: 8px;
	overflow: hidden;
}

/* label styling */
label {
	font-size: 13px;
	margin-bottom: 5px;
}

/* ===== TABLE DESIGN ===== */
.table {
	border-radius: 8px;
	overflow: hidden;
	background: #fff;
}

.table thead {
	background: background: linear-gradient(45deg, #6fb1fc, #d0e6ff);;
	color: #fff;
}

.table thead th {
	border: none !important;
	font-weight: 600;
	text-align: center;
	vertical-align: middle;
}

.table tbody td {
	vertical-align: middle !important;
	text-align: center;
}

/* Hover effect */
.table-hover tbody tr:hover {
	background-color: #f2f7ff;
	transition: 0.3s;
}

/* Zebra improvement */
.table-striped tbody tr:nth-of-type(odd) {
	background-color: #f9fbff;
}

/* Inputs inside table */
.table input {
	border-radius: 6px;
	border: 1px solid #ced4da;
	padding: 4px 8px;
}

/* Buttons */
.btn-sm {
	border-radius: 20px;
	padding: 5px 12px;
	font-size: 12px;
}

/* Section card */
.custom-card {
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
	margin-bottom: 20px;
}

.custom-header {
	background: linear-gradient(45deg, #007bff, #004085);
	color: #fff;
	padding: 10px;
	font-weight: 600;
	text-align: center;
	border-radius: 10px 10px 0 0;
}
</style>

<html>
<body>
	<div id="content" class="content">
		<div class="container-fluid" ng-controller="CaseFileController"
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
						<div class="card py-4 px-4">
							<div
								class="card-header  bg-dark text-white font-weight-bold px-4"
								style="height: 35px; display: flex;">
								<h4 class="text-white">Decree Send SMS</h4>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table id="data-table" st-table="displayedCollection"
								st-safe-src="masterdata"
								class="table table-striped table-bordered nowrap table-hover"
								width="100%">
								<thead>
									<tr>

										<td width="25%"><select class="form-control"
											ng-model="search.ct_ccms_id"
											ng-options="caseType.ct_ccms_id as caseType.labelandname for caseType in caseTypes  | orderBy:'ct_label' | filter:{ct_status:1}">
												<option value="">Select Case Type</option>
										</select></td>

										<td><input type="text" class="form-control"
											placeholder="Case No" ng-model="search.fd_case_no"></td>
										<td><input type="text" class="form-control"
											placeholder="Case Year" ng-model="search.fd_case_year">
										</td>
										<td>
											<button id="search" type="submit"
												class="btn btn-primary btn-sm"
												ng-click="searchCaseFileDecree()">Search</button>
										</td>
									</tr>

								</thead>

							</table>

							<div class="custom-card">
								<div class="custom-header">
									<i class="fa fa-users"></i> List of Associated Advocates
								</div>

								<div class="table-responsive">
									<table class="table table-hover table-striped table-bordered">
										<thead>
											<tr>
												<th>Sr.No</th>
												<th>AOR No</th>
												<th>Name</th>
												<th>Mobile</th>
												<th>Valid Till</th>
												<th>Select</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="row in advFileList  track by $index"
												class="odd gradeX">
												<td>{{$index+1}}</td>
												<td>{{row.aor}}</td>
												<td>{{row.name}}</td>
												<td><input type="text" ng-model="row.mobile" readonly
													ng-click="row.editable = true" ng-readonly="!row.editable">
												</td>

												<td>
													<div>
														<input class="form-control form-control-sm date-picker"
															type="date" style="width: 140px; height: 40px"
															ng-model="row.tillDate">


													</div>
												</td>
												<td><input type="checkbox" ng-model="row.selected"
													ng-change="syncSelectAll()"></td>
												<td>
													<%
													if (role.equals("DECREE CREATOR")) {
													%>
													<button ng-click="sendSingleSms(row)"
														class="btn btn-sm btn-primary">Send SMS</button> <!-- <button class="btn btn-success btn-sm" ng-click="viewCaseFile(row.fd_id)">View</button>
                                                             <button class="btn btn-success btn-sm" ng-click="genrateDecree(row.fd_id)">Generate Decree</button> -->
													<%
													}
													%>
												</td>
											</tr>

											<!-- Sms log button -->



											<tr ng-show="isAllCheck">
												<td></td>
												<td ng-show="advFileList && advFileList.length > 0">
													<button class="btn btn-sm btn-success"
														ng-click="openSmsHistory()">SMS-History</button>
												</td>
												<td ng-show="advFileList && advFileList.length > 0">

													<button class="btn btn-sm btn-warning" data-toggle="modal"
														data-target="#extraAdvocateModal">Add Extra
														Advocate</button>
												</td>
												<td></td>
												<td></td>




												<td ng-show="advFileList && advFileList.length > 0"><input
													type="checkbox" ng-model="selectAll"
													ng-change="toggleAll()"> <label>Select All</label></td>
												<td>
													<button ng-click="sendAllSms()"
														ng-show="advFileList && advFileList.length > 0"
														ng-disabled="!hasAnySelected()"
														class="btn btn-sm btn-success">Send All SMS</button>
												</td>

											</tr>


										</tbody>
									</table>
								</div>
								<hr>



								<div class="custom-card">
									<div class="custom-header">
										<i class="fa fa-user-plus"></i> List of Extra Advocates
									</div>

									<div class="table-responsive">
										<table class="table table-hover table-striped table-bordered">
											<thead>
												<tr>
													<th>Sr.No</th>
													<th>AOR No</th>
													<th>Name</th>
													<th>Mobile</th>
													<th>Valid Till</th>
													<th>Select</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="adv in extraAdvList  track by $index"
													class="odd gradeX">
													<td>{{$index+1}}</td>
													<td>{{adv.aor}}</td>
													<td>{{adv.name}}</td>
													<td><input type="text" ng-model="adv.mobile" readonly
														ng-click="adv.editable = true" ng-readonly="!adv.editable">
													</td>

													<td>
														<div>
															<input class="form-control form-control-sm date-picker"
																type="date" style="width: 140px; height: 40px"
																ng-model="adv.tillDateEx">


														</div>
													</td>
													<td><input type="checkbox" ng-model="adv.selected"
														ng-change="syncSelectAllExtra()"></td>
													<td>
														<%
														if (role.equals("DECREE CREATOR")) {
														%>
														<button ng-click="sendSingleSmsExtra(adv)"
															class="btn btn-sm btn-primary">Send SMS</button> <!-- <button class="btn btn-success btn-sm" ng-click="viewCaseFile(row.fd_id)">View</button>
                                                             <button class="btn btn-success btn-sm" ng-click="genrateDecree(row.fd_id)">Generate Decree</button> -->
														<button class="btn btn-danger btn-sm"
															ng-click="deleteExtraAdvocate(adv)">
															<i class="fa fa-trash"></i> Delete
														</button> <%
 }
 %>
													</td>
												</tr>





												<tr ng-show="isAllCheck">
													<td></td>
													<td ng-show="extraAdvList && extraAdvList.length > 0"></td>
													<td></td>
													<td></td>
													<td></td>




													<td ng-show="extraAdvList && extraAdvList.length > 0"><input
														type="checkbox" ng-model="selectAllExtra"
														ng-change="toggleAllExtra()"> <label>Select
															All</label></td>
													<td>
														<button ng-click="sendAllSmsExtra()"
															ng-show="extraAdvList && extraAdvList.length > 0"
															ng-disabled="!hasAnySelectedExtra()"
															class="btn btn-sm btn-success">Send All SMS</button>


													</td>

												</tr>


											</tbody>
										</table>
									</div>


								</div>



								<!--=-============== Model for add extra advocate================================  -->


								<!-- Add Extra Advocate Modal -->
								<div class="modal fade" id="extraAdvocateModal" tabindex="-1"
									role="dialog">
									<div class="modal-dialog modal-md" role="document">
										<div class="modal-content">

											<!-- HEADER -->
											<div class="modal-header text-light"
												style="background: linear-gradient(45deg, #007bff, #004085); border-bottom: none;">

												<h4 class="modal-title"
													style="font-weight: 600; color: #fff;">
													<i class="fa fa-user-plus"></i> Add Extra Advocate
												</h4>

												<button type="button" class="close text-danger"
													data-dismiss="modal" style="opacity: 1;">
													<span>&times;</span>
												</button>
											</div>

											<!-- BODY -->
											<div class="modal-body" style="padding: 20px 25px;">

												<div class="row">

													<!-- AOR -->
													<div class="col-md-12 mb-3">
														<label class="font-weight-bold">AOR Number</label> <input
															type="text" class="form-control form-control-sm"
															ng-model="extraAdv.aor" placeholder="Enter AOR Number">
													</div>

													<!-- Name -->
													<div class="col-md-12 mb-3">
														<label class="font-weight-bold">Advocate Name</label> <input
															type="text" class="form-control form-control-sm"
															ng-model="extraAdv.name"
															placeholder="Enter Advocate Name">
													</div>

													<!-- Mobile -->
													<div class="col-md-12 mb-3">
														<label class="font-weight-bold">Mobile Number</label> <input
															type="text" class="form-control form-control-sm"
															ng-model="extraAdv.mobile"
															placeholder="Enter Mobile Number" maxlength="10">
													</div>

												</div>

											</div>

											<!-- FOOTER -->
											<div class="modal-footer"
												style="border-top: none; padding: 15px 25px;">

												<button class="btn btn-success btn-sm"
													style="min-width: 90px;" ng-click="saveExtraAdvocate()">
													<i class="fa fa-save"></i> Save
												</button>

												<button class="btn btn-secondary btn-sm"
													style="min-width: 90px;" data-dismiss="modal">
													<i class="fa fa-times"></i> Cancel
												</button>

											</div>

										</div>
									</div>
								</div>



								<!-- /*   ********************************************************************************************************************************/
 -->
								<!-- SMS History Modal -->
								<div class="modal fade" id="smsHistoryModal" tabindex="-1"
									role="dialog">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">

											<div class="modal-header">
												<h4 class="modal-title text-center">SMS Log History</h4>
												<button type="button" class="close" style="font-size: 30px;"
													data-dismiss="modal">
													<span>&times;</span>
												</button>

											</div>

											<div class="modal-body">

												<table class="table table-bordered table-striped">
													<thead>
														<tr>
															<th>Sr No</th>
															<th>AOR</th>
															<th>Mobile</th>
															<th>SMS Text</th>
															<th>Send Date</th>
															<th>Status</th>
														</tr>
													</thead>
													<tbody>
														<tr ng-repeat="sms in smsLogList track by sms.dslId">
															<td>{{$index + 1}}</td>
															<td>{{sms.dslAor}}</td>
															<td>{{sms.dslMobile}}</td>
															<td>{{sms.smsText}}</td>
															<td>{{sms.dslSendDate | date:'dd-MM-yyyy HH:mm'}}</td>
															<td><span ng-if="sms.dslSmsStatus == true"
																class="label label-success"> Sent </span> <span
																ng-if="sms.dslSmsStatus == false"
																class="label label-danger"> Failed </span></td>
														</tr>


														<tr ng-if="smsLogList.length == 0">
															<td colspan="5" class="text-center">No SMS History
																Found</td>
														</tr>
													</tbody>
												</table>

											</div>

										</div>
									</div>
								</div>


								<div ng-if="showLoader" style="height: 60px">
									<div id="loader" class="center"></div>
								</div>
							</div>
							<div ng-show="officeReport.length > 0" class="container-fluid">
								<div class="col-md-12">
									<table style="table-layout: fixed;" id="data-table"
										style="table-layout: fixed; width: 100%"
										class="table table-striped table-bordered">
										<thead>

											<tr>
												<th style="width: 10%;">Date</th>
												<!-- <th style="width: 30%;">User</th> -->
												<th style="width: 70%; overflow-x: auto;">Report</th>

												<th style="width: 20%;" class="col-md-1">Action</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-show="officeReport.length <= 0">
												<td colspan="4" style="text-align: center;">No Report
													Found for Edit</td>
											</tr>

											<tr ng-repeat="data in officeReport ">

												<td>{{data.ord_created | date:"dd/MM/yyyy hh:mm a"}}</td>
												<!--    <td>{{data.submittedBy.um_fullname}}</td> -->
												<td style="width: 50%; overflow-x: auto;"
													ng-bind-html="data.ord_remark"></td>
												<!-- <td>{{data.ord_remark}}</td> -->

												<td><button ng-show="data.cl_flag ==0" type="button"
														class="btn btn-success btn-sm" data-toggle="modal"
														ng-click="setOfficeRpt(data)"
														data-target="#updateOfficeRpt">Edit Report</button>

													<p ng-show="data.cl_flag == 1">Case is Listed In Court</p>
													<p ng-show="data.cl_flag == 2">Time Expired</p></td>


												<!-- <td class="col-md-1"><button id="view" type="submit" class="btn btn-sm btn-success"
							ng-click="Getfile(data)"  data-toggle="modal"> view
						</button></td> -->
											</tr>
										</tbody>
									</table>
								</div>
							</div>

							<div ng-show="officeReport.length <= 0" class="container-fluid">
								<div class="col-md-12">
									<table style="table-layout: fixed;" id="data-table"
										style="table-layout: fixed; width: 100%"
										class="table table-striped table-bordered">
										<thead>

											<tr>
												<th style="width: 10%;">Date</th>
												<!-- <th style="width: 30%;">User</th> -->
												<th style="width: 70%; overflow-x: auto;">Report</th>

												<th style="width: 20%;" class="col-md-1">Action</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-show="officeReport.length <= 0">
												<td colspan="4" style="text-align: center;">No Report
													Found for Edit</td>
											</tr>

											<tr ng-repeat="data in officeReport ">

												<td>{{data.ord_created | date:"dd/MM/yyyy hh:mm a"}}</td>
												<!--    <td>{{data.submittedBy.um_fullname}}</td> -->
												<td style="width: 50%; overflow-x: auto;"
													ng-bind-html="data.ord_remark"></td>
												<!-- <td>{{data.ord_remark}}</td> -->

												<td><button ng-show="data.cl_flag ==0" type="button"
														class="btn btn-success btn-sm" data-toggle="modal"
														ng-click="setOfficeRpt(data)"
														data-target="#updateOfficeRpt">Edit Report</button>
													<p ng-show="data.cl_flag == 1">Case is Listed In Court</p>
													<p ng-show="data.cl_flag == 2">Time Expired</p></td>


												<!-- <td class="col-md-1"><button id="view" type="submit" class="btn btn-sm btn-success"
							ng-click="Getfile(data)"  data-toggle="modal"> view
						</button></td> -->
											</tr>
										</tbody>
									</table>
								</div>
							</div>

							<div class="modal fade" id="addcaseefiling" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												<strong>Add Case Details To Efling</strong>
											</h4>
										</div>
										<%@ include file="../casefile/addCaseEfiling.jsp"%>
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
							<div class="modal fade" id="updateOfficeRpt" tabindex="-1"
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
										<%@ include file="../casefile/_update_officeRpt.jsp"%>
									</div>
								</div>
							</div>
							<div class="modal fade" id="viewFiles" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												<strong>Stage History</strong>
											</h4>
										</div>
										<%@ include file="../casefile/filelist.jsp"%>
									</div>
								</div>
							</div>
							<div class="modal fade" id="updateCaseType" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												<strong>Change Case Type</strong>
											</h4>
										</div>
										<%@ include file="../casefile/updateCasetype.jsp"%>
									</div>
								</div>
							</div>

							<div class="modal fade" id="caseAssignTo" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												<strong>Case Assign To</strong>
											</h4>
										</div>
										<%@ include file="../casefile/caseAssignTo.jsp"%>
									</div>
								</div>
							</div>

						</div>
					</div>

					<!-- end panel -->

					<!-- end col-12 -->
				</div>
			</div>
		</div>

		<!-- end row -->
</body>

<!-- ================== END PAGE LEVEL JS ================== -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileController.js?v=76"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script> --%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>

<script>
	$(document).ready(function() {
		$("#txtEditor").Editor();
		$("#txtEditor1").Editor();
		App.init();

	});
</script>

<script>
	document.addEventListener("DOMContentLoaded", function() {

		// Date picker sync (your existing code)
		document.addEventListener("change", function(e) {
			if (e.target.classList.contains("date-picker")) {
				const selectedDate = e.target.value;
				document.querySelectorAll(".date-picker").forEach(
						function(picker) {
							picker.value = selectedDate;
						});
			}
		});

		// Check All logic (FIXED)
		/* document.addEventListener("change", function (e) {
		  if (e.target.id === "checkAll") {
		    const checked = e.target.checked;
		    document.querySelectorAll(".c1").forEach(cb => {
		      cb.checked = checked;
		    });
		  }
		}); */

	});
</script>


<script>
	
</script>



<!-- <link href="editor.css" type="text/css" rel="stylesheet"/> -->

</html>