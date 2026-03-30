<jsp:include page="../content/header2.jsp"></jsp:include>

	<div id="content" class="content">
		<div class="container-fluid" ng-controller="CauseListHomeCtrl" >
		</div>
	</div>
</div>
	
</body>
	<% //if(role.equals("Advocate") || role.equals("InPerson")){ %>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/scripts/controllers/CauseListHomeController.js"></script>
	<% //}else{ %>
<!-- 	<script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath}/js/scripts/controllers/scrutinyHomeController.js"></script> --%>
	<%//} %>
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			
		});
	</script>
</html>