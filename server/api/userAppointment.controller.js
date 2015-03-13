/**
* Module dependencies.
*/
module.exports = function(app){

    var UserAppointment = require('../models/userAppointment.model')(app);
    var User = require('../models/user.model')(app);
    
    return {
            getInvitations: function(req, res){
                new UserAppointment().fetchAll()
                .then(function(userAppointments) {
                    if(!userAppointments) return res.json(400, {error: 'userAppointments not found'});
                    res.send(userAppointments.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            getInvitation: function(req, res){
                new UserAppointment({id: req.params.invitationID}).fetch()
                    .then(function(userAppointments){
                        if(!userAppointments) return res.json(400, {error: 'userAppointments not found'});
                        res.send(userAppointments.toJSON());
                    })
                    .catch(function(err){
                        return res.send(500, {error:err.toString()});
                    });
            },
            getUserInvitations: function(req, res){
                new UserAppointment().where({User_UserID: req.params.userID}).fetchAll({withRelated: ['Appointment']})
                    .then(function(userAppointments){
                        if(!userAppointments) return res.json(400, {error: 'userAppointments not found'});
                        res.send(userAppointments.toJSON());
                    })
                    .catch(function(err){
                        return res.send(500, {error:err.toString()});
                    });
            },
            updateInvitation: function(req, res){
                new UserAppointment({id: req.params.invitationID}).fetch()
                  .then(function(userAppointment) {
                  if(!userAppointment) return res.json(400, {error: 'appointment not found'});
					userAppointment.save({
						ViewStatus: req.body.ViewStatus || userAppointment.get('ViewStatus'),
						ParticipantStatus: req.body.ParticipantStatus || userAppointment.get('ParticipantStatus'),
						User_UserID: userAppointment.get('User_UserID'),
                        Appointment_AppointmentID: userAppointment.get('Appointment_AppointmentID')
					})
					.then(function(updatedUserAppointment){
						res.send(updatedUserAppointment.toJSON())
					})
					.catch(function(err){
						return res.send(500, {error: err.toString()});
					});
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            }
    }
}
