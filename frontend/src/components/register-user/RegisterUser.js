import React from "react";
import styles from "./RegisterUser.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUserCircle } from "@fortawesome/free-solid-svg-icons";

/**
 * Register New User Component
 */
const RegisterUser = () => {
  // variables

  return (
    <div className="register-body">
      <div className="register-main">
        {/* Top Icon */}
        <div className="avatar">
          <FontAwesomeIcon icon={faUserCircle} color="red" size="3x" />
        </div>

        <h3 className="header">Sign Up</h3>
        <form className="register-form">
          <input type="text" className="form-control" placeholder="Email" />
          <input type="text" className="form-control" placeholder="Phone No" />
          <input
            type="password"
            className="form-control"
            placeholder="Password"
          />
          <div className="register-btn-group">
            <button className="btn btn-primary register-btn"> Clear </button>
            <button className="btn btn-primary register-btn"> Register </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default RegisterUser;
