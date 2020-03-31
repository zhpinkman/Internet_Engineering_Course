import * as React from "react";
import "../../Assets/styles/cart-styles.css";


export default class Cart extends React.Component {

    constructor(props) {
        super(props);
    }


    render() {
        return (
            <div>
                <div className="cart-container">
                        <span className="cart-title">
                            سبد خرید
                        </span>

                    <div className="cart-list-wrapper d-flex justify-content-center">

                        <div className="cart-list-item-wrapper d-flex flex-column">
                            <div className="d-flex flex-row justify-space-between">
                                <div className="align-self-start">
                                    پیتزا اعلا
                                </div>
                                <div className="align-self-end mr-auto">
                                    <a><i className="glyph-icon flaticon-minus cart-icon"></i></a>
                                    <span className="p-1">۳۰</span>
                                    <a><i className="glyph-icon flaticon-plus cart-icon"></i></a>
                                </div>
                            </div>
                            <div className="price d-flex flex-row justify-content-end">
                                ۳۹۰۰۰ تومان
                            </div>
                        </div>
                        <div className="break"></div>
                        <div className="cart-list-item-wrapper d-flex flex-column">
                            <div className="d-flex flex-row justify-space-between">
                                <div className="align-self-start">
                                    پیتزا نیمه اعلا
                                </div>
                                <div className="align-self-end mr-auto">
                                    <a><i className="glyph-icon flaticon-minus cart-icon"></i></a>
                                    <span className="p-1">۳۰</span>
                                    <a><i className="glyph-icon flaticon-plus cart-icon"></i></a>
                                </div>
                            </div>
                            <div className="price d-flex flex-row justify-content-end">
                                ۳۹۰۰۰ تومان
                            </div>
                        </div>
                    </div>

                    <div className="total-price">
                        جمع کل:
                        <span>
                                ۳۹۰۰۰ تومان
                            </span>
                    </div>

                    <button type="button" className="btn btn-default btn-cyan">
                        تایید نهایی
                    </button>


                </div>
            </div>
        );
    }
}