<%@ include file="../../content/header.jsp"%>
<!-- <script src="daterangepicker.js"></script> -->
<div id="content" class="content" ng-controller="searchByJudgementDateCtrl">
	<!-- begin row -->
	<div class="row">
		<div class="col-md-11">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">Search By Judgment Date</h4>
				</div>
				<div class="panel-body">
					<div>
						<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover"
							class="p-20" width="100%">
							<thead>
								<tr>
									<th class="m-l-25">Bench Code</th>
									<th style="width: 16%"><select
										ng-options="bcd.lk_id as bcd.lk_longname for bcd in branchDataList"
										class="form-control" id="benchCodeId" name="benchCodeId"
										ng-model="model.benchCodeId" required>
									</select></th>


										<th style="border: 0px">Date From</th>
									<th style="border: 0px">
										<div class="row" style="width: 125px">
											<div class="input-group date">
												<input type="text" class="form-control"
													datepicker-popup="{{format}}" ng-model="model.fromDate"
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
													datepicker-popup="{{format}}" ng-model="model.toDate"
													is-open="opened2" datepicker-options="dateOptions"
													ng-disabled="true" /> <span class="input-group-addon"
													ng-click="open($event,'opened2')"> <i
													class="glyphicon glyphicon-calendar"></i></span>
											</div></th>


									<!-- 	<th style="width: 150px;">
										<select
											ng-options="bcd.lk_id as bcd.lk_longname for bcd in genderDataList"
											class="form-control" id="genderData" 
											name="gender" ng-model="model.gender" required>
											<option value="">--Select--</option>
											</select>
										</th> -->

									<th style="border: 0px">
										<button type="submit" class="btn btn-sm btn-success"
											ng-click="getsearchByJudgmentData()">
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
							<th>First petetioner</th>
							<th>First Respondant</th>
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/searchByJudgmentDate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-csv.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-sanitize.min.js"></script>



