'use strict';

univerApp.controller('ProviderController', [
		'$scope',
		'ProviderService',
		function($scope, ProviderService) {
			var self = this;
			self.provider = {
				id : null,
				firm : '',
				address : '',
				phone : '',
				bankAccount: null,

			};

			self.providers = [];

			self.submit = submit;
			self.edit = edit;
			self.remove = remove;
			self.reset = reset;
			self.getProvider = getProvider;

			fetchAllProviders();

			function fetchAllProviders() {
				ProviderService.fetchAllProviders().then(function(d) {
					self.providers = d;
					console.log(d);
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			}

			function createProvider(provider) {
				ProviderService.createProvider(provider).then(fetchAllProviders,
						function(errResponse) {
							console.error('Error while creating User');
						}

				);
			}
			function getProvider(id) {
				console.log("GET PROVIDER-" + id);
				ProviderService.getProvider(id).then(function(d) {
					self.provider = d;
					console.log(self.provider);
				}, function(errResponse) {
					console.error('Error while finding User');
				});

			}

			function updateProvider(provider, id) {
				ProviderService.updateProvider(provider, id).then(fetchAllProviders,
						function(errResponse) {
							console.error('Error while updating User');
						});
			}

			function deleteProvider(id) {
				ProviderService.deleteProvider(id).then(fetchAllProviders,
						function(errResponse) {
							console.error('Error while deleting User');
						});
			}

			function submit() {
				if ($scope.providerForm.$valid) {
					if (self.provider.id === null) {
						console.log('Saving New Provider', self.provider);
						createProvider(self.provider);
					} else {
						updateProvider(self.provider, self.provider.id);
						console.log('Provider updated with id ', self.provider.id);
					}
					reset();
					closeForm();
				}
			}

			function edit(id) {
				console.log('id to be edited', id);
				ProviderService.getProvider(id).then(function(d) {
					self.provider = d;
				}, function(errResponse) {
					console.error('Error while finding User');
				});
			}

			function remove(id) {
				console.log('id to be deleted', id);
				if (self.provider.id === id) {// clean form if the user to be
					// deleted is shown there.
					reset();
				}
				deleteProvider(id);
			}

			function reset() {
				self.provider = {
					id : null,
					firm : '',
					address : '',
					phone : '',
					bankAccount: null,
				};
				$scope.providerForm.$setPristine(); // reset Form
			}
			
			function closeForm(){
				$('#form-editor').slideUp('slow');
			}

		} ]);
