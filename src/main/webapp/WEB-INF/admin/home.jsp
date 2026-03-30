<jsp:include page="../content/header2.jsp"></jsp:include>
<div id="content" class="content">
   <div class="container-fluid" ng-controller="AdminHomeCtrl" >
   
    <!--   <div class="col-md-3 col-sm-6">
         <div class="widget widget-stats bg-green">
            <div class="stats-icon">
               <i class="fa fa-desktop"></i>
            </div>
            <div class="stats-info">
               <h4>DMS Cases</h4>
               <h4>{{freshcount}}</h4>
            </div>
            <div class="stats-link">
               <a targert="_new" href="/dms/admin/dmscase">View Detail <i
                  class="fa fa-arrow-circle-o-right"></i></a>
            </div>
         </div>
      </div>
      <div class="col-md-3 col-sm-6">
         <div class="widget widget-stats bg-green">
            <div class="stats-icon">
               <i class="fa fa-desktop"></i>
            </div>
            <div class="stats-info">
               <h4>Online Case Filing</h4>
               <h4>{{dailycount}}</h4>
            </div>
            <div class="stats-link">
               <a targert="_new" href="/dms/admin/onlinecasefiling">View Detail <i
                  class="fa fa-arrow-circle-o-right"></i></a>
            </div>
         </div>
      </div> -->
      
   </div>
</div>
</body>
<% //if(role.equals("Advocate") || role.equals("InPerson")){ %>
<script type="text/javascript"
   src="${pageContext.request.contextPath}/js/scripts/controllers/AdminHomeController.js"></script>
<% //}else{ %>
<!-- 	<script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath}/js/scripts/controllers/scrutinyHomeController.js"></script> --%>
<%//} %>
<script type="text/javascript"
   src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
   $(document).ready(function() {
   	App.init();
   	
   });
</script>
</html>