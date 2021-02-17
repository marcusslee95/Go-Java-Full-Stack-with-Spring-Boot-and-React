import React, { Component } from 'react'
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom'
import './bootstrap.css';
import './App.css';
import AuthenticatedRoute from './components/AuthenticatedRoute';
import Login from './components/Login';
import ToDos from './components/ToDos';
import Header from './components/Header';
import Welcome from './components/Welcome';
import DefaultComponentForWhenRouteDidntMatchAnyComponent from './components/DefaultComponentForWhenRouteDidntMatchAnyComponent';
import Footer from './components/Footer';
import LogOut from './components/LogOut';
import ToDo from './components/ToDo';

class App extends Component {

  render(){
    return (
      <div className='App'>
        <Router>
          <Header/>
          <Switch>
            <Route exact path='/' component={Login}/>
            <Route path='/login' component={Login}/>
            <AuthenticatedRoute path='/welcome/:name' component={Welcome}/>
            <AuthenticatedRoute path='/todos/:id' component={ToDo}/>
            <AuthenticatedRoute path='/todos' component={ToDos}/>
            <AuthenticatedRoute path='/logOut' component={LogOut}/>
            <Route path='' component={DefaultComponentForWhenRouteDidntMatchAnyComponent}/>
          </Switch>
          <Footer/>
        </Router>
      </div>
    );
  }
}



export default App;
