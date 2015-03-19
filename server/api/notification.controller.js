/**
* Module dependencies.
*/
module.exports = function(app){

    var appointmentNotificatioon = require('./appointmentNotification.controller')(app);
    var groupNotification = require('./groupNotification.controller')(app);

    return {

		getUserNotifications : function(req, res){

		},

		updateAppointmentNotification : function(req, res){

		},

		updateGroupNotification : function(req, res){

		}

    }
}
