import axios from 'axios'


class ToDosRequestSender {

    //"/users/{username}/todos"
    toGetAllTodosEndpoint(username){
        // return axios.get(`http://localhost:8080/users/${username}/todos`)
        return axios.get(`http://localhost:8080/jpa/users/${username}/todos`)
    }

    //"/users/{username}/todos"
    toGetATodoEndpoint(username, id){
        // return axios.get(`http://localhost:8080/users/${username}/todos/${id}`)
        return axios.get(`http://localhost:8080/jpa/users/${username}/todos/${id}`)
    }


    //"/users/{username}/todos/{id}"
    toDeleteATodoEndpoint(username, id){
        return axios.delete(`http://localhost:8080/users/${username}/todos/${id}`)
    }

    //"/users/{username}/todos/{id}"
    toUpdateATodoEndpoint(username, id, todo){
        return axios.put(`http://localhost:8080/users/${username}/todos/${id}`, todo)
    }

    //"/users/{username}/todos"
    toCreateATodoEndpoint(username, todo){
        return axios.post(`http://localhost:8080/users/${username}/todos`, todo)
    }

}

export default new ToDosRequestSender()