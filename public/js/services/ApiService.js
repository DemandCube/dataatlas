services.service('Api', function($http, $q) {

  return {
    exampleFunction: function(){
      var defer = $q.defer();
      $http({
        method: 'GET',
        url: '/examplePath/'
      })
      .success(function(data, status) {
        defer.resolve(data);
      })
      .error(function(data, status){
        console.log('there was an error in ApiService.exampleFunction with these args: ', arguments);
      });
      return defer.promise;
    },
  };
});
