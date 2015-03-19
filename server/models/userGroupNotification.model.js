module.exports = function(app, User, GroupNotification) {

	var bookshelf = app.get('bookshelf');

	var UserGroupNotification = bookshelf.Model.extend(
	{
		tableName: 'GroupNotification_belongsTo_User',
		user: function() {
			return this.belongsTo(User);
		},
		groupNotification: function() {
			return this.belongsTo(GroupNotification);
		}
	});

	return UserGroupNotification;
}
