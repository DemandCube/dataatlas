// external modules
var express  = require('express');

// start the express server
var app = express();

app.use(express.static(__dirname + '/../public'));

require('./routes/appRoutes.js')(app);

module.exports = app;
