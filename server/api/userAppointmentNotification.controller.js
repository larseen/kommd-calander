/**
* Module dependencies.
*/
module.exports = function(app){

    var appointmentNotificatioon = require('./appointmentNotification.controller')(app);
    var groupNotification = require('./groupNotification.controller')(app);

    return {

		getUserNotifications : function(req, res){
			new User(UserID: req.params.userID).fetch({
				withRelated: ['appointmentNotifications']
			})
			.then(function(appointmentNotifications) {
				if(!appointmentNotifications) return res.json(400, {error: 'appointmentNotifications not found'});
				res.send(appointmentNotifications.toJSON());
			})

		},

		updateAppointmentNotification : function(req, res){

		},

		updateGroupNotification : function(req, res){

		}

    }
}
