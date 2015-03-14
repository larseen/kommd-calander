/**
* Main application routes
*/
'use strict';

var path = require('path');
var middleware = require('./config/middleware')

module.exports = function(app) {

	var User = require('./api/user.controller')(app)
	app.get('/api/users', User.getUsers);
	app.get('/api/users/:userID', User.getUser);
	app.get('/api/users/groups/:userID', User.getGroups);
	app.post('/api/users', User.createUser);
	app.post('/api/users/:userID', User.updateUser);
	app.put('/api/users/:userID', User.changePassword);
	app.delete('/api/users/:userID', User.deleteUser);

	var Group = require('./api/group.controller')(app)
	app.get('/api/groups', Group.getGroups);
	app.get('/api/groups/:groupID', Group.getGroup);
	app.get('/api/groups/users/:groupID', Group.getUsers);
	app.post('/api/groups/users', Group.addUsers);
	app.put('/api/groups/users', Group.removeUsers);
	app.post('/api/groups/:groupID', Group.updateGroup);
	app.delete('/api/groups/:groupID', Group.deleteGroup);

	var Room = require('./api/room.controller')(app)
	app.get('/api/rooms', Room.getRooms);
	app.get('/api/rooms/:roomID', Room.getRoom);
	app.post('/api/rooms', Room.createRoom);
	app.post('/api/rooms/:roomID', Room.updateRoom);
	app.delete('/api/rooms/:roomID', Room.deleteRoom);

	var Appointment = require('./api/appointment.controller')(app)
	app.get('/api/appointments', Appointment.getAppointments);
	app.get('/api/appointments/:appointmentID', Appointment.getAppointment);
	app.get('/api/appointments/users/:userID', Appointment.getUserAppointments);
	app.get('/api/appointments/users/invited/:appointmentID', Appointment.getUsers);
	app.post('/api/appointments/users', Appointment.addUsers);
	app.put('/api/appointments/users', Appointment.removeUsers);
	app.post('/api/appointments', Appointment.createAppointment);
	app.post('/api/appointments/:appointmentID', Appointment.updateAppointment);
	app.delete('/api/appointments/:appointmentID', Appointment.deleteAppointment);

	var AppointmentNotification = require('./api/appointmentNotification.controller')(app);
	app.post('/api/AppointmentNotifications', AppointmentNotification.createAppointmentNotification);
	app.get('/api/AppointmentNotifications/:userID', AppointmentNotification.getUserAppointmentNotifications);

	var Notification = require('./api/notification.controller')(app)
	app.get('/api/notifications/:userID', Notification.getUserNotifications);
	app.put('/api/notifications/appointment/:notificationID', Notification.updateAppointmentNotification);
	app.put('/api/notifications/group/:notificationID', Notification.updateGroupNotification);

	var Invitation = require('./api/userAppointment.controller')(app)
	app.get('/api/invitations', Invitation.getInvitations);
	app.get('/api/invitations/:invitationID', Invitation.getInvitation);
	app.get('/api/invitations/user/:userID', Invitation.getUserInvitations);
	app.put('/api/invitations/:invitationID', Invitation.updateInvitation);

	var Session = require('./api/session.controller')(app)
	app.post('/api/session', Session.login);
	app.delete('/api/session', Session.logout);


	// All other routes should return a 404
	app.route('/*')
	.get(middleware.setUserCookie, function(req, res) {
		console.log(req);
		res.send(404).end();
	});

};
