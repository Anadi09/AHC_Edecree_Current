<%@ include file="../content/header2.jsp"%>
    <div id="content" class="content" ng-controller="CaseStempReportController">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-inverse">
                    <div class="panel-heading">
                        <h4 class="panel-title">Stemp Reporting Case List</h4>
                    </div>
                    <div class="panel-body">
                        <!-- 				<div> -->

                        <!-- 					</div> -->

                        <div class="row">
                            <table st-table="caseList" st-safe-src="masterdata" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th style="width: 5px">Sr.No.</th>
                                        <th style="text-align:center ">Case Type</th>
                                        <th style="text-align:center">Case No</th>
                                        <th style="text-align:center">Case Year</th>
                                        <th style="text-align:center">Petitioner vs Respondent</th>
                                        <th style="text-align:center">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr ng-repeat="row in caseList">
                                        <td>{{$index+1}}</td>
                                        <td style="text-align:center">{{row.caseType.ct_label}}</td>
                                        <td style="text-align:center">{{row.fd_case_no}}</td>
                                        <td style="text-align:center">{{row.fd_case_year}}</td>
                                        <td style="text-align:center">
                                            <span ng-repeat="pet in row.petitioners | filter: { pt_sequence: 1} ">{{pet.pt_name}} </span>
                                            <br/> vs
                                            <br/>
                                            <span ng-repeat="ret in row.respondents | filter: { rt_sequence: 1} ">{{ret.rt_name}}</span>
                                        </td>
                                        <td style="text-align:center">
                                            <button class="btn btn-success btn-sm" ng-click="viewCaseFile(row.fd_id)">View</button>
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
    </body>

    <!-- ================== END PAGE LEVEL JS ================== -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CaseStempReportController.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>

    <script>
        $(document).ready(function() {
            $("#txtEditor").Editor();
            App.init();

        });
    </script>

    </html>