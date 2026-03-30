
<%@ include file="../content/header2.jsp"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<div id="content" class="content" ng-controller="causeListControllerForTransferCases">
<input type="hidden" class="form-control" value=${type_id} id="type_id" name="type_id"> 
<input type="hidden" class="form-control" value=${fromCourt} id="fromCourt" name="fromCourt"> 
	<div class="row">
			<div class="col-md-12 ">
			<div class="panel panel-inverse">
		       <div class="panel-heading">
		          <h4 class="panel-title">${listType.getClt_description()}</h4>
		       </div>
				<div class="panel-body">
				<div class="table-responsive">
				<% if(role.equals("Private_Secretory")){ %>
				
						<table id="data-table" ng-init="getApplicationStages()" st-table="displayedCollection" class="table table-striped table-bordered nowrap table-hover">
							<thead>
								<tr>
									<td>
										<select class="form-control" ng-model="report.cl_stage_id" ng-options="lookup.lk_id as lookup.lk_longname for lookup in applicationStages  | orderBy:'lk_longname'">
											<option value="">Select Status</option>
										</select>
									</td>
									<td width="25%">
										<div class="input-group date" >
										<input type="text" class="form-control"
											datepicker-popup="{{format}}" ng-model="report.cl_date"
											is-open="opened1" datepicker-options="dateOptions"
											ng-disabled="true" /> <span class="input-group-addon"
											ng-click="open($event,'opened1')"><i
											class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</td>
									<td>
										<button id="search" type="submit" class="btn btn-primary btn-sm" ng-click="submitReport()">Submit</button>
									</td>
								</tr>

							</thead>
						</table>
						<% } %>	
						<table st-table="displayedCollection" style="font-size:14px;" st-safe-src="masterdata"  class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th>Sl No.</th>
						<th style="text-align:center">Case No</th>
						<th style="text-align:center">Petitioner vs Respondent</th>
						<th style="text-align:center">Petitioner Counsel</th>
						<th style="text-align:center">Respondent Counsel</th>
						<% if(role.equals("Private_Secretory")){ %>
						<th style="text-align:center">Status</th>
						<th style="text-align:center">Action</th>
						<% } %>	
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="data in displayedCollection">
						 <td>
						 <span ng-show="displayedCollection[$index-1].cl_serial_no==data.cl_serial_no"><b>with</b></span>
							<span ng-show="displayedCollection[$index-1].cl_serial_no!=data.cl_serial_no">{{data.cl_serial_no}}</span>
						</td>
						<td ng-show ='data.cl_fd_mid'  style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" ng-click="viewApplication(data)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2" ng-click="viewCaseFile(data)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</td>
						<td  ng-show ='!data.cl_fd_mid' style="text-align:center;cursor: pointer;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" >Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</br>
						<span class ="text-danger">Not in Dms</span>
						</td>
						<td style="text-align:center">{{data.cl_first_petitioner}} <br/> vs <br/> {{data.cl_first_respondent}} </td>
						<td style="text-align:center">{{data.cl_petitioner_council}}</td>
						<td style="text-align:center">{{data.cl_respondent_council}}</td>
						<% if(role.equals("Private_Secretory")){ %>
						<td style="text-align:center">{{data.applicationStage.lk_longname}}</td>						
						<td>
                        	<input type="checkbox" name="checked" id="checked" value={{row.cl_id}} ng-model="data.checked" />
                         </td>
                         <% } %>	
						</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5" class="text-center">
									<div st-pagination="" st-items-by-page="5000"  st-displayed-pages="4" ></div>
								</td>
							</tr>
						</tfoot>
						</table>
					</div>
					<div ng-show ="showList">
									<table  style="font-size:14px;"  class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th>Lists</th>
						<th style="text-align:center">Count</th>
							
						</tr>
						</thead>
						<tbody>
						<!-- <tr ng-repeat="data in displayedCollection1"> -->
						<tr ng-repeat="data in listData | orderBy:'cl_list_type_mid'">
						
						
						<td  style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span  ng-click="viewList(data)">{{data.cType.clt_description}}</span>
						</td>
						<td>
						{{data.count}}
						</td>
						<!-- <td   style="text-align:center;cursor: pointer;text-decoration: underline;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" ng-click="viewApplication(data.cl_ano,data.cl_ayr,data.cl_fd_mid)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2" ng-click="viewCaseFile(data.cl_fd_mid)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</td>
						<td  ng-show ='!data.cl_fd_mid' style="text-align:center;cursor: pointer;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" >Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</td> -->
						
							
						</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5" class="text-center">
									<div st-pagination="" st-items-by-page="5000"  st-displayed-pages="4" ></div>
								</td>
							</tr>
							
						</tfoot>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CauseListControllerForTransferCases.js?v=2"></script>
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