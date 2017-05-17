
<div class="container" ng-controller="SubjectController as ctrl">
	<div class="panel panel-default" id='form-editor'
		style='display: none;'>
		<div class="panel-heading">
			<span class="lead">Add / Edit Form </span>
		</div>
		<div id='formEditor' class="form-horizontal" style='margin-top: 20px;'>
			<form ng-submit="ctrl.submit()" name="subjectForm">
				<input type="hidden" ng-model="ctrl.subject.id" />
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="student">Group</label>
					<div class="col-xs-10">
						<select name='group' class='form-control'
							data-ng-options="group for group in ctrl.subject.groupNumberList"
							data-ng-model="ctrl.subject.groupNumber" required>
							<option value="">Select group</option> 
							</select>
							<div class="has-error" ng-show="subjectForm.$dirty">
							<span class="error" 
							ng-show="subjectForm.group.$dirty && subjectForm.group.$invalid">Select group</span> 
							</div>
					</div>
				</div>


				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="LName">Subject
						name </label>
					<div class="col-xs-10">
						<input type="text" ng-model="ctrl.subject.subjectName" id="sname" name='sub'
							class="form-control" placeholder="Enter your subject name"
							required />
						<div class="has-error" ng-show="subjectForm.$dirty">
							<span ng-show="subjectForm.sub.$error.required">This is a
								required field</span> 
						</div> 
					</div>
				</div>

				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="student">Teacher</label>
					<div class="col-xs-10">
						<select name='t' class='form-control'
							data-ng-options="teacher.id as teacher.fio for teacher in ctrl.subject.teacherList"
							data-ng-model="ctrl.subject.selectedTeacher"
							required>
							<option value="">Select teacher</option> 
							</select>
							<div class="has-error" ng-show="subjectForm.$dirty">
							<span class="error" 
							ng-show="subjectForm.t.$dirty && subjectForm.t.$invalid">Select teacher</span> 
							</div>
					</div>
				</div>


				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="subjectType">Subject
						type</label>
					<div class="col-xs-10">
						<select name='type' class='form-control'
							data-ng-options="type for type in ctrl.subject.subjectTypeList"
							data-ng-model="ctrl.subject.selectedSubjectType"
							required>
							<option value="">Select type</option> 
							</select>
							<div class="has-error" ng-show="subjectForm.$dirty">
							<span class="error" 
							ng-show="subjectForm.type.$dirty && subjectForm.type.$invalid">Select teacher</span> 
							</div>

					</div>
				</div>

				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="semestrs">semestr
					</label>
					<div class="col-xs-10">
						<input type="number" ng-model="ctrl.subject.semestr" id="semestr" name='sem'
							class="form-control" placeholder="Enter semestr" required />
						 <div class="has-error" ng-show="subjectForm.$dirty">
							<span ng-show="subjectForm.sem.$error.required">This is a
								required field</span> 
						</div> 
					</div>
				</div>

				<div class="form-group">
					<div class='col-xs-12'>
						<input id='form-editor-submit-btn' type="submit"
							value="{{!ctrl.subject.id ? 'Add' : 'Update'}}"
							class="col-xs-2 col-xs-offset-7 btn btn-primary"
							style='margin-right: 10px;'>
						<button type="button" ng-click="ctrl.reset()"
							class="col-xs-2 btn btn-danger"
							ng-disabled="subjectForm.$pristine">Reset Form</button>
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
					<h4 class="modal-title" id="myModalLabel">{{ctrl.subject.subjectName}}</h4>
				</div>
				<div class="modal-body">
					<h4>Are you sure you want to delete?</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button ng-click="ctrl.remove(ctrl.subject.id)" id='modalSave-btn'
						type="button" class="btn btn-primary">Yes</button>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Subjects </span>
		</div>
		<a id='form-editor-btn' ng-click="ctrl.fillSelected()"
			class="btn btn-success"> <span class="glyphicon glyphicon-plus"></span>Subject
			add
		</a>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Group</th>
						<th>Subject</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="s in ctrl.subjects">
						<td><span ng-bind="s.groupNumber"></span></td>
						<td><span ng-bind="s.subjectName"></span></td>
						<td><span ng-bind="s.selectedSubjectType"></span></td>

						<td>
							<button type="button" id='edit-btn' ng-click="ctrl.edit(s.id)"
								class="btn btn-success">Edit</button>
							<button type="button" ng-click="ctrl.getSubject(s.id)"
								data-toggle="modal" data-target="#myModal"
								class="btn btn-danger">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

