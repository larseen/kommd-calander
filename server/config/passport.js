/**
* Module dependencies.
*/
module.exports = function(app){

	console.log("passport config")

	var passport = require("passport");
	var User = require('../models/user.model')(app);
	var LocalStrategy = require('passport-local').Strategy;


	passport.serializeUser(function(user, done){
	    done(null, user);
	});
	passport.deserializeUser(function(user, done){
		new User({'UserID': user.userID}).fetch()
		.then(function(user) {
	    	done(null, {userID: user.get('UserID'), name: user.get('Navn')});
	    })
	    .catch(function(err){
	    	done(err, null);
	    });
	});

	// add other strategies for more authentication flexibility
	passport.use(new LocalStrategy({
	    usernameField: 'email',
	    passwordField: 'password'
	  },	
	  function(username, password, done) {
	  	new User({'Email': username}).fetch()
            .then(function(user) {
                if(!user) return done(null, false, {message: 'This email is not registered.'});
                if(!User.authenticate(password, user.get('Salt'), user.get('PasswordHash'))) return done(null, false, {message: 'This password is not correct.'});
                return done(null, {userID: user.get('UserID'), name: user.get('Navn')});
            })
            .catch(function(err){
                return done({error: err.toString()}, null);
            });  
	  	}
	));

}
