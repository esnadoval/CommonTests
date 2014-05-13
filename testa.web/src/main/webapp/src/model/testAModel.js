define(['model/_testAModel'], function() {
    App.Model.TestAModel = App.Model._TestAModel.extend({

    });

    App.Model.TestAList = App.Model._TestAList.extend({
        model: App.Model.TestAModel
    });

    return  App.Model.TestAModel;

});