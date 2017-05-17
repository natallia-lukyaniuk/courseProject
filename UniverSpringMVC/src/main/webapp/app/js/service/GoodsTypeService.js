'use strict';

angular.module('univerApp').factory('GoodsTypeService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/UniverSpringMVC/goodsTypes/';

    var factory = {
        fetchAllGoodsTypes: fetchAllGoodsTypes,
        createGoodsType: createGoodsType,
        updateGoodsType: updateGoodsType,
        deleteGoodsType: deleteGoodsType,
        getGoodsType: getGoodsType,
    };

    return factory;

    function fetchAllGoodsTypes() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching GoodsTypes');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    
    function getGoodsType(id) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching GoodsTypes');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createGoodsType(goodsType) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, goodsType)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating GoodsType');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateGoodsType(goodsType, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, goodsType)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating GoodsType');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteGoodsType(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting GoodsType');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
