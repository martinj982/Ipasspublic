function loadOornr() {
			$.getJSON("restservices/koeien", function(data) {

				$.each(data, function(i, koe) {
					$("#selected").append(
							"<option value="+koe.oornr+">" + koe.oornr
									+ "</option>");
					  
				});
			});
		}

function loadOornrMelk() {
	$.getJSON("restservices/melk/oornr", function(data) {

		$.each(data, function(i, koe) {
			$("#selected").append(
					"<option value="+koe.oornr+">" + koe.oornr
							+ "</option>");
			  
		});
	});
}



function loadOornrVoer() {
			$.getJSON("restservices/koeien/voer/oornr", function(data) {

				$.each(data, function(i, koe) {
					$("#selected").append(
							"<option value="+koe.oornr+">" + koe.oornr
									+ "</option>");
				
				});
			});
		}