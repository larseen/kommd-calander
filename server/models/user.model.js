/**
 * Module dependencies.
 */
module.exports = function(app){
    
    var bookshelf = app.get('bookshelf');
    var Promise  = require('bluebird');
    var crypto = require('crypto');

    /**
	* crypto Methods
	*/
	var cryptoMethods = {
		/**
		* Authenticate - check if the passwords are the same
		*
		* @param {String} plainText
		* @return {Boolean}
		* @api public
		*/
		authenticate: function(plainText) {
			return this.encryptPassword(plainText) === this.hashedPassword;
		},

		/**
		* Make salt
		*
		* @return {String}
		* @api public
		*/
		makeSalt: function() {
			return crypto.randomBytes(16).toString('base64');
		},

		/**
		* Encrypt password
		*
		* @param {String} password
		* @return {String}
		* @api public
		*/
		encryptPassword: function(password, salt) {
			if (!password || !salt) return '';
			var salt = new Buffer(salt, 'base64');
			return crypto.pbkdf2Sync(password, salt, 10000, 64).toString('base64');
		}
	};

    var User = bookshelf.Model.extend(
    {
	  tableName: 'User',
	},
	{

		create: Promise.method(function(email, password, name, phone, title) {
		    if (!email || !password || !name) throw new Error('Email, password and name are all required');
		    var salt = cryptoMethods.makeSalt()
		    return this.forge
		    	({
		    		Navn: name,
		    		Email: email.toLowerCase(),
		    		Phone: phone,
		    		Title: title,
		    		Salt: salt,
		    		PasswordHash: cryptoMethods.encryptPassword(password, salt)
		    	}).save()
		  })


	});

    return User;
}