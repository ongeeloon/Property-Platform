$(document).ready(function() {
	$("#district").change(function() {
		sendAjaxRequest();
	});
	checkFreehold();
	submitCheck()
});


function sendAjaxRequest() {
	let district = $("#district").val();
	$.get("/project?district=" + district, function(data) {
		$("#project").empty();
		data.sort();
		data.forEach(function(item, i) {
			let itemFull = "\"" + item + "\""
			let option = "<option value = " + itemFull + ">" + item + "</option>";
			$("#project").append(option);
		});
	});
};

function checkFreehold() {
	$("#freehold").change(function() {
		let selection = $("input[name='leasetype']:checked").val()
		if (selection == "freehold") {
			$('#tenure').val('')
			$('#tenure').attr('disabled', 'disabled')
		} /*else if (selection == "leasehold") {
			$('#tenure').prop('disabled', false)
		}*/
	});
	$("#leasehold").change(function() {
		let selection = $("input[name='leasetype']:checked").val()
		if (selection == "leasehold") {
			$('#tenure').prop('disabled', false)
		}
	})
}

function submitCheck() {
	$("#priceform").submit(function(event) {
		let selection = $("input[name='leasetype']:checked").val()
		if (selection == "freehold") {
			$(this).append('<input type="hidden" name="tenure" value="999" /> ');
			return true;
		}
	});
}


