/**
 * Module dependencies.
 */
module.exports = function(app, User, Appointment){
    
    var bookshelf = app.get('bookshelf');

    var UserAppointment = bookshelf.Model.extend(
	    {
			tableName: 'User_invitedTo_Appointment',
			Appointment: function() {
			    return this.belongsTo(Appointment);
			  },
			UserID: function() {
			    return this.belongsTo(User);
			  }
		});

	return UserAppointment;

}
