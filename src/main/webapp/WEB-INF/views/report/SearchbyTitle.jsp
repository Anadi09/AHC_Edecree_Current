<%@ include file="../../content/header.jsp"%>
<!-- <script src="daterangepicker.js"></script> -->
<div id="content" class="content" ng-controller="searchbyTitle">
	<!-- begin row -->
	<div class="row">
		<div class="col-md-11">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">Search By Title</h4>
				</div>
				<div class="panel-body">
					<div>
						<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover"
							class="p-20" width="100%">
							
							
							<thead>
							<tr>
							<th>Search for</th>
							<th>By name(Full or Part)</th>
							<th>Delivered at (Main Seat/Bench</th>
							<th>Date From</th>
							<th>To</th>
							<th colspan="3"></th>
							</tr>
							</thead>
							<tr>
							<td><select class="form-control"
										ng-model="model.counsel">
											<option value="6">Petitioner</option>
											<option value="7">Respondent</option>
											<option value="">Don't Know</option>
									</select></td>
									<td><input type="text" class="form-control" name="counsel" ng-model="model.counselName"></td>
							<td><select
										ng-options="bcd.lk_id as bcd.lk_longname for bcd in branchDataList"
										class="form-control" id="benchCodeId" name="benchCodeId"
										ng-model="model.benchCodeId" required>
									</select></td>
							<td><div class="row" style="width: 125px">
											<div class="input-group date">
												<input type="text" class="form-control"
													datepicker-popup="{{format}}" ng-model="model.fromDate"
													is-open="opened1" datepicker-options="dateOptions"
													ng-disabled="true" /> <span class="input-group-addon"
													ng-click="open($event,'opened1')"><i
													class="glyphicon glyphicon-calendar"></i></span>
											</div>
										</div></td>
							<td><div class="row" style="width: 125px">
											<div class="input-group date">
												<input type="text" class="form-control"
													datepicker-popup="{{format}}" ng-model="model.toDate"
													is-open="opened2" datepicker-options="dateOptions"
													ng-disabled="true" /> <span class="input-group-addon"
													ng-click="open($event,'opened2')"> <i
													class="glyphicon glyphicon-calendar"></i></span>
											</div></td>
							
							<td><button type="submit" class="btn btn-sm btn-success"
											ng-click="getsearchByTitle()">
											<span class="glyphicon glyphicon-plus-sign"></span>Search
											here
										</button></td>
							<td><button style="float: left"
											type="button" class="btn btn-primary btn-sm pull-right"
											name="ExportToCsv1" ng-csv="masterdata" filename="test.csv"
											data-toggle="modal">
											<i class="fa fa-edit"></i> Csv
										</button></td>
										<td><button style="float: left"
											type="button" class="btn btn-primary btn-sm pull-right"
											name="ExportToExcel1" ng-csv="masterdata"
											filename="test23.xlsx" data-toggle="modal">
											<i class="fa fa-edit"></i> Excel
										</button></td>
							</tr>
							
							
						</table>
					</div>

					 <table id="data-table" st-table="" st-safe-src="masterdata"
						class="table table-striped table-bordered nowrap table-hover"
						width="100%">
						<tr>
							<th>Case No</th>
							<th>Case Year</th>
							<th>First Petitioner</th>
							<th>First Respondent</th>
							<th>Case Type</th>
							<th>Judgement Date</th>
							<th>Action</th>
						</tr>
						<tbody>
							<tr ng-repeat="data in displayedCollection " class="odd gradeX">
								
								<td>{{data.caseNo}}</td>
								<td>{{data.caseYear}}</td>
								<td>{{data.firstPetitioner}}</td>
								<td>{{data.firstRespondent}}</td>
								<td>{{data.caseType}}</td>
								<td>{{data.judgementDate | date:'dd/MM/yyyy'}}</td>					
								<td>
									<a target="_new" href="${pageContext.request.contextPath}/casefile/dataentry/{{data.fileId}}" class="btn btn-info btn-xs" ><span class="glyphicon" aria-hidden="true"></span>View</a>
								</td>
							</tr>
						</tbody> 
						<!-- <tfoot>
							<tr>
								<td colspan="6" class="text-center">
									<div st-pagination="6" st-items-by-page="10"
										st-displayed-pages="6"></div>
								</td>
							</tr>
						</tfoot> -->
					</table>
				</div>
			</div>
		</div>

	</div>
</div>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/searchbyTitle.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-csv.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-sanitize.min.js"></script>

<!-- <script>
	$(function() {
		$(".btn").click(function() {
			$(this).button('loading').delay(1000).queue(function() {
				$(this).button('reset');
				$(this).dequeue();
			});
		});
	});

	$(document).ready(function() {
		App.init();
		Notification.init();
	});
</script>
 -->