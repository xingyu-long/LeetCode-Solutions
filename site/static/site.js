(function () {
  "use strict";
  var root = document.body.getAttribute("data-root") || "";
  var $ = function (s, c) { return (c || document).querySelector(s); };
  var $$ = function (s, c) { return Array.prototype.slice.call((c || document).querySelectorAll(s)); };
  function store(k, v) {
    try { if (v === undefined) return localStorage.getItem(k); localStorage.setItem(k, v); } catch (e) { return null; }
  }

  // sidebar toggle
  var menu = $(".menu-btn"), side = $("#sidebar");
  if (menu) menu.addEventListener("click", function () {
    var open = side.classList.toggle("open");
    menu.setAttribute("aria-expanded", open);
  });

  // header search
  var input = $("#search-input"), list = $("#search-results"), index = null, sel = -1;
  function load() {
    if (index) return Promise.resolve(index);
    return fetch(root + "search-index.json").then(function (r) { return r.json(); })
      .then(function (d) { index = d; return d; }).catch(function () { index = []; return index; });
  }
  function render(items) {
    list.innerHTML = ""; sel = -1;
    items.forEach(function (e) {
      var li = document.createElement("li"), a = document.createElement("a");
      a.href = root + e.u + "index.html";
      a.innerHTML = '<span class="n"></span><span class="t"></span><span class="c"></span>';
      a.children[0].textContent = e.n; a.children[1].textContent = e.t; a.children[2].textContent = e.c;
      li.appendChild(a); list.appendChild(li);
    });
    list.hidden = !items.length;
  }
  function search() {
    var q = input.value.trim().toLowerCase();
    if (!q) { list.hidden = true; return; }
    load().then(function (d) {
      var out = d.filter(function (e) { return String(e.n).indexOf(q) === 0 || (e.n + " " + e.t).toLowerCase().indexOf(q) !== -1; });
      out.sort(function (a, b) { return (String(a.n) === q ? 0 : 1) - (String(b.n) === q ? 0 : 1); });
      render(out.slice(0, 8));
    });
  }
  if (input) {
    input.addEventListener("input", search);
    input.addEventListener("focus", load);
    input.addEventListener("keydown", function (ev) {
      var links = $$("a", list);
      if (ev.key === "ArrowDown" || ev.key === "ArrowUp") {
        ev.preventDefault();
        if (!links.length) return;
        sel = (sel + (ev.key === "ArrowDown" ? 1 : -1) + links.length) % links.length;
        links.forEach(function (l, i) { l.classList.toggle("sel", i === sel); });
      } else if (ev.key === "Enter" && links.length) {
        location.href = links[Math.max(sel, 0)].href;
      } else if (ev.key === "Escape") { list.hidden = true; input.blur(); }
    });
    document.addEventListener("click", function (ev) { if (!ev.target.closest(".search")) list.hidden = true; });
    document.addEventListener("keydown", function (ev) {
      var t = ev.target.tagName;
      if (ev.key === "/" && t !== "INPUT" && t !== "TEXTAREA") { ev.preventDefault(); input.focus(); }
    });
  }

  // category filter
  var filter = $("#cat-filter");
  if (filter) filter.addEventListener("input", function () {
    var q = filter.value.trim().toLowerCase(), any = false;
    $$(".row").forEach(function (r) {
      var show = !q || r.getAttribute("data-text").indexOf(q) !== -1;
      r.hidden = !show; any = any || show;
    });
    $$(".group").forEach(function (g) { g.hidden = !$$(".row:not([hidden])", g).length; });
    $("#no-match").hidden = any;
  });

  // problem page: tabs, side by side, copy
  var card = $(".code-card");
  if (card) {
    var tabs = $$(".tab", card), panels = $$(".panel", card);
    var switchBtns = $$(".vs", card);
    var canSplit = function () { return switchBtns.length && window.matchMedia("(min-width:1100px)").matches; };
    var view = store("lc-view") === "split" ? "split" : "tabs";
    var showLang = store("lc-lang");
    function select(i) {
      tabs.forEach(function (t, j) { t.setAttribute("aria-selected", j === i); });
      panels.forEach(function (p, j) { p.hidden = (view === "tabs") && j !== i; });
      store("lc-lang", tabs[i].textContent.replace(/ \(\d+\)$/, ""));
    }
    function apply() {
      var split = view === "split" && canSplit();
      card.classList.toggle("card-split", split);
      card.style.setProperty("--cols", panels.length);
      switchBtns.forEach(function (b) { b.setAttribute("aria-pressed", (b.dataset.view === "split") === split); });
      var cur = Math.max(0, tabs.findIndex(function (t) { return t.getAttribute("aria-selected") === "true"; }));
      panels.forEach(function (p, j) { p.hidden = !split && j !== cur; });
    }
    var start = Math.max(0, tabs.findIndex(function (t) { return t.textContent.replace(/ \(\d+\)$/, "") === showLang; }));
    tabs.forEach(function (t, i) { t.addEventListener("click", function () { select(i); }); });
    switchBtns.forEach(function (b) {
      b.addEventListener("click", function () { view = b.dataset.view; store("lc-view", view); apply(); });
    });
    window.addEventListener("resize", apply);
    tabs.forEach(function (t, j) { t.setAttribute("aria-selected", j === start); });
    apply();

    $$(".copy", card).forEach(function (b) {
      if (navigator.clipboard) b.classList.add("ready");
      b.addEventListener("click", function () {
        var text = $(".src", b.closest(".file")).value;
        navigator.clipboard.writeText(text).then(function () {
          b.textContent = "Copied"; setTimeout(function () { b.textContent = "Copy"; }, 1400);
        });
      });
    });
  }
})();
