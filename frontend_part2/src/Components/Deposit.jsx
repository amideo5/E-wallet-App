import React from "react";
import "../assets/main.css";
import { useState } from 'react';
import { useForm } from "react-hook-form";
import axios from "axios";
import NavBar from "./NavBar";
import NavigationBar from "./NavigationBar";

const Deposit = (props) => { 

  const {reset} = useForm();
  
  const [values, setValues]  = useState({
    lasttransactionamount: '',
    mobileNo:'',
  })



  const handleChange = (event) => {
    setValues({...values, [event.target.name]: event.target.value});
    
  }
  
  
  const handleSubmit = (event) => {
    
    if(true)
    {
        if (localStorage.getItem('token')!=null)
        {
            var obj = {
                "mobileNo":localStorage.getItem('mobileNo'),
                "lasttransactionamount":values.lasttransactionamount
            }
            //alert(obj)
            axios.post("http://localhost:8080/api/transaction/deposit",obj)
                        .then(response=>{  
                            console.log(response)
                            alert(response.data)
                            props.history.push({pathname: '/welcome',state: {
                              data: "Successful" ,
                              description: response.data.description,
                              id : response.data.data
                            } })
                        })
                        .catch(error => {
                            console.log(error)
                            
                            if(error.response.status == 400)
                            {
                              alert("You cannot recharge wallet with amount less than equal to zero")
                            }
                            else{
                              alert(error)
                            }
                        });
        }
    }    
    reset();
    event.preventDefault();
  }

  if (localStorage.getItem('token')!=null)
  {  
  return (
    <div className="page">
  <NavigationBar/>
  <div className="form-container">
    <div className="form-box">
      <div className="header-form">
        <h4 className="text-primary text-center">
          
        </h4>
        <div className="image"></div>
      </div>
      <h2>Recharge</h2>
      <br/><br/><br/>
      <div className="body-form">
        <form className="m-3" onSubmit={handleSubmit}>
       
          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <span className="input-group-text">
                <i class="fa fa-money fa-2x"></i>
              </span>
            </div>
            <input
              type="number"
              className="form-control"
              placeholder="Amount for deposit"
              onChange={handleChange}
              required
              title="Enter a valid Amount"
              name="lasttransactionamount"
              value={values.lasttransactionamount}
            />
          </div>
          <br/><br/>
          <button type="submit" className="btn btn-secondary btn-block">
            Submit
          </button>
        </form>
      </div>
    </div>
  </div>
  </div>
  );
  }
  if (localStorage.getItem('token') == null)
{
    return (
        <>
        <NavBar/>
        <div className="form-container">
            You have to login to use this application
        </div>
        </>
      );  
}
}



export default Deposit;