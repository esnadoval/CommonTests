define(['model/_testCMasterModel'], function() { 
    App.Model.TestCMasterModel = App.Model._TestCMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('testC-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.TestCModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.testCEntity,options);
            }
        }
    });

    App.Model.TestCMasterList = App.Model._TestCMasterList.extend({
        model: App.Model.TestCMasterModel
    });

    return  App.Model.TestCMasterModel;

});