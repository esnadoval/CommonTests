define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/testCModel', 'controller/testCController'], function() {
    App.Component.TestCComponent = App.Component._CRUDComponent.extend({
        name: 'testC',
        model: App.Model.TestCModel,
        listModel: App.Model.TestCList,
        controller : App.Controller.TestCController
    });
    return App.Component.TestCComponent;
});