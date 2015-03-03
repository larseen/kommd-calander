/**
* Module dependencies.
*/
module.exports = function(app){

    var Group = require('../models/group.model')(app);

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
            createGroup : function(req, res){
                Group.forge({
                    Name: req.body.name,
                    Description: req.body.description,
                    'Parent Group': req.body.parentGroup
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
                        Description: req.body.description || group.get('Description'), 
                        'Parent Group': req.body.parent || group.get('Parent Group'), 
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
            }
    }
}