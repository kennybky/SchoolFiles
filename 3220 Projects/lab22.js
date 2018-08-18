/*Question 1: (20pt) Add JavaScript code to Add.html so that if a user enters two numbers and clicks 
the Add button, the page displays the sum of the two numbers. */

$(function(){
    // Add your code here
	$("#add").click(function() {
	var x = $("input[name='x']").val();
	var y = $("input[name='y']").val();
	var sum = Number(x) + Number(y);
	$("#sum").text(sum);
	});
});

/*Question 2:(40pt) Add JavaScript code to DollarConvert.html so it converts an amount in dollar to another currency. In particular, the converted amount should be updated as the user types the dollar amount, 
and it should also be updated
 whenever the user selects a different currency. */

var rates = {
    "EUR" : 0.934,
    "GBP" : 0.791,
    "INR" : 64.659,
    "AUD" : 1.326
};
$(function(){
    // Add your code here
	$("input[name='dollar']").keyup(function() {
	var x = $("input[name='dollar']").val();
	var y = $("#currency").val();
	var c = Number(x) * Number(rates[y]);
	$("#amount").text(c);
	});
	
	$("#currency").change(function() {
	var x = $("input[name='dollar']").val();
	var y = $("#currency").val();
	var c = Number(x) * Number(rates[y]);
	$("#amount").text(c);
	});
	
	
});