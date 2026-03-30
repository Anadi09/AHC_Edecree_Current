<div class="panel panel-inverse overflow-hidden">
    <div class="panel-heading">
        <h3 class="panel-title">
                            <a class="accordion-toggle accordion-toggle-styled collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOrderReport">
									    <i class="fa fa-plus-circle pull-right"></i> 
										Order Report Data
									</a>
                        </h3>
    </div>
    <div id="collapseOrderReport" class="panel-collapse collapse in">
        <div class="panel-body">
            <div class="table-responsive">
                <table id="data-table" class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Report</th>
                            <th>Date</th>
                        </tr>
                        <tr ng-repeat="data in orderReports">
                            <td>{{data.ord_remark}}</td>
                            <td>{{data.ord_created | date:'dd/MM/yyyy'}}</td>
                        </tr>
                    </thead>

                </table>
            </div>
        </div>
    </div>
</div>