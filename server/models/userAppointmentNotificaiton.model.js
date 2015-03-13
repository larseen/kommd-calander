module.exports = function(app) {

	var bookshelf = app.get('bookshelf');
	var User = require('./user.model')(app);
	var AppointmentNotification = require('

	var userAppointemnetNotification = bookshelf.Model.extend {
		tableName: 'UserAppointmentNotification',
		idAttribute: 'UserAppointemnetNotificationID',
		users: function() {
			return this.belongsTo(User);
		}
		appointmentNotifications: function() {
			return this.belongsTo(AppointmentNotification);
		}
	}
}
