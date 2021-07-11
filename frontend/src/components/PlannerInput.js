import React from 'react';
import './component_styles.css'
import $ from 'jquery';

class PlannerInput extends React.Component {
    constructor(props) {
        super(props);
        this.cloneInput = this.cloneInput.bind(this);
    }

    cloneInput() {
        $('.dynamic-input').first().clone().appendTo('.dynamic-stuff').show();
    }

    render() {
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
                                   aria-label="Recipient's username" aria-describedby="basic-addon2">
                            </input>
                            <div className="input-group-append">
                                <button className="btn btn-outline-secondary" type="button">Search</button>
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
