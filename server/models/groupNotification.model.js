module.exports = function(app){

	var bookshelf = app.get('bookshelf');

	var GroupNotification = bookshelf.Model.extend(
		{
			idAttribute: 'GroupNotificationID',
			tableName: 'GroupNotification'
		});

	return GroupNotification;

}
