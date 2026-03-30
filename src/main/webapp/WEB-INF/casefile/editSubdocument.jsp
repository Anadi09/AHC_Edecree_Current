<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.eDecree.model.ObjectMaster"%>
<%@ page import="com.eDecree.model.User"%>

 <%
	User user2 = null;
	if (session.getAttribute("USER") != null)
		user2 = (User) session.getAttribute("USER");
	String role2 = user2.getUserroles().get(0).getLk().getLk_longname();
%> 


<form class="form-horizontal reduce-gap" name="addAppno" novalidate
	enctype="multipart/form-data" role="form">
	<div class="modal-body">
		<div ng-if="errorlist" class="alert alert-block alert-danger">
			<ul>
				<span ng-repeat="errors in errorlist "> <span
					ng-repeat="n in errors track by $index">
						<li>{{(n)}}</li>
				</span>
				</span>
			</ul>
		</div>
		<!-- <div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application Index Field<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<select ng-model="editSubdocument.sd_document_id"
					ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList | orderBy:'at_name'">
                   		 <option value="">Select Application Type</option>
                	</select>
				</div>
			</div>
		</div> -->
		<div class="row"><%=role2 %>
			<div class="form-group">
				<label class="control-label col-md-4">Application Type<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<select ng-model="editSubdocument.sd_document_id"
					ng-options="applicationType.at_id as applicationType.at_name for applicationType in applicationTypeList | orderBy:'at_name'">
                   		 <option value="">Select Application Type</option>
                	</select>
				</div>
			</div>
		</div>
			<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application No.<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="draft_no" id="draft_no" style="color: black;"
						ng-model="editSubdocument.sd_document_no"class="form-control col-md-4" ng-readonly="true"/>
				</div>
			</div>
		</div>
			<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application Year<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="diary_no" id="diary_no" style="color: black;"
						ng-model="editSubdocument.sd_document_year"class="form-control col-md-4" ng-readonly="true"  />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-4">Application Date<span
					class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" name="case_no" id="case_id"
						ng-model="editSubdocument.sd_submitted_date"
						class="form-control col-md-4" ng-readonly="true"/>
				</div>
			</div>
		</div>

	</div>
	<div class="modal-footer">
		<div>
			<input type="submit" value="Submit"
				ng-disabled="!editSubdocument.sd_document_id || !editSubdocument.sd_document_no || !editSubdocument.sd_document_year
				|| !editSubdocument.sd_submitted_date"
				ng-click="editSubDoc(editSubdocument)" class="btn btn-success" />
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
		</div>
	</div>
</form>
