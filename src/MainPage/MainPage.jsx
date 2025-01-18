import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../assets/Logo.png";
import UserIcon from "../assets/profil_icon.jpg"; 
import "./MainPage.css";

function MainPage() {
    const navigate = useNavigate();
    const [menuOpen, setMenuOpen] = useState(false); 

    const toggleMenu = () => {
        setMenuOpen(!menuOpen); 
    };

    return (
        <div className="main-container">
            {/* Logo principal */}
            <div className="main-logo">
                <img src={Logo} alt="Logo" />
            </div>

            {/* Icône utilisateur */}
            <div className="user-menu-icon" onClick={toggleMenu}>
                <img src={UserIcon} alt="User Icon" />
            </div>

            {/* Menu déroulant */}
            {menuOpen && (
                <div className="user-menu">
                    <button onClick={() => navigate("/login")}>Login</button>
                    <button onClick={() => navigate("/register")}>Register</button>
                    <button onClick={() => navigate("/forgot-password")}>Forgot Password</button>
                </div>
            )}

            {/* Titre principal */}
            <h1 className="main-title">WHAT HAPPENED ?</h1>

            {/* Boutons principaux */}
            <button className="button-main" onClick={() => navigate("/lost")}>
                I LOST
            </button>
            <button className="button-main" onClick={() => navigate("/found")}>
                I FOUND
            </button>
        </div>
    );
}

export default MainPage;
