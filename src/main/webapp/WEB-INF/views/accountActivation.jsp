<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dms.model.ObjectMaster"%>
<%@ page import="com.dms.model.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="pdmsApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EDMS</title>

<%@ include file="../content/style.jsp"%>
<%@ include file="../content/script.jsp"%>


</head>
<body>
<div id="content" class="content" ng-controller="securityQuestionCtrl">
	<!-- begin row -->
	<div class="row">
		<div class="col-md-11">
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<h4 class="panel-title">Account Activation</h4>
				</div>
				<form class="form-horizontal reduce-gap" name="adduserForm"
					role="form">
					<div ng-show="errorlist!=0" class="alert alert-block alert-danger">
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
										<!-- Questions Come into data -->
										<div class="form-group" ng-repeat="data in questDetails2">
<!-- "data in answerDetails=(answerDetails|orderBy:randomize).slice(0,2)" -->
											<label class="control-label col-md-5 col-sm-5"><h5>{{data.qest}}
													:</h5></label>
											<div class="col-md-6 col-sm-6">
												<input class="form-control" type="text"
													placeholder="Enter Answer" ng-model="data.pra_answer">
											</div>
										</div>

										<div class="form-group">
											<div style="padding-left: 107px;">
											
											<input type="submit" id="submitbtn"
													data-loading-text="Loading..." value="Submit" 
													
													ng-click="compareAnswer(questDetails2)"
													class="btn btn-success" />
											
								<!-- 				<input type="submit" id="submitbtn"
													data-loading-text="Loading..." value="Submit"
													ng-click="saveAnswer(answerDetails)"
													class="btn btn-success" />
													
													
													
												<button type="button" class="btn btn-success"
													ng-click="compareAnswer(answerDetails)">Compare</button> -->
												
												<button type="button" class="btn btn-danger"
													data-dismiss="modal">Cancel</button>
											</div>

										</div>

									</div>
								</div>
							</div>




						</div>
					</div>
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/scripts/controllers/accountActivation.js"></script>
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>

				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>