var postId, userId;
var dialog = $("#users-list").dialog({
	title: "Seleccionar usuario",
    autoOpen: false,
    height: 190,
    width: 520,
    modal: true,
    buttons: {
      "Elegir": function() {
    	  userId = $("#user-to-like").val();
    	  if (userId !== "") {
	    	  $.ajax({
	    		  type: "POST",
	    		  url: "/api/like/add",
	    		  data: JSON.stringify({"postId": postId, "userId": userId}),
	    		  contentType: 'application/json; charset=utf-8',
	    		  dataType: 'json',
	    		  success: function(result) {
	    			  switch (result.code) {
	    			  	case 200:
	    			  		var postLikes = $("#counter-" + postId);
	    			  		var likes = parseInt(postLikes.html().trim());
	    			  		postLikes.html(result.data ? ++likes : likes);
	    			  		$("#user-to-like").val("");
	    			  		dialog.dialog("close");
	    			  		break;
					  }
	    		  }});
    	  }
      },
      "Cancelar": function() {
    	  dialog.dialog("close");
      }
    },
    close: function() {
    }
});

$(".like-link").click(function() {
	postId = $(this).attr('id');
	dialog.dialog("open");
});