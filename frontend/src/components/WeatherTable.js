import React, { useState } from "react";
import { Table, NavLink } from "react-bootstrap";

const WeatherTable = props => {
    let keys = ["popularity", "title", "release_date", "overview"];
    const { movies } = props;

    return (
        <div>
            {/*<h2> Popular Movies for { new Date(Date.now()).toLocaleDateString()}</h2>*/}
            <div className="card mt-5">
                {/*<h5 className="card-header">Weather Forecast</h5>*/}
                <div className="card-header text-center bg-dark text-white rounded-0">
                    <div className="d-flex align-items-center">
                        <h5 className="mx-auto w-100">Weather Forecast</h5>
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
                {movies.map(movie => {
                    return (
                        <tr key={movie.id}>
                            <td>{movie.popularity}</td>
                            <td>{movie.title}</td>
                            <td>{movie.release_date}</td>
                            <td>modal will go here…</td>
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
