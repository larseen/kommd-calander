/**
* Module dependencies.
*/
module.exports = function(app){

    var Book = require('../models/bok.model')(app);

    return {
            getBooks : function(req, res){
                new Book().fetchAll()
                .then(function(books) {
                    if(!books) return res.json(400, {error: 'Book not found'});
                    res.send(books.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err});
                });
            },
            getBook : function(req, res){
                new Book({'bokid': req.params.bookId}).fetch()
                .then(function(book) {
                    if(!book) return res.json(400, {error: 'Book not found'})
                    res.send(book.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err});     
            });
        }
    }
}