<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.dms.model.User"%>	
<%@page import="com.dms.model.UserRole"%>
<%@ page import="java.util.List" %>
   
   <jsp:include page="../content/header2.jsp"></jsp:include> 
<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");

%>

<% 
		List<UserRole> userroles=user.getUserroles();
		Boolean dataentryop=false;
		Boolean isadmin=false;
		for(UserRole userrole:userroles){
			if(userrole.getLk().getLk_longname().equals("DMSMaker") || userrole.getLk().getLk_longname().equals("DMSChecker") || userrole.getLk().getLk_longname().equals("DMSVerifier")){
				dataentryop=true;
			}
			if(userrole.getLk().getLk_longname().equals("DMSAdmin")){
				isadmin=true;
			}
		}
%>

<div id="content" class="content" ng-controller="dashboardCtrl">   
<%
if(isadmin){ %>
	<div class="row">
	<div class="col-md-6">
			        <!-- begin panel -->
                    <div class="panel panel-inverse" data-sortable-id="table-basic-1">
                        <div class="panel-heading">
                            <h4 class="panel-title">Documents Information</h4>
                        </div>
                        <div class="panel-body">
       						<canvas id="pie" class="chart chart-pie" chart-data="data" chart-labels="labels" chart-options="options">
							</canvas>                    
                        </div>
                    </div>
                    <!-- end panel -->
			        <!-- begin panel -->
                    
                    <!-- end panel -->
	</div>
	<div class="col-md-6">
			        <!-- begin panel -->
             <div class="panel panel-inverse" data-sortable-id="table-basic-1">
                 <div class="panel-heading">
                     <h4 class="panel-title">Folders Data</h4>
                 </div>
                 <div class="panel-body">
						<table st-table="foldersCollection" st-safe-src="foldersdata"   class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
						<th>Folder Name</th>
						<th>Size On Disk</th>
						<th>Total No. Of Documents</th>
						
						</tr>
						</thead>
						<tbody>
						<tr ng-repeat="data in foldersCollection">
						<td>{{data.display_name}}</td>
						<td>{{data.sizeondisk}}</td>
						<td>{{data.noofdocs}}</td>
						</tr>
						</tbody>
						<tfoot>
									<tr>
										<td colspan="5" class="text-center">
											<div st-pagination="" st-items-by-page="10"  st-displayed-pages="4" ></div>
										</td>
									</tr>
						</tfoot>
						</table>                   
                 </div>
             </div>
                    <!-- end panel -->
			        <!-- begin panel -->
                    
                    <!-- end panel -->
	</div>
	 
	</div>
<% 
}
%>

</div>

	<% if(isadmin){ %>
	<script src="${pageContext.request.contextPath}/assets/plugins/chart.js/dist/Chart.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/angular-chart.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/admindashboard.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
	<% }else{%>
	<script type="text/javascript"src="${pageContext.request.contextPath}/js/scripts/controllers/userdashboard.js"></script>
	<% }  %>
	<script>
		$(document).ready(function() {
			App.init();
			ChartJs.init();
		});
	</script>	
</body>
</html>