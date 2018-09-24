
// msg alert
window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){ 
        $(this).remove(); 
    });
}, 4000);

//date input
$(function () {
	$('.datepicker').datepicker({
	    format: 'mm/dd/yyyy',
	    startDate: '-3d',
	    	autoclose: true
	});
});


// layout framework
$(document).ready(function(){
	
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
});


// open delete modal
$(document).on("click", ".open-delete", function (e) {
	e.preventDefault();
	
    var beanId = $(this).data('id');
    $(".modal-body #beanId").val( beanId );
    
});


// field mask
$(document).ready(function(){
    $('.date-mask').mask('00/00/0000');
    $('.time-mask').mask('00:00:00');
	$('.date_time-mask').mask('00/00/0000 00:00:00');
	$('.cep-mask').mask('00000-000');
	$('.phone-mask').mask('0000-0000');
	$('.phone_with_ddd-mask').mask('(00) 0000-0000');
	$('.cell-phone-with-ddd-mask').mask('(00) 00000-0000');
	$('.phone_us').mask('(000) 000-0000');
	$('.mixed').mask('AAA 000-S0S');
	$('.cpf-mask').mask('000.000.000-00', {reverse: true});
	$('.cnpj').mask('00.000.000/0000-00', {reverse: true});
	$('.money').mask('000.000.000.000.000,00', {reverse: true});
	$('.money2-mask').mask("#.##0,00", {reverse: true});
	$('.money3-mask').mask("#,##0.00", {reverse: true});
	$('.ip_address').mask('0ZZ.0ZZ.0ZZ.0ZZ', {
	  translation: {
	    'Z': {
	      pattern: /[0-9]/, optional: true
	    }
	  }
	});
	$('.ip_address').mask('099.099.099.099');
	$('.percent').mask('##0,00%', {reverse: true});
	$('.clear-if-not-match').mask("00/00/0000", {clearIfNotMatch: true});
	$('.placeholder').mask("00/00/0000", {placeholder: "__/__/____"});
	$('.fallback').mask("00r00r0000", {
	    translation: {
	      'r': {
	        pattern: /[\/]/,
	        fallback: '/'
	      },
	      placeholder: "__/__/____"
	    }
	  });
	$('.selectonfocus').mask("00/00/0000", {selectOnFocus: true});
});

