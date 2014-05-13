define(['model/_testCModel'], function() {
    App.Model.TestCModel = App.Model._TestCModel.extend({

    });

    App.Model.TestCList = App.Model._TestCList.extend({
        model: App.Model.TestCModel
    });

    return  App.Model.TestCModel;

});