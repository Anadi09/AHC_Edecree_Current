<%@ include file="../../content/header.jsp"%>
<!-- <script src="daterangepicker.js"></script> -->


<div id="content" class="content" ng-controller="searchByCaseNumberCtrl">
	<!-- begin row -->
	
	<div class="row">
		<div class="col-md-11">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">Search By Case Number</h4>
				</div>
				<div class="panel-body">
				
			<!-- 	<div ng-show="errorlist" class="alert alert-block alert-danger">
		<ul>
			<span ng-repeat="errors in errorlist"> <span
				ng-repeat="n in errors track by $index">
					<li>{{(n)}}</li>
			</span>
			</span>
		</ul>
	</div> -->
				
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



									<th class="m-l-25">Case Type</th>
									<th style="width: 16%"><select
										ng-options="casetype.lk_id as casetype.lk_longname for casetype in casetypeDataList"
										class="form-control" id="caseTypeId" name="caseTypeId"
										ng-model="model.caseTypeId" required>
									</select></th>


									<th style="border: 0px">Year</th>
									<th style="border: 0px">
										<div class="row" style="width: 125px">
											<select required style="width: 78px;" class="form-control"
												ng-model="model.year" name="year" id="year"
												ng-options="year as year for year in years">
											</select> <span style="color: red"
												ng-show="subform3{{$index}}.fromYear.$dirty && subform3{{$index}}.fromYear.$invalid && subform3{{$index}}.fromYear.$error ">
											</span>
										</div>
									</th>


									<th style="border: 0px">Number</th>
										<th style="width: 16%">
									<input type="text" name="casenumber" id="casenumber" ng-model="model.caseNumber" 
									ng-pattern="/^[a-zA-Z0-9 ]*$/"  class="form-control" required />
									</th>
									
									<th style="border: 0px">
										<button type="submit" class="btn btn-sm btn-success"
											ng-click="getSearchByCaseNumberData()">
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
	src="${pageContext.request.contextPath}/js/scripts/controllers/searchByCaseNumber.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/jspdf.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-csv.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-sanitize.min.js"></script>


