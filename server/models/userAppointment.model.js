/**
 * Module dependencies.
 */
module.exports = function(app){
    
    var bookshelf = app.get('bookshelf');

    var Group = bookshelf.Model.extend(
	    {
	    	idAttribute: 'GroupID',
		  	tableName: 'Group'
		});

	return Group;

}