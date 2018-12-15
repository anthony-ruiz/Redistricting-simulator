// color array to color each district
var colorArray = ['#FF6633', '#FFB399', '#FF33FF', '#FFFF99', '#00B3E6',
    '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D',
    '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A',
    '#FF99E6', '#CCFF1A', '#FF1A66', '#E6331A', '#33FFCC',
    '#66994D', '#B366CC', '#4D8000', '#B33300', '#CC80CC',
    '#66664D', '#991AFF', '#E666FF', '#4DB3FF', '#1AB399',
    '#E666B3', '#33991A', '#CC9999', '#B3B31A', '#00E680',
    '#4D8066', '#809980', '#E6FF80', '#1AFF33', '#999933',
    '#FF3380', '#CCCC00', '#66E64D', '#4D80CC', '#9900B3',
    '#E64D66', '#4DB380', '#FF4D4D', '#99E6E6', '#6666FF'];

var currentMode = 1;

// list of states loaded
states = [];

// boolean for if a state is selected and current state
var stateSelected = false;
var currentState;

// the bounds that the user is allowed to traverse in
var maxBounds = [
    [5.499550, -185], //Southwest
    [83.162102, -52.233040]  //Northeast
];

// creation of the map
var map = L.map('map', { attributionControl: false, maxBounds: maxBounds }).setView([40, -100], 5);

var prevZoom = map.getZoom();

map.on('zoomend', function () {
    if (map.getZoom() < 8 && (prevZoom >= 8) && stateSelected) {
        prevZoom = map.getZoom();

        geojson.clearLayers();
        geojson = L.geoJSON(window[currentState + "_districts"], {
            style: districtStyle,
            onEachFeature: onEachDistrictFeature
        }).addTo(map);

        map.removeControl(precinctInfo);
        districtInfo.addTo(map);
        console.log("show district");
    }
    else if (map.getZoom() >= 8 && (prevZoom < 8) && stateSelected) {
        prevZoom = map.getZoom();

        geojson.clearLayers();
        geojson = L.geoJSON(window[currentState], {
            style: precinctStyle,
            onEachFeature: onEachPrecinctFeature
        }).addTo(map);

        map.removeControl(districtInfo);
        precinctInfo.addTo(map);
        console.log("show precinct");
    }
});

// assigning the tilelayer
L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}', {
    attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
    maxZoom: 16,
    minZoom: 5
}).addTo(map);

var geojson;

// style that is assigned when hovering over a provence on the map
function style(feature) {
    return {
        weight: 2,
        opacity: 1,
        color: 'green',
        dashArray: '3',
        fillOpacity: 0.7
    };
}

// search for the state clicked on in the array of states loaded
function search(nameKey, myArray) {
    for (var i = 0; i < myArray.length; i++) {
        if (myArray[i].name === nameKey) {
            return true;
        }
    }
    return false;
}

function toggleSideBar() {
    var x = document.getElementById("sidebar1");
    var y = document.getElementById("sidebar2");
    if (x.style.display === "none") {
        x.style.display = "initial";
        y.style.display = "none";
    } else {
        x.style.display = "none";
        y.style.display = "initial";
    }
}

function include(file) {
    return new Promise(function (resolve, reject) {
        var script = document.createElement('script');
        script.src = './precincts/' + file + '.js';
        script.type = 'text/javascript';
        script.defer = true;
        document.getElementsByTagName('head').item(0).appendChild(script);

        script.onload = function () {
            states.push({ name: file });
            resolve()
        }
        script.onerror = function () {
            reject()
        }
    })
}

// function to reset the map to the state view
function showAllStates() {
    stateSelected = false;
    map.setView([40, -100], 5);

    geojson.clearLayers();

    geojson = L.geoJSON(statesData, {
        style: style,
        onEachFeature: onEachStateFeature
    }).addTo(map);

    map.removeControl(precinctInfo);
    map.removeControl(districtInfo);
    info.addTo(map);
    toggleSideBar();
}

var req;
var req2;
var s;
var w;
var a;
var f;

function startAlgorithm() {
    s = currentState;
    w1 = document.getElementById("weight1").value;
    w2 = document.getElementById("weight2").value;
    w3 = document.getElementById("weight3").value;
    a = document.getElementById("algorithm").value;
    var algorithmObj = { "state": s, "politicalFairness": w1, "compactness": w2, "populationEquality": w3, "algorithm": a };
    var myJSON = JSON.stringify(algorithmObj);
    req = new XMLHttpRequest();
    var url = "http://localhost:8080/CSE308/JsonHandler";
    req.open("POST", url, true);
    getUpdates();
    req.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    req.onreadystatechange = validateAlgorithmSuccess;
    req.send(myJSON);
    
}

function validateAlgorithmSuccess() {
    if (req.readyState == 4 && req.status == 200) {
        alert(req.responseText)
    }
}

var finished = false;

function getUpdates() {
//    while(!finished) {
        req2 = new XMLHTTPRequest();
        var url = "http://localhost:8080/CSE308/PrecinctUpdater";
        req2.open("GET", url, true);
        req2.onreadystatechange = receiveUpdates;
        req2.send(null);
//    }
}

function receiveUpdates() {
    if (req2.readyState == 4 && req2.status == 200) {
        alert(req2.responseText);
    }
}