var ks = angular.module('kafkaSphere', [ 'ui.router',
                                         'mm.foundation',
])

ks.config(function($stateProvider, $urlRouterProvider) {
  // $urlRouterProvider.otherwise("/groups");
  $stateProvider
  .state('consumers', {
    url: "/consumers",
    templateUrl: "components/consumers/consumers.html",
    controller: "consumersCtrl",
  })
  .state('topics', {
    url: "/topics",
    templateUrl: "components/topics/topics.html",
    controller: "topicsCtrl",
  })
});
