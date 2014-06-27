var consumers = angular.module('ks.consumers')

consumers.controller('consumersCtrl', function($scope, ConsumersService){
  $scope.consumers = new ConsumersService();
})
