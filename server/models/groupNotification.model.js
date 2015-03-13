module.exports = function(app){

	var bookshelf = app.get('bookshelf');
	var UserGroupNotification = require('./userGroupNotification.model')(app);

	var GroupNotification = bookshelf.Model.extend(
		{
			idAttribute: 'GroupNotificationID',
			tableName: 'GroupNotification',
			user: function() {
				return this.belongsToMany(UserGroupNotification);
			}

		});

	return GroupNotification;

}
