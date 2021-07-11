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

class App extends React.Component {
    state = {
        city: '',
        data: [] }

    constructor(props) {
        super(props);
    }

    getResults = (city, data) => {
        this.setState({
            city: city,
            data: data
        });
    }

    render() {
        return (
            <>
                <Navigation/>
                <Container fluid>
                    <Row>
                        {/*<CreateItineraryForm/>*/}
                        <Col sm={{span: 3}}>
                            <PlannerInput setResult={this.getResults}/>
                            {/*<Sidebar/>*/}
                        </Col>
                        <Col sm={{span: 9}}>
                            <WeatherTable city={this.state.city} forecasts={this.state.data}/>
                        </Col>
                    </Row>
                </Container>
            </>
        )
    }
}

export default App;
