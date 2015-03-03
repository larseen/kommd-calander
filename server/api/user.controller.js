/**
* Module dependencies.
*/
module.exports = function(app){

    var User = require('../models/user.model')(app);

    return {
            getUsers : function(req, res){
                new User().fetchAll()
                .then(function(users) {
                    if(!users) return res.json(400, {error: 'no users not found'});
                    res.send(users.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            getUser : function(req, res){
                new User({'UserID': req.params.userID}).fetch()
                .then(function(user) {
                    if(!user) return res.json(400, {error: 'user not found'})
                    res.send(user.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });     
            },
            createUser : function(req, res){
                User.create(req.body.email, req.body.password, req.body.name, req.body.phone, req.body.title)
                .then(function(user) {
                    res.send({name: user.attributes.Navn, phone: user.attributes.Phone, title: user.attributes.Title})
                })
                .catch(function(err){
                    return res.send(500, {error:err.toString()});
                });
            },
            updateUser : function(req, res){
                console.log(req.params.userID);
                console.log(req.body);
                User.forge({UserID: req.params.userID})
                .fetch({require: true})
                .then(function(user){
                    if(!user) return res.json(400, {error: 'no user not found'});
                    console.log(user)
                    user.save({
                        Navn: req.body.name || user.get('Navn'), 
                        Phone: req.body.phone || user.get('Phone'), 
                        Title: req.body.title || user.get('Title'), 
                        Email: req.body.email || user.get('Email')
                    })
                    .then(function(updateUser){
                        console.log(updateUser);
                        res.send(updateUser.toJSON());
                    })
                    .catch(function(err){
                        console.log(err);
                        return res.send(500, {error:err.toString()});
                    });
                });
            },
            changePassword : function(req, res){
                new User().fetchAll()
                .then(function(users) {
                    if(!users) return res.json(400, {error: 'no users not found'});
                    res.send(users.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err});
                });
            },
            deleteUser : function(req, res){
                new User({'UserID': req.params.userID}).fetch()
                .then(function(user) {
                    if(!user) return res.json(400, {error: 'user not found'})
                    res.send(user.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err});     
            });
        }
    }
}