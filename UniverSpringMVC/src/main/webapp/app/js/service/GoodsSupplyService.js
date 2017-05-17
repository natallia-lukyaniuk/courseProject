'use strict';

angular.module('univerApp').factory('GoodsSupplyService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/UniverSpringMVC/goodsSupplys/';

    var factory = {
        fetchAllGoodsSupplys: fetchAllGoodsSupplys,
        createGoodsSupply: createGoodsSupply,
        updateGoodsSupply:updateGoodsSupply,
        deleteGoodsSupply:deleteGoodsSupply,
        getGoodsSupply: getGoodsSupply,
        fillSelet: fillSelet,
    };

    return factory;

    function fetchAllGoodsSupplys() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching GoodsSupplys');
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
                console.error('Error while fetching GoodsSupplys');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    function getGoodsSupply(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching GoodsSupplys');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createGoodsSupply(GoodsSupply) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, GoodsSupply)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating GoodsSupply');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateGoodsSupply(GoodsSupply, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, GoodsSupply)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating GoodsSupply');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteGoodsSupply(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting GoodsSupply');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
