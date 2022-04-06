let map,chosenRegion,data;
let regionPolyArr = [],regionNameArr = [];
let regionNumberArr = ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19"];
let lowPriceArea = ["25","19","17","16","18","22","23","28","26","27"];
let middlePriceArea = ["05","04","08","11","14","12","13","15","20","21"];
let highPriceArea = ["07","06","03","01","02","10","09"];
const polyColorArray = [];
const strokeColor = "#808080";
function initMap() {
    initRegion();
    initDataArray();
    const location = { lat: 1.352083, lng:103.819839 }; // Locations for the Options for map centering
    const singaporeBoundary = {
        north: 1.477636,
        south: 1.199288,
        east: 104.074898,
        west: 103.602797,
    };

    let options =  { // options for map settings
        zoom:11.5,
        center: location,
        zoomControl: true,
        mapTypeControl: false,
        scaleControl: false,
        streetViewControl: false,
        rotateControl: false,
        fullscreenControl: false,
        restriction: {
            latLngBounds: singaporeBoundary,
        },
        mapId: "bacb4ab6bd00e8b5"
    };
    map = new google.maps.Map(document.getElementById("map"),options);
}
async function initDataArray() {
    let setUrl = "https://data.gov.sg/api/action/datastore_search?resource_id=f1765b54-a209-4718-8d38-a39237f502b3&sort=month%20desc&limit=10";
    const response = await fetch(setUrl);
    for( let i = 0 ; i<regionPolyArr.length;i++){
        let regionPoly =setRegionGrid(regionPolyArr[i],regionNameArr[i]);
        mouseHover(regionPoly, regionNameArr[i],polyColorArray[i]);
        mouseClick(regionPoly,regionNameArr[i]);
    }
}
function setRegionGrid(regionPolyArr,regionName){
    let options = {};
    let currentPolyColor;
    if(lowPriceArea.includes(regionName) ){ // 最低
        currentPolyColor = "#99FFFF";
        options = {
            paths: regionPolyArr,
            strokeColor: strokeColor,
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: currentPolyColor,
            fillOpacity: 0.35,
        };
    }
    else if (middlePriceArea.includes(regionName)) { //中等
        currentPolyColor = "#33FF33";
        options = {
            paths: regionPolyArr,
            strokeColor: strokeColor,
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: currentPolyColor,
            fillOpacity: 0.35,
        };
    }
    else{ //最高
        currentPolyColor = "#FF0000";
        options = {
            paths: regionPolyArr,
            strokeColor: strokeColor,
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: currentPolyColor,
            fillOpacity: 0.35,
        };
    }
    polyColorArray.push(currentPolyColor);
    const newRegion = new google.maps.Polygon(options);
    newRegion.setMap(map);
    return newRegion;

}

