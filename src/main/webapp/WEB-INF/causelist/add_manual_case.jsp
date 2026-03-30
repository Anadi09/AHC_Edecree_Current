<form name="updateForm" novalidate
	role="form">
	<div class="container-fluid">
	<div class="modal-body">
	
	
	<div class="row">
	<div class="md-col-7">
	
	<div class="row">
			<div class="form-group">
				<label class="col-md-4 control-label">Case Type<span
					class="text-danger">*</span></label>
				<div class="col-md-4">
					<select class="form-control col-md-4" required
					    name="cl_case_type_mid" id="cl_case_type_mid"
						ng-model="causelist.cl_case_type_mid"
						ng-options="caseType.ct_id as (caseType.ct_label+'-'+caseType.ct_name+'') for caseType in caseTypes  | orderBy:'ct_label'">
						<option value="">Select Case Type</option>
					</select>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Case No.<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" placeholder="Enter Case No"
						name="cl_case_no" id="cl_case_no"
						ng-model="causelist.cl_case_no" class="form-control col-md-4" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Case Year<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" placeholder="Enter Case Year"
						name="cl_case_year" id="cl_case_year"
						ng-model="causelist.cl_case_year" class="form-control col-md-4" />
				</div>
			</div>
		</div>
		
		
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application No.<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" placeholder="Enter Application No"
						name="cl_ano" id="cl_ano"
						ng-model="causelist.cl_ano" class="form-control col-md-4" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application Year<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" placeholder="Enter Application Year"
						name="cl_ayr" id="cl_ayr"
						ng-model="causelist.cl_ayr" class="form-control col-md-4" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Serial No.<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="number" class="form-control" placeholder="Serial No"
					name="cl_serial_no" id="cl_serial_no"
						ng-model="causelist.cl_serial_no">
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label class="col-md-4 control-label">List Type<span
					class="text-danger">*</span></label>
				<div class="col-md-4">
					<select
						ng-options="c.clt_id as (c.clt_description+' ('+c.clt_name+')') for c in causeListTypes"
						class="form-control" ng-model="causelist.cl_list_type_mid"
						name="cl_list_type_mid" id="cl_list_type_mid"
						ng-change="applicationOnly($index,clmodel)"><option
							value="">Select List Type</option></select>
				</div>
			</div>
		</div>
	
	
	</div>
	</div>
	
	<div class="row">
	<div class="md-col-5">
	
		<div class="row">
			<div class="form-group">
				<label class="col-md-4 control-label">Select Court<span
					class="text-danger">*</span></label>
				<div class="col-md-4">
					<select ng-options="c.cm_id as c.cm_name for c in courtList |  orderBy:'cm_value'"
					name="cl_court_no" id="cl_court_no"
									class="form-control" ng-model="causelist.cl_court_no"><option value="">Select Court</option></select>
				</div>
			</div>
		</div>
   
     <div class="row">
			<div class="form-group">
				<label class="col-md-4 control-label">Select Date<span
					class="text-danger">*</span></label>
				<div class="col-md-4">
					<div class="input-group date">
		<input type="text" class="form-control" datepicker-popup="{{format}}"
			ng-model="causelist.cl_dol1" is-open="opened1" name="date"
			datepicker-options="dateOptions" ng-disabled="true" /> <span
			class="input-group-addon" ng-click="open($event,'opened1')"><i
			class="glyphicon glyphicon-calendar"></i></span>
	</div>
				</div>
			</div>
		</div>
		

		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">First Petitioner<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="cl_first_petitioner" id="cl_first_petitioner"
					placeholder="Enter Petitioner Name"
						ng-model="causelist.cl_first_petitioner"
						class="form-control col-md-4" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">First Respondent<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="cl_first_respondent" id="cl_first_respondent"
					placeholder="Enter Respondent Name"
						ng-model="causelist.cl_first_respondent"
						class="form-control col-md-4" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Petitioner Council<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="cl_petitioner_council" id="cl_petitioner_council"
					placeholder="Enter Petitioner Council Name"
						ng-model="causelist.cl_petitioner_council"
						class="form-control col-md-4" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Respondent Council<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="cl_respondent_council" id="cl_respondent_council"
					placeholder="Enter Respondent Council Name"
						ng-model="causelist.cl_respondent_council"
						class="form-control col-md-4" />
				</div>
			</div>
		</div>
	
	
	</div>
	</div>
		</div>
		
		
	
	</div>
	<div class="modal-footer">
		<div>
			<input type="submit" value="Submit"
				ng-disabled="!causelist.cl_dol1 || !causelist.cl_court_no "
				ng-click="addManualCase()" class="btn btn-success" />
			<button type="button" class="btn btn-danger" data-dismiss="modal"
				ng-click="cancel()">Cancel</button>
		</div>
	</div>
	</div>
</form>