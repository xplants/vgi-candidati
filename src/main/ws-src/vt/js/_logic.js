jQuery(function($) {

	$('.col').each(function(index, el) {
		if($(this).find('.in-path,.active').length>0){
			var pot = $(this).offset().top;
			var top = $(this).find('.in-path,.active').offset().top - pot;
			$(this).scrollTop(top);
		}
	});

	$('body').on('click', '.col li', function(event) {
		event.preventDefault();
		var $li = $(this);
		var id = $(this).data('id');
		var wosid = $(this).data('wosid');
		var $colsWrapper = $(this).closest('.cols-wrapper');
		var $currentCol = $(this).closest('ul');
		var ulIndex = $(this).closest('.col').index();
		$colsWrapper.find('li.active').removeClass('active').addClass('in-path');
		$currentCol.find('li.in-path').removeClass('in-path');
		$li.addClass('active');
		$('input[data-field-name=id-parent]').val(id);
		var clazz = $(this).data("container-class");
		if(!clazz || "SECTION"===clazz){
			$('.form-add-file, .form-add-section').show();
		} else {
			$('.form-add-file, .form-add-section').hide();
		}
		$.ajax({
			url: VT.colAction,
			data: { "ajax": "true", "id" : id, "wosid" : wosid },
			cache : false,
			success : function(html){
				$colsWrapper.find('ul').each(function(index, el) {
					if(index>ulIndex){
						$(this).remove();
					}
				});
				$colsWrapper.append(html);
				$.ajax({
					url: VT.editorAction,
					data: { "ajax": "true", "id" : id, "wosid" : wosid },
					cache : false,
					success : function(html){
						$colsWrapper.find('#selected-record').remove();
						$colsWrapper.append(html);
					}
				});
			}
		});

		
		return false;
	});
	$('body').on('click', '.btn-danger', function(ev){
		var title = "Sei sicuro di voler effettuare l'operazione?";
		var attr = $(this).attr('title');
		if (typeof attr !== typeof undefined && attr !== false) {
			title = $(this).attr('title') + "?";
		}
		return window.confirm(title);
	});
});