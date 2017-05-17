
<div class="container" ng-controller="GoodsSupplyController as ctrl">
	<div class="panel panel-default" id='form-editor' style='display:none;'>
		<div class="panel-heading">
			<span class="lead">Add / Edit Form </span>
		</div>
		<div id='formEditor' class="form-horizontal" style='margin-top: 20px;'>
			<form ng-submit="ctrl.submit()" name="goodsSupplyForm">
				<input type="hidden" ng-model="ctrl.goodsSupply.id" />
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="type">Date</label>
					<div class="col-xs-10">
						<input  type="date" ng-model="ctrl.goodsSupply.date" id="date" name='date'
							class="form-control" placeholder="Enter date" required />
						 <div class="has-error" ng-show="goodsSupplyForm.$dirty">
							<span ng-show="goodsSupplyForm.goodsSupply.$error.required">This is a
								required field</span> 
						</div>
					</div>
					
					<!-- <div class="col-xs-10">
						<input type='text' pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name='date'
							ng-model="ctrl.goodsSupply.date" placeholder="YYYY-MM-DD"
							class="form-control" />
						<div class="has-error" ng-show="goodsSupplyForm.$dirty">
							<span ng-show="goodsSupplyForm.date.$error.required">This is a
								required field</span>
								<span ng-show="goodsSupplyForm.date.$invalid">Incorrect date. Must be YYYY-MM-DD </span>
						</div>
					</div> -->
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="type">Good</label>
					<div class="col-xs-10">
						<input  type="text" ng-model="ctrl.goodsSupply.name" id="name" name='name'
							class="form-control" placeholder="Enter name" required />
						 <div class="has-error" ng-show="goodsSupplyForm.$dirty">
							<span ng-show="goodsSupplyForm.goodsSupply.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="type">Amount</label>
					<div class="col-xs-10">
						<input  type="number" ng-model="ctrl.goodsSupply.amount" id="amount" name='amount'
							class="form-control" placeholder="Enter amount" required />
						 <div class="has-error" ng-show="goodsSupplyForm.$dirty">
							<span ng-show="goodsSupplyForm.goodsSupply.$error.required">This is a
								required field</span> 
						</div>
					</div>
				</div>
				
				<div class="form-group col-xs-12">
					<label class="col-xs-2 control-lable" for="type">Price(BYN)</label>
					<div class="col-xs-10">
						<input  type="number" ng-model="ctrl.goodsSupply.price" id="price" name='price'
							class="form-control" placeholder="Enter price" required />
						 <div class="has-error" ng-show="goodsSupplyForm.$dirty">
							<span ng-show="goodsSupplyForm.goodsSupply.$error.required">This is a
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
						<input id='form-editor-submit-btn' type="submit" value="{{!ctrl.goodsSupply.id ? 'Add' : 'Update'}}"
							class="col-xs-2 col-xs-offset-7 btn btn-primary"
							style='margin-right: 10px;'>
						<button type="button" ng-click="ctrl.reset()"
							class="col-xs-2 btn btn-danger" ng-disabled="goodsSupplyForm.$pristine">Reset
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
			<span class="lead">List of Goods supplys </span>
		</div>
		<a id='form-editor-btn'class="btn btn-success"> <span class="glyphicon glyphicon-plus"></span>Goods Supplys		add	</a>
		<div class="tablecontainer">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Date</th>
						<th>Good</th>
						<th>Amount</th>
						<th>Price(BYN)</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="goodsSupply in ctrl.goodsSupplys">
						<td><span ng-bind="goodsSupply.date"></span></td>
						<td><span ng-bind="goodsSupply.name"></span></td>
						<td><span ng-bind="goodsSupply.amount"></span></td>
						<td><span ng-bind="goodsSupply.price"></span></td>
						<td>
							<button type="button" id='edit-btn' ng-click="ctrl.edit(goodsSupply.id)"
								class="btn btn-success">Edit</button>
							<button type="button" ng-click="ctrl.getGoodSypply(goodsSupply.id)" data-toggle="modal" data-target="#myModal"
								class="btn btn-danger">Remove</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>