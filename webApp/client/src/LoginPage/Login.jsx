import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import Logo from "../assets/Logo.png";
import './Login.css'

const backendUrl = import.meta.env.VITE_APP_BACKEND_ADDRESS;

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [userInputted, setUserInputted] = useState(false);
    const [error, setError] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        if (userInputted){
            authenticateUser(email, password)
            .then(data => {
                if (data === "true"){
                    sessionStorage.setItem("isAuthenticated", "true");
                    sessionStorage.setItem("userEmail", email);
                    setUserInputted(false);
                    window.location.reload();
                }
                else {
                    setError("Incorrect Email or Password");
                    setUserInputted(false);
                }
            });
        }
    }, [userInputted])

    const handleLogin = () => {
        if (!email || !password) {
            setError("Both fields are required.");
        } else {
            setError("");
            setUserInputted(true);
        }
    };

    const authenticateUser = async (email, pswd) => {
        return axios.get(`${backendUrl}/userProfiles/auth?email=${email}&password=${pswd}`, { responseType: 'text' })
            .then(response => response.data);
    }

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
                placeholder="Email"
                value={email}
                onChange={(e) => {setEmail(e.target.value); setError("")}}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => {setPassword(e.target.value); setError("")}}
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
