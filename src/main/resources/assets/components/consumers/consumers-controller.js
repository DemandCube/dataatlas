var ks = angular.module('kafkaSphere')

ks.controller('consumersCtrl', ['$scope', function($scope){
  $scope.consumers = ["A", "List", "Of", "Consumers"];
}])
