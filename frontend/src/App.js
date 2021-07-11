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

function App() {
    const [movies, setMovies] = useState([]);
    const [err, setErr] = useState("");
    const [errBool, setErrBool] = useState(false);
    const [loading, setLoading] = useState(true);
    let URL = `https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}&language=en-US&page=1`;

    const fetchMovies = (url) => {
        axios
            .get(
                url
            )
            .then(response => {
                setMovies(response.data.results);
                setLoading(false);
            })
            .catch(err => {
                setErr(err.message);
                setErrBool(true);
                setLoading(false);
            });
    }

    React.useEffect(() => {
        fetchMovies(URL);
    }, [URL]);

    return (
        <>
            <Navigation/>
            <Container fluid>
                <Row>
                    <Col sm={{span: 4}} className="mh-100" style={{overflowY:'scroll'}}>
                        <PlannerInput/>
                    </Col>
                    <Col sm={{span: 8 }} className="mh-100" style={{overflowY:'scroll'}}>
                        <WeatherTable movies={movies}/>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default App;
