import * as React from "react";
import "../../../Assets/styles/cart-styles.css";
import UserService from "../../../services/UserService";
import {enToFaNumber} from "../../../utils/utils";


export default class Cart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cart: {}
        }
    }


    componentDidMount() {
        UserService.getCart().then(cart => {
            this.setState({cart: cart.data});
            console.log(this.state.cart);
        })
    }


    render() {
        return (
            <div>
                <div className="card cart-container">
                    {this.state.cart && this.state.cart.cartItems && (
                        <span className="cart-title">
                            سبد خرید
                        </span>
                    ) }
                    {this.getCart()}
                </div>
            </div>
        );
    }

    getCart() {
        if (this.state.cart && this.state.cart.cartItems)
        return (
        <>
            <div className="cart-list-wrapper d-flex justify-content-center">
                        {this.state.cart.cartItems.map(cartItem => {
                            return (
                                <>
                                <div className="cart-list-item-wrapper d-flex flex-column">
                                    <div className="d-flex flex-row justify-space-between">
                                        <div className="align-self-start">
                                            {cartItem.food.name}
                                        </div>
                                        <div className="align-self-end mr-auto">
                                            <a><i className="glyph-icon flaticon-minus cart-icon"></i></a>
                                            <span className="p-1">
                                                {cartItem.quantity}
                                            </span>
                                            <a><i className="glyph-icon flaticon-plus cart-icon"></i></a>
                                        </div>
                                    </div>
                                    <div className="price d-flex flex-row justify-content-end">
                                        {enToFaNumber(cartItem.food.price * cartItem.quantity)}
                                        تومان
                                    </div>
                                </div>
                                <div className="break"></div>
                                </>
                            )
                        })}

            </div>
            <div className="total-price">
                جمع کل:
                <span>
                    {enToFaNumber(this.state.cart.totalPrice || 0)}
                تومان
                            </span>
            </div>

            <button type="button" className="btn btn-default btn-cyan">
                تایید نهایی
            </button>

        </>
        );
        else
            return (
            <div className="spinner-grow text-danger food-party-loading-box align-self-center" role="status">
                <span className="sr-only">Loading...</span>
            </div>
            )
    }

}


