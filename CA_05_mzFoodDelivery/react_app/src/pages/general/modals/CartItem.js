import {enToFaNumber} from "../../../utils/utils";
import * as React from "react";
import UserService from "../../../services/UserService";
import {toast} from "react-toastify";
import cartRefresh from "../../../services/MessageService";


export default class CartItem extends React.Component {
    constructor(props) {
        super(props);
        this.state = {

        };

        this.incFood = this.incFood.bind(this);
        this.decFood = this.decFood.bind(this);
    }


    componentDidMount() {
        toast.configure({
            rtl: true
        });
        console.log(this.props);
    }

    incFood() {
        console.log(this.props)
        UserService.addToCart(this.props.cartItem.restaurant.id, this.props.cartItem.food.name).then(data => {
            toast.success('عملیات با موفقیت انجام شد', {
                position: "top-center"
            });
            cartRefresh.next();

        }).catch(error => {
            toast.error(error.toString(), {
                position: "top-center",
            });
        })
    }


    decFood() {
        UserService.addToCart(this.props.cartItem.restaurant.id, this.props.cartItem.food.name, -1).then(data => {
            toast.success('عملیات با موفقیت انجام شد', {
                position: "top-center"
            });
            cartRefresh.next();

        }).catch(error => {
            toast.error(error.toString(), {
                position: "top-center",
            });
        })

    }


    render() {
        return (
            <>
                <div className="cart-list-item-wrapper d-flex flex-column">
                    <div className="d-flex flex-row justify-space-between">
                        <div className="align-self-start">
                            {this.props.cartItem.food.name}
                        </div>
                        <div className="align-self-end mr-auto d-flex align-items-center">
                            <div onClick={this.decFood}><i className="glyph-icon flaticon-minus cart-icon"></i></div>
                            <span className="p-1">
                                                {enToFaNumber(this.props.cartItem.quantity)}
                                            </span>
                            <div onClick={this.incFood} ><i className="glyph-icon flaticon-plus cart-icon"></i></div>
                        </div>
                    </div>
                    <div className="price d-flex flex-row justify-content-end">
                        {enToFaNumber(this.props.cartItem.food.price * this.props.cartItem.quantity)}
                        تومان
                    </div>
                </div>
                <div className="break"></div>
            </>
        );
    }
}