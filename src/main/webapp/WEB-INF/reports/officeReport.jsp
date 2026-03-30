<%@ include file="../content/header2.jsp"%>

<div id="content" class="content">
	<div class="container-fluid" ng-controller="SearchController" ng-init="getCaseTypes();">

		<div class="row">
			<!-- begin col-12 -->
			<!-- begin panel -->
			<div class="panel panel-inverse">
				<div class="panel-heading">
					<div class="panel-heading-btn">
						<a href="javascript:;"
							class="btn btn-xs btn-icon btn-circle btn-default"
							data-click="panel-expand"><i class="fa fa-expand"></i></a>
					</div>
					<h4 class="panel-title">Search Office Report By Date</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
			
					<form name="searchbyparty">
					
						 <table class="table table-striped table-bordered nowrap table-hover">
							<thead>
								
								<tr>			
									<td width="25%">
								<div ><b>To</b>
								<input type="text" class="form-control"
									datepicker-popup="{{format}}" ng-model="model.cl_dol"
									is-open="opened2" datepicker-options="dateOptions"
									ng-disabled="true" /> <span class="input-group-addon"
									ng-click="opens($event,'opened2')"><i
									class="glyphicon glyphicon-calendar"></i></span>
								</div>
									</td>
								<td width="30%">
								<div  ><b>From</b>
								<input type="text" class="form-control"
									datepicker-popup="{{format}}" ng-model="model.cl_date"
									is-open="opened1" datepicker-options="dateOptions"
									ng-disabled="true" /> <span class="input-group-addon"
									ng-click="open($event,'opened1')"><i
									class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</td>
							
									<td>
										<button id="search" type="submit" ng-disabled="searchbyparty.$invalid"
											class="btn btn-primary btn-sm" ng-click="searchreport(model)">Search</button>
									</td>
								</tr>

							</thead>
						</table>
						</form>
						<div class="container-fluid">
                       <div class="col-md-12">
						<table  style="table-layout:fixed;" id="data-table" class="table table-striped table-bordered" >
							<thead >
	
								<tr >
									<th width ="100px">Case Type</th>
									<th>Case No</th>
									<th>Case Year</th>
									<th>Date</th>
									<th>User</th>
									<th>Remark</th>
									
									<th class="col-md-1">Action</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="handoverdataArray <= 0">
									<td colspan="6" style="text-align:center;">No data found!!</td>
								</tr>
								
								<tr ng-repeat="data in handoverdataArray ">
								
									 <td >{{data.ct_name}}</td>
									 <td >{{data.fd_case_no}}</td>
		                             <td >{{data.fd_case_year}}</td>
		                            <td >{{data.sd_cr_date | date:"dd/MM/yyyy hh:mm a"}}</td>
		                             <td>{{data.um_fullname}}</td>
										 <td ng-bind-html="data.ord_remark"></td>
 
		                             <td class="col-md-1"><button id="view" type="submit" class="btn btn-sm btn-success"
							ng-click="Getfile(data)"  data-toggle="modal"> view
						</button></td>
								</tr>
							</tbody>
						</table> 	
						</div>
						</div>
						
					</div>
					
				</div>
				
			</div>

			<!-- end panel -->
	 <div class="modal fade" id="casefile_Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="z-index:10500;">
		<div class="modal-dialog" style="width:50%;overflow: auto;right:298px;">
			<div class="modal-content">
				<div class="modal-header" style="background-color: black;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<strong style="color: #FBFCFD;">Office Report {{title}}</strong>
						
					</h4>
				</div>
				<div class="row">
				
				<object type="application/pdf" data="{{fileurl}}" width="100%" height="600">
				</object>
				
		</div>
	</div>
			<!-- end col-12 -->
		</div>
	</div>
</div>

</div>
<!-- end row -->
</body>


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/OfficeReportController.js"></script>	
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/angularJs/dirPagination.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();
	});
</script>



</html>