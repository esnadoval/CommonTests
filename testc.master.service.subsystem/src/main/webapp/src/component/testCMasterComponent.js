define(['controller/selectionController', 'model/cacheModel', 'model/testCMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/testCComponent',
 'component/testBComponent'
 
 ],function(SelectionController, CacheModel, TestCMasterModel, CRUDComponent, TabController, TestCComponent,
 TestBComponent
 ) {
    App.Component.TestCMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('testCMaster');
            var uComponent = new TestCComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-testC-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-testC-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-testC-list', function() {
                self.hideChilds();
            });
            Backbone.on('testC-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'testC-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-testC-save', function(params) {
                self.model.set('testCEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var testBModels = self.testBComponent.componentController.testBModelList;
                self.model.set('listTestB', []);
                self.model.set('createTestB', []);
                self.model.set('updateTestB', []);
                self.model.set('deleteTestB', []);
                for (var i = 0; i < testBModels.models.length; i++) {
                    var m = testBModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createTestB').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateTestB').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < testBModels.deletedModels.length; i++) {
                    var m = testBModels.deletedModels[i];
                    self.model.get('deleteTestB').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testC-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "TestB", name: "testB", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.TestCMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.testBComponent = new TestBComponent();
                    self.testBModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.TestBModel), self.model.get('listTestB'));
                    self.testBComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.TestBModel),
                        listModelClass: App.Utils.createCacheList(App.Model.TestBModel, App.Model.TestBList, self.testBModels)
                    });
                    self.testBComponent.render(self.tabs.getTabHtmlId('testB'));
                    Backbone.on(self.testBComponent.componentId + '-post-testB-create', function(params) {
                        params.view.currentTestBModel.setCacheList(params.view.testBModelList);
                    });
                    self.testBToolbarModel = self.testBComponent.toolbarModel.set(App.Utils.Constans.containmentToolbarConfiguration);
                    self.testBComponent.setToolbarModel(self.testBToolbarModel);
                	
                     
                
                    Backbone.on(self.testBComponent.componentId + '-toolbar-add', function() {
                        var selection = new App.Controller.SelectionController();
                        App.Utils.getComponentList('testBComponent', function(componentName, model) {
                            if (model.models.length == 0) {
                                alert('There is no testBs to select.');
                            } else {
                                selection.showSelectionList({list: model, name: 'name', title: 'TestB List'});
                            }
                            ;
                        });
                    });
                    Backbone.on('post-selection', function(models) {
                        var cacheTestBModel = App.Utils.createCacheModel(App.Model.TestBModel);
                        models = App.Utils.convertToModel(cacheTestBModel, models);
                        for (var i = 0; i < models.length; i++) {
                        	var model = models[i];
                        	model.setCacheList(self.testBComponent.componentController.testBModelList);
                        	model.save('',{});
                        }
                        self.testBComponent.componentController.showEdit=false;
                        self.testBComponent.componentController.list();
                        
                    });
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'testC-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.TestCMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.TestCMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.TestCMasterComponent;
});