<jsp:include page="../content/header2.jsp"></jsp:include>
<%@ page import="com.eDecree.model.User"%>	

<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");

String role=user.getUserroles().get(0).getLk().getLk_longname();

%>



	<div id="content" class="content">
			<div class="container-fluid" ng-controller="NoticeController" >
			
			
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>                                
                            </div>
                            <h4 class="panel-title">View Decree</h4>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table id="data-table" class="table table-striped table-bordered" ng-init="getDecreeExamList()" >
                                    <thead>
                                        <tr>
                                        	<th>Sr.<br>No</th>
 	                                        <th>Case Type</th>
                                            <th>Case No</th>
                                            <th>Case Year</th>
                                            <th>Return Remark</th>
                                            <th>Submit Timing</th>
                                            <th>Action</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr  ng-repeat="row in decreeExaminer" class="odd gradeX" ng-hide = "row.df_rec_status==0">
                                        	<td>{{$index+1}}</td>
                                        	 <td>{{row.caseFileDetail.caseType.ct_label}}</td>
                                             <td>{{row.caseFileDetail.fd_case_no}} </td>
                                              
                                             <td>{{row.caseFileDetail.fd_case_year}}</td>     
                                             <td style="color: rgb(0, 120, 255);">{{row.df_remark}}</td>                                     
                                             <td>{{row.df_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
                                             <td><button class="btn btn-sm btn-primary "   style="border-radius: 20px; padding: 5px 12px; font-size: 12px;"   ng-click="genrateDecree(row.df_fd_mid)">Preview</button></td>
                                             <!-- <td><button class="btn btn-success" ng-click="digitalSign(row.df_fd_mid)">Digital Sign</button></td> -->
                                          </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
          
			
			</div>
	</div>
	
	</div>
	
	</body>
	<% //if(role.equals("Advocate") || role.equals("InPerson")){ %>
		<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/notice/NoticeController.js?v=2"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	
	<script type="text/javascript" 
	src="${pageContext.request.contextPath}/js/digiSign/signer.js"></script>
	
	
	<script type="text/javascript" 
	src="${pageContext.request.contextPath}/js/digiSign/conf.js"></script>
	
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>


	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</html>