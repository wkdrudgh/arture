$(function() {
	$.scrollify({
		section : ".panel",
		scrollbars : false,
		before : function(i, panels) {
			var ref = panels[i].attr("data-section-name");
		},
		afterRender : function() {
			var activeClass = "";
			$(".panel").each(function(i) {
				activeClass = "";
				if (i === $.scrollify.currentIndex()) {
					activeClass = "active";
				}

			});
		}
	});
	
});