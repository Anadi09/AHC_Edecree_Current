
	<div class="modal-body">
		<div class="table-responsive">
			<table id="data-table" st-table="casefile.impugnedOrders" class="table table-striped table-bordered">
	            <thead>
	            	<tr>
	                	<th>Document Type</th>
	                    <th>No of Pages</th>
	                    <th>Submitted Date</th>                               
	                </tr>
	                <tr ng-repeat="data in files">
	                  	<td><span ng-if="data.df_sd_mid!=null">{{data.subDocument.indexField.if_label}}</span><span ng-if="data.df_sd_mid==null">Office Report</span></td>
	                 	<td>{{data.df_pages}}</td>
	                	<td>{{data.df_submitted_date | date:'dd-MM-yyyy'}}</td>
	            	</tr>
	        	</thead>
	      	</table>
  		</div>
		
	</div>
	<div class="modal-footer"> 
		<div>
		<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>  
		</div>	     
	</div>
