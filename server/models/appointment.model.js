/**
 * Module dependencies.
 */
module.exports = function(app, User, UserAppointment){

    var bookshelf = app.get('bookshelf');
	var AppointmentNotification = require('./appointmentNotification.model')(app);

    var Appointment = bookshelf.Model.extend(
    {
		idAttribute: 'AppointmentID',
		tableName: 'Appointment',
		appointmentNotifications: function() {
			return this.hasMany(AppointmentNotification);
		},
		users: function() {
		    return this.belongsToMany(User).through(UserAppointment);
		}
	});

    return Appointment ;
}
