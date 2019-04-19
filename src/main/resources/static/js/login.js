function appendAnswer(response) {
    window.location.href = window.location.origin;
}
var $ = jQuery.noConflict();


function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        if(response.status == 200){
            callback(response);
        }
        console.log(response);
    }).catch(err => {
        console.log("oops..", err)
    })
}

function sendLoginRequest() {
    const content ={
        userId : document.getElementById("member_id").value,
        password : document.getElementById("pwd").value
    };
    fetchManager({
        url: '/member/login',
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(content),
        callback: appendAnswer
    })
    return false;
}
