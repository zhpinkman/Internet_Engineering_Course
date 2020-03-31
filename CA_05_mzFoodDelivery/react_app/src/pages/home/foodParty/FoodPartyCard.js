import React from "react";
import "./foodParty.css";
import enToFaNumber from "../../../utils/utils"
import PrimaryButton from "../../general/button/PrimaryButton";

export default class FoodPartyCard extends React.Component {
    render() {
        return (
            <div className="food-party-card">

                {/*<div className="row img-wrapper">*/}
                {/*    <img alt="tmp" src={require("./../../../Assets/images/italian-pizza-square.jpg")} className="img"/>*/}
                {/*</div>*/}
                {/*<div className="row title-wrapper">*/}
                {/*    <span className="title-text">پیتزا نیمه اعلا</span>*/}
                {/*    <span className="rating-star-text">۴</span>*/}
                {/*    <span className="rating-star-img">&starf;</span>*/}
                {/*</div>*/}
                {/*<div className="row price-wrapper">۳۹۰۰۰ تومان</div>*/}
                {/*<div className="row card-button-wrapper">*/}
                {/*    <button type="button" className="btn btn-warning btn-default btn-orange"*/}
                {/*            disabled>ناموجود*/}
                {/*    </button>*/}
                {/*</div>*/}

                <div className="row m-0">
                    <div className="col-6 img-wrapper">
                        <img alt="tmp" src={this.props.partyFood.image} className="img"/>
                    </div>
                    <div className="col-6 justify-content-center">
                        <br/>
                        <span className="title-text float-right text-right w-100">{this.props.partyFood.name}</span>
                        <br/>
                        <span className="rating-star-text float-right">{enToFaNumber(this.props.partyFood.popularity * 5)}</span>
                        <span className="rating-star-img float-right">⭐</span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-6 text-left old-price">
                        {enToFaNumber(this.props.partyFood.oldPrice)}
                    </div>
                    <div className="col-6 text-right">
                        {enToFaNumber(this.props.partyFood.price)}
                    </div>
                </div>
                <div className="row">
                    <div className="col-6 justify-content-center">
                        <PrimaryButton text={"خرید"} disabled={this.props.partyFood.count == 0}/>
                    </div>
                    <div className="col-6 justify-content-center">
                        30000
                    </div>

                </div>
                <div className="row justify-content-center">
                    {this.props.partyFood.restaurantName}
                </div>

            </div>

        );
    }
}