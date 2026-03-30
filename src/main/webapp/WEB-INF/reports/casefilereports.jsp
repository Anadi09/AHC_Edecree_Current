<%@ include file="../content/header2.jsp"%>

<div id="content" class="content">
	<div class="container-fluid" style="background-color:white;" ng-controller="caseFileReportController" ng-init="getCaseTypes();">

		<div style="width: 500;height: 600;">
		
		<!-- <div class="container"> -->
  <h2>Case File Reports Graphical Representation</h2>
   </br>
            </br>
   
  <!-- <p>The panel-group class clears the bottom-margin. Try to remove the class and see what happens.</p> -->
  
    <!--       <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
  <div class="btn-group mr-2" role="group" aria-label="First group">
    <button type="button" class="btn btn-primary" ng-click="caseStageReport()" id ="caseStage">Case Stage Report</button>
    <button type="button" class="btn btn-primary" ng-click="cavStageReport()" id ="cavStage">Caveat Stage Report</button>
    <button type="button" class="btn btn-primary" ng-click="appStageReport()" id ="appStage">Application Stage Report</button>
     </div>
     </div>
     </br>
           </br>
            </br> -->
     
    <!-- <div class="row">
          <div class="col-md-2 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>STAGE</th>
            <th>COUNT</th>
            
        </tr>
    </thead>
    <tbody>
        <tr ng-repeat="data in caseFileReportData">
          <tr ng-repeat="data in caseStageReport" >
            <td>{{data.stage}}</td>
             <td>{{data.count}}</td>
           
        </tr>
        
                 
    </tbody>
</table>
</div>

        <div class="col-md-9 ">
         <canvas id="myChart7" ></canvas>
         </div>
          </div> -->
    <!-- <button type="button" class="btn btn-primary">4</button> -->
 
  
  
   <!--  <div class="panel panel-primary">
      <div class="panel-heading">Panel Header</div>
      <div class="panel-body">Panel Content</div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">Panel Header</div>
      <div class="panel-body">Panel Content</div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">Panel Header</div>
      <div class="panel-body">Panel Content</div>
    </div> -->
  

		
       <!--  <canvas id="myChart1" ></canvas>
     
        <canvas id="myChart2" ></canvas> -->
       <!--  </br>
           </br>
            </br> -->
        <div  class="border border-info">
         <div class="panel panel-primary" >
           <div class="panel-heading">Total Application, Caveat and Case Report	 </div>
             <div class="panel-body">
        <div class="row">
          <div class="col-md-2 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>NAME</th>
            <th>TOTAL COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
      <!--   <tr ng-repeat="data in caseFileReportData"> -->
          <tr>
            <td>Application</td>
             <td>{{caseFileReportData.applicationCount}}</td>
           
        </tr>
        <tr>
         <td>Caveat</td>
            <td>{{caseFileReportData.caveatCount}}</td>
        </tr>
        <tr>
         <td>Case</td>
            <td>{{caseFileReportData.fileCount}}</td>
        </tr>
                 
    </tbody>
</table>
</div>

        <div class="col-md-9 ">
         <canvas id="myChart3" ></canvas>
         </div>
          </div>
             </div>
          </div>
          </div>
          
        </br>
           </br>
            </br>
             </br>   
          
          
         
          <div class="panel panel-primary">
           <div class="panel-heading">Total of Case Registration According to Case Types</div>
             <div class="panel-body">
           <div class="row">
            <div class="col-md-2 col-md-offset-1">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>CASE TYPE</th>
            <th>TOTAL COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
        <tr ng-repeat="data in caseFileReportByType">
         
            <td>{{data.name}}</td>
             <td>{{data.count}}</td>
           
       
        </tr>
         
                 
    </tbody>
</table>
</div>
        <div class="col-md-9">
         <canvas id="myChart4" ></canvas>
         </div>
          </div>
            </div>
          </div>
          
          <div class="panel panel-primary" >
           <div class="panel-heading">Total of Case ,Application and Caveat Registration Periodically</div>
             <div class="panel-body">
          
           <div class="row">
          <div class="col-md-2">
         </br>
           </br>
            </br>
             </br>    
        <table class="table table-borderless table-hover">
    <thead>
        <tr>
            <th>YEAR</th>
            <th>CASE COUNTS</th>
              <th>APP COUNTS</th>
                <th>CAVEAT COUNTS</th>
            
        </tr>
    </thead>
    <tbody>
      <!--   <tr ng-repeat="data in caseFileReportData"> -->
         <tr ng-repeat="data in caseFileReportByYear">
         
            <td>{{data.year}}</td>
             <td>{{data.casecount}}</td>
             <td>{{data.appcount}}</td>
             <td>{{data.caveatcount}}</td>
           
       
        </tr>
                 
    </tbody>
</table>
</div>
        <div class="col-md-8 col-md-offset-2 ">
         <canvas id="myChart5" ></canvas>
         </div>
          </div>
           </div>
          </div>
          
          </br>
           </br>
            </br>
             </br>
          
       
         </div>
         </div>
    </div>
    </div>
    </div>
    </div>

<!-- </div> -->
<!-- end row -->
</body>


<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/caseFileReportController.js"></script>	
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/angularJs/dirPagination.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/chartModule/Chart.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();
	});
	
	
</script>



</html>