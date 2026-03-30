<%-- <%@ include file="../../content/header2.jsp"%> --%>
<div id="content" class="content" ng-controller="securityQuestionCtrl">
<!-- begin row -->
<div class="row">
<div >
<div class="panel panel-inverse">
<form class="form-horizontal reduce-gap" name="adduserForm"
role="form">


<!-- begin #content -->
      <div id="content" style="width: 154%;">
	
     <div class="col-md-7 ui-sortable">
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

					
					<div class="form-group" >
       									 <label class="control-label col-md-5 col-sm-5 for="password"><h5>New Password:</h5></label>
       									 	<div class="col-md-6 col-sm-6">
							       <input class="form-control" type="password" id="password" name="password" placeholder="New Password" ng-model="passwordn" required  />
							   <span ng-show="adduserForm.password.$error.required && adduserForm.password.$dirty">required</span>
							    <br />
							       </div>
							       
							       <label class="control-label col-md-5 col-sm-5 for="passwordc"><h5>Confirm Password:</h5></label>
							       <div class="col-md-6 col-sm-6">
							       <input  class="form-control" type="password" id="passwordc" name="passwordc" placeholder="Confirm Password" ng-model="passwordcn" required  />
							   <span ng-show="adduserForm.passwordc.$error.required && adduserForm.passwordc.$dirty">Please confirm your password.</span>
							      <br />
							      </div>						
										</div>



<!-- <input type="submit" id="submitbtn"
data-loading-text="Loading..." value="Login" ng-click="login()"
class="btn btn-success" /> -->

<input type="submit" id="submitbtn"
data-loading-text="Loading..." value="Save" ng-click="changePassword()"
class="btn btn-success" />

            </div>

   </div>
<script type="text/javascript"
src="${pageContext.request.contextPath}/js/scripts/controllers/securityQuestionController.js"></script>
<script type="text/javascript"
src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>

</form>
</div>
</div>
</div>
</div>