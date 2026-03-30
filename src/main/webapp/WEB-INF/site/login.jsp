<%@ include file="../site/_header.jsp"%> 	  
<!--       menu.jsp for navigation bar -->     
<%@ include file="../site/_menu.jsp"%> 	
<div class="container" ng-controller="siteLoginController" ng-clock>
	<div class="row">
		<div class="col-md-4 col-md-offset-4 well" style="padding: 0px;">
				<div class="col-md-12 text-white label-primary" style="margin-bottom: 25px;">
			<h3 style="margin-top: 10px;"> <strong>Login </strong></h3>
			</div>
			<form class=" reduce-gap" name="formForm" novalidate " style="padding: 20px;">      
			  <span id="msg_div"></span>
			  <div class="form-group " ng-class="{ 'has-success' : formForm.mobileno.$valid , 'has-error' : formForm.mobileno.$invalid && !formForm.mobileno.$pristine }">
			    <label for="mobileno" class="control-label ">Mobile Number <span class="star"> *</span></label>			    
			  	<div class="input-group">							      
			    		<input type="text" class="form-control" id="mobileno" ng-pattern="/^([0-9]*)$/" name="mobileno" ng-minlength="10" ng-maxlength="10" required ng-model="loginform.mobileno" required placeholder="Enter Mobile Number">
				     	<div class="input-group-addon"><span class="glyphicon glyphicon-phone" aria-hidden="true"></span></div>				      
			    </div>
				<span class="text-danger" ng-show="formForm.mobileno.$dirty && formForm.mobileno.$invalid">
						  <span ng-show="formForm.mobileno.$invalid">Mobile Number is required.</span>
				</span>
			  </div>
<!-- 			   <div class="col-sm-12"><p class="text-center text-muted" style="margin: 0px;"><strong>or</strong></p> </div> -->
			   <div class="form-group" ng-class="{ 'has-error' : formForm.email1.$invalid && !formForm.email1.$pristine }">
			    <label for="email" class="control-label ">Email</label>			    
			   		<div class="input-group">							      
			    	<input type="email" class="form-control" id="email" name="email1" ng-model="loginform.email" placeholder="Enter Email">
				     	<div class="input-group-addon"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></div>
			    </div>
			    
			    <span class="text-danger" ng-show="formForm.email1.$dirty && formForm.email1.$invalid">
						  <span ng-show="formForm.email1.$error.email">Invalid email address.</span>
				</span>				
    </div>
			  <div class="form-group" >			  	
			      <label for="newotp" class="control-label">OTP</label>
			      <div class="row">
			       <div class="col-sm-8">
			     		<input type="text" class="form-control " name="newotp"  id="newotp" ng-model="loginform.newotp" placeholder="OTP">
			  	  </div>
			  	   <div class="col-sm-4" style="  padding-left: 10px;">
			  	  <input type="submit" value="Generate OTP" ng-click="verify()" ng-disabled="formForm.$invalid" id="generate-button" data-loading-text="Generating..." autocomplete="off" class="btn btn-primary btn-sm"/>      		
			  	  </div>
			  	  </div>			  	
			  </div>
			  <div class="form-group text-right" >				  
			  	<input type="submit" value="Login" id="login_div" ng-click="login()" ng-disabled="formForm.$invalid" style="display:none" data-loading-text="Loading..." autocomplete="off"  class="btn btn-primary btn-block"/>      		
			 
			   </div>
			</form>
		</div>
	</div>	
</div>
<%@ include file="../site/_scripts.jsp"%> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/SiteLoginController.js"></script>
<%@ include file="../site/_footer.jsp"%> 