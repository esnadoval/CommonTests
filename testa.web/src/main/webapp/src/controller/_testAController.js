define(['model/testAModel'], function(testAModel) {
    App.Controller._TestAController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#testA').html());
            this.listTemplate = _.template($('#testAList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'testA-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'testA-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'testA-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'testA-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-testA-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'testA-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit(options);
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testA-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testA-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testA-create', {view: this});
                this.currentTestAModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-testA-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testA-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testA-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testA-list', {view: this, data: data});
                var self = this;
				if(!this.testAModelList){
                 this.testAModelList = new this.listModelClass();
				}
                this.testAModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-testA-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testA-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testA-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testA-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testA-edit', {view: this, id: id, data: data});
                if (this.testAModelList) {
                    this.currentTestAModel = this.testAModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-testA-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentTestAModel = new this.modelClass({id: id});
                    this.currentTestAModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-testA-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'testA-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testA-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testA-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testA-delete', {view: this, id: id});
                var deleteModel;
                if (this.testAModelList) {
                    deleteModel = this.testAModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-testA-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testA-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-testAForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-testA-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-testA-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-testA-save', {view: this, model : model});
                this.currentTestAModel.set(model);
                this.currentTestAModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-testA-save', {model: self.currentTestAModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'testA-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({testAs: self.testAModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({testA: self.currentTestAModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._TestAController;
});