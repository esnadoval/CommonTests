define(['model/testCModel'], function(testCModel) {
    App.Controller._TestCController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#testC').html());
            this.listTemplate = _.template($('#testCList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'testC-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'testC-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'testC-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'testC-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-testC-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'testC-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit(options);
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testC-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testC-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testC-create', {view: this});
                this.currentTestCModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-testC-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testC-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testC-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testC-list', {view: this, data: data});
                var self = this;
				if(!this.testCModelList){
                 this.testCModelList = new this.listModelClass();
				}
                this.testCModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-testC-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testC-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testC-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testC-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testC-edit', {view: this, id: id, data: data});
                if (this.testCModelList) {
                    this.currentTestCModel = this.testCModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-testC-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentTestCModel = new this.modelClass({id: id});
                    this.currentTestCModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-testC-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'testC-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testC-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testC-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testC-delete', {view: this, id: id});
                var deleteModel;
                if (this.testCModelList) {
                    deleteModel = this.testCModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-testC-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testC-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-testCForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testC-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testC-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testC-save', {view: this, model : model});
                this.currentTestCModel.set(model);
                this.currentTestCModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-testC-save', {model: self.currentTestCModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'testC-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({testCs: self.testCModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({testC: self.currentTestCModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._TestCController;
});