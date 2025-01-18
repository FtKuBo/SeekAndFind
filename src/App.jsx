import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Login from "./LoginPage/Login";
import Register from "./RegisterPage/Register";
import ForgotPassword from "./ForgotPasswordPage/ForgotPassword";
import MainPage from "./MainPage/MainPage";
import LostPage from "./FormPage/LostPage";
import FoundPage from "./FormPage/FoundPage";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/register" element={<Register />} />
                <Route path="/forgot-password" element={<ForgotPassword />} />
                <Route path="/login" element={<Login />} />
                <Route path="/lost" element={<LostPage />} />
                <Route path="/found" element={<FoundPage />} />
            </Routes>
        </Router>
    );
}

export default App;
