<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html ng-app="EDMSApp">
<!--<![endif]-->
<head>

	<meta charset="utf-8" />
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
	<title>Login Page</title>
 
	<!-- ================== END BASE JS @dev================== -->
<style type="text/css">
html, body { 
  height: 100%; 
  margin: 0; 
  overflow: hidden;
  overflow: clip; 
  contain: content;
}
body {
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url('assets/img/login-bg/aldd.jpg'); 
  
 background-repeat: no-repeat;
 background-size: cover;
 
    
}



.signin {
  background-color:#e3e8e8;						/* #c9c5ab 	#d3d3d3 */
  font-family: 'Montserrat', sans-serif;
  color: ;
  font-size: 14px;
  letter-spacing: 1px;
}

.login {
  position:relative;
  height: 380px;
  width: 405px;
  margin: auto;
  padding: 10px 10px;
  background:hsla(30%, 60%, 70%, 0.3);
  /*  background-image: url('assets/img/login-bg/bg1.jpg'); */  
  
 
 
  background-size: cover;
  box-shadow: 0px 30px 60px -5px #002;
  border-radius: 30px;
/*   background-image: url('assets/img/login-bg/bg-7.jpg');
   position: absolute;  */
   bottom: 85px;
 /*   left: 800px; */
   /*  margin-bottom: 5px; */
   margin-top: 105px;
   
   
}

form {
  padding-top: 50px;
}

/* .active {
  border-bottom: 2px solid #1161ed;
} */

 .nonactive {
  color: rgba(255, 255, 255, 0.2);
  
} 

h2 {
 /*  padding-left: 12px; */
  font-size: 28px;
  color:#d1c5d9;
  
 /*  text-transform: uppercase; */
 /*  padding-bottom: 5px; */
 /*  letter-spacing: 2px; */
  display: inline-block;
  font-weight: 100;
}

h2:first-child {
  padding-left: 0px;
  
}

::placeholder {
  color: #DCD8B6;
  opacity: 1; /* Firefox */
}
::-ms-input-placeholder { /* Edge 12-18 */
  color: red;
}





span {
  text-transform: uppercase;
  font-size: 12px;
  color:#e2dce6;
 /*  opacity: 0.4;  */
  display: inline-block;
  position: relative;
  top: -65px;
  transition: all 0.5s ease-in-out;
}

.text {
  border: none;
  width: 89%;
  padding: 10px 20px;
  display: block;
  height: 15px;
  border-radius: 20px;
  /* background: hsla(0%, 100%, 50%, 0.3); */
  background:rgba(20%, 60%, 20%, 0.3);
  border: 2px solid rgba(255, 255, 255, 0);
  overflow: hidden;
  margin-top: 15px;
  transition: all 0.5s ease-in-out;
  
}

.text:focus {
  outline: 0;
  border: 2px solid rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  background: rgba(0, 0, 0, 0);
  
}

.text:focus + span {
  opacity: 0.6;
}

input[type="text"],
input[type="password"] {
  font-family: 'Montserrat', sans-serif;
  color: #fff;
 
}



input {
  display: inline-block;
  padding-top: 20px;
  font-size: 14px;
  
}

h2,
span,
.custom-checkbox {
  margin-left: 20px;
}

.custom-checkbox {
  -webkit-appearance: none;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 8px;
  border-radius: 2px;
  display: inline-block;
  position: relative;
  top: 6px;
}

.custom-checkbox:checked {
  background-color: rgba(17, 97, 237, 1);
}

.custom-checkbox:checked:after {
  content: '\2714';
  font-size: 10px;
  position: absolute;
  top: 1px;
  left: 4px;
  color: #fff;
}

.custom-checkbox:focus {
  outline: none;
}

label {
  display: inline-block;
  padding-top: 10px;
  padding-left: 5px;
}

.signin {
  background-color: #1161ed;
  color: #FFF;
  width: 100%;
  padding: 10px 20px;
  display: block;
  height: 39px;
  border-radius: 20px;
  margin-top: 30px;
  transition: all 0.5s ease-in-out;
  border: none;
  text-transform: uppercase;
}

