<%@ include file="../content/header2.jsp"%>
<html>

<style>
.animated-btn {
	animation: pulse 1.5s infinite;
}

.table-responsive {
	overflow: visible !important;
}

.panel, .panel-body {
	overflow: visible !important;
}

.dropdown-menu {
	position: absolute !important;
	z-index: 999999 !important;
}

@
keyframes pulse { 0% {
	transform: scale(1);
}

50
%
{
transform
:
scale(
1.08
);
}
100
%
{
transform
:
scale(
1
);
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

.table {
	border-collapse: separate !important;
	border-spacing: 0 30px;
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
						<h4 class="panel-title">View Case File Details</h4>
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
											ng-model="search.fd_case_type"
											ng-options="caseType.ct_id as caseType.labelandname for caseType in caseTypes  | orderBy:'ct_label' | filter:{ct_status:1}">
												<option value="">Select Case Type</option>
										</select></td>

										<td><input type="text" class="form-control"
											placeholder="Case No" ng-model="search.fd_case_no"></td>
										<td><input type="text" class="form-control"
											placeholder="Case Year" ng-model="search.fd_case_year">
										</td>
										<td>
											<button id="search" type="submit"
												class="btn btn-primary btn-sm" ng-click="searchCasseStatus()">Search</button>
										</td>
									</tr>

								</thead>
							</table>

							<table id="data-table" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th style="width: 1%;">Sr.No.</th>
										<th>Case Type</th>
										<th>Case No</th>
										<th>Case Year</th>
										<th>Exist in Efiling</th>
										<th>Action</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="row in caseFileList" class="odd gradeX">
										<td>{{$index+1}}</td>
										<td>{{row.caseType.ct_name}}</td>
										<td>{{row.fd_case_no}}</td>
										<td>{{row.fd_case_year}}</td>
										<td align="center">{{dmsCaseData.status}}</td>
										<td>
											<% if(role.equals("DMSAdmin")) {%>
											<button class="btn btn-primary  btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="viewDetail(row.fd_id)">View Detail</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="genrateDecree(row.fd_id)">Generate Decree</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="downloadFiles(row.fd_id)">Download</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="notice(row.fd_id)">Notice</button>

											<button class="btn btn-primary  btn-sm"
												ng-click="setModel(row)" data-toggle="modal"
												data-target="#updateCaseType">Change Case Type</button>
											<button type="button" class="btn btn-primary  btn-sm"
												data-toggle="modal" ng-click="setModel(row)"
												data-target="#uploadDocument">Upload</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="searchreport(row.fd_id)">Office Reports</button>

											<button class="btn btn-primary  btn-sm"
												ng-click="setModel(row)" data-toggle="modal"
												data-target="#caseAssignTo">Assign To</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="setModel(row)" data-toggle="modal"
												data-target="#addcaseefiling">AddCaseToEfiling</button> <% }%>

											<% if(role.equals("DECREE CREATOR")) {%> <!-- <button class="btn btn-primary btn-sm" ng-click="createNotice(row.fd_id)">Create Notice</button> -->
											<button class="btn btn-primary  btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button> <!--   <button class="btn btn-primary  btn-sm" ng-click="genrateDecree(row.fd_id)">Generate Decree</button> -->
											<!--   <button class="btn btn-primary btn-sm" ng-click="genrateDecreef1(row.fd_id)">View Doc</button> -->
											<button type="button" class="btn btn-primary  btn-sm"
												data-toggle="modal" ng-click="setModel(row)"
												data-target="#uploadDocument">Upload</button> <% }%> <% if(role.equals("Review_Officer") || role.equals("Assistant Review Officer")) {%>
											<button type="button" class="btn btn-primary  btn-sm"
												data-toggle="modal" ng-click="setModel(row)"
												data-target="#uploadDocument">Upload</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="setModel(row)" data-toggle="modal"
												data-target="#updateCaseType">Change Case Type</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="searchreport(row.fd_id)">Office Reports</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button> <% }%> <% if(role.equals("Stamp_Reporter") || role.equals("REGISTRAR") || role.equals("REGISTRAR (J)") || role.equals("JOINT REGISTRAR") || role.equals("JOINT REGISTRAR (J)")) {%>
											<button type="button" class="btn btn-primary  btn-sm"
												data-toggle="modal" ng-click="setModel(row)"
												data-target="#uploadDocument">Upload</button>
											<button class="btn btn-primary  btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button> <% }%> <% if(role.equals("PS")) {%>
											<button class="btn btn-primary  btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button> <% }%> <% if(role.equals("Judge")) {%>
											<button class="btn btn-primary  btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button> <% }%>

										</td>
										<td>
											<div class="dropdown">
												<button class="btn btn-primary btn-sm dropdown-toggle"
													type="button" data-toggle="dropdown">
													Select Doc Type <span class="caret"></span>
												</button>

												<ul class="dropdown-menu"
													style="min-width: 120px; width: auto;">
													<li><a href=""
														ng-click="selectDoc('DECREE', row.fd_id)">Generate
															Decree</a></li>
													<li><a href=""
														ng-click="selectDoc('NOTICE', row.fd_id)">Generate
															Notice</a></li>
													<li><a href=""
														ng-click="selectDoc('ORDER', row.fd_id)">Generate
															Testamentary</a></li>
													<li><a href="" ng-click="selectDoc('MISC', row.fd_id)">Generate
															Miscellaneous</a></li>
												</ul>
											</div>
										</td>
									</tr>
									<tr ng-show="showStatus">
										<td colspan="6" style="text-align: center;">No Record
											found!!</td>
									</tr>


									<tr style="margin-top: 10px"></tr>
									<tr style="margin-top: 10px"></tr>
									<tr style="margin-top: 10px"></tr>
									<tr style="margin-top: 10px"></tr>
									<tr style="margin-top: 10px"></tr>
								</tbody>
							</table>
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
													class="btn btn-primary  btn-sm" data-toggle="modal"
													ng-click="setOfficeRpt(data)"
													data-target="#updateOfficeRpt">Edit Report</button>

												<p ng-show="data.cl_flag == 1">Case is Listed In Court</p>
												<p ng-show="data.cl_flag == 2">Time Expired</p></td>


											<!-- <td class="col-md-1"><button id="view" type="submit" class="btn btn-sm btn-primary "
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
													class="btn btn-primary  btn-sm" data-toggle="modal"
													ng-click="setOfficeRpt(data)"
													data-target="#updateOfficeRpt">Edit Report</button>
												<p ng-show="data.cl_flag == 1">Case is Listed In Court</p>
												<p ng-show="data.cl_flag == 2">Time Expired</p></td>


											<!-- <td class="col-md-1"><button id="view" type="submit" class="btn btn-sm btn-primary "
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileController.js?v=14"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

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

<!-- <link href="editor.css" type="text/css" rel="stylesheet"/> -->

</html>