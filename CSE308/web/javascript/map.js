// a list of all the state objects
var states = [{ state: Arizona, name: "Arizona" }, { state: Wisconsin, name: "Wisconsin" }, { state: Minnesota, name: "Minnesota" }];

// the bounds that the user is allowed to traverse in
var maxBounds = [
    [5.499550, -185], //Southwest
    [83.162102, -52.233040]  //Northeast
];

// creation of the map
var map = L.map('map', { attributionControl: false, maxBounds: maxBounds }).setView([40, -100], 5);


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
            return myArray[i].state;
        }
    }
}

function toggleSideBar() {
    var x = document.getElementById("sidebar1");
    var y = document.getElementById("sidebar2");
    if (x.style.display === "none") {
        x.style.display = "initial";
        y.style.display = "none";
        document.getElementById("weight1").value = document.getElementById("weight1").default;
        document.getElementById("weight2").value = document.getElementById("weight2").default;
        document.getElementById("weight3").value = document.getElementById("weight3").default;
        var radioA = document.getElementsByName("algorithm");
        radioA[0].checked = false;
        radioA[1].checked = false;
    } else {
        x.style.display = "none";
        y.style.display = "initial";
        document.getElementById("states").value = document.getElementById("states").default;
    }
}

// function to reset the map to the state view
function showAllStates() {

    map.setView([40, -100], 5);

    geojson.clearLayers();

    geojson = L.geoJSON(statesData, {
        style: style,
        onEachFeature: onEachStateFeature
    }).addTo(map);

    map.removeControl(precinctInfo);
    info.addTo(map);
    toggleSideBar();
}