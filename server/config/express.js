/**
 * Express configuration
 */

'use strict';

var express = require('express');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var session = require('express-session')
var path = require('path');
var cookieParser = require('cookie-parser')
var passport = require('passport');
//var passportAbakus = require("passport-abakus");

module.exports = function(app) {

app.use(bodyParser.json());
app.use(methodOverride());
app.use(cookieParser());
app.use(session({ secret: 'keyboard cat', cookie: { maxAge: 60000 }}))


  app.use(passport.initialize());
  app.use(passport.session());
  }