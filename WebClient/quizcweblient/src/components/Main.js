import React, { Component } from 'react';
 import quizapi from '../appconfig/QuizApi'
 import { action,observable } from 'mobx'

 
 const setquestion = (e) => {
 
     var mcqChoices =[4];

     const question = e.target.elements.question.value;
     const option1 = e.target.elements.option1.value;
     const option2 = e.target.elements.option2.value;
     const option3 = e.target.elements.option3.value;
     const option4 = e.target.elements.option4.value;
     const valid1 = false;

     
        const mcqOption1={
                'valid':valid1,
                'label':option1	
        }

        const mcqOption2={
            'valid':valid1,
            'label':option2	
        }

        const mcqOption3={
            'valid':valid1,
            'label':option3	
        }

        const mcqOption4={
            'valid':valid1,
            'label':option4
        }
        mcqChoices[0]=mcqOption1;
        mcqChoices[1]=mcqOption2;
        mcqChoices[2]=mcqOption3;
        mcqChoices[3]=mcqOption4;

    //insert reqeust
    let data = {
        'questionLabel':question,
        'mcqChoices':mcqChoices
    }
     createquestion(data);

    }
 

 const createquestion=(data)=>{
            fetch('/quiz-rest-services/rest/questions', {
            method: 'POST',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body:data
        })
 }
   

class Form extends Component {
    state = { 
        question : undefined,
        option1 : undefined,
        option2 : undefined,
        option3 : undefined,
        option4 : undefined,
        selectedOption: 'valid1'
     }
 
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