//Mouse event listener
function mouseHover(getRegionPolygon, regionName,polyColor) {
    let bounds = new google.maps.LatLngBounds();
    for (let i = 0; i < getRegionPolygon.getPath().getLength(); i++) {
        bounds.extend(getRegionPolygon.getPath().getAt(i));
    }
    let infowindow = new google.maps.InfoWindow();
    infowindow.opened = false;
    function mouseHoverFn(evt) {
        getRegionPolygon.setOptions({fillColor:'yellow', strokeColor: "#ffd700"});
        infowindow.setContent(regionName);
        infowindow.setPosition(bounds.getCenter());
        infowindow.open(map);
    }
    google.maps.event.addListener(getRegionPolygon, 'mouseover', mouseHoverFn);
    google.maps.event.addListener(getRegionPolygon, 'mouseout', function(evt) {
        infowindow.close();
        infowindow.opened = false;
        getRegionPolygon.setOptions({fillColor:polyColor, strokeColor:strokeColor});
    });
}
function mouseClick(getRegionPolygon, regionName){
    getRegionPolygon.addListener("click", () => {
        chosenRegion= regionName;
        window.location.href="/privateProperty/?district="+regionName;
    });
}
function initRegion(){
    const angmokioRegion = [
        { lat:1.39859, lng:103.85528 },
        { lat:1.39329, lng:103.85751 },
        { lat:1.3904, lng:103.85786 },
        { lat:1.37832, lng:103.85803},
        { lat:1.36919, lng:103.86143 },
        { lat:1.35569, lng:103.85614},
        { lat:1.36444, lng:103.84687},
        { lat:1.36437, lng:103.84108},
        { lat:1.3677, lng:103.83313},
        { lat:1.36682, lng:103.82749},
        { lat:1.37131, lng:103.82815},
        { lat:1.37371, lng:103.82609},
        { lat:1.37697, lng:103.82644},
        { lat:1.37783, lng:103.82798},
        { lat:1.38022, lng:103.81768},
        { lat:1.38343, lng:103.81926},
        { lat:1.3862, lng:103.81851},
        { lat:1.3871, lng:103.81648},
        { lat:1.39447, lng:103.81923},
        { lat:1.39386, lng:103.83077},
        { lat:1.39676, lng:103.84283},
        { lat: 1.39859, lng:103.85528},
    ];
    regionPolyArr[0] = angmokioRegion;
    regionNameArr[0] = "20";

    const hougangRegion = [
        { lat:1.33344, lng:103.88854 },
        { lat:1.33746, lng:103.89599 },
        { lat:1.34047, lng:103.89772 },
        { lat:1.34492, lng:103.89934},
        { lat:1.34733, lng:103.89839},
        { lat:1.34681, lng:103.89273},
        { lat:1.35891, lng:103.89539},
        { lat:1.35942, lng:103.90062},
        { lat:1.36123, lng:103.9008},
        { lat:1.36045, lng:103.9038},
        { lat:1.37651, lng:103.90991},
        { lat:1.38029, lng:103.90501},
        { lat:1.3796, lng:103.89712},
        { lat:1.37994, lng:103.89137},
        { lat:1.38595, lng:103.88467},
        { lat:1.38801, lng:103.8809},
        { lat:1.38835, lng:103.87699},
        { lat:1.38688, lng:103.87392},
        { lat:1.38439, lng:103.87668},
        { lat:1.37547, lng:103.87762},
        { lat:1.36964, lng:103.87668},
        { lat:1.37899, lng:103.87672},
        { lat:1.37547, lng:103.87762},
        { lat:1.36964, lng:103.87668},
        { lat:1.3624, lng:103.87321},
        { lat:1.35822, lng:103.87419},
        { lat:1.35539, lng:103.87393},
        { lat:1.3526, lng:103.87651},
        { lat:1.35189, lng:103.87802},
        { lat:1.34158, lng:103.88473},
        { lat:1.33344, lng:103.88854},
    ];
    regionPolyArr[1] = hougangRegion;
    regionNameArr[1] = "19";

    const sengkangRegion=[
        { lat:1.3904, lng:103.85786},
        { lat:1.39345, lng:103.85785},
        { lat:1.3977, lng:103.85593},
        { lat:1.4003, lng:103.85704},
        { lat:1.4011, lng:103.88437},
        { lat:1.40138, lng:103.88716},
        { lat:1.40028, lng:103.89319},
        { lat:1.39812, lng:103.90123},
        { lat:1.39297, lng:103.9081},
        { lat:1.38395, lng:103.91487},
        { lat:1.37866, lng:103.91393},
        { lat:1.37651, lng:103.90991},
        { lat:1.38029, lng:103.90501},
        { lat:1.3796, lng:103.89712},
        { lat: 1.37994, lng:103.89137},
        { lat:1.38595, lng:103.88467},
        { lat:1.38801, lng:103.8809},
        { lat:1.38835, lng:103.87699},
        { lat:1.38688, lng:103.87392},
        { lat:1.3904, lng:103.85786},
    ];
    regionPolyArr[2] = sengkangRegion;
    regionNameArr[2] = "28";

    const serangoonRegion =[
        { lat:1.3904, lng:103.85786},
        { lat:1.3911, lng:103.85826},
        { lat:1.38688, lng:103.87392},
        { lat:1.38439, lng:103.87668},
        { lat:1.37899, lng:103.87672},
        { lat:1.37547, lng:103.87762},
        { lat:1.36964, lng:103.87668},
        { lat:1.3624, lng:103.87321},
        { lat:1.35822, lng:103.87419},
        { lat:1.35539, lng:103.87393},
        { lat:1.3526, lng:103.87651},
        { lat:1.35189, lng:103.87802},
        { lat:1.34077, lng:103.8846},
        { lat:1.34432, lng:103.87679},
        { lat:1.34263, lng:103.87025},
        { lat:1.34415, lng:103.86533},
        { lat:1.34295, lng:103.86087},
        { lat:1.35205, lng:103.85726},
        { lat:1.35636, lng:103.85692},
        { lat:1.36919, lng:103.86143},
        { lat:1.37832, lng:103.85803},
        { lat:1.3904, lng:103.85786},
    ];
    regionPolyArr[3] = serangoonRegion;
    regionNameArr[3] = "19";
    const punggolRegion =[
        { lat:1.40138, lng:103.88716},
        { lat:1.41082, lng:103.89677},
        { lat:1.41665, lng:103.89917},
        { lat:1.42009, lng:103.90385},
        { lat:1.42021, lng:103.90683},
        { lat:1.4218, lng:103.91136},
        { lat:1.41959, lng:103.91324},
        { lat:1.41627, lng:103.91417},
        { lat:1.41279, lng:103.91475},
        { lat:1.40825, lng:103.91698},
        { lat:1.40293, lng:103.92441},
        { lat:1.39966, lng:103.92595},
        { lat:1.39537, lng:103.91977},
        { lat:1.38988, lng:103.91548},
        { lat:1.38611, lng:103.91393},
        { lat:1.39297, lng:103.9081},
        { lat:1.39812, lng:103.90123},
        { lat:1.40028, lng:103.89319},
        { lat:1.40138, lng:103.88716},
    ];
    regionPolyArr[4] = punggolRegion;
    regionNameArr[4] = "19";

    const pasirrisRegion =[
        { lat:1.39966, lng:103.92946},
        { lat:1.39485, lng:103.93581},
        { lat:1.38559, lng:103.94526},
        { lat:1.38147, lng:103.95847},
        { lat:1.38387, lng:103.96706},
        { lat:1.38833, lng:103.97306},
        { lat:1.38559, lng:103.97804},
        { lat:1.37889, lng:103.97907},
        { lat:1.37426, lng:103.97495},
        { lat:1.37134, lng:103.97959},
        { lat:1.36293, lng:103.97358},
        { lat:1.35486, lng:103.96806},
        { lat:1.35322, lng:103.9638},
        { lat:1.36038, lng:103.96161},
        { lat:1.36434, lng:103.95646},
        { lat:1.36705, lng:103.94629},
        { lat:1.37581, lng:103.93231},
        { lat:1.38096, lng:103.91875},
        { lat:1.38611, lng:103.91393},
        { lat:1.39331, lng:103.91933},
        { lat:1.39966, lng:103.92946},
    ];
    regionPolyArr[5] = pasirrisRegion;
    regionNameArr[5] = "17";

    const tampinesRegion = [
        { lat:1.37581, lng:103.93231},
        { lat:1.37204, lng:103.92991},
        { lat:1.36037, lng:103.93094},
        { lat:1.35076, lng:103.92561},
        { lat:1.3487, lng:103.92424},
        { lat:1.34551, lng:103.9305},
        { lat:1.33684, lng:103.93874},
        { lat:1.33401, lng:103.94887},
        { lat:1.32681, lng:103.95402},
        { lat:1.32234, lng:103.96002},
        { lat:1.31668, lng:103.96251},
        { lat:1.31419, lng:103.96419},
        { lat:1.31625, lng:103.98307},
        { lat:1.31831, lng:103.98462},
        { lat:1.32226, lng:103.97844},
        { lat:1.33582, lng:103.98376},
        { lat:1.34354, lng:103.96865},
        { lat:1.35298, lng:103.96379},
        { lat:1.3602, lng:103.96146},
        { lat:1.3641, lng:103.95637},
        { lat:1.36699, lng:103.94625},
        { lat:1.37581, lng:103.93231},
    ];
    regionPolyArr[6] = tampinesRegion;
    regionNameArr[6] = "18";

    const bedokRegion = [
        { lat:1.33746, lng:103.89599},
        { lat:1.32818, lng:103.90559},
        { lat:1.31737, lng:103.90499},
        { lat:1.30853, lng:103.9086},
        { lat:1.30552, lng:103.9098},
        { lat:1.30793, lng:103.92018},
        { lat:1.3045, lng:103.92138},
        { lat:1.3117, lng:103.94423},
        { lat:1.31205, lng:103.95075},
        { lat:1.31668, lng:103.96251},
        { lat:1.32234, lng:103.96002},
        { lat:1.32681, lng:103.95402},
        { lat:1.33681, lng:103.93866},
        { lat:1.34551, lng:103.9305},
        { lat:1.3487, lng:103.92424},
        { lat:1.34527, lng:103.91858},
        { lat:1.33977, lng:103.91772},
        { lat:1.342, lng:103.91514},
        { lat:1.34012, lng:103.9136},
        { lat:1.33806, lng:103.90931},
        { lat:1.34047, lng:103.89772},
        { lat:1.33746, lng:103.89599},
    ];
    regionPolyArr[7] = bedokRegion;
    regionNameArr[7] = "16";

    const yishunRegion = [
        { lat:1.44547, lng:103.83675},
        { lat:1.43979, lng:103.84452},
        { lat:1.4343, lng:103.84761},
        { lat:1.42589, lng:103.85723},
        { lat:1.42061, lng:103.86318},
        { lat:1.41091, lng:103.86138},
        { lat:1.40525, lng:103.85503},
        { lat:1.39859, lng:103.85528},
        { lat:1.39646, lng:103.84279},
        { lat:1.39386, lng:103.83077},
        { lat:1.39447, lng:103.81923},
        { lat:1.39603, lng:103.815},
        { lat:1.40084, lng:103.81259},
        { lat:1.4044, lng:103.81},
        { lat:1.41354, lng:103.8059},
        { lat:1.41543, lng:103.8095},
        { lat:1.41337, lng:103.81586},
        { lat:1.41405, lng:103.82289},
        { lat:1.42692, lng:103.82667},
        { lat:1.43541, lng:103.82615},
        { lat:1.43612, lng:103.82783},
        { lat:1.44213, lng:103.83068},
        { lat:1.44547, lng:103.83675},
    ];
    regionPolyArr[8] = yishunRegion;
    regionNameArr[8] = "26";


    const sembawangRegion = [
        { lat:1.46263, lng:103.79233},
        { lat:1.4698, lng:103.80389},
        { lat:1.47095, lng:103.80873},
        { lat:1.47109, lng:103.81797},
        { lat:1.46889, lng:103.82212},
        { lat:1.46666, lng:103.81963},
        { lat:1.46594, lng:103.81925},
        { lat:1.46663, lng:103.82209},
        { lat:1.46494, lng:103.82495},
        { lat:1.46855, lng:103.82761},
        { lat:1.46477, lng:103.83422},
        { lat:1.45928, lng:103.84478},
        { lat:1.45104, lng:103.83276},
        { lat:1.44555, lng:103.83654},
        { lat:1.44213, lng:103.83051},
        { lat:1.43883, lng:103.82947},
        { lat:1.43629, lng:103.82759},
        { lat:1.43544, lng:103.82588},
        { lat:1.43561, lng:103.82262},
        { lat:1.44093, lng:103.81815},
        { lat:1.44453, lng:103.81206},
        { lat:1.44613, lng:103.80742},
        { lat:1.44947, lng:103.80303},
        { lat:1.45671, lng:103.80006},
        { lat:1.45568, lng:103.79723},
        { lat:1.45753, lng:103.79479},
        { lat:1.46263, lng:103.79233},
    ];
    regionPolyArr[9] = sembawangRegion;
    regionNameArr[9] = "27";


    const woodlandsRegion = [
        { lat:1.44423, lng:103.76664},
        { lat:1.43102, lng:103.76904},
        { lat:1.42124, lng:103.77128},
        { lat:1.42682, lng:103.78243},
        { lat:1.4227, lng:103.79531},
        { lat:1.44192, lng:103.81127},
        { lat:1.44453, lng:103.81206},
        { lat:1.44613, lng:103.80742},
        { lat:1.44947, lng:103.80303},
        { lat:1.45671, lng:103.80006},
        { lat:1.45568, lng:103.79723},
        { lat:1.45753, lng:103.79479},
        { lat:1.46263, lng:103.79233},
        { lat:1.44887, lng:103.77059},
        { lat:1.44423, lng:103.76664}
    ];
    regionPolyArr[10] = woodlandsRegion;
    regionNameArr[10] = "25";

    const choachukangRegion = [
        { lat:1.40557, lng:103.74599},
        { lat:1.40154, lng:103.74337},
        { lat:1.39671, lng:103.74267},
        { lat:1.39139, lng:103.74022},
        { lat:1.38994, lng:103.74021},
        { lat:1.38675, lng:103.7416},
        { lat:1.37895, lng:103.73257},
        { lat:1.37483, lng:103.73661},
        { lat:1.3733, lng:103.73988},
        { lat:1.37192, lng:103.74536},
        { lat:1.36986, lng:103.74828},
        { lat:1.37259, lng:103.75136},
        { lat:1.3767, lng:103.7541},
        { lat:1.3798, lng:103.7608},
        { lat:1.38379, lng:103.75914},
        { lat:1.38925, lng:103.75484},
        { lat:1.39472, lng:103.75388},
        { lat:1.39995, lng:103.75564},
        { lat:1.40519, lng:103.75624},
        { lat:1.40515, lng:103.75152},
        { lat:1.40557, lng:103.74599},
    ];
    regionPolyArr[11] = choachukangRegion;
    regionNameArr[11] = "23";

    const bukitpanjangRegion = [
        { lat:1.39071, lng:103.77432},
        { lat:1.37586, lng:103.77707},
        { lat:1.36059, lng:103.78067},
        { lat:1.35381, lng:103.7872},
        { lat:1.34952, lng:103.79123},
        { lat:1.34823, lng:103.7896},
        { lat:1.34549, lng:103.78788},
        { lat:1.34354, lng:103.78447},
        { lat:1.34188, lng:103.77994},
        { lat:1.34218, lng:103.7775},
        { lat:1.34458, lng:103.77501},
        { lat:1.34664, lng:103.77184},
        { lat:1.34925, lng:103.7699},
        { lat:1.35557, lng:103.76891},
        { lat:1.358, lng:103.76715},
        { lat:1.36366, lng:103.76784},
        { lat:1.37087, lng:103.76337},
        { lat:1.3797, lng:103.76105},
        { lat:1.38381, lng:103.75928},
        { lat:1.38706, lng:103.75627},
        { lat:1.38925, lng:103.75484},
        { lat:1.38937, lng:103.76051},
        { lat:1.39114, lng:103.76462},
        { lat:1.39071, lng:103.77432},
    ];
    regionPolyArr[12] = bukitpanjangRegion;
    regionNameArr[12] = "21";

    const bukitbatokRegion = [
        { lat:1.37975, lng:103.76099},
        { lat:1.37087, lng:103.76337},
        { lat:1.36366, lng:103.76784},
        { lat:1.358, lng:103.76715},
        { lat:1.35557, lng:103.76891},
        { lat:1.34925, lng:103.7699},
        { lat:1.34717, lng:103.76703},
        { lat:1.34675, lng:103.76135},
        { lat:1.34579, lng:103.76432},
        { lat:1.34358, lng:103.76595},
        { lat:1.33998, lng:103.76423},
        { lat:1.33537, lng:103.76555},
        { lat:1.33264, lng:103.76509},
        { lat:1.3421, lng:103.74854},
        { lat:1.34478, lng:103.74364},
        { lat:1.34599, lng:103.73728},
        { lat:1.34887, lng:103.73862},
        { lat:1.35454, lng:103.73665},
        { lat:1.35628, lng:103.73674},
        { lat:1.3729, lng:103.75201},
        { lat:1.3767, lng:103.7541},
        { lat:1.37975, lng:103.76099},
    ];
    regionPolyArr[13] =  bukitbatokRegion;
    regionNameArr[13] = "23";

    const bukittimahRegion = [
        { lat:1.34952, lng:103.79123},
        { lat:1.34926, lng:103.79615},
        { lat:1.33956, lng:103.80619},
        { lat:1.33484, lng:103.81177},
        { lat:1.3321, lng:103.81683},
        { lat:1.32848, lng:103.81405},
        { lat:1.323, lng:103.81366},
        { lat:1.31991, lng:103.81134},
        { lat:1.31761, lng:103.80742},
        { lat:1.31497, lng:103.80471},
        { lat:1.31505, lng:103.80458},
        { lat:1.31497, lng:103.80471},
        { lat:1.31095, lng:103.80333},
        { lat:1.31217, lng:103.7996},
        { lat:1.31308, lng:103.79791},
        { lat:1.312, lng:103.79595},
        { lat:1.31387, lng:103.79296},
        { lat:1.31117, lng:103.79112},
        {lat:1.30806, lng:103.79136},
        { lat:1.3084, lng:103.78844},
        { lat:1.31055, lng:103.78447},
        { lat:1.30864, lng:103.78323},
        { lat:1.31267, lng:103.77679},
        { lat:1.3125, lng:103.77245},
        { lat:1.3216, lng:103.7707},
        { lat:1.32452, lng:103.77322},
        { lat:1.32589, lng:103.77563},
        { lat:1.33001, lng:103.77792},
        { lat:1.33305, lng:103.77076},
        { lat:1.33262, lng:103.7693},
        { lat:1.33264, lng:103.76509},
        { lat:1.33537, lng:103.76555},
        { lat:1.33998, lng:103.76423},
        { lat:1.34358, lng:103.76595},
        { lat:1.34579, lng:103.76432},
        { lat:1.34675, lng:103.76135},
        { lat:1.34717, lng:103.76703},
        { lat:1.34925, lng:103.7699},
        { lat:1.34664, lng:103.77184},
        { lat:1.34458, lng:103.77501},
        { lat:1.34218, lng:103.7775},
        { lat:1.34188, lng:103.77994},
        { lat:1.34354, lng:103.78447},
        { lat:1.34549, lng:103.78788},
        { lat:1.34823, lng:103.7896},
        { lat:1.34952, lng:103.79123},
    ];
    regionPolyArr[14] =  bukittimahRegion;
    regionNameArr[14] = "10";

    const clementiRegion = [
        { lat:1.34193, lng:103.74854},
        { lat:1.33567, lng:103.75983},
        { lat:1.33264, lng:103.76509},
        { lat:1.33262, lng:103.7693},
        { lat:1.33305, lng:103.77076},
        { lat:1.33001, lng:103.77792},
        { lat:1.32589, lng:103.77563},
        { lat:1.32452, lng:103.77322},
        { lat:1.3216, lng:103.7707},
        { lat:1.3125, lng:103.77245},
        { lat:1.29526, lng:103.76996},
        { lat:1.29157, lng:103.76795},
        { lat:1.29229, lng:103.76634},
        { lat:1.30127, lng:103.76199},
        { lat:1.29885, lng:103.75824},
        { lat:1.30718, lng:103.75383},
        { lat:1.30984, lng:103.75103},
        { lat:1.31009, lng:103.74794},
        { lat:1.31292, lng:103.74648},
        { lat:1.31764, lng:103.74897},
        { lat:1.32288, lng:103.75198},
        { lat:1.32923, lng:103.74983},
        { lat:1.33146, lng:103.74691},
        { lat:1.33266, lng:103.74743},
        { lat:1.33884, lng:103.74743},
        { lat:1.34193, lng:103.74854},
    ];
    regionPolyArr[15] = clementiRegion;
    regionNameArr[15] = "05"

    const jurongeastRegion = [
        { lat:1.35403, lng:103.7282},
        { lat:1.34599, lng:103.73728},
        { lat:1.34456, lng:103.74362},
        { lat:1.34193, lng:103.74854},
        { lat:1.33884, lng:103.74743},
        { lat:1.33266, lng:103.74743},
        { lat:1.33146, lng:103.74691},
        { lat:1.32923, lng:103.74983},
        { lat:1.32288, lng:103.75198},
        { lat:1.31292, lng:103.74648},
        { lat:1.31009, lng:103.74794},
        { lat:1.30984, lng:103.75103},
        { lat:1.30718, lng:103.75383},
        { lat:1.29885, lng:103.75824},
        { lat:1.29722, lng:103.75593},
        { lat:1.2998, lng:103.72795},
        { lat:1.29928, lng:103.72108},
        { lat:1.29945, lng:103.71181},
        { lat:1.30741, lng:103.71182},
        { lat:1.30803, lng:103.71005},
        { lat:1.31412, lng:103.71043},
        { lat:1.31457, lng:103.71921},
        { lat:1.32349, lng:103.72034},
        { lat:1.32513, lng:103.72605},
        { lat:1.32954, lng:103.72487},
        { lat:1.33615, lng:103.7255},
        { lat:1.3401, lng:103.72327},
        { lat:1.3419, lng:103.72138},
        { lat:1.34507, lng:103.72173},
        { lat:1.34482, lng:103.72816},
        { lat:1.35403, lng:103.7282},
    ];
    regionPolyArr[16] = jurongeastRegion;
    regionNameArr[16] = "22"

    const jurongwestRegion = [
        { lat:1.3625, lng:103.70482},
        { lat:1.36211, lng:103.70855},
        { lat:1.35739, lng:103.71362},
        { lat:1.35628, lng:103.71677},
        { lat:1.35403, lng:103.7282},
        { lat:1.34482, lng:103.72816},
        { lat:1.34507, lng:103.72173},
        { lat:1.3419, lng:103.72138},
        { lat:1.3401, lng:103.72327},
        { lat:1.33615, lng:103.7255},
        { lat:1.32954, lng:103.72487},
        { lat:1.32513, lng:103.72605},
        { lat:1.32354, lng:103.72011},
        { lat:1.32651, lng:103.71954},
        { lat:1.33221, lng:103.72109},
        { lat:1.32809, lng:103.70822},
        { lat:1.32843, lng:103.69672},
        { lat:1.32813, lng:103.67935},
        { lat:1.33036, lng:103.67969},
        { lat:1.33071, lng:103.67489},
        { lat:1.33911, lng:103.67781},
        { lat:1.3398, lng:103.6821},
        { lat:1.34838, lng:103.69274},
        { lat:1.35507, lng:103.69737},
        { lat:1.3625, lng:103.70482},

    ];
    regionPolyArr[17] = jurongwestRegion;
    regionNameArr[17] = "22";

    const bishanRegion = [
        { lat:1.36682, lng:103.82749},
        { lat:1.3677, lng:103.83313},
        { lat:1.36437, lng:103.84108},
        { lat:1.36444, lng:103.84687},
        { lat:1.35569, lng:103.85614},
        { lat:1.35205, lng:103.85726},
        { lat:1.34295, lng:103.86087},
        { lat:1.3434, lng:103.8536},
        { lat:1.34392, lng:103.84476},
        { lat:1.34521, lng:103.84116},
        { lat:1.34309, lng:103.83808},
        { lat:1.34649, lng:103.83832},
        { lat:1.34847, lng:103.83789},
        { lat:1.34761, lng:103.83618},
        { lat:1.34967, lng:103.83395},
        { lat:1.35027, lng:103.83214},
        { lat:1.35327, lng:103.82983},
        { lat:1.3543, lng:103.82674},
        { lat:1.35576, lng:103.82313},
        { lat:1.35936, lng:103.8245},
        { lat:1.35928, lng:103.82828},
        { lat:1.36177, lng:103.82785},
        { lat:1.36682, lng:103.82749},
    ];
    regionPolyArr[18] = bishanRegion;
    regionNameArr[18] = "20";

    const toapayohRegion = [
        { lat:1.34521, lng:103.84116},
        { lat:1.34392, lng:103.84476},
        { lat:1.3434, lng:103.8536},
        { lat:1.34295, lng:103.86087},
        { lat:1.34415, lng:103.86533},
        { lat:1.34263, lng:103.87025},
        { lat:1.34432, lng:103.87679},
        { lat:1.34158, lng:103.88473},
        { lat:1.33344, lng:103.88854},
        { lat:1.33128, lng:103.87909},
        { lat:1.32853, lng:103.86742},
        { lat:1.3311, lng:103.86158},
        { lat:1.32887, lng:103.85506},
        { lat:1.32905, lng:103.83961},
        { lat:1.33471, lng:103.83669},
        { lat:1.34157, lng:103.83549},
        { lat:1.34521, lng:103.84116},
    ];
    regionPolyArr[19] = toapayohRegion;
    regionNameArr[19] = "11";
    const queenstownRegion = [
        { lat:1.28727, lng:103.76583},
        { lat:1.29157, lng:103.76795},
        { lat:1.29526, lng:103.76996},
        { lat:1.30135, lng:103.77032},
        { lat:1.3125, lng:103.77245},
        { lat:1.31267, lng:103.77679},
        { lat:1.30864, lng:103.78323},
        { lat:1.31055, lng:103.78447},
        { lat:1.3084, lng:103.78844},
        { lat:1.30806, lng:103.79136},
        { lat:1.31117, lng:103.79112},
        { lat:1.31387, lng:103.79296},
        { lat:1.312, lng:103.79595},
        { lat:1.30987, lng:103.79553},
        { lat:1.30821, lng:103.79884},
        { lat:1.30796, lng:103.80251},
        { lat:1.30662, lng:103.80327},
        { lat:1.30376, lng:103.80359},
        { lat:1.3004, lng:103.80503},
        { lat:1.29885, lng:103.80681},
        { lat:1.29653, lng:103.80934},
        { lat:1.29658, lng:103.81651},
        { lat:1.29532, lng:103.81621},
        { lat:1.2916, lng:103.81552},
        { lat:1.29229, lng:103.80866},
        { lat:1.2877, lng:103.80458},
        { lat:1.28409, lng:103.80153},
        { lat:1.2831, lng:103.80115},
        { lat:1.27894, lng:103.80321},
        { lat:1.27482, lng:103.80205},
        { lat:1.27276, lng:103.80226},
        { lat:1.26749, lng:103.79902},
        { lat:1.28727, lng:103.76583},
    ];
    regionPolyArr[20] = queenstownRegion;
    regionNameArr[20] = "03";

    const bukitmerahRegion = [
        { lat:1.26749, lng:103.79902},
        { lat:1.27276, lng:103.80226},
        { lat:1.27482, lng:103.80205},
        { lat:1.27894, lng:103.80321},
        { lat:1.2831, lng:103.80115},
        { lat:1.28409, lng:103.80153},
        { lat:1.2877, lng:103.80458},
        { lat:1.29229, lng:103.80866},
        { lat:1.2916, lng:103.81552},
        { lat:1.29532, lng:103.81621},
        { lat:1.29541, lng:103.81672},
        { lat:1.29511, lng:103.81728},
        { lat:1.2954, lng:103.81884},
        { lat:1.29416, lng:103.82097},
        { lat:1.2921, lng:103.82445},
        { lat:1.29253, lng:103.82586},
        { lat:1.29283, lng:103.82857},
        { lat:1.29219, lng:103.83187},
        { lat:1.2924, lng:103.83341},
        { lat:1.2894, lng:103.83492},
        { lat:1.28588, lng:103.83487},
        { lat:1.28352, lng:103.83577},
        { lat:1.28087, lng:103.83946},
        { lat:1.27983, lng:103.83955},
        { lat:1.2752, lng:103.84101},
        { lat:1.27258, lng:103.84217},
        { lat:1.27241, lng:103.84779},
        { lat:1.27383, lng:103.85144},
        { lat:1.26065, lng:103.85213},
        { lat:1.25383, lng:103.84825},
        { lat:1.25413, lng:103.83619},
        { lat:1.25911, lng:103.82275},
        { lat:1.26065, lng:103.805593},
        { lat:1.26749, lng:103.79902},
    ];
    regionPolyArr[21] = bukitmerahRegion;
    regionNameArr[21] = "09";

    const kallangRegion = [
        { lat:1.29418, lng:103.86517},
        { lat:1.29881, lng:103.86526},
        { lat:1.30199, lng:103.8686},
        { lat:1.3043, lng:103.86826},
        { lat:1.30327, lng:103.86697},
        { lat:1.3043, lng:103.86603},
        { lat:1.3015, lng:103.86227},
        { lat:1.30503, lng:103.86012},
        { lat:1.30824, lng:103.85708},
        { lat:1.31064, lng:103.85439},
        { lat:1.31188, lng:103.85537},
        { lat:1.31361, lng:103.85432},
        { lat:1.31197, lng:103.85316},
        { lat:1.30839, lng:103.85077},
        { lat:1.30889, lng:103.85028},
        { lat:1.30674, lng:103.8488},
        { lat:1.31038, lng:103.84444},
        { lat:1.31724, lng:103.84787},
        { lat:1.31961, lng:103.8532},
        { lat:1.32265, lng:103.85269},
        { lat:1.32562, lng:103.84965},
        { lat:1.32647, lng:103.84205},
        { lat:1.32905, lng:103.83961},
        { lat:1.32887, lng:103.85506},
        { lat:1.3311, lng:103.86158},
        { lat:1.32853, lng:103.86742},
        { lat:1.32006, lng:103.87535},
        { lat:1.31607, lng:103.87449},
        { lat:1.31307, lng:103.87492},
        { lat:1.30882, lng:103.87595},
        { lat:1.30805, lng:103.87719},
        { lat:1.30659, lng:103.88144},
        { lat:1.30453, lng:103.88359},
        { lat:1.3011, lng:103.88539},
        { lat:1.29578, lng:103.8853},
        { lat:1.29418, lng:103.86517},
    ];
    regionPolyArr[22] = kallangRegion;
    regionNameArr[22] = "07";

    const geylangRegion = [
        { lat:1.30453, lng:103.88359},
        { lat:1.30659, lng:103.88144},
        { lat:1.30805, lng:103.87719},
        { lat:1.30882, lng:103.87595},
        { lat:1.31307, lng:103.87492},
        { lat:1.31607, lng:103.87449},
        { lat:1.32006, lng:103.87535},
        { lat:1.32853, lng:103.86742},
        { lat:1.33128, lng:103.87909},
        { lat:1.33344, lng:103.88854},
        { lat:1.33746, lng:103.89599},
        { lat:1.32818, lng:103.90559},
        { lat:1.31737, lng:103.90499},
        { lat:1.31332, lng:103.90663},
        { lat:1.30968, lng:103.90153},
        { lat:1.30955, lng:103.89766},
        { lat:1.30963, lng:103.89427},
        { lat:1.30869, lng:103.88972},
        { lat:1.30655, lng:103.89007},
        { lat:1.3056, lng:103.885},
        { lat:1.30453, lng:103.88359},
    ];
    regionPolyArr[23] = geylangRegion;
    regionNameArr[23] = "14";

    const marineparadeRegion = [
        { lat:1.28332, lng:103.88183},
        { lat:1.29333, lng:103.87565},
        { lat:1.29539, lng:103.87736},
        { lat:1.29578, lng:103.8853},
        { lat:1.3011, lng:103.88539},
        { lat:1.30453, lng:103.88359},
        { lat:1.3056, lng:103.885},
        { lat:1.30655, lng:103.89007},
        { lat:1.30869, lng:103.88972},
        { lat:1.30963, lng:103.89427},
        { lat:1.30955, lng:103.89766},
        { lat:1.30968, lng:103.90153},
        { lat:1.31332, lng:103.90663},
        { lat:1.30853, lng:103.9086},
        { lat:1.30552, lng:103.9098},
        { lat:1.30793, lng:103.92018},
        { lat:1.3045, lng:103.92138},
        { lat:1.28332, lng:103.88183},
    ];
    regionPolyArr[24] = marineparadeRegion;
    regionNameArr[24] = "15";
}