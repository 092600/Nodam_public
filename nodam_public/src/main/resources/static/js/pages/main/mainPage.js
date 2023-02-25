$(document).ready(function(){
    var data = {
        region : "서울",
    }
    $.ajax({
        type : 'GET',
        url : '/api/v4/clinic?region='+data.region,
        dataType : 'JSON',
        contentType : "application/json, charset=UTF-8",
        data : JSON.stringify(data),
    }).done(function(result) {
        for (let rs of Object.values(result)){
            $('#mainClinicTable > tbody:last-child').append('<tr class="tableRow">' +
                '<td class="organization">'+rs["organization"]+'</td>' +
                '<td class="address">'+rs["address"]+'</td>' +
                '<td class="number">'+rs["callNumber"]+'</td>' +
                '</tr>');
        }
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });



})

function regionChange(e){
    const clinicTableBody = document.querySelector(".clinicTableBody")
    var data = {
        region : $(e).attr("class"),
    }
    $.ajax({
        type : 'GET',
        url : '/api/v4/clinic?region='+data.region,
        dataType : 'JSON',
        contentType : "application/json, charset=UTF-8",
        data : JSON.stringify(data),
    }).done(function(result) {
        while (clinicTableBody.hasChildNodes()){
            clinicTableBody.removeChild(clinicTableBody.firstChild);
        }
        for (let rs of Object.values(result)){
            $('#mainClinicTable > tbody:last-child').append('<tr class="tableRow">' +
                '<td class="organization">'+rs["organization"]+'</td>' +
                '<td class="address">'+rs["address"]+'</td>' +
                '<td class="number">'+rs["callNumber"]+'</td>' +
                '</tr>');
        }
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}



function noSmoking(){
    var url = "/nosmoking";
    var name = "노담 서약서";
    var option = "width = 470, height = 570, top = 100, left = 200, location = no, resizable=no"
    window.open(url, name, option);
}

function noSmokingStop(){
    if (!confirm("금연을 중단하시겠습니까?")) {
        alert("잘 생각하셨어요 !\n조금만 더 힘내보아요 !!");
    } else {
        var data = {
            id : $("#id").val()
        }
        $.ajax({
            type : 'POST',
            url : '/api/v4/noSmokingStop',
            dataType : 'JSON',
            contentType : "application/json",
            data : JSON.stringify(data),
        }).done(function(date) {
            
            if (date != null) {
                alert(date +"일이나 금연하셨는데 아쉬워요 !\n" +
                "다음에 또 도전해봐요 !");
                window.location.href = "/";
            } else {
                alert("다시 한번 시도해주세요");
            }

        }).fail(function (error) {
            // alert(JSON.stringify(data));
        });
    }
}