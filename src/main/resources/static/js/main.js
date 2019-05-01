let sliderCount;
let sliderIndex = 0;
let slider;
let sliderDots;
window.onload = function () {
    sliderCount = document.querySelector("ul.img-box").children.length;
    slider = document.querySelectorAll(".img-box li.img-item");
    sliderDots = document.querySelector(".dot-btn-box").children;
    document.querySelector(".bm-icon.spr-btn-arrow-main-slide.next").addEventListener("click", next);
    document.querySelector(".bm-icon.spr-btn-arrow-main-slide.prev").addEventListener("click", prev);
}

const next = function (e) {
    removeClasses();
    sliderIndex = toNext(sliderIndex);
    addNextClasses();
}

const prev = function (e) {
    removeClasses();
    sliderIndex = toPrev(sliderIndex);
    addPrevClasses();
}

function toPrev(index) {
    return (index - 1 + 5) % sliderCount;
}

function toNext(index) {
    return (index +1 ) % sliderCount;
}

function removeClasses() {
    removeAllVisible();
    slider[toPrev(sliderIndex)].classList.remove("prev");
    slider[sliderIndex].classList.remove("current");
    slider[toNext(sliderIndex)].classList.remove("next");

    sliderDots[sliderIndex].classList.remove("on");
}

function removeAllVisible() {
    for(let i=0; i<sliderCount; i++){
        slider[i].classList.remove("visible");
    }
}

function addPrevClasses() {
    slider[toPrev(sliderIndex)].classList.add("prev");
    slider[sliderIndex].classList.add("current");
    slider[sliderIndex].classList.add("visible");
    slider[toNext(sliderIndex)].classList.add("next");
    slider[toNext(sliderIndex)].classList.add("visible");

    sliderDots[sliderIndex].classList.add("on");
}

function addNextClasses() {
    slider[toPrev(sliderIndex)].classList.add("prev");
    slider[toPrev(sliderIndex)].classList.add("visible");
    slider[sliderIndex].classList.add("current");
    slider[sliderIndex].classList.add("visible");
    slider[toNext(sliderIndex)].classList.add("next");

    sliderDots[sliderIndex].classList.add("on");

}
