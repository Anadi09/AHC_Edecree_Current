<%@ include file="../content/header2.jsp"%>
<div id="content" class="content">
	<div class="container-fluid" ng-controller="ReportController">

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
					<h4 class="panel-title">Download Files Report</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive">

						<table id="data-table" ng-init="getData(pageno)" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Username</th>
									<th>Case Type</th>
									<th>Case No</th>
									<th>Case Year</th>
									<th>Amount</th>
									<th>Date</th>
									<th>IP Address</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="downloadhistory.length <= 0">
									<td colspan="8" style="text-align:center;">Loading new data!!</td>
								</tr>
								<tr dir-paginate="report in downloadhistory | itemsPerPage:itemsPerPage " total-items="total_count" pagination-id="id2">
									 <td>{{report.userDetail.um_fullname}}</td>
									 <td>{{report.caseFileDetail.caseType.ct_name}}</td>
									 <td>{{report.caseFileDetail.fd_case_no}}</td>
		                             <td>{{report.caseFileDetail.fd_case_year}}</td>
		                             <td>{{report.dr_amount}}</td>
		                             <td>{{report.dr_cr_date | date:"dd-MM-yyyy"}}</td>
		                             <td>{{report.dr_ip_address}}</td>                     
		                             <td><button type="button" class="btn btn-success" data-toggle="modal" ng-click="setFiles(report)" data-target="#viewFiles">View Files</button></td>                
								</tr>
							</tbody>
						</table>
						<dir-pagination-controls
							max-size="8"
							direction-links="true"
							boundary-links="true" 
							pagination-id="id2"
							on-page-change="getData(newPageNumber)" >
						</dir-pagination-controls>
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
									<strong>Downloaded Files</strong>
								</h4>
							</div>
							<%@ include file="../reports/_viewfiles.jsp"%>
						</div>
					</div>
				</div>
			</div>

			<!-- end panel -->

			<!-- end col-12 -->
		</div>
	</div>
</div>

</div>
<!-- end row -->
</body>


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/ReportController.js"></script>	
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/angularJs/dirPagination.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();
	});
</script>



</html>