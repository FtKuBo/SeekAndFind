import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Logo from "../assets/Logo.png";
import UserIcon from "../assets/profil_icon.jpg";
import axios from "axios";
import "./Requests.css";

const backendUrl = import.meta.env.VITE_APP_BACKEND_ADDRESS;

function Requests() {
    const navigate = useNavigate();
    const [email, setEmail] = useState(sessionStorage.getItem("userEmail"));
    const [menuOpen, setMenuOpen] = useState(false); 
    const [requests, setRequests] = useState([]);

    useEffect(() => {
        axios.get(`${backendUrl}/requests/get?email=${email}`)
          .then((response) => response.data)
          .then((data) => setRequests(data))
          .catch((error) => {
            console.log('Error', 'Failed to fetch requests', error);
          });
      }, []);
    
    const toggleMenu = () => {
        setMenuOpen(!menuOpen);
    };

    const handleDelete = async (id, userEmail, objectDescription, objectType, objectBrand, objectLocation, objectStatus, objectDate) => {
        axios.delete(`${backendUrl}/requests/del?id=${id}`)
          .then(() => {
            axios({
                method: 'post',
                url: `${backendUrl}/broker/sendMessage?topic=object/delete`,
                headers: {}, 
                data: {
                  userEmail: userEmail,
                  objectDescription: objectDescription,
                  objectType: objectType,
                  objectBrand: objectBrand,
                  objectLocation: objectLocation,
                  objectStatus: objectStatus,
                  objectDate: objectDate
                }
              }).then(() => window.location.reload());
          })
          .catch((error) => {
            console.log('Error', 'Failed to delete request', error);
          });
    }

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

            <h1 className="main-title">MY REQUESTS</h1>

            <div className="requests-overview">
                {requests.length === 0 ? (
                <p>No requests</p>
                ) : (
                requests.map((request, index) => (
                    <div className="request-card" key={index}>
                        <h3>
                        {request.objectBrand + " " + request.objectType}
                        <button
                            className="delete-icon"
                            onClick={() => handleDelete(request.id, email, request.objectDescription, request.objectType, request.objectBrand, request.objectLocation, request.objectStatus, request.objectDate)}
                        >
                            &times;
                        </button>
                        </h3>
                        <p><strong>Date:</strong> {request.objectDate}</p>
                        <p><strong>Location:</strong> {request.objectLocation} <strong>Status:</strong> {request.objectStatus}</p>
                        <p><strong>Description:</strong> {request.objectDescription}</p>
                    </div>
                ))
                )}
            </div>
        </div>
    );
}

export default Requests;
