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

var weights = .5;
var districtAmount;

var currentMode = 0;
var currentAlg;

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
var map = L.map('map', {
    attributionControl: false, maxBounds: maxBounds
}).setView([40, -100], 5);

L.control.scale().addTo(map);

var prevZoom = map.getZoom();

// assigning the tilelayer
// L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}', {
//     attribution: 'Tiles &copy; Esri &mdash; Esri, DeLorme, NAVTEQ',
//     maxZoom: 16,
//     minZoom: 5
// }).addTo(map);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
        '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
        'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox.light',
    maxZoom: 16,
    minZoom: 5
}).addTo(map);

var searchControl = L.esri.Geocoding.geosearch({
    position: 'topleft'
}).addTo(map);

var results = L.layerGroup().addTo(map);

// searchControl.on('results', function (data) {
//     results.clearLayers();
//     for (var i = data.results.length - 1; i >= 0; i--) {
//         results.addLayer(L.marker(data.results[i].latlng));
//     }
// });

map.on('zoomend', function () {
    if (map.getZoom() < 8 && (prevZoom >= 8) && stateSelected && !loggedIn) {
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
    else if (map.getZoom() >= 8 && (prevZoom < 8) && stateSelected && !loggedIn) {
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
    var x = document.getElementById("leftbar1");
    var y = document.getElementById("leftbar2");
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
    currentAlg = null;
    stateSelected = false;
    document.getElementById("algorithm").checked = false;
    document.getElementById("rightbar1").style.display = "none";


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
var w1;
var w2;
var w3;
var a;
var f;

function startAlgorithm() {
    if (document.getElementById("algorithm").checked && document.getElementById("algorithm").value === 'REGION_GROWING') {
        loadRegionGrowingDefault();
        document.getElementById("display2").checked = true;
        currentAlg = 2;

        s = currentState;
        w1 = document.getElementById("weight1").value;
        w2 = document.getElementById("weight2").value;
        w3 = document.getElementById("weight3").value;
        a = document.getElementById("algorithm").value;
        var str = $("input[name=variant]:checked").val();
        var seeds = [];

        customSeeds.forEach(function (e) {
            seeds.push(e.target.feature.properties.GEOID10);
        });

        var algorithmObj = { "state": s, "politicalFairness": w1, "compactness": w2, "populationEquality": w3, "algorithm": a, "strategy": str, "seeds": seeds };
        var myJSON = JSON.stringify(algorithmObj);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/begin/",
            data: myJSON,
            dataType: 'json',
            cache: false,
            success: function (data) {
                alert(data);
            }
        });
        getUpdates();
    } else if (document.getElementById("algorithm").checked && document.getElementById("algorithm").value === 'SIMULATED_ANNEALING') {

    }
}


var toMove;
var movesMade = [];
var finished = false;
var request;

function getUpdates() {
    finished = false;
    politicalReady = false;
    if(!finished) {
        request = $.ajax({
            type: "GET",
            url: "/update/",
            dataType: 'text',
            cache: false,
            success: function (data) {
                if(!finished) {
                    if (data === "[]") {
                        if(!finished) {
                            getUpdates();
                        }
                    } else {
                        toMove = JSON.parse(data);
                        movesMade.push(toMove);

                        geojson.getLayers().forEach(function (e) {
                            // toMove.forEach(function (arrayItem) {

                            if (e.feature.properties.GEOID10 === toMove['precinctID']) {
                                e.setStyle({
                                    weight: weights,
                                    color: 'white',
                                    dashArray: '',
                                    fillOpacity: 1,
                                    fillColor: colorArray[toMove['districtID']]
                                });


                                e.bringToFront();
                            }

                            var x = toMove['precinctID'];
                            if (x === "population") {
                                finished = true;
                                request.abort();
                                getPopulation();
                            }
                        });


                        if (!finished) {
                            //console.log(toMove);
                            getUpdates();
                        }
                        // if (toMove['precinctID'] === "finished") {
                        //
                        // } else {
                        //     getUpdates();
                        //     console.log(toMove);
                        // }
                    }
                }


            }
        });
    }

}

var politicalReady = false;
function getPopulation() {
    if(!politicalReady) {
        $.ajax({
            type: "GET",
            url: "/updatepop/",
            dataType: 'text',
            cache: false,
            success: function (data) {
                if (!politicalReady) {
                    if (data === "[]") {
                        getPopulation()

                    } else {
                        var populationResult = JSON.parse(data);
                        var x = populationResult['precinctID'];
                        if(x === "political") {
                            politicalReady = true;
                            finished = false;
                        } else {
                            console.log(populationResult);
                            getPopulation();
                        }
                    }
                }

            }
        });
    }

}

function getPolitical() {
    $.ajax({
        type: "GET",
        url: "/update/",
        dataType: 'text',
        cache: false,
        success: function (data) {
            if (data === "[]") {
                getPolitical()
            } else {
                var politicalResult = JSON.parse(data);
                var x = politicalResult['precinctID'];
                if(x === "finished") {

                } else {
                    console.log(politicalResult);
                    getPolitical();
                }
            }
        }
    });
}



function saveWeights() {
    var pf = document.getElementById("weight1").value;
    var cmp = document.getElementById("weight2").value;
    var pe = document.getElementById("weight3").value;
    var weightsObj = {"Political Fairness": w1, "Compactness": w2, "Population Equality": w3};
    var weightsJSON = JSON.stringify(weightsObj);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/save_weights/",
        data: weightsJSON,
        cache: false,
        success: function () {
            alert("success");
        }
    });
}

function loadWeights() {
    $.ajax({
        type: "GET",
        url: "/load_weights/",
        dataType: 'text',
        cache: false,
        success: function (data) {
            console.log(data);
        }
    });
}