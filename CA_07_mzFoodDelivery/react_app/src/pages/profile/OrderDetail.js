import * as React from "react";
import "../../Assets/styles/order-detail-styles.scss";
import {enToFaNumber} from "../../utils/utils";
import PropTypes from "prop-types";


export default class OrderDetail extends React.Component {
    constructor(props) {
        super(props);
        this.state = {}

        this.calcTotalPrice = this.calcTotalPrice.bind(this);
    }


    calcTotalPrice() {
        return this.props.cart.reduce((total, item) => {
            return total + item.foodPrice * item.quantity;
        }, 0);
    }


    render() {
        return (
            <div className="wrapper">
                <div className="restaurant-title">
                    <span>
                        {this.props.restaurant.name}
                    </span>
                </div>

                <div className="separator">

                </div>
                <div className="food-table">
                    <table className="table table-bordered">
                        <thead className="black white-text">
                        <tr>
                            <th className="col-1">ردیف</th>
                            <th className="col-9">نام غذا</th>
                            <th className="col-1">تعداد</th>
                            <th className="col-1">قیمت</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.props.cart && this.props.cart.length > 0 &&
                                this.props.cart.map((cartItem, index) => {
                                    return (
                                        <tr>
                                            <th scope="row">{enToFaNumber(index + 1)}</th>
                                            <td>{cartItem.foodName}</td>
                                            <td>{enToFaNumber(cartItem.quantity)}</td>
                                            <td>{enToFaNumber(cartItem.foodPrice * cartItem.quantity)}</td>
                                        </tr>
                                    )
                                })
                        }
                        {
                            this.props.cart.length === 0 && (
                                <div className="text-center">
                                    خالی
                                </div>
                            )
                        }
                        </tbody>
                    </table>
                </div>

                <div className="price-wrapper">
                    <div className="price">
                        <span>
                            {enToFaNumber(this.calcTotalPrice())}
                            تومان
                        </span>
                        <span>
                             جمع کل:‌
                        </span>
                    </div>
                </div>
            </div>
        );
    }
}


OrderDetail.propTypes = {
    cart: PropTypes.shape({
        restaurant: PropTypes.shape({
            name: PropTypes.string
        }),
        size: PropTypes.number,
        cartItems: PropTypes.arrayOf({
            food: PropTypes.shape({
                name: PropTypes.string,
                price: PropTypes.number
            }),
            quantity: PropTypes.number

        }),
        totalPrice: PropTypes.number
    })
}