//http://jmesnil.net/stomp-websocket/doc/
//Create stomp client over sockJS protocol
var socket = new SockJS(baseURL+"/chatSocket");
var stompClient = Stomp.over(socket);
console.log("ran the socket creation '" + baseURL+"'");

var doSendUser = function ()
{
    var jsonstr = JSON.stringify({'message': 'geta job for a the user'});
    console.log("sending to user " + jsonstr)
    stompClient.send("/app/userAction", {}, jsonstr)
}

var doSendAppCode = function ()
{
    var jsonstr = JSON.stringify({'message': 'geta job'});
    stompClient.send("/app/bonzoAction", {}, jsonstr)

}

var renderBonzo = function (frame) {
    // frame.command, frame.headers, frame.body, toString()
    //console.log("destination " + frame.headers.destination);
    // console.log("frame to String "+frame.toString());
    var body = JSON.parse(frame.body);
    console.log(body.message);
    $('#renderBonzo').text(body.message);

}

var renderUser = function (frame)
{
    var body = JSON.parse(frame.body);
    console.log("User handler got " + body.message + " user " + frame.headers['user-name']);
    $('#renderUser').text(body.message);
}

var renderFrodo = function (frame) {
    // frame.command, frame.headers, frame.body, toString()
    //console.log("destination " + frame.headers.destination);
    // console.log("frame to String "+frame.toString());
    var body = JSON.parse(frame.body);
    console.log(body.message);
    $('#renderFrodo').text(body.message);

}
var connectCallback = function (frame) {
    stompClient.subscribe('/topic/bonzo', renderBonzo);
    stompClient.subscribe('/queue/frodo', renderFrodo);
    stompClient.subscribe('/user/queue/userChannel', renderUser);

    console.log("connect call back %" + frame + "%");
};

// Callback function to be called when stomp client could not connect to server
var errorCallback = function (error) {
    alert(error.headers.message);
};



$(document).ready(function () {
    stompClient.connect("guest", "guest", connectCallback, errorCallback);
});




