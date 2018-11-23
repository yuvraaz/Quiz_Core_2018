import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Main from './components/Main';
import Homepage from './components/HomePage';
import TakeAquiz from './components/TakeAquiz';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Quiz from './components/QuizeSample'

class App extends Component {
  render() {
    return (
      <BrowserRouter>
      <div>
                    <Switch>
                            <Route path = "/"  component= { Homepage }  exact/>
                            <Route path = "/Main"  component= {Main }/>
                            <Route path = "/TakeAquiz"  component= {TakeAquiz }/> 
                            <Route path = "/QuizSample"  component= {Quiz }/> 

                    </Switch>      
      </div>
  </BrowserRouter>  
    );
  }
}

export default App;