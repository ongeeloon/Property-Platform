$(document).ready(function() {
    $("#town").change(function() {
        sendAjaxRequest();
    });
});


function sendAjaxRequest() {
    let town = $("#town").val();
    $.get( "/street?town=" + town, function( data ) {
        $("#street").empty();
        data.sort();
        data.forEach(function(item, i) {
			let itemFull = "\"" + item + "\""
            let option = "<option value = " + itemFull + ">" + item +  "</option>";
            $("#street").append(option);
        });
    });
};





