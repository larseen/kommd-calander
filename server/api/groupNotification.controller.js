module.exports = function(app){

	var User = require('../models/user.model')(app);
	var GroupNotification = require('../models/userGroupNotification.model')(app);
	var UserGroupNotification = require('../models/groupNotification.model')(app, User, GroupNotification);

	return {
		createGroupNotification: function(req, res) {
			GroupNotification.forge({
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
				if(!groupNotification) return res.json(400, {error: 'groupNotification not found'});
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
