function appendAnswer(response) {
    console.log(response);
    console.log(response.body)
}
function deleteAnswer(response) {
    console.log(response);
    console.log(response.body)
}

var $ = jQuery.noConflict();


function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        console.log(response);
        console.log(response.json())
    }).catch(err => {
        console.log("oops..", err)
    })
}

function sendDeleteRequest(id) {
    console.log("삭졔 : " +id);
    fetchManager({
        url: '/api/menuCategory/' + id,
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},
        body: null,
        callback: deleteAnswer
    })
    return false;
}

function sendCreateRequest(id) {
    const name = document.getElementById("category"+id).value;
    const parentId = id;
    const content ={
        name : name,
        parentId : parentId
    };
    console.log(content);
    fetchManager({
        url: '/api/menuCategory',
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(content),
        callback: appendAnswer
    })
    return false;
}
