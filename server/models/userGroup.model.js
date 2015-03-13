/**
 * Module dependencies.
 */
module.exports = function(app){
    
    var bookshelf = app.get('bookshelf');

    var UserGroup = bookshelf.Model.extend(
	    {
	    	tableName: 'User_memberOf_Group',
	    	GroupID: function() {
			    return this.belongsTo(Group);
			  },
			UserID: function() {
			    return this.belongsTo(User);
			  }
		});

	return UserGroup;

}