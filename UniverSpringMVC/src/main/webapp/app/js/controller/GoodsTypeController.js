'use strict';

univerApp.controller('GoodsTypeController', [
		'$scope',
		'GoodsTypeService',
		function($scope, GoodsTypeService) {
			var self = this;
			self.goodsType = {
				id : null,
				type : '',

			};

			self.goodsTypes = [];

			self.submit = submit;
			self.edit = edit;
			self.remove = remove;
			self.reset = reset;
			self.getGoodsType = getGoodsType;

			fetchAllGoodsTypes();

			function fetchAllGoodsTypes() {
				GoodsTypeService.fetchAllGoodsTypes().then(function(d) {
					self.goodsTypes = d;
					console.log(d);
				}, function(errResponse) {
					console.error('Error while fetching GoodsTypes');
				});
			}

			function createGoodsType(goodsType) {
				GoodsTypeService.createGoodsType(goodsType).then(fetchAllGoodsTypes,
						function(errResponse) {
							console.error('Error while creating GoodsType');
						}

				);
			}
			function getGoodsType(id) {
				console.log("GET GoodsType-" + id);
				GoodsTypeService.getGoodsType(id).then(function(d) {
					self.goodsType = d;
					console.log(self.goodsType);
				}, function(errResponse) {
					console.error('Error while finding GoodsType');
				});

			}

			function updateGoodsType(goodsType, id) {
				GoodsTypeService.updateGoodsType(goodsType, id).then(fetchAllGoodsTypes,
						function(errResponse) {
							console.error('Error while updating GoodsType');
						});
			}

			function deleteGoodsType(id) {
				GoodsTypeService.deleteGoodsType(id).then(fetchAllGoodsTypes,
						function(errResponse) {
							console.error('Error while deleting GoodsType');
						});
			}

			function submit() {
				if ($scope.goodsTypeForm.$valid) {
					if (self.goodsType.id === null) {
						console.log('Saving New GoodsType', self.goodsType);
						createGoodsType(self.goodsType);
					} else {
						updateGoodsType(self.goodsType, self.goodsType.id);
						console.log('GoodsType updated with id ', self.goodsType.id);
					}
					reset();
					closeForm();
				}
			}

			function edit(id) {
				console.log('id to be edited', id);
				GoodsTypeService.getGoodsType(id).then(function(d) {
					self.goodsType = d;
				}, function(errResponse) {
					console.error('Error while finding GoodsType');
				});
			}

			function remove(id) {
				console.log('id to be deleted', id);
				if (self.goodsType.id === id) {// clean form if the user to be
					// deleted is shown there.
					reset();
				}
				deleteGoodsType(id);
			}

			function reset() {
				self.goodsType = {
					id : null,
					type : '',
				};
				$scope.goodsTypeForm.$setPristine(); // reset Form
			}
			
			function closeForm(){
				$('#form-editor').slideUp('slow');
			}

		} ]);
