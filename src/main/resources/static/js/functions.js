/**
 * Created by Gile on 8/2/2017.
 */
var clicked = 0;
var dogId;
function getRefinedSearchResults(){

    var dogGender;
    var dogAge;
    var dogName;
    var isDogPuppy;

    dogGender = $('#dogGenderSearchParam').children(':selected').text();
    dogAge = $('#dogAgeSearchParam').val();
    dogName = $('#dogNameSearchParam').val();
    isDogPuppy =  $("#dogIsPuppySearchParam").is(":checked");



}

function initDogParams(currentDogId){
    dogId = currentDogId;

    $(document).ready(function() {
        //set default action for check box
        $(".visibilityCheckbox").on('change', function (e) {
            visibility = $(this).is(":checked");
            imageId = $(this).attr('id');
            setVisibilityImageHandler(imageId, visibility);
        });

        //alert('dogId='+dogId);
    });
}

function deleteImageHandler(imgId){
    $.ajax({
        url: '/dog/'+dogId+'/deleteImage/'+imgId,
        type: 'POST',
        /*data: formData,*/
        dataType: 'html',
        cache: false,
        contentType: false,
        processData: false,
        success: function(response) {
            document.open();
            document.write(response);
            document.close();
        },
        error: function() {
            window.location.reload();
        }
    });
}

function setVisibilityImageHandler(imageId, visibility){

    $.ajax({
        url: '/dog/'+dogId+'/setImageVisibility/'+imageId,
        type: 'POST',
        data: {visibility:visibility},
        success: function(response) {
            alert('success')
            document.open();
            document.write(response);
            document.close();
        },
        error: function() {
            alert('failed')
            window.location.reload();
        }
    });

}

function uploadImageHandler(){

    if( clicked === 0 ){
        $('.uploadFile').on('change',function(e){
            var file = this.files[0];
            var name = file.name;
            var size = file.size;
            var type = file.type;
            //alert('You selected file called '+name+' , its type is '+type+' and size is '+size);

            var formData = new FormData();
            formData.append('image', $('input[type=file]')[0].files[0]);
            formData.append('name', name);

            var csrf = $('input[name=_csrf]', '#upload-form').val();
            if(csrf != undefined) {
                formData.append('_csrf', csrf);
            }
            //formData.append()

            $.ajax({
                url: '/dog/'+dogId+'/uploadImage/',
                type: 'POST',
                data: formData,
                dataType: 'html',
                cache: false,
                contentType: false,
                processData: false,
                success: function(response) {
                    document.open();
                    document.write(response);
                    document.close();
                },
                error: function() {
                    window.location.reload();
                }
            });
        });
    }


    $('.uploadFile').click();
    clicked++;
}