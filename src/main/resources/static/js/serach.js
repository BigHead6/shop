

$(document).ready(function(){
    $("#ai-topsearch").on('click',function () {
        var name = $("#searchInput").val();
        location.href='search?txt=' + encodeURI(name);//将值发送到search页面

    })
});

