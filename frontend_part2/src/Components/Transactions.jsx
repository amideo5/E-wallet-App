import React, { useEffect, useState } from "react";
import NavigationBar from "./NavigationBar";
import '../assets/transactions.css';
import { Card, Table } from "react-bootstrap";
import NavBar from "./NavBar";

import axios from "axios";

const Transaction = (props) => { 
  
  const mobileNo = localStorage.getItem('mobileNo');
  const [transactions, setTransactions] = useState([]);  
  const [balance, setBalance] = useState(0);  

  function getTransactions() {
  axios.get("http://localhost:8080/api/transaction/" + mobileNo)
    .then(response => {
      console.log(response.data)
      setTransactions(response.data)
    })
  }

  function getBalance() {
    axios.get("http://localhost:8080/api/transaction/balance/"+ mobileNo)
    .then(response => {
        setBalance(response.data)
    })
  }

  useEffect(() => {
      getTransactions();
      getBalance();
    },  []);
    

  if (localStorage.getItem('token')!=null)
  {    
    return (
      <div className="Page">
        <NavigationBar/>
        <div><br /></div>
        <div><br /></div>
        <div><br /></div>            
        <Card className={"border border-dark bg-light text-dark paddingTop"}>
          <Card.Header>
            <h1>Transaction Summary</h1>
            <h3>Current Balance is {balance}</h3>
          </Card.Header>
          <Card.Body>
            <Table bordered hover striped variant="dark">
              <thead fixed="top">
              <tr>
                <th>Type</th>
                <th>Amount</th>
                <th>Timestamp</th>
                <th>Description</th>
                <th>Wallet Balance</th>
              </tr>
              </thead>
              <tbody>
                {
                  transactions.length==0?
                    <tr align="center">
                        <td colSpan="6">No transactions</td>
                    </tr>:
                  transactions.map((t)=>(
                      <tr key={t.id} id={t.id}>
                          <td>{t.type}</td>
                          <td>{t.amount}</td>
                          <td>{ new Date(t.timestamp).toLocaleDateString("en-US", {hour:"numeric",minute:"numeric",second:"numeric"})
                          }</td>
                          <td>{t.description}</td>
                          <td>{t.balance}</td>
                      </tr>
                  ))
                }
              </tbody>
            </Table>
          </Card.Body>
        </Card>
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

export default Transaction;