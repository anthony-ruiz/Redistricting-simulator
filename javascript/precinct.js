// control that shows precinct info on hover
var precinctInfo = L.control();

precinctInfo.onAdd = function (map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

precinctInfo.update = function (props) {
    this._div.innerHTML = '<h4>Precinct Information</h4>' + (props ?
        '<b>' + props.NAME10 : 'Hover over a precinct');
};
// end of precinct control

function precinctHighlightFeature(e) {
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

    precinctInfo.update(layer.feature.properties);
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