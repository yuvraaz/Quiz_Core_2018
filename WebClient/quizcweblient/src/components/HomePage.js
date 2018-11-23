import React, { Component } from 'react';
import Main from './Main';
import { NavLink } from 'react-router-dom';

class homepage extends Component {
    state = {  }
    render() { 
        return ( 
            <div>
            
                <h1> Welcome to Quize Application</h1>
                <div>
                <NavLink className = "Navbar-link" to = "/Main">Create a quiz</NavLink>

                </div>

                  <div>
                  <NavLink className = "Navbar-link" to = "/TakeAquiz">Take a quiz</NavLink>

                </div>

                  <div>
                  <NavLink className = "Navbar-link" to = "/QuizeSample"> quiz</NavLink>

                </div>

            </div>
         );
    }
}
 
export default homepage;