cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "xyh.MyPlugin",
    "file": "plugins/xyh/www/MyPlugin.js",
    "pluginId": "xyh",
    "clobbers": [
      "MyPlugin"
    ]
  }
];
module.exports.metadata = 
// TOP OF METADATA
{
  "cordova-plugin-whitelist": "1.3.3",
  "xyh": "0.0.1"
};
// BOTTOM OF METADATA
});