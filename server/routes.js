/**
* Main application routes
*/
'use strict';

var path = require('path');
var middleware = require('./config/middleware')

module.exports = function(app) {

	app.use('/api/users', require('./api/user'));

	// All other routes should return a 404
	app.route('/*')
	.get(middleware.setUserCookie, function(req, res) {
		res.send(404).end();
	});

};
