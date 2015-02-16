/**
* Main application routes
*/
'use strict';

var path = require('path');
var middleware = require('./config/middleware')

module.exports = function(app) {

	app.use('/api/users', require('./api/user'));

	// All other routes should redirect to the index.html
	app.route('/*')
	.get(middleware.setUserCookie, function(req, res) {
		res.sendfile(path.resolve(app.get('appPath') + '/index.html'));
	});

};
