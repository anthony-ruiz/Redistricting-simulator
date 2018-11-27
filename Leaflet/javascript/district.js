// control that shows district info on hover
var districtInfo = L.control();

districtInfo.onAdd = function (map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

districtInfo.update = function (props) {
    this._div.innerHTML = '<h4>District Information</h4>' + (props ?
        '<b> District: ' + props.District + '<br> Representitive: ' + props.Represent + '<br> Party: ' + props.Party : 'Hover over a district');
};
// end of district control

function districtHighlightFeature(e) {
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

    districtInfo.update(layer.feature.properties);
}

function getDistrictColor(d) {
    return d === "Republican" ? '#E91D0E' : '#232066';
}

function districtStyle(feature) {
    return {
        weight: 2,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 1,
        fillColor: currentMode === 0 ? precinctDistrictColor(feature.properties) : getDistrictColor(feature.properties.Party)
    };
}

function districtResetHighlight(e) {
    geojson.resetStyle(e.target);
    districtInfo.update();
}

function districtClicked(e) {
    map.fitBounds(e.target.getBounds());
}

function onEachDistrictFeature(feature, layer) {
    layer.on({
        mouseover: districtHighlightFeature,
        mouseout: districtResetHighlight,
        click: districtClicked
    });
}