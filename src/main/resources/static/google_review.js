let autocomplete;
var map;
var service;

function initMap() {
	
	 var pyrmont = new google.maps.LatLng(-33.8665433,151.1956316);//random place to initialize the google map
	
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
	    else {
			let detailNode = document.getElementById("query-search")
			let reviewNode = document.getElementById('review');
			var nearbyNode = document.getElementById('nearby_places');
			let addressNode = document.getElementById('formatted_address');
			let phoneNumber = document.getElementById('international_phone_number');
			let rating = document.getElementById('rating');
			let webNode = document.getElementById('website');
			let no_website = document.createElement('p');
			let nearbyPlace = document.createElement("div");
			nearbyPlace.innerHTML = '<h5>No Available Reviews</h5>';
			nearbyNode.appendChild(nearbyPlace);
			
			let reviews = document.createElement("div");
			reviews.innerHTML = '<h5>no available reviews</h5>';
			reviewNode.appendChild(reviews);
			
			let details = document.createElement("div");
			details.innerHTML = '<h5>No Details Available</h5>';
			detailNode.appendChild(details);
			
			rating.innerText = 'no available rating';
			phoneNumber.innerText= 'no available phone number';
			addressNode.innerText = 'no availabe address';
			no_website.innerText = 'no website availabe';
			webNode.appendChild(no_website);
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
					console.log(place)
					let addressNode = document.getElementById('formatted_address');
					let phoneNumber = document.getElementById('international_phone_number');
					let rating = document.getElementById('rating');
					let website = document.createElement('a');
					let webNode = document.getElementById('website');
					let no_website = document.createElement('p');
					place.formatted_address ? addressNode.innerText=place.formatted_address : addressNode.innerText = 'no availabe address';
					place.international_phone_number ? phoneNumber.innerText=place.international_phone_number : phoneNumber.innerText= 'no available phone number';
					place.rating ? rating.innerText=place.rating : rating.innerText = 'no available rating';
					
					if(place.website){
						website.href = place.website;
						website.innerText = place.website;
						webNode.appendChild(website);
					} else {
						no_website.innerText = 'no website availabe'
						webNode.appendChild(no_website);
					}
					
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
					} else {
						let noPhotoNode = document.getElementById('no_photos');
						noPhotoNode.innerHTML ='<h5>No available photos</h5>'
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

	let place_types = ['subway_station','supermarket','shopping_mall','tourist_attraction','hospital',
						'police','city_hall','park'];
						
 	map = new google.maps.Map(document.getElementById('map'), {
      center: resultQuery.geometry.location,
      zoom: 15
    });
	service = new google.maps.places.PlacesService(map);
    
    var node = document.getElementById('nearby_places');
    for (let i = 0; i < place_types.length; i++) {
		let request = {
	    	location: resultQuery.geometry.location,
	    	radius: '500',
	    	types : [place_types[i]],
	    	business_status : 'OPERATIONAL'
	    }
		service.nearbySearch(request, function (results, status) {
			if (status == google.maps.places.PlacesServiceStatus.OK) {
				
				if(results.length > 0) {
					for (let i = 0; i < results.length; i++) {
			    	let nearbyPlace = document.createElement("div");
					let noImageString = 'https://st4.depositphotos.com/14953852/24787/v/600/depositphotos_247872612-stock-illustration-no-image-available-icon-vector.jpg'
					let imageString = results[i].photos ? results[i].photos[0].getUrl() : noImageString
					
					nearbyPlace.innerHTML='<div class="card ml-3 mr-3" style="width: 18rem;">' +
						  `<img class="card-img-top" src=${imageString} alt=${i}>` +
						  '<div class="card-body">' +
						    `<h5 class="card-text">${results[i].types[0] == 'subway_station' ? results[i].name + ' MRT Station' : results[i].name}</h5>` + 
						    `<p class="card-text">Type : ${results[i].types[0]}</p>` + 
						     `<p class="card-text">${results[i].vicinity}</p>` +
						  '</div>' +
						'</div>';
					node.appendChild(nearbyPlace);
					}
				} else {
					let nearbyPlace = document.createElement("div");
					nearbyPlace.innerHTML = '<h5>No Available Reviews</h5>'
					node.appendChild(nearbyPlace);
				}
			}
		});
	}	
}




