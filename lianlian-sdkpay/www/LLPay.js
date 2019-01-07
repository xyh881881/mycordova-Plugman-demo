var exec = require('cordova/exec');

var LLPay = {
    startPay: function (args, onSuccess, onError) {
        exec(onSuccess, onError, "LLPay", "startPay", [args]);
    }
}

module.exports = LLPay;
