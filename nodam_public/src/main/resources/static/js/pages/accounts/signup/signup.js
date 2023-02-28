
function isPassword(asValue) {
    var regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

    return regExp.test(asValue);
}

function isEmail(asValue) {
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    return regExp.test(asValue);
}

function genderButtonClick(e){
    var genderButtonId = $(e).attr("id");

    $(".genderButton").css("background-color", "white");
    $("#"+genderButtonId).css("background-color", "rgb(250, 197, 103)");

    $(".gender").val(genderButtonId);
}

function submitUserInfo() {
    var namePTag = $("#name").text()
    var emailPTag = $("#emailUnderPTag").text();
    var passwordPTag = $("#passwordUnderPTag").text();
    var password2PTag = $("#password2UnderPTag").text();

    var data = {
        name : $("#name").val(),
        email : $("#email").val(),
        password : $("#password").val(),
        userInfo : {
            "gender" : $(".gender").val(),
            "birthday" : $("#birthday").val(),
        }
    }

    console.log(data);
    console.log(data.userInfo);

    if ((namePTag == "") & (emailPTag == "사용할 수 있는 이메일입니다.") & (passwordPTag == "") & (password2PTag == "비밀번호가 일치합니다.")) {
        $.ajax({
            url:"/api/v4/accounts/signup",
            type:"POST",
            dataType : 'JSON',
            contentType : "application/json",
            data : JSON.stringify(data),
            success:function(result){
                alert("회원가입이 완료되었습니다.");
                window.location.href = "/";
            },
            error:function(err){
                alert("다시 한번 시도해주세요.");
                // window.location.href = "/";
            }

        });
    } else {
        alert("필수입력사항을 모두 입력해주세요.");
    }

}

var availableName = false;
var availableEmail = false;
var availablePassword = false;
var availablePassword2 = false;
$(document).ready(function(){
    $('#email').change(function(){
        var email = $('#email').val();
        if (isEmail(email)){
            $("#emailUnderPTag").text("");
            $.ajax({
                url:"/api/v4/accounts/checkEmail?email="+email,
                type:"GET",
                cache:false,
                async:false,
                // dataType : 'JSON',
                // contentType : "application/json, charset=UTF-8",
                data : $("#userSignUpForm").serialize(),
                success:function(result){
                    if (result){
                        $("#emailUnderPTag").text("사용할 수 없는 이메일입니다.");
                        $("#emailUnderPTag").css("color", "red");
                    } else {
                        $("#emailUnderPTag").text("사용할 수 있는 이메일입니다.");
                        $("#emailUnderPTag").css("color", "blue");
                        availableEmail = true;
                    }
                },
                error:function(err){
                    console.error(err);
                    availableEmail = false;
                }

            });
        } else {
            $("#emailUnderPTag").text("이메일 형식에 맞지 않습니다.");
            $("#emailUnderPTag").css("color", "red");
            availableEmail = false;
        }
    });

    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");
    $('#password').change(function(){
        var passwordValue = password.value;
        var passwordValueLength = passwordValue.length;
        if (passwordValueLength>=8){
            if (isPassword(passwordValue)){
                $("#passwordUnderPTag").text("");
                availablePassword = true;
            } else {
                $("#passwordUnderPTag").text("숫자, 영문 대소문자, 특수문자 중 두가지 이상으로 조합해 주십시오.");
                $("#passwordUnderPTag").css("color", "red");
                availablePassword = false;
            }
        } else {
            $("#passwordUnderPTag").text("8~30자 이내로 입력해 주십시오.");
            $("#passwordUnderPTag").css("color", "red");
            availablePassword = false;
        }
    });

    $('#password2').change(function(){
        var passwordValue = password.value;
        var password2Value = password2.value;
        if (passwordValue != password2Value){
            $("#password2UnderPTag").text("비밀번호가 일치하지 않습니다.");
            $("#password2UnderPTag").css("color", "red");
            availablePassword2 = false;
        } else {
            $("#password2UnderPTag").text("비밀번호가 일치합니다.");
            $("#password2UnderPTag").css("color", "blue");
            availablePassword2 = true;
        }
    });
    $("#name").change(function(){
        if ($("#name").val() == ""){
            $("#nameUnderPTag").text("이름을 입력해주세요.");
            $("#nameUnderPTag").css("color", "red");
        } else {
            $("#nameUnderPTag").text("");
            availableName = true;
        }
    })

})
