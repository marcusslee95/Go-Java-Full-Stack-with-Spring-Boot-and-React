import React, { Component } from 'react'
import { ErrorMessage, Field, Form, Formik } from 'formik'
import moment from 'moment'
import ToDosRequestSender from "../requestSenders/ToDosRequestSender.js"
import AuthenticationService from "../AuthenticationService.js"

class ToDo extends Component{ 
    constructor(props){
        super(props)
        this.state = {
          id: this.props.match.params.id,
          description: '', 
          targetDate: moment(new Date()).format('YYYY-MM-DD')
        }
        this.onSubmit = this.onSubmit.bind(this)
    }

    onSubmit(values){
        // console.log(values)
        const username = AuthenticationService.getTokenOfLoggedInUser()
        const todo = {
            id: this.state.id,
            description: values.description, 
            targetDate: values.targetDate
        }
        if (this.state.id===-1) {//if creating new ToDo hit post endpoint otherwise aka. if updating existing Todo hit put endpoint 
            ToDosRequestSender.toCreateATodoEndpoint(username, todo)
                .then(() => this.props.history.push('/todos'))
        }
        else {
            ToDosRequestSender.toUpdateATodoEndpoint(username, this.state.id, todo)
                .then(() => this.props.history.push('/todos'))
        }
    }
    
    componentDidMount(){// used for populating initial values in form fields
        if (this.state.id===-1) return //no need to try to fetch existing record from backend if know we want to create new ToDo
        
        const username = AuthenticationService.getTokenOfLoggedInUser()
        ToDosRequestSender.toGetATodoEndpoint(username, this.state.id)
            .then(response => 
                // console.log(response)
                this.setState({description: response.data.description, targetDate: moment(response.data.targetDate).format('YYYY-MM-DD')})
            )
    }

    validate(values){
        // console.log(values)
        let errors = {}
        if (!values.description) errors.description='Fill Out Description Field'
        else if (values.description.length < 5) errors.description='Description Should Be Longer Than 4 Letters'

        if (!moment(values.targetDate).isValid()) errors.targetDate='Invalid Target Date'
        console.log(errors)

        return errors
    }    

    render(){
        const { description, targetDate } = this.state;
        return (
            <div>
                <h1>ToDo</h1>
                <div className='container'>
                    <Formik
                    initialValues={{description: description, targetDate: targetDate}}
                    onSubmit={this.onSubmit}
                    validate={this.validate}
                    validateOnChange={false}
                    validateOnBlur={false}
                    enableReinitialize={true}
                    > 
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage
                                    name='description' component='div' className='alert alert-warning'
                                    />
                                    <ErrorMessage
                                    name='targetDate' component='div' className='alert alert-warning'
                                    />
                                    <fieldset className='form-group'>
                                        <label>Description</label>
                                        <Field className='form-control' type='text' name='description'/>
                                    </fieldset>
                                    <fieldset className='form-group'>
                                        <label>Target Date</label>
                                        <Field className='form-control' type='date' name='targetDate'/>
                                    </fieldset>
                                    <button className='btn btn-success' type='submit'>Save</button>
                                </Form>
                            )
                        }
                    </Formik>
                </div>
            </div>
            // <div>
            //     ToDo id - {this.props.match.params.id}
            // </div>
        )
    }
}

export default ToDo

// How I read the Code in the Formik element:
// <Formik> -> this is a formik element
// <Form> -> this is a formik form
// fieldset -> this is a normal html element that contains the label and field for a signel input in a form
// Field -> this is a formik field element

/* <Field className='form-control' type='text' name='description'/>
<Field className='form-control' type='date' name='targetDate'/> 
<button className='btn btn-success' type='submit'>Save</button> */