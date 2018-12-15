// control that shows precinct info on hover
var precinctInfo = L.control();

precinctInfo.onAdd = function (map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

precinctInfo.update = function (props) {
    this._div.innerHTML = '<h4>Precinct Information</h4>' + (props ?
        '<b> Name: ' + props.NAME10
        + '<br> ID: ' + props.GEOID10
        + '<br> District: ' + props.District
        + '<br> Party: ' + (props.PRS08_REP > props.PRS08_DEM ? "Republican" : "Democrat")
        + '<br> Republican Votes: ' + parseInt(props.PRS08_REP)
        + '<br> Democratic Votes: ' + parseInt(props.PRS08_DEM)
        + '<br> Population: ' + parseInt(props.TOTPOP)
        : 'Hover over a precinct');
};
// end of precinct control
var currentStyle;

function precinctHighlightFeature(e) {
    var layer = e.target;

    // geojson.getLayers().forEach(function(e) {
    //     az_n.neighbors[layer.feature.properties.GEOID10].forEach(function(j){
    //         if(e.feature.properties.GEOID10 === j) {
    //             e.setStyle({
    //                 weight: 5,
    //                 color: 'red',
    //                 dashArray: '',
    //                 fillOpacity: .1
    //             });
    //             e.bringToFront();
    //         }
    //     });
    // });
    currentStyle = layer.options;


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

function regionGrowingStyle(feature) {
    return {
        weight: 2,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 1,
        fillColor: 'gray'
    };
}

function precinctResetHighlight(v) {
    // geojson.getLayers().forEach(function(e) {
    //     az_n.neighbors[v.target.feature.properties.GEOID10].forEach(function(j){
    //         if(e.feature.properties.GEOID10 === j) {
    //             geojson.resetStyle(e);
    //         }
    //     });
    // });


    geojson.resetStyle(v.target);

    // currentStyle.setStyle({
    //     weight: 2,
    //     color: 'white',
    //     fillOpacity: 1
    // })

    currentStyle.color = 'white';
    currentStyle.weight = 2;
    v.target.setStyle(currentStyle);
    
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