import React from "react";

export default class RestaurantCard extends React.Component{
    render() {
        return (
            <div className="col-xl-3 col-lg-6">
                <div className="card food-item-container">
                    <div className="row img-wrapper">
                        <img alt="tmp" src="./../Assets/images/italian-pizza-square.jpg" className="img" />
                    </div>
                    <div className="row title-wrapper">
                        <span className="title-text"> پیتزا اعلا</span>
                        <span className="rating-star-text">۵</span>
                        <span className="rating-star-img">&starf;</span>
                    </div>
                    <div className="row price-wrapper">۳۹۰۰۰ تومان</div>
                    <div className="row card-button-wrapper">
                        <button type="button" className="btn btn-warning btn-default btn-orange">افزودن به سبد
                            خرید
                        </button>
                    </div>
                </div>
            </div>
        );
    }
}