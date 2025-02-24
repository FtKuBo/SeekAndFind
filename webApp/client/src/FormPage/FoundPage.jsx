import React, { useState, useEffect } from "react";
import Logo from "../assets/Logo.png";
import UserIcon from "../assets/profil_icon.jpg"; 
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import axios from "axios";
import "./FormPage.css";

const backendUrl = import.meta.env.VITE_APP_BACKEND_ADDRESS;

function FoundPage() {
    const [objectType, setObjectType] = useState("");
    const [brand, setBrand] = useState("");
    const [location, setLocation] = useState("");
    const [loading, setLoading] = useState(false);
    const [date, setDate] = useState("");
    const [description, setDescription] = useState("");
    const [userInputted, setUserInputted] = useState(false);
    const navigate = useNavigate();
    const [menuOpen, setMenuOpen] = useState(false);
    const [email, setEmail] = useState(sessionStorage.getItem("userEmail"));

    useEffect(() => {
                if (userInputted){
                    sendFoundRequest(description, objectType, location, date, brand)
                    .then(data => {
                        if (data.objectDescription === description){
                            sendFoundRequestToMatchingSys(description, objectType, location, date, brand)
                            .then(() => {
                                setUserInputted(false);
                                setLoading(false);
                                Swal.fire({
                                    title: "Form Submitted!",
                                    html: `
                                        <p><strong>Object Type:</strong> ${objectType}</p>
                                        <p><strong>Brand:</strong> ${brand}</p>
                                        <p><strong>Location:</strong> ${location}</p>
                                        <p><strong>Date:</strong> ${date}</p>
                                        <p><strong>Description:</strong> ${description}</p>
                                    `,
                                    icon: "success",
                                    confirmButtonText: "Go Home",
                                    customClass: {
                                        popup: "custom-swal-popup",
                                        title: 'custom-title',
                                    },
                                }).then(() => {
                                    navigate("/");
                                });
                            })
                        }
                        else {
                            setUserInputted(false);
                            setLoading(false);
                            Swal.fire({
                                title: "Submission Failed",
                                text: "Something weird hapenned.",
                                icon: "error",
                                confirmButtonText: "Retry",
                                customClass: {
                                    popup: "custom-swal-popup",
                                    title: 'custom-title',
                                },
                            });
                            return;
                        }
                    });
                }
            }, [userInputted])

    const sendFoundRequest = async (descrp, objType, objLoc, dt, objBrand) => {
        return axios({
            method: 'post',
            url: `${backendUrl}/requests/add`,
            headers: {}, 
            data: {
              userEmail: email,
              objectDescription:descrp,
              objectType:objType,
              objectBrand:objBrand,
              objectLocation:objLoc,
              objectDate: dt,
              objectStatus:"found"
            }
          }).then(response => response.data);
        }
    
    const sendFoundRequestToMatchingSys = async (descrp, objType, objLoc, dt, objBrand) => {
        return axios({
            method: 'post',
            url: `${backendUrl}/broker/sendMessage?topic=object/found`,
            headers: {}, 
            data: {
            userEmail: email,
            objectDescription:descrp,
            objectType:objType,
            objectBrand:objBrand,
            objectLocation:objLoc,
            objectDate: dt,
            objectStatus:"found"
            }
        }).then(response => response.data);
    }
    
    const toggleMenu = () => {
        setMenuOpen(!menuOpen); 
    };

    const electronicDevices = ["Laptop", "Phone", "Tablet", "Smartwatch", "Headphones", "Camera"];
    const brands = {
        Laptop: ["Apple", "Dell", "HP", "Lenovo", "Asus", "Microsoft"],
        Phone: ["Apple", "Samsung", "Google", "OnePlus", "Huawei", "Xiaomi"],
        Tablet: ["Apple", "Samsung", "Microsoft", "Lenovo", "Huawei"],
        Smartwatch: ["Apple", "Samsung", "Fitbit", "Garmin", "Huawei"],
        Headphones: ["Sony", "Bose", "Beats", "JBL", "Sennheiser"],
        Camera: ["Canon", "Nikon", "Sony", "Panasonic", "Fujifilm"],
    };
    const uOttawaLocations = [
        "Morisset Library",
        "Tabaret Hall",
        "SITE Building",
        "Colonel By Hall",
        "Hamelin Hall",
        "Desmarais Building",
        "Fauteux Hall",
        "STEM Complex",
    ];

    const handleSubmit = () => {
        if (!objectType || !brand || !location || !date || !description) {
            setUserInputted(false);
            Swal.fire({
                title: "Submission Failed",
                text: "Please fill in all fields before submitting.",
                icon: "error",
                confirmButtonText: "OK",
                customClass: {
                    popup: "custom-swal-popup",
                    title: 'custom-title',
                },
            });
            return;
        }
        setUserInputted(true);
        setLoading(true);
    };

    return (
        <div className="form-container">
            <div className="form-logo">
                <img src={Logo} alt="Logo" onClick={() => navigate("/")}/>
            </div>

            {loading && (
            <div className="loading-overlay">
                <div className="spinner"></div>
            </div>
            )}

            <div className="user-menu-icon" onClick={toggleMenu}>
                <img src={UserIcon} alt="User Icon" />
            </div>

            {menuOpen && (
                <div className="user-menu">
                    <button onClick={() => {sessionStorage.setItem("isAuthenticated", "false"); sessionStorage.setItem("userEmail", ""); window.location.reload()}}>Log out</button>
                    <button onClick={() => navigate("/profile")}>Profile</button>
                </div>
            )}

            <h1 className="form-title">Found an Electronic Object</h1>
            
            {/* Select Object Type */}
            <select
                className="select-field"
                value={objectType}
                onChange={(e) => setObjectType(e.target.value)}
            >
                <option value="">Select Object Type</option>
                {electronicDevices.map((device) => (
                    <option key={device} value={device}>
                        {device}
                    </option>
                ))}
            </select>
            
            {/* Select Brand */}
            {objectType && (
                <select
                    className="select-field"
                    value={brand}
                    onChange={(e) => setBrand(e.target.value)}
                >
                    <option value="">Select Brand</option>
                    {brands[objectType].map((brandOption) => (
                        <option key={brandOption} value={brandOption}>
                            {brandOption}
                        </option>
                    ))}
                </select>
            )}
            
            {/* Select Location */}
            <select
                className="select-field"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
            >
                <option value="">Select Location</option>
                {uOttawaLocations.map((loc) => (
                    <option key={loc} value={loc}>
                        {loc}
                    </option>
                ))}
            </select>
            
            {/* Select Date */}
            <input
                type="date"
                className="input-field"
                value={date}
                onChange={(e) => setDate(e.target.value)}
            />
            
            {/* Description */}
            <textarea
                placeholder="Describe the object"
                className="textarea-field"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
            ></textarea>
            
            {/* Submit Button */}
            <button className="submit-button" onClick={handleSubmit}>
                Submit
            </button>
        </div>
    );
}

export default FoundPage;

