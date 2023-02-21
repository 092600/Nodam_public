function changeRegion(region) {
    window.location.href = '/clinic?page=1&region='+region;
}

$(document).ready(function(){
    var selectedRegion = $("#selectedRegion").val();
    $("#regionSelector").val(selectedRegion);
})