/**
 * Module dependencies.
 */
module.exports = function(app, User, UserGroup, GroupNotification){

    var bookshelf = app.get('bookshelf');

    var Group = bookshelf.Model.extend(
	{
		idAttribute: 'GroupID',
		tableName: 'Group',
		users: function() {
			return this.belongsToMany(User).through(UserGroup);
		},
		groupNotifications: function() {
			return this.hasMany(GroupNotification);
		}
	});

	return Group;

}
