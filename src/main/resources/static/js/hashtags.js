document.body.onload = function (event) {
    var elems = document.body.querySelectorAll('[data-contain-hashtags]');
    for (var i = 0; i < elems.length; i++) {
        elems[i].innerHTML = hashtags(elems[i].innerHTML);
    }
};

function hashtags(text) {
    var regex = /#[A-Za-z-]+/g;
    var hashArr = text.match(regex);
    for (var i = 0; hashArr != null && i < hashArr.length; i++) {
        text = text.replace(hashArr[i],
            "<a href='/search?hashtag=" + encodeURIComponent(hashArr[i]) + "'>" + hashArr[i] + "</a>");
    }
    return text;
}

function removeHashtags(text) {
    var elems = $.parseHTML(text);
    for (var i = 0; i < elems.length; i++) {
        text = text.replace(elems[i].outerHTML, elems[i].innerHTML);
    }
    return text;
}