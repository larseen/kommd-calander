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
                new User({UserID: req.params.userID}).fetch()
                  .then(function(user) {
                    if(!user) return res.json(400, {error: 'no user not found'});
                    if(req.body.name) user.save({Navn: req.body.name});
                    if(req.body.title) user.save({Title: req.body.title});
                    if(req.body.phone) user.save({Phone: req.body.phone});
                    if(req.body.email) user.save({Email: req.body.email});
                    console.log(user);
                    res.send(user.toJSON());
                })
                .catch(function(err){
                    console.log(err);
                    return res.send(500, {error:err.toString()});
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