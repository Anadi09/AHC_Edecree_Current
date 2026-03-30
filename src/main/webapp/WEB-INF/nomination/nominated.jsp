<%@ include file="../content/header2.jsp"%>
    <div id="content" class="content" ng-controller="nominatedController">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-inverse">
                    <div class="panel-heading">
                        <h4 class="panel-title">Case Nominated To </h4>
                    </div>
                    <div class="panel-body">
                        <!-- 				<div> -->
                        <table id="data-table" st-table="displayedCollection" st-safe-src="masterdata" class="table table-striped table-bordered nowrap table-hover" width="100%">
                            <thead>
                                <tr>
                                    <td width="25%">
                                        <select class="form-control" ng-model="search.fd_case_type" ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypes  | orderBy:'ct_name'">
                                            <option value="">Select Case Type</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="text" class="form-control" placeholder="Case No" ng-model="search.fd_case_no">
                                    </td>
                                    <td>
                                        <input type="text" class="form-control" placeholder="Case Year" ng-model="search.fd_case_year">
                                    </td>
                                    <td>
                                        <button id="search" type="submit" class="btn btn-primary btn-sm" ng-click="searchCaseFiles()">Search</button>

                                    </td>
                                </tr>

                            </thead>
                        </table>

                        <table id="data-table" class="table table-striped table-bordered table-hover" width="100%">
                            <thead> 
	                            <th colspan="3" style="text-align:left;">Case File Details</th>
	                            <th colspan="2" style="text-align:left;">Case From</th>
	                            <th colspan="4" style="text-align:left;">Case Nominated To</th>
	                            <th colspan="1" style="text-align:left;">Action</th>
                            </thead>
                             <tr>
	                              <th style="text-align:left;">Case Type</th>
	                              <th style="text-align:left;">Case No</th>
	                              <th style="text-align:left;">Case Year</th>
	                              <th style="text-align:left;">Court No</th>
	                              <th style="text-align:left;">Judge Name</th>
	                              <th style="text-align:left;">Court No</th>
	                              <th style="text-align:left;">Judge Name</th>
	                              <th style="text-align:left;">List Type</th>
	                              <th style="text-align:left;">CauseList Date</th>
	                              <th style="text-align:left;">Action</th>
                             </tr>

                            <tr ng-repeat="row in caseFileList">
                                <td width="5%" class="odd gradeX">
                                    <div class="input-group">
                                        {{row.caseType.ct_label}}
                                    </div>
                                </td>
                                <td width="5%" class="odd gradeX">
                                    <div class="input-group">
                                        {{row.fd_case_no}}
                                    </div>
                                </td>
                                <td width="5%">
                                    <div class="input-group">
                                        {{row.fd_case_year}}
                                    </div>
                                </td>
                                <td width="10%" class="odd gradeX">
                                	<div class="input-group">
                                		<select ng-options="c.cm_id as c.cm_name for c in courtList" class="form-control" ng-model="row.caseNominated.cn_from_cm_mid">
                                            <option value="">Select Court</option>
                                        </select>
                                	</div>
                                </td>
                           		 <td width="12%" class="odd gradeX">
                                	<div class="input-group">
                                		<select ng-options="j.jg_id as j.jg_name for j in judgeList" class="form-control" ng-model="row.caseNominated.cn_from_jg_mid">
                                            <option value="">Select Judge</option>
                                        </select>
                                         
                                	</div>
                                </td>
                                <td width="10%">
                                    <div>
                                        <select ng-change="getjudgeList()" ng-options="c.cm_id as c.cm_name for c in courtList" class="form-control" ng-model="row.cl_court_no">
                                            <option value="">Select Court</option>
                                        </select>
                                    </div>
                                </td>
                                  <td width="12%" class="odd gradeX">
                                	<div class="input-group">
                                		<select ng-options="j.jg_id as j.jg_name for j in judgeList" class="form-control" ng-model="row.cl_to_judge">
                                            <option value="">Select Judge</option>
                                        </select>
                                	</div>
                                </td>
                                <td width="14%">
                                    <div >
                                        <select ng-options="c.clt_id as (c.clt_description+' ('+c.clt_name+')') for c in causeListTypes" class="form-control" ng-model="row.cl_list_type_mid" ng-change="applicationOnly($index,clmodel)">
                                            <option value="">Select List Type</option>
                                        </select>
                                    </div>
                                </td>
                               
                                <td width="10%">
                                    <div>
                                        <input type="text" class="form-control" datepicker-popup="{{format}}" ng-model="row.cl_dol" is-open="opened1" datepicker-options="dateOptions" ng-disabled="true" min-date="maxDate" /> <span class="input-group-addon" ng-click="open($event,'opened1')"><i
									class="glyphicon glyphicon-calendar"></i></span>
                                    </div>
                                </td>

                                <td width="25%">
									<button class="btn btn-success btn-sm" ng-click="viewCaseFile(row.fd_id)">View</button>
									<button ng-if="row.fd_assign_to==39" id="search" type="submit" class="btn btn-success btn-sm" ng-click="saveRegistrarListing(row)">Approve</button>
                                    <button ng-if="row.fd_assign_to==39" search" type="submit" class="btn btn-success btn-sm" ng-click="saveRegistrarListing(row)">Reject</button>
                                    <button ng-if="row.fd_assign_to==38" id="search" type="submit" class="btn btn-success btn-sm" ng-click="saveRegistrarListing(row)">Submit</button>
                                    <button type="button" class="btn btn-success btn-sm"  data-toggle="modal" ng-click="getNominatedReport(row)" data-target="#report">Report</button>
                                    <!-- <button id="search" type="submit" class="btn btn-success btn-sm" ng-click="deleteCauseFile($index)">Delete</button> -->
                                    <!-- <button id="search" ng-disabled="clmodel.cl_list_type_mid!=1 && clmodel.cl_list_type_mid!=2"  type="submit" class="btn btn-success btn-sm" ng-click="vieapplication(row,clmodel)">View Application</button> -->

                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal fade" id="report" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
						  <div class="modal-content">
							 <div class="modal-header">
							    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							       <h4 class="modal-title" id="myModalLabel"><strong>Case Nominated To Court </strong></h4>
							   </div>	     
						  		<%@ include file="../nomination/report.jsp"%>
						   </div>
						</div>
				    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/NominatedController.js"></script>
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