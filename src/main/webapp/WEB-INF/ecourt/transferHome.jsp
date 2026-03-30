<jsp:include page="../content/header2.jsp"></jsp:include>

<div id="content" class="content" ng-controller="TransferECourtHomeCtrl">
	<div class="container-fluid">
<div ng-repeat ="data in transferCases | orderBy:'cl_list_type_mid'">
		<div ng-show ="data.count >= 1" class="col-md-3 col-sm-6"  ng-click ="viewList(data)">
		<!-- <div  class="col-md-3 col-sm-6"> -->
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">{{data.transferFrom}}</h4>
					<h4 style ="font-size: 15px;">{{data.count}}</h4>
				</div>
				<div class="stats-link">	
					<a targert="_new" href ng-click ="viewList(data)">View Detail <i
						class="fa fa-arrow-circle-o-right"></i></a>
				</div>
			</div>
		</div>
		</div>
		</div>
		
		<!-- <div ng-repeat ="data in transferCases | orderBy:'cl_list_type_mid'"> -->
		<!-- <div class="container-fluid">
		<div ng-show ="showTransfer" class="col-md-3 col-sm-6">
		<div  class="col-md-3 col-sm-6">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4>Transfered Case/Application</h4>
					<h4>.</h4>
				</div>
				<div class="stats-link">
					<a targert="_new" href="/dms/causelist/type/7">View Detail <i
						class="fa fa-arrow-circle-o-right"></i></a>
				</div>
			</div>
		</div>
		</div> -->
		<!-- </div> -->
		
<!-- 				                                                 <button id="doownloadCauseList" type="button" class="btn btn-primary btn-sm" ng-click="doownloadCauseList()">Cause List</button>
 -->		

		<!-- Sushant -->

<!-- 		<div class="col-md-3 col-sm-6">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4>Click To view Previous Date Causlist</h4>
					<h4></h4>
				</div>
				<div class="stats-link">
					<a targert="_new" ng-click="show()">View Detail <i
						class="fa fa-arrow-circle-o-right"></i></a>
				</div>
			</div>
		</div> -->

	
	<div class="container-fluid" ng-show="divShow">
		<label class="control-label col-md-4">Date of Decision: <span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-change="getCauseList(cause_list_date)" ng-model="cause_list_date" is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" close-text="Close" show-button-bar="false" />
					<span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
				
				
				
				
				
	</div>
</div>


</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/TransferECourtHomeCtrl.js?v=4"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();

	});
</script>
</html>