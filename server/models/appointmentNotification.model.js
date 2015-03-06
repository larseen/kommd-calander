module.exports = function(app){

	var bookshelf = app.get('bookshelf');

	var AppointmentNotification = bookshelf.model.extend(
		{
			idAttribute: 'AppointmentNotificationID',
			tableName: 'AppointmentNotification'
		});

	return AppointmentNotification;
}
