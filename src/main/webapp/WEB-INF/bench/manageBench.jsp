<%@ include file="../content/header2.jsp"%>
    <html>

    <body>
        <div id="content" class="content">
            <div class="container-fluid" ng-controller="BenchController" oncontextmenu="return false;">

                <div class="row">
                    <!-- begin col-12 -->
                    <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                            </div>
                            <h4 class="panel-title">Manage Benches</h4>
                        </div>
                        <div class="panel-body">
                        
                        <div class="row">
						<div class="col-md-12 ">
							<button type="button" class="btn btn-primary btn-sm pull-right" ng-click="resetModel()" data-toggle="modal" data-target="#user_Modal">
								<span class="glyphicon glyphicon-plus-sign"></span> Add New Court
							</button>
						</div>
					</div>
                            <div class="table-responsive">
                             
                                <table id="data-table" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                        
                                            <th>Court Name</th>
                                            <th>Bench Id</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="row in courtList" ng-include="getData(row)"  class="odd gradeX">
                                        
                                        <script type="text/ng-template" id="view">
                                        	
                                            <td>{{row.cm_name}}</td>
 <td>{{row.cm_bench_id}} <br/>
<span ng-repeat="data in row.subBenches">
{{data.sb_bench_id}}</sapn> <span ng-show=row.cm_bench_id!=null>
                                 <a ng-click="deleteSubBench(data.sb_id)"> 
                                 <i style="cursor: pointer; font-size: 16px;" class="fa fa-trash-o"></i>
                                 </a> 
                                 </span><br>
</td>
<td>
 <button class="btn btn-success btn-sm" ng-click="editBench(row)">Edit Bench</button>
<button class="btn btn-success btn-sm" ng-disabled="!row.cm_bench_id" ng-click="setMasterdata(row)" data-toggle="modal" data-target="#user_Modal" >
<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Add More Benches</button>

<button class="btn btn-success btn-sm"  ng-click="setMasterdata(row)" data-toggle="modal" data-target="#nextBench_model" >
<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Add Bench For Next Date</button>
</td>
</script>
                                          <!--  <td>{{row.cm_bench_id}}</td> -->
                                            <script type="text/ng-template" id="edit">

                                            <td>{{row.cm_name}}</td>
                                             <td><input type = "text" ng-model = "row.cm_bench_id"></td>
 <td><button class="btn btn-success btn-sm" ng-click="updateBench($index,row)">Update Bench</button>
 <button class="btn btn-success btn-sm" ng-click="reset()">Reset</button></td>
</script>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                          <div class="modal fade" id="user_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"> <span ng-if="!masterentity.um_id"><strong> Add New Bench</strong></span>
						        
						      </div>	     
					  			<%@ include file="../bench/master_bench.jsp"%>
						    </div>
						  </div>
					</div>	
					
					
					  <div class="modal fade" id="nextBench_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  <div class="modal-dialog modal-lg">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel"> <span ng-if="!masterentity.um_id"><strong> Add Next Date Bench</strong></span>
						       
						      </div>	     
					  			<%@ include file="../bench/nextDateBench.jsp"%>
						    </div>
						  </div>
					</div>	
                            

                        </div>
                    </div>

                    <!-- end panel -->

                    <!-- end col-12 -->
                </div>
            </div>
        </div>

        <!-- end row -->
    </body>

    <!-- ================== END PAGE LEVEL JS ================== -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/BenchController.js?v=2"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootbox.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>

    <script>
        $(document).ready(function() {
            $("#txtEditor").Editor();
            App.init();

        });
    </script>

    <!-- <link href="editor.css" type="text/css" rel="stylesheet"/> -->

    </html>