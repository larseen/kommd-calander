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
	app.post('/api/users', User.createUser);
	app.post('/api/users/:userID', User.updateUser);
	app.put('/api/users/:userID', User.changePassword);
	app.delete('/api/users/:userID', User.deleteUser);

	var Group = require('./api/group.controller')(app)
	app.get('/api/groups', Group.getGroups);
	app.get('/api/groups/:groupID', Group.getGroup);
	app.post('/api/groups', Group.createGroup);
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
	app.get('/api/appointments/user/:userID', Appointment.getUserAppointments);
	app.post('/api/appointments', Appointment.createAppointment);
	app.post('/api/appointments/:appointmentID', Appointment.updateAppointment);
	app.delete('/api/appointments/:appointmentID', Appointment.deleteAppointment);

/*	

	var Notification = require('./api/notification.controller')(app)
	app.get('/api/notifications', Notification.getAppointments);
	app.get('/api/notifications/:notificationID', Notification.getNotification);
	app.get('/api/notifications/:userID', Notification.getUserNotifications);
	app.post('/api/notifications/:appoinmentID', Notification.updateAppointment);

	var Invitation = require('./api/invitation.controller')(app)
	app.get('/api/invitations', Invitation.getInvitations);
	app.get('/api/invitations/:invitationID', Invitation.getInvitation);
	app.get('/api/invitations/:userID', Invitation.getUserInvitations);
	app.post('/api/invitations/:invitationID', Invitation.updateInvitation);


*/

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
