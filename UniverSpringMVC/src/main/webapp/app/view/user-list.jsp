
<div class="container" ng-controller="UserController as ctrl">
	<div class="panel panel-default" id='form-editor'
		style='display: none;'>
		<div class="panel-heading">
			<span class="lead">Add / Edit Form </span>
		</div>
		<div id='formEditor' class="form-horizontal" style='margin-top: 20px;'>
			<form ng-submit="ctrl.submit()" name="userForm">
				<input type="hidden" ng-model="ctrl.user.id" />
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="login">Login</label>
					<div class="col-xs-10">
						<input type="text" ng-model="ctrl.user.login" id="login"
							name='login' class="form-control"
							placeholder="Enter your login" required />
						<div class="has-error" ng-show="userForm.$dirty">
							<div ng-show="userForm.login.$error.required">This is a
								required field</div>
						</div>

					</div>
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="password">Password</label>
					<div class="col-xs-10">
						<input type="password" ng-model="ctrl.user.password" id="password"
							name='login' class="form-control"
							placeholder="Enter your password" required />
						<div class="has-error" ng-show="userForm.$dirty">
							<div ng-show="userForm.password.$error.required">This is a
								required field</div>
						</div>

					</div>
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="admin">Is admin</label>
					<div class="col-xs-10">
						<input id='admin' ng-model='ctrl.user.admin' name='admin' type="checkbox" />
						<p ng-show=ctrl.admin.id class='form-controll'>
							<span>{{ctrl.user.admin ? "Admin" : "User"}} </span>
						</p>
					</div>
				</div>

				<div class="form-group">
					<div class='col-xs-12'>
						<input id='form-editor-submit-btn' type="submit" 
							value="{{!ctrl.user.id ? 'Add' : 'Update'}}"
							class="col-xs-2 col-xs-offset-7 btn btn-primary"
							style='margin-right: 10px;'>
						<button type="button" ng-click="ctrl.reset()"
							class="col-xs-2 btn btn-danger" ng-disabled="userForm.$pristine">Reset
							Form</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">{{ctrl.user.login}}</h4>
				</div>
				<div class="modal-body">
					<h4>Are you sure you want to delete?</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button ng-click="ctrl.remove(ctrl.user.id)" id='modalSave-btn'
						type="button" class="btn btn-primary">Yes</button>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Users </span>
		</div>
		<a id='form-editor-btn'
			class="btn btn-success"> <span class="glyphicon glyphicon-plus"></span>User
			add
		</a>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Login</th>
						<th>Role</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="u in ctrl.users">
						<td><span ng-bind="u.login"></span></td>
						<td><span>{{u.admin ? "admin" : "user"}}</span></td>
						<td>
							<button type="button" id='edit-btn' ng-click="ctrl.edit(u.id)"
								class="btn btn-success">Edit</button>
							<button type="button" ng-click="ctrl.getUser(u.id)"
								data-toggle="modal" data-target="#myModal"
								class="btn btn-danger">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

