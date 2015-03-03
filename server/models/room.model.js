/**
 * Module dependencies.
 */
module.exports = function(app){
    
    var bookshelf = app.get('bookshelf');

    var Room = bookshelf.Model.extend(
	    {
	    	idAttribute: 'RoomID',
		  	tableName: 'Room'
		});

	return Room;

}