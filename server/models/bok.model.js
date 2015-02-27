/**
 * Module dependencies.
 */
module.exports = function(app){
    
    var bookshelf = app.get('bookshelf');

    var Book = bookshelf.Model.extend({
	  tableName: 'BOK'
	});

    return Book
}