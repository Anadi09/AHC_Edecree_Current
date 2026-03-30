<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 
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


        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="col-sm-offset-2">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
            
                
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Modules <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${pageContext.request.contextPath}/repository">Repository</a></li>  
		            <li><a href="${pageContext.request.contextPath}/folder">Folder</a></li>
		          </ul>
                </li>  
            </ul>
        </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav  >
<div class="loading-dialog" data-loading>            
</div>