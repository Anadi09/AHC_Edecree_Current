<%@ include file="../content/header2.jsp"%>
<div id="content" class="content" ng-controller="causeListController">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">Add Case To CauseList</h4>
		       </div>
				<div class="panel-body">
<!-- 				<div> -->
				<table id="data-table" st-table="displayedCollection"
							st-safe-src="masterdata"
							class="table table-striped table-bordered nowrap table-hover"
							width="100%">
							<thead>
								<tr>
									<td width="25%"><select class="form-control"
										ng-model="search.fd_case_type"
										ng-options="caseType.ct_id as (caseType.ct_label+'-'+caseType.ct_name+'') for caseType in caseTypes  | orderBy:'ct_label'">
											<option value="">Select Case Type</option>
									</select></td>
									<td><input type="text" class="form-control"
										placeholder="Case No" ng-model="search.fd_case_no">
									</td>
									<td><input type="text" class="form-control"
										placeholder="Case Year" ng-model="search.fd_case_year">
									</td>
									<td>
										<button id="search" type="submit"
											class="btn btn-primary btn-sm" ng-click="searchCaseFiles()">Search</button>
											
										
									</td>
								</tr>
								
							</thead>
						</table>



					<table id="data-table" class="table table-striped table-bordered table-hover" width="100%">
					<thead >
					<th style="text-align:left;">Case Type</th>
					<th style="text-align:left;">Case No</th>
					<th style="text-align:left;">Case Year</th>
					<th style="text-align:left;">Serial No</th>
					<th style="text-align:left;">List Type</th>
					<th style="text-align:left;">Court No</th>
					<th style="text-align:left;">CauseList Date</th>
					<th style="text-align:left;">Action</th>
					</thead>
					
						<tr ng-repeat="row in caseFileList">	
						<td width="8%" class="odd gradeX">
								<div class="input-group"  >
								{{row.caseType.ct_label}}
								<!-- 
									<span ng-model="clmodel.cl_case_type"  ng-init="clmodel.cl_case_type_mid=row.fd_case_type"></span>
										{{clmodel.cl_case_type_mid}} -->
									<!-- <select ng-options="c.clt_id as (c.clt_description+' ('+c.clt_name+')') for c in causeListTypes"
										class="form-control" ng-model="model.cl_list_type_mid"><option value="">Select Case Type</option></select>
							 -->	
							 </div>	
							</td>
							<td width="8%" class="odd gradeX">
								<div class="input-group" >
									<!-- <input type="text" class="form-control" placeholder="Case No"> -->
									{{row.fd_case_no}}
									<!-- 
									 <span ng-model="clmodel.cl_case_no"  ng-init="clmodel.cl_case_no=row.fd_case_no"></span>
									 {{clmodel.cl_case_no}} -->
								</div>	
							</td>
							<td width="8%" >
								<div class="input-group">
									
									{{row.fd_case_year}}
									
									<!-- <span ng-model="clmodel.cl_case_year"  ng-init="clmodel.cl_case_year=row.fd_case_year"></span> -->
									<!-- <input type="text" class="form-control" placeholder="Case Year"> -->
								</div>	
							</td>
							
								<td width="10%" class="odd gradeX">
								<div class="input-group" >
									<input type="number" class="form-control" placeholder="Serial No" ng-model="row.cl_serial_no">
									
								</div>	
							</td>
												
							<td width="15%" >
								<div class="input-group" ng-if="$index==0">
									<select ng-options="c.clt_id as (c.clt_description+' ('+c.clt_name+')') for c in causeListTypes"
										class="form-control" ng-model="clmodel.cl_list_type_mid" ng-change="applicationOnly($index,clmodel)"><option value="">Select List Type</option></select>
								</div>	
							</td>
							<td width="12%">
								<div class="input-group" ng-if="$index==0">
								<select ng-options="c.cm_id as c.cm_name for c in courtList | orderBy:'cm_value'"
									class="form-control" ng-model="clmodel.cl_court_no"><option value="">Select Court</option></select>
								</div>
							</td>
							<td width="15%">
								<div class="input-group date" ng-if="$index==0">
								<input type="text" class="form-control"
									datepicker-popup="{{format}}" ng-model="clmodel.cl_dol"
									is-open="opened1" datepicker-options="dateOptions"
									ng-disabled="true" min-date="maxDate"/> <span class="input-group-addon"
									ng-click="open($event,'opened1')"><i
									class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</td>
							
							<td width="25%">
							
								<button id="search" type="submit" class="btn btn-success btn-sm" ng-click="deleteCauseFile($index)">Delete</button>
								<button id="search" ng-disabled="clmodel.cl_list_type_mid!=1 && clmodel.cl_list_type_mid!=2"  type="submit" class="btn btn-success btn-sm" ng-click="vieapplication(row,clmodel)">View Application</button>
								
							</td>
						</tr>		
					</table>
					<div align="center">
					<button id="search" type="submit"  ng-disabled="!clmodel.cl_dol || !clmodel.cl_court_no" class="btn btn-success btn-sm"  ng-click="addCaseToCouseList(clmodel)">Submit</button>
					</div>
					
						<div ng-show="approw" class="table-responsive">
	                                <table  id="data-table" class="table table-striped table-bordered">
			                           <tr>
		                                   <th>Application Type</th>
		                                   <th>Application No</th>
		                                   <th>Application Year</th>
		                                   <th>Submitted Date</th>
		                                   <th>Action</th>
		                                   <!-- <th>Select All
		                                  	 <input id="{{data.cl_id}}" type="checkbox" value="{{data.cl_id}}" name="cl_id" ng-click="checkAll()" ng-model="selectedAll" />
		                                   </th> -->                     
			                           </tr>
			                            <tr ng-repeat="data in subDocuments">
											<td>{{data.documentType.at_name}}</td>
											<td>{{data.sd_document_no}}</td>
											<td>{{data.sd_document_year}}</td>
											<td>{{data.sd_submitted_date | date:"dd/MM/yyyy"}}</td>
											<td>
					                        	<input type="checkbox" name="checked" id="checked" value={{data.sd_id}}  ng-model="data.checked" ng-click="checklist()"  />
					                        </td>
			                         	</tr>
			                		</table>
	                            </div>

				</div>
			</div>
		</div>
	</div>
</div>
</div> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CauseListController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/tree-control/tree-control-attribute.css'>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</body>
</html>