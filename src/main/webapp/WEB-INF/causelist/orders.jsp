<jsp:include page="../content/header2.jsp"></jsp:include>
<div id="content" class="content">
	<div class="container-fluid" ng-controller="CourtOrderController">
		<div class="row">
			<!-- begin col-12 -->
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
					</div>
					<h4 class="panel-title">Bulk Orders</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<button type="button" class="btn btn-primary btn-sm pull-right" data-toggle="modal" data-target="#uploadDocument">Upload</button>
						<table id="data-table" class="table table-striped table-bordered" ng-init="getList()">
							<thead>
								<tr>
									<th>SNO</th>
									<th>Created</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="row in courtorders " class="odd gradeX">
									<td>{{$index + 1}}</td>
									<td>{{row.co_cr_date | date : 'dd/MM/yyyy'}}</td>
									<td><button class="btn btn-success btn-sm"
											ng-click="viewFile(row.co_id)">View</button>
									</td>
								</tr>
								<tr ng-show="courtorders.length==0">
									<td colspan="10"><div class="alert alert-danger">No
											Records Found</div></td>
								</tr>
							</tbody>
						</table>
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
										<strong>Upload Report</strong>
									</h4>
								</div>
								<%@ include file="_upload_order.jsp"%>
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

</div>
<!-- end row -->
</body>




<!-- ================== END PAGE LEVEL JS ================== -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/CourtOrderController.js"></script>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();

	});
</script>



</html>