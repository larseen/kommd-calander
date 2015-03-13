module.exports = function(app){

	var bookshelf = app.get('bookshelf');
	var User = require('./user.model')(app);

	var GroupNotification = bookshelf.Model.extend(
		{
			idAttribute: 'GroupNotificationID',
			tableName: 'GroupNotification',
			user: function() {
				return this.belongsTo(User);
			}

		});

	return GroupNotification;

}
