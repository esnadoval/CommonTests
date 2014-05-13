define([], function() {
    App.Model._TestBModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : '' ,  
		 'atr2' : ''        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._TestBList = Backbone.Collection.extend({
        model: App.Model._TestBModel,
        initialize: function() {
        }

    });
    return App.Model._TestBModel;
});