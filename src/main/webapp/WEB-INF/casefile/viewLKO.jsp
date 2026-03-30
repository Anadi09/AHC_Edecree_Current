<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.eDecree.model.ObjectMaster"%>
<%@ page import="com.eDecree.model.User"%>

<%
User user = null;
if (session.getAttribute("USER") != null)
	user = (User) session.getAttribute("USER");
 String role = user.getUserroles().get(0).getLk().getLk_longname();
%>

<jsp:include page="../content/header2.jsp"></jsp:include>

	<div id="content" class="content" >
		<div class="container-fluid" ng-controller="CaseFileCtrl" >
			<div class="row">
				<div class="panel panel-inverse" style="min-height:1000px;">
					<div class="panel-heading">
					<div class="panel-heading-btn">
			            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>			            
			        </div>
	                     <h4 class="panel-title"style="font-size: 4mm;">Case File Details &nbsp{{casefile.caseType.ct_label}} &nbsp/{{casefile.fd_case_no}} &nbsp/{{casefile.fd_case_year}}</h4>
	                     <c:if test="${isImpugnedOrder==1}">
	                     <h4 class="panel-title">Impugned Order Details ${casetype}/${caseno}/${caseyear}</h4>
	                     </c:if>
	                </div>
	                <div class="panel-body">
	                	<input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id"> 
						<input type="hidden" class="form-control" value=${document_name} id="document_name" name="document_name">
						<div class="col-md-4">
							<jsp:include page="treeview.jsp"></jsp:include>
							
						</div>
						<% if(role.equals("DMSAdmin") || role.equals("ECOURT")) {%>
					<div class="col-md-8" id="myDiv">
						<jsp:include page="viewer.jsp"></jsp:include>
					</div>
					<%} %>

					<% if(role.equals("PS") || role.equals("Review_Officer") || role.equals("Download Copy") || role.equals("Judge"))  {%>
					<div class="col-md-8">
						<jsp:include page="viewer1.jsp"></jsp:include>
					</div>
					<%} %>
					
					
	                </div>
	            </div>
			</div>
			

	<!-- Sushant -->
		<div id="mySidenav" class="sidenav" title="Notes">

			<textarea rows="5" id="comment" placeholder="insert some text"
				ng-model="nt.nt_notes" ng-change="saveNotes(nt)"></textarea>

		</div>

		<!--sushant  -->
			
			
		</div>
	
	</div>
	<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
</div>

	
	
	
</body>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/scripts/controllers/CaseFileViewControllerLKO.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Smart-Table-master/dist/smart-table.js"></script>
	
	
	
<style>


.st-sort-ascent:before {
	content: '\25B2';
}

.st-sort-descent:before {
	content: '\25BC';
}

/* 
/* 	    /*Suhsant  */
.sidenav {
	height: 100%;
	width: 100%;
	position: fixed;
}

textarea {
	height: 100%;
	width: 100%;
	font-size: 28px;
	border-style: none;
	border-color: Transparent;
}

.ui-widget.sidenav-dialog {
	font-family: Verdana, Arial, sans-serif;
	font-size: 1em;
}

.ui-widget-content.sidenav-dialog {
	background: #F9F9F9;
	border: 1px solid #90d93f;
	color: #222222;
}

.ui-dialog.sidenav-dialog {
	left: 0;
	outline: 0 none;
	padding: 0 !important;
	position: absolute;
	top: 0;
}

.ui-dialog.sidenav-dialog .ui-dialog-content {
	background: none repeat scroll 0 0 transparent;
	border: 0 none;
	overflow: auto;
	position: relative;
	padding: 0 !important;
	margin: 0;
}

.ui-dialog.sidenav-dialog .ui-widget-header {
	background: #b0de78;
	border: 0;
	color: #fff;
	font-weight: normal;
}

.ui-dialog.sidenav-dialog .ui-dialog-titlebar {
	padding: 0.1em .5em;
	position: relative;
	font-size: 1em;
	color: #191919 !important;
}

/*Sushant  */

/* ------------------------------ */
</style>
	
	
	<script>
		$(document).ready(function() {
			App.init();
			
		});
		
		$(function() {
			$("#mySidenav").dialog({
				autoOpen : false,
				height : 200,
				width : 350,
				resizable : true,				 
				position : {
					my : "left top",
					at : "right bottom"
				}, 
				dialogClass : 'no-close sidenav-dialog'
			})/* 
			.parent().draggable({
             containment: '#content'
           }) */;
			$("#btn_click").click(function() {
				$("#mySidenav").dialog("open");
			});
			$("#mySidenav").on('click', '#closebtn', function() {
				setTimeout(function() {
					$("#mySidenav").dialog("close");
				}, 1000);
			});

		});	
		
		
		
		
	</script>
</html>