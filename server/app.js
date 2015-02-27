/**
 * Module dependencies.
 */
var express = require('express');
var Config = require('./config/environment'),
    nconf = new Config();
var orm = require('orm');
    
// Connect to database
var knex = require('knex')({
  client: 'mysql',
  connection: {
    host     : 'localhost',
    port     : '8889',
    user     : 'root',
    password : 'root',
    database : 'bokdatabase',
    charset  : 'utf8'
  }
});

var bookshelf = require('bookshelf')(knex);

var app = express();
app.set('bookshelf', bookshelf);
var server = require('http').createServer(app);
require('./routes')(app);



// Start server
server.listen(nconf.get('port'), nconf.get('hostname'), function () {
    console.log('Express server listening on %d, in %s mode', nconf.get('port'), app.get('env'));
});

// Expose app
exports = module.exports = app;