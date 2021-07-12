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
        let forecast_url = `http://localhost:8080/travelplanner/forecast?city=${this.state.city}`;
        let create_url = `http://localhost:8080/travelplanner/create`;

        const fetchForecasts = () => {
            axios
                .get(
                    forecast_url
                )
                .then(response => {
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
                            <div className="card-body">
                                <form>
                                    {/*First input field*/}
                                    <div className="input-group mb-3 dynamic-input">
                                        <input type="text" className="form-control" placeholder="Enter Destination"
                                               aria-label="Recipient's username" aria-describedby="basic-addon2" onChange={this.handleInputChange}>
                                        </input>
                                        <div className="input-group-append">
                                            <button className="btn btn-outline-secondary" type="button" onClick={fetchForecasts}>Search</button>
                                        </div>
                                    </div>
                                    {/*Append cloned input fields here*/}
                                    <div className="dynamic-stuff" id={"dynamic-div"}>
                                    </div>
                                    <div>
                                        <button type="button" className="btn btn-link add-one" onClick={this.cloneInput}>+Add
                                            Destination
                                        </button>
                                    </div>
                                    {/*Create Itinerary Button*/}
                                    <div className={"mt-3"}>
                                        {/*Not implemented*/}
                                        <button type="button" className="btn btn-secondary btn-block"> Save Itinerary
                                        </button>
                                    </div>

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
