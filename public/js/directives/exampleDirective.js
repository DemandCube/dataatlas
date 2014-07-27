directives.directive('exampleDirective', function() {
  return {
    restrict: "E",
    scope: {
      route: "="
    },
    templateUrl: "../../templates/directives/exampleDirective.html",
    controller: function () {
      console.log('your directive here');
    }
  };
});
