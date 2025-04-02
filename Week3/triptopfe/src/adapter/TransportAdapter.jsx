import { useState } from 'react';
import { NSAdapter } from '../adapters/NSAdapter';
import { DrivingAdapter } from '../adapters/DrivingAdapter';

function TransportAdapter({ origin, destination, transportType }) {
  const [routes, setRoutes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const NS_API_KEY = import.meta.env.VITE_NS_API_KEY;
  const DRIVING_API_KEY = import.meta.env.VITE_DRIVING_API_KEY;

  const getAdapter = (type) => {
    switch (type) {
      case 'train':
        return new NSAdapter(NS_API_KEY);
      case 'car':
        return new DrivingAdapter(DRIVING_API_KEY);
      default:
        throw new Error('Unsupported transport type');
    }
  };

  const fetchRoutes = async () => {
    setLoading(true);
    setError(null);
    
    try {
      const adapter = getAdapter(transportType);
      const routeResults = await adapter.getRoutes(origin, destination);
      
      if (routeResults.length > 0) {
        setRoutes(routeResults);
      } else {
        setRoutes([]);
        setError('No routes found.');
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <button onClick={fetchRoutes}>Find Routes</button>
      
      {loading && <p>Loading routes...</p>}
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}
      
      {routes.length > 0 && (
        <div>
          <h3>Available Routes:</h3>
          <ul>
            {routes.map((route, index) => (
              <li key={index}>
                <p>Origin: {route.origin}</p>
                <p>Destination: {route.destination}</p>
                <p>Departure Time: {route.departureTime}</p>
                <p>Arrival Time: {route.arrivalTime}</p>
                <hr />
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default TransportAdapter;