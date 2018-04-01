var net = require('net');
var app = require('express')();
var express = require('express');
var http = require('http').Server(app);
var integration = require('./lib/integration.js');

var socketClient = new net.Socket();

// socketClient.connect(1000, '127.0.0.1');
// socketClient.on('connect', () => {
// 	console.log("established socket conection on port 1000");
// })


//http server
http.listen(3000, function(){
  console.log('listening on *:3000');
});

app.use(express.static('public'));

// app.get('/', function(req, res){
// 	res.sendFile(__dirname + '/public/controls.html');
// });

app.post('/mouse/move/:direction', function(req, res){
	var mb = new integration.messageBuilder();
	mb.setTarget('mouse').setAction('move').addParameter(req.params.direction);
	console.log(req.params);
	res.send(mb.build());
})

