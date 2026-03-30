<%@ include file="../../content/header.jsp"%>
<!-- <script src="daterangepicker.js"></script> -->
<div id="content" class="content"
	ng-controller="fullBenchJudgmentOrderSearchCtrl">
	<!-- begin row -->
	<div class="row">
		<div class="col-md-11">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">Full Bench Judgment/Order Search</h4>
				</div>
				<div class="panel-body">
					<div>
						<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover"
							class="p-20" width="100%">
							<thead>
								<tr>

									<th>Select Hon'ble Judges</th>
									<th style="width: 16%">
									<select class="form-control" ng-model="judgeType">
										<option value="all">All Judges</option>
										<option value="sitting">All Sitting Judges</option>
									</select></th>

									<th class="m-l-25">First Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="firstJudge" name="firstJudge"
										ng-model="firstJudge" required>
									</select></th>

									<th class="m-l-25">Second Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="secondJudge" name="secondJudge"
										ng-model="secondJudge" required>
									</select></th>

									<th class="m-l-25">Third Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="thirdJudge" name="thirdJudge"
										ng-model="thirdJudge" required>
									</select></th>


								</tr>

								<tr>

									<th class="m-l-25">Judgment Type</th>
									<th style="width: 16%">
									<select class="form-control" ng-model="judgmentType">
										<option value="all">All</option>
										<option value="reportable">All Reportable</option>
									</select></th>

									<th style="border: 0px">Judgment/Order Date From</th>
									<th style="border: 0px">
										<div class="row" style="width: 125px">
											<div class="input-group date">
												<input type="text" class="form-control"
													datepicker-popup="{{format}}" ng-model="model.fromdate"
													is-open="opened1" datepicker-options="dateOptions"
													ng-disabled="true" /> <span class="input-group-addon"
													ng-click="open($event,'opened1')"><i
													class="glyphicon glyphicon-calendar"></i></span>
											</div>
										</div>
									</th>
									<th style="border: 0px">To</th>
									<th><div class="row" style="width: 125px">
											<div class="input-group date">
												<input type="text" class="form-control"
													datepicker-popup="{{format}}" ng-model="model.todate"
													is-open="opened2" datepicker-options="dateOptions"
													ng-disabled="true" /> <span class="input-group-addon"
													ng-click="open($event,'opened2')"> <i
													class="glyphicon glyphicon-calendar"></i></span>
											</div></th>


									<th style="border: 0px">
										<button type="submit" class="btn btn-sm btn-success"
											ng-click="getsearchByfullBenchJudgmentOrder()">
											<!-- <span class="glyphicon glyphicon-plus-sign"></span> -->
											Submit

										</button>

									</th>
									<th style="border: 0px"><button style="float: left"
											type="button" class="btn btn-primary btn-sm pull-right"
											name="ExportToCsv1" ng-csv="dailyreportListData"
											csv-header="['Name of Person','Date', 'Time Worked(HH:MM)','Entries Made','Entries Correct','Entries In Error','% of Accuracy']"
											filename="test.csv" data-toggle="modal">
											<i class="fa fa-edit"></i> Csv
										</button></th>
									<th style="border: 0px"><button style="float: left"
											type="button" class="btn btn-primary btn-sm pull-right"
											name="ExportToExcel1" ng-csv="ReportData"
											filename="test23.xlsx" data-toggle="modal">
											<i class="fa fa-edit"></i> Excel
										</button></th>
									<!-- <th style="border: 0px"><button style="float: left"
											type="button"
											onClick="$('#data-table').tableExport({type:'pdf',escape:'false'});"
											class="btn btn-primary btn-sm pull-right">
											<i class="fa fa-edit"></i>pdf
										</button></th> -->
								</tr>

							</thead>
						</table>
					</div>

					<table id="data-table" st-table="" st-safe-src="masterdata"
						class="table table-striped table-bordered nowrap table-hover"
						width="100%">
						<tr>
							<th>Case No</th>
							<th>Case Year</th>
							<th>First Petetioner</th>
							<th>First Respondant</th>
							<th>Case Type</th>
							<th>Judgement Date</th>
							<th>Action</th>

						</tr>
						<tbody>
							<tr ng-repeat="data in displayedCollection " class="odd gradeX">

								<td>{{data.parameter1}}</td>
								<td>{{data.parameter2}}</td>
								<td>{{data.parameter3}}</td>
								<td>{{data.parameter4}}</td>
								<td>{{data.parameter5}}</td>
								<td>{{data.parameter6 | date:'dd/MM/yyyy'}}</td>					
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/searchByFullBenchJudgmentOrder.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-csv.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-sanitize.min.js"></script>




