/**
 * Module dependencies.
 */
module.exports = function(app, User, UserGroup){
    
    var bookshelf = app.get('bookshelf');

    var Group = bookshelf.Model.extend(
	    {
	    	idAttribute: 'GroupID',
		  	tableName: 'Group',
		  	users: function() {
		    	return this.belongsToMany(User).through(UserGroup);
			}
		});

	return Group;

}