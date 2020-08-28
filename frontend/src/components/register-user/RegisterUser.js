import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUserCircle } from "@fortawesome/free-solid-svg-icons";
import UserForm from "./UserForm";

/**
 * Register New User Component
 */
const RegisterUser = () => {
  // variables
  const [user, setUser] = useState({
    email: "",
    phoneNo: "",
    password: "",
  });

  // Event Handler
  const handleChange = ({ target }) => {
    setUser({ ...user, [target.name]: target.value });
  };

  return (
    <div className="register-body">
      <div className="register-main">
        {/* Top Icon */}
        <div className="avatar">
          <FontAwesomeIcon icon={faUserCircle} color="red" size="3x" />
        </div>
        <h3 className="header">Sign Up</h3>
        {/* User Form */}
        <UserForm user={user} onChange={handleChange} />
      </div>
    </div>
  );
};

export default RegisterUser;
