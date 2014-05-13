define(['model/_testAMasterModel'], function() { 
    App.Model.TestAMasterModel = App.Model._TestAMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('testA-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.TestAModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.testAEntity,options);
            }
        }
    });

    App.Model.TestAMasterList = App.Model._TestAMasterList.extend({
        model: App.Model.TestAMasterModel
    });

    return  App.Model.TestAMasterModel;

});