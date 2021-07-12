import React, {useState} from "react";
import {Table, NavLink} from "react-bootstrap";

const WeatherTable = props => {
    let keys = ["Temperature", "Description", "Date"];
    const {city, forecasts} = props;

    return (
        <div>
            <div className="card mt-5">
                <div className="card-header text-center bg-light rounded-0 border-0">
                    <div className="d-flex align-items-center">
                        <h5 className="mx-auto w-100">Weather Forecast of {city} </h5>
                    </div>
                </div>
                <Table
                    variant="default"
                    bordered
                    responsive
                    className="table-sm"
                >
                    <thead>
                    <tr>
                        {keys.map(heading => {
                            return <td key={heading}>{heading}</td>;
                        })}
                    </tr>
                    </thead>
                    <tbody>
                    {forecasts.map(record => {
                        return (
                            <tr key={record.temperature}>
                                <td>{record.temperature}°C</td>
                                <td>{record.description}</td>
                                <td>{record.dt_txt}</td>
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
