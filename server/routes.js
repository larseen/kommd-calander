/**
* Main application routes
*/
'use strict';

var path = require('path');
var middleware = require('./config/middleware')

module.exports = function(app) {

	var bookController = require('./api/book.controller')(app)
	app.get('/api/books', bookController.getBooks);
	app.get('/api/books/:bookId', bookController.getBook);


	/*

	var appointmentController = require('./api/appointment.controller')(app)
	app.get('/api/appointments', appointmentController.getappointments);
	app.get('/api/books/:bookId', bookController.getBook);

	*/

	// All other routes should return a 404
	app.route('/*')
	.get(middleware.setUserCookie, function(req, res) {
		res.send(404).end();
	});

};
