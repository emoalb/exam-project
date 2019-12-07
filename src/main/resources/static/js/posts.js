const URLS = {
    posts: '/api/posts'
};
const post = ({id, title, pictureUrl, description, creatorUsername}) => {
    return `
 <div class="col-md-4">
        <div class="card" style="width: 18rem;">
        <img src="${pictureUrl}" class="card-img-top" alt="Image for">
        <div class="card-body">
        <h5 class="card-title">${title}</h5> 
        <p class="card-text">${description}</p>
        <p class="card-text">Creator: ${creatorUsername}</p>
        <a class="btn btn-primary" href="/posts/details/${id}">Details</a>
    </div>
</div>
</div>`
};

fetch(URLS.posts)
    .then(response => response.json())
    .then(posts => {
        let result = '';
        posts.forEach(p => {
            const itemString = post(p);
            result += itemString;
        });
        let row = document.createElement('div');
        $(row).addClass("row");
        $(row).html(result);
$('#all-posts').html(row)
    });