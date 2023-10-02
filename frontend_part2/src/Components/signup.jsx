import React from "react";
import "../assets/signup.css";
import "../assets/main.css";
import { useState } from 'react';
import { useForm } from "react-hook-form";
import axios from "axios";
import NavBar from "./NavBar";

const Signup = (props) => { 

  const {reset} = useForm();
  
  const [values, setValues]  = useState({
    name: '',
    password: '',
    mobileNo:'',
    confirmPassword:'',
  })

  const handleChange = (event) => {
    setValues({...values, [event.target.name]: event.target.value});
    
  }
  
  const handleSubmit = (event) => {
    if ((values.password == values.confirmPassword))
    {
      var register = {
        "name": values.name,
        "password": values.password,
        "mobileNo":values.mobileNo
      }
      axios.post("http://localhost:8080/api/user/signup", register)
                  .then(response=>{
                    
                    if (response.data == 'User created')
                    {                    
                      alert("User created")
                      props.history.push({pathname: '/'})
                      reset()
                    }
                  })
                  .catch(error => {
                    console.log(error)
                    //alert(error)
                    if(error.response.status == 400)
                    {
                      alert("User Already Exists")
                    }
                    else{
                      alert(error)
                    }
                });  
    }
    else{
      alert("all bad")
    }
    reset();
    event.preventDefault();
  }

  
  return (
  <div className="page">
    <NavBar/>
    <div className="form-container">
      <div className="form-box-signup">
        <div className="header-form">
          <h4 className="text-primary text-center">
            <i className="fa fa-user-circle" style={{ fontSize: "110px" }}></i>
          </h4>
          <div className="image"></div>
        </div>
        <h2>Sign Up</h2>
        <div className="body-form">
          <form className="m-3" onSubmit={handleSubmit}>
            <div className="input-group mb-3">
              <div className="input-group-prepend">
                <span className="input-group-text">
                  <i class="fa fa-phone fa-2x"></i>
                </span>
              </div>
            
              <input
                type="text"
                className="form-control"
                placeholder="Mobile No."
                onChange={handleChange}
                name="mobileNo"
                pattern="[0-9]+"
                required
                title="Enter a valid Phone"
                value={values.mobileNo}
              />
            </div>
            <div className="input-group mb-3">
              <div className="input-group-prepend">
                <span className="input-group-text">
                  <i class="fa fa-user fa-2x"></i>
                </span>
              </div>
              <input
                type="text"
                className="form-control"
                placeholder="Name"
                name="name"
                required
                value={values.name} 
                onChange={handleChange}
              />
            </div>
            <div className="input-group mb-3">
              <div className="input-group-prepend">
                <span className="input-group-text">
                  <i class="fa fa-lock fa-2x"></i>
                </span>
              </div>
              <input
                type="password"
                className="form-control"
                placeholder="Password"
                onChange={handleChange}
                name="password"
                required
                value={values.password}
                pattern=".{8,}"
                title="Eight or more characters"
              />
            </div>
            <div className="input-group mb-3">
              <div className="input-group-prepend">
                <span className="input-group-text">
                  <i class="fa fa-lock fa-2x"></i>
                </span>
              </div>
              <input
                type="password"
                className="form-control"
                placeholder="Confirm Password"
                onChange={handleChange}
                name="confirmPassword"
                required
                value={values.confirmPassword}
                pattern=".{8,}"
                title="Eight or more characters"
              />
            </div>
            <button type="submit" className="btn btn-secondary btn-block">
              Sign Up
            </button><br /><br />
            <strong>Already a User    <a href= "/">Sign In</a></strong>
          </form>
        </div>
      </div>
    </div>
  </div>
  );
}

export default Signup;