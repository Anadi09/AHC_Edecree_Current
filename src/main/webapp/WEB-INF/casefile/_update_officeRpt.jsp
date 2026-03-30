<form class="form-horizontal reduce-gap" name="updateOffice"  role="form">
    <div class="modal-body">

        <div class="row">
            <div class="form-group">
                <label class="control-label col-md-4">Order Remark<span class="text-danger"> * </span></label>
                <div class="col-md-10 col-md-offset-1">
                    <!-- <textarea name="order_date" id="order_date" ng-model="subdocument.ord_remark" class="form-control col-md-4"></textarea> -->
                    <textarea id="txtEditor1" name="abc" ng-model="officeRpt.ord_remark"></textarea>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group">
                <label class="col-md-4 control-label" for="file3">Replace or Add File<span class="star">*</span></label>
                <input type="file" ngf-select ng-model="picFile3" name="file3">
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <div>
            <input type="submit" value="Update"  ng-click="updateOfficeRpt(officeRpt)" class="btn btn-success" />
         <!--    <input type="submit" value="ReplaceFile"  ng-click="updateOfficeRptFile(officeRpt)" class="btn btn-success" /> -->
            <button type="button" class="btn btn-danger" data-dismiss="modal" ng-click="cancel()">Cancel</button>
        </div>
    </div>

    	<!-- <script>
			$(document).ready(function() {
				$("#txtEditor1").Editor();
			});
		</script> -->
</form>