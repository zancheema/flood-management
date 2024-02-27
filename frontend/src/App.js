import Login from "./pages/Login";
import "./App.css";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import SignUp from "./pages/SignUp";
import Home from "./pages/Home";
import RescueTeam from "./pages/RescueTeam";
import AuthorityNotification from "./pages/AuthorityNotification";
import AuthoritySendNotification from "./pages/AuthoritySendNotification";
import AuthorityReport from "./pages/AuthorityReport";
import AuthorityLocation from "./pages/AuthorityLocation";
import { useAuth } from "./util/functions";
import { useEffect } from "react";
import RescueReport from "./pages/RescueReport";
import ForgotPassword from "./pages/ForgotPassword";

function App() {
  const [isAuthenticated, setIsAuthenticated] = useAuth();

  useEffect(() => {
    console.log(`isAuthenticated: ${JSON.stringify(isAuthenticated)}`);
  }, [isAuthenticated]);

  return (
    <div className="App">
      <Router>
        {
          isAuthenticated.authenticated ?
          isAuthenticated.role === 'authority'
          ? <Routes>
              <Route path="/authority/notification" element={<AuthorityNotification />} />
              <Route path="/authority/notification/send/:reportId" element={<AuthoritySendNotification />} />
              <Route path="/authority/report/:reportId" element={<AuthorityReport />} />
              <Route path="/authority" element={<Navigate to="/authority/notification" />} />
              <Route path="/authority/location" element={<AuthorityLocation />} />
              <Route path="*" element={<Navigate to="/authority/notification" />} />
          </Routes>
          : <Routes>
              <Route path="/rescue" element={<RescueTeam />} />
              <Route path="/rescue/:rescueNotificationId/report" element={<RescueReport />} />
              <Route path="*" element={<Navigate to="/rescue" replace />} />
            </Routes>
          :
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/forgot-password" element={<ForgotPassword />} />
              <Route path="*" element={<Navigate to={'/'} />} />
            </Routes>
        }
      </Router>
    </div>
  );
}

export default App;
