jQuery(document).ready((function($){$("form").on("submit",(function(n){$(this).find(".btn").addClass("disabled"),$(this).find(".btn").on("click",(function(n){return n.preventDefault(),!1})),$(this).find(".btn .fa").removeClass("fa-download").addClass("fa-spinner fa-spin")}))}));