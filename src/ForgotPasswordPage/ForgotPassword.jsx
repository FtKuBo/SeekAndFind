import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../assets/Logo.png";
import './ForgotPassword.css'

function ForgotPassword() {
    const [mail, setEmail] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleResetButton = () => {
        if (!mail) {
            setError("Please enter a valid email address.");
        } else {
            alert(`An email has successfully been sent to the address: ${mail}`);
            navigate("/"); 
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
            <h1 className="login-title">RESET PASSWORD</h1>
            {error && <p className="error-message">{error}</p>}
            <input
                type="text"
                placeholder="Email Address"
                onChange={(e) => setEmail(e.target.value)}
                className="input-email"
            />
            <button className="button-send" onClick={handleResetButton}>
                RESET
            </button>
            <div className="login-footer">© 2024 Sporting Inc. Tous droits réservés.</div>
        </div>
    );
}

export default ForgotPassword;
