/**
* Module dependencies.
*/
module.exports = function(app){

    var User = require('../models/user.model')(app);

    return {
            getUsers : function(req, res){
                new User().fetchAll({columns: ['UserID', 'Navn', 'Title', 'Phone', 'Email']})
                .then(function(users) {
                    if(!users) return res.json(400, {error: 'no users not found'});
                    res.send(users.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            getUser : function(req, res){
                new User({'UserID': req.params.userID}).fetch({columns: ['UserID','Navn', 'Title', 'Phone', 'Email']})
                .then(function(user) {
                    if(!user) return res.json(400, {error: 'user not found'})
                    res.send(user.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });     
            },
            createUser : function(req, res){
                User.create(req.body.email, req.body.password, req.body.name, req.body.phone, req.body.title)
                .then(function(user) {
                    res.send({userID: user.attributes.UserID, name: user.attributes.Navn, phone: user.attributes.Phone, title: user.attributes.Title, email: user.attributes.Email});
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            updateUser : function(req, res){
                new User({UserID: req.params.userID}).fetch({columns: ['UserID','Navn', 'Title', 'Phone', 'Email']})
                .then(function(user){
                    if(!user) return res.json(400, {error: 'no user not found'});
                    user.save({
                        Navn: req.body.name || user.get('Navn'), 
                        Phone: req.body.phone || user.get('Phone'), 
                        Title: req.body.title || user.get('Title'), 
                        Email: req.body.email || user.get('Email')
                    })
                    .then(function(updateUser){
                        res.send(updateUser.toJSON());
                    })
                    .catch(function(err){
                        return res.send(500, {error: err.toString()});
                    });
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            changePassword : function(req, res){
                new User({UserID: req.params.userID}).fetch()
                .then(function(user){
                    if(User.authenticate(req.body.oldPassword, user.Salt, user.PasswordHash)){
                        User.newPassword(user, req.body.newPassword)
                        .then(function(user){
                            res.send({userID: user.attributes.UserID, name: user.attributes.Navn, phone: user.attributes.Phone, title: user.attributes.Title, email: user.attributes.Email});
                        })
                        .catch(function(err){
                            return res.send(500, {error: err.toString()});
                        });
                    } else {
                      res.send(403, {error: 'wrong password'});
                    }
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            deleteUser : function(req, res){
                new User({UserID: req.params.userID}).fetch()
                .then(function(user) {
                if(!user) return res.json(400, {error: 'no user found'});
                    user.destroy()
                    .then(function(){
                        return res.send(200);
                    })
                    .catch(function(err){
                        return res.send(500, {error: err.toString()});
                    });
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            }
    }
}