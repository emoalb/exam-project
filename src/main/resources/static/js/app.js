const BASE_URL = 'http://localhost:8000';
$(() => {
    $('#button-edit').click((event) => {
        event.preventDefault();
        let id = $('#post-id-edit').val();
        let title = $('#post-title-edit').val();
        let pictureUrl = $('#post-url-edit').val();
        let description = $('#post-description-edit').val();
        let creatorUsername = $('#post-creator-edit').val();
        $.ajax({
            url: BASE_URL + '/api/edit',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            method: 'POST',
            data: JSON.stringify({id, title, pictureUrl, description, creatorUsername}),
            success: function (result) {
                //location.href = "/"
                console.log("Success");
                console.log(result)
            },
            error:function (data, textStatus, xhr) {
                console.log("Error");
                console.log(data.responseText);
            }
        });
    })
});




