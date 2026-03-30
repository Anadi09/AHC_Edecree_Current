<jsp:include page="../content/header2.jsp"></jsp:include>
<div id="content" class="content">


	<div class="container-fluid" ng-controller="courtDetailController">

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
					<h4 class="panel-title">Court Details</h4>
				</div>
				<div class="panel-body">
					
					<div class="table-responsive">
						<table id="data-table" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>						
											
							<td width="25%">
								<div class="input-group" >
								<select ng-options="c.cm_id as c.cm_name for c in courtList"
								name="cl_court_no" id="cl_court_no"
									class="form-control" ng-model="causelist.cl_court_no"><option value="">Select Court</option></select>
								</div>
							</td>
							<td width="25%">
								<div class="input-group date" >
								<input type="text" class="form-control"
									datepicker-popup="{{format}}" ng-model="causelist.cl_dol"
									name="causelist_date"
									is-open="opened1" datepicker-options="dateOptions"
									ng-disabled="true" /> <span class="input-group-addon"
									ng-click="open($event,'opened1')"><i
									class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</td>
							
							<td width="25%">
								<div class='input-group'>
									<button type="submit" class="btn btn-sm btn-success" ng-click="getCauseList(causelist.cl_dol)">Search</button>
								
								</div>
							</td>
							
						</tr>
					</thead>
					</table>
						<table class=" table-bordered" >
							<thead>
								<tr>
								   <!--  <th class="text-center" style="padding:1px;">Court</th> -->
									<th class="text-center" style="padding:1px;">Application</th>
									<th class="text-center" style="padding:1px;">Correction Application</th>
									<th class="text-center" style="padding:1px;">Cause List</th>
									<th class="text-center" style="padding:1px;">Backlog</th>
									<th class="text-center" style="padding:1px;">Fresh Cases</th>
									<th class="text-center" style="padding:1px;">Supplementary</th>
									<th class="text-center" style="padding:1px;">Additional</th>
									<th class="text-center" style="padding:1px;">Fresh Supplementary List-1</th>
									<th class="text-center" style="padding:1px;">Fresh Supplementary List-2</th>
									<th class="text-center" style="padding:1px;">Fresh Supplementary List-3</th>
									<th class="text-center" style="padding:1px;">Additional List-1</th>
									<th class="text-center" style="padding:1px;">Additional List-2</th>
									<th class="text-center" style="padding:1px;">Additional List-3</th>
									<th class="text-center" style="padding:1px;">Additional List-4</th>
									<th class="text-center" style="padding:1px;">Additional List-5</th>
					
								</tr>
							</thead>
							<tbody class="text-center">
								<tr ng-repeat="data in causetList" class="odd gradeX">

                                    
									<!--  <td>{{data.applicationcount}}</td>
									 <td>{{data.correctioncount}}</td>
									 <td>{{data.dailycount}}</td>
									 <td>{{data.backlogcount}}</td>
									 <td>{{data.freshcount}}</td>
									 <td>{{data.supplementarycount}}</td>
									 <td>{{data.additionalcount}}</td>
									 <td>{{data.freshapplicationlist1}}</td>
									 <td>{{data.freshapplicationlist2}}</td>
									 <td>{{data.freshapplicationlist3}}</td>
									 <td>{{data.additionallist1}}</td>
									 <td>{{data.additionallist2}}</td>
									 <td>{{data.additionallist3}}</td>
									 <td>{{data.additionallist4}}</td>
									 <td>{{data.additionallist5}}</td> -->
									 
									<td>{{data[1]}}</td>
									<td>{{data[2]}}</td>
									<td>{{data[3]}}</td>
									<td>{{data[4]}}</td>
									<td>{{data[5]}}</td>
									<td>{{data[6]}}</td>
									<td>{{data[7]}}</td>
									<td>{{data[8]}}</td>
									<td>{{data[9]}}</td>
									<td>{{data[10]}}</td>
									<td>{{data[11]}}</td>
									<td>{{data[12]}}</td>
									<td>{{data[13]}}</td>
									<td>{{data[14]}}</td>
									
									
																				
								</tr>

								<tr ng-show="caveatList.length==0">
									<td colspan="8">
										<div class="alert alert-danger">No Records Found</div>

									</td>
								</tr>
							</tbody>
						</table>		

					</div>
		
				</div>
			</div>
		</div>
	</div>
</div>

</div>
<!-- end row -->

<!-- ================== END PAGE LEVEL JS ================== -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/courtDetailController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>


<script>
	$(document).ready(function() {
		App.init();

	});
</script>
