import * as React from "react";
import PropTypes from "prop-types";

export default class Orderitem extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div className="order-item">
                <div className="row">
                    <div className="col-2">
                        <div className="order-index">
                                    <span>
                                        {this.props.index}
                                    </span>
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="order-restaurant">
                                <span>
                                    {this.props.order.cart.restaurant.name}
                                </span>
                        </div>
                    </div>
                    <div className="col-4">
                        <div className="order-status">
                            <div className="status-box delivery">
                                        <span>
                                            {this.props.order.status}
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

Orderitem.propTypes = {
    index: PropTypes.number,
    order: PropTypes.shape({
        cart: PropTypes.shape({
            restaurant: PropTypes.shape({
                name: PropTypes.string
            })
        }),
        status: PropTypes.string
    })
};