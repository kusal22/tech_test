import React, {useState} from 'react';
import Navigation from "./components/Navigation";
import WeatherTable from "./components/WeatherTable";
import PlannerInput from "./components/PlannerInput";
import './App.css';
import axios from "axios";

import {
    Row,
    Col,
    Container,
} from "react-bootstrap";

const API_KEY = '8211056c6040f1cafd2ffb0a9203986e';

class App extends React.Component {
    state = { forecasts: [] }

    constructor(props) {
        super(props);
    }

    getResults = (data) => {
        alert(data)
        this.setState({forecasts: data});
        // alert(this.state.forecasts)
    }

    render() {
        return (
            <>
                <Navigation/>
                <Container fluid>
                    <Row>
                        <Col sm={{span: 4}} className="mh-100" style={{overflowY: 'scroll'}}>
                            <PlannerInput setResult={this.getResults}/>
                        </Col>
                        <Col sm={{span: 8}} className="mh-100" style={{overflowY: 'scroll'}}>
                            <WeatherTable forecasts={this.state.forecasts}/>
                        </Col>
                    </Row>
                </Container>
            </>
        )
    }
}

export default App;
