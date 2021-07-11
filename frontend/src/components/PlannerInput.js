import React from 'react';
import './component_styles.css'
import $ from 'jquery';
import axios from "axios";

class PlannerInput extends React.Component {
    state = {
        city:'',
        country:'',
        data:[],
        loading: false,
        errBool: false,
        err:"",
    }

    constructor(props) {
        super(props);
        this.cloneInput = this.cloneInput.bind(this);
    }

    cloneInput() {
        $('.dynamic-input').first().clone().appendTo('.dynamic-stuff').show();
    }

    handleInputChange = (event) =>{
        this.setState(
            {city: event.target.value}
        );
    }

    render() {
        let URL = `http://localhost:8080/travelplanner/forecast?city=London`;

        const fetchMovies = () => {
            axios
                .get(
                    URL
                )
                .then(response => {
                    // alert(response.data.city);
                    this.setState(

                        {
                            city: response.data.city,
                            country: response.data.country,
                            data: response.data.list,
                            loading:false
                        });
                    this.props.setResult(this.state.city, this.state.data);

                })
                .catch(err => {
                    this.setState({err: err.message, errBool: true, loading: false })
                });
        }

        return (
            <div id="wrapper">
                <div id="sidebar-wrapper" bg="light" className="border-right">
                    <div className="mt-5">
                        {/*<div className="card">*/}
                        {/*    <div className="ml-4 text-center">*/}
                        {/*        <div className="d-flex align-items-center">*/}
                        {/*            <h5>Itinerary Planner</h5>*/}
                        {/*        </div>*/}
                        {/*    </div>*/}
                            <div className="card-body">
                                <form>
                                    {/*First input field*/}
                                    <div className="input-group mb-3 dynamic-input">
                                        <input type="text" className="form-control" placeholder="Enter Destination"
                                               aria-label="Recipient's username" aria-describedby="basic-addon2" onChange={this.handleInputChange}>
                                        </input>
                                        <div className="input-group-append">
                                            <button className="btn btn-outline-secondary" type="button" onClick={fetchMovies}>Search</button>
                                        </div>
                                    </div>
                                    {/*Append cloned input fields here*/}
                                    <div className="dynamic-stuff" id={"dynamic-div"}>
                                    </div>

                                    <button type="button" className="btn btn-link add-one" onClick={this.cloneInput}>+Add
                                        Destination
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            // </div>
        )
    }
}

export default PlannerInput;
