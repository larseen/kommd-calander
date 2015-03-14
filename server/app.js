/**
 * Module dependencies.
 */
var express = require('express');
var Config = require('./config/environment'),
    nconf = new Config();
var orm = require('orm');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
    
// Connect to database
var knex = require('knex')({
  client: 'mysql',
  connection: {
    host     : 'pu56.cxdjfwxxonuf.eu-west-1.rds.amazonaws.com',
    port     : '3306',
    user     : 'ntnu_all_pu_g56',
    password : '20e9SYzAzp9V9mO',
    database : 'pu56-dev',
    charset  : 'utf8'
  }
});

var bookshelf = require('bookshelf')(knex);


var app = express();
app.set('bookshelf', bookshelf);

// Passport configuration
var passport = require('./config/passport')(app);


var server = require('http').createServer(app);
require('./config/express')(app);
require('./routes')(app);


// Start server
server.listen(nconf.get('port'), nconf.get('hostname'), function () {
    console.log('Express server listening on %d, in %s mode', nconf.get('port'), app.get('env'));
});

// Expose app
exports = module.exports = app;
