
<form class="form-horizontal reduce-gap" name="uploadForm" novalidate enctype="multipart/form-data" role="form">      
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
		<div class="row">{{data.ord_remark}}
			<div class="form-group">
				  <label class="control-label col-md-4">Order Remark<span class="text-danger"></span></label>
				<div class="col-md-10 col-md-offset-1" >
					<div >
					
					<textarea   id="txtEditor1"  name="abc"  ng-bind-html="data.ord_remark" ng-model="TemplateDescription"></textarea> 
				</div>
			</div>
		</div></div>
	</div>
	<div class="modal-footer"> 
		<div>
		<div >
					
		<input type="submit" value="Submit"   ng-click="update()"   class="btn btn-success"/>    
				<button type="button" class="btn btn-danger" data-dismiss="modal" ng-click="cancel()">Cancel</button>  
		</div>	     
	</div>
</form>
<script>
	$(document).ready(function() {
		$("#txtEditor1").Editor();
		App.init();

	});

	
</script>