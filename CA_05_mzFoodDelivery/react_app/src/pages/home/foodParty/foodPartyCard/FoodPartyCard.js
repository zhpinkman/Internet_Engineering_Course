import React from "react";
import "../foodParty.css";
import {enToFaNumber} from "../../../../utils/utils"
import PrimaryButton from "../../../general/button/PrimaryButton";
import StockTag from "./StockTag";
import Cart from "../../../general/modals/Cart";
import Modal from "react-bootstrap/Modal";
import Food from "../../../general/modals/Food";

export default class FoodPartyCard extends React.Component {
    constructor(props) {
        super(props);
        this.handleModalShow = this.handleModalShow.bind(this);
        this.handleModalClose = this.handleModalClose.bind(this);

        this.state = {
            modalShow: false,
        };
    }

    handleModalClose() {
        this.setState({ modalShow: false });

    }

    handleModalShow() {
        this.setState({ modalShow: true });

    }

    render() {
        return (
            <div className="food-party-card">

                <div className="row m-0">
                    <div className="col-5 img-wrapper">
                        <img alt="tmp" src={this.props.partyFood.image} className="img"/>
                    </div>
                    <div className="col-7 justify-content-center pt-2">
                        <span className="title-text float-right text-right w-100">{this.props.partyFood.name}</span>
                        <br/>
                        <span className="rating-star-text float-right">{enToFaNumber(this.props.partyFood.popularity * 5)}</span>
                        <span className="rating-star-img float-right">⭐</span>
                    </div>
                </div>
                <div className="row price-text">
                    <div className="col-6 text-left old-price">
                        {enToFaNumber(this.props.partyFood.oldPrice)}
                    </div>
                    <div className="col-6 text-right">
                        {enToFaNumber(this.props.partyFood.price)}
                    </div>
                </div>
                <div className="row m-0 mt-4 mx-2">
                    <div className="col-6 justify-content-center">
                        <StockTag stock={this.props.partyFood.count} />
                    </div>
                    <div className="col-6 justify-content-center" onClick={this.handleModalShow}>
                        <PrimaryButton text={"خرید"} onClick={this.handleModalShow} disabled={this.props.partyFood.count === 0}/>
                    </div>
                </div>
                <hr className="dash" />
                <div className="row justify-content-center food-party-card-subtitle">
                    {this.props.partyFood.restaurantName}
                </div>

                <Modal show={this.state.modalShow} onHide={this.handleModalClose} centered>
                    <Food food={this.props.partyFood}/>
                </Modal>
            </div>

        );
    }
}