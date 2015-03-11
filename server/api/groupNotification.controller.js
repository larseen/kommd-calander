module.exports = function(app){

	var GroupNotification = require('../models/groupNotification.model')(app);

	return {
		createGroupNotification: function(req, res) {
			GroupNotification.forge({
				Message: req.body.message,
				Viewstatus: req.body.viewstatus,
				Offset: req.body.offset
			}).save()
			.then(function(notification) {
				res.send(notification.toJSON());
			})
			.catch(function(err) {
				return res.send(500, {error: err.toString()});
			});
		},

		getGroupNotification: function(req, res) {
			new GroupNotification({groupNotificationID: req.params.groupNotificationID}).fetch()
			.then(function(groupNotification) {
				if(!groupNotification) return res.json(500, {error: 'groupNotification not found'});
				res.send(groupNotification.toJSON());
			})
			.catch(function(err) {
				return res.send(500, {error: err.toString()});
			});
		},

		updateGroupNotification: function(req, res) {
			new GroupNotification({groupNotificationID: req.params.groupNotificationID}).fetch()
			.then(function(groupNotification) {
				if(!groupNotification) return res.json(400, {error: 'groupNotification not found'});
				groupNotification.save({
					Message: req.body.message || groupNotification.get('Message'),
					Viewstatus: req.body.viewstatus || groupNotification.get('Viewstatus')
				})
				.then(function(updatenotification) {
					res.send(updateGroupNotification.toJSON())
				})
				.catch(function(err) {
					return res.send(500, {error: err.toString()});
				});
			})
		},

		deleteGroupNotification: function(req, res) {
			new GroupNotification({groupNotificationID: req.params.groupNotificationID}).fetch()
			.then(function(groupNotification) {
				if(!groupNotification) return res.json(400, {error: 'groupNotification not found'}):
				groupNotification.destroy()
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
