import React, { useState } from "react";
import {
    Navbar,
    Nav,
    NavDropdown,
    Form,
    FormControl,
    Button
} from "react-bootstrap";

// import ModalLogin from "./ModalLogin";

const Navigation = () => {
    const [modalShow, setModalShow] = useState(false);

    return (
        <Navbar className="navbar navbar-expand-md navbar-light shadow-sm" sticky="top" bg="light">
            <Navbar.Brand href="#home">Travel Planner</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />

            {/*<a className="navbar-brand" href="https://front.codes/" target="_blank"><img*/}
            {/*    src="https://assets.codepen.io/1462889/fcy.png" alt=""/></a>*/}

            <button className="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav ml-auto py-4 py-md-0">
                    <li className="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
                        <a className="nav-link" href="#">Contact</a>
                    </li>
                </ul>
            </div>

        </Navbar>
);
};
export default Navigation;
