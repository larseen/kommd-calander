/**
 * Module dependencies.
 */
module.exports = function(app){

    var bookshelf = app.get('bookshelf');
	var AppointmentNotification = require('./appointmentNotification.model')(app);

    var Appointment = bookshelf.Model.extend(
    {
		idAttribute: 'AppointmentID',
		tableName: 'Appointment',
		appointments: function() {
			return this.belongsToMany(AppointmentNotification);
		},

	});

    return Appointment ;
}
