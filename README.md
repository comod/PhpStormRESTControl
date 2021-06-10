# PhpStormRESTControl #
PhpStormRESTControl IntelliJ IDEA Plugin.

### What does it do? ###
Control PHPStorm via RESTful API

### API ###

Function | Endpoint
--- | ---
Jump to File | GET http://localhost:*port*/?*file*
Jump to File + Line | *coming soon*

### Example ###
Jump to a File from Bitbucket (or any other Repository Tool) with a simple AJAX-Call:
```javascript
let file = 'README.md';
return $.ajax({
  'http://localhost:'8100/?' + file,
  type: 'GET'
});
```

See also: [userScriptForBrowser.js]

[userScriptForBrowser.js]: userScriptForBrowser.js

### Contribute ###
Help is always welcome!
Tell me your opinion or ideas or help me develop the plugin.