import React from "react";
import "./foodParty.css";

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
                        <img alt="tmp" src={require("./../../../Assets/images/italian-pizza-square.jpg")} className="img"/>
                    </div>
                    <div className="col-6 justify-content-center">
                        <br/>
                        <span className="title-text float-right text-right">پیتزا نیمه اعلا</span>
                        <br/>
                        <span className="rating-star-text float-right">۴</span>
                        <span className="rating-star-img float-right">⭐</span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-6 text-left">
                        29000
                    </div>
                    <div className="col-6 text-right">
                        30000
                    </div>
                </div>

            </div>

        );
    }
}