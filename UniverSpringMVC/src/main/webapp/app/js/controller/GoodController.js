'use strict';


univerApp.controller('GoodController', ['$scope', 'GoodService',
    function ($scope, GoodService) {
	var self = this;
	self.good = {
			id : null,
			name: '',
			measure: '',
			firm: '',
			firmList: null,
			type:'',
			typeList:	null,			
		};
	
    self.goods=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.getGood = getGood;
    self.fillSelected = fillSelected;
 
 
    fetchAllGoods();
 
    function fetchAllGoods(){
        GoodService.fetchAllGoods()
            .then(
            function(d) {
                self.goods = d;
                console.log(self);
            },
            function(errResponse){
                console.error('Error while fetching Goods');
            }
        );
    }
 
    function createGood(Good){
        GoodService.createGood(Good)
            .then(fetchAllGoods,
            function(errResponse){
                console.error('Error while creating Good');
            }
            
        );
    }
    
    function fillSelected(){
        GoodService.fillSelet().then(
        function(d) {
            self.good = d;
            console.log('FILL-select');
            console.log(self);

        },
        function(errResponse){
            console.error('Error while finding User');
        }
    );
    }
    
    function getGood(id){
    	console.log("GET Good-" + id);
    	GoodService.getGood(id).then(
    	        function(d) {
    	            self.good = d;
    	        },
    	        function(errResponse){
    	            console.error('Error while finding Good');
    	        });
    	
    }
    
 
    function updateGood(Good, id){
        GoodService.updateGood(Good, id)
            .then(
            fetchAllGoods,
            function(errResponse){
                console.error('Error while updating Good');
            }
        );
    }
 
    function deleteGood(id){
        GoodService.deleteGood(id)
            .then(
            fetchAllGoods,
            function(errResponse){
                console.error('Error while deleting Good');
            }
        );
    }
 
    function submit() {
    	if ($scope.goodForm.$valid) {
        if(self.good.id===null){
            console.log('Saving New Good', self.good);
            createGood(self.good);
        }else{
            updateGood(self.good, self.good.id);
            console.log('Good updated with id ', self.good.id);
        }
        reset();
        closeForm();
    	}
    }
 
    function edit(id){
        console.log('id to be edited', id);
        GoodService.getGood(id).then(
        function(d) {
            self.good = d;
        },
        function(errResponse){
            console.error('Error while finding Good');
        }
    );
    }
    

    function remove(id){
        console.log('id to be deleted', id);
        if(self.good.id === id) {// clean form if the Good to be deleted
									// is shown there.
            reset();
        }
        deleteGood(id);
    }
 
 
    function reset(){
        self.good = {
        		id : null,
        		name: '',
    			measure: '',
    			firm: '',
    			firmList: null,
    			type:"",
    			typeList:	null,
    	};
        $scope.goodForm.$setPristine(); // reset Form
    }
 

 }]);



