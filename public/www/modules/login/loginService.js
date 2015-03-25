appLogin.factory("LoginService", function ($auth) {
    var self = this;
    
    self.authenticate = function (provider) {
        $auth.authenticate(provider)
            .then(function (response) {
                console.log(response);
            })
    };

    return self;
})