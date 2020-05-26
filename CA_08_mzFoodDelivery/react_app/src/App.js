import React from "react";
import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from "./pages/home/Home";
import Profile from "./pages/profile/Profile";
import Login from "./pages/login/Login";
import Signup from "./pages/signup/Signup";
import Example from "./pages/Example";
import Restaurant from "./pages/restaurant/Restaurant";
import RestaurantService from "./services/RestaurantService";
import AuthService from "./services/AuthService";

function App() {
  return (
    <Router>
      <Switch>

        <Route path="/about">
          <About />
        </Route>
        <Route path="/dashboard">
          <Dashboard />
        </Route>
        <Route path="/test">
          <Example/>
        </Route>
        <Route exact path="/profile" component={()=>AuthService.getUserToken()?<Profile/>:<Login/>} />
        <Route exact path="/restaurant" component={()=>AuthService.getUserToken()?<Restaurant/>:<Login/>} />
        <Route exact path="/login" component={()=>AuthService.getUserToken()?<Home/>:<Login/>} />
        <Route exact path="/signup" component={()=>AuthService.getUserToken()?<Home/>:<Signup/>} />
        <Route exact path="/" component={()=>AuthService.getUserToken()?<Home/>:<Login/>} />
      </Switch>
    </Router>
  );
}

function About() {
  return (
    <div>
      <h2>About</h2>
    </div>
  );
}

function Dashboard() {
  return (
    <div>
      <h2 onClick={RestaurantService.testGet}>Dashboard</h2>
    </div>
  );
}

export default App;
