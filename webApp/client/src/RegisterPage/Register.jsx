import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Logo from "../assets/Logo.png";
import './Register.css'

const backendUrl = import.meta.env.VITE_APP_BACKEND_ADDRESS;

function Register() {
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [userInputted, setUserInputted] = useState(false);
    const [error, setError] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
            if (userInputted){
                registerUser(email, username, password)
                .then(data => {
                    console.log(data.email);
                    if (data.email === email){
                        sessionStorage.setItem("isAuthenticated", "true");
                        sessionStorage.setItem("userEmail", email);
                        setUserInputted(false);
                        window.location.reload();
                    }
                    else {
                        setError("Email already used");
                        setUserInputted(false);
                    }
                });
            }
        }, [userInputted])

    const registerUser = async (eml, nme, pswd) => {
        return axios({
            method: 'post',
            url: `${backendUrl}/userProfiles/add`,
            headers: {}, 
            data: {
              email: eml,
              name: nme,
              password: pswd
            }
          }).then(response => response.data);
        }

    const handleRegister = () => {
        if (!email || !username || !password || !confirmPassword ) {
            setError("All fields are required."); 
        } else if (password !== confirmPassword) {
            setError("Passwords do not match.");
        } else {
            setError("");
            setUserInputted(true);
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
                    onClick={() => navigate("/")}
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
                placeholder="Name"
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
            <button onClick={() => navigate("/login")} className="button-login">LOGIN</button>
            <div className="login-footer">© 2025 SeekAndFind Inc. Tous droits réservés.</div>
        </div>
    );
}

export default Register;
