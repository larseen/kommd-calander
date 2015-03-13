module.exports = function(app){

	var bookshelf = app.get('bookshelf');
	var UserGroupNotification = require('./userGroupNotification.model')(app);

	var GroupNotification = bookshelf.Model.extend(
	{
		idAttribute: 'GroupNotificationID',
		tableName: 'GroupNotification',
		users: function() {
			return this.belongsToMany(User).through(UserGroupNotification);
		},
		group: function() {
			return this.belongsTo(Group);
		}
	});

	return GroupNotification;

}
