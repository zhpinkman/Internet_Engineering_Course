import React from "react";
import "../../Assets/styles/header.css";
import Modal from 'react-bootstrap/Modal'
import Cart from "./modals/Cart";
import {Link} from "react-router-dom";

export default class Header extends React.Component{
    constructor(props) {
        super(props);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            show: false,
        };
    }

    handleClose() {
        this.setState({ show: false });

    }

    handleShow() {
        this.setState({ show: true });

    }



    render() {
        return (
            <div>
            <header>
                <div className="header">
                    <div className="loghme-icon  ml-auto">
                        <Link to="/">
                            <img alt="tmp" src={require("../../Assets/images/LOGO.png")}/>
                        </Link>
                    </div>
                    <div className="cart m-2" onClick={this.handleShow}>
                        <div className="cart-icon">
                            <i className="flaticon-smart-cart"></i>
                        </div>
                        <div className="cart-items">
                            <span>۳</span>
                        </div>
                    </div>
                    <div className="profile m-2">
                        <Link to="/profile">
                            حساب کاربری
                        </Link>
                    </div>
                    <div className="logout m-2">
                        خروج
                    </div>
                </div>
            </header>
                <Modal show={this.state.show} onHide={this.handleClose}>
                  <Cart/>
                </Modal>

            </div>

        );
    }
}