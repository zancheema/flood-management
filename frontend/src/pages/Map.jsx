import { GoogleMap, useLoadScript, MarkerF } from "@react-google-maps/api";
import "./Map.css";
import { useEffect } from "react";

function Map({drones}) {
    // const center = drones[0].location;

    useEffect(() => {
        console.log('Map: drones: ' + JSON.stringify(drones))
    }, []);


    const { isLoaded } = useLoadScript({
        googleMapsApiKey: 'AIzaSyBRUjA0eBedMOwY-3q5dYAOj9ZLAAj26Yk',

    });

    if (!isLoaded) return <h1>Loading...</h1>;

    return <GoogleMap 
        zoom={10} 
        center={{lat: 44, lng: -80}} 
        mapContainerClassName="map-container">
            {/* <MarkerF position={{lat: 44, lng: -80}} label={"Drones"} /> */}
            {
                drones.map(drone => (
                    <MarkerF key={drone.droneId} position={drone.location} label={`Drone ${drone.droneId}`} />
                ))
            }
    </GoogleMap>
}

export default Map;