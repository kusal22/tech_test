import React from 'react';
import './component_styles.css'
import $ from 'jquery';
import axios from "axios";

class CreateItineraryForm extends React.Component {
    state = {
        city: '',
        country: '',
        data: [],
        loading: false,
        errBool: false,
        err: "",
    }

    constructor(props) {
        super(props);
        this.cloneInput = this.cloneInput.bind(this);
    }

    cloneInput() {
        $('.dynamic-input').first().clone().appendTo('.dynamic-stuff').show();
    }

    handleInputChange = (event) => {
        this.setState(
            {city: event.target.value}
        );
    }

    render() {
        const API_KEY = '8211056c6040f1cafd2ffb0a9203986e';
        // let URL = `https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}&language=en-US&page=1`;
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
                            loading: false
                        });
                    this.props.setResult(this.state.city, this.state.data);

                })
                .catch(err => {
                    this.setState({err: err.message, errBool: true, loading: false})
                });
        }

        return (
            <div className="container py-3">
                <div className="row">
                    <div className="col-md-12">
                        {/*<h2 className="text-center mb-3">Create Your Travel Itinerary</h2>*/}
                        {/*<hr className="mb-4"/>*/}
                        <div className="row justify-content-center">
                            <div className="col-md-6">
                                <span className="anchor" id="formLogin"></span>
                                <div className="card card-outline-secondary">
                                    <div className="card-header">
                                        <h3 className="mb-0">Login</h3>
                                    </div>
                                    <div className="card-body">
                                        <form autoComplete="off" className="form" id="formLogin" name="formLogin"
                                              role="form">
                                            <div className="form-group">
                                                <label htmlFor="uname1">Username</label>
                                                <input className="form-control" id="uname1" name="uname1"
                                                       required="" type="text"/>
                                            </div>
                                            <div className="form-group">
                                                <label>Password</label>
                                                <input autoComplete="new-password" className="form-control"
                                                       id="pwd1" required="" type="password"/>
                                            </div>
                                            <div className="form-check small">
                                                <label className="form-check-label">
                                                    <input className="form-check-input" type="checkbox"/>
                                                    <span>Remember me on this computer</span>
                                                </label>
                                            </div>
                                            <button className="btn btn-success btn-lg float-right"
                                                    type="button">Login
                                            </button>
                                        </form>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default CreateItineraryForm;
