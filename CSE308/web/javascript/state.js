// control that shows state info on hover
var info = L.control();
var currentState = "";

info.onAdd = function (map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

info.update = function (props) {
    this._div.innerHTML = '<h4>State Population Density</h4>' + (props ?
        '<b>' + 'State name: ' + props.name : 'Hover over a state');
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

function stateResetHighlight(e) {
    geojson.resetStyle(e.target);
    info.update();
}

function stateClicked(e) {
    map.fitBounds(e.target.getBounds())

    geojson.clearLayers()
    
    geojson = L.geoJSON(search(e.target.feature.properties.name, states), {
        style: style,
        onEachFeature: onEachPrecinctFeature
    }).addTo(map);
    currentState = e.target.feature.properties.name;

    map.removeControl(info)
    precinctInfo.addTo(map)
    toggleSideBar()
}

function comboBoxClicked() {
    var stateObj = search(document.getElementById('states').value, states)
    currentState = document.getElementById('states').value;
    geojson.clearLayers()

    geojson = L.geoJSON(stateObj, {
        style: style,
        onEachFeature: onEachPrecinctFeature
    }).addTo(map);

    map.fitBounds(geojson.getBounds())

    map.removeControl(info)
    precinctInfo.addTo(map)
    toggleSideBar()
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