/* DevVault — client-side behaviour (Phase 17: AJAX) */

document.addEventListener('DOMContentLoaded', function () {

  var ctx = window.location.pathname.split('/')[1];
  var BASE = ctx ? '/' + ctx : '';

  /* ---------- password show/hide ---------- */
  document.querySelectorAll('.dv-eye').forEach(function (eye) {
    eye.addEventListener('click', function () {
      var input = document.getElementById(eye.dataset.target);
      if (!input) return;
      var showing = input.type === 'text';
      input.type = showing ? 'password' : 'text';
      eye.classList.toggle('fa-eye', showing);
      eye.classList.toggle('fa-eye-slash', !showing);
    });
  });

  /* ---------- AJAX voting ---------- */
  document.querySelectorAll('.dv-vote-btn').forEach(function (btn) {
    btn.addEventListener('click', function () {
      var solutionId = btn.dataset.solution;
      var type = btn.dataset.type;

      fetch(BASE + '/vote/' + solutionId + '?type=' + type, { method: 'POST' })
        .then(function (r) { return r.json(); })
        .then(function (data) {
          if (data.error === 'unauthorized') {
            window.location.href = BASE + '/login';
            return;
          }
          var el = document.getElementById('score-' + solutionId);
          if (el) el.textContent = data.score;
        })
        .catch(function (e) { console.error('Vote failed', e); });
    });
  });

  /* ---------- AJAX bookmark ---------- */
  document.querySelectorAll('.dv-bookmark').forEach(function (btn) {
    btn.addEventListener('click', function () {
      var errorId = btn.dataset.error;

      fetch(BASE + '/bookmarks/error/' + errorId, { method: 'POST' })
        .then(function (r) { return r.json(); })
        .then(function (data) {
          if (data.status === 'unauthorized') {
            window.location.href = BASE + '/login';
            return;
          }
          btn.innerHTML = '<i class="fa-solid fa-bookmark"></i> Saved';
          btn.disabled = true;
        })
        .catch(function (e) { console.error('Bookmark failed', e); });
    });
  });

  /* ---------- unread notification badge ---------- */
  var bell = document.querySelector('.dv-bell');
  if (bell) {
    fetch(BASE + '/notifications/unread-count')
      .then(function (r) { return r.json(); })
      .then(function (data) {
        if (data.count > 0) {
          var dot = document.createElement('span');
          dot.textContent = data.count;
          dot.style.cssText = 'position:absolute;top:-4px;right:-4px;background:#EF4444;color:#fff;' +
            'font-size:10px;font-weight:700;min-width:16px;height:16px;border-radius:8px;' +
            'display:flex;align-items:center;justify-content:center;padding:0 4px;';
          bell.style.position = 'relative';
          bell.appendChild(dot);
        }
      })
      .catch(function () {});
  }

});
