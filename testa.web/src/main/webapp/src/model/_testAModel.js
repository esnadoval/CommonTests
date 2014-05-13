define([], function() {
    App.Model._TestAModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : '' ,  
		 'atr1' : ''        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._TestAList = Backbone.Collection.extend({
        model: App.Model._TestAModel,
        initialize: function() {
        }

    });
    return App.Model._TestAModel;
});