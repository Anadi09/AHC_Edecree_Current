<%@ page import="java.util.List"%>
<%@ page import="com.dms.model.ObjectMaster"%>
<%@ page import="com.dms.model.User"%>
<%@ page import="com.dms.model.User"%>	
<%@page import="com.dms.model.UserRole"%>
<%@ page import="java.util.List" %>

<jsp:include page="../content/header2.jsp"></jsp:include>
<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");

%>

<% 
				List<UserRole> userroles=user.getUserroles();
				Boolean operator=false;
				for(UserRole userrole:userroles){
					if(userrole.getLk().getLk_longname().equals("Verifier")){
						operator=true;
					}
				}
%>
<div id="content" class="content" ng-controller="editProfileCtrl">

	<!-- begin row -->
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


	<!-- <div ng-show="errorlist" class="alert alert-block alert-danger">
		<ul>
			<span ng-repeat="errors in errorlist"> <span
				ng-repeat="n in errors track by $index">
					<li>{{(n)}}</li>
			</span>
			</span>
		</ul>
	</div> -->

	<!-- begin #content -->
	<div id="content" class="content" style="width:80%;">
		<!-- <div class="row"> -->
				
			<!-- <div class="col-md-5 ui-sortable"> -->
			   
				 <!-- <div data-sortable-id="form-validation-1"> --> 
					<div class="panel-body panel-form" style="width:80%">
						<!-- Change password -->
						.

						<div class="form-group">
							<!-- {{userobj}} -->
							<!-- {{userobj.userroles[0].lk.lk_longname}} -->
							<!-- {{userobj.lk_team.lk_longname}}
							{{userobj.lk_shift.lk_longname}} -->
							<label class="control-label col-md-5 col-sm-5"><h5>Login
									Name :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="username"
									name="username" ng-model="userobj.username" ng-readonly="true" />
								<br />
							</div>

							<label class="control-label col-md-5 col-sm-5"><h5>Full
									Name :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="username"
									name="username" ng-model="userobj.um_fullname"
									ng-readonly="" /> <br />
							</div>
							<label class="control-label col-md-5 col-sm-5"><h5>User
									Role :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="userrole"
									name="userrole" ng-model="userobj.userroles[0].lk.lk_longname"
									ng-readonly="true" /> <br />
							</div>
							
						   <label class="control-label col-md-5 col-sm-5"><h5>Branch :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="userrole"
									name="userrole" ng-model="userobj.lk_benchcode.lk_longname"
									ng-readonly="true" /> <br />
							</div>
							<label class="control-label col-md-5 col-sm-5"><h5>Password Validity Date :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="username"
									name="username" ng-model="validitydate"
									ng-readonly="true" /> <br />
							</div>
							
						    <%  if (operator){ %>
							<label class="control-label col-md-5 col-sm-5"><h5>PDF
									Viewer Speed :</h5></label>
							<div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" id="userrole"
									name="userrole" ng-model="userobj.pdf_viewer_speed"
									ng-readonly="" /> <br />
							</div>
							<%}%>
							
							<!-- um_vendor_id -->
						<!-- 	
							<div ng-show="userobj.um_vendor_id != null">
								<label class="control-label col-md-5 col-sm-5"><h5>Team
										:</h5></label>
								<div class="col-md-6 col-sm-6">
									<input type="text" class="form-control" id="teamdata"
										name="teamdata" ng-model="userobj.lk_benchcode.
										lk_id[userobj.um_vendor_id].lk_longname"
										ng-readonly="true" /> <br />
								</div>
								<label class="control-label col-md-5 col-sm-5"><h5>Shift
										:</h5></label>
								<div class="col-md-6 col-sm-6">
									<input type="text" class="form-control" id="shiftdata"
										name="shiftdata" ng-model="userobj.lk_shift.lk_longname"
										ng-readonly="true" /> <br />
								</div>
							</div> -->
						
                                   
						</div>
					 <div class="form-group">
							<div style="padding-left: 107px;">

                                 <input type="submit" id="submitbtn"
									data-loading-text="Loading..." value="Submit"
									ng-click="save(userobj)" class="btn btn-success" />


								<!-- <button type="button" class="btn btn-danger"
									data-dismiss="modal">Cancel</button> -->
									<button type="button" class="btn btn-danger"
									data-dismiss="modal" ng-click="landingForm()">Cancel</button>
							</div>

						</div>

					</div>
				<!-- </div> -->
			<!-- </div> -->
		<!-- </div> -->
	</div>

 <!-- </form> -->
	
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

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editProfile.js"></script>
	<script type="text/javascript"
src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
	
</script>
</body>
</html>