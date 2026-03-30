
<form class="form-horizontal reduce-gap" name="addCase" novalidate role="form">      
	<div class="modal-body">
		<div class="row">							  			      		
			<div class="form-group">
				<label class="col-md-4 control-label">Case Type<span class="text-danger">*</span></label>
				<div class="col-md-4">
					<select class="form-control col-md-4" required ng-model="clmodel.cl_case_type_mid"
				 		ng-options="at.ct_id as at.ct_name for at in caseTypes  | orderBy:'ct_name'">
						<option value="">Select Case Type</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">							  			      		
			<div class="form-group">
				<label class="col-md-4 control-label">List Type<span class="text-danger">*</span></label>
				<div class="col-md-4">
					<select class="form-control col-md-4" required ng-model="clmodel.cl_list_type_mid"
					 ng-options="c.clt_id as (c.clt_description+' ('+c.clt_name+')') for c in causeListTypes | orderBy:'clt_description'">
						<option value="">Select List Type</option>
					</select>

				</div>
			</div>
		</div>
		<div class="row">							  			      		
			<div class="form-group">
				<label class="col-md-4 control-label">Court Type<span class="text-danger">*</span></label>
				<div class="col-md-4">
					<select class="form-control col-md-4" required ng-options="c.cm_id as c.cm_name for c in courtList"
						ng-model="clmodel.cl_court_no"><option value="">Select Court</option></select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Case No<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" required name="cl_case_no" id="cl_case_no" ng-model="clmodel.cl_case_no" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Case Year<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" required name="cl_case_year" id="cl_case_year" ng-model="clmodel.cl_case_year" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		<!-- <div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">First Petitioner<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" required name="cl_first_petitioner" id="cl_first_petitioner" ng-model="clmodel.cl_first_petitioner" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">First Respondent<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" required name="cl_first_respondent" id="cl_first_respondent" ng-model="clmodel.cl_first_respondent" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Petitioner Counsel<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" required name="cl_petitioner_council" id="cl_petitioner_council" ng-model="clmodel.cl_petitioner_council" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Respondent Counsel<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" required name="cl_respondent_council" id="cl_respondent_council" ng-model="clmodel.cl_respondent_council" class="form-control col-md-4" />
				</div>
			</div>
		</div> -->
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application No.</label>
				<div class="col-md-4">
					<input type="text" name="cl_ano" id="cl_ano" ng-model="clmodel.cl_ano" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application Year</label>
				<div class="col-md-4">
					<input type="text" name="cl_ayr" id="cl_ayr" ng-model="clmodel.cl_ayr" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Date of Listing<span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" class="form-control"
									datepicker-popup="{{format}}" ng-model="clmodel.cl_dol"
									is-open="opened1" datepicker-options="dateOptions"
									ng-disabled="true" /> <span class="input-group-addon"
									ng-click="open($event,'opened1')"><i
									class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Serial No.</label>
				<div class="col-md-4">
					<input type="text" name="cl_serial_no" id="cl_serial_no" ng-model="clmodel.cl_serial_no" class="form-control col-md-4" />
				</div>
			</div>
		</div>
	</div>
		
	<div class="modal-footer"> 
		<div>
			<input type="submit" value="Submit" ng-disabled="addCase.$invalid" ng-click="create()"   class="btn btn-success"/>      
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
		</div>	     
	</div>
</form>
