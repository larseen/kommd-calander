/**
 * Module dependencies.
 */
module.exports = function(app, User, UserAppointment){

    var bookshelf = app.get('bookshelf');


    var Appointment = bookshelf.Model.extend(
    {
		idAttribute: 'AppointmentID',
		tableName: 'Appointment',
		users: function() {
		    return this.belongsToMany(User).through(UserAppointment);
		},
	});

    return Appointment ;
}
