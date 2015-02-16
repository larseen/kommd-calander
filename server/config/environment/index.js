module.exports = function() {
    var path = require('path');
    var nconf = require('nconf');
    var fs = require('fs');
    nconf.env().argv();

    nconf.defaults({file: 'server/config/environment/default.json'});
    switch (process.env.NODE_ENV) {
        case 'production':
            nconf.file({file: 'server/config/environment/production.json'});
            break;
         case 'development':
            nconf.file({file: 'server/config/environment/development.json'});
            break;
        default:
            nconf.file({file: 'server/config/environment/development.json'});
    }

    nconf.getUrl = function() {
        return nconf.get('hostname') + (nconf.get('port') == 80 ? '' : ':' + nconf.get('port'));
    };

nconf.defaults({type: 'file', file: 'server/config/environment/default.json'}); //default/shared config
    return nconf;
};
