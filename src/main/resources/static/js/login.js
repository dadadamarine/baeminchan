function appendAnswer({id, contents, question, writer, time}) {
    console.log("append");
    // const html = `
    //     <article class="article">
    //         <div class="article-header">
    //             <div class="article-header-thumb">
    //                 <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
    //             </div>
    //             <div class="article-header-text">
    //                 <a href="/users/${writer.id}" class="article-author-name">${writer.name}</a>
    //                 <div class="article-header-time">${time}</div>
    //             </div>
    //         </div>
    //         <div class="article-doc comment-doc">
    //             ${contents}
    //         </div>
    //         <div class="article-util">
    //         <ul class="article-util-list">
    //             <li>
    //                 <a class="link-modify-article" href="/api/questions/${question.id}/answers/${id}">수정</a>
    //             </li>
    //             <li>
    //                 <form class="delete-answer-form" action="/api/questions/${question.id}/answers/${id}" method="POST">
    //                     <input type="hidden" name="_method" value="DELETE">
    //                     <button type="submit" class="delete-answer-button">삭제</button>
    //                 </form>
    //             </li>
    //         </ul>
    //         </div>
    //     </article> `
    //
    // $(".qna-comment-slipp-articles").insertAdjacentHTML("beforeend", html);
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
        //window.location.assign(response.location);

        // const data = response.json();
        // return data.then(result =>  {
        //     return {
        //         'result' : result,
        //         'status' : response.status
        //     }
        // })
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
