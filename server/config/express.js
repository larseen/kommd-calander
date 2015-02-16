/**
 * Express configuration
 */

'use strict';

var express = require('express');
var favicon = require('serve-favicon');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var path = require('path');
var passport = require('passport');

module.exports = function(app) {
  var env = app.get('env');

  app.engine('html', require('ejs').renderFile);
  app.set('view engine', 'html');
  app.use(bodyParser.urlencoded({ extended: false }));
  app.use(bodyParser.json());
  app.use(methodOverride());

  app.use(passport.initialize());
  app.use(passport.session());

  // Presistent login with session
  //TODO 


  
  if ('production' === env) {
    app.use(favicon(path.join("client", 'public', 'favicon.ico')));
    app.use(express.static('client'));
    app.set('appPath', 'client');
  }

  if ('development' === env) {
    app.use(express.static('client'));
    app.set('appPath', 'client');
  }
};