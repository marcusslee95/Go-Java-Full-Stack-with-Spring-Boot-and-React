import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import HelloWorldRequestSender from '../requestSenders/HelloWorldRequestSender.js'

class Welcome extends Component {
  constructor(props){
    super(props)
    this.state = {
      customizedWelcomeMsg: ''
    }
    this.userClickedButton = this.userClickedButton.bind(this)
    this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this)
  }

  render(){
    return <>
      <h1>Welcome!</h1>
      <div className='container'>
        Welcome {this.props.match.params.name}. 
        To view your to-do list <Link to='/todos'>go here.</Link>
      </div>
      <div className='container'>
        Click here to get a customized welcome message:
        <button onClick={this.userClickedButton} className='btn btn-success'>Get Extra Welcome</button>
      </div>
      <div className='container'>
        {this.state.customizedWelcomeMsg}
      </div>
      </>
  }

  userClickedButton(){
    // HelloWorldRequestSender.toHelloWorldEndpoint() //returns a promise aka. "hey this process will eventually complete"
    // .then(response => this.handleSuccessfulResponse(response))

    // HelloWorldRequestSender.toHelloWorldBeanEndpoint()
    // .then(response => this.handleSuccessfulResponse(response))

    HelloWorldRequestSender.toHelloWorldPathVariableEndpoint(this.props.match.params.name)
    .then(response => this.handleSuccessfulResponse(response))
    .catch(error => this.handleErrorResponse(error))
  }

  handleSuccessfulResponse(response){
    console.log(response)
    this.setState({customizedWelcomeMsg: response.data.msg})
  }

  handleErrorResponse(error){
    console.log(error.response)
    this.setState({customizedWelcomeMsg: error.response.data.message})
  }

}

export default Welcome