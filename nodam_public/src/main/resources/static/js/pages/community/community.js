function goToLoginPage() {
    alert("글 작성은 로그인 후 가능합니다.");
}

function searchPost(){
    recordSize = $("#findPostsSelectTag option:selected").val();
    title = $("#searchPostTitle").val();
    writer = $("#searchPostAuthor").val();

    if ((title == "") && (writer == "")) {
        window.location.href = "/community?page=0&recordSize="+recordSize;
    } else {
        window.location.href = "/community/post/search?title="+title+"&writer="+writer+"&page=0&recordSize="+recordSize;
    }

}



$(document).ready(function() {

    var uri = window.location.search;
    const recordSize = new URLSearchParams(uri).get("recordSize");

    if (recordSize == 10){
        $("#contentDiv").css("height", 1080);
        $("#communityTable").css("height", 690);
        $("#commnuityDiv2").css("height", 720);

    } else if (recordSize == 15){
        $("#contentDiv").css("height", 1225);
        $("#communityTable").css("height", 900);
        $("#commnuityDiv2").css("height", 760);
    } else if (recordSize == 20){

        $("#communityTable").css("height", 1223);
        $("#commnuityDiv2").css("height", 800);
        $("#contentDiv").css("height", 1570);
    } else {
        alert("알맞은 게시글 수를 입력해주세요");
        window.location.href = "/community?page=1&recordSize=10"
    }

    $("#findPostsSelectTag").val(recordSize).prop("selected", true);
})






