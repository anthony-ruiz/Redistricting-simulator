// To save the style when the user hovers over a precinct to save it
var currentStyle;

// control that shows precinct info on hover
var precinctInfo = L.control();

precinctInfo.onAdd = function () {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

precinctInfo.update = function (props) {
    this._div.innerHTML = '<h4>Precinct Information</h4>' + (props ?
        '<b> Name: ' + props.NAME10
        + '<br> ID: ' + props.GEOID10
        + '<br> District: ' + districtDisplay(props)
        + '<br> Party: ' + (props.PRS08_REP > props.PRS08_DEM ? "Republican" : "Democrat")
        + '<br> Republican Votes: ' + parseInt(props.PRS08_REP)
        + '<br> Democratic Votes: ' + parseInt(props.PRS08_DEM)
        + '<br> Population: ' + parseInt(props.TOTPOP)
        : 'Hover over a precinct');
};
// end of precinct control

// Determin what district each precinct is apart of
function districtDisplay(props) {
    if (currentAlg === 0) {
        return "Unassigned";
    } else if (currentAlg === 2) {
        // for (var i = 0; i < Object.keys(movesMade).length; i++) {
        //     if (props.GEOID10 === movesMade[i]['precinctID']) {
        //         return currentState + ' ' + movesMade[i]['districtID'];
        //     }
        // }
    } else {
        return props.District;
    }
}

function precinctHighlightFeature(e) {
    var layer = e.target;
    currentStyle = layer.options;

    layer.setStyle({
        weight: 4,
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
        weight: weights,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 1,
        fillColor: currentMode === 0 ? precinctDistrictColor(feature.properties) : getPrecinctColor(feature.properties)
    };
}

function regionGrowingStyle(feature) {
    return {
        weight: weights,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 1,
        fillColor: 'gray'
    };
}

function precinctResetHighlight(v) {
    geojson.resetStyle(v.target);

    currentStyle.color = 'white';
    currentStyle.weight = weights;
    currentStyle.fillOpacity = 1;

    v.target.setStyle(currentStyle);

    precinctInfo.update();
}

var customSeeds = [];

function variantSelection(variant) {
    var x = document.getElementById("submit");

    loadRegionGrowingDefault(true);

    if (variant === "SELECT_SEED") {
        customSeeds = [];

        x.style.display = "none";
        document.getElementById('selection').innerHTML = '<a><font color="red">Select ' + districtAmount + ' More</font></a>';
    }
    else if (variant == "REP_SEED") {
        document.getElementById('selection').innerHTML = "";
        x.style.display = "initial";
    }
    else {
        document.getElementById('selection').innerHTML = "";
        x.style.display = "initial";
    }
}

function precinctClicked(e) {
    var x = $("input[name=variant]:checked").val();
    var y = document.getElementById("submit");

    if (x === "SELECT_SEED") {
        // document.getElementById("variant").checked = false;
        var cid = e.target.feature.properties.GEOID10;
        var print = "";
        var skip = false;

        customSeeds.forEach(function (v, index) {
            if (v.target.feature.properties.GEOID10 === cid) {
                geojson.resetStyle(v.target);
                currentStyle = v.target.options;
                customSeeds.splice(index, 1);
                skip = true;
            }
        });

        if (!skip && ((customSeeds.length) < districtAmount)) {
            customSeeds.push(e);
        } else {
            y.style.display = "none";
        }
        print += '<a><font color="red">Select ' + (districtAmount - customSeeds.length) + ' More</font></a>';


        customSeeds.forEach(function (v, i) {
            print += '<p><font color=' + colorArray[i] + '>' + (i + 1) + '. ' + v.target.feature.properties.NAME10 + '</font></p>';
            v.target.setStyle({
                weight: weights,
                color: 'white',
                dashArray: '',
                fillOpacity: 1,
                fillColor: colorArray[i]
            });
        });


        if (customSeeds.length >= districtAmount)
            y.style.display = "initial";


        document.getElementById('selection').innerHTML = print;
    } else {
        map.fitBounds(e.target.getBounds());
    }
}

function onEachPrecinctFeature(feature, layer) {
    layer.on({
        mouseover: precinctHighlightFeature,
        mouseout: precinctResetHighlight,
        click: precinctClicked
    });
}