
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">
    <div class="modal-body">
        <div class="row">
            <div class="form-group">
                <label class="col-md-4 control-label">Select Field Type<span class="star">*</span></label>
                <select ng-model="new_case_type" required ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypesForChange  | orderBy:'ct_name'">
                    <option value="">New Case Type</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-md-4 control-label">Case No<span class="star">*</span></label>
                <input type="text" name="new_case_no" id="new_case_no" ng-model="new_case_no" required>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label class="col-md-4 control-label">Case Year<span class="star">*</span></label>
                <input type="text" name="new_case_year" id="new_case_year" ng-model="new_case_year" required>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <div>
            <!-- ng-disabled="masterForm.$invalid" -->
            <input  type="submit" value="Submit" ng-disabled="!new_case_type || !new_case_no || !new_case_year || loading==false" ng-click="updateCasetype()" class="btn btn-success" />
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
        </div>
    </div>
</form>