define(['model/testBModel'], function(testBModel) {
    App.Controller._TestBController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#testB').html());
            this.listTemplate = _.template($('#testBList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'testB-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'testB-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'testB-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'testB-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-testB-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'testB-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit(options);
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testB-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testB-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testB-create', {view: this});
                this.currentTestBModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-testB-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testB-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testB-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testB-list', {view: this, data: data});
                var self = this;
				if(!this.testBModelList){
                 this.testBModelList = new this.listModelClass();
				}
                this.testBModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-testB-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testB-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testB-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testB-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testB-edit', {view: this, id: id, data: data});
                if (this.testBModelList) {
                    this.currentTestBModel = this.testBModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-testB-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentTestBModel = new this.modelClass({id: id});
                    this.currentTestBModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-testB-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'testB-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testB-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testB-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testB-delete', {view: this, id: id});
                var deleteModel;
                if (this.testBModelList) {
                    deleteModel = this.testBModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-testB-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testB-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-testBForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testB-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testB-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testB-save', {view: this, model : model});
                this.currentTestBModel.set(model);
                this.currentTestBModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-testB-save', {model: self.currentTestBModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'testB-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({testBs: self.testBModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({testB: self.currentTestBModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._TestBController;
});