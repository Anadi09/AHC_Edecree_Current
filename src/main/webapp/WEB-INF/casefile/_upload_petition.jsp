<form class="form-horizontal reduce-gap" name="petitionForm" novalidate enctype="multipart/form-data" role="form">
    <div class="modal-body">
        <div class="row">
            <div class="form-group">
                <label class="col-md-4 control-label" for="petitionFile">File<span class="star">*</span></label>
                <input type="file" ngf-select ng-model="petitionFile" name="petitionFile">
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <div>
            <input type="submit" value="Submit" ng-disabled="petitionForm.$invalid" ng-click="savePetition()" class="btn btn-success" />
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
        </div>
    </div>
</form>