module.exports = function(app, User, Appointment){

	var bookshelf = app.get('bookshelf');

	var AppointmentNotification = bookshelf.Model.extend(
	{
		idAttribute: 'NotificationID',
		tableName: 'AppointmentNotification',
		appointmentID: function() {
			return this.belongsTo(Appointment);
		}
	});

	return AppointmentNotification;
}
