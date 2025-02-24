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
            <div className="main-logo">
                <img src={Logo} alt="Logo" onClick={() => navigate("/")}/>
            </div>

            <div className="user-menu-icon" onClick={toggleMenu}>
                <img src={UserIcon} alt="User Icon" />
            </div>

            {menuOpen && (
                <div className="user-menu">
                    <button onClick={() => {sessionStorage.setItem("isAuthenticated", "false"); sessionStorage.setItem("userEmail", ""); window.location.reload()}}>Log out</button>
                    <button onClick={() => navigate("/profile")}>Profile</button>
                </div>
            )}

            <h1 className="main-title">WHAT HAPPENED ?</h1>
            
            <button className="button-main" onClick={() => navigate("/lost")}>
                I LOST
            </button>
            <button className="button-main" onClick={() => navigate("/found")}>
                I FOUND
            </button>
            <button className="button-main" onClick={() => navigate("/requests")}>
                MY REQUESTS
            </button>
        </div>
    );
}

export default MainPage;
