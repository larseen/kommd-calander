/**
 * Module dependencies.
 */
var express = require('express');
var Config = require('./config/environment'),
    nconf = new Config();
    
// Connect to database
// TODO


// Passport configuration
// TODO

// Setup server
var app = express();
var server = require('http').createServer(app);
require('./routes')(app);



// Start server
server.listen(nconf.get('port'), nconf.get('hostname'), function () {
    console.log('Express server listening on %d, in %s mode', nconf.get('port'), app.get('env'));
});

// Expose app
exports = module.exports = app;