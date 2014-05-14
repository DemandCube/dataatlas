var ks = angular.module('kafkaSphere', [ 'ui.router',
                                         'mm.foundation',
                                         'ks.consumers',
                                         'ks.topics',
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
  .state('topics.details', {
    url: "/:id",
    templateUrl: "components/topics/topics.topic.html",
    controller: "topicCtrl",
  })
});

angular.module('ks.consumers', []);
angular.module('ks.topics', []);
