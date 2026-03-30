<div class="modal-body">
		<div class="panel-body">
			<div id="report_table" class="table-responsive">
			
                <table width="100%" id="data-table" class="table table-striped table-bordered">
                   <tbody>
                   		<th style="width: 1%;">Sr.<br>No</th>
                   		<th style="width: 5%;">Application Type</th>
                   		<th style="width: 5%;">Application Uploaded On </th>
                   		<th style="width: 7%;">Advocate Roll</th>
                   		<th style="width: 15%;">Counsel Name</th>
                   		<th style="width: 5%;">Application Status</th>
                   		<th style="width: 5%;">Filing Date</th>
                   		
                         <tr class="odd gradeX" ng-repeat ="application in pendingApp.efilingPendingApplicationList">
                         	<td>{{$index+1}}</td>
                            
                            <span ng-if ="application.efilingPendingApplicationList.length > 1">
                            	{{$index+1}})
								<td>{{application.applicationType.at_name}} <br>{{application.ap_diary_no}}</td> 
								<td>{{application.ap_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td> 
								<td>{{application.userMaster.username}}</td>
								<td>{{application.userMaster.um_fullname}}</td>
								<td>Pending for scrutiny</td>
								<td>
									
									{{application.filingDate | date:"dd/MM/yyyy HH:mm:ss"}}<br>
								</td>
							
                            </span>
                                
                                	                            
                         </tr>
                        
                    </tbody>
            	</table>
            	
        	</div>
        	<button type="button" style="left: 10%; position: relative;" class="btn btn-success" data-dismiss="modal">Close</button>
		</div>
</div>
