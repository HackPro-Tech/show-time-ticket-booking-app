import React from "react";
import PropTypes from "prop-types";

// Common Text Input Field
const TextInput = (props) => {
  let wrapperClass = "form-group";
  if (props.error.length > 0) {
    wrapperClass += "has-error";
  }

  return (
    <div className={wrapperClass}>
      <input
        type={props.type}
        className="form-control"
        placeholder={props.placeholder}
        name={props.name}
        value={props.value}
        onChange={props.onChange}
      />
      {props.error && (
        <div className="form-control-feedback">{props.error}</div>
      )}
    </div>
  );
};

TextInput.propTypes = {
  name: PropTypes.string.isRequired,
  placeholder: PropTypes.string.isRequired,
  type: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  value: PropTypes.string,
  error: PropTypes.string,
  errorMessage: PropTypes.string,
};

TextInput.defaultProps = {
  error: "",
};

export default TextInput;
