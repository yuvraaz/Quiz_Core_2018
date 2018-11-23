import React, { Component } from 'react';
var setquestion = (e) => {
    console.log('i am in getinfo function');
     const question = e.target.elements.question.value;
     const option1 = e.target.elements.option1.value;
     const option2 = e.target.elements.option2.value;
     const option3 = e.target.elements.option3.value;
     const option4 = e.target.elements.option4.value;



  //Display all the entered valus in console
     console.log( question);
     console.log(option1)
     console.log(option2);
     console.log(option3);
     console.log(option4);
   
        e.preventDefault();
   }
   

class Form extends Component {
    state = { 
        question : undefined,
        option1 : undefined,
        option2 : undefined,
        option3 : undefined,
        option4 : undefined
     }
     
    //  setState ({
    //      question : question,
    //      option1 : option1,
    //      option2 : option2,
    //      option3 : option3,
    //      option4 : option4
    //  });
    render() {
        return ( 
            <div className = "container" style={{display: 'flex',  justifyContent:'center', alignItems:'top', height: '100vh'}}>
  
                                 <form  onSubmit = {setquestion}>   
                                        <p>Enter Mcq question</p>
                                        <input type ="text" name="question"></input><br></br>
                                        <label>option 1</label>
                                        <input type ="text" name="option1"></input><br></br>
                                        <label >Option 2</label>
                                        <input type="test" name="option2"></input><br></br>
                                        <label >Option 3</label>
                                        <input type ="text" name="option3"></input><br></br>
                                        <label >Option 4</label>
                                        <input type ="text" name="option4"></input><br></br>

                                        <button className = "btn btn-danger">Save</button>      
                                </form>
                        </div>
         );
    }}

 
export default Form;