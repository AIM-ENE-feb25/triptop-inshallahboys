export class DrivingAdapter extends TravelService {
    constructor() {
      this.apiKey = import.meta.env.VITE_DRIVING_API_KEY;
    }
  
    async getRoutes(origin, destination) {
      const response = await fetch(
        `https://driving-directions1.p.rapidapi.com/get-directions?origin=${origin}&destination=${destination}&distance_units=km&avoid_routes=tolls%2Cferries&country=nl&language=en`,
        {
          headers: {
            'Content-Type': 'application/json',
            'x-rapidapi-key': this.apiKey,
            'x-rapidapi-host': 'driving-directions1.p.rapidapi.com'
          }
        }
      );
  
      const jsonData = await response.json();
      const data = jsonData.data;
  
      if (!data.best_routes || data.best_routes.length === 0) {
        return [];
      }
  
      const route = data.best_routes[0];
      const now = new Date();
      const durationInMinutes = Math.round(route.duration_seconds / 60);
      const arrivalTime = new Date(now.getTime() + (durationInMinutes * 60 * 1000));
      
      return [{
        origin: data.origin.name,
        destination: data.destination.name,
        departureTime: now.toLocaleTimeString(),
        arrivalTime: arrivalTime.toLocaleTimeString()
      }];
    }
  }