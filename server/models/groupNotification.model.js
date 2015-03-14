module.exports = function(app, User, GroupNotification){

	var bookshelf = app.get('bookshelf');

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
