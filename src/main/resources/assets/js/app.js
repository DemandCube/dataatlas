angular
.module('kafkaSphere', [
        'ui.router',
        'mm.foundation',
        ])

.config(function($stateProvider, $urlRouterProvider) {
  // $urlRouterProvider.otherwise("/groups");
  $stateProvider
  .state('groups', {
    url: "/groups",
    templateUrl: "partials/groups/groups.html"
  })
  .state('groups.list', {
    url: "/list",
    templateUrl: "partials/groups/groups.list.html",
    controller: function($scope) {
      $scope.groups = ["A", "List", "Of", "Groups"];
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
