import { useState, useEffect } from "react";
import "./App.css";
import Login from "./Login";
import Transport from "./Transport";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogout = () => {
    sessionStorage.removeItem("token");
    setIsLoggedIn(false);
  };

  return (
    <div>
      {isLoggedIn ? (
        <>
          <button onClick={handleLogout}>Logout</button>
          <Transport />
        </>
      ) : (
        <Transport />
      )}
    </div>
  );
}

export default App;
