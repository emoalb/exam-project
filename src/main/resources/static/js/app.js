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
                location.href = "/";
            },
            error: function (data, textStatus, xhr) {
                console.log("Error");
                let errorObj = JSON.parse(data.responseText);
                if (errorObj["title"] !== undefined) {
                    appendErrorMessage(errorObj["title"], "#post-title-edit");
                }
                if (errorObj["pictureUrl"] !== undefined) {
                    appendErrorMessage(errorObj["pictureUrl"], "#post-url-edit");
                }
                if (errorObj["description"] !== undefined) {
                    appendErrorMessage(errorObj["description"], "#post-description-edit");
                }

            }
        });
    })
});

function appendErrorMessage(errorMsg, id) {
    $(id).addClass("is-invalid");
    let error = document.createElement("div");
    $(error).addClass("invalid-feedback");
    $(error).html(errorMsg);
    $(error).insertAfter(id);
    setTimeout(() => {
        $(error).empty().fadeOut();
    }, 5000)
}



