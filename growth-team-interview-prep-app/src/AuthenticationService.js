
class AuthenticationService {
    sendTokenOverToUser(username, password){
        console.log('login success!')
        sessionStorage.setItem('loggedInUser', username)
    }

    deleteUserToken(){
        sessionStorage.removeItem('loggedInUser')
    }

    isLoggedIn(){
        const userToken = sessionStorage.getItem('loggedInUser')
        if (userToken === null) return false
        else return true
    }

    getTokenOfLoggedInUser(){
        return sessionStorage.getItem('loggedInUser')
    }
}

export default new AuthenticationService()