import React, { Component } from 'react'
import AuthenticationService from "../AuthenticationService";

class Login extends Component {
    constructor(props){
      super(props)
      this.state = {
        username: '',
        password: '',
        // showLoginWorked: false,
        showLoginFailed: false
  
      }
      this.handleChange = this.handleChange.bind(this)
      this.checkIfCredentialsLegit = this.checkIfCredentialsLegit.bind(this)
    }
  
    handleChange(event){ 
      // console.log(event.target.name)
      // console.log(event.target.value) // proof that form elements keep track of their own state - we don't want that we want all state of the UI to be taken care of by the component itself
      this.setState({[event.target.name]: event.target.value})
    }
  
    checkIfCredentialsLegit(event){ 
      // console.log(this.state)
  
      //correct user credentials: m, v
      if (this.state.username === 'M' && this.state.password === 'v'){
        // this.setState({showLoginWorked: true})
        // this.props.history.push('/welcome') // change route to /welcome
        AuthenticationService.sendTokenOverToUser(this.state.username, this.state.password)
        this.props.history.push(`/welcome/${this.state.username}`)
      }
      else
      this.setState({showLoginFailed: true})
    }
  
    render(){
      return (
        <div className='Login'>
          <h1>Login</h1>
          <div className='container'>
            {/* {this.state.showLoginWorked && <ShowLoginSucceeded/>} */}
            {this.state.showLoginFailed && <ShowLoginFailed/>}
            Username: <input type='text' onChange={this.handleChange}
            value={this.state.username}
            name='username'/>
            Password: <input type='text' onChange={this.handleChange}
            value={this.state.password}
            name='password'/> 
            <button onClick={this.checkIfCredentialsLegit} className='btn btn-success'>Login</button>
          </div>
        </div>)
    }
  }
  
  
const ShowLoginFailed = () => {
return <div>Login Failed</div>
}

export default Login