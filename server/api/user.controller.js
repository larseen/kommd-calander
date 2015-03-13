/**
* Module dependencies.
*/
module.exports = function(app){

    var User = require('../models/user.model')(app);

    return {
            getUsers : function(req, res){
                new User().fetchAll({columns: ['UserID', 'Name', 'Title', 'Phone', 'Email', 'Admin']})
                .then(function(users) {
                    if(!users) return res.json(400, {error: 'no users not found'});
                    res.send(users.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            getUser : function(req, res){
                new User({'UserID': req.params.userID}).fetch({columns: ['UserID','Name', 'Title', 'Phone', 'Email', 'Admin']})
                .then(function(user) {
                    if(!user) return res.json(400, {error: 'user not found'})
                    res.send(user.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });     
            },
            getGroups : function(req, res){
                new User({'UserID': req.params.userID}).fetch({
                    columns: ['UserID','Name', 'Title', 'Phone', 'Email', 'Admin'],
                    withRelated: ['groups']

                })
                .then(function(user) {
                    if(!user) return res.json(400, {error: 'user not found'})
                    res.send(user.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });     
            },
            createUser : function(req, res){
                User.create(req.body.Email, req.body.Password, req.body.Name, req.body.Phone, req.body.Title)
                .then(function(user) {
                    res.send({UserID: user.attributes.UserID, Name: user.attributes.Name, Phone: user.attributes.Phone, Title: user.attributes.Title, Admin: user.attributes.Admin, Email: user.attributes.Email});
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            updateUser : function(req, res){
                new User({UserID: req.params.userID}).fetch({columns: ['UserID','Name', 'Title', 'Phone', 'Email', 'Admin']})
                .then(function(user){
                    if(!user) return res.json(400, {error: 'no user not found'});
                    user.save({
                        Name: req.body.Name || user.get('Name'), 
                        Phone: req.body.Phone || user.get('Phone'), 
                        Title: req.body.Title || user.get('Title'), 
                        Email: req.body.Email || user.get('Email'),
                        Admin: req.body.Admin || user.get('Admin')
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
		console.log(req.body)
                new User({UserID: req.params.userID}).fetch()
                .then(function(user){
                    if(User.authenticate(req.body.oldPassword, user.get('Salt'), user.get('PasswordHash'))){
                        User.newPassword(user, req.body.newPassword)
                        .then(function(user){
                            res.send({UserID: user.get('UserID'), Name: user.get('Name'), Phone: user.get('Phone'), Title: user.get('Title'), Admin: user.get('Admin'), Email: user.get('Email')});
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
