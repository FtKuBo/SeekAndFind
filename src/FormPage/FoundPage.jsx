import React, { useState } from "react";
import Logo from "../assets/Logo.png";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import "./FormPage.css";

function FoundPage() {
    const [objectType, setObjectType] = useState("");
    const [brand, setBrand] = useState("");
    const [location, setLocation] = useState("");
    const [date, setDate] = useState("");
    const [description, setDescription] = useState("");
    const [image, setImage] = useState(null);
    const navigate = useNavigate();
    

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

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setImage(file);
        }
    };

    const handleSubmit = () => {
        if (!objectType || !brand || !location || !date || !description || !image) {
            Swal.fire({
                title: "Submission Failed",
                text: "Please fill in all fields before submitting.",
                icon: "error",
                confirmButtonText: "OK",
                customClass: {
                    popup: "custom-swal-popup",
                },
            });
            return;
        }

        Swal.fire({
            title: "Form Submitted!",
            html: `
                <p><strong>Object Type:</strong> ${objectType}</p>
                <p><strong>Brand:</strong> ${brand}</p>
                <p><strong>Location:</strong> ${location}</p>
                <p><strong>Date:</strong> ${date}</p>
                <p><strong>Image:</strong> ${image.name}</p>
                <p><strong>Description:</strong> ${description}</p>
            `,
            icon: "success",
            confirmButtonText: "Go Home",
            customClass: {
                popup: "custom-swal-popup",
            },
        }).then(() => {
            navigate("/");
        });
    };

    return (
        <div className="form-container">
            <div className="form-logo">
                <img src={Logo} alt="Logo" />
            </div>
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
            
            {/* Upload Image */}
            <input
                type="file"
                accept="image/*"
                className="input-field"
                onChange={handleImageChange}
            />
            {image && <p className="image-info">Image Selected: {image.name}</p>}
            
            {/* Submit Button */}
            <button className="submit-button" onClick={handleSubmit}>
                Submit
            </button>
        </div>
    );
}

export default FoundPage;
