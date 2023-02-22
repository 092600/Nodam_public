function goToLoginPage() {
    alert("글 작성은 로그인 후 가능합니다.");
}


$(document).ready(function() {


    var uri = window.location.href;
    var recordSize = uri.split("&").at(1).split("=").at(1);

    if (recordSize == 5){
        $("#indexContentDiv").css("height", 288);
        $("#commnuityDiv2").css("height", 255);
        $("#contentDiv").css("height", 465);
    } else if (recordSize == 10){
        $("#indexContentDiv").css("height", 535);
        $("#contentDiv").css("height", 645);
    } else if (recordSize == 15){
        $("#indexContentDiv").css("height", 565);
        $("#commnuityDiv2").css("height", 545);
        $("#contentDiv").css("height", 745);
    } else if (recordSize == 20){
        $("#commnuityDiv2").css("height", 685);
        $("#contentDiv").css("height", 885);
    } else {
        alert("알맞은 게시글 수를 입력해주세요");
        window.location.href = "/community?page=1&recordSize=10"
    }
    $("#findPostsSelectTag").val(recordSize).prop("selected", true);
})
function isit(){
    alert("로그인 후 글을 작성할 수 있습니다. \n" +
        "로그인 화면으로 이동합니다.");
}

// function postCreate(){
//     var data = {
//         writer : $("#postWriter").val(),
//         title : $("#postTitle").val(),
//         content : $("#postContent").val(),
//     }

//     $.ajax({
//         type : 'POST',
//         url : '/api/v4/community/post',
//         dataType : 'json',
//         contentType : 'application/json; charset=utf-8',
//         data : JSON.stringify(data),
//     }).done(function(postNum) {
//         alert("글이 등록되었습니다.");
//         window.location.href = '/community?page=1&recordSize=10';
//     }).fail(function (error) {
//         alert("글 등록에 실패했습니다.");
//     });
// }

function postDelete(){
    var postNum = $("#postNum").val();

    $.ajax({
        type : 'DELETE',
        url : '/api/v4/community/board?postNum'+postNum,
        header:{
            "Content-Type" : "application/json",
            "X-HTTP-Method-Override" : "DELETE"
        },
        // dataType : 'json',
        // contentType : 'application/json; charset=utf-8',
        }).done(function(result) {
        if (result){
            alert("글이 삭제되었습니다");
            window.location.href = '/community?page=1&recordSize=10'
        } else {
            alert("비정상적인 접근이 감지되었습니다.");
            window.location.href = '/community?page=1&recordSize=10'
        }
        }).fail(function (error) {
            alert(JSON.stringify(error));

        // alert("글 등록에 실패했습니다r.");
        });
}

function postUpdate(){
    var postNum = $("#postNum").val();
    var data = {
        updatePostNum : $("#postNum").val(),
        updatePostTitle : $("#updatePostTitle").val(),
        updatePostContent : $("#updatePostContent").val(),
    }
    $.ajax({
        type : 'PATCH',
        url : '/api/v4/community/board/'+postNum,
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(data),
    }).done(function(data) {
        alert("글이 수정되었습니다.");
        window.location.href = '/community?page=1&recordSize=10';
    }).fail(function (error) {
        alert(JSON.stringify(error));
        // alert("글 등록에 실패했습니다r.");
    });
}
function searchPost(){
    var data = {
        recordSize : $("#findPostsSelectTag option:selected").val(),
        searchPostTitle : $("#searchPostTitle").val(),
        searchPostAuthor : $("#searchPostAuthor").val(),
    }

    $.ajax({
        type : 'POST',
        url : '/api/v4/community/board/search',
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(data),
    }).done(function(result) {
        if (result == 1) {
            window.location.href = '/community/board/search?page=1&recordSize='+data.recordSize+'&searchPostTitle='+data.searchPostTitle+'&searchPostAuthor='+data.searchPostAuthor;
        } else if (result == 2){
            window.location.href = '/community?page=1&recordSize='+data.recordSize;
        } else {
            alert("비정상적인 접근입니다.");
        }

    }).fail(function(error) {
        alert(JSON.stringify(error));
        // alert("글 등록에 실패했습니다r.");
    });
}
