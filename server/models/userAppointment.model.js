/**
 * Module dependencies.
 */
module.exports = function(app){
    
    var bookshelf = app.get('bookshelf');
    var Appointment = require('./appointment.model')(app);
    var User = require('./appointment.model')(app);

    var UserAppointment = bookshelf.Model.extend(
	    {
	    	tableName: 'User_invitedTo_Appointment',
	    	AppointmentID: function() {
			    return this.belongsTo(Appointment);
			  },
			UserID: function() {
			    return this.belongsTo(User);
			  }
		});

	return UserAppointment;

}