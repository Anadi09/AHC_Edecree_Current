<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <jsp:include page="../content/header2.jsp"></jsp:include>
    <input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id">
    <div id="content" class="content">
        <div class="container-fluid" ng-controller="DownloadCtrl">
            <div class="row">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-inverse overflow-hidden">
                        <div class="panel-heading">
                            <h3 class="panel-title">
	                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#caseDetails">
										    <i class="fa fa-plus-circle pull-right"></i> 
											Case Details
								</a>
	                        </h3>
                        </div>
                        <div id="caseDetails" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table id="data-table" class="table table-striped table-bordered">
                                        <tr>
                                            <th>Case Type</th>
                                            <th>case No.</th>
                                            <th>Year</th>
                                            <th>first Petitioner</th>
                                            <th>first Respondent</th>
                                        </tr>
                                        <tr>
                                            <td>{{casefile.caseType.ct_label}}</td>
                                            <td>{{casefile.fd_case_no}}</td>
                                            <td>{{casefile.fd_case_year}}</td>
                                            <td>{{casefile.first_petitioner}}</td>
                                            <td>{{casefile.first_respondent}}</td>

                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-inverse ">
                        <div class="panel-heading">
                            <h3 class="panel-title">
	                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#subdocuments">
										    <i class="fa fa-plus-circle pull-right"></i> 
											Documents
								</a>
	                        </h3>
                        </div>
                        <div id="subdocuments" class="panel-collapse ">
                            <div class="panel-body">
                                <div class="row">
                                    <button type="button" class="btn btn-success m-r-5 m-b-5 pull-right" ng-click="addfiles()">Add Files for Download</button>
                                </div>
                                <div class="table-responsive">
                                    <table id="data-table" class="table table-striped table-bordered">
                                        <tr>
                                            <th>Type</th>
                                            <th>Submitted</th>
                                            <th>Select All
                                                <input id="{{data.cl_id}}" type="checkbox" value="{{data.cl_id}}" name="cl_id" ng-click="checkAll()" ng-model="selectedAll" />
                                            </th>
                                        </tr>
                                        <tr ng-repeat="data in subDocuments">
                                            <td ng-click="showSubDocumentshow(data.sd_id)" style="text-decoration: underline;cursor:pointer;padding:10px 5px;width:35%"><b>{{data.documentType.at_name}} <br/> {{data.sd_submitted_date | date:'dd-MM-yyyy'}}</b></td>
                                            <td>{{data.sd_submitted_date | date:"dd/MM/yyyy"}}</td>
                                            <td>
                                                <input type="checkbox" name="checked" id="checked" value={{data.sd_id}} ng-model="data.checked" />
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="table-responsive">
                                    <table id="data-table" class="table table-striped table-bordered">
                                        <tr>
                                            <th>Type</th>
                                            <th>Submitted</th>
                                            <th>Remark</th>
                                            <th>Action</th>
                                        </tr>
                                        <tr ng-repeat="data in orderReports">
                                            <td><span ng-if="data.subDocument==null">Off. Rep.</span><span ng-if="data.subDocument!=null" ng-click="showSubDocument(data.subDocument.sd_id)" style="text-decoration: underline;cursor:pointer;">Off. Rep.</span></td>
                                            <td>{{data[0].ord_created | date:"dd/MM/yyyy"}}</td>
                                            <!-- <td>{{data[0].ord_remark}}</td> -->
                                            <td>
                                                <div ng-bind-html="ord_remark[$index]"></div>
                                            </td>
                                            <td>
                                                <input type="checkbox" name="checked" id="checked" value={{data[0].ord_id}} ng-model="data.checked" />
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-inverse overflow-hidden">
                        <div class="panel-heading">
                            <h3 class="panel-title">
	                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#downloadDetails">
										    <i class="fa fa-plus-circle pull-right"></i> 
											Download History
								</a>
	                        </h3>
                        </div>
                        <div id="downloadDetails" class="panel-collapse collapse">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table id="data-table" class="table table-striped table-bordered">
                                        <tr>
                                            <th>Amount</th>
                                            <th>Created</th>
                                            <th>Download Status</th>
                                            <th>Action</th>
                                        </tr>
                                        <tr ng-repeat="data in reports">
                                            <td>{{data.dr_amount}}</td>
                                            <td>{{data.dr_cr_date | date:'dd-MM-yyyy'}}</td>
                                            <td><span ng-if="data.dr_rec_status==1">Pending</span><span ng-if="data.dr_rec_status==2">Done</span></td>
                                            <td>
                                                <button type="button" class="btn btn-success" data-toggle="modal" ng-click="setFiles(data)" data-target="#viewFiles">View Details</button>
                                                <button ng-if="data.dr_rec_status==1" type="button" class="btn btn-success" ng-click="downloadFiles(data.dr_id)">Download Files</button>
                                                <button type="button" class="btn btn-success" ng-click="removeFiles(data.dr_id)">Remove</button>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="viewFiles" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
									<strong>Downloaded Files</strong>
								</h4>
                            </div>
                            <%@ include file="../casefile/_viewfiles.jsp"%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>

    </div>

    </body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileDownloadController.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
    <style>
        .st-sort-ascent:before {
            content: '\25B2';
        }
        
        .st-sort-descent:before {
            content: '\25BC';
        }
    </style>
    <script>
        $(document).ready(function() {
            App.init();

        });
    </script>

    </html>