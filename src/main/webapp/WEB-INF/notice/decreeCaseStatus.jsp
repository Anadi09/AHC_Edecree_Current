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
						<h4 class="panel-title">Decree Case Status </h4>
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
												class="btn btn-primary btn-sm" ng-click="searchCaseStatus()">Search</button>
										</td>
									</tr>

								</thead>
							</table>
							<div ng-if="showStatus" class="table-responsive">
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>Stage</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <tr ng-repeat="s in decreeCaseStatus">
                <td>{{s.stageName}}</td>
                <td>{{s.stageDate | date:'dd-MM-yyyy HH:mm'}}</td>
            </tr>
        </tbody>
    </table>
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileController.js?v=13"></script>

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