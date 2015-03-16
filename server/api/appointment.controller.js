/**
* Module dependencies.
*/
module.exports = function(app){

    var async = require('async');
    var UserAppointment = require('../models/userAppointment.model')(app);
    var User = require('../models/user.model')(app, UserAppointment);
    var Appointment = require('../models/appointment.model')(app, User, UserAppointment);
    var appointmentNotification = require('./appointmentNotification.controller')(app, Appointment);


    return {
            getAppointments: function(req, res){
                new Appointment().fetchAll()
                .then(function(appointments) {
                    if(!appointments) return res.json(400, {error: 'appointments not found'});
                    res.send(appointments.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            getAppointment: function(req, res){
                new Appointment({'AppointmentID': req.params.appointmentID}).fetch()
                .then(function(appointment) {
                    if(!appointment) return res.json(400, {error: 'appointment not found'})
                    res.send(appointment.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            getUsers: function(req, res){
				console.log(req.body);
                new Appointment({'AppointmentID': req.params.appointmentID}).fetch({
                    withRelated: ['users']
                })
                .then(function(appointment) {
                    if(!appointment) return res.json(400, {error: 'appointment not found'})
                    res.send(appointment.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            getUserAppointments: function(req, res){
                new User({UserID: req.params.userID}).fetch({
                        withRelated: ['appointments']
                    })
                    .then(function(appointments){
                        if(!appointments) return res.json(400, {error: 'appointments not found'});
                        res.send(appointments.toJSON());
                    })
            },
            addUsers: function(req, res){
                var response = [];
                async.forEach(req.body.users, function (user, callback){ 
                    UserAppointment.forge({
                        Appointment_AppointmentID: req.body.appointment,
                        User_UserID: user,
                        ParticipantStatus: false,
                        ViewStatus: false
                    }).save()
                    .then(function(userAppointment) {
                        response.push(userAppointment.toJSON());
                        callback();
                    })
                    .catch(function(err){
                        console.log(err);
                        return res.send(500, {error:err.toString()});
                    });
                }, function(err) {
                    if(err) return res.send(err);
                    return res.send(response);
                }); 
            },
            removeUsers: function(req, res){
                var response = [];
                async.forEach(req.body.users, function (user, callback){ 
                    new UserAppointment().where({Appointment_AppointmentID: req.body.appointment, User_UserID: user}).fetch()
                    .then(function(userAppointment) {
                        if(!userAppointment) return res.json(400, {error: 'userAppointment not found'})
                        userAppointment.destroy()
                        .then(function(){
                            callback()
                        })
                        .catch(function(err){
                            return res.send(500, {error: err.toString()});
                        });
                    })
                    .catch(function(err){
                        console.log(err);
                        return res.send(500, {error:err.toString()});
                    });
                }, function(err) {
                    if(err) return res.send(err);
                    return res.send(200);
                }); 
            },
            createAppointment: function(req, res){
				Appointment.forge({
					Location: req.body.Location,
					Title: req.body.Title,
                    Room_RoomID: req.body.Room_RoomID,
                    AppointmentAdmin: req.body.AppointmentAdmin,
					Description: req.body.Description,
					DateTimeFrom: req.body.DateTimeFrom,
					DateTimeTo: req.body.DateTimeTo
				}).save()
                .then(function(appointment) {
                    res.send(appointment.toJSON());
				})
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            updateAppointment: function(req, res){
                new Appointment({appointmentID: req.params.appointmentID}).fetch()
                  .then(function(appointment) {
                  if(!appointment) return res.json(400, {error: 'appointment not found'});
					appointment.save({
						Title: req.body.Title || appointment.get('Title'),
						Location: req.body.Location || appointment.get('Location'),
						Description: req.body.Description || appointment.get('Description'),
						DateTimeFrom: req.body.DateTimeFrom || appointment.get('DateTimeFrom'),
						DateTimeTo: req.body.DateTimeTo || appointment.get('DateTimeTo'),
                        AppointmentAdmin: req.body.AppointmentAdmin || appointment.get('AppointmentAdmin'),
                        Room_RoomID: req.body.Room_RoomID || appointment.get('Room_RoomID')
					})
					.then(function(updatedAppointment){
						res.send(updatedAppointment.toJSON())
					})
					.catch(function(err){
						return res.send(500, {error: err.toString()});
					});
                })
                .catch(function(err){
                    console.log(err);
                    return res.send(500, {error:err.toString()});
                });
            },
            deleteAppointment : function(req, res){
                new Appointment({AppointmentID: req.params.appointmentID}).fetch()
                .then(function(appointment) {
                if(!appointment) return res.json(400, {error: 'appointment not found'})
					appointment.destroy()
					.then(function(){
						return res.send(200);
					})
					.catch(function(err){
						return res.send(500, {error: err.toString()});
					});
                })
                .catch(function(err){
                    return res.send(500, {error: err});
            });
        }
    }
}
