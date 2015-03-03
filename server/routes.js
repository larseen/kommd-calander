/**
* Main application routes
*/
'use strict';

var path = require('path');
var middleware = require('./config/middleware')

module.exports = function(app) {

	var User = require('./api/user.controller')(app)
	app.get('/api/users', User.getUsers);
	app.get('/api/users/:userID', User.getUser);
	app.post('/api/users', User.createUser);
	app.post('/api/users/:userID', User.updateUser);
	app.put('/api/users/:userID', User.changePassword);
	app.delete('/api/users/:userID', User.deleteUser);

	var Session = require('./api/session.controller')(app)
	app.post('/api/session', Session.login);
	app.delete('/api/session', Session.logout);


	// All other routes should return a 404
	app.route('/*')
	.get(middleware.setUserCookie, function(req, res) {
		console.log(req);
		res.send(404).end();
	});

};
