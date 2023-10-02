import React, { useEffect, useState } from "react";
import NavigationBar from "./NavigationBar";
import '../assets/welcome.css';
import Deposit from '../assets/deposit.png';
import Transfer from '../assets/transfer.png';
import {Card, Button } from "react-bootstrap";
import NavBar from "./NavBar";
import axios from "axios";

const Welcome = (props) => { 
  
  const mobileNo = localStorage.getItem('mobileNo');
  const [balance, setBalance] = useState(0); 
  const [name, setName] = useState(1); 
  const [amount, setAmount] = useState(2); 
  
  function getBalance() {
    axios.get("http://localhost:8080/api/transaction/balance/"+ mobileNo)
    .then(response => {
        setBalance(response.data)
    })
  } 

  function getAmount() {
    axios.get("http://localhost:8080/api/transaction/lasttranamount/"+ mobileNo)
    .then(response => {
        setAmount(response.data)
    })
  } 

  function getName() {
    axios.get("http://localhost:8080/api/user/name/"+ mobileNo)
    .then(response => {
        setName(response.data)
    })
  }

  useEffect(() => {
    getName();
  }, []);

  useEffect(() => {
    getAmount();
  }, []);


  useEffect(() => {
      getBalance();
  }, []);
  
  const handleDeposit = () => {
    props.history.push({pathname: '/deposit'})
  }


  const handleTransfer = () => {
    props.history.push({pathname: '/transfer'})
  }

  if (localStorage.getItem('token')!=null)
  {    
    return (
      <>
      
      <div className="welcomePage">
      <NavigationBar/>
      <div className="col m-5">
        Welcome {name}

        <br /><br /> Your balance is {balance}<br /><br />
        Your Last Transfer was for {amount}
        </div>
      
      <div className="row m-5">
      <div className="col m-5">
      <Card style={{ width: '18rem' }}>
        <Card.Img className="img" variant="top" src={Deposit} />
        <Card.Body>
          <Card.Title>
            Recharge Wallet
          </Card.Title><br />
          <Button variant="primary" onClick={handleDeposit}>Recharge</Button>
        </Card.Body>
      </Card>
      </div>
      <div className="col m-5">
      <Card style={{ width: '18rem' }}>
        <Card.Img className="img" variant="top" src={Transfer} />
        <Card.Body>
          <Card.Title>
            Transfer Money to other Wallet
          </Card.Title>
          <Button variant="primary"  onClick={handleTransfer}>Transfer</Button>
        </Card.Body>
      </Card>
      </div>
      </div>
      </div>
      </>
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

export default Welcome;