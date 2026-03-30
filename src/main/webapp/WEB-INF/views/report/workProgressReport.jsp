<%@ include file="../../content/header.jsp"%>

<script	src="https://cdnjs.cloudflare.com/ajax/libs/ng-csv/0.3.6/ng-csv.js"></script>
<script src="https://code.angularjs.org/1.2.20/angular-sanitize.min.js"></script>

<div id="content" class="content" ng-controller="workProgressReportCtrl">
		<!-- begin row -->
	
		<div class="row">
		<div class="col-md-11">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">Critical Report</h4>
				</div>
				<div class="panel-body">
					<div>
					<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover"
							class="p-20" width="100%">
							<tr>
							<th class="col-md-2">Date From</th>
							<th class="col-md-2">Date To</th>
							<th class="col-md-2"></th>
							</tr>
							<tr>
							<td>
							<div class="row">
											<div class="input-group date">
										<input type="text" class="form-control" datepicker-popup="{{format}}" name="fromDate" ng-model="model.fromDate" required is-open="fromDate" max-date="maxDate"  datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			<span class="input-group-addon" ng-click="open($event,'fromDate')"><i class="glyphicon glyphicon-calendar"></i></span>											</div>
							</div>
							</td>
							<td>
							<div class="row">
											<div class="input-group date">
											<input type="text" class="form-control" datepicker-popup="{{format}}" name="toDate" ng-model="model.toDate" required is-open="toDate" datepicker-options="dateOptions" date-disabled="disabled(date, mode)" ng-required="true" close-text="Close" show-button-bar="false" />
				            			<span class="input-group-addon" ng-click="open($event,'toDate')"><i class="glyphicon glyphicon-calendar"></i></span>											</div>
											</div>
							</div>
							</td>
							<td>
							<button type="submit" class="btn btn-sm btn-success" ng-disabled="caseTypeReport.$invalid" ng-click="getWorkProgressReportData()" >
                              				  <span class="glyphicon glyphicon-plus-sign" ></span>Search here</button>
							</td>
							</tr>
					</table>
											</div>

					<table id="data-table" st-table="" st-safe-src="masterdata"
						class="table table-striped table-bordered nowrap table-hover"
						width="100%">
						<tr>
							<th>Date </th>
							<th>Openning Balance</th>
							<th>Bundles Received</th>
							<th>CaseFiles Received</th>
							<th>No of Casefiles Scaned</th>
							<th>MetaData Entry Done</th>
							<th>Checker Entry Done</th>
							<th>Closing Balance</th>
							<th>No. of files Verified by court Offical</th>
							<th>Files Returns to court</th>
							<th>CumulativeFiles Received</th>
							<th>CumulativeFiles Completed</th>
							<th>CumulativeFiles Verified court offical</th>
							<th>CumulativeFiles Return to court</th>
						</tr>
						<tbody>
							<tr ng-repeat="data in displayedCollection " class="odd gradeX">
								
								<td>{{data.value1}}</td>
								<td>{{data.value2}}</td>
								<td>{{data.value3}}</td>
								<td>{{data.value4}}</td>
								<td>{{data.value5}}</td>
								<td>{{data.value6}}</td>
								<td>{{data.value7}}</td>
								<td>{{data.value8}}</td>
								<td>{{data.value9}}</td>
								<td>{{data.value10}}</td>
								<td>{{data.value11}}</td>
								<td>{{data.value12}}</td>
								<td>{{data.value13}}</td>
							    <td>{{data.value14}}</td>
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/workProgressReport.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>










