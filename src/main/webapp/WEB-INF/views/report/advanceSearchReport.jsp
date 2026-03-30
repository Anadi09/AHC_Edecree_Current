<%@ include file="../../content/header.jsp"%>

<!-- <script src="daterangepicker.js"></script> -->

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/ng-csv/0.3.6/ng-csv.js"></script>
<script src="https://code.angularjs.org/1.2.20/angular-sanitize.min.js"></script>

<div id="content" class="content"
	ng-controller="advanceSearchReportCtrl">
	<!-- begin row -->
	<div class="row">
		<div class="col-md-11">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">Advance Search Report</h4>
				</div>
				<div class="panel-body">
					<div>
						<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata" class="table table-striped table-bordered nowrap table-hover" width="100%">
							<thead>
								<tr>
									<th st-sort="lk_longname" class="m-l-25">Bench Code</th>
									<th style="width: 16%"><select
										ng-options="bcd.lk_id as bcd.lk_longname for bcd in branchDataList"
										class="form-control" id="benchCodeId" name="benchCodeId"
										ng-model="model.benchCodeId" required>
									</select></th>


									<th class="m-l-25">Case Type</th>
									<th style="width: 16%"><select
										ng-options="bcd.lk_id as bcd.lk_longname for bcd in casetypeDataList"
										class="form-control" id="caseTypeId" name="caseTypeId"
										ng-model="model.caseTypeId" required>
									</select></th>
								</tr>

								<tr>
									<th class="m-l-25">Judgement year Between</th>
									<th style=""><select required style="width: 78px;"
										class="form-control" ng-model="fromYear" name="fromYear"
										id="fromYear" ng-options="year as year for year in years">
									</select> <span style="color: red"
										ng-show="subform3{{$index}}.fromYear.$dirty && subform3{{$index}}.fromYear.$invalid && subform3{{$index}}.fromYear.$error ">
									</span></th>


									<th class="m-l-25">And</th>
									<th style=""><select required style="width: 78px;"
										class="form-control" ng-model="toYear" name="toYear"
										id="toYear" ng-options="year as year for year in years">
									</select> <span style="color: red"
										ng-show="subform3{{$index}}.fromYear.$dirty && subform3{{$index}}.fromYear.$invalid && subform3{{$index}}.fromYear.$error ">
									</span></th>

									<th class="m-l-25">Judge Type</th>
									<th style="width: 16%"><select class="form-control"
										ng-model="judgeType">
											<option value="">All Judges</option>
											<option value="">All Siting Judges</option>
									</select></th>
								</tr>

								<tr>



									<th class="m-l-25">First Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="judgeName" name="judgeName"
										ng-model="firstJudgeName" required>
									</select></th>

									<th class="m-l-25">Second Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="judgeName" name="judgeName"
										ng-model="secondJudgeName" required>
									</select></th>


									<th class="m-l-25">Third Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="judgeName" name="judgeName"
										ng-model="thirdJudgeName" required>
									</select></th>
								</tr>

								<tr>



									<th class="m-l-25">Fourth Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="judgeName" name="judgeName"
										ng-model="fourthJudgeName" required>
									</select></th>
									<th class="m-l-25">Fifth Judge</th>
									<th style="width: 16%"><select
										ng-options="bcd.jg_id as bcd.jg_name for bcd in judgeDataList"
										class="form-control" id="judgeName" name="judgeName"
										ng-model="fifthJudgeName" required>
									</select></th>




									<th style="border: 0px">
										<button type="submit" class="btn btn-sm btn-success"
											ng-click="getAdvanceSearchReportData()">
											<span class="glyphicon glyphicon-plus-sign"></span>Search
											here
										</button>

									</th>


									<th style="border: 0px"><button style="float: left"
											type="button" class="btn btn-primary btn-sm pull-right"
											name="ExportToCsv1" ng-csv="masterdata" filename="test.csv"
											data-toggle="modal">
											<i class="fa fa-edit"></i> Csv
										</button></th>
									<th style="border: 0px"><button style="float: left"
											type="button" class="btn btn-primary btn-sm pull-right"
											name="ExportToExcel1" ng-csv="masterdata"
											filename="test23.xlsx" data-toggle="modal">
											<i class="fa fa-edit"></i> Excel
										</button></th>


								</tr>

							</thead>
						</table>

					</div>

					<table id="data-table" st-table="" st-safe-src="masterdata"
						class="table table-striped table-bordered nowrap table-hover"
						width="100%">
						<tr>
							<th>Case Type</th>
							<th>Case No</th>
							<th>Case Year</th>
							<th>First Petitioner</th>
							<th>First Respondent</th>
							<th>Judgement Date</th>
						</tr>
						<tbody>
							<tr ng-repeat="data in displayedCollection " class="odd gradeX">

								<!-- <td style="width: 30%">{{data.parameter1}}</td> -->
								<td>{{data.parameter1}}</td>
								<td>{{data.parameter2}}</td>
								<td>{{data.parameter3}}</td>
								<td>{{data.parameter4}}</td>
								<td>{{data.parameter5}}</td>
								<td>{{data.parameter6}}</td>



							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</div>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/advanceSearchReport.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/jspdf.js"></script>
