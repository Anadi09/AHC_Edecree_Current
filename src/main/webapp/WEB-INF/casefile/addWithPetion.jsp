<form class="form-horizontal reduce-gap" name="uploadForm" id="uploadForm" novalidate enctype="multipart/form-data" role="form">
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
                <label class="control-label col-md-4">Application Type<span class="star">*</span></label>
                <select ng-model="appWithPetition.awp_ap_at_mid" ng-options="at.at_id as at.at_name for at in applicationsTypeList  | orderBy:'at_name'">
                    <option value="">Select Application Type</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Application No.<span class="text-danger"> * </span></label>
                <div class="col-md-4">
                    <input type="text" name="awp_ap_no" id="awp_ap_no" ng-model="appWithPetition.awp_ap_no" class="form-control col-md-4" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Application Year<span class="text-danger"> * </span></label>
                <div class="col-md-4">
                    <input type="text" name="awp_ap_year" id="awp_ap_year" ng-model="appWithPetition.awp_ap_year" ng-minlength="4" ng-maxlength="4" maxlength="4" class="form-control col-md-4" />
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <div>
            <input type="submit" value="Submit" ng-disabled="uploadForm.$invalid || loading==false" " ng-click="saveAWP() "   class="btn btn-success "/>    
				<button type="button " class="btn btn-danger " data-dismiss="modal " ng-click="cancel() ">Cancel</button>  
		</div>	     
	</div>

<!-- 	<script>
			$(document).ready(function() {
				$("#txtEditor ").Editor();
			});
		</script> -->
</form>