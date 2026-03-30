<jsp:include page="../content/header2.jsp"></jsp:include>

<div id="content" class="content" ng-controller="editProfileCtrl">

	<div class="row">
		<div class="col-md-10">
		<div class="panel panel-inverse">
			<div class="panel-heading">
				<div class="panel-heading-btn">
			           <button type="button" class="btn btn-primary btn-sm pull-right"
							ng-click="resetModel()" data-toggle="modal"
							data-target="#changePassword_Modal">
							<span class="glyphicon glyphicon-plus-sign"></span> Change Password
						</button>
				 </div>
				<h4 class="panel-title">EDIT PROFILE</h4>
			</div>
			
			<div id="content" class="content" style="width:80%;">
			<div class="panel-body panel-form" style="width:80%">
				<div class="form-group">
				
							<label class="control-label col-md-5 col-sm-5"><h5>Login
									Name :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="username"
									name="username" ng-model="userobj.username" ng-readonly="true" />
								<br />
							</div>

							<label class="control-label col-md-5 col-sm-5"><h5>Full Name :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="username"
									name="username" ng-model="userobj.um_fullname" ng-readonly="true"/> 
									<br />
							</div>
							
							<!-- <label class="control-label col-md-5 col-sm-5"><h5>Email :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="email"
									name="email" ng-model="userobj.um_email"/> 
									<br />
							</div> -->
							
							<!-- <label class="control-label col-md-5 col-sm-5"><h5>Mobile :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="mobile"
									name="mobile" ng-model="userobj.um_mobile"/> 
									<br />
							</div> -->
							
							
							<!-- <div class="form-group">
								<div style="padding-left: 107px;">
	
	                                 <input type="submit" id="submitbtn"
										data-loading-text="Loading..." value="Submit"
										ng-click="save(userobj)" class="btn btn-success" />
	
										<button type="button" class="btn btn-danger"
										data-dismiss="modal" ng-click="landingForm()">Cancel</button>
								</div>
							</div> -->
							
							
						  
							
							
						
                                   
						</div>
			
			
			</div>
			</div>
				
		</div>		
		</div>
	</div>
	
	<div class="modal fade" id="changePassword_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="background-color: black;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<strong style="color: #FBFCFD;">Change Password</strong>
					</h4>
				</div>
				<%@ include file="editProfileForm.jsp"%>
			</div>
		</div>
	</div>
 
</div>
 </body>
 <%--  <link rel='stylesheet'
	href='${pageContext.request.contextPath}/assets/plugins/bootstrap/css/bootstrap.min.css'> --%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editProfile.js?v=1"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/angular-tree-control.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootbox.min.js"></script>
            
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>


</html>