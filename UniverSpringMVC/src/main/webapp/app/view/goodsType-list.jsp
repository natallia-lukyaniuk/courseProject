
<div class="container" ng-controller="GoodsTypeController as ctrl">
	<div class="panel panel-default" id='form-editor' style='display:none;'>
		<div class="panel-heading">
			<span class="lead">Add / Edit Form </span>
		</div>
		<div id='formEditor' class="form-horizontal" style='margin-top: 20px;'>
			<form ng-submit="ctrl.submit()" name="goodsTypeForm">
				<input type="hidden" ng-model="ctrl.goodsType.id" />
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="type">Goods type</label>
					<div class="col-xs-10">
						<input  type="text" ng-model="ctrl.goodsType.type" id="type" name='type'
							class="form-control" placeholder="Enter goodsType" required />
						 <div class="has-error" ng-show="goodsTypeForm.$dirty">
							<span ng-show="goodsTypeForm.goodsType.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class='col-xs-12'>
						<input id='form-editor-submit-btn' type="submit" value="{{!ctrl.goodsType.id ? 'Add' : 'Update'}}"
							class="col-xs-2 col-xs-offset-7 btn btn-primary"
							style='margin-right: 10px;'>
						<button type="button" ng-click="ctrl.reset()"
							class="col-xs-2 btn btn-danger" ng-disabled="goodsTypeForm.$pristine">Reset
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
        <h4 class="modal-title" id="myModalLabel">{{ctrl.goodsType.type}}</h4>
      </div>
      <div class="modal-body">
       <h4>Are you sure you want to delete?</h4>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button ng-click="ctrl.remove(ctrl.goodsType.id)" id='modalSave-btn' type="button" class="btn btn-primary">Yes</button>
      </div>
    </div>
  </div>
</div>
	
	
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">
			<span class="lead">List of Goods Types </span>
		</div>
		<a id='form-editor-btn'class="btn btn-success"> <span class="glyphicon glyphicon-plus"></span>Goods Type		add	</a>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Goods type</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="goodsType in ctrl.goodsTypes">
						<td><span ng-bind="goodsType.type"></span></td>
						<td>
							<button type="button" id='edit-btn' ng-click="ctrl.edit(goodsType.id)"
								class="btn btn-success">Edit</button>
							<button type="button" ng-click="ctrl.getGoodsType(goodsType.id)" data-toggle="modal" data-target="#myModal"
								class="btn btn-danger">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>