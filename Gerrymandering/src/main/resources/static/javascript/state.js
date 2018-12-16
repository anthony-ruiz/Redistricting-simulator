// control that shows state info on hover
var info = L.control();

info.onAdd = function (map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

info.update = function (props) {
    this._div.innerHTML = '<h4>State Information</h4>' + (props ?
        '<b> State : ' + props.name : 'Hover over a state');
};
info.addTo(map);
// end of state control

function stateHighlightFeature(e) {
    var layer = e.target;

    layer.setStyle({
        weight: 5,
        color: '#666',
        dashArray: '',
        fillOpacity: 0.7
    });

    if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
        layer.bringToFront();
    }

    info.update(layer.feature.properties);
}

// colors each precinct based on the district that they are in
function precinctDistrictColor(d) {
    return colorArray[parseInt(d.Code)];
}

function selectColor(code) {
    currentMode = code;

    if ((map.getZoom() >= 8) || loggedIn) {
        geojson.clearLayers();
        geojson = L.geoJSON(window[currentState], {
            style: precinctStyle,
            onEachFeature: onEachPrecinctFeature
        }).addTo(map);
    }
    else {
        geojson.clearLayers();
        geojson = L.geoJSON(window[currentState + "_districts"], {
            style: districtStyle,
            onEachFeature: onEachDistrictFeature
        }).addTo(map);
    }
}

function stateResetHighlight(e) {
    geojson.resetStyle(e.target);
    info.update();
}

function stateClicked(e) {
    currentState = e.target.feature.properties.name;
    showStateDistricts();
}

function comboBoxClicked() {
    currentState = document.getElementById('states').value;
    showStateDistricts();
}

function showStateDistricts() {
    stateSelected = true;
    if (search(currentState, states)) {
        if (!loggedIn)
            loadDistrictView();
        else
            loadPrecinctView();
    }
    else {
        include(currentState).then(function () {
            if (!loggedIn)
                loadDistrictView();
            else
                loadPrecinctView();
        });
    }
}

function loadDistrictView() {
    geojson.clearLayers();

    geojson = L.geoJSON(window[currentState + "_districts"], {
        style: districtStyle,
        onEachFeature: onEachDistrictFeature
    }).addTo(map);

    map.fitBounds(geojson.getBounds());

    map.removeControl(info);
    districtInfo.addTo(map);
    toggleSideBar();
}

function loadPrecinctView() {
    geojson.clearLayers();

    geojson = L.geoJSON(window[currentState], {
        style: precinctStyle,
        onEachFeature: onEachPrecinctFeature
    }).addTo(map);

    map.fitBounds(geojson.getBounds());

    map.removeControl(info);
    precinctInfo.addTo(map);
    toggleSideBar();
}

function loadRegionGrowingDefault() {
    geojson.clearLayers();

    geojson = L.geoJSON(window[currentState], {
        style: regionGrowingStyle,
        onEachFeature: onEachPrecinctFeature
    }).addTo(map);

    map.fitBounds(geojson.getBounds());

    map.removeControl(info);
    precinctInfo.addTo(map);
}

function onEachStateFeature(feature, layer) {
    layer.on({
        mouseover: stateHighlightFeature,
        mouseout: stateResetHighlight,
        click: stateClicked
    });
}

// populate map with initial state borders
geojson = L.geoJSON(statesData, {
    style: style,
    onEachFeature: onEachStateFeature
}).addTo(map);