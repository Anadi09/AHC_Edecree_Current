<jsp:include page="../content/header2.jsp"></jsp:include>
<div id="content" class="content">
	<div class="container-fluid" ng-controller="OLReportController">

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
					<h4 class="panel-title">OL Reports</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						 <table id="data-table" st-table="displayedCollection"st-safe-src="masterdata"class="table table-striped table-bordered nowrap table-hover" width="100%">
								<thead>
									<tr>
										<td >
											<input type="number" class="form-control" placeholder="Enter Application No" ng-model ="olrsearch.ol_no">
										</td>
										<td >
											<input type="number" ng-minlength="4" ng-maxlength="4"  class="form-control" placeholder="Enter Application Year" ng-model ="olrsearch.ol_year">
										</td>
										<td style="width: 70mm;">
										
											 <input type="text" class="form-control" datepicker-popup="{{format}}" name="stageDate" ng-model="olrsearch.ol_created" required is-open="stageDate" ng-disabled="true" max-date="maxDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			  <span class="input-group-addon" ng-click="open($event,'stageDate')"><i class="glyphicon glyphicon-calendar"></i></span>
				            			  
				            			  
										</td>
										<td>
											<button id="search" type="submit" class="btn btn-primary btn-sm" ng-click="searchOLR()">Search</button>
										</td>
									</tr>
								</thead>
							</table>
					
					
						<table id="data-table" class="table table-striped table-bordered" ng-init="getList()">
							<thead>
								<tr>
									<th style="width: 0.2%;">Sr.No</th>
									<th>Application No</th>
									<th>Application Year</th>
									<th>Created</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="row in olreports.reverse()" class="odd gradeX">
									<td>{{$index+1}}</td>
									<td>{{row.ol_no}}</td>
									<td>{{row.ol_year}}</td>
									<td>{{row.ol_created | date : 'dd/MM/yyyy'}}</td>
									<td> 
									 <button type="button" class="btn btn-primary btn-sm" ng-click="viewFile(row.ol_id)">View</button> 
									<span ng-if =row.read_status=="1">
									&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary btn-sm" ng-click="remove(row.ol_id)">Remove</button>
									</span>
									<span ng-if =row.read_status=="0">&nbsp&nbsp&nbsp&nbsp&nbsp
									<button class="btn btn-success btn-sm" ng-click="resetStatus(row.ol_id)">Reset Status</button></span>
									
									</td>
									
								</tr>
								<tr ng-show="olreports.length==0">
									<td colspan="10"><div class="alert alert-danger">No Records Found</div></td>
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

</div>
<!-- end row -->
</body>




<!-- ================== END PAGE LEVEL JS ================== -->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/OLReportController.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();

	});
</script>



</html>