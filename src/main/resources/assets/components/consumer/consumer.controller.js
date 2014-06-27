var consumer = angular.module('ks.consumers.consumer')

consumer.controller('consumerCtrl', function($scope, ConsumerService){
  $scope.consumer = new ConsumerService();
})
