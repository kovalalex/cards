$(function() {
             $('.custom-file-input').on('change',function(){
                 var fileName = $(this).val().split('\\').pop();
                 $(this).next('.form-control-file').hide();
                  $(this).next('label').text(fileName);
             })
             });