import React from "react";
import {enToFaNumber} from "../../utils/utils";
import Cart from "../general/modals/Cart";
import Modal from "react-bootstrap/Modal";
import Food from "../general/modals/Food";
import "./restaurant.css";

export default class RestaurantMenuCard extends React.Component{

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
            <>
            <div className="col-xl-4 col-lg-6">
                <div className="card food-item-container">
                    <div className="row img-wrapper">
                        <img alt="tmp" src={this.props.food.image}
                             className="img"/>
                    </div>
                    <div className="row title-wrapper">
                        <span className="title-text">{this.props.food.name}</span>
                        <span className="rating-star-text">{enToFaNumber(this.props.food.popularity * 5)}</span>
                        <span className="rating-star-img">★</span>
                    </div>
                    <div className="row price-wrapper">{enToFaNumber(this.props.food.price)} تومان</div>
                    <div className="row card-button-wrapper">
                        {/*<button type="button" className="btn btn-warning btn-default btn-orange"*/}
                        {/*        disabled>ناموجود*/}
                        {/*</button>*/}
                        <button type="button" onClick={this.handleShow}
                                className="btn btn-warning btn-default btn-orange">افزودن به سبد
                            خرید
                        </button>
                    </div>
                </div>
            </div>
                <Modal show={this.state.show} onHide={this.handleClose} centered>
                    <Food food={this.props.food} />
                </Modal>
            </>

        );
    }
}