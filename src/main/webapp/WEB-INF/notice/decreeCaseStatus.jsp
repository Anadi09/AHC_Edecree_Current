<jsp:include page="../content/header2.jsp"></jsp:include>
<%@ page import="com.eDecree.model.User"%>

<%
User user = null;
if (session.getAttribute("USER") != null)
	user = (User) session.getAttribute("USER");

String role = user.getUserroles().get(0).getLk().getLk_longname();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!-- DataTables CSS -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">

<style>
/* Page background */
body {
	background: #f4f6f9;
}

/* Panel redesign */
.panel {
	border-radius: 10px;
	border: none;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}

.panel-heading {
	background: linear-gradient(135deg, #4e73df, #224abe);
	color: white !important;
	border-radius: 10px 10px 0 0;
	padding: 15px;
}

.panel-title {
	font-size: 18px;
	font-weight: 600;
}

/* Table styling */
#decreeTable {
	border-radius: 8px;
	overflow: hidden;
}

#decreeTable thead {
	background: #4e73df;
	color: white;
}

#decreeTable th {
	text-align: center;
	font-weight: 900;
	color: white;
}

#decreeTable td {
	vertical-align: middle;
	text-align: center;
}

/* Hover effect */
#decreeTable tbody tr {
	transition: 0.2s ease;
}

#decreeTable tbody tr:hover {
	background-color: #eef2ff;
	transform: scale(1.01);
}

/* Serial column highlight */
#decreeTable td:first-child {
	font-weight: bold;
	color: #4e73df;
}

/* Search box styling */
.dataTables_filter input {
	border-radius: 20px !important;
	padding: 6px 12px;
	border: 1px solid #ccc;
}

/* Pagination styling */
.dataTables_wrapper .dataTables_paginate .paginate_button {
	border-radius: 50% !important;
}

/* Subtle animation */
.panel {
	animation: fadeIn 0.4s ease-in;
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(10px);
}

to {
	opacity: 1;
	transform: translateY(0);
}
}
</style>


<!-- jQuery UI (fix for sortable error) -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>

<!-- DataTables -->
<script
	src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<!-- Your app -->
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>



<!-- ===================================== 	JAVA FULLSTACK DEVELOPER VIJAY CHAURASIYA ================================== -->



<div id="content" class="content">
	<div class="container-fluid" ng-controller="NoticeController">


		<div class="row">
			<!-- begin col-12 -->
			<div class="col-md-12">
				<!-- begin panel -->
				<div class="panel panel-inverse">
					<div class="panel-heading">
						<div class="panel-heading-btn">
							<a href="javascript:;"
								class="btn btn-xs btn-icon btn-circle btn-default"
								data-click="panel-expand"><i class="fa fa-expand"></i></a>
						</div>
						<h4 class="panel-title">Decree Case Status</h4>
					</div>
					<div class="panel-body">

						<table id="decreeTable" class="table table-bordered table-striped">
							<thead>
								<tr class="text-white">
									<th>Sr. No</th>
									<th>Case Type</th>
									<th>Case No</th>
									<th>Case Year</th>
									<th>Date</th>
									<th>User Name</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="row" items="${decreeData}" varStatus="status">
									<tr>
										<td>${status.index + 1}</td>

										<td><span class="label label-primary">${row[2]}</span></td>

										<td><strong>${row[3]}</strong></td>

										<td>${row[4]}</td>

										<td><span style="color: #F54927; font-weight: 500;">
												${row[6]} </span></td>

										<td><i class="fa fa-user"></i> ${row[9]}</td>
									</tr>
								</c:forEach>

								<c:if test="${empty decreeData}">
									<tr>
										<td colspan="6" style="text-align: center;">No Records
											Found</td>
									</tr>
								</c:if>
							</tbody>
						</table>

					</div>
				</div>
				<!-- end panel -->
			</div>
			<!-- end col-12 -->
		</div>
		<!-- ===================================== 	JAVA FULLSTACK DEVELOPER VIJAY CHAURASIYA ================================== -->

	</div>
</div>

</div>

</body>
<%
//if(role.equals("Advocate") || role.equals("InPerson")){
%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/notice/NoticeController.js?v=2"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/digiSign/signer.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/digiSign/conf.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>

<script>
	$(document).ready(function() {

		$('#decreeTable').DataTable({
			paging : true,
			searching : true,
			ordering : true,
			pageLength : 10,
			lengthMenu : [ 5, 10, 25, 50 ],
			responsive : true,
			language : {
				search : "",
				searchPlaceholder : "🔍 Search cases...",
				lengthMenu : "Show _MENU_ entries",
				zeroRecords : "No matching records found"
			}
		});

	});
</script>




<script>
	$(document).ready(function() {
		App.init();

	});
</script>
</html>