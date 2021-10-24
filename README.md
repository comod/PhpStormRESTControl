# PhpStormRESTControl #
PhpStormRESTControl IntelliJ IDEA Plugin.

### What does it do? ###
Control PHPStorm via RESTful API

### API ###

Function | Endpoint
--- | ---
Jump to File | GET http://localhost:*port*?file=*file*
Jump to File + Line | GET http://localhost:*port*?file=*file*&line=*line*

### Port ###
This plugin is searching for a free port starting at 8100

### Example ###
Jump to a File from Bitbucket (or any other Repository Tool) with a simple AJAX-Call:
```javascript
return $.ajax({
  'http://localhost:8100?file=README.md[&line=10]', // 'line' parameter is optional
  type: 'GET'
});
```
```javascript
fetch('http://localhost:8100?file=README.md[&line=10]');
```
```bash
curl 'http://localhost:8100?file=README.md[&line=10]'
```

See also: [userScriptForBrowser.js]

[userScriptForBrowser.js]: userScriptForBrowser.js

### Contribute ###
Help is always welcome!
Tell me your opinion or ideas or help me develop the plugin.
