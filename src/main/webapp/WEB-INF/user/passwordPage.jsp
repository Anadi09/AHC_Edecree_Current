<%-- <%@ include file="../../content/header2.jsp"%> --%>

<div class="modal-body">
<form class="form-horizontal reduce-gap" name="adduserForm"
role="form">


<!-- begin #content -->
     <div ng-show="errorlist" class="alert alert-block alert-danger">
		<ul>
		<span ng-repeat="errors in errorlist"> <span
		ng-repeat="n in errors track by $index">
		<li>{{(n)}}</li>
		</span>
		</span>
		</ul>
		</div> 
<!-- {{passDetails}} -->
                <!--    <label style="align: left;"><h4><font color="#080808">Your Password Is:</font><font color="red">{{passDetails}}</font></h4> </label>  -->

	<div class="row">				
					<div class="form-group" >
       									 <label class="control-label col-md-5 col-sm-5" for="password"><h5>New Password:</h5></label>
       									 	<div class="col-md-6 col-sm-6">
							       				<input class="form-control" type="password" id="password" name="password" placeholder="New Password" ng-model="user.password" required  />
							   			
									</div>
									</div>       
							       <div  class="form-group" >
							       <label class="control-label col-md-5 col-sm-5" for="confirmpassword"><h5>Confirm Password:</h5></label>
							       <div class="col-md-6 col-sm-6">
							       <input  class="form-control" type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm Password" ng-model="user.confirmpassword" required  />
							   </div>						
										</div>

</div>

<!-- <input type="submit" id="submitbtn"
data-loading-text="Loading..." value="Login" ng-click="login()"
class="btn btn-success" /> -->
<div class="modal-footer"> 
<input type="submit" id="submitbtn"
data-loading-text="Loading..." value="Save" ng-click="changePassword(user)"
class="btn btn-success" />

            </div>
</form>
</div>
   