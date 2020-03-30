import React from "react";
import "../../Assets/styles/header.css";

export default class Header extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            test: 1
        };
    }

    render() {
        return (
            <header>
                <div className="header">
                    <div className="loghme-icon  ml-auto">
                        <a href="home.html">
                            <img alt="tmp" src={require("../../Assets/images/LOGO.png")}/>
                        </a>
                    </div>
                    <div className="cart m-2">
                        <div className="cart-icon">
                            <i className="flaticon-smart-cart"></i>
                        </div>
                        <div className="cart-items">
                            <span>۳</span>
                        </div>
                    </div>
                    <div className="profile m-2">
                        <a href="profile.html">
                            حساب کاربری
                        </a>
                    </div>
                    <div className="logout m-2">
                        خروج
                    </div>
                </div>
            </header>
        );
    }
}