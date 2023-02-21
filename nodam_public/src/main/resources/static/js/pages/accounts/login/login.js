function login(){
    var idValue = $("#id").val();
    var passwordValue = $("#password").val();

    if ((idValue == "") || (passwordValue == "")){
        alert("아이디 혹은 비밀번호가 입력되지않았습니다.");
    } else {
        var data = {
            email : idValue,
            password : passwordValue,
        }
        $.ajax({
            type : 'POST',
            url : '/api/v4/accounts/login',
            dataType : 'JSON',
            contentType : "application/json",
            data : JSON.stringify(data),
        }).done(function(result) {
            if (result){
                alert("로그인되었습니다.");
                window.location.href = "/";
            } else {
                alert("아이디 또는 비밀번호를 잘못 입력했습니다.\n" +
                    "입력하신 내용을 다시 확인해주세요.");
            }
        }).fail(function (error) {
            alert(JSON.stringify(data));
            alert(JSON.stringify(error));
        });
    }
}

$(document).ready(function(){
})