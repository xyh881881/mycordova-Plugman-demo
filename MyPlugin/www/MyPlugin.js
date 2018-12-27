var exec = require('cordova/exec');

var myMathFunc = function(){};


myMathFunc.prototype.plus = function(arg0, success, error) {
    exec(success, error, "MyPlugin", "plus", arg0);
};


myMathFunc.prototype.minus = function(arg0, success, error) {
    exec(success, error, "MyPlugin", "minus", arg0);
};



myMathFunc.prototype.multiply = function(arg0, success, error) {
    exec(success, error, "MyPlugin", "multiply", arg0);
};



myMathFunc.prototype.division = function(arg0, success, error) {
    exec(success, error, "MyPlugin", "division", arg0);
};



var MYMATHFUNC = new myMathFunc();
module.exports = MYMATHFUNC;
