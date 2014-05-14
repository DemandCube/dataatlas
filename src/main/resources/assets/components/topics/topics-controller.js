var topics = angular.module('ks.topics')

topics.controller('topicsCtrl', ['$scope', function($scope){
  $scope.topics = ["A", "Set", "Of", "Topics"];
}])
