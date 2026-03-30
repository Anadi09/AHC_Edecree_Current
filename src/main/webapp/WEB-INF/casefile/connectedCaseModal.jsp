<div class="modal-body">
		<div class="panel-body">
			<div id="report_table" class="table-responsive">
			
<table id="data-table" st-table="casefile.respondents" class="table table-striped table-bordered">
                           <thead>
                               	<tr>
                               	<th>Sl No.</th>
                                   <th>Case No</th>
                                      <th>Petitioner vs Respondent</th>
                                      <th style="text-align:center">Petitioner Counsel</th>
						<th style="text-align:center">Respondent Counsel</th>                          
                                </tr>
                                <tr ng-repeat="data in connectedCasesList" ng-class="{animateme: $index === connectedMatch}" >
                                <td>
						 <span ng-show="$index !=0"><b>with</b></span>
							<span ng-show="$index ==0">{{data.cl_serial_no}}</span>
						</td>
                                 <td  ng-show ='!data.cl_fd_mid' style="text-align:center;cursor: pointer;">
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" >Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</br>
						<span class ="text-danger">Not in Dms</span>
						</td> 
						<td ng-show ='data.cl_fd_mid'  style="cursor: pointer;text-decoration: underline;">
					<!-- 	<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" ng-click="viewApplication(data.cl_ano,data.cl_ayr,data.cl_fd_mid)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span> -->
						<span ng-if="data.cl_list_type_mid==1 || data.cl_list_type_mid==2" ng-click="viewApplication(data)">Application No. {{data.cl_ano}}/{{data.cl_ayr}}<br/>{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						
						<span ng-if="data.cl_list_type_mid !=1 && data.cl_list_type_mid !=2" ng-click="viewCaseFileFromModal(data)">{{data.caseType.ct_label}}<br/> {{data.cl_case_no}}/{{data.cl_case_year}}</span>
						</td>
						<td>{{data.cl_first_petitioner}} <br/> vs <br/> {{data.cl_first_respondent}} </td>
						<td style="text-align:center">{{data.cl_petitioner_council}}</td>
						<td style="text-align:center">{{data.cl_respondent_council}}</td>
                                 
                                                                
                         		</tr>
                    </thead>
                    
                </table>
            	
        	</div>
        	<button type="button" style="left: 10%; " class="btn btn-success" data-dismiss="modal">Close</button>
        	<span>  <button ng-show ="pFlag" type="button" ng-click="getPreviousCaseFromModal('P')" class="priviousButton" ></button></span>
     <!--    <span ng-show ="!pFlag"  >First</span> -->
       <!--   <span style ="color :red" >{{cl_serial_noCurrent}}</span> -->
        
       <button ng-show ="nFlag"  type="button" class="nextButton"  ng-click="getNextCaseFromModal('N')"></button> </span>
		</div>
</div>
