<jsp:include page="../../../content/header.jsp"></jsp:include>

<div id="content" class="content" ng-controller="userMstrCtrl">
	<!-- begin row -->
	<div class="row">
		<!-- begin col-10 -->
		<div class="col-md-10">
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<button type="button" class="btn btn-primary btn-sm pull-right"
							ng-click="resetModel()" data-toggle="modal"
							data-target="#user_Modal">
							<span class="glyphicon glyphicon-plus-sign"></span> Add New User
						</button>
					</div>

					<h4 class="panel-title">User Management</h4>
				</div>

				<div class="panel-body">



					<table id="data-table" st-table="displayedCollection"
						st-safe-src="masterdata"
						class="table table-striped table-bordered nowrap table-hover"
						width="100%">
						<thead>
							<tr>
								<th st-sort="um_fullname">User Name</th>
								<th st-sort="username">Login Name</th>
								<th st-sort="">User Role</th>
								<!-- <th st-sort="">Validity Date</th> -->
								
							</tr>
							<tr>
								<th colspan="6"><input st-search
									placeholder="global search" class="input-sm form-control"
									type="search" /></th>
							</tr>
						</thead>
						<tbody>
							 <tr ng-repeat="data in displayedCollection" class="odd gradeX">
								<td> {{data.um_fullname}}</td>
								<td>{{data.username}}</td>
								
								<td>{{data.userroles[0].lk.lk_longname}}</td>
								<!-- <td>{{data.um_pass_validity_date | date:"dd/MM/yyyy"}}
								
								
								</td> -->
								
								<td>								
								<a class="btn btn-info btn-xs"
									ng-click="setMasterdata($index,data)" data-toggle="modal"
									data-target="#user_Modal"> <span
										class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										Edit
								</a></td>
							</tr>
						</tbody>

						<tfoot>
							<tr>
								<td colspan="6" class="text-center">
									<div st-pagination="" st-items-by-page="10"
										st-displayed-pages="8"></div>
								</td>
							</tr>
						</tfoot>
					</table>

				</div>
			</div>
			<!-- end panel -->
		</div>
		<!-- end col-10 -->
	</div>
	<!-- end row -->
	
	<div class="modal fade" id="user_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="background-color: black;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<strong style="color: #FBFCFD;">User Management  </strong>
					</h4>
				</div>
				<%@ include file="userMstrForm.jsp"%>
			</div>
		</div>
	</div>




</div>

<!-- begin theme-panel -->
<div class="theme-panel">
	<a href="javascript:;" data-click="theme-panel-expand"
		class="theme-collapse-btn"><i class="fa fa-cog"></i></a>
	<div class="theme-panel-content">
		<h5 class="m-t-0">Color Theme</h5>
		<ul class="theme-list clearfix">
			<li class="active"><a href="javascript:;" class="bg-green"
				data-theme="default" data-click="theme-selector"
				data-toggle="tooltip" data-trigger="hover" data-container="body"
				data-title="Default">&nbsp;</a></li>
			<li><a href="javascript:;" class="bg-red" data-theme="red"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Red">&nbsp;</a></li>
			<li><a href="javascript:;" class="bg-blue" data-theme="blue"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Blue">&nbsp;</a></li>
			<li><a href="javascript:;" class="bg-purple" data-theme="purple"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Purple">&nbsp;</a></li>
			<li><a href="javascript:;" class="bg-orange" data-theme="orange"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Orange">&nbsp;</a></li>
			<li><a href="javascript:;" class="bg-black" data-theme="black"
				data-click="theme-selector" data-toggle="tooltip"
				data-trigger="hover" data-container="body" data-title="Black">&nbsp;</a></li>
		</ul>
		<div class="divider"></div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">Header Styling</div>
			<div class="col-md-7">
				<select name="header-styling" class="form-control input-sm">
					<option value="1">default</option>
					<option value="2">inverse</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label">Header</div>
			<div class="col-md-7">
				<select name="header-fixed" class="form-control input-sm">
					<option value="1">fixed</option>
					<option value="2">default</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">Sidebar Styling</div>
			<div class="col-md-7">
				<select name="sidebar-styling" class="form-control input-sm">
					<option value="1">default</option>
					<option value="2">grid</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label">Sidebar</div>
			<div class="col-md-7">
				<select name="sidebar-fixed" class="form-control input-sm">
					<option value="1">fixed</option>
					<option value="2">default</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">Sidebar
				Gradient</div>
			<div class="col-md-7">
				<select name="content-gradient" class="form-control input-sm">
					<option value="1">disabled</option>
					<option value="2">enabled</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-5 control-label double-line">Content Styling</div>
			<div class="col-md-7">
				<select name="content-styling" class="form-control input-sm">
					<option value="1">default</option>
					<option value="2">black</option>
				</select>
			</div>
		</div>
		<div class="row m-t-10">
			<div class="col-md-12">
				<a href="#" class="btn btn-inverse btn-block btn-sm"
					data-click="reset-local-storage"><i class="fa fa-refresh m-r-3"></i>
					Reset Local Storage</a>
			</div>
		</div>
	</div>
</div>
<!-- end theme-panel -->

<!-- begin scroll to top btn -->
<a href="javascript:;"
	class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
	data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
<!-- end scroll to top btn -->


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/userMstrController.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>


<script>
	$(function() {
		$(".btn").click(function() {
			$(this).button('loading').delay(1000).queue(function() {
				$(this).button('reset');
				$(this).dequeue();
			});
		});
	});

	$(document).ready(function() {
		App.init();

	});
</script>
</body>
</html>