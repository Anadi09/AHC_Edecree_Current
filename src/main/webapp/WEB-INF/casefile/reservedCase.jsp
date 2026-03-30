<jsp:include page="../content/header2.jsp"></jsp:include>
<div id="content" class="content">
	<div class="container-fluid" ng-controller="ReservedCaseController">

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
					<h4 class="panel-title">Reserved Case List</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<!--  <table id="data-table" st-table="displayedCollection"st-safe-src="masterdata"class="table table-striped table-bordered nowrap table-hover" width="100%">
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
							</table> -->
					
					
						<table id="data-table" class="table table-striped table-bordered" ng-init="getList()">
							<thead>
								<tr>
									<th style="width: 0.2%;">Sr.<br>No.</th>
									<th>Case Type</th>
									<th style="width: 6%;">Case No.</th>
									<th style="width: 6%;">Case Year</th>
									<th style="text-align:center;width: 30%">Petitioner vs Respondent</th>
									<th style="text-align:center;width: 15%">Petitioner Counsel</th>
									<th style="text-align:center;width: 15%">Respondent Counsel</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="row in reservedCase" class="odd gradeX">
									<td>{{$index+1}}</td>
									<td>{{row.caseType.ct_label}}</td>
									<td>{{row.fd_case_no}}</td>
									<td>{{row.fd_case_year}}</td>
									<td style="text-align:center">
										<span ng-repeat="pet in row.petitioners" ng-if="pet.pt_sequence=='1'">
										{{pet.pt_name}}
										</span> <br/> vs <br/>
										 <span ng-repeat="ret in row.respondents" ng-if="ret.rt_sequence==1">
										{{ret.rt_name}}
										</span>
  									</td>
									
									<td style="text-align:center">
										<span ng-repeat="petc in row.pCounsels">
										{{petc.pc_name}}
  									</td>
									<td style="text-align:center">
										<span ng-repeat="retc in rCounsels">
										{{rec.rc_name}}
  									</td>
									<td> 
									 <button type="button" class="btn btn-primary btn-sm" ng-click="viewCaseFile(row.fd_id)">View</button> 
									<span>
									&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="button" class="btn btn-primary btn-sm" ng-click="remove(row.fd_id)">Remove</button>
									</span>
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/ReservedCaseController.js"></script>
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