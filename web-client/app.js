var net = require('net');
var app = require('express')();
var express = require('express');
var http = require('http').Server(app);
var integration = require('./lib/integration.js');

var socketClient = new net.Socket();

socketClient.connect(1000, '127.0.0.1');
socketClient.on('connect', () => {
	console.log("established socket conection on port 1000");
})


//http server
http.listen(3000, function(){
  console.log('listening on *:3000');
});

app.use(express.static('public'));
app.post('/mouse/move/:x/:y', function(req, res){
	var mb = new integration.messageBuilder();
	mb.setTarget('mouse').setAction('move').addParameter(req.params.x).addParameter(req.params.y);
	socketClient.write(mb.build() + '\n');
	// console.log(req.params);
	res.send(mb.build());
});

app.post('/mouse/click/:button', function(req, res){
	var mb = new integration.messageBuilder();
	mb.setTarget('mouse').setAction('click').addParameter(req.params.button);
	socketClient.write(mb.build() + '\n');
	// console.log(req.params);
	res.send(mb.build());
});


