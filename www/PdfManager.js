var exec = require('cordova/exec');

module.exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'PdfManager', 'coolMethod', [arg0]);
};
