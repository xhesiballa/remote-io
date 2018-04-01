var net = require('net');
var app = require('express')();
var http = require('http').Server(app);

var socketClient = new net.Socket();

// socketClient.connect(1000, '127.0.0.1');
// socketClient.on('connect', () => {
// 	console.log("established socket conection on port 1000");
// })


//http server

http.listen(3000, function(){
  console.log('listening on *:3000');
});

app.get('/', function(req, res){
	res.sendFile(__dirname + '/view/index.html');
});