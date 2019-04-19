function appendAnswer(response) {
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

function sendDeleteRequest(target) {
    fetchManager({
        url: '/member',
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(content),
        callback: appendAnswer
    })
    return false;
}
