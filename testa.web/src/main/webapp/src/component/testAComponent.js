define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/testAModel', 'controller/testAController'], function() {
    App.Component.TestAComponent = App.Component._CRUDComponent.extend({
        name: 'testA',
        model: App.Model.TestAModel,
        listModel: App.Model.TestAList,
        controller : App.Controller.TestAController
    });
    return App.Component.TestAComponent;
});