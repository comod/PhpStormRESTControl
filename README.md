# PhpStormRESTControl IntelliJ IDEA Plugin & Userscripts
Jump to a File & Line with by simple Rest-Call

## What does it do?
- This plugin creates an internal rest api service you can talk to (examples below)
- In combination with UserScripts (eg Tampermonkey Chrome Extension) any Path can be used to be opened in the IDE (eg Bitbucket, Gitlab, Kibana, Sentry -paths)

## API

Function | Endpoint
--- | ---
Jump to File | GET http://localhost:*port*?file=*file*
Jump to File + Line | GET http://localhost:*port*?file=*file*&line=*line*

### Port
This plugin is searching for a free port starting at 8100

### Example
```bash
GET http://localhost:8100/?file=README.md
```

## Plugin
[PhpStormRESTControl.jar](PhpStormRESTControl.jar)

## Userscripts
[userscripts/](userscripts/)

## Contribute ###
Help is always welcome!
