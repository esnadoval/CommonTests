define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/testBModel', 'controller/testBController'], function() {
    App.Component.TestBComponent = App.Component._CRUDComponent.extend({
        name: 'testB',
        model: App.Model.TestBModel,
        listModel: App.Model.TestBList,
        controller : App.Controller.TestBController
    });
    return App.Component.TestBComponent;
});