import { Redirect, Route } from "react-router-dom";
import AuthenticationService from "../AuthenticationService";



const AuthenticatedRoute = (props) => {
    if (AuthenticationService.isLoggedIn()){
        return <Route {...props}/>
    }
    else {
        return <Redirect to='/login'/>
    }
}

export default AuthenticatedRoute