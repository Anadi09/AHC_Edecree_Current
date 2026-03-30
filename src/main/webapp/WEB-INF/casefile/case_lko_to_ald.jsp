<%@ include file="../content/header2.jsp"%>
    <html>

    <body>
        <div id="content" class="content">
            <div class="container-fluid" ng-controller="LKOCaseFileController" oncontextmenu="return false;">

                <div class="row">
                    <!-- begin col-12 -->
                    <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                            </div>
                            <h4 class="panel-title">Search Case in Lucknow</h4>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table id="data-table" st-table="displayedCollection" st-safe-src="masterdata" class="table table-striped table-bordered nowrap table-hover" width="100%">
                                    <thead>
                                        <tr>
                                            <td width="25%">
                                                <select class="form-control" ng-model="lkosearch.fd_case_type" ng-options="lkocaseTypes.ct_id as lkocaseTypes.ct_name for lkocaseTypes in lkocaseTypes  | orderBy:'ct_name'">
                                                    <option value="">Select Case Type</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="text" class="form-control" placeholder="Case No" ng-model="lkosearch.fd_case_no">
                                            </td>
                                            <td>
                                                <input type="text" class="form-control" placeholder="Case Year" ng-model="lkosearch.fd_case_year">
                                            </td>
                                            <td>
                                                <button id="search" type="submit" class="btn btn-primary btn-sm" ng-click="lkosearchCaseFiles()">Search</button>
                                            </td>
                                        </tr>

                                    </thead>
                                </table>
                                <table id="data-table" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Case Type</th>
                                            <th>Case No</th>
                                            <th>Case Year</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="row in lkocaseFileList" class="odd gradeX">
                                            <td>{{row.caseType.ct_name}}</td>
                                            <td>{{row.fd_case_no}}</td>
                                            <td>{{row.fd_case_year}}</td>
                                            <td>
                                                <% if(role.equals("DMSAdmin")) {%>
                                                    <button class="btn btn-success btn-sm" ng-click="viewCaseFileLKO(row.fd_id)">View</button>
                                                    <!-- <button class="btn btn-success btn-sm"ng-click="viewDetail(row.fd_id)">View Detail</button> -->
                                                    <button class="btn btn-success btn-sm" ng-click="downloadFiles(row.fd_id)">View Documents</button>
                                                    <button class="btn btn-success btn-sm" ng-click="setModel(row)" data-toggle="modal" data-target="#moveCaseLKOtoALD">Move LKO To ALD</button>

                                                    <!-- <button class="btn btn-success btn-sm" ng-click="setModel(row)" data-toggle="modal"data-target="#updateCaseType">Change Case Type</button> -->
                                                    <!-- <button type="button" class="btn btn-success btn-sm"data-toggle="modal" ng-click="setModel(row)"data-target="#uploadDocument">Upload</button> -->
                                                    <% }%>

                                                        <% if(role.equals("Review_Officer")) {%>
                                                            <!-- <button type="button" class="btn btn-success btn-sm"data-toggle="modal" ng-click="setModel(row)"data-target="#uploadDocument">Upload -->
                                                            </button>
                                                            <!-- <button class="btn btn-success btn-sm"ng-click="viewCaseFile(row.fd_id)">View</button> -->
                                                            <% }%>

                                                                <% if(role.equals("PS")) {%>
                                                                    <!-- <button class="btn btn-success btn-sm"ng-click="viewCaseFile(row.fd_id)">View</button> -->
                                                                    <% }%>

                                                                        <% if(role.equals("Judge")) {%>
                                                                            <!-- <button class="btn btn-success btn-sm"ng-click="viewCaseFile(row.fd_id)">View</button> -->

                                                                            <% }%>

                                            </td>
                                        </tr>
                                        <tr ng-show="lkocaseFileList.length==0">
                                            <td colspan="10">
                                                <div class="alert alert-danger">No Records Found</div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal fade" id="uploadDocument" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="myModalLabel">
										<strong>Upload Document</strong>
									</h4>
                                        </div>
                                        <%@ include file="../casefile/_upload_form.jsp"%>
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
										<strong>Stage History</strong>
									</h4>
                                        </div>
                                        <%@ include file="../casefile/filelist.jsp"%>
                                    </div>
                                </div>
                            </div>
                            <div class="modal fade" id="moveCaseLKOtoALD" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="myModalLabel">
										<strong>Transfer Case LKO To ALD</strong>
									</h4>
                                        </div>
                                        <%@ include file="../casefile/updateCasetype.jsp"%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- end panel -->

                    <!-- end col-12 -->
                </div>
            </div>
        </div>

        <!-- end row -->
    </body>

    <!-- ================== END PAGE LEVEL JS ================== -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/LKOCaseFileController.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
    <script>
        $(document).ready(function() {
            App.init();

        });
    </script>

    </html>