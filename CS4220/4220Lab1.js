/* Sample Data */
const sample = [
		'http://twitter.com/',
        'http://www.spotify.com/us/',
        'http://google.com/nothing',
        'http://www.google.com/'
    ]

	/* Question 1 */
	const getTimes = (urls, callback) => {
	 const http = require('http')
	 let times = []
	 for (let url of urls){
	 let begin = Date.now()
	 http.get(url, (res) => {
	  let cost = Date.now() - begin;
	  let newObj = new Object();
	   newObj.url = url;
	   newObj.time = cost;
	   times.push(newObj);
	   if (times.length == urls.length) {
	   callback(times);
	}
	});
	}
	}




function orderTimes(s) {
		getTimes(s, function(times){
				var sorted = times.sort(function(i, j) {
    return parseFloat(i.time) - parseFloat(j.time);
		})
				console.log(sorted)
			})
}
/*End of Question 1*/


/*Question 2*/
function getResponses(urls) {
	const http = require('http')
	let promises = urls.map((url)=>{
	return new Promise((resolve, reject)=>{
	  http.get(url, (res) => {
	  let newObj = new Object();
	   newObj.url = url;
	   newObj.status = res.statusCode;
	   resolve(newObj);
	});
	});

	});

	Promise.all(promises).then((results)=>{
		let status = new Object();
		status.success = []
		status.error = []
		for (var obj of results) {
			if (obj.status >= 200 && obj.status <= 399) {
				status.success.push(obj.url)
			} else if(obj.status >= 400 || obj.status <= 599) {
				status.error.push(obj.url)
			} else {
				// skip it, it is an enigma
			}
		}
		console.log(status)
	}).catch(error=>{
		console.log(error)
	})

}
/*End of Question 2*/


/* Test cases */
orderTimes(sample)
getResponses(sample)


/* End of Test Cases */


