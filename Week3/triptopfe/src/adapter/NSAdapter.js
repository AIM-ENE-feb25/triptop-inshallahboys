export class NSAdapter extends TravelService  {
    constructor() {
      this.apiKey = import.meta.env.VITE_NS_API_KEY;
    }
  
    async getRoutes(origin, destination) {
      const response = await fetch(
        `https://gateway.apiportal.ns.nl/reisinformatie-api/api/v3/trips?fromStation=${origin}&toStation=${destination}`,
        {
          headers: {
            'Content-Type': 'application/json',
            'Cache-Control': 'no-cache',
            'Ocp-Apim-Subscription-Key': this.apiKey,
            'Origin': 'http://localhost:5173'
          }
        }
      );
  
      const jsonData = await response.json();
      const trips = jsonData.trips;
  
      if (trips.length === 0) {
        return [];
      }
  
      return trips.map(trip => {
        const leg = trip.legs[0];
        return {
          origin: leg.origin.name,
          destination: leg.destination.name,
          departureTime: leg.origin.plannedDateTime,
          arrivalTime: leg.destination.plannedDateTime
        };
      });
    }
  }