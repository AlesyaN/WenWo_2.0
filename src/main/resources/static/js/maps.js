var myMap;
ymaps.ready(init);

function init () {

    ymaps.geolocation.get({
        provider: 'yandex',
        mapStateAutoApply: true
    }).then(function (result) {
        setCoordinates(result.geoObjects.get(0).geometry.getCoordinates());
        });

    ymaps.geolocation.get({
        provider: 'browser',
        mapStateAutoApply: true
    }).then(function (result) {
        setCoordinates(result.geoObjects.get(0).geometry.getCoordinates());
    });
}

function setCoordinates(coordinates) {
    document.getElementById("x").value = coordinates[0];
    document.getElementById("y").value = coordinates[1];
}

function showOnMap(event) {
    var x = parseFloat(event.target.dataset.x.replace(',', '.'));
    var y = parseFloat(event.target.dataset.y.replace(',', '.'));
    var id = event.target.dataset.id;
    var mapElem = document.getElementById("map" + id);
    var coords = [x, y];
    var map = new ymaps.Map("map" + id, {
        center: coords,
        zoom: 10
    }, {
        searchControlProvider: 'yandex#search'
    });
    if (mapElem.style.display === "none") {
        mapElem.style.display = "block";

        ymaps.geolocation.get({
            provider: 'yandex',
            mapStateAutoApply: true
        }).then(function (result) {
            result.geoObjects.options.set('preset', 'islands#redCircleIcon');
            result.geoObjects.get(0).properties.set({
                balloonContentBody: 'Мое местоположение'
            });
            map.geoObjects.add(result.geoObjects);
        });

        ymaps.geolocation.get({
            provider: 'browser',
            mapStateAutoApply: true
        }).then(function (result) {
            result.geoObjects.options.set('preset', 'islands#blueCircleIcon');
            map.geoObjects.add(result.geoObjects);
        });

        var placemark = new ymaps.Placemark(coords, {}, {
            preset: 'islands#violetDotIcon'
        });
        map.geoObjects.add(placemark);
        ymaps.geocode(coords).then(function (res) {
            var firstGeoObject = res.geoObjects.get(0);
            placemark.properties
                .set({
                    iconCaption: [
                        firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                        firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                    ].filter(Boolean).join(', '),
                    balloonContent: firstGeoObject.getAddressLine()
                });
        });
    } else {
        map.destroy();
        mapElem.style.display = "none";
    }
}
function showMapInModal(event) {
    var x = parseFloat(event.target.dataset.x.replace(',', '.'));
    var y = parseFloat(event.target.dataset.y.replace(',', '.'));
    var id = event.target.dataset.id;
    var mapElem = document.getElementById("map" + id);
    mapElem.innerHTML = "";
    var coords = [x, y];
    var map = new ymaps.Map("map" + id, {
        center: coords,
        zoom: 10
    }, {
        searchControlProvider: 'yandex#search'
    });
        ymaps.geolocation.get({
            provider: 'yandex',
            mapStateAutoApply: true
        }).then(function (result) {
            result.geoObjects.options.set('preset', 'islands#redCircleIcon');
            result.geoObjects.get(0).properties.set({
                balloonContentBody: 'Мое местоположение'
            });
            map.geoObjects.add(result.geoObjects);
        });

        ymaps.geolocation.get({
            provider: 'browser',
            mapStateAutoApply: true
        }).then(function (result) {
            result.geoObjects.options.set('preset', 'islands#blueCircleIcon');
            map.geoObjects.add(result.geoObjects);
        });

        var placemark = new ymaps.Placemark(coords, {}, {
            preset: 'islands#violetDotIcon'
        });
        map.geoObjects.add(placemark);
        ymaps.geocode(coords).then(function (res) {
            var firstGeoObject = res.geoObjects.get(0);
            placemark.properties
                .set({
                    iconCaption: [
                        firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                        firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                    ].filter(Boolean).join(', '),
                    balloonContent: firstGeoObject.getAddressLine()
                });
        });

}