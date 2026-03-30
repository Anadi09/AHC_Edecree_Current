<jsp:include page="../content/header2.jsp"></jsp:include>
<div id="content" class="content" ng-controller="CasefileSearchControllerForRemoval">
    <div class="row">
        <!-- begin panel -->
        <div class="panel panel-inverse">
            <div class="panel-heading">
                <div class="panel-heading-btn">
                    <!-- <button type="button" class="btn btn-primary btn-sm pull-right"
							ng-click="resetMasterdata()" data-toggle="modal"
							data-target="#casefile_Modal">
							<span class="glyphicon glyphicon-plus-sign"></span> Add New
							Record
						</button> -->
                </div>
                <h4 class="panel-title">Case File Search</h4>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table id="data-table" st-table="displayedCollection" st-safe-src="masterdata" class="table table-striped table-bordered nowrap table-hover" width="100%">
                        <thead>
                            <tr>
                                <th style="text-align: center;" width="40%" st-sort="fd_case_type">Case Type</th>
                                <th style="text-align: center;" width="20%" st-sort="fd_case_no">Case Number</th>
                                <th style="text-align: center;" width="25%" st-sort="fd_case_year">Case Year</th>
                                <th style="text-align: center;" width="10%">Search</th>
                            </tr>
                            <tr>
                                <th>
                              
                                    <select   ng-options="caseType.ct_id as (caseType.ct_label+'-'+caseType.ct_name+'') for caseType in caseTypeList  | orderBy:'ct_label'"  class="form-control" id="caseTypeData" name="fd_case_type" ng-model="model.fd_case_type" required>
                                        <option value="">--Select--</option>
                                    </select>
                                </th>
                                <th>
                                    <input numbers-only id="fd_case_no" type="text" name="fd_case_no" class="form-control" ng-pattern="/^[a-zA-Z0-9]*$/" ng-model="model.fd_case_no" />
                                </th>
                                <th>
                                    <input numbers-only id="fd_case_year" type="text" name="fd_case_year" class="form-control" ng-pattern="/^[a-zA-Z0-9]*$/" maxlength=4 ng-model="model.fd_case_year" />
                                </th>
                                <th>
                                    <button id="search" type="submit" class="btn btn-primary btn-sm pull-right" ng-click="searchCaseFile()" data-toggle="modal" style="size: 2px">
                                        <span class="glyphicon"></span>Search</button>
                                </th>
                            </tr>
                        </thead>
                    </table>
                    <table id="data-table" st-table="displayedCollection" st-safe-src="masterdata" class="table table-striped table-bordered nowrap table-hover" width="100%">
                        <thead>
                            <tr>
                                <th style="text-align: center;" width="20%" st-sort="fd_case_type">Case Type</th>
                                <th style="text-align: center;" width="20%" st-sort="fd_case_no">Case Number</th>
                                <th style="text-align: center;" width="20%" st-sort="fd_case_year">Case Year</th>
                                <th style="text-align: center;" width="20%" st-sort="fd_first_petitioner">First Petitioner </th>
                                <th style="text-align: center;" width="20%" st-sort="fd_first_respondent">First Respondent</th>
                                <th style="text-align: center;" width="20%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="data in caseFileList" class="odd gradeX">
                                <td align="center">{{data.caseType.ct_name}}</td>
                                <td align="center">{{data.fd_case_no}}</td>
                                <td align="center">{{data.fd_case_year}}</td>
                                <td align="center">{{data.fd_first_petitioner}}</td>
                                <td align="center">{{data.fd_first_respondent}}</td>
                                <td align="center">
                                    <button id="Submit" type="submit" class="btn btn-success" data-target="#usersModel" data-toggle="modal" ng-click="viewAmendment(data)">
                                        View Advocate</button>
                                </td>

                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- end panel -->
    </div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/CasefileSearchControllerForRemoval.js?v=1"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<!-- ================== END PAGE LEVEL JS ================== -->
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
    $(document).ready(function() {
        App.init();

    });
</script>

</html>