angular.module('app', ['ui.router', 'satellizer', 'app.config', 'app.login' ])
    .config(function($authProvider, $stateProvider, $urlRouterProvider, AuthConfig, RouteConfig) {

        $authProvider.facebook(AuthConfig.Facebook);

        $authProvider.google( AuthConfig.Google);

        //$authProvider.tokenRoot = true; // set the token parent element if the token is not the JSON root
        $authProvider.tokenName = 'access_token'; //We are sending the access_token into response.data['access_token']
        $authProvider.tokenPrefix = 'groupdeal'; // Local Storage name prefix

        $stateProvider
            .state('login', RouteConfig.login)

        $urlRouterProvider.otherwise('login');

    });