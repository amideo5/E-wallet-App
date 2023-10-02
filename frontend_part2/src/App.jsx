import './App.css';
import { BrowserRouter as Router,Switch,Route } from 'react-router-dom';
import { Row,Col } from 'react-bootstrap';
import signin from './Components/signin';
import signup from './Components/signup';
import Welcome from './Components/Welcome';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';
import Deposit from './Components/Deposit';
import Transfer from './Components/Transfer';
import Transaction from './Components/Transactions';

function App() {
  return (
    <Router>
      <div className="App">
          <Row>
            <Col>
              <Switch>
                <Route path="/" exact component={signin}/>
                <Route path="/signup" exact component={signup} />
                <Route path="/welcome" exact component={Welcome} />
                <Route path="/deposit" exact component={Deposit} />
                <Route path="/transfer" exact component={Transfer} />
                <Route path="/transactions" exact component={Transaction} />
              </Switch>
            </Col>
          </Row>
      </div>
    </Router> 
  );
}

export default App;
