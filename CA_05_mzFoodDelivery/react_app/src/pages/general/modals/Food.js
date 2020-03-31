import * as React from "react";
import "../../../Assets/styles/food-modal-styles.scss";


export default class Food extends  React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }


    render() {
        return (
            <div>
                <div className="top-part">
                    <div className="restaurant-name">
                        رستوران خامس
                    </div>
                    <div className="food-wrapper">
                        <div className="food-img-wrapper">
                            <div className="food-img">
                                <img src={require("../../../Assets/images/italian-pizza-square.jpg")} alt="temp" />
                            </div>
                        </div>
                        <div className="food-detail-wrapper">
                            <div className="food-detail">
                                <div className="name-rate">
                                    <div className="food-name">
                                        پیتزا
                                    </div>
                                    <div className="food-rate">
                                        ۵
                                        <i className="flaticon-star"></i>
                                    </div>
                                </div>
                                <div className="food-description">
                                    پخته‌شده با مرغوب‌ترین مواد اولیه
                                </div>
                                <div className="food-prices">
                                    <div className="old-price">
                                        ۳۹۰۰۰
                                    </div>
                                    <div className="curr-price">
                                        ۲۹۰۰۰ تومان
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div className="separator-border"></div>


                <div className="bottom-part">
                    <div className="remaining">
                        <span> موجودی : </span>
                        <span> ۳ </span>
                    </div>
                    <div className="actions">
                        <div className="inc-amount mx-1">
                            <i className="flaticon-plus"></i>
                        </div>
                        <div className="amount mx-1">
                            ۲
                        </div>
                        <div className="dec-amount mx-1">
                            <i className="flaticon-minus"></i>
                        </div>
                        <div className="add-to-cart mx-2">
                            <span>
                                افزودن به سبد خرید
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}