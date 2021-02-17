import React, { Component } from 'react'
import AuthenticationService from "../AuthenticationService.js"
import ToDosRequestSender from "../requestSenders/ToDosRequestSender.js"
import moment from 'moment'

class ToDos extends Component{
    constructor(props){
      super(props)
      this.state = {
        todos: [],
        messsage: null
      }
      this.getTodosAndChangeUIAfterwards = this.getTodosAndChangeUIAfterwards.bind(this)
      this.deleteToDo = this.deleteToDo.bind(this)
      this.updateToDo = this.updateToDo.bind(this)
      this.createToDo = this.createToDo.bind(this)
    }

    componentDidMount(){
      // console.log(username)
      this.getTodosAndChangeUIAfterwards()
    }

    getTodosAndChangeUIAfterwards(){
      const username = AuthenticationService.getTokenOfLoggedInUser()
      ToDosRequestSender.toGetAllTodosEndpoint(username)
        .then(response => {
          // console.log(response)
          this.setState({todos: response.data})
        })
        .catch(error => console.log(error.response))
    }
  
    deleteToDo(id){
      const username = AuthenticationService.getTokenOfLoggedInUser()
      ToDosRequestSender.toDeleteATodoEndpoint(username, id)
        .then(response => {
          // console.log(response)
          this.setState({messsage: `Delete of todo ${id} Successful`})
          this.getTodosAndChangeUIAfterwards()
        })
        .catch(error => console.log(error.response))
    }

    updateToDo(id){
      this.props.history.push(`/todos/${id}`)
    }

    createToDo(){
      this.props.history.push('/todos/-1') //guarantees todo we send over in request won't match a todo in backend -> guaranteeing a new ToDo will be created
    }

    render(){
      return (
      <div>
        <h1>Todo List</h1>
        {this.state.messsage && <div className='alert alert-success'>{this.state.messsage}</div>}
        <div className='container'>
          <table className='table'>
            <thead>
              <tr>
                <th>description</th>
                <th>isCompleted</th>
                <th>targetDate</th>
                <th>Update</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              {
                this.state.todos.map(
                  (todo) => 
                  <tr key={todo.id}>
                    <td>{todo.description}</td>
                    <td>{todo.completed.toString()}</td>
                    <td>{moment(todo.targetDate).format('YYYY-MM-DD')}</td>
                    <td><button onClick={() => this.updateToDo(todo.id)} className='btn btn-success' >Update</button></td>
                    <td><button onClick={() => this.deleteToDo(todo.id)} className='btn btn-warning' >Delete</button></td>
                  </tr>
                )
              } 
            </tbody>
          </table>
          <div className='row'><button onClick={() => this.createToDo()} className='btn btn-success' >Add a ToDo</button></div>
        </div>
      </div>);
    }
  
}

export default ToDos