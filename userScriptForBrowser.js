// ==UserScript==
// @name         PhpStormRESTControl
// @version      0.1
// @description  Opens a file from Bitbucket in your IDE!
// @author       Michael Wölk
// @match        https://bitbucket.org/*
// @require      http://ajax.googleapis.com/ajax/libs/jquery/latest/jquery.js
// ==/UserScript==

(function () {

  let msInitOnReady = 200;
  let port = 8100;

  function send(file = '', line = 0) {

    let url = 'http://localhost:' + port + '/?' + file;

    return $.ajax({
      url,
      type: "GET",
      port
    });

  }

  function init() {

    $('[data-path]').each(function (i, sel) {
      $(sel).find('.filename').prepend('<span class="phpstormIcon" style="cursor: pointer;"><img src="http://woelk.it/phpstorm_logo.jpg" height="16"/></span>');
    });

    $('.phpstormIcon').click(function () {
      let filename = $(this).closest('section').data('path');
      send(filename);
    });

  }

  function initOnReady() {
    setTimeout(function tick() {
      if ($('[data-path]').length > 0) {
        init();
      } else {
        setTimeout(tick, msInitOnReady);
      }
    }, msInitOnReady);
  }

  $(document).ready(function () {
    initOnReady();
  });

})();