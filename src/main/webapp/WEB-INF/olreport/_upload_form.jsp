
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">      
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
				<label class="col-md-4 control-label">Application No.<span class="star">*</span></label>
				<input type="text" class="col-md-4" ng-model="model.ol_no" />
			</div>
		</div>
		<div class="row">							  			      		
			<div class="form-group">
				<label class="col-md-4 control-label">Application Year<span class="star">*</span></label>
				<input type="text" class="col-md-4" ng-model="model.ol_year" />
			</div>
		</div>
			
		<div class="row">							  			      		
				<div class="form-group">
							<label class="col-md-4 control-label" for="file">File<span class="star">*</span></label>
							<input type="file" ngf-select ng-model="picFile" name="file"  >
				 </div>
		</div>
	
	</div>
	<div class="modal-footer"> 
		<div>
		<input type="submit" value="Submit" ng-click="save()"   class="btn btn-success"/>      
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
		</div>	     
	</div>
</form>
