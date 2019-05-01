window.onload = function () {
    getCategories()
}

function appendAnswer(response) {
    console.log(response);
}

function appendCategories(result) {
    result.forEach(category =>{
        const parent = `
            <div class="category">
                    <div class="category-editor_add">
                        <div>
                        ${category.name}
                            <button class="delete-button" name="${category.id}" onClick="sendDeleteRequest(this.name)"></button>
                        </div>
                        <input id="category${category.id}" type="text">
                        <button class="add-button" name="${category.id}" onClick="sendCreateRequest(this.name)">추가하기</button>
                    </div>
                </div>
                `
        document.querySelector(".category-editor").insertAdjacentHTML("beforeend", parent);

        category.children.forEach(child =>{
            const childHtml = `
                    <div class="category-editor_contents">
                        <ul class="category-list">
                            <li class="category-list_item">
                                ${child.name}
                                <button class="delete-button" name="${child.id}" onClick="sendDeleteRequest(this.name)">
                                </button>
                            </li>
                        </ul>
                    </div>
                            
                `
            document.querySelector(".category:last-child").insertAdjacentHTML("beforeend", childHtml);
        })
    })

}

function deleteAnswer(response) {
    console.log(response);
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((response) => {
        const data = response.json();
        return data.then(result => {
            return {
                'result': result,
                'status': response.status
            }
        })
    }).then(({result, status}) => {
            if (status > 400) {
                console.log('error 가 발생했네요 ', result.error);
            } else {
                callback(result);
            }
        }
    ).catch(err => {
        console.log("oops..", err)
    })
}

function sendDeleteRequest(id) {
    console.log("삭졔 : " + id);
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
    const name = document.getElementById("category" + id).value;
    const parentId = id;
    const content = {
        name: name,
        parentId: parentId
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

function getCategories() {
    console.log("get Category method");
    fetchManager({
        url: '/api/menuCategory',
        method: 'GET',
        headers: {'Content-Type': 'application/json'},
        callback: appendCategories
    })
    return false;
}
