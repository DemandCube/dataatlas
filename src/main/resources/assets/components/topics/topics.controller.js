var topics = angular.module('ks.topics')

topics.controller('topicsCtrl', ['$scope', 'TopicsService',
  function($scope, TopicsService){
    $scope.topics = new TopicsService();
}])
