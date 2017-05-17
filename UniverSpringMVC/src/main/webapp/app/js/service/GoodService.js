'use strict';

angular.module('univerApp').factory('GoodService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/UniverSpringMVC/goods/';

    var factory = {
        fetchAllGoods: fetchAllGoods,
        createGood: createGood,
        updateGood:updateGood,
        deleteGood:deleteGood,
        getGood: getGood,
        fillSelet: fillSelet,
    };

    return factory;

    function fetchAllGoods() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Goods');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fillSelet() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'add')
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data)
            },
            function(errResponse){
                console.error('Error while fetching Goods');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    function getGood(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Goods');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createGood(Good) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, Good)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Good');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateGood(Good, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, Good)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Good');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteGood(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Good');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
