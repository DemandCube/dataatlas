var topics = angular.module('ks.topics');

topics.service('TopicsService', function(){
  // dummy data duplicated from an example
  return function() {
    this.active = ["hr","kafka","tests"];
  };
})
