const BASE_URL = 'http://localhost:8000';
$(() => {
$('#button-edit').click(()=>{
   let title = $('#post-title-edit').val();
   let pictureUrl = $('#post-url-edit').val();
   let description = $('#post-description-edit').val();
    $.ajax({
        url: BASE_URL + '/posts/edit',
        headers: {
            "Accept" : "application/json",
            "Content-Type": "application/json"
},
        method: 'POST',
        data:{title,pictureUrl,description}
    });
})
});




