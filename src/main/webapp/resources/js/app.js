
// msg alert
window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){ 
        $(this).remove(); 
    });
}, 4000);


// layout framework
$(document).ready(function(e){
	e.preventDefault();
	
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


// date input
$(function (e) {
	e.preventDefault();
	
	$('.datepicker').datepicker({
	    format: 'mm/dd/yyyy',
	    startDate: '-3d'
	});
});


// field mask
$(document).ready(function(e){
	e.preventDefault();
	
	  $('.date_mask').mask('00/00/0000');
	  $('.time_mask').mask('00:00:00');
	  $('.date_time_mask').mask('00/00/0000 00:00:00');
	  $('.cep_mask').mask('00000-000');
	  $('.phone_mask').mask('0000-0000');
	  $('.phone_with_ddd_mask').mask('(00) 0000-0000');
	  $('.cell_phone_with_ddd_mask').mask('(00) 00000-0000');
	  $('.phone_us_mask').mask('(000) 000-0000');
	  $('.mixed_mask').mask('AAA 000-S0S');
	  $('.cpf_mask').mask('000.000.000-00', {reverse: true});
	  $('.cnpj_mask').mask('00.000.000/0000-00', {reverse: true});
	  $('.money_mask').mask('000.000.000.000.000,00', {reverse: true});
	  $('.money2_mask').mask("#.##0,00", {reverse: true});
	  $('.money3_mask').mask("#,##0.00", {reverse: true});
	  $('.percent_mask').mask('##0,00%', {reverse: true});
});
