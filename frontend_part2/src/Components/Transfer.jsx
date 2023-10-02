import React from "react";
import "../assets/main.css";
import "../assets/transfer.css";
import { useState } from 'react';
import { useForm } from "react-hook-form";
import axios from "axios";
import NavBar from "./NavBar";
import NavigationBar from "./NavigationBar";

const Transfer = (props) => { 

  const {reset} = useForm();
  const mobileNo = localStorage.getItem('mobileNo');
  const [values, setValues]  = useState({
    lasttransactionamount: '',
    fromMobileNo:mobileNo,
    toMobileNo:'',
  })
  
  console.log('created');
  const handleChange = (event) => {
    setValues({...values, [event.target.name]: event.target.value});
    
  }
  
  const handleSubmit = (event) => {
    if (localStorage.getItem('token')!=null)
        {
            
          if (values.fromMobileNo == values.toMobileNo){
            alert("Error: Same Phone Nos.. Cannot transfer money to same Phone No")
          }
          else{
            axios.post("http://localhost:8080/api/transaction/transfer",values)
                        .then(response=>{  
                            console.log(response)
                            //alert(response.data.description)
                            if (values.fromMobileNo == values.toMobileNo)
                            {
                              alert("Same Phone Nos")
                            }                        
                            else{    
                              alert(response.data)
                            props.history.push({pathname: '/welcome',state: {
                              data: "Successful" ,
                              description: response.data.description,
                              id : response.data.data
                            } })
                          }
                        })
                        .catch(error => {
                            console.log(error)
                            if (values.fromMobileNo == values.toMobileNo)
                            {
                              alert("Same Phone No")
                            }
                            //alert(error)
                            else if(error.response.status == 400)
                            {
                              alert("You cannot transfer wallet with amount less than equal to zero")
                            }
                            else if(error.response.status == 417)
                            {
                              alert("You have insufficient balance in your wallet")
                            }
                            else if(error.response.status == 404)
                            {
                              alert("The account with Phone No: " + values.toMobileNo +  " does not exist")
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
    <div className="form-box-transfer">
      <div className="header-form">
        <h4 className="text-primary text-center">
          
        </h4>
        <div className="image"></div>
      </div>
      <h2>Transfer</h2><br/><br/><br/>
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
              placeholder="To Mobile No"
              onChange={handleChange}
              name="toMobileNo"
              required
              pattern="[0-9]+"
              title="Enter a valid Phone"
              value={values.mobileNo}
            />
          </div>

          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <span className="input-group-text">
                <i class="fa fa-money fa-2x"></i>
              </span>
            </div>
            <input
              type="number"
              className="form-control"
              placeholder="Amount for Transfer"
              onChange={handleChange}
              required
              name="lasttransactionamount"
              value={values.lasttransactionamount}
            />
          </div><br/><br/>
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

export default Transfer;