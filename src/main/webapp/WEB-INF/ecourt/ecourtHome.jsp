<jsp:include page="../content/header2.jsp"></jsp:include>
<%@page import="com.dms.model.Sub_Benches"%>
<%@ page import="com.dms.model.User"%>	
<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");
	%>
	
<div id="content" class="content" ng-controller="OtpController"  
 style="background-image: url('/dms/images/bg1024.jpg');background-repeat: no-repeat;background-attachment: fixed; background-size: cover;height: 590px;">
		<div class="form-group text-center">
		 
						    <span style="font-size:20px;" >
						    <p style="text-align:center">Bench Code</p>
						    
						    
						     <a href=<%= "../getLinkecourt/"+user.getCourtMaster().getCm_bench_id()+""%> target="_blank">
						    <p style="text-align:center"><%="Bench "+user.getCourtMaster().getCm_bench_id()+". "+user.getCourtMaster().getCm_judges_name()%></p></a>
						    <% 
						    for(Sub_Benches sb : user.getCourtMaster().getSubBenches()){
	%>
						    <a href=<%= "../getLinkecourt/"+sb.getSb_bench_id()+""%> target="_blank">
						    <p style="text-align:center"><%=""+sb.getSb_judge_name()+"("+sb.getSb_bench_id()+")" %></p></a>
						      <% 
						    }
	%>
						    </span>
						</div> 
		</div>
		</div>
		</div>
		
	
	</div>
</div>


</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/OtpController.js?v=6"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
	
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();

	});
</script>
</html>