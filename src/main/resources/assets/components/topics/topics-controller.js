var ks = angular.module('kafkaSphere')

ks.controller('topicsCtrl', ['$scope', function($scope){
  $scope.topics = ["A", "Set", "Of", "Topics"];
}])
