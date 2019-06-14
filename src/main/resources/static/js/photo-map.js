var myMap;

ymaps.ready(function () {
    document.getElementById("photomapbtn").onclick = togglePhotoMap;
}());

function init () {
    var geolocation = ymaps.geolocation;

    geolocation.get({
        provider: 'yandex',
        mapStateAutoApply: true
    }).then(function (result) {
        result.geoObjects.options.set('preset', 'islands#redCircleIcon');
        result.geoObjects.get(0).properties.set({
            balloonContentBody: 'Мое местоположение'
        });
        myMap.geoObjects.add(result.geoObjects);
    });

    geolocation.get({
        provider: 'browser',
        mapStateAutoApply: true
    }).then(function (result) {
        result.geoObjects.options.set('preset', 'islands#blueCircleIcon');
        myMap.geoObjects.add(result.geoObjects);
    });


}

function addPhotosToMap() {
    $.ajax({
      url: "/api/getPhotosWithGPS",
      data: {
          "login": document.getElementById("photomapbtn").dataset.login
      },
      method: "get",
      success(photos) {
          for (var i = 0; i < photos.length; i++) {
              addPhotoToMap(photos[i]);
          }
      }
    })
}

function addPhotoToMap(photo) {
    var coords = [photo.coordinateX, photo.coordinateY];
    var placemark = new ymaps.Placemark(coords, {}, {
        iconLayout: 'default#image',
        iconImageHref: photo.photoPath,
        iconImageSize: [42, 42],
        iconImageOffset: [-5, -38]
    });
    myMap.geoObjects.add(placemark);
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

function togglePhotoMap() {
    var map = document.getElementById("photo-map");
    if (map.style.display === "none") {
        map.style.display = "block";
        myMap = new ymaps.Map('photo-map', {
            center: [55, 34],
            zoom: 10
        }, {
            searchControlProvider: 'yandex#search'
        });
        init();
        addPhotosToMap();
    } else {
        map.style.display = "none";
    }
}