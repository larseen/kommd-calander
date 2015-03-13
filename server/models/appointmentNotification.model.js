module.exports = function(app, Appointment){


	var bookshelf = app.get('bookshelf');
	var Appointment = require('./appointment.model')(app);
	var User = require('./user.model')(app);
	var UserAppointmentNotification = require('userAppointmentNotifiaction.model')(app);

	var AppointmentNotification = bookshelf.Model.extend({
			idAttribute: 'NotificationID',
			tableName: 'AppointmentNotification',
			appointmentID: function() {
				return this.belongsTo(Appointment);
			}
		});

	return AppointmentNotification;
}
