
<div class="container" ng-controller="ProviderController as ctrl">
	<div class="panel panel-default" id='form-editor' style='display:none;'>
		<div class="panel-heading">
			<span class="lead">Add / Edit Form </span>
		</div>
		<div id='formEditor' class="form-horizontal" style='margin-top: 20px;'>
			<form ng-submit="ctrl.submit()" name="providerForm">
				<input type="hidden" ng-model="ctrl.provider.id" />
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="firm">Firm</label>
					<div class="col-xs-10">
						<input  type="text" ng-model="ctrl.provider.firm" id="firm" name='firm'
							class="form-control" placeholder="Enter firm name" required />
						 <div class="has-error" ng-show="providerForm.$dirty">
							<span ng-show="providerForm.provider.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>

				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="address">Address</label>
					<div class="col-xs-10">
						<input type="text" ng-model="ctrl.provider.address" id="address" name='address'
							class="form-control" placeholder="Enter firm address" required />
						 <div class="has-error" ng-show="providerForm.$dirty">
							<span ng-show="providerForm.address.$error.required">This is a
								required field</span> 
						</div> 
					</div>
				</div>

				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="phone">Phone</label>
					<div class="col-xs-10">
						<input type="text" ng-model="ctrl.provider.phone" id="phone" name='phone'
							class="form-control" placeholder="Enter firm phone"
							required />
						<div class="has-error" ng-show="providerForm.$dirty">
							<span ng-show="providerForm.phone.$error.required">This is a
								required field</span> 
						</div> 
					</div>
				</div>
				
				
			<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="bankAccount">Bank account</label>
					<div class="col-xs-10">
						<input type="number" ng-model="ctrl.provider.bankAccount" id="bankAccount" name='bankAccount'
							class="form-control" placeholder="Enter firm bank account"
							required />
						<div class="has-error" ng-show="providerForm.$dirty">
							<span ng-show="providerForm.bankAccount.$error.required">This is a
								required field</span> 
						</div> 
					</div>
				</div>



				<div class="form-group">
					<div class='col-xs-12'>
						<input id='form-editor-submit-btn' type="submit" value="{{!ctrl.provider.id ? 'Add' : 'Update'}}"
							class="col-xs-2 col-xs-offset-7 btn btn-primary"
							style='margin-right: 10px;'>
						<button type="button" ng-click="ctrl.reset()"
							class="col-xs-2 btn btn-danger" ng-disabled="providerForm.$pristine">Reset
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
        <h4 class="modal-title" id="myModalLabel">{{ctrl.provider.firm}}</h4>
      </div>
      <div class="modal-body">
       <h4>Are you sure you want to delete?</h4>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button ng-click="ctrl.remove(ctrl.provider.id)" id='modalSave-btn' type="button" class="btn btn-primary">Yes</button>
      </div>
    </div>
  </div>
</div>
	
	
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Providers </span>
		</div>
		<a id='form-editor-btn'class="btn btn-success"> <span class="glyphicon glyphicon-plus"></span>Provider		add	</a>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Firm</th>
						<th>Address</th>
						<th>Phone</th>
						<th>Bank account</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="provider in ctrl.providers">
						<td><span ng-bind="provider.firm"></span></td>
						<td><span ng-bind="provider.address"></span></td>
						<td><span ng-bind="provider.phone"></span></td>
						<td><span ng-bind="provider.bankAccount"></span></td>
						<td>
							<button type="button" id='edit-btn' ng-click="ctrl.edit(provider.id)"
								class="btn btn-success">Edit</button>
							<button type="button" ng-click="ctrl.getProvider(provider.id)" data-toggle="modal" data-target="#myModal"
								class="btn btn-danger">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>