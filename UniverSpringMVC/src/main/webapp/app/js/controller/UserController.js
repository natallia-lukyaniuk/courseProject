'use strict';

univerApp.controller('UserController', [
		'$scope',
		'UserService',
		function($scope, UserService) {
			var self = this;
			self.user = {
				id : null,
				login : '',
				password : '',
				admin : '',

			};

			self.users = [];

			self.submit = submit;
			self.edit = edit;
			self.remove = remove;
			self.reset = reset;
			self.getUser = getUser;

			fetchAllUsers();

			function fetchAllUsers() {
				UserService.fetchAllUsers().then(function(d) {
					self.users = d;
					console.log(d);
				}, function(errResponse) {
					console.error('Error while fetching Users');
				});
			}

			function createUser(user) {
				UserService.createUser(user).then(fetchAllUsers,
						function(errResponse) {
							console.error('Error while creating User');
						}

				);
			}
			function getUser(id) {
				console.log("GET USER-" + id);
				UserService.getUser(id).then(function(d) {
					self.user = d;
					console.log(self.user);
				}, function(errResponse) {
					console.error('Error while finding User');
				});

			}

			function updateUser(user, id) {
				UserService.updateUser(user, id).then(fetchAllUsers,
						function(errResponse) {
							console.error('Error while updating User');
						});
			}

			function deleteUser(id) {
				UserService.deleteUser(id).then(fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting User');
						});
			}

			function submit() {
				if ($scope.userForm.$valid) {
					if (self.user.id === null) {
						console.log('Saving New User', self.user);
						createUser(self.user);
					} else {
						updateUser(self.user, self.user.id);
						console.log('User updated with id ', self.user.id);
					}
					reset();
					closeForm();
				}
			}

			function edit(id) {
				console.log('id to be edited', id);
				UserService.getUser(id).then(function(d) {
					self.user = d;
				}, function(errResponse) {
					console.error('Error while finding User');
				});
			}

			function remove(id) {
				console.log('id to be deleted', id);
				if (self.user.id === id) {// clean form if the user to be
					// deleted is shown there.
					reset();
				}
				deleteUser(id);
			}

			function reset() {
				self.user = {
					id : null,
					login : '',
					password : '',
					admin : '',
				};
				$scope.userForm.$setPristine(); // reset Form
			}
			
			function closeForm(){
				$('#form-editor').slideUp('slow');
			}

		} ]);
