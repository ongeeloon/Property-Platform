let autocomplete;
var map;
var service;

function initMap() {
	 var pyrmont = new google.maps.LatLng(-33.8665433,151.1956316);
	
	 map = new google.maps.Map(document.getElementById('map'), {
	      center: pyrmont,
	      zoom: 15
	 });
	 
	 
	let queryString = document.getElementById("query-search").innerText + " Singapore";
	var request = {
    	query: queryString,
    	fields: ["name", "formatted_address", "geometry", "place_id"],
  	};
  	service = new google.maps.places.PlacesService(map);
    service.findPlaceFromQuery(request, function(result, status) {
	
		if (status === google.maps.places.PlacesServiceStatus.OK) {
			findPlaceDetailFromPlaceId(result[0].place_id);
	      	searchNearbyPlaces(result[0]);
	    }
    });

}

function findPlaceDetailFromPlaceId(place_id) {
		let reviewNode = document.getElementById('review');
        const request = {
            placeId: place_id,
            fields: ["name", "formatted_address", "place_id", "geometry", "rating", "types","website","photos","icon","international_phone_number", "review", "vicinity"],
        };
        service.getDetails(request, (place, status) => {
           if (
              status === google.maps.places.PlacesServiceStatus.OK &&
              place &&
              place.geometry &&
              place.geometry.location
            ) {
					place.formatted_address ? document.getElementById('formatted_address').innerText=place.formatted_address : ''
					place.international_phone_number ? document.getElementById('international_phone_number').innerText=place.international_phone_number : ''
					place.rating ? document.getElementById('rating').innerText=place.rating : ''
					place.website ? document.getElementById('website').innerText=place.website : ''
					if(place.photos){
						let photoElement = document.getElementById('carousel_inner');
						place.photos.map((i, index) => {
							let imageDiv = document.createElement("div");
							index == 0 ? imageDiv.setAttribute("class", "carousel-item active") : imageDiv.setAttribute("class", "carousel-item");
							let image = document.createElement("IMG");
							image.setAttribute("src", i.getUrl());
							image.setAttribute("class", "d-block w-100");
							imageDiv.appendChild(image);
							photoElement.appendChild(imageDiv);
						});
					}
                    if(place.reviews){
                        place.reviews.map(i => {
                            const newNameNode = document.createElement("h5");
                            const newTextNode = document.createElement("p");
                            const nameText = document.createTextNode(i.author_name);
                            const textReview = document.createTextNode(i.text);
                            newNameNode.appendChild(nameText);
                            newTextNode.appendChild(textReview);
                            reviewNode.appendChild(newNameNode);
                            reviewNode.appendChild(newTextNode);
                        });
                    } else {
                         const noReviewNode = document.createElement("h5");
                         const noReviewText = document.createTextNode('No available reviews for this place');
                         noReviewNode.appendChild(noReviewText);
                         reviewNode.appendChild(noReviewNode);
                    }
              };
           });
    };


function searchNearbyPlaces(resultQuery) {

	let place_types = ['university','shopping_mall','subway_station','gym','hospital',
						'supermarket','police','restaurant'];
 	map = new google.maps.Map(document.getElementById('map'), {
      center: resultQuery.geometry.location,
      zoom: 15
    });
	service = new google.maps.places.PlacesService(map);
    
    var node = document.getElementById('nearby_places');
    for (var i = 0; i < place_types.length; i++) {
		var request = {
	    	location: resultQuery.geometry.location,
	    	radius: '300',
	    	types : [place_types[i]],
	    	business_status : 'OPERATIONAL'
	    }
		service.nearbySearch(request, function (results, status) {
			if (status == google.maps.places.PlacesServiceStatus.OK) {
				for (var i = 0; i < results.length; i++) {
			    	var nearbyPlace = document.createElement("div");
		
				nearbyPlace.innerHTML='<div class="card ml-3 mr-3" style="width: 18rem;">' +
					  `<img class="card-img-top" src=${results[i].photos[0].getUrl()} alt=${i}>` +
					  '<div class="card-body">' +
					    `<p class="card-text">${results[i].name}</p>` + '<br/>' +
					    `<p class="card-text">${results[i].types[0]}</p>` + '<br/>' +
					     `<p class="card-text">${results[i].vicinity}</p>` +
					  '</div>' +
					'</div>';
				node.appendChild(nearbyPlace);
			}
		}
		});
	}	
}




