module.exports = function(app, User, AppointmentNotification) {

	var bookshelf = app.get('bookshelf');

	var UserAppointemnetNotification = bookshelf.Model.extend({
		tableName: 'AppointmentNotification_belongsTo_User',
		UserID: function() {
			return this.belongsTo(User);
		},
		NotificationID: function() {
			return this.belongsTo(AppointmentNotification);
		}
	});

	return UserAppointemnetNotification;
}
