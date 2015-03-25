var appLogin = angular.module("app.login", []);

appLogin.controller("LoginController", function ($scope, LoginService) {
    $scope.authenticate = function (provider) {
        LoginService.authenticate(provider);
    }
})