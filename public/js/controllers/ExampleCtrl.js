controllers.controller('ExampleCtrl', function($scope, Api) {

  var exampleResult = Api.exampleFunction();

  // for ng-repeat
  $scope.exampleList = ['one', 'two', 'three'];
});
