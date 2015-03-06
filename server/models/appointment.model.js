/**
 * Module dependencies.
 */
module.exports = function(app){

    var bookshelf = app.get('bookshelf');


    var Appointment = bookshelf.Model.extend(
    {
		idAttribute: 'AppointmentID',
		tableName: 'Appointment'
	});

    return Appointment ;
}
