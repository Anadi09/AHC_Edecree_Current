<div class="panel panel-inverse">

    <div class="panel-body">
        <div class="table-responsive">
            <table id="data-table" class="table table-striped table-bordered nowrap table-hover" width="100%">
                <thead>
                    <tr>
                        <th style="text-align: center;">Type</th>
                        <th style="text-align: center;">Appl Name</th>
                        <th style="text-align: center;">Appl No</th>
                        <th style="text-align: center;">Appl Year</th>
                        <th style="text-align: center;">User name</th>
                        <th style="text-align: center;">Full name</th>
                        <th style="text-align: center;">Amendment Status</th>
                        <th style="text-align: center;">Date</th>
                        <th style="text-align: center;">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="data in amendments" class="odd gradeX">
                        <td align="center"><span ng-show="data.am_type=='P'">Petition</span><span ng-show="data.am_type=='A'">Application</span></td>
                        <td align="center"><span>{{data.applicationType.at_name}}</span></td>
                        <td align="center"><span>{{data.am_document_no}}</span></td>
                        <td align="center"><span>{{data.am_document_year}}</span></td>
                        <td align="center">{{data.userMaster.username}}</td>
                        <td align="center">{{data.userMaster.um_fullname}}</td>
                        <td align="center">{{data.lkStatus.lk_longname}}</td>
                        <td align="center">{{data.am_created | date:"dd/MM/yyyy"}}</td>
                        <td align="center">
                        <button ng-show="data.lkStatus.lk_longname=='Amendment created'" id="Submit" type="submit" class="btn btn-success" ng-click="deleteAmendment(data.am_id)">
                                Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>