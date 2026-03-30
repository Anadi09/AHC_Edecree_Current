<div class="modal-body">
    <div class="panel-body">
        <div class="table-responsive">
            <table id="data-table" class="table table-striped table-bordered">
                <thead>
                    <tr>
                    	<th>Sr No.</th>
                        <th>Application name</th>
                        <th>Application no</th>
                        <th>Application year</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="row in applicationTypeList" class="odd gradeX">
                    	<td>{{$index+1}}</td>
                        <td>{{row[1]}}</td>
                        <td>{{row[3]}}</td>
                        <td>{{row[4]}}</td>
                        <td align="center">
                            <button id="Submit" type="submit" class="btn btn-success" data-toggle="modal" ng-click="addAmendment(row)">
                                Allow</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>