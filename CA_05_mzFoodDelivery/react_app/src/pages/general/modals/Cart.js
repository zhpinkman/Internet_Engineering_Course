import * as React from "react";
import "../../../Assets/styles/cart-styles.css";
import UserService from "../../../services/UserService";
import {enToFaNumber} from "../../../utils/utils";
import {toast} from "react-toastify";
import CartItem from "./CartItem";
import {cartRefresh, creditRefresh} from "../../../services/subjects/MessageService";
import {OK, TOAST_MESSAGE_OK} from "../../../config/config";


export default class Cart extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cart: {},
            isLoading: true
        }
    }


    componentDidMount() {
        toast.configure({rtl: true, className: "text-center"});
        this.getUserCart();
        cartRefresh.asObservable().subscribe(() => {
            this.getUserCart();
        })
    }

    async getUserCart() {
        this.setState({isLoading: true});
        UserService.getCart().then(cart => {
            this.setState({
                cart: cart.data,
                isLoading: false
            });
            console.log(this.state.cart);
        })
    }

    finalizeOrder() {
        this.setState({isLoading: true});
        UserService.finalizeOrder().then(data => {
            if(data === OK) {
                toast.success(TOAST_MESSAGE_OK);
                cartRefresh.next();
                creditRefresh.next();
            }else{
                toast.error(data);
            }
            this.setState({isLoading: false});
        }).catch(error => {
            toast.error(error.toString());
            this.setState({isLoading: false});
        })
    }

    render() {
        return (
            <div className="cart-wrapper">
                <div className="cart-container">
                    {this.state.cart && this.state.cart.cartItems && (
                        <span className="cart-title">
                            سبد خرید
                        </span>
                    )}
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
                                <CartItem cartItem={cartItem}/>
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

                    <button type="button" className="btn btn-default btn-cyan" onClick={() => (!this.state.isLoading ? this.finalizeOrder() : {/*pass*/})}>
                        تایید نهایی
                        {this.state.isLoading &&
                        <span className="spinner-border mr-2" role="status" aria-hidden="true"/>
                        }
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


