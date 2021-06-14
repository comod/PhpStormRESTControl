// ==UserScript==
// @name         PhpStormRESTControl
// @version      0.2
// @description  Opens a file from Bitbucket in your IDE!
// @author       Michael Wölk
// @match        https://bitbucket.org/*
// @require      https://code.jquery.com/jquery-3.2.1.min.js
// @require      https://cdnjs.cloudflare.com/ajax/libs/arrive/2.4.1/arrive.min.js
// ==/UserScript==

this.$ = this.jQuery = jQuery.noConflict(true);

(function () {

  const msInitOnReady = 200;
  const startPort = 8100;
  const maxPort = 8110;

  function send(port, file = '', line = 0) {

    const url = 'http://localhost:' + port + '/?file=' + encodeURIComponent(file) + '&line=' + line;

    return $.ajax({
      url,
      type: "GET",
      port
    });

  }

  function sendBatch(file = '', line = 0) {

    for (const port = startPort; port <= maxPort; port++) {
      send(port, file, line);
    }

  }

  function init(elements) {

    elements.each(function (i, sel) {
      const span = '<span class="phpstormIcon" style="cursor: pointer;"><img src="https://upload.wikimedia.org/wikipedia/commons/c/c9/PhpStorm_Icon.svg" height="16"/></span>';
      $(sel).not(':has(span.phpstormIcon)').prepend(span);
    });

    $('.phpstormIcon').click(function (event) {
      event.stopPropagation();
      let filename = $(this).parents('.changes-tree .file, .file-header').find('a').attr('href');
      filename = filename.replace(/^.*#/, '');
      if (filename === null) {
        return;
      }
      const re = /\?t=(\d+)$/;
      const lineMatch = re.exec(filename);
      if (lineMatch !== null) {
        const line = lineMatch[1];
        filename = filename.replace(re, '');
        sendBatch(filename, line);
      } else {
        sendBatch(filename);
      }
    });

  }

  function initOnReady() {
    setTimeout(function tick() {
      const elements = $('.changes-tree .file, .file-header .atlaskit-icon');
      if (elements.length > 0) {
        init(elements);
      } else {
        setTimeout(tick, msInitOnReady);
      }
    }, msInitOnReady);
  }

  $('#pull-requests-container').arrive('.pull-request-activities, .changes', initOnReady);
  $(document).ready(initOnReady);

})();