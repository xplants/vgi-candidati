 jQuery(document).ready(function($){
  		$('form').on("submit",function(ev){
  			$(this).find('.btn').addClass('disabled');
  			$(this).find('.btn').on('click', function(event) {
  				event.preventDefault();
  				return false;
  			});
  			$(this).find('.btn .fa').removeClass('fa-download').addClass('fa-spinner fa-spin');
  		});
  
  });