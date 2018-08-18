fs = require('fs');
http = require('http');
url = require('url');


http.createServer(function(req, res){
  var request = "https://www.google.com/images/srpr/logo11w.png"

     var img = fs.readFileSync(request);
     res.writeHead(200, {'Content-Type': 'image/gif' });
     res.end(img, 'binary');
 
}).listen(8080, '127.0.0.1');