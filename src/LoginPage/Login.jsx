import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../assets/Logo.png";
import './Login.css'

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = () => {
        if (!username || !password) {
            setError("Both fields are required.");
        } else {
            setError("");
            alert("Login successful!");
        }
    };

    return (
        <div className="login-container">
            {}
            <div className="login-logo" onClick={() => navigate("/")} style={{ cursor: "pointer" }}>
                <img src={Logo} alt="Logo" style={{ width: "100%", height: "110%" }} />
            </div>
            <h1 className="login-title">LOGIN</h1>
            {error && <p className="error-message">{error}</p>}
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
            <button onClick={handleLogin} className="button-login">LOGIN</button>
            <button onClick={() => navigate("/register")} className="button-register">
                REGISTER
            </button>
            <div className="login-footer">
                Forgot password? <a href="/forgot-password" style={{ color: "white" }}>Click HERE</a>.
                <br />
                © 2025 SeekAndFind Inc. Tous droits réservés.
            </div>
        </div>
    );
}

export default Login;
