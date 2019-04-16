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

function sendJoinRequest() {
    const content ={
        userId : document.getElementById("email_id").value+ "@" + document.getElementById("email_domain").value,
        password : document.getElementById("pw1").value,
        email : document.getElementById("email_id").value+ "@" + document.getElementById("email_domain").value,
        name : document.getElementById("name").value,
        phoneNumber:document.getElementById("cell1").value
            + "-" + document.getElementById("cell2").value
            + "-" + document.getElementById("cell3").value,
    };
    fetchManager({
        url: '/member',
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(content),
        callback: appendAnswer
    })
    return false;
}
