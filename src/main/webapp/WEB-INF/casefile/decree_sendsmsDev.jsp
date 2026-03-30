<%@ include file="../content/header2.jsp"%>

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
						<h4 class="panel-title">Decree Send Sms</h4>
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

							<table id="data-table" class="table table-striped table-bordered">
								<thead>
									<tr class=" ">
										<th style="width: 1%;">Sr.No.</th>
										<th>AOR No</th>
										<th>Advocate Name</th>
										<th>Mobile</th>
										<th>Valid till date</th>
										<th>Select</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="row in advFileList  track by $index"
										class="odd gradeX"  ng-if="row.aor && row.name ">
										
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
										<td>
										<button class="btn btn-sm btn-success"
												ng-click="openSmsHistory()">SMS-History</button>
										</td>
										<td></td>
										<td></td>
										<td></td>

										<!--     <input class="form-control form-control-sm date-picker"
               type="date"
               ng-model="commonTillDate"
               ng-change="applyTillDateToSelected()"
               style="width: 140px; height: 40px"> -->


										<td ng-show="advFileList && advFileList.length > 0"><input
											type="checkbox" ng-model="selectAll" ng-change="toggleAll()">
											<label>Select All</label></td>
										<td>
											<button ng-click="sendAllSms()"
												ng-show="advFileList && advFileList.length > 0"
												ng-disabled="!hasAnySelected()"
												class="btn btn-sm btn-success">Send All SMS</button>
										</td>

									</tr>

									<!-- <tr ng-show="!advFileList || advFileList.length === 0">
    <td colspan="7" style="text-align:center;">
        No Record found!!
    </td>
</tr> -->

								</tbody>
							</table>

							<!-- /*   ********************************************************************************************************************************/
 -->
							<!-- SMS History Modal -->
							<div class="modal fade" id="smsHistoryModal" tabindex="-1"
								role="dialog">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">

										<div class="modal-header">
											<h4 class="modal-title">SMS Log History</h4>
											<button type="button" class="close" data-dismiss="modal">
   								 			<span>&Chi;</span>
											</button>

										</div>

										<div class="modal-body">

											<table class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Sr No</th>
														<th>Mobile</th>
														<th>SMS Text</th>
														<th>Send Date</th>
														<th>Status</th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="sms in smsLogList track by $index">
														<td>{{$index+1}}</td>
														<td>{{sms.dslMobile}}</td>
														<td>{{sms.smsText}}</td>
														<td>{{sms.dslSendDate | date:'dd-MM-yyyy HH:mm'}}</td>
														<td><span ng-if="sms.dslSmsStatus == true"
															class="label label-success">Sent</span> <span
															ng-if="sms.dslSmsStatus == false"
															class="label label-danger">Failed</span></td>
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
						<div class="modal fade" id="viewFiles" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileController.js?v=54"></script>

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