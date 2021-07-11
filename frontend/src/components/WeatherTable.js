import React, { useState } from "react";
import { Table, NavLink } from "react-bootstrap";

const WeatherTable = props => {
    // let keys = ["popularity", "title", "release_date", "overview"];
    let keys = ["temperature", "description"];
    const { city, forecasts } = props;

    return (
        <div>
            {/*<h2> Popular Movies for { new Date(Date.now()).toLocaleDateString()}</h2>*/}
            <div className="card mt-5">
                {/*<h5 className="card-header">Weather Forecast</h5>*/}
                <div className="card-header text-center bg-dark text-white rounded-0">
                    <div className="d-flex align-items-center">
                        <h5>Weather Forecast of {city} </h5>
                    </div>
                </div>
            <Table
                variant="default"
                striped
                bordered
                responsive
                className="card-table table"
            >
                <thead>
                <tr>
                    {keys.map(heading => {
                        return <td key={heading}>{heading}</td>;
                    })}
                </tr>
                </thead>
                <tbody>
                {forecasts.map(movie => {
                    return (
                        <tr key={movie.temperature}>
                            <td>{movie.temperature}</td>
                            <td>{movie.description}</td>
                            {/*<td>{movie.release_date}</td>*/}
                            {/*<td>modal will go here…</td>*/}
                        </tr>
                    );
                })}
                </tbody>
            </Table>
            </div>
        </div>
    );
};

export default WeatherTable;
