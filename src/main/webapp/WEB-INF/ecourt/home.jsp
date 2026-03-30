<jsp:include page="../content/header2.jsp"></jsp:include>

<<style>
<!--
.container-fluid {
    padding-right: 0px;
    padding-left: 0px;
    margin-right: auto;
    margin-left: auto;
}
-->
</style>

<div id="content" class="content" ng-controller="ECourtHomeCtrl">
	<div class="container-fluid">
			<div class="col-md-3 col-sm-6">
				<a target="_new" href="/dms/causelist/type/5">
					<div class="widget widget-stats bg-green">
						<div class="stats-icon">
							<i class="fa fa-desktop"></i>
						</div>
						<div class="stats-info">
							<h4 style ="font-size: 15px;">Fresh Cases (Sl.No. upto 300)</h4>
							<h4 style ="font-size: 15px;">Total Case Listed : {{freshcount}}</h4>
						</div>
				</a>
						<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/5">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
					</div>
				</a>
		</div>
		
			<div class="container-fluid">
	   <div  class="col-md-3 col-sm-6">
	   	<a target="_new" href="/dms/causelist/type/3">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;" >Cause List</h4>
					<h4 style ="font-size: 15px;" >Total Case Listed : {{dailycount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/3">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
		
			<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/1">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;" >Daily IA (SL. No. 801 onwards)</h4>
					<h4 style ="font-size: 15px;" >Total Case Listed : {{applicationcount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/1">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>

        	<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/2">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;" >Correction Application</h4>
					<h4 style ="font-size: 15px;" >Total Case Listed : {{correctioncount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/2">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
			<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/4">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;" >Backlog</h4>
					<h4 style ="font-size: 15px;">Total Case Listed : {{backlogcount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/4">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>

			<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/6">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Fresh Supplementry</h4>
					<h4 style ="font-size: 15px;">Total Case Listed : {{supplementarycount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/6">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
			<div class="container-fluid">
		<div class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/7">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Additional/Unlisted (Sl. No. 301 To 800)</h4>
					<h4 style ="font-size: 15px;">Total Case Listed : {{additionalcount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/7">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
			
		</div>
			<div class="container-fluid">
		<div  ng-show ="showProductionList" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/19">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Production Cause List</h4>
					<h4 style ="font-size: 15px;">{{productioncount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/19">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
			
			
		</div>
		</div>
		</br>
		</br>
		</br>
		</br>
		
		<div class="container-fluid">
		<div  ng-show ="showtdList" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/26">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Transferred Daily Cause List </h4>
					<h4 style ="font-size: 15px;">{{tdcount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/26">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
		
		<div class="container-fluid">
		<div  ng-show ="showtfList" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/27">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Transferred Fresh List </h4>
					<h4 style ="font-size: 15px;">{{tfcount}}</h4>
				</div>
				</a>
			<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/27">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
			
			<div class="container-fluid">
		<div  ng-show ="showtiaList" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/28">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Transferred Daily IA</h4>
					<h4 style ="font-size: 15px;">{{tiacount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/28">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
		<div class="container-fluid">
		<div  ng-show ="showtsList" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/29">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Transferred Fresh Supplementary </h4>
					<h4 style ="font-size: 15px;">{{tscount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/29">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
		<div class="container-fluid">
		<div  ng-show ="showtuList" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/30">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Transferred Additional/Unlisted </h4>
					<h4 style ="font-size: 15px;">{{tucount}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/30">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
		<div class="container-fluid">
		<div  ng-show ="showtu1List" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/causelist/type/31">
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Transferred Additional/Unlisted List-1</h4>
					<h4 style ="font-size: 15px;">{{tu1count}}</h4>
				</div>
				</a>
				<div class="stats-link">
							<a target="_new" href="/dms/causelist/type/31">View Detail <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
			</div>
		</div>
		<div class="container-fluid">
		<div ng-show ="showTransfer" class="col-md-3 col-sm-6">
			<a target="_new" href="/dms/ecourt/transferHome">
		<!-- <div  class="col-md-3 col-sm-6"> -->
			<div class="widget widget-stats bg-green">
				<div class="stats-icon">
					<i class="fa fa-desktop"></i>
				</div>
				<div class="stats-info">
					<h4 style ="font-size: 15px;">Transferred Cases/Applications</h4>
					<h4 style ="font-size: 15px;">{{totalCases}}</h4>
				</div>
				</a>
				<div class="stats-link">
					<a targert="_new" href="/dms/ecourt/transferHome">View Detail <i
						class="fa fa-arrow-circle-o-right"></i></a>
				</div>
			</div>
		</div>
		</div>
		
			
		
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

	<div class="modal fade" id="user_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"> <span ng-if="!masterentity.um_id"><strong> Calendar</strong></span>
						      
						      </div>	     
					  <%@ include file="../ecourt/calender_form.jsp"%>
						    </div>
						  </div>
					</div>
	<div class="container-fluid" ng-show="divShow">
		<label class="control-label col-md-4">Date of Decision: <span class="text-danger"> * </span></label>
				<div class="col-md-4">
					<input type="text" class="form-control" datepicker-popup="{{format1}}" name="fromDate1" ng-change="getCauseList(cause_list_date)" ng-model="cause_list_date" is-open="fromDate1" max-date="maxDate"  datepicker-options="dateOptions" ng-disabled="true" date-disabled="disabled(date, mode)" close-text="Close" show-button-bar="false" />
					<span class="input-group-addon" ng-click="open1($event,'fromDate1')"><i class="glyphicon glyphicon-calendar"></i></span>
				</div>
				
				
				
				
				
	</div>
	
</div>

 <div style="position: absolute;">
    <marquee width=100% direction="left">
     <font size="2" face="verdana" color="red"> 
The list (in new format) have been populated based on CIS data. Any changes/ modification in case listing data in CIS, the same will be reflected in e-Court also.</font>
</marquee>
    </div>


</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/EcourtHomeController.js?v=8"></script>
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