define(['model/_testBModel'], function() {
    App.Model.TestBModel = App.Model._TestBModel.extend({

    });

    App.Model.TestBList = App.Model._TestBList.extend({
        model: App.Model.TestBModel
    });

    return  App.Model.TestBModel;

});