var topics = angular.module('ks.topics')

topics.controller('topicsCtrl', ['$scope', 'TopicsService',
  function($scope, TopicsService){
    $scope.topics = new TopicsService();
}])

topics.controller('topicCtrl', ['$scope', '$stateParams',
  function($scope, $stateParams){
    $scope.topic = $stateParams.id;
    $scope.consumers = ['consumer1', 'consumer2', 'consumer3', ];
    $scope.lastActive = new Date();
    $scope.messageCount = ~~Math.random(1000);
}])
