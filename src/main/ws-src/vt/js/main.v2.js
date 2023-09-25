//@codekit-prepend "jquery-3.7.0.min.js", "interact.min.js", "_logic.js", "_jquery.filtertable.js", "_datepicker.min.js", "_datepicker.it-IT.js", "_jquery-ui.js";

Array.prototype.remove = function () {
  var what,
    a = arguments,
    L = a.length,
    ax;
  while (L && this.length) {
    what = a[--L];
    while ((ax = this.indexOf(what)) !== -1) {
      this.splice(ax, 1);
    }
  }
  return this;
};

function multipleItemsSetup() {
  var validateEmail = function (email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  };
  var createItem = function ($ul, item) {
    var $btn = $("<a />", {
      class: "btn btn-danger pull-right btn-sm",
      html: '<span class="fa fa-remove"></span>',
      href: "#",
      click: function (ev) {
        ev.preventDefault();
        var value = $(this).closest("li").data("value");
        var name = $(this).closest("ul").data("name");
        var arrayName = "array_mi_" + name.replace(".", "_");
        VT[arrayName].remove(value);
        $('textarea[name="' + name + '"]').val(JSON.stringify(VT[arrayName]));
        $(this).closest("li").remove();
        return false;
      },
    });
    var $li = $("<li />", {
      html: item,
      class: "list-group-item",
    }).attr("data-value", item);
    $li.append($btn);
    $ul.append($li);
  };

  $(".mask-password").on("click", function (event) {
    event.preventDefault();
    window.alert($(this).text());
    return false;
  });

  $(".multiple-items").each(function (index, el) {
    $(this).hide();

    var $wrapper = $(this).closest(".form-group").length > 0 ? $(this).closest(".form-group") : $(this);

    var tname = $(el).attr("name");
    var name = "array_mi_" + $(el).attr("name").replace(".", "_");
    var src = $(el).val();
    if (!src || src === "") {
      src = "[]";
    }
    VT[name] = JSON.parse(src);
    var array = VT[name];

    var $ul = $("<ul />", { class: "list-group" }).data("name", tname);
    for (var i = array.length - 1; i >= 0; i--) {
      var item = array[i];
      createItem($ul, item);
    }

    $wrapper.append($ul);
    var $div = $("<div />", {
      class: "form-group",
      html: "<label>Aggiungi un indirizzo email</label>",
    });
    var $input = $("<input />", {
      type: "email",
      placeholder: "nome@dominio.ext",
      class: "form-control",
    })
      .on("keyup keydown", function (ev) {
        var keycode = ev.keyCode ? ev.keyCode : ev.which;
        if (keycode === 13 || keycode === 9) {
          console.log(ev.type + " code " + keycode);
          try {
            var value = $(this).val();
            if (value && value !== "") {
              if (validateEmail(value)) {
                var name = $(this).data("name");
                var arrayName = "array_mi_" + name.replace(".", "_");
                var array = VT[arrayName];
                var addAddress = true;
                for (var i = array.length - 1; i >= 0; i--) {
                  var c = array[i];
                  if (c === value) {
                    addAddress = false;
                  }
                }
                if (addAddress) {
                  notifyUnsavedChanges();
                  VT[arrayName].push(value);
                  $('textarea[name="' + name + '"]').val(JSON.stringify(VT[arrayName]));
                  createItem($ul, value);
                } else {
                  window.alert("indirizzo già presente");
                }

                $(this).val("");
              } else {
                window.alert("L'indirizzo email non è valido");
              }
            }
          } catch (err) {
            console.log(err);
          }
          return false;
        } else if (keycode === 27) {
          $(this).val("");
          $(this).trigger("blur");
          return false;
        }
      })
      .data("name", tname);
    var $helpBlock = $("<small />", {
      html: "Digita l'indirizzo email e premi invio per aggiungerlo all'elenco dei destinatari",
      class: "form-text text-muted",
    });
    $div.append($input);
    $div.append($helpBlock);
    $wrapper.after($div);
  });
}

