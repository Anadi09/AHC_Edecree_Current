<div class="modal-body">
    <div class="panel-body">
        <div class="table-responsive">
            <table id="data-table" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Checklist</th>
                        <th>Remark</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="row in checkListHistory" class="odd gradeX">
                        <td>{{row.checklist.name}}</td>
                        <td>{{row.cm_remark}}</td>
                        <td>{{row.cm_cr_date | date:"dd/MM/yyyy HH:mm:ss"}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>