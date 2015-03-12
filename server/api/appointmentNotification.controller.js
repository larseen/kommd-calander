module.exports = function(app){

	var AppointmentNotification = require('../models/appointmentNotificaiton.model')(app);

	return {
		createAppointmentNotification: function(req, res) {
			AppointmentNotification.forge({
				Message: req.body.message,
				Viewstatus: req.body.viewstatus,
				Alarmtime: req.body.alarmtime
			}).save()
			.then(function(notification) {
				res.send(notification.toJSON());
			})
			.catch(function(err) {
				return res.send(500, {error:err.toString()});
			});
		},

		getAppointmenNotification: function(req, res) {
			new AppointmentNotification({apppointmentNotificationID: req.params.apppointmentNotificationID}).fetch()
			.then(function(appointmentNotification) {
				if(!appointmentNotification) return res.json({error: 'appointmentNotification not found'});
				res.send(appointmentNotification.toJSON());
			})
			.catch(function(err) {
				return res.send(500, {error: err.toString()});
			});
		},

		updateAppointmentNotification: function(req, res) {
			new AppointmentNotification({apppointmentNotificationID: req.params.appointmentNotificationID}).fetch()
			.then(function(appointmentNotification) {
				if(!appointmentNotificaiton) return res.json(400, {error: 'appointmentNotification not found'});
				appointmentNotificaiton.save({
					Message: req.body.message || appointmentNotificaiton.get('Message'),
					Viewstatus: req.body.viewstatus || appointmentNotificaiton.get('Viewstatus')
					Alarmtime: req.body.alarmtime || appointmentNotification.get('Alarmtime')
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

		deleteAppointmentNotification: function(req, res) {
			new AppointmentNotification({apppointmentNotificationID: req.params.apppointmentNotificationID}).fetch()
			.then(function(appointmentNotification) {
				if(!appointmentNotificaiton) return res.json(400, {error: 'appointmentNotificaiton not found'});
				appointmentNotificaiton.destroy()
				.then(function() {
					return res.send(200);
				})
				.catch(function(err) {
					return res.send(500, {error: err.toString()});
				});
			})
			.catch(function(err) {
				return res.send(500, {error: err});
			});
		}
	}
}
