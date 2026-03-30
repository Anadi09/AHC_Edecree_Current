<div class="modal-body">
		<div class="panel-body">
			<div class="table-responsive">
                <table id="data-table" class="table table-striped table-bordered">
	                <thead>
	                      <tr>
	                            <th>Document Type</th>
	                            <th>Application / Order Type</th>
	                            <th>Submitted Date</th>
	                            <th>Created Date</th>
	                            <th>Action</th>                                                              
	                      </tr>
	                </thead>
	                <tbody>
	                      <tr ng-repeat="row in subDocuments" class="odd gradeX">
                             <td>{{row.indexField.if_label}}</td>
                             <td>{{row.documentType.at_name}}</td>  
                             <td>{{row.sd_submitted_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
                             <td>{{row.sd_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
                             <td><button class="btn btn-success btn-sm" ng-show="row.sd_if_mid==39"
											ng-click="deleteSubDocument(row.sd_id)">Delete</button></td>
	                      </tr>
	                </tbody>
            	</table>
        	</div>
		</div>
</div>
