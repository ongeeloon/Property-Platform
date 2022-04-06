var script = document.createElement('script');

script.src = '//code.jquery.com/jquery-1.11.0.min.js';
document.getElementsByTagName('head')[0].appendChild(script);

$(document).ready(function(){
    $("#uploadFile").change(function(){
        checkImageFileSize($(this));
    })
});

function deleteImageFromListing2(propId, filepath, rowNumber) {
    if(confirm("You are about to remove this image from your listing. This action cannot be undone. Do you want to" +
        " proceed?")){
        filename = filepath.substring(filepath.lastIndexOf("/") + 1);
        url = '/property/editlisting/deleteimage/' + propId + '/' + filename;
        $.post("/property/editlisting/deleteimage/" + propId + "/" + filename, function(response){
            if (response.includes("imagedeleted")) {
                removeImage2(rowNumber);
            }
        })
    }
    else{

    }
}

function removeImage2(rowNumber){
    imageCardID = "image-card" + rowNumber;
    var card = document.getElementById(imageCardID);
    card.remove();
    console.log("Function ended");
}


function checkImageFileSize(){
    var MAX_FILE_SIZE = 2 * 1024 * 1024; // 2MB
    let files = document.getElementById("uploadFile").files;
    var totalFileSize = 0;
    var submitButton = document.getElementById("imageSubmitButton");

    for(let i = 0; i < files.length; i ++){
        totalFileSize += files[i].size;
    }
    if (totalFileSize > MAX_FILE_SIZE) {
        console.log("Error. File upload must not exceed 2 MB!")
        alert("File upload must not exceed 2 MB!");
        submitButton.disabled = true;
    } else {
        submitButton.disabled = false;
    }
}

function deletePropListConfirm(propId){
    if(confirm("You are about to delete this listing. This action cannot be undone. Do you want to proceed?")){
        $.post("/property/deletelisting/" + propId, function (response){
            if (response.includes("proplistdeleted")) {
                location.reload(true);
            }
        })
    }
    else{

    }
}

;