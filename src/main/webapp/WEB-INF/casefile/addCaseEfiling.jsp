<form class="form-horizontal reduce-gap" name="addcaseEflingForm" novalidate enctype="multipart/form-data" role="form">
    <div class="modal-body">
        <div ng-if="errorlist" class="alert alert-block alert-danger">
            <ul>
                <span ng-repeat="errors in errorlist "> <span
						ng-repeat="n in errors track by $index">
							<li>{{(n)}}</li>
					</span>
                </span>
            </ul>
        </div>

        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Case Type.<span class="text-danger"> * </span></label>
                <div class="col-md-4">
                    <!-- <input type="text" ng-readonly="dmsCaseData.caseType.ct_label" name="sd_document_no" id="sd_document_no" ng-model="dmsCaseData.caseType.ct_label" class="form-control col-md-4" /> -->
                    
                    
                     <select class="form-control" ng-model="dmsCaseData.fd_case_type" ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypes  | orderBy:'ct_name'">
                        <option value="">Select Case Type</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Case No.<span class="text-danger"> * </span></label>
                <div class="col-md-4">
                    <input type="text" placeholder="Enter Case No" name="sd_document_year" id="sd_document_year" ng-model="dmsCaseData.fd_case_no" class="form-control col-md-4" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Case Year<span class="text-danger"> * </span></label>
                <div class="col-md-4">
                    <input type="text" placeholder="Enter Case Year" name="sd_document_no" id="sd_document_no" ng-model="dmsCaseData.fd_case_year" class="form-control col-md-4" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Frist Petitioner<span class="text-danger"> * </span></label>
                <div class="col-md-4">
                    <input type="text" name="sd_document_no" id="sd_document_no" ng-model="dmsCaseData.first_petitioner" class="form-control col-md-4" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Frist Respondent<span class="text-danger"> * </span></label>
                <div class="col-md-4">
                    <input type="text" name="sd_document_no" id="sd_document_no" ng-model="dmsCaseData.first_respondent" class="form-control col-md-4" />
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <div>
            <input type="submit" value="Submit" ng-disabled="addcaseEflingForm.$invalid || loading==false" ng-click="addCaseEfling()" class="btn btn-success" />
            <button type="button" class="btn btn-danger" data-dismiss="modal" ng-click="cancel()">Cancel</button>
        </div>
    </div>

</form>