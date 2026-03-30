<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">
    <div class="modal-body">
        <div class="row">
            <div class="form-group">
                <label class="col-md-4 control-label">Case Assign To<span class="star">*</span></label>
                <select ng-model="um_id" required ng-options="user.um_id as user.um_fullname+ ' ('+user.username+')' for user in displayedCollection | orderBy:'um_fullname'">
                    <option value="">Select User</option>
                </select>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <div>
            <!-- ng-disabled="masterForm.$invalid" -->
            <input type="submit" value="Submit" ng-click="caseAssignTo()" class="btn btn-success" />
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
        </div>
    </div>
</form>