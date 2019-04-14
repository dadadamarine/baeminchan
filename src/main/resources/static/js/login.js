function appendAnswer({id, contents, question, writer, time}) {
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
    }).then( ({result, status}) => {
        if(status >= 400) {
            console.log('error 가 발생했네요 ', result.error);
        }else{
            callback(result)
        }
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
