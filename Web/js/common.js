/* DOM READY
--------------------------------------------------------------------------------------------------------------------------------------*/

$(function(){
	// CUSTOM FILE TYPE
	$('#file').customFileInput();	
	$('#filee').customFileInput();	

	// TRI TABLEAU
	$("#list").tablesorter({debug: true})

	// ANIM FORM NEW
	$('.addnew').click(function(){
		$('#new').slideDown('5000');
		$('.close_add').fadeIn('5000');
		return false;
	});

	$('.close_add').click(function(){
		$('#new').slideUp('5000');
		$('.close_add').fadeOut('5000');
		return false;
	});

	// RECHERCHE AVANCEE
	$('.search').click(function(){
		$('#search').slideDown('5000');
		$('.close_search').fadeIn('5000');
		return false;
	});

	$('.close_search').click(function(){
		$('#search').slideUp('5000');
		$('.close_search').fadeOut('5000');
		return false;
	});
	
	// FORM MODIF
	$(".edit").click(function() {
		$("#edit_fiche").slideDown('5000');
        $(this).parents(2).siblings('td').each(function(){
            var current_field = $(this).attr('class');
            $('#edit_fiche input[name='+current_field+']').val($(this).text());
			$('#edit_fiche textarea[name='+current_field+']').val($(this).text());
        });
        return false;
    });

	$('.close_edit').click(function(){
		$('#edit_fiche').slideUp('5000');
		return false;
	});
	
	// AFFICHE SINGLE
	$(".linksingle").click(function() {
    	$(this).parent().parent().siblings(".overlaysingle").fadeIn("slow");
        return false;
    });

	// POPIN DELETE
	$(".delete").click(function() {
    	$(this).parent().parent().siblings(".overlay").fadeIn("slow");
        return false;
    });
	
	$(".no").click(function() {
    	$(".overlay").fadeOut("slow");
		$(".overlaysingle").fadeOut("slow");
		$(".relati").show();
        return false;
    });
	
	// ECHAP
	$(document).keyup(function(e) {
    	if(e.keyCode == 27) {
        	$('.overlay').fadeOut('slow');
			$('.overlaysingle').fadeOut('slow');
        }
    });
	
	$(".deco").click(function() {
    	$(this).siblings(".overlay").fadeIn("slow");
        return false;
    });

	// AJAX NEW
	$("#new").submit(function(){
  		var verification;
  		verification = 1;

  		$(".required").each(function(){
   			if ($(this).val()==""){
      			$(this).addClass("error");
      			verification = 0;
   			 }
    		else{
      			$(this).removeClass("error");
    		}
  		});
		if (verification == 1){
    		return true; 
  		}
  		return false; 
	});

});