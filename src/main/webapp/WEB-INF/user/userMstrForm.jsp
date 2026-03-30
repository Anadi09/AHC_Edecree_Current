<form class="form-horizontal reduce-gap" name="adduserForm" novalidate
	role="form">

	<div ng-show="sizeOf(errorlist)" class="alert alert-block alert-danger">
		<ul>
			<span ng-repeat="errors in errorlist"> <span
				ng-repeat="n in errors track by $index">
					<li>{{(n)}}</li>
			</span>
			</span>
		</ul>
	</div>

	<!-- begin #content -->
	<div id="content" class="content" style="width: 154%;">
		<div class="row">
			<div class="col-md-5 ui-sortable">
				<div data-sortable-id="form-validation-1">
					<div class="panel-body panel-form">

						<div class="form-group">
							<label class="control-label">User name</label>
							<div class="row row-space-10">

								<div class="col-md-6">

									<input class="form-control" type="text" id="usrFullName"
										name="usrFullName" ng-model="masterentity.um_fullname"
										placeholder="User Full Name" ng-minlength=1 ng-maxlength=20
										ng-pattern="/^[a-zA-Z0-9]*$/" required
										data-parsley-required="true" data-parsley-id="5819"> <span
										style="color: red"
										ng-show="adduserForm.usrFullName.$dirty && adduserForm.usrFullName.$invalid && adduserForm.usrFullName.$error ">
										<span ng-show="adduserForm.usrFullName.$error.required">User
											name is required.</span> <small class="error"
										ng-show="adduserForm.usrFullName.$error.minlength">User
											Name is required to be at least 1 characters</small> <small
										class="error"
										ng-show="adduserForm.usrFullName.$error.maxlength">User
											name cannot be longer than 20 characters</small>
									</span>

									<ul class="parsley-errors-list" id="parsley-id-5819"></ul>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row row-space-10">

								<div class="col-md-6">
									<label class="control-label">Login Id</label> <input
										type="text" class="form-control" id="umusername"
										placeholder="Login Id" ng-model="masterentity.username"
										ng-minlength=4 ng-maxlength=10 ng-pattern="/^[a-zA-Z0-9]*$/"
										required data-parsley-required="true" data-parsley-id="5819" />
									<small class="error" style="color: red" ng-show="error">login
										Id already Exist.</small> <span style="color: red"
										ng-show="adduserForm.umusername.$dirty && adduserForm.umusername.$invalid && adduserForm.umusername.$error ">
										<span ng-show="adduserForm.umusername.$error.required">login
											Id is required.</span> <small class="error"
										ng-show="adduserForm.umusername.$error.minlength">login
											Id is required to be at least 4 characters</small> <small
										class="error"
										ng-show="adduserForm.umusername.$error.maxlength">login
											Id cannot be longer than 10 characters</small>
									</span>
								</div>
								<div class="col-md-6">
									<label class="control-label">Password</label> <input
										type="password" class="form-control" placeholder="password"
										ng-model="masterentity.password" />
								</div>
							</div>
						</div>


						<div class="form-group">
							<div class="row row-space-10">

								<div class="col-md-6">
									<label class="control-label">User Role</label> <select
										ng-model="masterentity.um_role_id" class="form-control"
										id="role_id" required name="role_id"
										ng-options="rl.lk_id as rl.lk_longname for rl in roleData"
										required>
									</select>
								</div>

								<div ng-show="[2,4].indexOf(masterentity.um_role_id) != -1">
									<div class="col-md-6">
										<label class="control-label">Team </label> 
										<select
											ng-model="masterentity.um_vendor_id" class="form-control"
											id="um_vendor_id" required name="um_vendor_id"
											ng-options="td.lk_id as td.lk_longname for td in teamData"
											required>
										</select>
									</div>

								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label">User Status</label>
							<div class="row row-space-10">
								<div class="col-md-6">
									<input type="radio" name="Active" value="1"
										ng-model="masterentity.rec_status" /> Active <input
										type="radio" name="InActive" value="2"
										ng-model="masterentity.rec_status" /> InActive
								</div>
							</div>
						</div>
						<div class="form-group">
							<div ng-if="!masterentity.um_id" style="padding-left: 107px;">
								<input type="submit" id="submitbtn"
									data-loading-text="Loading..." value="Submit"
									ng-click="user_create(masterentity)" class="btn btn-success" />
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Cancel</button>
							</div>
							<div ng-if="masterentity.um_id" style="padding-left: 107px;">
								<input type="submit" id="updatebtn" value="Update"
									data-loading-text="Loading..." ng-click="user_create()"
									class="btn btn-success" />
								<button type="button" onclick="javascript:removeErrorClass()"
									class="btn btn-danger" data-dismiss="modal">Cancel</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

</form>