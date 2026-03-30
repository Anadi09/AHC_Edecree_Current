<%@ include file="../content/header2.jsp"%>
<div id="content" class="content">
	<div class="container-fluid" ng-controller="SearchController" ng-init="searchmodel.courtType='h';getHighCourtCaseTypes();getDistricts();">

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
					<h4 class="panel-title">Search By Impugned Order</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered nowrap table-hover">
							<thead>
								<tr>
									<td width="20%">
										<select class="form-control" ng-change="getIOCaseTypes()"  ng-model="searchmodel.courtType">
											<option value="h">High Court</option>
											<option value="l">Lower Court</option>
										</select>
									</td>
									<td width="20%"><select class="form-control"
										ng-model="searchmodel.caseTypeId"
										ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypes  | orderBy:'ct_name'">
											<option value="">Select Case Type</option>
										</select>
									</td>
									<td><input type="text" class="form-control"
										placeholder="Case No" ng-model="searchmodel.caseNo">
									</td>
									<td><input type="text" class="form-control"
										placeholder="Case Year" ng-model="searchmodel.caseYear">
									</td>
									<td><input type="text" class="form-control"
										placeholder="CNR No." ng-model="searchmodel.cnrNo">
									</td>
									<td><select class="form-control"
										ng-model="searchmodel.district"
										ng-options="district.dt_id as district.dt_name for district in districts  | orderBy:'dt_name'">
											<option value="">Select District</option>
										</select>
									</td>
									<td>
										<button id="search" type="submit"
											class="btn btn-primary btn-sm" ng-click="searchByImpugned(1)">Search</button>
									</td>
								</tr>

							</thead>
						</table>
						Total Records Found : <b>{{total_count}}</b>
						<table id="data-table" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>Case Type</th>
									<th>Case No</th>
									<th>Case Year</th>
									<th>First Petitioner</th>
									<th>First Respondent</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="caseFileDetails.length <= 0">
									<td colspan="6" style="text-align:center;">No data found!!</td>
								</tr>
								<tr dir-paginate="casefile in caseFileDetails | itemsPerPage:itemsPerPage " total-items="total_count" pagination-id="id2">
									 <td>{{casefile.caseType.ct_name}}</td>
									 <td>{{casefile.fd_case_no}}</td>
		                             <td>{{casefile.fd_case_year}}</td>
		                             <td>{{casefile.petitioners[0].pt_name}}</td>
		                             <td>{{casefile.respondents[0].rt_name}}</td>
		                             <td><button class="btn btn-success btn-sm"
											ng-click="viewCaseFile(casefile.fd_id)">View</button></td>
								</tr>
							</tbody>
						</table>
						<dir-pagination-controls
							max-size="8"
							direction-links="true"
							boundary-links="true" 
							pagination-id="id2"
							on-page-change="searchByImpugned(newPageNumber)" >
						</dir-pagination-controls>
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


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/SearchController.js"></script>	
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