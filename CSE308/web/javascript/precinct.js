// control that shows precinct info on hover
var precinctInfo = L.control();

precinctInfo.onAdd = function (map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

precinctInfo.update = function (props) {
    this._div.innerHTML = '<h4>Precinct Information</h4>' + (props ?
        '<b> Name: ' + props.NAME10 + '<br> Party: ' + (props.PRS08_REP > props.PRS08_DEM ? "Republican" : "Democrat")
        + '<br> District: ' + props.District : 'Hover over a precinct');
};
// end of precinct control

function precinctHighlightFeature(e) {
    var layer = e.target;

    layer.setStyle({
        weight: 5,
        color: 'green',
        dashArray: '',
        fillOpacity: .9
    });

    if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
        layer.bringToFront();
    }

    precinctInfo.update(layer.feature.properties);
}

// colors each precinct based on the party they are in
function getPrecinctColor(d) {
    return d.PRS08_REP > d.PRS08_DEM ? '#E91D0E' : '#232066';
}

function precinctStyle(feature) {
    return {
        weight: 2,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 1,
        fillColor: currentMode === 0 ? precinctDistrictColor(feature.properties) : getPrecinctColor(feature.properties)
    };
}

function precinctResetHighlight(e) {
    geojson.resetStyle(e.target);
    precinctInfo.update();
}

function precinctClicked(e) {
    map.fitBounds(e.target.getBounds());
}

function onEachPrecinctFeature(feature, layer) {
    layer.on({
        mouseover: precinctHighlightFeature,
        mouseout: precinctResetHighlight,
        click: precinctClicked
    });
}