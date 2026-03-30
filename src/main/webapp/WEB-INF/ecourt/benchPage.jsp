<jsp:include page="../content/header2.jsp"></jsp:include>
<%@ page import="com.dms.model.User"%>	
<% 
User user = null;
if(session.getAttribute("USER")!=null)
	 user = (User)session.getAttribute("USER");
	%>
	
<div id="content" class="content" ng-controller="ECourtHomeCtrl" >
		<div class="form-group text-center">
						    <span style="font-size:80px;" >
						    <p style="text-align:center">Bench Code</p>
						    
						    
						     <a href=<%= "../getLinkecourt/"+user.getCourtMaster().getCm_bench_id()+""%>>
						    <p style="text-align:center"><%=""+user.getCourtMaster().getCm_judges_name()+")" %></p></a></br>
						    <a href=<%= "../getLinkecourt/"+user.getCourtMaster().getCm_bench2_id()+""%>>
						    <p style="text-align:center"><%=""+user.getCourtMaster().getCm_judges_name2()+")" %></p></a>
						   
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