
<form class="form-horizontal reduce-gap" name="masterForm" novalidate role="form">      
<div class="modal-body">

<div ng-show="errorlist.length!=0" class="alert alert-block alert-danger">
	<ul>
    <span ng-repeat="errors in errorlist"  >
	        <span ng-repeat="n in errors track by $index">
	        	<li>{{(n)}}</li>
	        </span>
	    </span>
    </ul>
</div>

<div class="row">
<div class="col-md-12 ">
 <ul class="nav nav-pills">
    <li class="active"><a data-toggle="pill" href="#repositories">Repository Permission</a></li>
    <li><a data-toggle="pill" href="#folders">Folder Permission</a></li>
 </ul>
  
  <div class="tab-content">
    <div id="repositories" class="tab-pane fade in active">
      <table st-table="repositories" class="table table-striped table-bordered table-hover">
		<thead>
		<tr>
		<th st-sort="value">Name</th>
		<th >Path</th>
		<th st-sort="status">Status</th>
		<th style=" width: 250px;" class="text-center">Action
		</th>					
		</tr>
		</thead>
		<tbody>
		<tr ng-repeat="data in repositories">
		<td>{{data.name}}</td>
		<td>{{data.folderPath}}</td>
		<td><span ng-if="data.status==1">Assigned</span><span ng-if="data.status==0">Not Assigned</span></td>
		<td>
		<a  class="btn btn-info btn-xs" ng-click="changeStatus(data)" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><span ng-if="data.status==1">Revoke</span><span ng-if="data.status==0">Assign</span></a>
		<span ng-if="data.assignAll"><a  class="btn btn-info btn-xs" ng-click="updateAllChildrens(data)" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Assign All</a></span>
		</td>
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
    <div id="folders" class="tab-pane fade">
      <table st-table="folders" class="table table-striped table-bordered table-hover">
		<thead>
		<tr>
		<th st-sort="value">Name</th>
		<th >Path</th>
		<th st-sort="status">Status</th>
		<th style=" width: 250px;" class="text-center">Action
		</th>					
		</tr>
		</thead>
		<tbody>
		<tr ng-repeat="data in folders">
		<td>{{data.name}}</td>
		<td>{{data.folderPath}}</td>
		<td><span ng-if="data.status==1">Assigned</span><span ng-if="data.status==0">Not Assigned</span></td>
		<td>
		<a  class="btn btn-info btn-xs" ng-click="changeStatus(data)" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span><span ng-if="data.status==1">Revoke</span><span ng-if="data.status==0">Assign</span></a>
		<span ng-if="data.assignAll"><a  class="btn btn-info btn-xs" ng-click="updateAllChildrens(data)" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Assign All</a></span>
		</td>
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
</div>
</div>
</div>


</form>

 