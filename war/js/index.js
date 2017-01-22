/**
 * 
 */
$(document).ready(function() {
	$("#OnlineStatus").everyTime(3000, function() {
		$.get('/jsp/AvailableUserCount.jsp', function(data) { 
			$("#OnlineStatus").html(data);
    	});
	}); 
});