function isEmail(asValue) {
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    return regExp.test(asValue);
}

function findUserEmail(){
    var email = $("#email").val();

    if (isEmail(email)){
        $.ajax({
            url:"/api/v4/accounts/checkEmail?email="+email,
            type:"GET",
            cache:false,
            async:false,
            success:function(result){
                if (result){
                    if (confirm("입력하신 아이디가 존재합니다.\n" +
                        "해당 아이디의 비밀번호를 찾으시겠습니까?")){
                        window.location.href = "/accounts/findPw";
                    }
                } else {
                    alert("입력하신 아이디를 찾을 수 없습니다.");
                }
            },
            error:function(err){
                console.error(err);
            }

        });
    } else {
        alert("이메일을 입력해주세요");
    }
}


function findUserEmailInFindPw(){
    var email = $("#email").val();
    if (isEmail(email)){
        $.ajax({
            url:"/api/v4/accounts/find/password?email="+email,
            type:"GET",
            success:function(result){
                if (result == true) {
                    alert("해당 이메일로 인증번호가 전송되었습니다.");
                    $("#certificationNumberPTag").css("display", "block");
                    $("#findEmailContentPTag").css("margin-bottom", "12px");

                } else {
                    alert("입력하신 아이디를 찾을 수 없습니다.");
                }

            },
            error:function(err){
                console.error(err);
            }

        });
    } else {
        alert("이메일을 입력해주세요");
    }
}


function certificate() {
    data = {
        email : $("#email").val(),
        certificationInfo : {
            "certificationNumber" : $("#certifivationNumber").val(),
            "certificateDate" : new Date(), 
        }
    }

    $.ajax({
        url:"/api/v4/accounts/find/password",
        type:"PATCH",
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
        success:function(result){
            if (result){
                alert("회원님의 이메일로 임시비밀번호를 전송했습니다.\n" +
                "로그인 후 마이페이지에서 비밀번호 변경해주세요");
                window.location.href = "/";
            } else {
                alert("유효한 인증번호가 아닙니다.\n" +
                    "다시 시도해주세요.");
            }
        },
        error:function(err){
            console.error(err);
        }
    });

}

$(document).ready(function(){
    $('#email').keydown(function() {
        if (event.keyCode === 13) {
            event.preventDefault();
        };
    });
})


