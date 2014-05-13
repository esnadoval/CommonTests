define([], function() {
    App.Model._TestCMasterModel = Backbone.Model.extend({
     
    });

    App.Model._TestCMasterList = Backbone.Collection.extend({
        model: App.Model._TestCMasterModel,
        initialize: function() {
        }

    });
    return App.Model._TestCMasterModel;
    
});