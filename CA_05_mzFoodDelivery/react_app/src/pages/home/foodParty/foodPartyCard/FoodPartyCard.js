import React from "react";
import "../foodParty.css";
import enToFaNumber from "../../../../utils/utils"
import PrimaryButton from "../../../general/button/PrimaryButton";
import StockTag from "./StockTag";

export default class FoodPartyCard extends React.Component {
    render() {
        return (
            <div className="food-party-card">

                <div className="row m-0">
                    <div className="col-6 img-wrapper">
                        <img alt="tmp" src={this.props.partyFood.image} className="img"/>
                    </div>
                    <div className="col-6 justify-content-center pt-2">
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
                    <div className="col-6 justify-content-center">
                        <PrimaryButton text={"خرید"} disabled={this.props.partyFood.count === 0}/>
                    </div>
                </div>
                <hr className="dash" />
                <div className="row justify-content-center food-party-card-subtitle">
                    {this.props.partyFood.restaurantName}
                </div>

            </div>

        );
    }
}