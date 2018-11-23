import axios from 'axios'
 import quizapi from '../appconfig/QuizApi'
 
export const apiClient = function () {
    const token = 'token'
    const params = {
        baseURL: quizapi.base,
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': 'Token ' + token
        }
    }
    return axios.create(params)
}