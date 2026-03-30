<form class="form-horizontal reduce-gap" name="uploadForm"
	id="uploadForm" novalidate enctype="multipart/form-data" role="form">

	<div class="panel-body">
		<div class="table-responsive">

			<table id="data-table" class="table table-striped table-bordered">
				<thead>
					<tr>

						<th>Application Type</th>
						<th>Application No.</th>
						<th>Application Year</th>
						<th>Action</th>
					</tr>
					</thead>
					<tr ng-repeat ="awp in applicationWithPetition" ng-include="getData(awp)" >
					 <script type="text/ng-template" id="view">
                                        	
                                            <td><select  ng-disabled="true" class="form-control" ng-model="awp.awp_ap_at_mid"
					ng-options="at.at_id as at.at_name for at in applicationsTypeList  | orderBy:'at_name'">
						<option value="">Select Application Type</option>
				</select></td>
 <td>{{awp.awp_ap_no}}</td>
<td>{{awp.awp_ap_year}}</td>
<td>
 <button class="btn btn-success btn-sm" ng-click="editBench(awp)">Edit</button>
</td>
</script>
<script type="text/ng-template" id="edit">
<td><select class="form-control" ng-model="awp.awp_ap_at_mid"
					ng-options="at.at_id as at.at_name for at in applicationsTypeList  | orderBy:'at_name'">
						<option value="">Select Application Type</option>
				</select></td>
				<td><input class="form-control" type="text" name="awp_no" id="awp_no" ng-model="awp.awp_ap_no" class="form-control col-md-4" /></td>
				<td><input class="form-control" type="text" name="awp_year" id="awp_year" ng-model="awp.awp_ap_year" class="form-control col-md-4" /></td>
					
				 <td><button class="btn btn-success btn-sm" ng-click ="updateApplicationWithPetition($index,awp)">Update</button>
				 
				 <button class="btn btn-success btn-sm" ng-click ="deleteApplicationWithPetition($index,awp)">Delete</button></td>
                                         
</script>
					
				 </tr>
				 
				
			</table>
		</div>
		        
	       

	</div>


</form>