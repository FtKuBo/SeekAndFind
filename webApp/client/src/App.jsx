import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Navigate } from "react-router-dom";
import Login from "./LoginPage/Login";
import Register from "./RegisterPage/Register";
import Profile from "./ProfilePage/ProfilePage";
import Requests from "./RequestsPage/Requests";
import MainPage from "./MainPage/MainPage";
import LostPage from "./FormPage/LostPage";
import FoundPage from "./FormPage/FoundPage";

function App() {
        
    function isAuthenticated() {
        return sessionStorage.getItem("isAuthenticated") === "true";
    }

    const ProtectedRoute = ({ element }) => {
        return isAuthenticated() ? element : <Navigate to="/login" />;
    };

    const AuhtenticatedRoute = ({ element }) => {
        return isAuthenticated() ? <Navigate to="/" /> : element;
    };

    return (
        <Router>
            <Routes>
                <Route path="/" element={<ProtectedRoute element={<MainPage />} />} />
                <Route path="/register" element={<AuhtenticatedRoute element={<Register />} />} />
                <Route path="/login" element={<AuhtenticatedRoute element={<Login />} />} />
                <Route path="/profile" element={<ProtectedRoute element={<Profile />} />} />
                <Route path="/requests" element={<ProtectedRoute element={<Requests />} />} />
                <Route path="/lost" element={<ProtectedRoute element={<LostPage />} />} />
                <Route path="/found" element={<ProtectedRoute element={<FoundPage />} />} />
            </Routes>
        </Router>
    );
}

export default App;