function dndSetup() {
  var rebuildInsetOutsetItems = function () {
    $("#selection-col div[data-pk]").removeClass("in-set").addClass("out-set");
    if (VT.theContainers) {
      VT.theContainers.sort();
      for (var i = VT.theContainers.length - 1; i >= 0; i--) {
        var id = VT.theContainers[i];
        var $div = $("#selection-col div[data-pk=" + id + "]");
        $div.closest("li").find("div[data-pk]").removeClass("out-set").addClass("in-set");
      }
    }
  };

  $("#selection-col").on("click", ".more", function (event) {
    event.preventDefault();
    $(this).toggleClass("active");
    $(this).closest("li").find("ul").toggle();
    return false;
  });

  $("#elements").on("click", ".btn-remove", function (event) {
    event.preventDefault();
    var id = $(this).data("pk");
    VT.theContainers.remove(id);
    $("#VT-theContainers").val(JSON.stringify(VT.theContainers));
    $(this).closest("li").remove();
    rebuildInsetOutsetItems();
    return false;
  });

  function dragMoveListener(event) {
    var target = event.target,
      x = (parseFloat(target.getAttribute("data-x")) || 0) + event.dx,
      y = (parseFloat(target.getAttribute("data-y")) || 0) + event.dy;
    target.style.webkitTransform = target.style.transform = "translate(" + x + "px, " + y + "px)";
    target.style.zIndex = 999;
    target.setAttribute("data-x", x);
    target.setAttribute("data-y", y);
  }
  interact(".draggable").draggable({
    inertia: false,
    restrict: {
      endOnly: true,
      elementRect: { top: 0, left: 0, bottom: 1, right: 1 },
    },
    autoScroll: true,
    onmove: dragMoveListener,
    onend: function (event) {
      var $rel = $(event.target);
      $rel.removeAttr("style").removeClass("can-drop").removeAttr("data-x").removeAttr("data-y");
      rebuildInsetOutsetItems();
    },
  });

  // this is used later in the resizing and gesture demos
  window.dragMoveListener = dragMoveListener;
  interact(".dropzone").dropzone({
    accept: ".out-set",
    overlap: 0.75,
    ondropactivate: function (event) {
      event.target.classList.add("drop-active");
    },
    ondragenter: function (event) {
      var draggableElement = event.relatedTarget,
        dropzoneElement = event.target;
      dropzoneElement.classList.add("drop-target");
      draggableElement.classList.add("can-drop");
    },
    ondragleave: function (event) {
      $(event.target).removeClass("drop-target");
      $(event.relatedTarget).removeClass("can-drop");
    },
    ondrop: function (event) {
      var $rel = $(event.relatedTarget);
      var id = $rel.data("pk");
      $rel.removeAttr("style").removeClass("can-drop").removeAttr("data-x").removeAttr("data-y");
      if (id) {
        if (!VT.theContainers.includes(id)) {
          notifyUnsavedChanges();
          VT.theContainers.push(id);
          VT.theContainers.sort();
          $("#VT-theContainers").val(JSON.stringify(VT.theContainers));
          $rel.removeClass("out-set").addClass("in-set");
          var $el = $rel.clone();
          $el.removeAttr("class");
          var $li = $("<li />", { class: "list-group-item" }).append($el);
          var $btn = $("<a />", {
            href: "#",
            html: '<span class="fa fa-remove"></span>',
            class: "btn btn-remove btn-danger btn-sm pull-right",
          }).data("pk", id);
          $el.append($btn);
          $("#elements > ul").append($li);

          var $records = $rel.closest("li").find("div[data-pk]");
          for (var i = $records.length - 1; i >= 0; i--) {
            var $el = $($records[i]);
            var pk = $el.data("pk");
            if (pk !== id) {
              console.log("elimino #elements div[data-pk=" + pk + "]");
              var $elToRemove = $("#elements div[data-pk=" + pk + "]");
              console.log($elToRemove);
              $elToRemove.find(".btn-remove").trigger("click");
            }
          }
        }
      }
    },
    ondropdeactivate: function (event) {
      console.log(event.type);
      event.target.classList.remove("drop-active");
      event.target.classList.remove("drop-target");
    },
  });

  rebuildInsetOutsetItems();
}

function notifyUnsavedChanges() {
  $("#unsaved-changes-alert").fadeIn();
  VT.hasUnsavedChanges = true;
}

jQuery(function ($) {
  $("ul.pagination > li").addClass("page-item");
  $("ul.pagination > li > a").addClass("page-link");
  $("#link input").on("focus click", function (event) {
    $(this).select();
    var $this = $(this);
    try {
      var ok = document.execCommand("copy");
      if (ok) {
        $("#message .alert-success").html("Link copiato negli appunti").show();
      } else {
        $("#message .alert-success").hide();
      }
    } catch (err) {
      $("#message .alert-warning").html("Browser non supportato").show();
    }

    $this.on("mouseup", function () {
      $this.off("mouseup");
      return false;
    });
    return false;
  });

  $("#link .btn").on("click", function (ev) {
    ev.preventDefault();
    $("#link input").select();
    try {
      var ok = document.execCommand("copy");

      if (ok) {
        $("#message .alert-success").html("Link copiato negli appunti").show();
      } else {
        $("#message .alert-warning").html("Errore nella copia del link").show();
      }
    } catch (err) {
      $("#message .alert-warning").html("Browser non supportato").show();
    }
    return false;
  });

  if (VT.hasUnsavedChanges) {
    $("#unsaved-changes-alert").fadeIn();
  }
  $(".form-record-editing input, .form-record-editing select, .form-record-editing textarea").on("change", function (event) {
    if (!$(this).data("ignore-changes")) {
      if (!VT.hasUnsavedChanges) {
        notifyUnsavedChanges();
      }
    }
  });

  $("body").on("click", ".btn-back", function (event) {
    window.history.back(-1);
  });

  $(".table-to-filter").filterTable({ minRows: 2, placeholder: "ricerca nella tabella", containerTag: "p", label: "Filtra:" });

  $(".btn-delete").on("click", function (event) {
    if (window.confirm("Sei sicuro di voler effettuare l'operazione?")) {
      return true;
    }
    event.preventDefault();
    event.stopPropagation();
    return false;
  });

  $('input[data-toggle="datepicker"]').datepicker({
    language: "it-IT",
    format: "dd/mm/yyyy",
  });

  $(".form-prepare-delivery").on("submit", function (ev) {
    var count = $(this).find("[name=id-addressee]:checked").length;
    if (count <= 0) {
      window.alert("Seleziona almeno un destinatario");
      return false;
    }
  });

  //$('form.validate').each(function(index, el) {
  //	$(this).validate();
  //});

  multipleItemsSetup();
  dndSetup();
});
