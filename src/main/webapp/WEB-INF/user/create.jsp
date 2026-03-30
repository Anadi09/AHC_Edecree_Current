<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css'>

<title>Insert title here</title>
</head>
<body>
	<!-- Navigation -->
    <nav class="navbar navbar-inverse " role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">DMS</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="repository/manage">Repository</a>
                    </li>
                    <li>
                        <a href="#">Login</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
   <ol class="breadcrumb">
	  <li><a href="#">Home</a></li>
	  <li><a href="#">Create Repository</a></li>	  
	</ol>
	<div class="container">		
		<form:form method="POST" class="form-horizontal" action="/dms/repository/manage" commandName='repository'>
			  <div class="form-group">		 
		  			<form:label path="name" class="control-label col-sm-2">Name</form:label>
		  			<div class="col-sm-10"> 
		        	<form:input path="name" class="form-control"  />  
		        	</div>
			  </div>
			  <div class="form-group">		 
		  			<form:label path="path" class="control-label col-sm-2">Path</form:label>
		  			<div class="col-sm-10"> 
		        	<form:input path="path" class="form-control"  />  
		        	</div>
			  </div>
			  <div class="form-group">		 
		  			<form:label path="workflow" class="control-label col-sm-2">Workflow</form:label>
		  			<div class="col-sm-10"> 
		        	<form:input path="workflow" class="form-control"  />  
		        	</div>
			  </div>
			  <div class="form-group"> 
    			<div class="col-sm-offset-2 col-sm-10">
			  <input class="btn btn-primary" type="submit" value="Submit"/>	
			  </div> 
			  </div> 
		</form:form>			
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
