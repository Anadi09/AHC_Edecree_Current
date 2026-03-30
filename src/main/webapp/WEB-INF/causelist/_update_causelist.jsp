
<form class="form-horizontal reduce-gap" name="updateForm" novalidate role="form">      
	<div class="modal-body">
		<div class="row">							  			      		
			<div class="form-group">
				<label class="col-md-4 control-label" for="cl_new_court_no">Court<span class="star">*</span></label>
				<select ng-options="c.cm_id as c.cm_name for c in courtList" ng-model="cl_new_court_no" id="cl_new_court_no" required><option value="">Select Court</option></select>			
			</div>
		</div>	
	</div>
	<div class="modal-footer"> 
		<div>
		<input type="submit" value="Submit" ng-disabled="updateForm.$invalid" ng-click="updatecauselist()"   class="btn btn-success"/>      
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
		</div>	     
	</div>
</form>
