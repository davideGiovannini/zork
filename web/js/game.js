

var getXhttp = function(){
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xhttp;
};




var doPost = function(path, data, callback){
    var xhttp = getXhttp();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            callback(xhttp);
        }
    };
    xhttp.open("POST", path, true);
    xhttp.send(data);
};


var doGet = function(path, callback){
    var xhttp = getXhttp();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            callback(xhttp);
        }
    };
    xhttp.open("GET", path, true);
    xhttp.send();
};





var refreshState = function(){
    doGet("game", function(xhttp){
        var xml = xhttp.responseXML;
        var actions = document.getElementById("actions");
        var xActions = xml.getElementsByTagName("action");
        var i;
        var node;

        //CleanUp actions
        while(actions.firstChild) {
            actions.removeChild(actions.firstChild);
        }

        for (i = 0; i < xActions.length; i++) {
            node = document.createElement("div");
            node.setAttribute("class", xActions[i].getAttribute("class"));
            node.setAttribute("onClick", "postAction(\""+node.getAttribute("class")+"\")");
            actions.appendChild(node);
        }
    });

};


var postAction = function(action){
    doPost("game?"+action, action, function(xhttp){
        alert(xhttp.responseText);
    });
};

