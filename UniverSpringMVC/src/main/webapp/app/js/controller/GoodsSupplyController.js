'use strict';


univerApp.controller('GoodsSupplyController', ['$scope', 'GoodsSupplyService',
    function ($scope, GoodsSupplyService) {
	var self = this;
	self.goodsSupply = {
			id : null,
			date: '',
			name: '',
			amount: null,
			price: null,		
		};
	
    self.goodsSupplys=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.getGoodsSupply = getGoodsSupply;
    self.fillSelected = fillSelected;
 
 
    fetchAllGoodsSupplys();
 
    function fetchAllGoodsSupplys(){
        GoodsSupplyService.fetchAllGoodsSupplys()
            .then(
            function(d) {
                self.goodsSupplys = d;
                Object.keys(self.goodsSupplys).map(function(key, index) {
                	self.goodsSupplys[key].date = new Date(self.goodsSupplys[key].date).toDateString();
                });
                console.log(self);
            },
            function(errResponse){
                console.error('Error while fetching GoodsSupplys');
            }
        );
    }
 
    function createGoodsSupply(GoodsSupply){
        GoodsSupplyService.createGoodsSupply(GoodsSupply)
            .then(fetchAllGoodsSupplys,
            function(errResponse){
                console.error('Error while creating GoodsSupply');
            }
            
        );
    }
    
    function fillSelected(){
        GoodsSupplyService.fillSelet().then(
        function(d) {
            self.goodsSupply = d;
            console.log('FILL-select');
            console.log(self);

        },
        function(errResponse){
            console.error('Error while finding User');
        }
    );
    }
    
    function getGoodsSupply(id){
    	console.log("GET GoodsSupply-" + id);
    	GoodsSupplyService.getGoodsSupply(id).then(
    	        function(d) {
    	            self.goodsSupply = d;
                    self.goodsSupply.date = new Date(self.goodsSupply.date).toDateString();
    	        },
    	        function(errResponse){
    	            console.error('Error while finding GoodsSupply');
    	        });
    	
    }
    
 
    function updateGoodsSupply(GoodsSupply, id){
        GoodsSupplyService.updateGoodsSupply(GoodsSupply, id)
            .then(
            fetchAllGoodsSupplys,
            function(errResponse){
                console.error('Error while updating GoodsSupply');
            }
        );
    }
 
    function deleteGoodsSupply(id){
        GoodsSupplyService.deleteGoodsSupply(id)
            .then(
            fetchAllGoodsSupplys,
            function(errResponse){
                console.error('Error while deleting GoodsSupply');
            }
        );
    }
 
    function submit() {
    	if ($scope.goodsSupplyForm.$valid) {
        if(self.goodsSupply.id===null){
            console.log('Saving New GoodsSupply', self.goodsSupply);
            createGoodsSupply(self.goodsSupply);
        }else{
            updateGoodsSupply(self.goodsSupply, self.goodsSupply.id);
            console.log('GoodsSupply updated with id ', self.goodsSupply.id);
        }
        reset();
        closeForm();
    	}
    }
 
    function edit(id){
        console.log('id to be edited', id);
        GoodsSupplyService.getGoodsSupply(id).then(
        function(d) {
            self.goodsSupply = d;
            self.goodsSupply.date = new Date(self.goodsSupply.date).toDateString();
        },
        function(errResponse){
            console.error('Error while finding GoodsSupply');
        }
    );
    }
    

    function remove(id){
        console.log('id to be deleted', id);
        if(self.goodsSupply.id === id) {// clean form if the GoodsSupply to be deleted
									// is shown there.
            reset();
        }
        deleteGoodsSupply(id);
    }
 
 
    function reset(){
        self.goodsSupply = {
        		id : null,
    			date: '',
    			name: '',
    			amount: null,
    			price: null,
    	};
        $scope.goodsSupplyForm.$setPristine(); // reset Form
    }
 

 }]);



