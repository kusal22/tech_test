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

    // getUsers = () => {
    //     axios
    //         .get("https://reqres.in/api/users?page=1")
    //         .then(data => this.setState({ users: data.data.data }))
    //         .catch(err => {
    //             console.log(err);
    //             return null;
    //         });
    // };

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
                            loading:false
                        });
                    this.props.setResult(this.state.city, this.state.data);

                })
                .catch(err => {
                    this.setState({err: err.message, errBool: true, loading: false })
                });
        }

        return (
            <div className="top-buffer mt-5">
            <div className="card">
                <div className="card-header text-center bg-dark text-white rounded-0">
                    <div className="d-flex align-items-center">
                        <h5 className="mx-auto w-100">Itinerary planner</h5>
                    </div>
                </div>
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
        )
    }
}

export default PlannerInput;
