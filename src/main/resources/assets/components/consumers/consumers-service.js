var consumers = angular.module('ks.consumers')

consumers.service('ConsumersService', function(){
  // dummy data duplicated from an example
  return function() {
    this.groups = ["console-consumer-94209", "console-consumer-65474",
                   "console-consumer-68831", "console-consumer-42312"];
  };
})
