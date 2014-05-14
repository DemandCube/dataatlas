angular
.module('kafkaSphere', [
        'ui.router',
        'mm.foundation',
        ])

.config(function($stateProvider, $urlRouterProvider) {
  // $urlRouterProvider.otherwise("/groups");
  $stateProvider
  .state('consumers', {
    url: "/consumers",
    templateUrl: "partials/consumers/consumers.html"
  })
  .state('consumers.list', {
    url: "/list",
    templateUrl: "partials/consumers/consumers.list.html",
    controller: function($scope) {
      $scope.consumers = ["A", "List", "Of", "Consumers"];
    }
  })
  .state('topics', {
    url: "/topics",
    templateUrl: "partials/topics/topics.html",
  })
  .state('topics.list', {
    url: "/list",
    templateUrl: "partials/topics/topics.list.html",
    controller: function($scope) {
      $scope.topics = ["A", "Set", "Of", "Topics"];
    }
  })
});
