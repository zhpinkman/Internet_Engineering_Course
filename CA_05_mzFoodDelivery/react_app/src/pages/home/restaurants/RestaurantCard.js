import React from "react";
import "./restaurants.css";
import {Link, Redirect} from 'react-router-dom'

export default class RestaurantCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            redirect: false
        }
    }


    render() {
        return (
            <div className="col-xl-3 col-lg-4 col-md-6 col-sm-12 px-4">
                <div className="card restaurant-item-container">
                    <div className="row img-wrapper">
                        <img alt="tmp" src={this.props.restaurant.logo} className="img"/>
                    </div>
                    <div className="row title-wrapper">
                        <span className="title-text">{this.props.restaurant.name}</span>
                    </div>
                    {/*<Link to={"/restaurant?id=s"}>*/}
                    <div className="row card-button-wrapper">
                            <button type="button" className="btn btn-warning btn-default btn-orange"
                                    onClick={() => (window.location = "/restaurants?id=" + this.props.restaurant.id)}>
                                نمایش منو
                            </button>
                    </div>
                    {/*</Link>*/}
                </div>
            </div>
        );
    }
}