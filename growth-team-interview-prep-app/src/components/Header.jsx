import React from 'react'
import { withRouter } from 'react-router'
import { Link } from 'react-router-dom'
import AuthenticationService from "../AuthenticationService";

const Header = withRouter(() => {
    const isLoggedIn = AuthenticationService.isLoggedIn()
    console.log(isLoggedIn)
  
    return (
      <header>
        <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
          <div><a href='https://www.linkedin.com/in/marcus-seungho-lee' className='navbar-brand'>ToDo...OrDie</a></div>
          <ul className='navbar-nav'>
            {isLoggedIn && <li><Link to='/welcome/M' className='nav-link'>Home</Link></li>}
            {isLoggedIn && <li><Link to='/todos' className='nav-link'>ToDos</Link></li>}
          </ul>
          <ul className='navbar-nav  navbar-collapse justify-content-end'>
            {!isLoggedIn && <li><Link to='/login' className='nav-link'>Login</Link></li>}
            {isLoggedIn && <li><Link to='/logout' className='nav-link' onClick={AuthenticationService.deleteUserToken}>LogOut</Link></li>}
          </ul>
        </nav>
      </header>
    )

})

export default Header