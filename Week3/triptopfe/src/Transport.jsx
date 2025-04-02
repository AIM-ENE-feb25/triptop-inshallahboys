import { useState } from "react";
import { NSAdapter } from "../src/adapter/NSAdapter";
import { DrivingAdapter } from "../src/Adapter/DrivingAdapter";


function Transport() {
  const [formData, setFormData] = useState({
    origin: "",
    destination: "",
    transportType: "car",
  });
  const [routes, setRoutes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const NS_API_KEY = import.meta.env.VITE_NS_API_KEY;
  const DRIVING_API_KEY = import.meta.env.VITE_DRIVING_API_KEY;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const getAdapter = (type) => {
    switch (type) {
      case "train":
        return new NSAdapter(NS_API_KEY);
      case "car":
        return new DrivingAdapter(DRIVING_API_KEY);
      default:
        throw new Error("Unsupported transport type");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const adapter = getAdapter(formData.transportType);
      const routeResults = await adapter.getRoutes(
        formData.origin,
        formData.destination
      );

      if (routeResults.length > 0) {
        setRoutes(routeResults);
      } else {
        setRoutes([]);
        setError("No routes found.");
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>Transport Request</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="origin">Origin:</label>
          <input
            type="text"
            id="origin"
            name="origin"
            value={formData.origin}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="destination">Destination:</label>
          <input
            type="text"
            id="destination"
            name="destination"
            value={formData.destination}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label htmlFor="transportType">Transport Type:</label>
          <select
            id="transportType"
            name="transportType"
            value={formData.transportType}
            onChange={handleChange}
            required
          >
            <option value="car">Car</option>
            <option value="train">Train</option>
          </select>
        </div>

        <button type="submit">Find Routes</button>
      </form>

      {loading && <p>Loading routes...</p>}
      {error && <p style={{ color: "red" }}>Error: {error}</p>}

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

export default Transport;
