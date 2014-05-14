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
    controller: "js/features/consumers/controllers.js",
  })
  .state('consumers.list', {
    url: "/list",
    templateUrl: "partials/consumers/consumers.list.html",
  })
  .state('topics', {
    url: "/topics",
    controller: "js/features/topics/controllers.js",
    templateUrl: "partials/topics/topics.html",
  })
  .state('topics.list', {
    url: "/list",
    templateUrl: "partials/topics/topics.list.html",
  })
});
