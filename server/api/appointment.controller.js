/**
* Module dependencies.
*/
module.exports = function(app){

    var Appointment = require('../models/appointment.model')(app);
    console.log("update");
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
            getAppointmentInInterval: function(req, res){
                new Appointment({'AppointmentID': req.params.appointmentID}).fetch()
                .then(function(appointment) {
                    if(!appointment) return res.json(400, {error: 'appointment not found'})
                    res.send(appointment.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            createAppointment: function(req, res){
				Appointment.forge({
					Name: req.body.name,
					Location: req.body.location,
					Participants: req.body.participants,
					Title: req.body.title,
					Description: req.body.description,
					StartTime: req.body.starttime,
					EndTime: req.body.endtime
				})
                .then(function(appointment) {
                    res.send(appointment.toJSON());
				})
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            updateAppointment: function(req, res){
                console.log(req.params.appointmentID);
                console.log(req.body);
                new Appointment({appointmentID: req.params.appointmentID}).fetch()
                  .then(function(appointmentID) {
                  if(!appointment) return res.json(400, {error: 'appointment not found'});
					appointment.save({
						Name: req.body.name || appointment.get('Name'),
						Location: req.body.location || appointment.get('Location'),
						Participants: req.body.participants || appointment.get('Participants'),
						Title: req.body.title || appointment.get('Title'),
						Description: req.body.description || appointment.get('Description'),
						StartTime: req.body.starttime || appointment.get('StartTime'),
						EndTime: req.body.endtime || appointment.get('EndTime')
					})
					.then(function(updateappointment){
						res.send(updateAppointment.toJSON())
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
