function noSmoking(){
    var data = {
        id : $("#id").val(),
    }

    $.ajax({
        type : 'POST',
        url : '/api/v4/accounts/user/noSmoking',
        dataType : 'JSON',
        contentType : "application/json",
        data : JSON.stringify(data),
    }).done(function(result) {
        const d = new Date(result);
        const date = new Date(d.getTime()).toISOString().split('T')[0];
        const time = d.toTimeString().split(' ')[0];

        alert(date +" "+ time +" 부터 금연 시작합니다.\n 우리 같이 힘내봐요");
        opener.location.href = "/";
        window.close();
    }).fail(function (error) {
    });

}