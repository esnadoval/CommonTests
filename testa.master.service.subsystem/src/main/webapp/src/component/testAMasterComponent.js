define(['controller/selectionController', 'model/cacheModel', 'model/testAMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/testAComponent',
 'component/testBComponent'
 
 ],function(SelectionController, CacheModel, TestAMasterModel, CRUDComponent, TabController, TestAComponent,
 TestBComponent
 ) {
    App.Component.TestAMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('testAMaster');
            var uComponent = new TestAComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-testA-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-testA-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-testA-list', function() {
                self.hideChilds();
            });
            Backbone.on('testA-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'testA-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-testA-save', function(params) {
                self.model.set('testAEntity', params.model);
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
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'testA-master-save', view: self, error: error});
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
            App.Model.TestAMasterModel.prototype.urlRoot = this.configuration.context;
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
                    self.testBToolbarModel = self.testBComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.testBComponent.setToolbarModel(self.testBToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'testA-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.TestAMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.TestAMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.TestAMasterComponent;
});