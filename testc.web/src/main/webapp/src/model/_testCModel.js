define([], function() {
    App.Model._TestCModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : '' ,  
		 'atr3' : ''        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._TestCList = Backbone.Collection.extend({
        model: App.Model._TestCModel,
        initialize: function() {
        }

    });
    return App.Model._TestCModel;
});