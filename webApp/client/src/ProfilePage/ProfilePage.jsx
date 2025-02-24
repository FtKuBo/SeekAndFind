import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
import Logo from "../assets/Logo.png";
import './ProfilePage.css'

const backendUrl = import.meta.env.VITE_APP_BACKEND_ADDRESS;

function ProfilePage() {
    const email = sessionStorage.getItem("userEmail");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [userInputted, setUserInputted] = useState(false);
    const [userDeleted, setUserDeleted] = useState(false);
    const [error, setError] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
            if (userInputted){
                EditUser(email, username, password)
                .then(data => {
                    setLoading(false);
                    if (data.email === email){
                        setUserInputted(false);
                        Swal.fire({
                            title: `${username} Your Profile Has Been Updated !`,
                            icon: "success",
                            confirmButtonText: "Go Home",
                            customClass: {
                                popup: "custom-swal-popup",
                                title: 'custom-title',
                                },
                            }).then(() => {
                                navigate("/");
                            });
                    }
                    else {
                        setError("Error! Please retry later.");
                        setUserInputted(false);
                    }
                });
            }

            if (userDeleted){
                DeleteUser(email)
                .then(() =>{
                    setLoading(false);
                    Swal.fire({
                        title:`${username} Your Profile Has Been Deleted :(`,
                        icon: "success",
                        confirmButtonText: "Go Home",
                        customClass: {
                            popup: "custom-swal-popup",
                            title: 'custom-title',
                        },
                    }).then(() => {
                        setUserDeleted(false);
                        sessionStorage.setItem("isAuthenticated", "false"); 
                        sessionStorage.setItem("userEmail", ""); 
                        navigate("/");
                    });
                })
            }
        }, [userInputted, userDeleted])

    const EditUser = async (eml, nme, pswd) => {
        return axios({
            method: 'put',
            url: `${backendUrl}/userProfiles/put`,
            headers: {}, 
            data: {
              email: eml,
              name: nme,
              password: pswd
            }
          }).then(response => response.data);
        }
    
    const DeleteUser = async (eml) => {
        return axios({
            method: 'delete',
            url: `${backendUrl}/userProfiles/del?email=${eml}`,
            headers: {}
          }).then(response => response.data);
        }

    const handleEdit = () => {
        if (!username || !password ) {
            setError("All fields are required.");
            setUserInputted(false);
        } else {
            setError("");
            setUserInputted(true);
            setLoading(true);
        }
    };

    const handleDelete = () => {
        setUserDeleted(true);
        setLoading(true);
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

            {loading && (
            <div className="loading-overlay">
                <div className="spinner"></div>
            </div>
            )}

            <h1 className="login-title">Profile</h1>
            {error && <p className="error-message">{error}</p>}
            <div className="user-email">
                <p>
                    {email}
                </p>
            </div>
            <input
                type="text"
                placeholder="New username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="New Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleEdit} className="button-profile">
                Edit Profile
            </button>
            <button onClick={handleDelete} className="button-profile" style={{ backgroundColor: "red" }}>
                Delete Profile
            </button>
            <div className="login-footer">© 2025 SeekAndFind Inc. Tous droits réservés.</div>
        </div>
    );
}

export default ProfilePage;
