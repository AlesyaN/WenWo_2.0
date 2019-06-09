var myMap;
ymaps.ready(init);

function init () {
    var geolocation = ymaps.geolocation,
        myMap = new ymaps.Map('map', {
            center: [55, 34],
            zoom: 10
        }, {
            searchControlProvider: 'yandex#search'
        });

    geolocation.get({
        provider: 'yandex',
        mapStateAutoApply: true
    }).then(function (result) {
        // Красным цветом пометим положение, вычисленное через ip.
        result.geoObjects.options.set('preset', 'islands#redCircleIcon');
        result.geoObjects.get(0).properties.set({
            balloonContentBody: 'Мое местоположение'
        });
        myMap.geoObjects.add(result.geoObjects);
        setCoordinates(result.geoObjects.get(0).geometry.getCoordinates());
        });

    geolocation.get({
        provider: 'browser',
        mapStateAutoApply: true
    }).then(function (result) {
        // Синим цветом пометим положение, полученное через браузер.
        // Если браузер не поддерживает эту функциональность, метка не будет добавлена на карту.
        result.geoObjects.options.set('preset', 'islands#blueCircleIcon');
        myMap.geoObjects.add(result.geoObjects);
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
        var placemark = new ymaps.Placemark(coords, {}, {
            preset: 'islands#violetDotIcon'
        });
        map.geoObjects.add(placemark);
        ymaps.geocode(coords).then(function (res) {
            var firstGeoObject = res.geoObjects.get(0);

            placemark.properties
                .set({
                    // Формируем строку с данными об объекте.
                    iconCaption: [
                        // Название населенного пункта или вышестоящее административно-территориальное образование.
                        firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                        // Получаем путь до топонима, если метод вернул null, запрашиваем наименование здания.
                        firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                    ].filter(Boolean).join(', '),
                    // В качестве контента балуна задаем строку с адресом объекта.
                    balloonContent: firstGeoObject.getAddressLine()
                });
        });
    } else {
        map.destroy();
        mapElem.style.display = "none";
    }
}