module.exports = function(app){

	var bookshelf = app.get('bookshelf');
	var Appointment = require('./appointment.model')(app);

	var AppointmentNotification = bookshelf.model.extend(
		{
			idAttribute: 'AppointmentNotificationID',
			tableName: 'AppointmentNotification',
			appointmentID: function() {
				return this.belongsTo(Appointment);
			}
		});

	return AppointmentNotification;
}
