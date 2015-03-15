/**
* Module dependencies.
*/
module.exports = function(app){

    var Room = require('../models/room.model')(app);

    return {
            getRooms : function(req, res){
                new Room().fetchAll()
                .then(function(rooms) {
                    if(!rooms) return res.json(400, {error: 'no rooms found'});
                    res.send(rooms.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            getRoom : function(req, res){
                new Room({RoomID: req.params.roomID}).fetch()
                .then(function(room) {
                    if(!room) return res.json(400, {error: 'room not found'})
                    res.send(room.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            createRoom : function(req, res){
                Room.forge({
                    Name: req.body.name,
                    Location: req.body.location,
                    Size: req.body.size,
                    Description: req.body.description
                }).save()
                .then(function(room) {
                    res.send(room.toJSON());
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            updateRoom : function(req, res){
                new Room({RoomID: req.params.roomID}).fetch()
                .then(function(room){
                    if(!room) return res.json(400, {error: 'room not found'});
                    room.save({
                        Name: req.body.name || room.get('Name'),
                        Description: req.body.description || room.get('Description'),
                        Location: req.body.location || room.get('Location'),
                        Size: req.body.size || room.get('Size')
                    })
                    .then(function(updateroom){
                        res.send(updateroom.toJSON());
                    })
                    .catch(function(err){
                        return res.send(500, {error: err.toString()});
                    });
                })
                .catch(function(err){
                    return res.send(500, {error: err.toString()});
                });
            },
            deleteRoom : function(req, res){
                new Room({RoomID: req.params.roomID}).fetch()
                .then(function(room) {
                if(!room) return res.json(400, {error: 'no room found'});
                    room.destroy()
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
