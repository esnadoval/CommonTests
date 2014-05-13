define([], function() {
    App.Model._TestAMasterModel = Backbone.Model.extend({
     
    });

    App.Model._TestAMasterList = Backbone.Collection.extend({
        model: App.Model._TestAMasterModel,
        initialize: function() {
        }

    });
    return App.Model._TestAMasterModel;
    
});