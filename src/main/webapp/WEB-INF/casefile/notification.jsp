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
                   		<th style="width: 35%;">Defects</th>
                   		<th style="width: 5%;">Defects Date</th>
                   		<th style="width: 25%;">Remarks</th>
                   		
                         <tr class="odd gradeX" ng-repeat ="application in notifications.efilingApplicationList">
                         	<td>{{$index+1}}</td>
                            
                            <span ng-if ="application.efilingApplicationList.length > 1">
                            	{{$index+1}})
								<td>{{application.applicationType.at_name}} <br>{{application.ap_diary_no}}</td> 
								<td>{{application.ap_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td> 
								<td>{{application.userMaster.username}}</td>
								<td>{{application.userMaster.um_fullname}}</td>
								<td>{{application.caseStage.lk_longname}}</td>
								<td>
									<span ng-repeat ="chk in application.appCheckListMapping">
									{{$index+1}})&nbsp {{chk.checklist.name}}<br></span>
								</td>
								<td>
									<span ng-repeat ="chk2 in application.appCheckListMapping |limitTo:1">
									{{chk2.cm_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}<br></span>
								</td>
								<td>
									<span ng-repeat ="chkr in application.appCheckListMapping">
									 <span ng-if="chkr.cm_remark!=null"> 
									 	{{$index+1}})&nbsp
									 </span>
										{{chkr.cm_remark}}<br>
									</span>
								</td>
							
                            </span>
                                
                                	                            
                         </tr>
                        
                    </tbody>
            	</table>
            	
        	</div>
        	<button type="button" style="left: 10%; position: relative;" class="btn btn-success" data-dismiss="modal">Close</button>
		</div>
</div>
