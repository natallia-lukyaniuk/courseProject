'use strict';

angular.module('univerApp').factory('ProviderService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/UniverSpringMVC/providers/';

    var factory = {
        fetchAllProviders: fetchAllProviders,
        createProvider: createProvider,
        updateProvider: updateProvider,
        deleteProvider: deleteProvider,
        getProvider: getProvider,
    };

    return factory;

    function fetchAllProviders() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    function getProvider(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createProvider(provider) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, provider)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateProvider(provider, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, provider)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteProvider(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
