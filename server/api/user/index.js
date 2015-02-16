'use strict';

var express = require('express');
var controller = require('./user.controller');
var middleware = require('./../../config/middleware')

var router = express.Router();

//router.get('/show/:userId', controller.getUser);

module.exports = router;