<div class="modal-body">
<div class="panel-body">

<form class="form-horizontal reduce-gap" name="changePasswordForm"
role="form">
 

<!-- begin #content -->
      <div id="content" style="width: 154%;">

     <div class="col-md-7 ui-sortable">
      <!-- <div ng-if="errorlist" class="alert alert-block alert-danger">
		<ul>
			<span ng-repeat="errors in errorlist "> <span
				ng-repeat="n in errors track by $index">
					<li>{{(n)}}</li>
			</span>
			</span>
		</ul>
	  </div> -->
	
		<div class="form-group" >
		<label class="control-label col-md-5 col-sm-5 for="password"><h5>Old Password:</h5></label>
       			<div class="col-md-6 col-sm-6">
				       <input class="form-control" type="password" id="old_password" name="old_password" placeholder="Old Password" ng-model="old_password" ng-blur="matchOldPassword()" required  />
					   <span ng-show="changePasswordForm.old_password.$error.required && changePasswordForm.old_password.$dirty && changePasswordForm.old_password.$touched">required</span>
					    <br />
				</div>
       			<label class="control-label col-md-5 col-sm-5 for="password"><h5>New Password:</h5></label>
       			<div class="col-md-6 col-sm-6">
				       <input class="form-control" type="password" id="passwordn" name="passwordn" placeholder="New Password" ng-model="passwordn" required  />
					   <span ng-show="changePasswordForm.passwordn.$error.required && changePasswordForm.passwordn.$dirty">required</span>
					    <br />
				</div>
							       
				<label class="control-label col-md-5 col-sm-5 for="passwordc"><h5>Confirm Password:</h5></label>
				<div class="col-md-6 col-sm-6">
				     <input  class="form-control" type="password" id="passwordcn" name="passwordcn" placeholder="Confirm Password" ng-model="passwordcn" required  />
				     <span ng-show="changePasswordForm.passwordcn.$error.required && changePasswordForm.passwordcn.$dirty">Please confirm your password.</span>
				     <br />
				</div>						
		</div>


		<input type="submit" id="submitbtn"
			data-loading-text="Loading..." value="Save" ng-click="changePassword()"
			class="btn btn-success" />

       </div>

   </div>

</form>

</div>
</div>

