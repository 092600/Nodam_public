$(document).ready(function(){
    document.getElementById('smokingmapDiv1').innerHTML='<object width="100%" height="100%" type="text/html" data="/mapHtml/히트맵.html"></object>';

    // document.getElementById('dashboardTitle').textContent = '히 트 맵'
    // document.getElementById('mapInfoDiv').innerText='서울시의 흡연구역은 중랑구, 광진구, 은평구가 가장 많습니다.'


    $('.godwjdrn').change(function(){
        var value_name = $('option:selected', this).text();

        document.getElementById('smokingmapDiv1').textContent = '';
        document.getElementById('smokingmapDiv1').innerHTML='<object width="100%" height="100%" type="text/html" data="/mapHtml/'+value_name+'.html"></object>';
        if (value_name == '서울시'){
            document.getElementById('smokingmapTitle').textContent = '흡연구역 지도';
            document.getElementById('mapInfoDiv').innerText='총 322개'
        } else if (value_name=='서울시 금연구역'){
            document.getElementById('smokingmapTitle').textContent = '금연구역 지도';
            document.getElementById('mapInfoDiv').innerText='총 5292개'
        }  else if (value_name=='히트맵'){
            document.getElementById('smokingmapTitle').textContent = '히 트 맵';
            document.getElementById('mapInfoDiv').innerText='서울시의 흡연구역은 중랑구, 광진구, 은평구가 가장 많습니다.'
        }else {
            var dict_gu = {}
            dict_gu['강남구'] = '2';
            dict_gu['강동구'] = '0';
            dict_gu['강서구'] = '12';
            dict_gu['강북구'] = '5';
            dict_gu['관악구'] = '5';
            dict_gu['광진구'] = '12';
            dict_gu['구로구'] = '12';
            dict_gu['노원구'] = '5';
            dict_gu['동대문구'] = '27';
            dict_gu['동작구'] = '1';
            dict_gu['마포구'] = '3';
            dict_gu['서대문구'] = '4';
            dict_gu['서초구'] = '24';
            dict_gu['성동구'] = '1';
            dict_gu['송파구'] = '4';
            dict_gu['양천구'] = '24';
            dict_gu['영등포구'] = '53';
            dict_gu['용산구'] = '37';
            dict_gu['은평구'] = '65';
            dict_gu['중구'] = '5';
            dict_gu['중랑구'] = '56';

            document.getElementById('smokingmapTitle').textContent = '흡연구역 지도';
            document.getElementById('mapInfoDiv').innerText = '총 '+dict_gu[value_name]+'개';
        }
    })
})