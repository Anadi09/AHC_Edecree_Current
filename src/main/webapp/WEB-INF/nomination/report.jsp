<div class="modal-body">
		<div class="panel-body">
			<div id="report_table" class="table-responsive">
			
                <table width="100%" id="data-table" class="table table-striped table-bordered">
                   <tbody>
                   		<span style="font-weight: bold; font-size: 110%">
                   		{{cnReport.caseType.ct_label}}/{{cnReport.fd_case_no}}/{{cnReport.fd_case_year}}
                   		</span><br>
                   		<th>Details</th>
                   		<th>From Court</th>
                   		<th>Nominated To Court</th>
                         <tr class="odd gradeX">
                         		<td style="width:25%; font-weight: bold;">Court No.</td>
                                <td style="width:25%;"> {{cnReport.caseNominated.fromCourtMaster.cm_name}}</td>
                                <td style="width:40%;">{{cnReport.caseNominated.toCourtMaster.cm_name}}</td>                                                
                         </tr>
                         <tr class="odd gradeX">
                                <td style="font-weight: bold;">Judge Name</td> 
                                <td style="width:25%;">{{cnReport.caseNominated.fromJudge.jg_name}}</td> 
                                <td style="width:25%;">{{cnReport.caseNominated.toJudge.jg_name}}</td>                                                                       
                         </tr>
                         <tr class="odd gradeX">
                                <td style="font-weight: bold;">Cause List Type</td>
                                <td style="width:25%;">{{}}</td>
                                <td style="width:25%;">{{cnReport.caseNominated.toCauseListType.clt_description}}</td>   
                                
                                
                         </tr>  
                         <tr class="odd gradeX">
                                <td style="font-weight: bold;">Date</td>
                                 <td style="width:25%;">{{cnReport.caseNominated.cn_from_date | date:"dd/MM/yyyy"}}</td> 
                               <td style="width:25%;">{{cnReport.caseNominated.cn_todate | date:"dd/MM/yyyy"}}</td>  
                         </tr>   
                       
                         <tr class="odd gradeX">
                                <td style="font-weight: bold;">Remarks</td>
                                <td>{{srd.srd_remark}}</td>  
                                <td>{{srd.srd_remark}}</td>  
                         </tr>
                         <tr class="odd gradeX">
                                <td style="vertical-align: top;font-weight: bold;">Submitted/Approved By</td>
                                <td style="width:25%;">{{cnReport.caseNominated.byFromUser.um_fullname}} <br>(REGISTRAR_LISTING)</td> 
                                <td>{{cnReport.caseNominated.byNominatedUser.um_fullname}} <br>(CJI)</td>  
                         </tr>
                    </tbody>
            	</table>
            	
        	</div>
        	<button id="btnPrint" class="btn btn-success">Print</button>
        	<!-- <button  class="btn btn-success">Previous</button>
        	<butto class="btn btn-success">Next</button> -->
		</div>
</div>
