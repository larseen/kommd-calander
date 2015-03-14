module.exports = function(app){

	var User = require('../models/user.model')(app);
	var Appointment = require('../models/appointment.model');
	var AppointmentNotification = require('../models/appointmentNotification.model')(app, User, Appointmen, User, Appointment);
	var UserAppointmentNotification = require('../models/UserAppointmentNotification.model')(app, User, AppointmentNotification);

	return {
		createAppointmentNotification: function(req, res) {
			AppointmentNotification.forge({
				Message: req.body.Message,
				Appointment_AppointmentID: req.body.Appointment_AppointmentID
			}).save()
			.then(function(notification) {
				UserAppointmentNotification.forge({
					User_UserID: req.body.User_UserID,
					SeenStatus: false,
					AppointmentNotification_NotificationID: notification.get('NotificationID')
				}).save()
					.then(function(userNotification) {
						return res.send({notification: notification.toJSON(), userNotification: userNotification.toJSON()});
					})
					.catch(function(err) {
						return res.send(500, {error: err.toString()});
					});
			})
			.catch(function(err) {
				return res.send(500, {error: err.toString()});
			});
		},

		getUserAppointmentNotifications : function(req, res){
			UserAppointmentNotification.where({User_UserID: req.params.userID}).fetchAll({
				withRelated: ['NotificationID']
			})
				.then(function(notification) {
					return res.send(notification);
				})
				.catch(function(err) {
					return res.send(500, {error: err.toString()});
				});
		},

		updateAppointmentNotification: function(req, res) {
			new UserAppointmentNotification({AppointmentNotification_NotificationID: req.params.AppointmentNotification_NotificationID}).fetch()
			.then(function(appointmentNotification) {
				if(!appointmentNotification) return res.json(400, {error: 'appointmentNotification not found'});
				appointmentNotificaiton.save({
					SeenStatus: req.body.SeenStatus || appointmentNotificaiton.get('SeenStatus')
				})
				.then(function(updatenotification) {
					res.send(updateAppointmentNotification.toJSON())
				})
				.catch(function(err) {
					return res.send(500, {error: err.toString()});
				});
			})
			.catch(function(err) {
				return res.send(500, {error: err.toString()});
			});
		},

	}
}
