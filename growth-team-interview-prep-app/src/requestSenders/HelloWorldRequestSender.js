import axios from 'axios'

//a class that has a bunch of method that send requests
class HelloWorldRequestSender {

    //"/hello-world"
    toHelloWorldEndpoint(){
        return axios.get('http://localhost:8080/hello-world')
    }

    //"/hello-world-bean"
    toHelloWorldBeanEndpoint(){
        return axios.get('http://localhost:8080/hello-world-bean')
    }

    // "/hello-world/path-parameter/{personName}"
    toHelloWorldPathVariableEndpoint(personName){
        return axios.get(`http://localhost:8080/hello-world/path-parameter/${personName}`)
    }
}

export default new HelloWorldRequestSender()