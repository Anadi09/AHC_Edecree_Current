<div class="panel panel-inverse">

    <div class="panel-body">
        <div class="table-responsive">
            <table id="data-table" st-table="displayedCollection" class="table table-striped table-bordered nowrap" width="100%">
                <thead>
                    <tr>
                        <th style="text-align: center;" width="80%" st-sort="fd_case_year">Advocate name
                        </th>
                        <th style="text-align: center;" width="20%" st-sort="fd_case_type">Action</th>
                    </tr>
                    <tr>
                        <th>
                            <input type="text" name="username" class="form-control" ng-model="search.username" />
                        </th>
                        <th>
                            <button id="search" type="submit" class="btn btn-primary btn-sm pull-left" ng-click="searchCaseFile()" data-toggle="modal" style="size: 2px">Search</button>
                        </th>
                    </tr>
                </thead>
            </table>
            <table id="data-table" class="table table-striped table-bordered nowrap table-hover" width="100%">
                <thead>
                    <tr>
                        <th style="text-align: center;" width="20%">Username</th>
                        <th style="text-align: center;" width="20%">Full name</th>
                        <th style="text-align: center;" width="20%">Email</th>
                        <th style="text-align: center;" width="20%">Mobile</th>
                        <th style="text-align: center;" width="20%">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="data in searchedusers" class="odd gradeX">
						<td align="center">{{data.username}}</td>
						<td align="center">{{data.um_fullname}}</td>
						<td align="center">{{data.um_email}}</td>
						<td align="center">{{data.um_mobile}}</td>
						<td align="center">
						<button id="Submit" type="submit" class="btn btn-success"
							data-target="#usersModel" data-toggle="modal" ng-click="addUser(data,$index)" >
						Add</button>
						</td>
					</tr>

                    <tr ng-show="searcheduser.username!= undefined  || searcheduser.username!= null" class="odd gradeX">
                        <td align="center">{{searcheduser.username}}</td>
                        <td align="center">{{searcheduser.um_fullname}}</td>
                        <td align="center">{{searcheduser.um_email}}</td>
                        <td align="center">{{searcheduser.um_mobile}}</td>
                        <td align="center">
                            <button id="Submit" type="submit" class="btn btn-success" data-target="#usersModel" data-toggle="modal" ng-click="addUser(searcheduser,$index)">Add</button>
                        </td>
                    </tr>
                    <tr ng-show="useNotFoundFlag==true">
                        <td colspan="10">
                            <div class="alert alert-danger">No Records Found</div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="panel panel-inverse">
    <div class="panel-heading">
        <h4 class="panel-title">Case Advocate List</h4>
    </div>
    <div class="panel-body">
        <div class="table-responsive">
            <table id="data-table" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Full name</th>
                        <th>Email</th>
                        <th>Mobile</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- <tr ng-repeat="row in users" class="odd gradeX">
						<td>{{row.username}}</td>
						<td>{{row.um_fullname}}</td>
						<td>{{row.um_email}}</td>
						<td>{{row.um_mobile}}</td>
						<td align="center">
							<button id="Submit" type="submit" class="btn btn-success"
								data-target="#ApplicationsModel" data-toggle="modal"
								ng-click="setUser(row)">View Applications</button>
							<button id="Submit" type="submit" class="btn btn-success"
								ng-click="allowPetition(row)">Allow Petition</button>
							<button id="Submit" ng-show="row.flag==true" type="submit"
								class="btn btn-success" data-toggle="modal"
								ng-click="removeUser(row,$index)">Remove</button>

						</td>
					</tr> -->

                    <tr ng-repeat="row in users" class="odd gradeX">

                        <td>{{row.username}}</td>
                        <td>{{row.um_fullname}}</td>
                        <td>{{row.um_email}}</td>
                        <td>{{row.um_mobile}}</td>
                        <td align="center">
                            <button id="Submit" type="submit" class="btn btn-success" data-target="#ApplicationsModel" data-toggle="modal" ng-click="setUser(row)">View Applications</button>
                            <button id="Submit" type="submit" class="btn btn-success" ng-click="allowPetition(row)">Allow Petition</button>
                            <button id="Submit" ng-show="row.flag==true" type="submit" class="btn btn-success" data-toggle="modal" ng-click="removeUser(row,$index)">Remove</button>

                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="modal fade" id="ApplicationsModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content" style="height: 400px; overflow: auto;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
							<strong>Applications</strong>
						</h4>
                    </div>
                    <%@ include file="../amendment/applicationslist.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>