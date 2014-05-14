var consumer = angular.module('ks.consumers.consumer')

consumer.service('ConsumerService', function(){
  return function() {
    this.name = 'consumer1'
  };
})