.signin:hover {
  background: #4082f5;
  box-shadow: 0px 4px 35px -5px #4082f5;
  cursor: pointer;
}

.signin:focus {
  outline: none;
}

hr {
  border: 1px solid rgba(255, 255, 255, 0.1);
  top: 40px;
  position: relative;
}

a {
  text-align: center;
  display: block;
  top: 90px;
  position: relative;
  text-decoration: none;
  color: rgba(255, 255, 255, 0.2);
}

</style>
</head>

<body>
<!-- <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'> -->
<div id="page-container" class="fade" ng-controller="loginController">
<div class="login" id="login">

<h2 class="active"> <em><b style="color: blue">e</b>-Decree</em> </h2>
<h2 class="active"> <em>Allahabad High Court</em> </h2>


  <form ng-hide="show">
   <span class="msg_div"></span>
   <div class="form-group" ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
   <input type="text" class="text" id="username" name="username" ng-model="loginform.username" required placeholder="User Name" />
     <span><b>username</b></span>
     </div>
    <br>
   
    <div class="form-group m-b-15" ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
   <input type="password" class="text"  id="password" name="password"  ng-model="loginform.password" required placeholder="Password" />
     <span><b>password</b></span>
     </div>
    <br>
    <!-- <input type="checkbox" id="checkbox-1-1" class="custom-checkbox" />
    <label for="checkbox-1-1">Keep me Signed in</label> -->
    
     <div class="login-buttons">
    <button class="signin" type="submit" ng-click="login()">Login In </button>
     <button class="signin" type="submit" ng-click="signUp()">New Registraion </button>
    </div>
   <!--  <hr> -->
    <!--  <a href="#">Forgot Password?</a> -->
     
  </form>
  
  
  <form ng-show="show">
   <span class="msg_div"></span>
   <div class="form-group" ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
   <input type="number" class="text" id="username1" name="username" ng-model="masterentity.username" required placeholder="User Name(Employee Id)" />
     <span><b>username</b></span>
     </div>
    <br>
    
    
    <div class="form-group" ng-class="{ 'has-error' : loginfrm.username.$invalid && !loginfrm.username.$pristine , 'has-success' : loginfrm.username.$valid  }">
   <input type="text" class="text" id="fullname" name="username" ng-model="masterentity.um_fullname" required placeholder="Fullname" />
     <span><b>Fullname</b></span>
     </div><br/>
   
    <div class="form-group m-b-15" ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
   <input type="password" class="text"  id="password1" name="password"  ng-model="masterentity.password" required placeholder="Password" />
     <span><b>password</b></span>
     </div>
     
     <div class="form-group m-b-15" ng-class="{ 'has-error' : loginfrm.password.$invalid && !loginfrm.password.$pristine ,'has-success' : loginfrm.password.$valid  }">
   <select id="um_role_id" ng-model="masterentity.um_role_id"
	      		 class="form-control" id="role_id" required name="role_id" ng-options="rl.lk_id as rl.lk_longname for rl in roleData"></select>
     <span><b>Role</b></span>
     </div>
    <br>
    <!-- <input type="checkbox" id="checkbox-1-1" class="custom-checkbox" />
    <label for="checkbox-1-1">Keep me Signed in</label> -->
    
     <div class="login-buttons">
    <button class="signin" type="submit" ng-click="registrationDecree(masterentity)">Registration </button>
   
    </div>
   <!--  <hr> -->
    <!--  <a href="#">Forgot Password?</a> -->
     
  </form>

</div>
</div>

<!-- ================== BEGIN BASE JS ================== -->
<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>

<!-- ================== END BASE JS ================== -->

<script  src="${pageContext.request.contextPath}/js/angularJs/angular.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/loginController.js"></script>
<script>
		$(document).ready(function() {
			App.init();
		});
	</script>

</body>
</html>