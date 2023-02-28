var uri = window.location.search;
const id = new URLSearchParams(uri).get("id");


function commentCreate(){
    var data = {
        writer : $("#CommentCreatorId").text(),
        content : $("#comment").val(),
        post : {
            id : $("#postNum").val(),
        }
    }

    $.ajax({
        type : 'POST',
        url : '/api/v4/community/post/comment',
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(data),
        success:function(result){
            if (result) {
                alert("댓글이 등록되었습니다.");
                window.location.href = "/community/post/view?id="+data.post.id;
            } else {
                alert("다시 시도해주세요");
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    })
}

function commentDelete(commentId, postId, email) {
    var data = {
        id : commentId,
        writer : email,
        post : {
            id : $("#postNum").val(),
        }
    }
    $.ajax({
        type : 'DELETE',
        url : '/api/v4/community/post/comment',
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(data),
        success:function(result){
            if (result) {
                alert("댓글이 삭제되었습니다.");
                window.location.href = "/community/post/view?id="+postId;
            } else {
                alert("다시 시도해주세요");
            }
        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    })
}


function postCreate(){
    var data = {
        writer : $("#writer").val(),
        title : $("#title").val(),
        content : $("#content").val(),
    }

    $.ajax({
        type : 'POST',
        url : '/api/v4/community/post',
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(data),
        success:function(result){
            if (result) {
                alert("글이 등록되었습니다.");
                window.location.href = "/community?page=0&recordSize=10"
            } else {
                alert("다시 한번 시도해주세요.");
            }

        },
        error:function(err){
            alert("다시 한번 시도해주세요.");
        }
    })
}

function postDelete(){
    if (confirm("해당 글을 삭제하시겠습니까?")){
        $.ajax({
            type : 'DELETE',
            url : '/api/v4/community/post?id='+id,
        }).done(function(result) {
            if (result){
                alert("글이 삭제되었습니다");
                window.location.href = '/community?page=0&recordSize=10'
            } else {
                alert("비정상적인 접근이 감지되었습니다.");
                window.location.href = '/community?page=0&recordSize=10'
            }
        }).fail(function (error) {
            alert("다시 한번 시도해주세요.");
        });
    }
}

function postUpdate(){
    var data = {
        id : id,
        title : $("#updatePostTitle").val(),
        content : $("#updatePostContent").val(),
    }
    $.ajax({
        type : 'PATCH',
        url : '/api/v4/community/post',
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',
        data : JSON.stringify(data),
    }).done(function(data) {
        alert("글이 수정되었습니다.");
        window.location.href = '/community/post/view?id='+id;
    }).fail(function (error) {
        alert(JSON.stringify(error));
        // alert("글 등록에 실패했습니다r.");
    });
}


$(document).ready(function() {

    const commentsCnt = $(".commentsEachDiv").length;
    if (commentsCnt != 0) {
        const postCommentsDivHeight = (commentsCnt * (180) + 140 + 80);

        $(".postCommentsDiv").css("height", postCommentsDivHeight);
    }
    
})