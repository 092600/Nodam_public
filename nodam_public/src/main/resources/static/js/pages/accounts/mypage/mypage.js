function isPassword(asValue) {
    var regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

    return regExp.test(asValue);
}

function isEmail(asValue) {
    var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    return regExp.test(asValue);
}


$(document).ready(function() {
    var userPasswordCertification = document.getElementById("userPasswordCertification");
    var password = document.getElementById("password");
    var password2 = document.getElementById("password2");

    $("#password").change(function (){
        var passwordValue = password.value;
        var passwordValueLength = passwordValue.length;
        if (passwordValueLength>=8){
            if (isPassword(passwordValue)){
                if (passwordValue == userPasswordCertification.value){
                    $("#passwordUnderPTag").text("기존의 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
                    $("#passwordUnderPTag").css("color", "red");
                } else {
                    $("#passwordUnderPTag").text("");
                }
            } else {
                $("#passwordUnderPTag").text("숫자, 영문 대소문자, 특수문자 중 두가지 이상으로 조합해 주십시오.");
                $("#passwordUnderPTag").css("color", "red");
            }
        } else {
            $("#passwordUnderPTag").text("8~30자 이내로 입력해 주십시오.");
            $("#passwordUnderPTag").css("color", "red");
        }
    })


    $('#password2').change(function(){
        var passwordValue = password.value;
        var password2Value = password2.value;
        if (passwordValue != password2Value){
            $("#password2UnderPTag").text("비밀번호가 일치하지 않습니다.");
            $("#password2UnderPTag").css("color", "red");
            $("#changingUserPasswordButton").attr("disabled", true);
        } else {
            if (passwordValue == ""){
                $("#password2UnderPTag").text("");
                $("#changingUserPasswordButton").attr("disabled", true);
            } else {
                $("#password2UnderPTag").text("비밀번호가 일치합니다.");
                $("#password2UnderPTag").css("color", "blue");
                $("#changingUserPasswordButton").attr("disabled", false);
            }
        }
    });

})

function deleteUserProfileImg(){
    if (confirm("기본 프로필 사진을 사용하시겠습니까?"))  {
        uploadable = false;

        $("#userProfileImgInput").val("");
        document.getElementById('userProfileImg').src = "/images/user.JPG";   
        
        var data = {
            email : $("#userEmail").text(),
        }
        
        $.ajax({
            url:"/api/v4/accounts/user/profileImg",
            type:"DELETE",
            dataType : 'JSON',
            contentType : "application/json",
            data : JSON.stringify(data),
            success:function(result){
                alert("기본 프로필 사진으로 변경되었습니다.");
                window.location.href = "/accounts/mypage";
            },
            error:function(err){
                alert("다시 한번 시도해주세요.");
                // window.location.href = "/";
            }

        });

    }

}

function changeUserProfileImg(input){
    if(!/\.(gif|jpg|jpeg|png)$/i.test(input.name)) {
        if (input.files && input.files[0] && (input.files[0].size < 1024 * 1022)) {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('userProfileImg').src = e.target.result;
                uploadable = true;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            alert("1MB 이하의 파일을 선택해주세요");
            input.value = "";
            document.getElementById('userProfileImg').src = "/images/user.JPG";
        }
    } else {
        alert("gif, jpg, jpeg, png 파일을 선택해주세요");
    }
    
}

var uploadable = false;

function saveProfileImg() {
    
    if (uploadable) {
        const profileImg = document.getElementById("userProfileImgInput").files[0];
        const email = $("#userEmail").text();

        const formData = new FormData();
        formData.append("profileImg", profileImg);
        formData.append("email", email);

        $.ajax({
            url:"/api/v4/accounts/user/profileImg",
            type:"POST",
            cache : false,
            contentType: false,               // * 중요 *
            processData: false,               // * 중요 *
            enctype : 'multipart/form-data',  // * 중요 *
            data : formData,
            success:function(result){
                if (result) {
                    alert("프로필 사진이 변경되었습니다.");    
                } else {
                    alert("다시 한번 시도해주세요");
                }
            },
            error:function(err){
                console.error(err);
            }
    
        });
    } else {
        alert("이미지를 업로드해주세요");
    }
}

function certificationPassword(){
    var userData = {
        email : $("#userEmail").text(),
        password : $("#userPasswordCertification").val(),
    }
    $.ajax({
        url:"/api/v4/accounts/mypage/user/certification",
        type:"POST",
        cache:false,
        async:false,
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(userData),
        success:function(result){
            if (result){
                $("#changePasswordDiv").css("display", "block");
                $("#userPasswordCertification").attr("disabled", true);
                $("#userPasswordCertificationButton").attr("disabled", true);
            } else {
                alert("비밀번호가 일치하지 않습니다.");
            }
        },
        error:function(err){
            console.error(err);
        }

    });
}

function changingPassword(){
    var userData = {
        email : $("#userEmail").text(),
        password : $("#password").val(),
    }
    
    $.ajax({
        url:"/api/v4/accounts/mypage/user/password",
        type:"PATCH",
        cache:false,
        async:false,
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(userData),
        success:function(result){
            if (result){
                alert("비밀번호가 변경되었습니다.");
                window.location.href = "/accounts/mypage";
            } else {
                alert("문제가 발생하였습니다.\n" +
                    "다시 시도해주세요.");
                window.location.href = "/accounts/mypage";
            }
        },
        error:function(err){
            console.error(err);
        }

    });
}