module.exports = function(app) {
	var bookshelf = require('bookshelf');
	var User = require('./user.model');
	var GroupNotification('./groupNotification.model');

	var UserGroupNotification = bookshelf.Model.extend(
		{
			tableName: 'GroupNotification_belongsTo_User',
			user: function() {
				return this.belongsTo(User);
			},
			groupNotification: function() {
				return this.belongsTo(GroupNotification);
			}

		}

}
