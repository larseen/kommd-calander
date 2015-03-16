/**
* Module dependencies.
*/
module.exports = function(app){

    var User = require('../models/user.model')(app);
    var passport = require('passport');

    return {

        /**
         * Logout
         */
        logout : function (req, res) {
            req.logout();
            res.send(200);
        },

        /**
         * Login
         */
        login : function (req, res, next) {
            passport.authenticate('local', function(err, user, info) {
                var error = err || info;
                if (error) return res.json(401, error);
                    req.logIn(user, function(err) {
                if (err) return res.send(err);
                    res.json(req.user);
                    });
                })(req, res, next);
            }
    }
}
