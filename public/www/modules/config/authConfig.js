angular.module("app.config", [])
    .constant("AuthConfig", {
        Facebook: {
            clientId: AuthConstant.Facebook.clientId
        },
        Google:  {
            clientId: AuthConstant.Google.clientId
        }
    })

    .constant("RouteConfig", {
        login: {
            url: "/login",
            templateUrl: "public/www/modules/login/login.html",
            controller: 'LoginController'
        }
    })
