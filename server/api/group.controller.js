/**
* Module dependencies.
*/
module.exports = function(app){


    var async = require('async');
    var User = require('../models/user.model')(app);
    var UserGroup = require('../models/userGroup.model')(app, User);
	var GroupNotification = require('../models/groupNotification.model');
    var Group = require('../models/group.model')(app, User, UserGroup, GroupNotification);

    return {
            getGroups : function(req, res){
                new Group().fetchAll()
                .then(function(groups) {
                    if(!groups) return res.json(400, {error: 'no groups found'});
                    res.send(groups.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            getGroup : function(req, res){
                new Group({GroupID: req.params.groupID}).fetch()
                .then(function(group) {
                    if(!group) return res.json(400, {error: 'group not found'})
                    res.send(group.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });     
            },
            getUsers : function(req, res){
                console.log(req.params);
                new Group({GroupID: req.params.groupID}).fetch({
                    withRelated: ['users']
                })
                .then(function(group) {
                    if(!group) return res.json(400, {error: 'group not found'})
                    res.send(group.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });     
            },
            createGroup : function(req, res){
                Group.forge({
                    Name: req.body.name,
                    Description: req.body.description
                }).save()
                .then(function(group) {
                    res.send(group.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                }); 
            },
            updateGroup : function(req, res){
                new Group({GroupID: req.params.groupID}).fetch()
                .then(function(group){
                    if(!group) return res.json(400, {error: 'group not found'});
                    group.save({
                        Name: req.body.name || group.get('Name'), 
                        Description: req.body.description || group.get('Description') 
                    })
                    .then(function(updategroup){
                        res.send(updategroup.toJSON());
                    })
                    .catch(function(err){
                        return res.send(500, {error: err.toString()});
                    });
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            deleteGroup : function(req, res){
                new Group({GroupID: req.params.groupID}).fetch()
                .then(function(group) {
                if(!group) return res.json(400, {error: 'no group found'});
                    group.destroy()
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
            },
            addUsers : function(req, res){
                var response = [];
                async.forEach(req.body.users, function (user, callback){ 
                    UserGroup.forge({
                        Group_GroupID: req.body.group,
                        User_UserID: user,
                    }).save()
                    .then(function(userGroup) {
                        response.push(userGroup.toJSON());
                        callback();
                    })
                    .catch(function(err){
                        console.log(err);
                        return res.send(500, {error:err.toString()});
                    });
                }, function(err) {
                    if(err) return res.send(err);
                    return res.send(response);
                }); 
            },
            removeUsers : function(req, res){
                async.forEach(req.body.users, function (user, callback){ 
                    new UserGroup().where({Group_GroupID: req.body.group, User_UserID: user}).fetch()
                    .then(function(userGroup) {
                        if(!userGroup) return res.json(400, {error: 'userGroup not found'})
                        userGroup.destroy()
                        .then(function(){
                            callback()
                        })
                        .catch(function(err){
                            return res.send(500, {error: err.toString()});
                        });
                    })
                    .catch(function(err){
                        console.log(err);
                        return res.send(500, {error:err.toString()});
                    });
                }, function(err) {
                    if(err) return res.send(err);
                    return res.send(200);
                }); 
            },
            getUserGroups: function(req, res){
                new User({UserID: req.params.userID}).related('groups').fetch()
                    .then(function(groups){
                        if(!groups) return res.json(400, {error: 'groups not found'});
                        res.send(groups.toJSON());
                    })
                    .catch(function(err){
                        return res.send(500, {error:err.toString()});
                    });
            },
    }
}
