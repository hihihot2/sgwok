String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".add-answer-btn").click(addAnswer);

function addAnswer(e){
	e.preventDefault();
	console.log('add reply');
	
	var url = $(".reply-write").attr("action");
	console.log("url : "+url);
	
	var queryString = $(".reply-write").serialize();
	console.log("queryString : "+queryString);
	
	$.ajax({
		type: 'post',
		url:url,
		data:queryString,
		success:function(result) {
			console.log(result);
			
			var template = $("#replyTemplate").html();
			var returntemp = template.format(result.user.userId, result.formattedCreateDate, result.rep_contents, result.question.id, result.id);
			$(".reply-write").before(returntemp);
			$("textarea[name=contents]").val("");
		},
		error:function(e) {
			alert('Recall f5');
		}
		
	});
}

$(".delete-answer-button").click(deleteanswer);
function deleteanswer(e){
	
}