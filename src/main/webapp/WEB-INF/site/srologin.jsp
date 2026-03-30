<%@ include file="../site/_header.jsp"%> 	  
<!--       menu.jsp for navigation bar -->     
<%@ include file="../site/_menu.jsp"%> 	

<div class="container" ng-controller="SROLoginController">

	<div class="row">
		<div class="col-md-4 col-md-offset-4 well" style="padding: 0px;">
			<div class="col-md-12 text-white label-primary" style="margin-bottom: 25px;">
			<h3 style="margin-top: 10px;"> <strong>Login </strong></h3>
			</div>
			<form class=" reduce-gap" name="formForm" novalidate role="form" style="padding: 20px;">      
			  <span id="msg_div"></span>
			  <div class="form-group " ng-class="{ 'has-error' : formForm.username.$invalid && !formForm.username.$pristine , 'has-success' : formForm.username.$valid  }">
			    <label for="username" class="control-label ">Username <span class="star"> *</span></label>			    
			  	<div class="input-group">							      
			    		<input type="text" class="form-control" id="username" name="username" ng-model="loginform.username" required placeholder="Enter Username">
				     	<div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
			    </div>
			  </div>
			   <div class="form-group" ng-class="{ 'has-error' : formForm.password.$invalid && !formForm.password.$pristine ,'has-success' : formForm.password.$valid  }">
			    <label for="password" class="control-label ">Password <span class="star"> *</span></label>			    
			   		<div class="input-group">							      
			    	<input type="password" class="form-control" id="password" name="password" ng-model="loginform.password" required placeholder="Enter Password">
				     	<div class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></div>
			    </div>
			   </div>
			   <div class="form-group">
				    <label for="language" class="col-md-6 control-label">Choose Language<span class="star">*</span></label>
				    <div class="col-md-6">
				    	<label class="radio-inline">
						  <input type="radio" name="language" ng-required="true"  ng-model="loginform.language" id="inlineRadio1" value="en"  > English
						</label>
						<label class="radio-inline">
						  <input type="radio" name="language" ng-required="true"  ng-model="loginform.language" id="inlineRadio2" value="hn"> Hindi
						</label>
				    </div>				   
			</div>
			  <div class="form-group text-right" >				  
<!-- 			  	<input type="submit" value="Login" id="login_div" ng-click="login()" ng-disabled="formForm.$invalid"  class="btn btn-primary btn-block"/>      		
 -->			 </div>
			    
			  <div class="form-group text-center" id="verifyImage" style="min-height: 150px;">
			  			<img alt="" src="/regnproc/images/fingerprint.png" style="height:150px;">

			  </div>
			  <div class="form-group text-right" >	
			  	<input type="submit" value="Capture Finger Print" id="capture-fingerprint" ng-click="captureFingerPrint()" data-loading-text="Capturing..." autocomplete="off" class="btn btn-primary btn-block"/>      		  			  
			    <input type="submit" value="Login" id="final_login_div" ng-click="loginverify()"  data-loading-text="Verifying..." autocomplete="off"  class="btn btn-primary btn-block"/>     
			  	<input type="submit" value="Direct Login" ng-click="srouserlogin()"   class="btn btn-primary btn-block"/>      		
			  	 		
			 </div>
			</form>
		</div>
	</div>	
</div>
<%@ include file="../site/_scripts.jsp"%> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/SroLoginController.js"></script>
<%@ include file="../site/_footer.jsp"%> 