import React, { useState } from "react";
import Logo from "../assets/Logo.png";
import "./FormPage.css"; 

function FoundPage() {
    const [address, setAddress] = useState("");
    const [date, setDate] = useState("");
    const [objectType, setObjectType] = useState("");
    const [specifications, setSpecifications] = useState("");
    const [description, setDescription] = useState("");
    const [image, setImage] = useState(null);

    const handleSubmit = () => {
        if (image) {
            console.log("Image uploaded:", image.name);
        }
        alert("Form submitted!");
    };

    const handleImageChange = (e) => {
        const file = e.target.files[0]; 
        if (file) {
            setImage(file);
        }
    };

    return (
        <div className="form-container">
            <div className="form-logo">
                <img src={Logo} alt="Logo" />
            </div>
            <h1 className="form-title">Found an Object</h1>
            
            <input
                type="text"
                placeholder="Enter Location"
                className="input-field"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
            />
            
            <input
                type="date"
                className="input-field"
                value={date}
                onChange={(e) => setDate(e.target.value)}
            />
            
            <select
                className="select-field"
                value={objectType}
                onChange={(e) => setObjectType(e.target.value)}
            >
                <option value="">Select Object Type</option>
                <option value="Phone">Phone</option>
                <option value="Wallet">Wallet</option>
                <option value="Keys">Keys</option>
                <option value="Bag">Bag</option>
                <option value="Jewelry">Jewelry</option>
                <option value="Other">Other</option>
            </select>
            
            <input
                type="text"
                placeholder="Enter Object Specifications (e.g., Color, Size)"
                className="input-field"
                value={specifications}
                onChange={(e) => setSpecifications(e.target.value)}
            />
            
            <textarea
                placeholder="Describe the object"
                className="textarea-field"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
            ></textarea>
            
            {}
            <input
                type="file"
                accept="image/*" 
                className="input-field"
                onChange={handleImageChange}
            />
            
            {image && <p className="image-info">Image Selected: {image.name}</p>}

            <button className="submit-button" onClick={handleSubmit}>
                Submit
            </button>
        </div>
    );
}

export default FoundPage;
