import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../assets/Logo.png";
import './Register.css'

function Register() {
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleRegister = () => {
        if (!email || !username || !password || !confirmPassword) {
            setError("All fields are required.");
        } else if (password !== confirmPassword) {
            setError("Passwords do not match.");
        } else {
            navigate("/"); 
            setError("");
            alert("Registration successful!");
        }
    };

    const handleLogoClick = () => {
        navigate("/");
    };

    return (
        <div className="login-container">
            <div className="login-logo" onClick={handleLogoClick} style={{ cursor: "pointer" }}>
                <img
                    src={Logo}
                    alt="Logo"
                    style={{ width: "100%", height: "110%" }}
                />
            </div>
            <h1 className="login-title">REGISTER</h1>
            {error && <p className="error-message">{error}</p>}
            <input
                type="text"
                placeholder="Email Address"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <input
                type="password"
                placeholder="Confirm Password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
            />
            <button onClick={handleRegister} className="button-register">
                REGISTER
            </button>
            <div className="login-footer">© 2025 SeekAndFind Inc. Tous droits réservés.</div>
        </div>
    );
}

export default Register;
