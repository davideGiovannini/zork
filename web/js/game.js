// AJAX UTILITY FUNCTIONS
var getXhttp = function () {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xhttp;
};

var doPost = function (path, data, callback) {
    var xhttp = getXhttp();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            callback(xhttp);
        }
    };
    xhttp.open("POST", path, true);
    xhttp.send(data);
};

var doGet = function (path, callback) {
    var xhttp = getXhttp();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            callback(xhttp);
        }
    };
    xhttp.open("GET", path, true);
    xhttp.send();
};


var refreshState = function () {
    doGet("game", updateStateFromXML);

};


var postAction = function (action) {
    doPost("game?" + action, action, function (xhttp) {
        try {
            updateStateFromXML(xhttp);
        }catch (error){
            location.reload();
        }
    });
};


var updateStateFromXML = function (xhttp) {
    var xml = xhttp.responseXML,
        actions = document.getElementById("actions"),
        place = document.getElementById("place"),
        xActions = xml.getElementsByTagName("action"),
        xPlace = xml.getElementsByTagName("place")[0],
        i,
        node, text;

    //CleanUp actions
    while (actions.firstChild) {
        actions.removeChild(actions.firstChild);
    }

    //Set actions
    for (i = 0; i < xActions.length; i++) {
        node = document.createElement("div");
        text = document.createElement("h1");
        node.setAttribute("class", xActions[i].getAttribute("class"));
        node.setAttribute("onClick", "postAction(\"" + node.getAttribute("class") + "\")");

        text.setAttribute("class", "action_description");
        text.innerHTML = xActions[i].innerHTML;

        node.appendChild(text);

        actions.appendChild(node);
    }

    //Set place
    place.setAttribute("class", xPlace.getAttribute("class"));
    document.getElementById("place_title").innerHTML = xPlace.innerHTML;

};
