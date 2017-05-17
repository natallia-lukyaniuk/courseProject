
<div class="container" ng-controller="GoodController as ctrl">
	<div class="panel panel-default" id='form-editor' style='display:none;'>
		<div class="panel-heading">
			<span class="lead">Add / Edit Form </span>
		</div>
		<div id='formEditor' class="form-horizontal" style='margin-top: 20px;'>
			<form ng-submit="ctrl.submit()" name="goodForm">
				<input type="hidden" ng-model="ctrl.good.id" />
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="type">Name</label>
					<div class="col-xs-10">
						<input  type="text" ng-model="ctrl.good.name" id="name" name='name'
							class="form-control" placeholder="Enter good name" required />
						 <div class="has-error" ng-show="goodForm.$dirty">
							<span ng-show="goodForm.good.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="type">Measure</label>
					<div class="col-xs-10">
						<input  type="text" ng-model="ctrl.good.measure" id="measure" name='measure'
							class="form-control" placeholder="Enter good measure" required />
						 <div class="has-error" ng-show="goodForm.$dirty">
							<span ng-show="goodForm.good.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="firm">Firm</label>
					<div class="col-xs-10">
						<input  type="text" ng-model="ctrl.good.firm" id="firm" name='firm'
							class="form-control" placeholder="Enter good firm" required />
						 <div class="has-error" ng-show="goodForm.$dirty">
							<span ng-show="goodForm.good.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="firm">Type</label>
					<div class="col-xs-10">
						<input  type="text" ng-model="ctrl.good.type" id="type" name='type'
							class="form-control" placeholder="Enter good type" required />
						 <div class="has-error" ng-show="goodForm.$dirty">
							<span ng-show="goodForm.good.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>
				
				<!-- <div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="firm">Firm</label>
					<div class="col-xs-10">
						<select name='firm' class='form-control'
							data-ng-options="firm for firm in ctrl.good.ProviderList"
							data-ng-model="ctrl.good.firm" required>
							<option value="">Select firm</option> 
							</select>
							<div class="has-error" ng-show="goodForm.$dirty">
							<span class="error" 
							ng-show="goodForm.group.$dirty && goodForm.group.$invalid">Select firm</span> 
							</div>
					</div>
				</div> -->

				<div class="form-group">
					<div class='col-xs-12'>
						<input id='form-editor-submit-btn' type="submit" value="{{!ctrl.good.id ? 'Add' : 'Update'}}"
							class="col-xs-2 col-xs-offset-7 btn btn-primary"
							style='margin-right: 10px;'>
						<button type="button" ng-click="ctrl.reset()"
							class="col-xs-2 btn btn-danger" ng-disabled="goodForm.$pristine">Reset
							Form</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">{{ctrl.good.name}}</h4>
      </div>
      <div class="modal-body">
       <h4>Are you sure you want to delete?</h4>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button ng-click="ctrl.remove(ctrl.good.id)" id='modalSave-btn' type="button" class="btn btn-primary">Yes</button>
      </div>
    </div>
  </div>
</div>
	
	
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Goods </span>
		</div>
		<a id='form-editor-btn'class="btn btn-success"> <span class="glyphicon glyphicon-plus"></span>Goods		add	</a>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Name</th>
						<th>Measure</th>
						<th>Firm</th>
						<th>Type</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="good in ctrl.goods">
						<td><span ng-bind="good.name"></span></td>
						<td><span ng-bind="good.measure"></span></td>
						<td><span ng-bind="good.firm"></span></td>
						<td><span ng-bind="good.type"></span></td>
						<td>
							<button type="button" id='edit-btn' ng-click="ctrl.edit(good.id)"
								class="btn btn-success">Edit</button>
							<button type="button" ng-click="ctrl.getGood(good.id)" data-toggle="modal" data-target="#myModal"
								class="btn btn-danger">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>