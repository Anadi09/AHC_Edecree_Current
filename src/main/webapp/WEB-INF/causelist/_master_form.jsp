
<form class="form-horizontal reduce-gap" name="masterForm" novalidate enctype="multipart/form-data" role="form">      
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
	
		<div class="row">							  			      		
				<div class="form-group">
							<label class="col-md-4 control-label" for="file">File<span class="star">*</span></label>
				<!-- 			<input type="file" file-upload multiple/> -->
							<input type="file" ngf-select ng-model="picFile" name="file"  >
				 </div>
<!-- 				 <div class="form-group"> -->
<!-- 				 	<label class="col-md-4 control-label" for="file">Date<span class="star">*</span></label> -->
<!-- 					<div class="input-group" style="margin:0 10px;"> -->
<!-- 						<input type="text" class="form-control" -->
<!-- 									datepicker-popup="{{format}}" ng-model="causelist.cl_date" -->
<!-- 									is-open="opened1" datepicker-options="dateOptions" -->
<!-- 									ng-disabled="true" /> <span class="input-group-addon" -->
<!-- 									ng-click="open($event,'opened1')"><i -->
<!-- 									class="glyphicon glyphicon-calendar"></i></span> -->
<!-- 					</div>			 -->
<!-- 		 		</div> -->
		</div>
	
	</div>
	<div class="modal-footer"> 
		<div>
		<input type="submit" value="Submit" ng-disabled="masterForm.$invalid || loading==false" ng-click="save()"   class="btn btn-success"/>      
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>  
		</div>	     
	</div>
</form>
