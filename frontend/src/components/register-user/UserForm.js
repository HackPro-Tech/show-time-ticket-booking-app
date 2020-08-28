import React from "react";
import "./RegisterUser.scss";
import TextInput from "../common/text-field/TextInput";

const UserForm = (props) => {
  return (
    <form className="register-form">
      <TextInput
        type="text"
        name="email"
        placeholder="Email"
        value={props.user.email}
        onChange={props.onChange}
      />
      <TextInput
        type="text"
        name="phoneNo"
        placeholder="Phone No"
        value={props.user.phoneNo}
        onChange={props.onChange}
      />
      <TextInput
        type="password"
        name="password"
        placeholder="Password"
        value={props.user.password}
        onChange={props.onChange}
      />
      <div className="register-btn-group">
        <button className="btn btn-primary register-btn"> Clear </button>
        <button className="btn btn-primary register-btn"> Register </button>
      </div>
    </form>
  );
};

export default UserForm;
